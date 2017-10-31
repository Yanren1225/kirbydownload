package com.kirby.runanjing.fragment.preference;

import android.os.*;
import android.preference.*;
import com.kirby.runanjing.*;

public  class AboutPreferenceFragment extends PreferenceFragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.about);
	}
}
