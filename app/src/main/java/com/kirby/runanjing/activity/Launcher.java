package com.kirby.runanjing.activity;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import com.kirby.runanjing.*;
import android.content.pm.*;
import android.widget.*;
import com.kirby.runanjing.thefirst.*;
import com.kirby.runanjing.untils.*;
import android.content.res.*;
import android.util.*;
import java.util.*;

public class Launcher extends AppCompatActivity
{

	private boolean 状态_;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		//隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setLanguage();
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
				theFirst();
			}
		}.start();
	}
	
	private void theFirst()
	{
		SharedPreferences 状态=getSharedPreferences("boolean", 0);
		状态_ = 状态.getBoolean("thefirst_状态", false);
		if (状态_ == false)
		{
			//跳转
			Intent intent=new Intent(Launcher.this, MainActivity.class);
			intent.setClass(Launcher.this, WelcomeActivity.class);
			startActivity(intent);
			finish();
		}
		else{
			//跳转
			Intent intent=new Intent(Launcher.this, MainActivity.class);
			intent.setClass(Launcher.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}
	private void setLanguage() {

        //读取SharedPreferences数据，默认选中第一项
        SharedPreferences preferences = getSharedPreferences("string", Context.MODE_PRIVATE);
        String language = preferences.getString("language","auto");

        //根据读取到的数据，进行设置
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();

        switch (language){
            case "auto":
                configuration.setLocale(Locale.getDefault());
                break;
            case "zh_cn":
                configuration.setLocale( Locale.CHINA);
                break;
			case "zh_tw":
                configuration.setLocale( Locale.TAIWAN);
                break;
			case "en":
                configuration.setLocale( Locale.ENGLISH);
                break;
            default:
                break;
        }
        resources.updateConfiguration(configuration,displayMetrics);
    }
}
