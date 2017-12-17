package com.kirby.runanjing.fragment.fab;

import android.app.*;
import android.view.*;
import android.widget.*;
import com.allattentionhere.fabulousfilter.*;
import com.kirby.runanjing.*;

import com.kirby.runanjing.R;
import com.kirby.runanjing.activity.*;
import android.content.*;
import android.content.SharedPreferences.*;

public class SendFabFragment extends AAH_FabulousFragment
 {
    public static SendFabFragment newInstance() {
        SendFabFragment f = new SendFabFragment();
        return f;
    }
    @Override
    public void setupDialog(Dialog dialog, int style) {
		final View contentView = View.inflate(getContext(), R.layout.filter_sample_view, null);
        RelativeLayout rl_content = (RelativeLayout) contentView.findViewById(R.id.edit);
       contentView.findViewById(R.id.发送).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//获取字符串转化为string数据
					EditText 内容=(EditText)contentView.findViewById(R.id.内容_编辑);
					String edit_内容=内容.getText().toString();
					//判断是否为空
					if (edit_内容.isEmpty())
					{
						Toast.makeText(getContext(), getActivity().getString(R.string.is_null), Toast.LENGTH_SHORT).show();
					}
					else
					{			
					SharedPreferences y=getContext().getSharedPreferences("string",getContext().MODE_PRIVATE);
					SharedPreferences.Editor edit=y.edit();
					edit.putString("Message", edit_内容);
					edit.apply();
					closeFilter("closed");
					}
				}
			});
		setAnimationListener((AnimationListener) getActivity());
	    setPeekHeight(300);
        setViewMain(rl_content); //necessary; main bottomsheet view
        setMainContentView(contentView); // necessary; call at end before super
        super.setupDialog(dialog, style); //call super at last
    }
}
