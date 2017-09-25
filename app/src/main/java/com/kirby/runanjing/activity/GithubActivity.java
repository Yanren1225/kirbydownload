package com.kirby.runanjing.activity;

import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.untils.*;

public class GithubActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        Theme.setClassTheme(this);
		setContentView(R.layout.github_layout);
		Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		}
}
