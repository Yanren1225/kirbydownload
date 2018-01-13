package com.kirby.runanjing.activity;
import android.content.*;
import android.content.res.*;
import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import com.kirby.runanjing.*;
import java.util.*;

import com.kirby.runanjing.R;
import com.hanks.htextview.base.*;
import com.kirby.runanjing.untils.*;

public class Launcher extends AppCompatActivity
{

	private boolean 状态_;
	private Handler mHandler = new Handler();

	private HTextView welcome;

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		//隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Theme.setClassTheme(this);
		super.onCreate(savedInstanceState);
		setLanguage();
		setContentView(R.layout.welcome);
		welcome = (HTextView)findViewById(R.id.textview);
		welcome.setTextColor(getColorPrimary());
		welcome.animateText(getResources().getString(R.string.welcome_to));
		mHandler.postDelayed(new Runnable() {
				@Override
				public void run()
				{
					welcome.animateText("kirby download");
				}
			}
			, 1250);
		mHandler.postDelayed(new Runnable() {
				@Override
				public void run()
				{
					theFirst();
				}
            }
			, 2500);
	}

	private void theFirst()
	{
		SharedPreferences 状态=getSharedPreferences("boolean", 0);
		状态_ = 状态.getBoolean("thefirst_状态", false);
		if (状态_ == false)
		{
			Intent intent=new Intent(Launcher.this, KirbyIntroActivity.class);
			intent.setClass(Launcher.this, KirbyIntroActivity.class);
			startActivity(intent);
			finish();
		}
		else
		{
			//跳转
			Intent intent=new Intent(Launcher.this, MainActivity.class);
			intent.setClass(Launcher.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}
	private void setLanguage()
	{

        //读取SharedPreferences数据，默认选中第一项
        SharedPreferences preferences = getSharedPreferences("string", 0);
        String language = preferences.getString("language", "auto");

        //根据读取到的数据，进行设置
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();

        switch (language)
		{
            case "auto":
                configuration.setLocale(Locale.getDefault());
                break;
            case "zh_cn":
                configuration.setLocale(Locale.CHINA);
                break;
			case "zh_tw":
                configuration.setLocale(Locale.TAIWAN);
                break;
			case "en":
                configuration.setLocale(Locale.ENGLISH);
                break;
            default:
                break;
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }
	public int getColorPrimary()
	{
		TypedValue typedValue = new  TypedValue();
		getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
		return typedValue.data;
	}
}
