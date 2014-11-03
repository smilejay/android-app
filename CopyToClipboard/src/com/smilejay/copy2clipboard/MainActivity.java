package com.smilejay.copy2clipboard;

import android.support.v7.app.ActionBarActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.commons.lang3.StringEscapeUtils;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
		final EditText inputText = (EditText)findViewById(R.id.inputEditText);
		final Button copyButton = (Button)findViewById(R.id.copyButton);
		View.OnClickListener btnListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Context context = getApplicationContext();
				if(inputText.getText().length() == 0){
					CharSequence text = "Please input in Edit View first.";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}else{
					String text = StringEscapeUtils.unescapeJava(inputText.getText().toString());
					final ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
				    final ClipData clipData = ClipData.newPlainText("text label", text);
				    cm.setPrimaryClip(clipData);
				    MainActivity.this.finish();
				}
			}
		};
		copyButton.setOnClickListener(btnListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
