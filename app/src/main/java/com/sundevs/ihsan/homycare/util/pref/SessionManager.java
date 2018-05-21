package com.sundevs.ihsan.homycare.util.pref;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.sundevs.ihsan.homycare.view.activities.MainActivity;
import com.sundevs.ihsan.homycare.view.activities.SplashScreenActivity;

import java.util.HashMap;

        public class SessionManager {
            SharedPreferences pref;
            Editor editor;
            Context _context;
            int PRIVATE_MODE = 0;
            private static final String PREF_NAME = "BelajarPref";
            private static final String IS_LOGIN = "IsLoggedIn";
            public static final String KEY_USERNAME = "username";
            public static final String KEY_PASSWORD = "password";
            public SessionManager(Context context) {
                this._context = context;
                pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
                editor = pref.edit();
            }


            /**
             * Membuat login session
             */
            public void createLoginSession(String nama, String email) {
                editor.putBoolean(IS_LOGIN, true);
                editor.putString(KEY_USERNAME, nama);
                editor.putString(KEY_PASSWORD, email);
                editor.commit();
            }

            public HashMap<String, String> getUserDetails() {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
                user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
                return user;


            }

            public void checkLogin() {
                if (!this.isLoggedIn()) {
                    Intent i = new Intent(_context, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    _context.startActivity(i);
                    // }
                }
            }

            public boolean isLoggedIn(){
                return pref.getBoolean(IS_LOGIN, false);
            }

            public void logoutUser() {
                   editor.clear();
                   editor.commit();
                Intent i = new Intent(_context, SplashScreenActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _context.startActivity(i);
            }
        }