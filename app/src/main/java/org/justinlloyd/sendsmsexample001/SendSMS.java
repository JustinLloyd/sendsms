package org.justinlloyd.sendsmsexample001;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class SendSMS extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.send_sm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttonSendSMS(View v)
    {
        Log.d(SendSMS.class.getName(), "Send SMS button clicked");
        String destinationTelephoneNumber = ((EditText)(findViewById(R.id.editTextDestinationTelephoneNumber))).getText().toString();
        if (destinationTelephoneNumber.isEmpty())
        {
            Log.d(SendSMS.class.getName(), "The telephone number supplied by the user is empty.");
            return;
        }

        Uri smsURI = Uri.fromParts("sms", destinationTelephoneNumber, null);
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, smsURI);
        startActivity(smsIntent);
        Log.d(SendSMS.class.getName(), "Sent a request to open the SMS Composer activity");
    }
}
