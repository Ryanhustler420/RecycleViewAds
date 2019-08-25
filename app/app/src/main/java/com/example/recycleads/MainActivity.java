package com.example.recycleads;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ITEMS_PER_ADS = 4;
    private static final String BANNER_TEST_AD_ID = "ca-app-pub-3940256099942544/6300978111";
    private List<Object> recyclerItems = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // https://developers.google.com/admob/android/test-ads
        // refer this

        // change this on with non-test app id and on manifest as well
        MobileAds.initialize(this, "ca-app-pub-xxxxxxxxxx-xxxxx");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getPersons();
        getBannerAds();
        loadBannerAds();
        adaptor = new RecyclerAdaptor(recyclerItems, this);
        recyclerView.setAdapter(adaptor);
    }

    private void getPersons() {
        recyclerItems.add(new Person("Gaurav", "Gamharia"));
        recyclerItems.add(new Person("Saurav", "Mango"));
        recyclerItems.add(new Person("Sangeeta", "Bistupur"));
        recyclerItems.add(new Person("Sarita", "Sakchi"));
    }

    private void getBannerAds() {
        for (int  i = 0; i < recyclerItems.size(); i+= ITEMS_PER_ADS) {
            final AdView adView = new AdView(MainActivity.this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(BANNER_TEST_AD_ID);
            recyclerItems.add(i, adView);
        }
    }

    private void loadBannerAds() {
        for(int i=0; i<recyclerItems.size(); i++) {
            Object item = recyclerItems.get(i);

            if(item instanceof AdView) {
                AdView adView = (AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }
}
