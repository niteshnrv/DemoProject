package com.example.niteshverma.demoproject;

import android.app.Application;

import com.example.niteshverma.demoproject.injection.ContextModule;
import com.example.niteshverma.demoproject.injection.DaggerRepositoryComponent;
import com.example.niteshverma.demoproject.injection.RepositoryComponent;

public class DemoApplication extends Application{

    private static DemoApplication application;
    private RepositoryComponent repositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        repositoryComponent = DaggerRepositoryComponent.builder().contextModule(new ContextModule(this.getApplicationContext())).build();

    }

    public static synchronized DemoApplication get() {
        return application;
    }
    public RepositoryComponent getComponent() {
        return repositoryComponent;
    }
}
