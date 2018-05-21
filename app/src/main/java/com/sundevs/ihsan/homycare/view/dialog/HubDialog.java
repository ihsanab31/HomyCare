package com.sundevs.ihsan.homycare.view.dialog;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.view.base.NormalActivity;

import butterknife.OnClick;

public class HubDialog extends NormalActivity {
    String number ="";
    @Override
    protected int getActivityView() {
        return R.layout.activity_hub_dialog;
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
        number = getBundle().getString("number");
    }

    @OnClick({R.id.btn_sms,R.id.btn_telp,R.id.btn_wa})
    void hun(View view){
        switch (view.getId()){
            case R.id.btn_sms:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
                break;
            case R.id.btn_telp:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
                startActivity(intent);
                break;
            case R.id.btn_wa :
                Intent sendIntent = new Intent();
                sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.putExtra("jid", number);
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
                break;
        }
    }
}
