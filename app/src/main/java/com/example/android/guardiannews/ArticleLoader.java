package com.example.android.guardiannews;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Jeffrey on 28-6-2017.
 */

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {
    private static final String LOG_TAG = ArticleLoader.class.getSimpleName();

    private String url;

    public ArticleLoader(Context context, String url) {
        super(context);
        this. url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        Log.i(LOG_TAG, "Load in Background");
        if (url == null) {
            Log.i(LOG_TAG, "URL is null");
            return null;
        }

        List<Article> articles = QueryUtils.fetchArticleData(url);
        return articles;
    }
}
