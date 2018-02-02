package com.kirby.runanjing.activity;

import android.os.*;
import android.support.v7.widget.*;
import com.github.anzewei.parallaxbacklayout.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.untils.*;

import com.kirby.runanjing.R;

@ParallaxBack
public class PayActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        Theme.setClassTheme(this);
		setContentView(R.layout.activity_pay);
		Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
	}
}
