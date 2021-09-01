package com.peluffo.actividad2;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServicioMensajes extends Service {
    public ServicioMensajes() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @SuppressLint("Range")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread trabajador = new Thread(new Runnable() {
            @Override
            public void run() {
                Uri mensajes = Uri.parse("content://sms/inbox");
                ContentResolver cr = getContentResolver();
                while (true) {
                    Cursor c = cr.query(mensajes,
                            null,
                            null,
                            null,
                            null);
                    String dia = null;
                    String mensaje = null;
                    if (c != null && c.getCount() > 0) {
                        int i = 1;
                        while (c.moveToNext() && i < 6) {
                            dia = c.getString(c.getColumnIndex(Telephony.Sms._ID));
                            mensaje = c.getString(c.getColumnIndex(Telephony.Sms.BODY));
                            Log.d("mensajes", dia + " " + mensaje);
                            i++;
                        }
                        try {
                            Thread.sleep(9000);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    c.close();
                }
            }
        });
        trabajador.start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
