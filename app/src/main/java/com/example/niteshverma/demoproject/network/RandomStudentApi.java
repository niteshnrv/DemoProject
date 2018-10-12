package com.example.niteshverma.demoproject.network;

import com.example.niteshverma.demoproject.models.RandomStudent;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomStudentApi {

    @GET("api")
    Observable<Response<RandomStudent>> getRandomStudent(@Query("results") int size);
}
