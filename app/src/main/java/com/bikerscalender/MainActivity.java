package com.bikerscalender;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView eventsList;
    private EventListAdapter eventListAdapter;
    public static Integer[] mThumbIds = {
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d , R.drawable.e , R.drawable.f , R.drawable.g , R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l,
            R.drawable.m, R.drawable.n, R.drawable.m, R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v, R.drawable.w,
            R.drawable.x, R.drawable.y, R.drawable.z, R.drawable.ab, R.drawable.ac, R.drawable.ad, R.drawable.ae, R.drawable.af, R.drawable.ag, R.drawable.ah, R.drawable.ai,
            R.drawable.aj, R.drawable.ak, R.drawable.al, R.drawable.am, R.drawable.an, R.drawable.ao, R.drawable.ap, R.drawable.aq, R.drawable.ar, R.drawable.as,
            R.drawable.au, R.drawable.av
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventsList = (RecyclerView) findViewById(R.id.events_list);
        eventListAdapter = new EventListAdapter(this, getEventListData());
        eventsList.setAdapter(eventListAdapter);
        eventsList.setLayoutManager(new LinearLayoutManager(this));

        final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Tourer");
        loadBackdrop();
    }



    public static List<EventListData> getEventListData(){
        List<EventListData> eventListData = new ArrayList<>();
        String[] titles = {"BIKERS vs WILD ( Season - 11 ) - SAACH PASS", "BIKERS vs WILD ( Season - 11 ) - SAACH PASS", "BIKERS vs WILD ( Season - 11 ) - SAACH PASS", "BIKERS vs WILD ( Season - 11 ) - SAACH PASS"};
        String[] from = {"AB", "BC", "CD", "EF"};
        String[] to = {"B", "C", "D", "F"};
        String[] startDate = {"20-Dec-2014 17:00", "21-Dec-2014 17:00", "23-Dec-2014 17:00", "24-Dec-2014 17:00"};
        String[] totalTime = {"17d 3h 5m 6s", "17d 3h 5m 6s", "17d 3h 5m 6s", "17d 3h 5m 6s", "17d 3h 5m 6s"};
        for(int i=0; i<titles.length; i++){
            EventListData current = new EventListData();
            current.title = titles[i];
            current.from = from[i];
            current.to = to[i];
            current.start_date = startDate[i];
            current.total_time = totalTime[i];
            eventListData.add(current);
        }
        return eventListData;
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);

        final int[] i = {0};

        new Runnable(){

            @Override
            public void run(){
                Glide.with( getBaseContext() ).load(mThumbIds[i[0]]).centerCrop().into(imageView);
                i[0]++;
                if(i[0] >= mThumbIds.length){
                    i[0] = 0;
                }
                imageView.postDelayed(this, 20000); //set to go off again in 3 seconds.
            }
        }.run();
    }
}