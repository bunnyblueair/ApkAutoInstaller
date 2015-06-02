package org.bunnyblue.apkautoInstaller;

import java.io.File;
import java.util.LinkedList;

import org.bunnyblue.apkautoInstaller.utils.ApkFinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class InstallerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LinkedList<String> apkPaths = new LinkedList<String>();
		ApkFinder.findApks(new File("/sdcard/"), apkPaths);
		ListView mListView = (ListView) findViewById(R.id.listView);
		mListView.setAdapter(new ApkAdapter(apkPaths, this));
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
