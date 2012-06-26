package name.mikhailkrishtop;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MessageServiceActivity extends Activity/* implements OnTouchListener*/{
	
	ArrayList<Message> m_messages = new ArrayList<Message>();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
				| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		View view = getLayoutInflater().inflate(R.layout.main, null);
		setContentView(view);

		TextView title = (TextView) findViewById(R.id.title);
		TextView message = (TextView) findViewById(R.id.message);

		Bundle extras = getIntent().getExtras();
		
		if(extras !=null) {
		    String titleValue = extras.getString("name.mikhailkrishtop.SMSServiceActivity.title");
		    String messageValue = extras.getString("name.mikhailkrishtop.SMSServiceActivity.message");
		    		    
		    if (titleValue != null && title != null) title.setText("From: " + titleValue);
		    if (messageValue != null && message != null) message.setText(messageValue);
		}	
		Log.d("1111", "create");
	}
	
	@Override
	public void onBackPressed() {
		if (m_messages.size() == 0) {
			super.onBackPressed();
		} else {
			Message msg = m_messages.remove(0);
			
			TextView title = (TextView) findViewById(R.id.title);
			TextView message = (TextView) findViewById(R.id.message);
		    if (msg != null && msg.getTitle() != null) title.setText(msg.getTitle());
		    if (msg != null && msg.getBoady() != null) message.setText(msg.getBoady());
		}
	}
	
	@Override
	public void onNewIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		
		if(extras !=null) {
		    String titleValue = extras.getString("name.mikhailkrishtop.SMSServiceActivity.title");
		    String messageValue = extras.getString("name.mikhailkrishtop.SMSServiceActivity.message");
		    
		    Message newMsg = new Message();
			newMsg.setTitle("From: " + titleValue);
			newMsg.setBoady(messageValue);
			m_messages.add(newMsg);
		}
	}
}