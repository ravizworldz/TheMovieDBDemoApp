package com.android.stanley.network;


import com.android.stanley.models.MovieModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

    //@GET("movie?api_key=c338fc8e28821210725ce5a9dbfef41e&language=en-US&page=1&include_adult=false")
    @GET("movie?page=1&include_adult=false")
    Call<MovieModel> doGetMovieList(@Query("api_key") String api_key, @Query("language") String language, @Query("query") String query);

}
