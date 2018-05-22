package com.sundevs.ihsan.homycare.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;
import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.util.param.BaseUrl;
import com.sundevs.ihsan.homycare.util.pref.SessionManager;
import com.sundevs.ihsan.homycare.view.base.NormalActivity;
import com.sundevs.ihsan.homycare.view.dialog.EditDialog;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends NormalActivity {
    @BindView(R.id.ic_profile)
    CircleImageView foto;
    @BindView(R.id.txt_profile_nama)
    TextView nama;
    @BindView(R.id.txt_nohp_profile)
    TextView nohp;
    @BindView(R.id.txt_alamat_profile)
    TextView alamat;
    SharedPreferences preferences;
    String nama1="",no_hp1="",images1="",alamat1="",id_user="";
    SessionManager session;
    @Override
    protected int getActivityView() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }


    public void initView(){
        session = new SessionManager(getApplicationContext());
        preferences = getApplicationContext().getSharedPreferences("data", 0);
        nama1 = preferences.getString("nama", null);
        no_hp1 = preferences.getString("no_hp", null);
        images1 = preferences.getString("image", null);
        alamat1= preferences.getString("alamat", null);
        id_user= preferences.getString("id_user", null);
        nama.setText(nama1);
        nohp.setText(no_hp1);
        alamat.setText(alamat1);
        Picasso.with(this).load(BaseUrl.URL_BASE + "/" + images1).noFade().into(foto);
    }

    @OnClick(R.id.btn_edit)
    void edit(){
        Intent intent = new Intent(getApplicationContext(), EditDialog.class);
        intent.putExtra("nama",nama.getText().toString());
        intent.putExtra("alamat", alamat.getText().toString());
        intent.putExtra("nohp", nohp.getText().toString());
        intent.putExtra("id_user", id_user);
        startActivity(intent);

    }
    @OnClick(R.id.btn_logout)
    void log(){
        logoutUser();
    }

    private void logoutUser() {
        session.logoutUser();
        Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        initView();
    }
}
