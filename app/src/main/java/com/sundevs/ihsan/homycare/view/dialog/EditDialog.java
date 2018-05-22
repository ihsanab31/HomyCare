package com.sundevs.ihsan.homycare.view.dialog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sundevs.ihsan.homycare.HomyCare;
import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.util.param.BaseUrl;
import com.sundevs.ihsan.homycare.util.param.Params;
import com.sundevs.ihsan.homycare.view.base.NormalActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class EditDialog extends NormalActivity {
    @BindView(R.id.et_nama_edit)
    EditText nama;
    @BindView(R.id.et_alamat_edit)
    EditText alamat;
    @BindView(R.id.et_nohp_edit)
    EditText nohp;
    String id_user = "", namauser= "", alamatuser="", no_hpuser="",emailuser="", passworduser="";
    SharedPreferences preference;

    private static final String TAG = EditDialog.class.getSimpleName();

    @Override
    protected int getActivityView() {
        return R.layout.dialog_edit;
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

    private void initView() {
        id_user = getBundle().getString("id_user");
        nama.setText(getBundle().getString("nama"));
        alamat.setText(getBundle().getString("alamat"));
        nohp.setText(getBundle().getString("nohp"));

    }

    @OnClick(R.id.btn_edit_profile)
    void editProfile() {
        uploadData();
    }

    private void uploadData() {
        showProgressDialog("Please wait..");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseUrl.URL_BASE + Params.URL_EDIT_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Response: " + response.toString());
                        try {
                            JSONObject jObj = new JSONObject(response);
                            Params.success = jObj.getInt(Params.TAG_SUCCESS);
                            if (Params.success == 1) {
                                Log.d("v Add", jObj.toString());
                                Toast.makeText(EditDialog.this, jObj.getString(Params.TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                                preference =
                                        getApplicationContext().getSharedPreferences("data", 0);
                                SharedPreferences.Editor editor = preference.edit();
                                editor.putString("id_user", id_user);
                                editor.putString("nama", nama.getText().toString());
                                editor.putString("alamat", alamat.getText().toString());
                                editor.putString("no_hp", nohp.getText().toString());
                                editor.commit();

                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), jObj.getString(Params.TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                dismissProgressDialog();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dismissProgressDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(EditDialog.this, "Error Respone", Toast.LENGTH_SHORT).show();
                        dismissProgressDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Params.id_user, id_user);
                params.put(Params.nama, nama.getText().toString());
                params.put(Params.alamat, alamat.getText().toString());
                params.put(Params.no_hp, nohp.getText().toString());
                Log.d(TAG, "" + params);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        HomyCare.getInstance().addToRequestQueue(stringRequest, Params.tag_json_obj);
    }
}
