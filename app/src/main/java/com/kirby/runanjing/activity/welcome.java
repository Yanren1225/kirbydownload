package com.kirby.runanjing.activity;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import com.kirby.runanjing.*;

public class welcome extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
		new Thread(){
			public void run()
			{
				try
				{				
					sleep(2500);
				}
				catch (Exception e)
				{}
				Intent intent=new Intent(welcome.this, MainActivity.class);
				intent.setClass(welcome.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}.start();
	}
}