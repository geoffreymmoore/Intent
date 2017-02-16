package com.moore.intent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

public class AppleActivity extends AppCompatActivity {

    // build the object that is going to be the notification itself
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45123; // the notification has to be assigned to

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);

        Intent servI = new Intent(this, MyIntentService.class);
        startService(servI);

        // build the new notification
        notification = new NotificationCompat.Builder(this);
        // remove notification once it has been visited
        notification.setAutoCancel(true);


        Bundle data = getIntent().getExtras();
        if (data == null){
            return;
        }

        String msg = data.getString("msg");
        final TextView appleTV = (TextView) findViewById(R.id.appleTV);
        appleTV.setText(msg);
    }

    public void appleClick(View view) {

        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Here is the title");
        notification.setContentText("I am the body text of your notification");

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.setSound(alarmSound);

        // send the notification to this activity
        Intent bacI = new Intent(this, AppleActivity.class);
        // give the device access to perform this intent by calling the pendingintent
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,bacI,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        // send out the notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());

        Intent i = new Intent(this,BaconActivity.class);
        startActivity(i);
    }
}
