package com.example.niteshverma.demoproject.injection;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Named("application_context")
    @Provides
    public Context context(){
        return this.context.getApplicationContext();
    }
}
