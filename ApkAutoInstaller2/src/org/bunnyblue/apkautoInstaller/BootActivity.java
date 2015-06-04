package org.bunnyblue.apkautoInstaller;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BootActivity extends Activity {
	SharedPreferences mSharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boot);
		mSharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
		final EditText btnPath = (EditText) findViewById(R.id.btnPath);
		Button btnInstall, btnSetting;
		btnInstall = (Button) findViewById(R.id.btnInstall);
		btnSetting = (Button) findViewById(R.id.btnSetting);
		String dir = mSharedPreferences.getString("dir", "");
		if (TextUtils.isEmpty(dir)) {
			dir = Environment.getExternalStorageDirectory().getAbsolutePath();
			mSharedPreferences.edit().putString("dir", dir).commit();
		}
		btnPath.setText(dir);
		btnSetting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String path = btnPath.getText().toString().trim();
				File file = new File(path);
				if (file.isDirectory()) {
					mSharedPreferences.edit().putString("dir", path).commit();
				} else {
					Toast.makeText(getBaseContext(), path + " is not dir", Toast.LENGTH_SHORT).show();
				}

			}
		});
		btnInstall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String path = btnPath.getText().toString().trim();
				File file = new File(path);
				if (file.isDirectory()) {
					startActivity(new Intent(BootActivity.this, InstallerActivity.class));
				} else {
					Toast.makeText(getBaseContext(), path + " is not dir", Toast.LENGTH_SHORT).show();
				}

			}
		});
		;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.boot, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
