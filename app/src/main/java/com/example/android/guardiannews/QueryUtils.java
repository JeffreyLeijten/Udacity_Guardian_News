package com.example.android.guardiannews;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 20-6-2017.
 */

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static final String test = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":23711,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":2372,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"politics/2017/apr/18/theresa-may-rules-out-participating-in-tv-debates-before-election\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-04-18T17:20:59Z\",\"webTitle\":\"Theresa May rules out participating in TV debates before election\",\"webUrl\":\"https://www.theguardian.com/politics/2017/apr/18/theresa-may-rules-out-participating-in-tv-debates-before-election\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/apr/18/theresa-may-rules-out-participating-in-tv-debates-before-election\",\"isHosted\":false},{\"id\":\"politics/live/2017/mar/21/death-martin-mcguinness-reaction-politics-live\",\"type\":\"liveblog\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-03-21T17:29:43Z\",\"webTitle\":\"Scottish parliament debates call for second independence referendum - Politics live\",\"webUrl\":\"https://www.theguardian.com/politics/live/2017/mar/21/death-martin-mcguinness-reaction-politics-live\",\"apiUrl\":\"https://content.guardianapis.com/politics/live/2017/mar/21/death-martin-mcguinness-reaction-politics-live\",\"isHosted\":false},{\"id\":\"politics/2017/may/31/the-snap-best-worst-reasons-miss-tv-leaders-debates\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-05-31T05:46:02Z\",\"webTitle\":\"The Snap: the best-worst reasons to miss the TV leaders' debates\",\"webUrl\":\"https://www.theguardian.com/politics/2017/may/31/the-snap-best-worst-reasons-miss-tv-leaders-debates\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/may/31/the-snap-best-worst-reasons-miss-tv-leaders-debates\",\"isHosted\":false},{\"id\":\"politics/ng-interactive/2017/may/22/david-squires-on-the-tory-manifesto-leaders-debates-and-hot-takes-on-poverty\",\"type\":\"interactive\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-05-22T11:43:57Z\",\"webTitle\":\"David Squires on â€¦ the Tory manifesto, leaders' debates and hot takes on poverty\",\"webUrl\":\"https://www.theguardian.com/politics/ng-interactive/2017/may/22/david-squires-on-the-tory-manifesto-leaders-debates-and-hot-takes-on-poverty\",\"apiUrl\":\"https://content.guardianapis.com/politics/ng-interactive/2017/may/22/david-squires-on-the-tory-manifesto-leaders-debates-and-hot-takes-on-poverty\",\"isHosted\":false},{\"id\":\"us-news/2016/sep/26/presidential-debates-nixon-kennedy-1960\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2016-09-26T15:57:34Z\",\"webTitle\":\"The Nixon-Kennedy presidential debates: from the archive, 1960\",\"webUrl\":\"https://www.theguardian.com/us-news/2016/sep/26/presidential-debates-nixon-kennedy-1960\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2016/sep/26/presidential-debates-nixon-kennedy-1960\",\"isHosted\":false},{\"id\":\"politics/2017/apr/19/theresa-may-accused-at-pmqs-of-running-scared-from-tv-debates\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-04-19T12:10:28Z\",\"webTitle\":\"Theresa May accused at PMQs of running scared from TV debates\",\"webUrl\":\"https://www.theguardian.com/politics/2017/apr/19/theresa-may-accused-at-pmqs-of-running-scared-from-tv-debates\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/apr/19/theresa-may-accused-at-pmqs-of-running-scared-from-tv-debates\",\"isHosted\":false},{\"id\":\"commentisfree/2017/apr/21/theresa-may-cyncism-election-lynton-crosby-snp-alex-salmond\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2017-04-21T06:00:12Z\",\"webTitle\":\"No dissent, no risks, no TV debates: Mayâ€™s election stall offers only cynicism | Alex Salmond\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2017/apr/21/theresa-may-cyncism-election-lynton-crosby-snp-alex-salmond\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2017/apr/21/theresa-may-cyncism-election-lynton-crosby-snp-alex-salmond\",\"isHosted\":false},{\"id\":\"politics/2017/apr/19/bbc-and-itv-plan-leaders-debates-despite-mays-refusal-to-take-part\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-04-19T14:01:59Z\",\"webTitle\":\"May 'open to other options' after ruling out head-to-head television debates\",\"webUrl\":\"https://www.theguardian.com/politics/2017/apr/19/bbc-and-itv-plan-leaders-debates-despite-mays-refusal-to-take-part\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/apr/19/bbc-and-itv-plan-leaders-debates-despite-mays-refusal-to-take-part\",\"isHosted\":false},{\"id\":\"commentisfree/2017/apr/19/theresa-may-chickening-out-tv-debates-shameful-caroline-lucas\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2017-04-19T12:54:55Z\",\"webTitle\":\"Chickening out of TV debates is shameful. Why is May avoiding us? | Caroline Lucas\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2017/apr/19/theresa-may-chickening-out-tv-debates-shameful-caroline-lucas\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2017/apr/19/theresa-may-chickening-out-tv-debates-shameful-caroline-lucas\",\"isHosted\":false},{\"id\":\"culture/shortcuts/2017/feb/06/the-next-doctor-who-a-black-bond-the-pop-culture-debates-that-never-end\",\"type\":\"article\",\"sectionId\":\"culture\",\"sectionName\":\"Culture\",\"webPublicationDate\":\"2017-02-06T07:00:20Z\",\"webTitle\":\"The next Doctor Who, a black Bond â€¦ theÂ pop culture debates that never end\",\"webUrl\":\"https://www.theguardian.com/culture/shortcuts/2017/feb/06/the-next-doctor-who-a-black-bond-the-pop-culture-debates-that-never-end\",\"apiUrl\":\"https://content.guardianapis.com/culture/shortcuts/2017/feb/06/the-next-doctor-who-a-black-bond-the-pop-culture-debates-that-never-end\",\"isHosted\":false}]}}";

    public static List<Article> fetchArticleData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        Log.i(LOG_TAG, "Extract from JSON");

        List<Article> articles = extractFeatureFromJson(jsonResponse);

        return articles;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Guardian JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<Article> extractFeatureFromJson(String articleJSON) {

        if (TextUtils.isEmpty(articleJSON)) {
            Log.e(LOG_TAG, "JSON is empty");
            return null;
        }
        Log.i(LOG_TAG, "JSON not empty");

        ArrayList<Article> articles = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(articleJSON);
            JSONObject response = baseJsonResponse.getJSONObject("response");

            if (response.getInt("pageSize") == 0){
                return articles;
            }

            JSONArray resultsArray = response.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject currentarticle = resultsArray.getJSONObject(i);

                String title = currentarticle.getString("webTitle");

                String section = currentarticle.getString("sectionName");

                try{
                String publicationDateString = currentarticle.getString("webPublicationDate");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'");
                Date publicationDate = simpleDateFormat.parse(publicationDateString);

                String url = currentarticle.getString("webUrl");

                Article article = new Article(title, section, publicationDate, url);

                articles.add(article);
                } catch (ParseException e){
                    Log.e(LOG_TAG, "Problem parsing date: " + e);
                }
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the article JSON results", e);
        }
        return articles;
    }
}
