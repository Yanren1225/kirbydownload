package com.kirby.runanjing.thefirst;

import android.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.content.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.thefirst.*;

import com.kirby.runanjing.R;

public class PermissionActivity extends BaseActivity
{
	private Button next;
	private Button sd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.thefirst_permission);
		next = (Button)findViewById(R.id.next);
		sd = (Button)findViewById(R.id.sd);
		next.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					Intent i=new Intent(PermissionActivity.this, FinishActivity.class);
					startActivity(i);
					finish();
				}
			});
	sd.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					ActivityCompat.requestPermissions(PermissionActivity.this,
													  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
													  1);
				}
			});
		initPer();
	}
	private void initPer()
	{
		if (ContextCompat.checkSelfPermission(PermissionActivity.this,
											  Manifest.permission.WRITE_EXTERNAL_STORAGE)
			!= PackageManager.PERMISSION_GRANTED)
		{
			next.setEnabled(true);
			sd.setEnabled(false);
		}
		else
		{
			next.setEnabled(false);
			sd.setEnabled(true);
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String permissions[], int[] grantResults)
	{
		switch (requestCode)
		{
			case 1: {
					// If request is cancelled, the result arrays are empty.
					if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED)
					{
						sd.setEnabled(false);
						next.setEnabled(true);
					}
					else
					{
						sd.setEnabled(true);
						next.setEnabled(false);
						Toast.makeText(PermissionActivity.this, "未授权", Toast.LENGTH_SHORT).show();
						// permission denied, boo! Disable the
						// functionality that depends on this permission.
					}
					return;
				}
		}
	}
}
