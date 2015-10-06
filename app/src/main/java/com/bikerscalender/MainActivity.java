package com.bikerscalender;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bikerscalender.EvenDialogBox.EventDialog;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MainActivity extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate, FloatingActionButton.OnClickListener {

    public RecyclerView eventsList;
    private EventListAdapter eventListAdapter;
    private List<EventListData> eventListData;

    private int mNewPageNumber = 0;
    public BGARefreshLayout mRefreshLayout;
    protected ProgressDialog mLoadingDialog;
    private View searcView;
    private Button cancelSearchButton;
    private EditText searchEditText;
    public FloatingActionButton searchFAB;
    FragmentManager fm = getSupportFragmentManager();

    public static Integer[] mThumbIds = {
            R.drawable.a, R.drawable.d, R.drawable.c
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

        searcView = findViewById(R.id.search_view_ll);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Tourer");
        loadBackdrop();

        searchFAB = (FloatingActionButton) findViewById(R.id.search_button);
        searchFAB.setOnClickListener(this);

        cancelSearchClick();
        textSearchListener();

        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.rl_modulename_refresh);
        mRefreshLayout.setDelegate(this);
        BGANormalRefreshViewHolder viewholder = new BGANormalRefreshViewHolder(this, false); // false is to disable footer loading
        viewholder.setRefreshingText("");
        viewholder.setPullDownRefreshText("");
        viewholder.setReleaseRefreshText("");
        mRefreshLayout.setRefreshViewHolder(viewholder);
        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setMessage("Loading...");

        eventsList.addOnItemTouchListener(
            new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.d("here", "testing on click");
                    EventDialog eventDialog = new EventDialog();
                    eventDialog.show(fm, "Dialog");
                }
            })
        );
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

    public void cancelSearchClick(){
        cancelSearchButton = (Button) findViewById(R.id.search_button_cancel);
        cancelSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFAB.setVisibility(View.VISIBLE);
                searcView.setVisibility(View.GONE);
                searchEditText.setText("");
            }
        });
    }

    private void textSearchListener(){
        searchEditText = (EditText) findViewById(R.id.search_edit_text);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final List<EventListData> filteredModelList = filter(MainActivity.getEventListData(), s.toString());
                eventListAdapter.animateTo(filteredModelList);
                eventsList.scrollToPosition(0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private List<EventListData> filter(List<EventListData> models, String query) {
        query = query.toLowerCase();

        final List<EventListData> filteredModelList = new ArrayList<>();
        for (EventListData model : models) {
            final String fromText = model.from.toLowerCase();
            final String titleText = model.title.toLowerCase();
            final String toText = model.to.toLowerCase();

            if (titleText.contains(query)) {
                filteredModelList.add(model);
            }else if (toText.contains(query)) {
                filteredModelList.add(model);
            }else if (fromText.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


    @Override
    public void onClick(View v) {
        if(searcView.getVisibility() == View.GONE) {
            searchFAB.setVisibility(View.GONE);
            searcView.setVisibility(View.VISIBLE);
        }
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
        /*mNewPageNumber++;
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
        return true;*/
        return true;
    }

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
