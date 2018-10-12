package com.example.niteshverma.demoproject.injection;

import com.example.niteshverma.demoproject.network.RandomStudentApi;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface RepositoryComponent {

    RandomStudentApi getRandomStudentService();
    Picasso getPicasso();

}
