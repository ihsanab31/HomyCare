package com.sundevs.ihsan.homycare.util.param;

import com.sundevs.ihsan.homycare.adapter.model.ActionSMS;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;



/**
 * Created by iihsa on 22/03/2018.
 */

public interface DataService {
    @GET("smsapi.php")
    Call<List<ActionSMS>> SendAction(
            @Query("userkey") String userkey,
            @Query("passkey") String passkey,
            @Query("nohp") String nohp,
            @Query("pesan") String pesan
    );

}
