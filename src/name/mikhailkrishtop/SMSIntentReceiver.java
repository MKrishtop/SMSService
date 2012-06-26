package name.mikhailkrishtop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSIntentReceiver extends BroadcastReceiver {

	static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ACTION)) {
			Bundle bundle = intent.getExtras();
			String str = "";
			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				final SmsMessage[] msgs = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) {
					msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				}
				for (int i = 0; i < msgs.length; i++) {
					Intent newIntent = new Intent(context,
							MessageServiceActivity.class);
					newIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
							| Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					newIntent.putExtra(
							"name.mikhailkrishtop.SMSServiceActivity.title",
							msgs[i].getDisplayOriginatingAddress());
					newIntent.putExtra(
							"name.mikhailkrishtop.SMSServiceActivity.message",
							msgs[i].getMessageBody().toString());
					context.startActivity(newIntent);
				}
			}
		}
	}

}
