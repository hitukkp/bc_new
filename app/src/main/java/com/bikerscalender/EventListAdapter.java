package com.bikerscalender;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by applect on 2/9/15.
 */

public class EventListAdapter extends RecyclerView.Adapter <EventListAdapter.EventListViewHolder> {

    private LayoutInflater inflater;
    List<EventListData> list_data = Collections.emptyList();

    public EventListAdapter(Context context, List<EventListData> list_data){
        inflater = LayoutInflater.from(context);
        this.list_data = list_data;
    }

    @Override
    public EventListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.event_list_custom_row, viewGroup, false);
        EventListViewHolder holder = new EventListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventListViewHolder eventListViewHolder, int i) {
        EventListData current_obj = list_data.get(i);
        eventListViewHolder.title.setText(current_obj.title);
        eventListViewHolder.start_date.setText(current_obj.start_date);
        eventListViewHolder.from.setText(current_obj.from);
        eventListViewHolder.to.setText(current_obj.to);
        eventListViewHolder.total_time.setText(current_obj.total_time);
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    class EventListViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView start_date;
        TextView from;
        TextView to;
        TextView total_time;

        public EventListViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_id);
            start_date = (TextView) itemView.findViewById(R.id.start_date_id);
            from = (TextView) itemView.findViewById(R.id.from_id);
            to = (TextView) itemView.findViewById(R.id.to_id);
            total_time = (TextView) itemView.findViewById(R.id.time_total_id);
        }
    }
}
