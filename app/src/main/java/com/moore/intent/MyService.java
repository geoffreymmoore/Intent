package com.moore.intent;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand method called");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<5;i++){
                    long futureTime = System.currentTimeMillis() + 5000;
                    while (System.currentTimeMillis()<futureTime){
                        synchronized (this){
                            try{
                                wait(futureTime - System.currentTimeMillis());
                                Log.i(TAG, "Service is doing something");
                            }catch (Exception e){

                            }
                        }
                    }
                }
            }
        };

        // put all your code in the thread
        Thread myThread = new Thread(r);
        myThread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy method called");
       super.onDestroy();
    }


}
