package com.example.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CarbonDioxideActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CarbonAdapter.RecyclerViewClickListener listener;
    private CarbonAdapter mCarbonAdapter;
    private ArrayList<CarbonDioxideModel> mCarbonList;
    private ProgressDialog progressDialog;
    SharedPreferences sharedpreferences;
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> rssItemList = new ArrayList<>();
    Parser rssParser = new Parser();
    List<CarbonDioxideModel> rssItems = new ArrayList<>();
    //Define XML reading tags
    private static String TAG_TITLE = "title";
    private static String TAG_LINK = "link";
    private static String TAG_PUB_DATE = "pubDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon);
/**
 * Initialize variables
 */
        mRecyclerView = findViewById(R.id.carbon_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

/**
 * Start Asynce task
 */
        new LoadRSSFeedItems().execute("https://www.carboninterface.com/api/v1/vehicle_makes");
        Button btnFav = (Button) findViewById(R.id.btnCarbonFav);
        /**
         * Execude button click listener
         */
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarbonDioxideActivity.this, CarbonFavActivity.class);
                startActivity(intent);
            }
        });
    }

    public class LoadRSSFeedItems extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CarbonDioxideActivity.this);
            progressDialog.setMessage("Fetching The Data....");
            progressDialog.show();
            mCarbonList= new ArrayList<CarbonDioxideModel>();
            rssItemList=new ArrayList<HashMap<String, String>>();
        }

        @Override
        protected String doInBackground(String... args) {
            /**
             * rss link url
             */
            String rss_url = args[0];
            Log.d("rss_url", args[0]);
            /**
             * list of rss items
             */
            rssItems = rssParser.getRSSFeedItems(rss_url);
            Log.d("rssItems",rssItems.toString());

            for (final CarbonDioxideModel item : rssItems) {
                /**
                 * creating new HashMap
                 */
                if (item.getURL().toString().equals(""))
                    break;
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(TAG_TITLE, item.getTitle());
                map.put(TAG_LINK, item.getURL());
                map.put(TAG_PUB_DATE, item.getPubDate());
                /**
                 * adding HashList to ArrayList
                 *                 rssItemList.add(map);
                 */
                mCarbonList.add(new CarbonDioxideModel(item.getTitle(),item.getPubDate(),item.getThumbnail(),item.getURL(),""));
                setOnClickListner();
            }
            /**
             * updating UI from Background Thread
             */
            runOnUiThread(new Runnable() {
                public void run() {
                    mCarbonAdapter = new CarbonAdapter(CarbonDioxideActivity.this,mCarbonList,listener);
                    mRecyclerView.setAdapter(mCarbonAdapter);
                    progressDialog.dismiss();
                }
            });
            return null;
        }

        protected void onPostExecute(String args) {
            Toast.makeText(getApplicationContext(),"Hello Javatpoint" +mCarbonList.size() ,Toast.LENGTH_SHORT).show();
            Log.d("args", ""+mCarbonList.size());
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Data Loaded", Snackbar.LENGTH_LONG).show();

        }
    }

    private void setOnClickListner() {
        listener = new CarbonAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                CarbonDioxideModel md = mCarbonList.get(position);
                Bundle args = new Bundle();
                args.putString("Title",md.getTitle());
                args.putString("URL",md.getURL());
                args.putString("Thumbnail",md.getThumbnail());
                args.putString("PubDate",md.getPubDate());
                args.putString("Guid",md.getGuid());
                CarbonDetailActivityFragment frg =  new CarbonDetailActivityFragment();
                frg.setArguments(args);
                frg.show(getSupportFragmentManager(),"mycarbonfragment");
            }
        };
    }
}