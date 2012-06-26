package name.mikhailkrishtop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class EmailIntentReceiver extends BroadcastReceiver {

	public static final Uri k9Uri = Uri
			.parse("content://com.fsck.k9.messageprovider/inbox_messages/");
	static String[] messages_projection = new String[] { "_ID", "date",
			"sender", "subject", "preview", "account", "uri", "delUri" };

	@Override
	public void onReceive(Context context, Intent intent) {
		Context appContext = context.getApplicationContext();
		Intent newIntent = new Intent(appContext, MessageServiceActivity.class);
		
		Cursor curSt = context.getContentResolver().query(k9Uri,
				messages_projection, null, null, null);
		curSt.moveToFirst();
		String preview = curSt.getString(curSt.getColumnIndex("preview"));
		String title = curSt.getString(curSt.getColumnIndex("subject"));
		curSt.close();
		
		newIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		newIntent.putExtra("name.mikhailkrishtop.SMSServiceActivity.title",
				intent.getStringExtra("com.fsck.k9.intent.extra.FROM"));
		newIntent.putExtra("name.mikhailkrishtop.SMSServiceActivity.message",
				"Title: " + title + "\nMessage: " + preview.toString());
		appContext.startActivity(newIntent);
	}

}
