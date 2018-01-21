package com.kirby.runanjing;

import android.os.*;
import android.support.v7.app.*;
import com.jaeger.library.*;
import com.kirby.runanjing.untils.*;
import android.util.*;
import cn.bmob.v3.*;

public class BaseActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Bmob.initialize(this, "e39c2e15ca40b358b0dcc933236c1165");
	}
	
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
		StatusBarUtil.setColor(this, getDarkColorPrimary(),0);
    }
	public int getDarkColorPrimary(){
		TypedValue typedValue = new  TypedValue();
		getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
		return typedValue.data;
	}
}
