package com.kirby.runanjing.fragment.preference;

import android.os.*;
import android.preference.*;
import com.kirby.runanjing.*;
import android.content.*;
import android.net.*;
import android.widget.*;

public  class AboutPreferenceFragment extends PreferenceFragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.about);
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference)
	{
		switch(preference.getKey()){
			case "star":
				Intent star=new Intent ("android.intent.action.VIEW");
				star .setData(Uri.parse("market://details?id=com.kirby.runanjing"));
				star .setPackage("com.coolapk.market");
				getActivity().startActivity(star);
				break;
			case "help":
				String key="6j76WE8N9l378jnsWzmmUDv5HohOteHu";
				joinQQGroup(key);
				break;
			case "WeChat":
				Intent WeChat=new Intent ("android.intent.action.VIEW");
				WeChat .setData(Uri.parse("https://github.com/nihaocun/pay/blob/master/WX.png"));
				getActivity().startActivity(WeChat);
				break;
			case "Alibaba":
				Intent Alibaba=new Intent ("android.intent.action.VIEW");
				Alibaba .setData(Uri.parse("https://github.com/nihaocun/pay/blob/master/ZFB.jpg"));
				getActivity().startActivity(Alibaba);
				break;
			case "github":
				Intent github=new Intent ("android.intent.action.VIEW");
				github.setData(Uri.parse("https://github.com/nihaocun/kirbydownload"));
				getActivity().startActivity(github);
				break;
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
	public boolean joinQQGroup(String key) {
		Intent intent = new Intent();
		intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
		// 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		try {
			getActivity().startActivity(intent);
			return true;
		} catch (Exception e) {
			// 未安装手Q或安装的版本不支持
			return false;
		}
	}
}
