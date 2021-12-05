package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity for display Saved Rss Feed
 *
 * This activity is used to display Saved RSS feed in recycler view from SQLLite.
 *
 * @author ujas1
 * @version 4.0
 * @since 1.0
 */
public class CarbonDetailActivityFragment extends DialogFragment {
    public Bundle extras;
    private String Title="";
    private String PubDate="";
    private String Thumbnail="";
    private String URL="";
    private String Guid="";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.activity_carbon_details,container,false);
        extras = getArguments();
        TextView mtitle;

        if(extras!=null) {
            mtitle = (TextView) rootView.findViewById(R.id.txt_carbon_dt_title);
            mtitle.setText("Title - " + extras.getString("Title"));
            Title=extras.getString("Title");
            PubDate=extras.getString("PubDate");
            Thumbnail=extras.getString("Thumbnail");
            URL=extras.getString("URL");
            Guid=extras.getString("Guid");
        }

        final DatabaseHelper helper = new DatabaseHelper(getActivity().getApplicationContext());
        rootView.findViewById(R.id.btnCarbonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (helper.insertCarbonDioxide(Title,PubDate,Thumbnail,URL,Guid)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Saved To DB", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "NOT Saved", Toast.LENGTH_LONG).show();
                }

            }
        });
        rootView.findViewById(R.id.btnCarbonOpen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebView webView = (WebView) getActivity().findViewById(R.id.webvidedetail);
                webView.getSettings().setJavaScriptEnabled(true);

                webView.loadUrl(URL);
                dismiss();
            }
        });
        return rootView;
    }
}