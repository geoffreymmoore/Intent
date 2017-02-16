package com.moore.intent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class BaconActivity extends AppCompatActivity {

    private static final String TAG = "MyOnPause";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacon);

        Intent bacServI = new Intent(this, MyService.class);
        startService(bacServI);
    }

    public void baconClick(View view) {

        final EditText baconET = (EditText) findViewById(R.id.baconET);

        Intent i = new Intent(this,AppleActivity.class);

        String msg = baconET.getText().toString();
        i.putExtra("msg",msg);

        startActivity(i);
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause method called");

        super.onPause();
    }
}
