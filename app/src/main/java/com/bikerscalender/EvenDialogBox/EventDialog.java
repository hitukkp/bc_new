package com.bikerscalender.EvenDialogBox;

import android.content.DialogInterface;
import android.os.Bundle;

import com.bikerscalender.EventListData;

import fr.tvbarthel.lib.blurdialogfragment.BlurDialogEngine;

/**
 * Created by Hitesh Goel on 6/10/15.
 */
public class EventDialog extends MyCustomDialog {

    private EventListData currentObject ;
    private BlurDialogEngine mBlurEngine;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventDialog() {

    }

    public EventDialog( EventListData currentObject ){
        this.currentObject = currentObject;
        this.setCurrentEventDetails(currentObject);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBlurEngine = new BlurDialogEngine(getActivity());
        mBlurEngine.debug(false);
        mBlurEngine.setBlurRadius(6);
        mBlurEngine.setDownScaleFactor(4f);
        mBlurEngine.setBlurActionBar(true);
        mBlurEngine.setUseRenderScript(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBlurEngine.onResume(getRetainInstance());
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mBlurEngine.onDismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBlurEngine.onDestroy();
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }
}