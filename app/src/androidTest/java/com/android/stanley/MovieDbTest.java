package com.android.stanley;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.android.stanley.db.AppDatabase;
import com.android.stanley.db.DatabaseInitializer;
import com.android.stanley.db.MovieDao;
import com.android.stanley.models.MovieDetailsModel;
import com.android.stanley.ui.DetailActivity;
import com.android.stanley.utils.LogUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class MovieDbTest {

    private MovieDao movieDao =  null;
    private AppDatabase appDatabase = null;

    @Before
    public void setup() {

        appDatabase = AppDatabase.getAppDatabase(InstrumentationRegistry.getTargetContext());

    }

    @After
    public void closeDb() throws IOException {
        appDatabase.close();
    }


    @Test
    public void movieInsetRecord(){
        MovieDetailsModel detailModel = new MovieDetailsModel();
        detailModel.setId(1000);
        detailModel.setTitle("ravi");
        detailModel.setRelease_date("2019.08.01");
        detailModel.setVote_count(200);
        detailModel.setVideo(false);
        detailModel.setVote_average(100);
        detailModel.setPopularity(7.2);
        detailModel.setPoster_path("test");
        detailModel.setOriginal_language("En");
        detailModel.setOriginal_title("ravi");
        detailModel.setBackdrop_path("ravi");
        detailModel.setOverview("ravi kumar");

        assertNotNull(detailModel.getTitle());

        DatabaseInitializer.populateAsync(appDatabase, detailModel);

        List<MovieDetailsModel> data =   DatabaseInitializer.getAllMovies(appDatabase);
        assertNotNull(data);
        assertEquals(1, data.size());

    }

    @Test
    public void getMovieListNoHistory(){

        List<MovieDetailsModel> data =   DatabaseInitializer.getAllMovies(appDatabase);
        assertNotNull(data);
        assertEquals(0, data.size());
    }


}
