package com.example.moviedb.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.api.JsonPlaceHolderApi;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<JsonPlaceHolderApi> mJPHA;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setJPHA(JsonPlaceHolderApi jsonPlaceHolderApi){
        mJPHA.setValue(jsonPlaceHolderApi);
    }
    public LiveData<JsonPlaceHolderApi> getJPHA(){
        return mJPHA;
    }
}