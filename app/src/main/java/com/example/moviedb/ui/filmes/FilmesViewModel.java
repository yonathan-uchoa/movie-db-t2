package com.example.moviedb.ui.filmes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FilmesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FilmesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is filmes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}