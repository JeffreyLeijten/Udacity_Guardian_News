package com.example.android.guardiannews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {

    public static final String LOG_TAG = MainActivity.class.getName();
    private final static String USGS_REQUEST_URL = "https://content.guardianapis.com/search?";
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView articleListView = (ListView) findViewById(R.id.list);

        articleListView.setEmptyView(findViewById(R.id.empty_view));
        ((TextView)findViewById(R.id.empty_view)).setText(R.string.no_articles);

        adapter = new ArticleAdapter(this, new ArrayList<Article>());

        articleListView.setAdapter(adapter);

        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article currentArticle = adapter.getItem(position);

                Uri articleUri = Uri.parse(currentArticle.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);

                startActivity(websiteIntent);
            }
        });

        runLoader();
    }

    private void runLoader(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){

            LoaderManager loaderManager = getLoaderManager();

            loaderManager.restartLoader(1, null, this);
        } else {
            ((TextView)findViewById(R.id.empty_view)).setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String pageSize = sharedPrefs.getString(
                getString(R.string.settings_page_size_key),
                getString(R.string.settings_page_size_default));

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("page-size", pageSize);
        uriBuilder.appendQueryParameter("section", "football");
        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("api-key", "test");
        Log.i(LOG_TAG, uriBuilder.toString());
        return new ArticleLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        adapter.clear();

        ((TextView)findViewById(R.id.empty_view)).setText(R.string.no_articles);

        if (articles != null && !articles.isEmpty()) {
            adapter.addAll(articles);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        adapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        } else if(id == R.id.refresh){
            runLoader();
        }
        return super.onOptionsItemSelected(item);
    }
}
