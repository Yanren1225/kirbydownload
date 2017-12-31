package com.kirby.runanjing.thefirst;

import android.os.*;
import android.support.v7.app.*;
import com.kirby.runanjing.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import com.kirby.runanjing.activity.*;

public class FinishActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.thefirst_finish);
		Button next=(Button)findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Intent i=new Intent(FinishActivity.this,MainActivity.class);
					startActivity(i);
					SharedPreferences.Editor y=getSharedPreferences("boolean", 0).edit().putBoolean("thefirst_状态", true);
					y.apply();
					finish();
				}
			});
	}
}
