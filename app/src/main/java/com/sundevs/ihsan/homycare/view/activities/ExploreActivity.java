package com.sundevs.ihsan.homycare.view.activities;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.adapter.BSAdapter;
import com.sundevs.ihsan.homycare.util.param.BaseUrl;
import com.sundevs.ihsan.homycare.util.param.Params;
import com.sundevs.ihsan.homycare.view.base.NormalActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExploreActivity extends NormalActivity implements OnMapReadyCallback, LocationListener,
        GoogleMap.OnMarkerClickListener, RoutingListener {
    @BindView(R.id.coordinator_ex)
    CoordinatorLayout coordinatorLayout;
    private GoogleMap mMap;
    private String[] nama, alamat, gambar, umur, pendidikan, no_hp, token, status;
    private int[] harga, id_suster;
    int numData;
    LatLng latLng[];
    Boolean markerD[];
    private Double[] lat, lng;
    Double latitude, longitude;
    Criteria criteria;
    Location location;
    BSAdapter adapter;
    LatLng myPosition;
    int MY_LOCATION_REQUEST_CODE = 0;
    LocationManager locationManager;
    String provider;
    private List<Polyline> polylines = new ArrayList<>();

    @Override
    protected int getActivityView() {
        return R.layout.activity_explore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        ExploreActivity.this.getRoutingPath(0, 0);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            myPosition = new LatLng(latitude, longitude);
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(provider, 1000, 0, this);


    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        getLokasi();
    }

    private void getLokasi() {
        showProgressDialog("Please Wait..");
        JsonArrayRequest request = new JsonArrayRequest(BaseUrl.URL_BASE + Params.URL_MAPS, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                numData = response.length();
                Log.d("DEBUG_", "Parse JSON");
                latLng = new LatLng[numData];
                markerD = new Boolean[numData];
                nama = new String[numData];
                alamat = new String[numData];
                id_suster = new int[numData];
                umur = new String[numData];
                token = new String[numData];
                pendidikan = new String[numData];
                harga = new int[numData];
                no_hp = new String[numData];
                status = new String[numData];
                gambar = new String[numData];
                lat = new Double[numData];
                lng = new Double[numData];
                for (int i = 0; i < numData; i++) {
                    try {
                        JSONObject data = response.getJSONObject(i);
                        id_suster[i] = data.getInt(Params.id_suster);
                        latLng[i] = new LatLng(data.getDouble("lat"),
                                data.getDouble("lng"));
                        nama[i] = data.getString(Params.nama);
                        harga[i] = data.getInt(Params.harga);
                        pendidikan[i] = data.getString(Params.pedidikan);
                        gambar[i] = data.getString(Params.gambar);
                        alamat[i] = data.getString(Params.alamat);
                        no_hp[i] = data.getString(Params.no_hp);
                        umur[i] = data.getString(Params.umur);
                        token[i] = data.getString(Params.token);
                        status[i] = data.getString(Params.status);
                        lat[i] = data.getDouble(Params.lat);
                        lng[i] = data.getDouble(Params.lng);
                        markerD[i] = false;
                        mMap.addMarker(new MarkerOptions().position(latLng[i]).title(nama[i]).snippet(no_hp[i])
                                .snippet(status[i])
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mother)));
                    } catch (JSONException je) {
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng[i], 15.5f));
                }
                dismissProgressDialog();
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Log.d("DEBUG_", "Marker clicked");
                        for (int i = 0; i < numData; i++) {
                            if (marker.getTitle().equals(nama[i])) {
                                if (markerD[i]) {
                                    Log.d("DEBUG_", "panggil activity");
                                    getRoutingPath(lat[i], lng[i]);
                                    markerD[i] = false;
                                } else {
                                    Log.d("DEBUG_", "show info");
                                    // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15.5f));
                                    markerD[i] = true;
                                    marker.showInfoWindow();
                                    Snackbar snacka = Snackbar.make(coordinatorLayout, "Klik marker kembali, untuk route", Snackbar.LENGTH_LONG);
                                    TextView v = (TextView) snacka.getView().findViewById(android.R.id.message);
                                    if (v != null)
                                        v.setGravity(Gravity.CENTER);
                                    snacka.show();
                                }
                            } else {
                                markerD[i] = false;
                            }
                        }
                        return false;
                    }

                });
//                        if (PermissionHelper.canGetLocation()) {
//                            double range = Utility.distance(PermissionHelper.getLatitude(), gpsTracker.getLongitude(),
//                                    listGuru.get((listGuru.size() - 1)).getLatitude(),
//                                    listGuru.get((listGuru.size() - 1)).getLongitude());
//                            Log.d("gps_latitude", gpsTracker.getLatitude() + "");
//                            Log.d("gps_longitude", gpsTracker.getLongitude() + "");
//                            Toast.makeText(MapsActivity.this, range + "KM", Toast.LENGTH_LONG).show();
//                        }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ExploreActivity.this);
                builder.setTitle("Error!");
                builder.setMessage("cek jaringan bro");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        Volley.newRequestQueue(this).add(request);
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        myPosition = new LatLng(latitude, longitude);

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

    private void drawRoute(ArrayList<Route> routes) {
        if (polylines.size() > 0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        for (int i = 0; i < routes.size(); i++) {
            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(Color.BLUE);
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(routes.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);
        }
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        Toast.makeText(getApplicationContext(), "Routing Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {
        drawRoute(arrayList);
    }

    @Override
    public void onRoutingCancelled() {
        Toast.makeText(ExploreActivity.this, "Routing Cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    private void getRoutingPath(double lat, double lng) {
        try {
            LatLng from = new LatLng(latitude, longitude);
            LatLng to = new LatLng(lat, lng);
            Routing routing = new Routing.Builder()
                    .travelMode(Routing.TravelMode.DRIVING)
                    .withListener(this)
                    .waypoints(from, to)
                    .build();
            routing.execute();
        } catch (Exception e) {

        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }
}
