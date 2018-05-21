package com.sundevs.ihsan.homycare.view.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.util.Base64;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class RegisterActivity extends NormalActivity {
    @BindView(R.id.et_nama_user)
    EditText nama;
    @BindView(R.id.et_alamat_user)
    EditText alamat;
    @BindView(R.id.et_password_user)
    EditText password;
    @BindView(R.id.et_nohp_user)
    EditText nohp;
    @BindView(R.id.profile_image)
    CircleImageView foto;
    Bitmap bitmap, decoded;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100
    private static final String TAGG = RegisterActivity.class.getSimpleName();

    @Override
    protected int getActivityView() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @OnClick(R.id.btn_daftar)
    void daftar() {
        uploadData();
    }

    @OnClick(R.id.flotbtn_image)
    void pickgambar() {
        showFileChooser();
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @OnClick(R.id.profile_image)
    void ambilfoto() {
        showFileChooser();
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void kosong() {
        foto.setImageResource(0);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        foto.setImageBitmap(decoded);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void uploadData() {
        showProgressDialog("Please wait..");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseUrl.URL_BASE + Params.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAGG, "Response: " + response.toString());
                        try {
                            JSONObject jObj = new JSONObject(response);
                            Params.success = jObj.getInt(Params.TAG_SUCCESS);
                            if (Params.success == 1) {
                                Log.d("v Add", jObj.toString());
                                Toast.makeText(RegisterActivity.this, jObj.getString(Params.TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                                bitmap = null;
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                                kosong();
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
                        Toast.makeText(RegisterActivity.this, "Error Respone", Toast.LENGTH_SHORT).show();
                        dismissProgressDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Params.id_user, String.valueOf(0));
                params.put(Params.nama, nama.getText().toString());
                params.put(Params.alamat, alamat.getText().toString());
                params.put(Params.no_hp, nohp.getText().toString());
                params.put(Params.token, "-");
                params.put(Params.password, password.getText().toString());
                params.put(Params.image, getStringImage(bitmap));
                Log.d(TAGG, "" + params);
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
