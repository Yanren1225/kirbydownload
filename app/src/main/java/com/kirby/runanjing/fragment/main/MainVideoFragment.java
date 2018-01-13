package com.kirby.runanjing.fragment.main;

import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import cn.bmob.v3.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.activity.*;

public class MainVideoFragment extends Fragment
{
	private View view;

	private MainActivity m;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
        view = inflater.inflate(R.layout.main_video, container, false);
		m = (MainActivity)getActivity();
		Bmob.initialize(getActivity(), "e39c2e15ca40b358b0dcc933236c1165");
		initVideo(view);
		return view;
	}

	private void initVideo(View view)
	{
		// TODO: Implement this method
	}
}
