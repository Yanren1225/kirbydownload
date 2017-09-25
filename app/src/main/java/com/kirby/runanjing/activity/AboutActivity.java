package com.kirby.runanjing.activity;

import android.content.*;
import android.net.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.kirby.runanjing.*;

import android.support.v7.widget.Toolbar;
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
		//实例化
		RelativeLayout 捐赠_微信=(RelativeLayout)findViewById(R.id.捐赠_微信);
		RelativeLayout 捐赠_支付宝=(RelativeLayout)findViewById(R.id.捐赠_支付宝);
		RelativeLayout 官网=(RelativeLayout)findViewById(R.id.官网);
		RelativeLayout 反馈=(RelativeLayout)findViewById(R.id.反馈);
		RelativeLayout 交流=(RelativeLayout)findViewById(R.id.交流);
		RelativeLayout 开源=(RelativeLayout)findViewById(R.id.开源);

		捐赠_微信.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					Intent intent=new Intent ("android.intent.action.VIEW");
					intent .setData(Uri.parse("https://github.com/nihaocun/pay/blob/master/WX.png"));
					startActivity(intent);
				}
			}
		);
		捐赠_支付宝.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					Intent intent=new Intent ("android.intent.action.VIEW");
					intent .setData(Uri.parse("https://github.com/nihaocun/pay/blob/master/ZFB.jpg"));
					startActivity(intent);
				}
			}
		);
		官网.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					Intent intent=new Intent ("android.intent.action.VIEW");
					intent .setData(Uri.parse("http://www.kirbydownload.sxl.cn"));
					startActivity(intent);
				}
			}
		);
		反馈.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					Intent intent=new Intent ("android.intent.action.VIEW");
					intent .setData(Uri.parse("market://details?id=com.kirby.runanjing"));
					startActivity(intent);
				}
			}
		);
		交流.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					String key="6j76WE8N9l378jnsWzmmUDv5HohOteHu";
					joinQQGroup(key);
				}
			}
		);
		开源.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					Intent intent=new Intent(AboutActivity.this,GithubActivity.class);
					startActivity(intent);
				}
			}
		);
	}
	/****************
	 *
	 * 发起添加群流程。群号：kirby download(519661207) 的 key 为： 6j76WE8N9l378jnsWzmmUDv5HohOteHu
	 * 调用 joinQQGroup(6j76WE8N9l378jnsWzmmUDv5HohOteHu) 即可发起手Q客户端申请加群 kirby download(519661207)
	 *
	 * @param key 由官网生成的key
	 * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
	 ******************/
	public boolean joinQQGroup(String key) {
		Intent intent = new Intent();
		intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
		// 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		try {
			startActivity(intent);
			return true;
		} catch (Exception e) {
			// 未安装手Q或安装的版本不支持
			return false;
		}
	}
}
		
