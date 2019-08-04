package com.android.stanley.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.android.stanley.db.AppDatabase;
import com.android.stanley.db.DatabaseInitializer;
import com.android.stanley.models.MovieDetailsModel;
import com.android.stanley.utils.LogUtils;

import java.util.List;

public class HistoryActivityViewModel  extends AndroidViewModel {

    private static final String TAG = "HistoryActivityViewMode";
    private MutableLiveData<List<MovieDetailsModel>> movieDataList;

    /**
     * Constructor of View Model with app context.
     * @param application
     */
    public HistoryActivityViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * to set the observable on this Mutable live data.
     * @return MutableLiveData<List<MovieDetailsModel>>
     */
    public MutableLiveData<List<MovieDetailsModel>>  getMovieDataList() {
        LogUtils.log(TAG,"getMovieDataList movieDataList called : " + movieDataList);
        if (movieDataList == null) {
            movieDataList = new MutableLiveData<>();
        }
        loadData();
        return movieDataList;
    }

    /**
     * Load Data from Db and build a list.
     */
    private void loadData() {
        List<MovieDetailsModel> data =   DatabaseInitializer.getAllMovies(AppDatabase.getAppDatabase(getApplication()));
        if(data != null) {
            movieDataList.setValue(data);
        } else {
            movieDataList.setValue(null);
        }
    }

}

