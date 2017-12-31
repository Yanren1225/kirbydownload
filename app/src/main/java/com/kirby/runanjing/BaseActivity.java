package com.kirby.runanjing;

import android.os.*;
import android.support.v7.app.*;
import com.jaeger.library.*;
import com.kirby.runanjing.untils.*;
import android.util.*;

public class BaseActivity extends AppCompatActivity
 {
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
