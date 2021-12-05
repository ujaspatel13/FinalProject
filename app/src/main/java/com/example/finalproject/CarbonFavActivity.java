package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;


public class CarbonFavActivity extends AppCompatActivity {
    private ArrayList<CarbonDioxideModel> mCarbonList;
    private CarbonAdapter mCarAdapter;
    private RecyclerView mRecyclerView;
    private CarbonAdapter.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_fav);
        final DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        mCarbonList = helper.getSavedSoccerGame();
        LoadFav();
    }
    private void LoadFav()
    {
        mRecyclerView = findViewById(R.id.carbon_recycle_fav_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        mCarbonList = new ArrayList<>();
        mCarbonList = helper.getSavedSoccerGame();
        mCarAdapter = new CarbonAdapter(CarbonFavActivity.this,mCarbonList,null);
        mRecyclerView.setAdapter(mCarAdapter);
    }
}