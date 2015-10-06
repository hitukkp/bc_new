package com.bikerscalender.EvenDialogBox;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bikerscalender.R;

/**
 * Created by applect on 6/10/15.
 */
public class MyCustomDialog extends DialogFragment {

    public MyCustomDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogue_description_event, container);
        getDialog().setTitle("Title");
        return view;
    }
}