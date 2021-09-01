package com.peluffo.actividad2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private Intent i ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkCallingOrSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS}, 1000);
        }

        i = new Intent(this, ServicioMensajes.class);

        startService(i);
        //ejecutarTarea(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(i);
    }

    private void ejecutarTarea(Intent intent){
        int tiempo = 9000;
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startService(intent);
                h.postDelayed(this, tiempo);
            }
        }, tiempo);
    }

}