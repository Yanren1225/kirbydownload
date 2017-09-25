package com.kirby.runanjing.fragment;

import android.app.*;
import android.view.*;
import android.widget.*;
import com.allattentionhere.fabulousfilter.*;
import com.kirby.runanjing.*;

import com.kirby.runanjing.R;
import com.kirby.runanjing.activity.*;

public class SampleFabFragment extends AAH_FabulousFragment
 {
    public static SampleFabFragment newInstance() {
        SampleFabFragment f = new SampleFabFragment();
        return f;
    }
    @Override
    public void setupDialog(Dialog dialog, int style) {
		View contentView = View.inflate(getContext(), R.layout.filter_sample_view, null);
        RelativeLayout rl_content = (RelativeLayout) contentView.findViewById(R.id.edit);
       contentView.findViewById(R.id.发送).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					closeFilter("closed");
				}
			});
	    setPeekHeight(400);
        setViewMain(rl_content); //necessary; main bottomsheet view
        setMainContentView(contentView); // necessary; call at end before super
        super.setupDialog(dialog, style); //call super at last
    }
}
