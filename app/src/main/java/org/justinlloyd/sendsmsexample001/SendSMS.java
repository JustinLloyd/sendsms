package org.justinlloyd.sendsmsexample001;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SendSMS extends Activity {

    public static final String TAG = SendSMS.class.getName();

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
        Log.d(TAG, "Send SMS button clicked");
        Log.d(TAG, "Compose SMS button clicked");
        String destinationTelephoneNumber = ((EditText)(findViewById(R.id.editTextDestinationTelephoneNumber))).getText().toString();
        String message = ((EditText)(findViewById(R.id.editTextMessage))).getText().toString();
        if (destinationTelephoneNumber.isEmpty())
        {
            Log.e(TAG, "The telephone number supplied by the user is empty.");
            Toast.makeText(this, getResources().getString(R.string.toast_empty_number), Toast.LENGTH_LONG).show();
            return;
        }

        if (message.isEmpty())
        {
            Log.e(TAG, "The message supplied by the user is empty.");
            Toast.makeText(this, getResources().getString(R.string.toast_empty_message), Toast.LENGTH_LONG).show();
            return;
        }

        SmsManager.getDefault().sendTextMessage(destinationTelephoneNumber, null, message, null, null);
        Log.d(TAG, String.format("Directly sent SMS message with the following content: \"%s\" to \"%s\"", message, destinationTelephoneNumber));
        Toast.makeText(this, getResources().getString(R.string.toast_sent_sms), Toast.LENGTH_SHORT).show();
    }

    public void buttonComposeSMS(View v)
    {
        Log.d(TAG, "Compose SMS button clicked");
        String destinationTelephoneNumber = ((EditText)(findViewById(R.id.editTextDestinationTelephoneNumber))).getText().toString();
        String message = ((EditText)(findViewById(R.id.editTextMessage))).getText().toString();
        if (destinationTelephoneNumber.isEmpty())
        {
            Log.e(TAG, "The telephone number supplied by the user is empty.");
            Toast.makeText(this, getResources().getString(R.string.toast_empty_number), Toast.LENGTH_LONG).show();
            return;
        }

        if (message.isEmpty())
        {
            Log.e(TAG, "The message supplied by the user is empty.");
            Toast.makeText(this, getResources().getString(R.string.toast_empty_message), Toast.LENGTH_LONG).show();
            return;
        }

        Uri smsURI = Uri.fromParts("smsto", destinationTelephoneNumber, null);
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsURI);
        smsIntent.putExtra("sms_body", message);
        startActivity(smsIntent);
        Log.d(TAG, String.format("Sent a request to open the SMS Composer activity and send the following message: \"%s\" to \"%s\"", message, destinationTelephoneNumber));
        Toast.makeText(this, getResources().getString(R.string.toast_opening_sms_composer), Toast.LENGTH_SHORT).show();
    }

}
