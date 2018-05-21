package com.sundevs.ihsan.homycare.view.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.sundevs.ihsan.homycare.HomyCare;
import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.adapter.BSAdapter;
import com.sundevs.ihsan.homycare.adapter.model.SusterModel;
import com.sundevs.ihsan.homycare.util.param.BaseUrl;
import com.sundevs.ihsan.homycare.util.param.Params;
import com.sundevs.ihsan.homycare.view.base.NormalActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ListBabyActivity extends NormalActivity implements LocationListener,
        SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.sr_baby_siter)
    SwipeRefreshLayout swipe;
    @BindView(R.id.rv_baby_siter)
    RecyclerView mRecyclerView;
    List<SusterModel> itemList = new ArrayList<>();
    Double latitude, longitude;
    Criteria criteria;
    Location location;
    BSAdapter adapter;
    LocationManager locationManager;
    String provider;

    @Override
    protected int getActivityView() {
        return R.layout.activity_list_baby;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();
        adapter = new BSAdapter(itemList, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
                itemList.clear();
                lokasi();

            }
        });

    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.e(TAG, "User location latitude:" + latitude + ", longitude:" + longitude);
        itemList.clear();
        callListVolley(latitude, longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(false);
        itemList.clear();
        lokasi();
    }

    public void callListVolley(double lat, double lng) {
        itemList.clear();
        adapter.notifyDataSetChanged();
        showProgressDialog("Search Data...");
        JsonArrayRequest jArr = new JsonArrayRequest(BaseUrl.URL_BASE + Params.URL_JARAK + lat + "&lng=" + lng,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                SusterModel j = new SusterModel();
                                j.setId_suster(obj.getInt(Params.id_suster));
                                j.setNama(obj.getString(Params.nama));
                                j.setAlamat(obj.getString(Params.alamat));
                                j.setPedidikan(obj.getString(Params.pedidikan));
                                j.setHarga(obj.getInt(Params.harga));
                                j.setUmur(obj.getString(Params.umur));
                                j.setLat(obj.getDouble(Params.lat));
                                j.setLng(obj.getDouble(Params.lng));
                                j.setStatus(obj.getString(Params.status));
                                j.setNo_hp(obj.getString(Params.no_hp));
                                double jarak = Double.parseDouble(obj.getString(Params.jarak));
                                j.setJarak("" + round(jarak, 2));
                                j.setGambar(obj.getString(Params.gambar));
                                itemList.add(j);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        // memberitahu adapter jika ada perubahan data
                        adapter.notifyDataSetChanged();
                        dismissProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.e(TAG, "Error: " + volleyError.getMessage());
                Toast.makeText(ListBabyActivity.this, "Error Respon", Toast.LENGTH_SHORT).show();
                dismissProgressDialog();
            }
        });
        jArr.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        HomyCare.getInstance().addToRequestQueue(jArr);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @SuppressLint("MissingPermission")
    private void lokasi() {
        location = locationManager.getLastKnownLocation(provider);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(provider, 10000, 3, this);

        if (location != null) {
            onLocationChanged(location);
        } else {
            callListVolley(-6.920957, 107.720645);
        }
    }

}
