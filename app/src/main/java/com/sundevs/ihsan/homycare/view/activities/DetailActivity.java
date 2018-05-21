package com.sundevs.ihsan.homycare.view.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sundevs.ihsan.homycare.HomyCare;
import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.adapter.model.ActionSMS;
import com.sundevs.ihsan.homycare.util.param.BaseUrl;
import com.sundevs.ihsan.homycare.util.param.DataProvider;
import com.sundevs.ihsan.homycare.util.param.DataService;
import com.sundevs.ihsan.homycare.view.base.NormalActivity;
import com.sundevs.ihsan.homycare.view.dialog.EditDialog;
import com.sundevs.ihsan.homycare.view.dialog.HubDialog;

import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends NormalActivity implements OnMapReadyCallback {
    @Nullable
    GoogleMap googleMap;
    ImageLoader imageLoader;
    double lat = 0, lng = 0;
    String nama = "", umur = "", alamat = "", status = "", gambar = "", pendidikan = "", jarak = "";
    int harga = 0;
    int id_suster;
    @BindView(R.id.txt_nama_detail)
    TextView txt_nama;
    @BindView(R.id.txt_harga)
    TextView txt_harga;
    @BindView(R.id.txt_pendidikan)
    TextView txt_pendidikan;
    @BindView(R.id.txt_alamat_detail)
    TextView txt_alamat;
    @BindView(R.id.txt_umur_detail)
    TextView txt_umur;
    @BindView(R.id.txt_jarak_detail)
    TextView txt_jarak;
    @BindView(R.id.img_foto_detail)
    NetworkImageView imageView;
    String NEXMO_API_KEY = "l0xcd7";
    String NEXMO_API_SECRET = "02030405ab";
    String TO_NUMBER = "";
    public static DataService nService;
    private ActionSMS number;
    @Override
    protected int getActivityView() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initData();
        DataProvider provider = new DataProvider();
        nService = provider.getTService();

    }

    @SuppressLint("SetTextI18n")
    private void initData() {

        nama = getBundle().getString("nama");
        jarak = getBundle().getString("jarak");
        pendidikan = getBundle().getString("pendidikan");
        harga = getBundle().getInt("harga");
        alamat = getBundle().getString("alamat");
        umur = getBundle().getString("umur");
        TO_NUMBER = getBundle().getString("nohp");
        status = getBundle().getString("status");
        gambar = getBundle().getString("gambar");
        id_suster = getBundle().getInt("id_suster");
        lat = getBundle().getDouble("lat");
        lng = getBundle().getDouble("lng");
        txt_nama.setText(nama);
        txt_harga.setText("Harga Rp." + harga);
        txt_pendidikan.setText("Pendidikan Terakhir " + pendidikan);
        txt_alamat.setText(alamat);
        txt_umur.setText(umur + " Tahun\n" + "Jarak " + jarak + " KM");
        txt_jarak.setText("Status " + status);
        if (imageLoader == null)
            imageLoader = HomyCare.getInstance().getImageLoader();
        imageView.setImageUrl(BaseUrl.URL_BASE + "/" + gambar, imageLoader);
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng mallLocation = new LatLng(lat, lng);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mallLocation, 15f));
        MarkerOptions markerOptions = new MarkerOptions();
        googleMap.addMarker(markerOptions.position(mallLocation)
                .title(alamat + "\n" + jarak + " KM")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mother)));
    }

    @OnClick(R.id.btn_booking)
    void booking() {
//        Toast.makeText(DetailActivity.this, "Send message", Toast.LENGTH_SHORT).show();
//        Call<List<ActionSMS>> call = nService.SendAction(NEXMO_API_KEY, NEXMO_API_SECRET, TO_NUMBER, "Anda ");
//        call.enqueue(new Callback<List<ActionSMS>>() {
//            @Override
//            public void onResponse(Call<List<ActionSMS>> call, Response<List<ActionSMS>> response) {
//            }
//            @Override
//            public void onFailure(Call<List<ActionSMS>> call, Throwable t) {
//            }
//        });
        Intent intent = new Intent(getApplicationContext(), HubDialog.class);
        intent.putExtra("number",TO_NUMBER );
        startActivity(intent);
    }
}
