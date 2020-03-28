package ro.pub.cs.systems.eim.lab05.startedserviceactivity.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import java.util.ArrayList;

import ro.pub.cs.systems.eim.lab05.startedserviceactivity.general.Constants;

public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    private TextView messageTextView;

    // TODO: exercise 9 - default constructor

    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView
        String action = intent.getAction();
        String data = null;
        int intData;
        ArrayList<String> stringData;

        if (Constants.ACTION_STRING.equals(action)) {
            data = intent.getStringExtra(Constants.DATA);
            if (messageTextView != null) {
                messageTextView.setText(messageTextView.getText().toString() + "\n" + data);
            }
        }
        if (Constants.ACTION_INTEGER.equals(action)) {
            intData = intent.getIntExtra(Constants.DATA, 0);
            if (messageTextView != null) {
                messageTextView.setText(messageTextView.getText().toString() + "\n" + intData);
            }
        }
        if (Constants.ACTION_ARRAY_LIST.equals(action)) {
            stringData = intent.getStringArrayListExtra(Constants.DATA);
            if (messageTextView != null) {
                for (String s : stringData) {
                    messageTextView.setText(messageTextView.getText().toString() + "\n" + s);
                }
            }
        }

        // TODO: exercise 9 - restart the activity through an intent
        // if the messageTextView is not available
        Intent startedServiceActivityIntent = new Intent(context, StartedServiceActivity.class);
        Intent[] intentArray = new Intent[1];

        startedServiceActivityIntent.putExtra(Constants.MESSAGE, data);
        startedServiceActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intentArray[0] = startedServiceActivityIntent;
        context.startActivities(intentArray);
    }
}
