package com.peluffo.actividad2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this, ServicioMensajes.class);
        //startService(i);
        ejecutarTarea(i);
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