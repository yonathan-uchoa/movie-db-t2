package com.example.moviedb.ui.series;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SeriesModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SeriesModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is series fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}