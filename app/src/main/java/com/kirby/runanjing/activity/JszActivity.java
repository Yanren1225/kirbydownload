package com.kirby.runanjing.activity;

import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.kirby.runanjing.*;
import java.util.*;

import android.support.v7.widget.Toolbar;
import com.kirby.runanjing.untils.*;
import com.kirby.runanjing.adapter.*;

public class JszActivity extends AppCompatActivity
{
	
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		Theme.setClassTheme(this);
		setContentView(R.layout.jsz_layout);
		//配置toolbar
		final Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		
	}
}
