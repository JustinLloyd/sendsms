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
import android.widget.Toast;


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
        String message = ((EditText)(findViewById(R.id.editTextMessage))).getText().toString();
        if (destinationTelephoneNumber.isEmpty())
        {
            Log.e(SendSMS.class.getName(), "The telephone number supplied by the user is empty.");
            Toast.makeText(this, "You need to supply a destination telephone number of where to send an SMS.", Toast.LENGTH_LONG).show();
            return;
        }

        if (message.isEmpty())
        {
            Log.e(SendSMS.class.getName(), "The message supplied by the user is empty.");
            Toast.makeText(this, "You need to supply some message content", Toast.LENGTH_LONG).show();
            return;
        }

        Uri smsURI = Uri.fromParts("sms", destinationTelephoneNumber, null);
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, smsURI);
        smsIntent.putExtra("sms_body", "Let's tell the world \"hello!\" from our SendSMS app");
        startActivity(smsIntent);
        Log.d(SendSMS.class.getName(), "Sent a request to open the SMS Composer activity");
        Toast.makeText(this, "Opening SMS composer", Toast.LENGTH_SHORT).show();
    }
}
