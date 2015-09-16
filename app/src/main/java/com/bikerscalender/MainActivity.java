package com.bikerscalender;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MainActivity extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {

    public RecyclerView eventsList;
    private EventListAdapter eventListAdapter;

    private int mNewPageNumber = 0;
    private int mMorePageNumber = 0;
    private BGARefreshLayout mRefreshLayout;
    protected ProgressDialog mLoadingDialog;

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

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Tourer");
        loadBackdrop();

        FloatingActionButton searchFAB = (FloatingActionButton) findViewById(R.id.search_button);
        searchFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked pink Floating Action Button", Toast.LENGTH_SHORT).show();
            }
        });
        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.rl_modulename_refresh);
        mRefreshLayout.setDelegate(this);
        BGANormalRefreshViewHolder viewholder = new BGANormalRefreshViewHolder(this, true);
        viewholder.setRefreshingText("");
        viewholder.setPullDownRefreshText("");
        viewholder.setReleaseRefreshText("");
        mRefreshLayout.setRefreshViewHolder(viewholder);

        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setMessage("Loading...");
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

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        mNewPageNumber++;
        if (mNewPageNumber > 4) {
            mRefreshLayout.endRefreshing();
            showToast("There are no recent data the");
            return;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
            }
        }, 1000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        mNewPageNumber++;
        if (mNewPageNumber > 5) {
            mRefreshLayout.endLoadingMore();
            showToast("No more data the");
            return false;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endLoadingMore();
            }
        }, 1000);
        return true;
    }

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
