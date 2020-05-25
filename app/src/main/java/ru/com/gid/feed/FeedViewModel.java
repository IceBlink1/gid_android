package ru.com.gid.feed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedViewModel extends ViewModel {
    MutableLiveData<FeedData> feedData = new MutableLiveData<>();

    public void post(FeedData fd) {
        feedData.postValue(fd);
    }
}
