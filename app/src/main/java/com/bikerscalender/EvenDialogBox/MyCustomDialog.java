package com.bikerscalender.EvenDialogBox;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bikerscalender.EventListData;
import com.bikerscalender.R;
import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;

/**
 * Created by Hitesh Goel on 6/10/15.
 */
public class MyCustomDialog extends DialogFragment {

    public MyCustomDialog() {
        // Empty constructor required for DialogFragment
    }

    EventListData currentEventDetails;

    public void setCurrentEventDetails( EventListData currentEventDetails ){
        this.currentEventDetails = currentEventDetails;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialogue_description_event, container);

        ImageView dialogImage = (ImageView) rootView.findViewById(R.id.dialog_image_view);
        Glide.with(this).load(currentEventDetails.getImageUrlLink()).centerCrop().into(dialogImage);

        ExpandableTextView expTv1 = (ExpandableTextView) rootView.findViewById(R.id.dialog_expand_view).findViewById(R.id.expand_text_view);
        expTv1.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {

            }
        });
        expTv1.setText(getString(R.string.dummy_desc));
        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}