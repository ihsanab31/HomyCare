package com.sundevs.ihsan.homycare.view.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.sundevs.ihsan.homycare.HomyCare;
import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.util.param.BaseUrl;
import com.sundevs.ihsan.homycare.util.param.Params;
import com.sundevs.ihsan.homycare.util.pref.SessionManager;
import com.sundevs.ihsan.homycare.view.activities.MainActivity;
import com.sundevs.ihsan.homycare.view.base.BaseDialog;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for Login Dialog
 */
public class LoginDialog extends BaseDialog {

    @NotEmpty(sequence = 0)
    @Email(sequence = 1)
    @BindView(R.id.et_phone)
    EditText etEmail;
    @NotEmpty
    @BindView(R.id.et_password)
    EditText etPassword;
    private static final String TAG = LoginDialog.class.getSimpleName();
    SessionManager session;
    SharedPreferences preferences;
    public LoginDialog() {
    }

    @SuppressLint("ValidFragment")
    public LoginDialog(Context context) {
        super(context);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etEmail.setText("");
        etPassword.setText("");
        session = new SessionManager(getActivity().getApplicationContext());
    }

    @Override
    public int getDialogView() {
        return R.layout.dialog_login;
    }

    @Override
    public boolean isDialogCancelable() {
        return true;
    }

    @Override
    public void onLoadInstance(Bundle savedInstanceBundle) {
    }

    @Override
    public void onPostInit() {
    }

    @OnClick(R.id.bt_sign_in)
    public void onOkClicked() {
        login();
    }

    private void login() {
        showProgressDialog("Login..");
        StringRequest strReq = new StringRequest(Request.Method.POST, BaseUrl.URL_BASE+Params.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    Params.success = jObj.getInt(Params.TAG_SUCCESS);

                    // Cek error node pada json
                    if (Params.success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String  id_user = jObj.getString(Params.id_user);
                        String nama = jObj.getString(Params.nama);
                        String alamat = jObj.getString(Params.alamat);
                        String no_hp = jObj.getString(Params.no_hp);
                        String image = jObj.getString(Params.image);
                        String password = jObj.getString(Params.password);
                        preferences =
                               getActivity().getSharedPreferences("data", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("id_user", id_user);
                        editor.putString("nama", nama);
                        editor.putString("alamat", alamat);
                        editor.putString("no_hp", no_hp);
                        editor.putString("image", image);
                        editor.putString("password", password);
                        editor.commit();
                        Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                        session.createLoginSession(etEmail.getText().toString(), etPassword.getText().toString());
                    } else {
                        dismissProgressDialog();
                        Toast.makeText(context, "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dismissProgressDialog();
                Toast.makeText(context, "Ero", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put(Params.no_hp, etEmail.getText().toString());
                params.put(Params.password, etPassword.getText().toString());
                return params;
            }

        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        HomyCare.getInstance().addToRequestQueue(strReq, Params.tag_json_obj);
    }
}
