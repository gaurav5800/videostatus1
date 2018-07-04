package com.apnasapnamoney.videostatus.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRestAdapter {
    public static final String BASE_URL = "http://www.thinkbeauty.net:3000/api/";

    static GitHubService retrofit;

    public static GitHubService getRestAdapter() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(GitHubService.class);
        }

        return retrofit;
    }

}
