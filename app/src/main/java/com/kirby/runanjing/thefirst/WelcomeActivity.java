package com.kirby.runanjing.thefirst;

import android.os.*;
import android.support.v7.app.*;
import com.kirby.runanjing.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;

public class WelcomeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.thefirst_welcome);
		Button next=(Button)findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Intent i=new Intent(WelcomeActivity.this,UserActivity.class);
					startActivity(i);
					finish();
				}
			});
	}
}
