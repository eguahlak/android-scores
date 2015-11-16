package dk.cphbusiness.highscores;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
	public static final String SMS_EXTRA_NAME = "pdus";
	private static final String LOG_TAG = "SmsReceiver";
	public static final String ID = "_id";
	public static final String ADDRESS = "address";
    public static final String BODY = "body";
    
	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Override
	public void onReceive(Context context, Intent intent) {
		// HighScoreDb db = new HighScoreDb(context);
		try (HighScoreDb db = new HighScoreDb(context)) {
			db.open();
			Bundle extras = intent.getExtras();
			if (extras == null) return;

			Object[] smsExtra = (Object[])extras.get(SMS_EXTRA_NAME);
			for (Object data : smsExtra) {
				SmsMessage message = SmsMessage.createFromPdu((byte[])data);
				String body = message.getMessageBody();
				Toast.makeText(context, body, Toast.LENGTH_LONG).show();
				if (body.startsWith("#SCORE:")) {
					// abortBroadcast();
					String[] parts = body.split(":");
					String name = parts[1];
					int score = Integer.parseInt(parts[2]);
					db.createHighScore(name, score);
					}
				}
			}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
			Log.e(LOG_TAG, "Invalid format of SMS", ex);
			}
		}

	}
