package com.kirby.runanjing.activity;

import android.os.*;
import android.support.v7.widget.*;
import android.widget.*;
import com.github.anzewei.parallaxbacklayout.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.untils.*;

import android.support.v7.widget.Toolbar;
import com.kirby.runanjing.R;
import android.view.View.*;
import android.view.*;
import android.didikee.donate.*;

@ParallaxBack
public class PayActivity extends BaseActivity
{
	private static String payCode="FKX08445PLEZRZWTESFZAA";
	private Button donate;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        Theme.setClassTheme(this);
		setContentView(R.layout.activity_pay);
		Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		donate=(Button)findViewById(R.id.donate);
		donate.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View view)
				{
					boolean hasInstalledAlipayClient = AlipayDonate.hasInstalledAlipayClient(PayActivity.this);
					if (hasInstalledAlipayClient) {
						AlipayDonate.startAlipayClient(PayActivity.this, payCode);
					}
				}
			});
	}
}
