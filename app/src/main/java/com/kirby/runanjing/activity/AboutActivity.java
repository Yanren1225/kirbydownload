package com.kirby.runanjing.activity;

import android.content.*;
import android.net.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.fragment.preference.*;
import com.kirby.runanjing.untils.*;

public class AboutActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        Theme.setClassTheme(this);
		setContentView(R.layout.about);
		Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		getFragmentManager().beginTransaction().replace(R.id.about_fragment, new AboutPreferenceFragment()).commit();
	}
}
		
