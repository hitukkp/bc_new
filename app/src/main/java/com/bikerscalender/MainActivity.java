package com.bikerscalender;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.bikerscalender.HttpConnect.ApiUrls;
import com.bikerscalender.HttpConnect.HttpServiceProvider;
import com.bikerscalender.JsonToJavaObjDir.EventDetails;
import com.bikerscalender.JsonToJavaObjDir.ResultData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, FloatingActionButton.OnClickListener, AppBarLayout.OnOffsetChangedListener {

    public RecyclerView eventsList;
    private static EventListAdapter eventListAdapter;
    private View searcView;
    private Button cancelSearchButton;
    private EditText searchEditText;
    public FloatingActionButton searchFAB;
    private static SwipeRefreshLayout mSwipeRefreshLayout;
    private AppBarLayout appBarLayout;
    public static final List<EventListData> eventListData = new ArrayList<>();
    private static final String BASE_DOMAIN = "http://myroomi.com";

    FragmentManager fm = getSupportFragmentManager();

    public static Integer[] mThumbIds = {
            R.drawable.a, R.drawable.d, R.drawable.c
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventsList = (RecyclerView) findViewById(R.id.events_list);
        eventListAdapter = new EventListAdapter(this, eventListData);
        eventsList.setAdapter(eventListAdapter);
        eventsList.setLayoutManager(new LinearLayoutManager(this));

        final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searcView = findViewById(R.id.search_view_ll);

        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Travel Limitless");
        loadBackdrop();
        collapsingToolbar.setContentScrimColor(getResources().getColor(R.color.toolbar_background));

        /*ImageView image = (ImageView) findViewById(R.id.backdrop);
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int mutedColor = palette.getMutedColor(0x000000);
                collapsingToolbar.setContentScrimColor(mutedColor);
            }
        });*/

        searchFAB = (FloatingActionButton) findViewById(R.id.search_button);
        searchFAB.setOnClickListener(this);

        cancelSearchClick();
        textSearchListener();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.contentView);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar_background);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                        getEvents();
                    }
                }
        );

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        eventsList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        EventListData currentObj = eventListData.get(position);
                        EventDialog eventDialog = new EventDialog( currentObj );
                        eventDialog.show(fm, "Dialog");
                    }
                })
        );
    }

    @Override
    public void onRefresh() {
        getEvents();
    }

    /*
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
        eventListAdapter.notifyDataSetChanged();
        return eventListData;
    }
    */

    private void getEvents(){

        // showing refresh animation before making http call
        mSwipeRefreshLayout.setRefreshing(true);
        try {
            ApiUrls client = HttpServiceProvider.createService( ApiUrls.class, BASE_DOMAIN );
            Call<ResultData> call = client.getEvents("testsapis");
            call.enqueue(new Callback<ResultData>() {
                @Override
                public void onResponse(Response<ResultData> response, Retrofit retrofit) {
                    ResultData dataSet = response.body();
                    List<EventDetails> listData = dataSet.getData();
                    for(EventDetails object : listData){
                        EventListData current = new EventListData();
                        current.title = object.getTitle();
                        current.from = object.getFromLocation();
                        current.to = object.getToLocation();
                        current.start_date = object.getSrartTime();
                        current.image_url = object.getImgSrc();
                        current.url_link = object.getEventUrl();
                        current.description = object.getDescription();
                        current.total_time = "17d 3h 5m 6s";
                        eventListData.add(current);
                    }
                    eventListAdapter.notifyDataSetChanged();

                    // hiding refresh animation before making http call
                    mSwipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Throwable t) {
                    // hiding refresh animation before making http call
                    showToast("Server Error");
                    mSwipeRefreshLayout.setRefreshing(false);
                    logresult(t.toString());
                }
            });
        } catch (Throwable e){
            Log.d("Error Here", e.toString());
        }
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
                final List<EventListData> filteredModelList = filter(MainActivity.eventListData, s.toString());
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

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected static void logresult(String text){
        Log.d("hiteshtest", text);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        mSwipeRefreshLayout.setEnabled(i == 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }
}
