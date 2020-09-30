package com.rsr.frankly;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class SessionManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "no";

    public static final String KEY_USER_ID = null;


    public SessionManager(Context _context){

        context = _context;
        userSession = _context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = userSession.edit();

    }

    public void createLoginSession(String user_id){

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_USER_ID, user_id);

        editor.commit();

    }

    public HashMap<String, String> getUserData(){

        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_USER_ID, userSession.getString(KEY_USER_ID, null));

        return userData;
    }

    public boolean checkLogin(){

        if(userSession.getBoolean(IS_LOGIN, true)){
            return true;
        }
        else {
            return false;
        }

    }

    public void logout(){
        editor.putBoolean(IS_LOGIN, false);
        editor.clear();
        editor.commit();
    }
}
