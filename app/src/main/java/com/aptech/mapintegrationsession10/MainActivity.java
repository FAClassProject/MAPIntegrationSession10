package com.aptech.mapintegrationsession10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES
            );
            for (Signature signature : info.signatures){
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("ShaKey", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
            }
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();

        }catch (NoSuchAlgorithmException algorithmException){
            algorithmException.getMessage();
        }
    }
}