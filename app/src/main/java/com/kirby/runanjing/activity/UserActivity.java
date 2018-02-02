package com.kirby.runanjing.activity;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.widget.*;
import android.util.*;
import cn.bmob.v3.*;
import cn.bmob.v3.exception.*;
import cn.bmob.v3.listener.*;
import com.github.anzewei.parallaxbacklayout.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.adapter.*;
import com.kirby.runanjing.bean.*;
import com.kirby.runanjing.bmob.*;
import com.kirby.runanjing.untils.*;
import com.scwang.smartrefresh.layout.api.*;
import com.scwang.smartrefresh.layout.listener.*;
import java.util.*;

import com.kirby.runanjing.R;

@ParallaxBack
public class UserActivity extends BaseActivity
{
	private CollapsingToolbarLayout coll;
	private List<Mess> messlist = new ArrayList<>();
	private MyUser u;
	private int messItem=0;
	private RecyclerView re;
	private RefreshLayout 刷新;
	private MessageAdapter adapter;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        Theme.setClassTheme(this);
		setContentView(R.layout.activity_user);
		Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		u = BmobUser.getCurrentUser(MyUser.class);
		coll = (CollapsingToolbarLayout)findViewById(R.id.折叠);
		coll.setTitle(getUserName());
		initMess();
		getMessage();
	}
	private void initMess()
	{
//设置显示留言的列表
		re = (RecyclerView)findViewById(R.id.动态列表);
		GridLayoutManager layoutManager=new GridLayoutManager(this, 1);
		re.setLayoutManager(layoutManager);
		adapter = new MessageAdapter(messlist);	
	}
	public void  getMessage()
	{
		messlist.clear();//清空列表
		//使用BmobQuery获取留言数据
		BmobQuery<MessageBmob> query=new BmobQuery<MessageBmob>();
		query.order("-createdAt");//时间降序排列
		query.addWhereEqualTo("nickname",getUserName());
		query.findObjects(new FindListener<MessageBmob>() {
			
				@Override
				public void done(List<MessageBmob> list, BmobException e)
				{
					if (e == null)
					{
						Message message = messHandler.obtainMessage();
						message.what = 0;
						//以消息为载体
						message.obj = list;//这里的list就是查询出list
						//向handler发送消息
						messHandler.sendMessage(message);
						messItem = 20;
					}
					else
					{
						Log.e("bmob", "" + e);
						刷新.finishRefresh();
					}
				}
			});
	}
	private Handler messHandler=new Handler(){

		private String message;

		private boolean show_all;
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
						if (message_full.length() > 40)
						{
							message = message_full.substring(0, 40) + "...";
							show_all = true;
						}
						else
						{
							message = message_full;
							show_all = false;
						}
						String time_=m.getCreatedAt();
						String time = time_.substring(0, 16);
						Mess mess=new Mess(user, message, time, message_full, show_all);
						//将查询到的数据依次添加到列表
						messlist.add(mess);
						//设置适配器
						re.setAdapter(adapter);
					}			
					break;
			}
		}
	};
	private String getUserId()
	{
		return u.getObjectId();
	}

	private String getUserName()
	{
		return u.getUsername();
	}

}
