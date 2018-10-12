package com.example.niteshverma.demoproject.injection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.niteshverma.demoproject.network.RandomStudentApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module (includes = ContextModule.class)
public class NetworkModule {

    @Singleton
    @Provides
    public RandomStudentApi randomStudentApi(Retrofit retrofit){
        return retrofit.create(RandomStudentApi.class);
    }

    @Singleton
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Singleton
    @Provides
    public OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new
                HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Log.i("Interceptor", message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Singleton
    @Provides
    public Cache cache(File cacheFile){
        return new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
    }

    @Singleton
    @Provides
    public File file(@Named("application_context") Context context){
        File cacheFile = new File(context.getCacheDir(), "HttpCache");
        cacheFile.mkdirs();
        return cacheFile;
    }

    @Singleton
    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }

    @Singleton
    @Provides
    public Picasso picasso(@Named("application_context") Context context, OkHttp3Downloader okHttpDownloader){
        Log.e("Network Module", "provides Picasso");
        Picasso picasso = new Picasso.Builder(context).downloader(okHttpDownloader).loggingEnabled(true).indicatorsEnabled(true).build();

        return picasso;
    }

    @Singleton
    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

}
