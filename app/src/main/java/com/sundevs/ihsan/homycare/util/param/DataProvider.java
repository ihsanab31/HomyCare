package com.sundevs.ihsan.homycare.util.param;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iihsa on 22/03/2018.
 */

public class DataProvider {
    private DataService nService;
    private Retrofit mRetrofitClient;

    /**
     * config Retrofit in initialization
     */
    public DataProvider() {

        OkHttpClient httpClient = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        nService = retrofit.create(DataService.class);
    }


    public DataService getTService() {
        return nService;
    }

    public Retrofit getRetrofitClient() {
        return mRetrofitClient;
    }
}
