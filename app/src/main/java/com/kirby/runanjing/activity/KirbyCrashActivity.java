package com.kirby.runanjing.activity;


import android.content.*;
import android.os.*;
import android.support.v7.widget.*;
import android.widget.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.untils.*;

import android.support.v7.widget.Toolbar;
import android.text.*;

public class KirbyCrashActivity extends BaseActivity
{
    private Throwable crash_;

	private PhoneUtil phoneInfo;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        Theme.setClassTheme(this);
		setContentView(R.layout.error);
		Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		Intent crash=getIntent();
        crash_=(Throwable) crash.getSerializableExtra("crash");
		phoneInfo = new PhoneUtil(this);
		TextView crashText=(TextView)findViewById(R.id.crashText);
	  
		crashText.append("手机品牌:");
        crashText.append(Html.fromHtml("<font color=\"#E51C23\">" + phoneInfo.getBrand() + "</font>"));
        crashText.append("\n");
        crashText.append("手机型号:");
        crashText.append(Html.fromHtml("<font color=\"#E51C23\">" + phoneInfo.getModel() + "</font>"));
        crashText.append("\n");
        crashText.append("名称:");
        crashText.append(Html.fromHtml("<font color=\"#E51C23\">" + phoneInfo.getProduct() + "</font>"));
        crashText.append("\n");
        crashText.append("安卓版本:");
        crashText.append(Html.fromHtml("<font color=\"#E51C23\">" + phoneInfo.getAndroidVersion() + "</font>"));
        crashText.append("\n");
        crashText.append("软件版本:");
        crashText.append(Html.fromHtml("<font color=\"#E51C23\">" + phoneInfo.getAppVersion() + "</font>"));
        crashText.append("\n");
		
		crashText.append("错误信息: ");
        crashText.append(Html.fromHtml("<font color=\"#E51C23\">" + crash_.getMessage() + "</font>"));
        crashText.append("\n");
        for (StackTraceElement stackTraceElement : crash_.getStackTrace())
		{
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            String fileName = stackTraceElement.getFileName();
            String line = stackTraceElement.getLineNumber() + "";
            crashText.append("\t\t\t\t\t\t");
            crashText.append(Html.fromHtml("<font  color=\"#E51C23\">at</font>"));
            crashText.append("\t" + className);
            crashText.append("." + methodName);
            crashText.append("(");
            crashText.append(Html.fromHtml("<font color=\"#E51C23\">" + fileName + "</font>"));
            crashText.append(":");
            crashText.append(Html.fromHtml("<u><font color=\"#5677FC\">" + line + "</font></u>"));
            crashText.append(")");
            crashText.append("\n");
        }
		
	}
}
