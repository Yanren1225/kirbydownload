package com.kirby.runanjing.fragment.main;

import android.graphics.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.widget.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import cn.bmob.v3.*;
import cn.bmob.v3.exception.*;
import cn.bmob.v3.listener.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.activity.*;
import com.kirby.runanjing.adapter.*;
import com.kirby.runanjing.fragment.fab.*;
import com.kirby.runanjing.untils.*;
import com.wang.avi.*;
import java.util.*;

import com.kirby.runanjing.R;
import com.wang.avi.indicators.*;
import com.kirby.runanjing.bmob.*;
import com.kirby.runanjing.bean.*;

public class MainMessFragment extends Fragment
{
	private List<Mess> messlist = new ArrayList<>();
	private MessageAdapter adapter;
	private RecyclerView re;
	private SwipeRefreshLayout 刷新;
	private String name;
	private FloatingActionButton 编写;
	private View view;
	private MainActivity m;
	private AVLoadingIndicatorView p;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
        view = inflater.inflate(R.layout.main_mess, container, false);
		m = (MainActivity)getActivity();
		initMess(view);
		getMessage();
		Bmob.initialize(getActivity(), "e39c2e15ca40b358b0dcc933236c1165");
		return view;
	}
	
	private void initMess(View view)
	{
	    p=(AVLoadingIndicatorView)view.findViewById(R.id.mess_刷新);
		String[] arr = {
			 "BallPulseIndicator"
			,"BallGridPulseIndicator"
			,"BallClipRotateIndicator"
			,"BallClipRotatePulseIndicator"
			,"SquareSpinIndicator"
			,"BallClipRotateMultipleIndicator"
			,"BallPulseRiseIndicator"
			,"BallRotateIndicator"
			,"CubeTransitionIndicator"
			,"BallZigZagIndicator"
			,"BallZigZagDeflectIndicator"
			,"BallTrianglePathIndicator"
			,"BallScaleIndicator"
			,"LineScaleIndicator"
			,"LineScalePartyIndicator"
			,"BallScaleMultipleIndicator"
			,"BallPulseSyncIndicator"
			,"BallBeatIndicator"
			,"LineScalePulseOutIndicator"
			,"LineScalePulseOutRapidIndicator"
			,"BallScaleRippleIndicator"
			,"BallScaleRippleMultipleIndicator"
			,"BallSpinFadeLoaderIndicator"
			,"LineSpinFadeLoaderIndicator"
			,"TriangleSkewSpinIndicator"
			,"PacmanIndicator"
			,"BallGridBeatIndicator"
			,"SemiCircleSpinIndicator"
		};
		int i=(int)(Math.random()*arr.length);
		p.setIndicator(arr[i]);
		//设置显示留言的列表
		re = (RecyclerView)view.findViewById(R.id.留言);
		GridLayoutManager layoutManager=new GridLayoutManager(getActivity(), 1);
		re.setLayoutManager(layoutManager);
		adapter = new MessageAdapter(messlist);	
		//刷新数据
		刷新 = (SwipeRefreshLayout)view.findViewById(R.id.刷新);
		刷新.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
		刷新.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
				@Override
				public void onRefresh()
				{
					//刷新
					getMessage();
				}
			});
		//使用BmobUser类获取部分用户数据
		MyUser u = BmobUser.getCurrentUser(MyUser.class);
		name = u.getUsername();
		编写 = (FloatingActionButton)view.findViewById(R.id.FAB_编辑);
		编写.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)			
				{
					//处理发送
					SendFabFragment dialogFrag = SendFabFragment.newInstance();
					dialogFrag.setParentFab(编写);
					dialogFrag.show(m.getSupportFragmentManager(), dialogFrag.getTag());
				}
			});
	}

	public void  getMessage()
	{
		messlist.clear();//清空列表
		//使用BmobQuery获取留言数据
		BmobQuery<MessageBmob> query=new BmobQuery<MessageBmob>();
		query.order("-createdAt");//时间降序排列
		query.findObjects(new FindListener<MessageBmob>() {
				@Override
				public void done(List<MessageBmob> list, BmobException e)
				{
					if (e == null)
					{
						Message message = handler.obtainMessage();
						message.what = 0;
						//以消息为载体
						message.obj = list;//这里的list就是查询出list
						//向handler发送消息
						handler.sendMessage(message);
					}
					else
					{
						Log.e("bmob", "" + e);
					}
				}
			});
	}
	private Handler handler=new Handler(){

		private String message;
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
				case 0:
					List<MessageBmob> list= (List<MessageBmob>)msg.obj;
					for (MessageBmob m : list)
					{
						//从获取的数据中提取需要的数据
						String user=m.getNickname();
						String message_full=m.getMessage();
						if(message_full.length()>40){
							 message=message_full.substring(0,40);
						}
						else
						{
							 message=message_full;
						}
						String time_=m.getCreatedAt();
						String time = time_.substring(0, 16);
						Mess mess=new Mess(user,message,time,message_full);
						//将查询到的数据依次添加到列表
						messlist.add(mess);
						//设置适配器
						re.setAdapter(adapter);
					}			
					//刷新回调
					p.setVisibility(View.GONE);
					刷新.setRefreshing(false);
					break;
			}
		}
	};
}
