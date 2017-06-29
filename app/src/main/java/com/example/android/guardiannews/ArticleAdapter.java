package com.example.android.guardiannews;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jeffrey on 26-6-2017.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Activity context, ArrayList<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        Article currentArticle = getItem(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.title);
        titleTextView.setText(currentArticle.getTitle());

        TextView sectionTextView = (TextView) convertView.findViewById(R.id.section);
        sectionTextView.setText(currentArticle.getSection());

        TextView PublicationDateTextview = (TextView) convertView.findViewById(R.id.publication_date);
        PublicationDateTextview.setText(publicationDateToSring(currentArticle.getPublicationDate()));

        return convertView;
    }

    /**
     * Formats a date to show either the full DateTime or, if the date is today, just the Time.
     * @param publicationDate The date the article was published
     * @return if publicationDate is today just the Time else the Date and Time in local format
     */
    String publicationDateToSring(Date publicationDate){
        DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        //if publicationDate is today
        if ( dateFormat.format(publicationDate).equals(dateFormat.format(new Date())) ){
            return timeFormat.format(publicationDate);
        } else {
            return dateTimeFormat.format(publicationDate);
        }

    }

}
