package com.kirby.runanjing.activity;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import com.kirby.runanjing.*;

public class Launcher extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		//隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
		new Thread(){
			public void run()
			{
				try
				{		
				//延时2.5秒		
					sleep(2500);
				}
				catch (Exception e)
				{}
				//跳转
				Intent intent=new Intent(Launcher.this, MainActivity.class);
				intent.setClass(Launcher.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}.start();
	}
}
