package com.kirby.runanjing.activity;

import android.app.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import cn.bmob.v3.*;
import cn.bmob.v3.exception.*;
import cn.bmob.v3.listener.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.adapter.*;
import com.kirby.runanjing.untils.*;
import java.util.*;
import android.graphics.*;

public class MessageActivity extends AppCompatActivity
{
	private SwipeRefreshLayout 刷新;
	private List<Mess> messlist = new ArrayList<>();
	private String name;

	private MessageAdapter adapter;

	private RecyclerView re;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_layout);
		Bmob.initialize(this, "e39c2e15ca40b358b0dcc933236c1165");
		getMessage();
	re=(RecyclerView)findViewById(R.id.留言);
		GridLayoutManager layoutManager=new GridLayoutManager(this, 1);
		re.setLayoutManager(layoutManager);
		adapter = new MessageAdapter(messlist);	
		刷新 = (SwipeRefreshLayout)findViewById(R.id.刷新);
		刷新.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
		//刷新.setRefreshing(true);
		刷新.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
				@Override
				public void onRefresh()
				{
					getMessage();
					refreshMessage();
				}
			});
		MyUser u = BmobUser.getCurrentUser(MyUser.class);
		name = u.getUsername();
		FloatingActionButton 编写=(FloatingActionButton)findViewById(R.id.FAB_编写);
		编写.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v)
				{
					final Dialog fullscreenDialog = new Dialog(MessageActivity.this, R.style.DialogFullscreen);
					fullscreenDialog.setContentView(R.layout.dialog_fullscreen);
					ImageView 关闭 = (ImageView) fullscreenDialog.findViewById(R.id.关闭);
					ImageView 发送=(ImageView) fullscreenDialog.findViewById(R.id.发送);
					关闭.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v)
							{
								fullscreenDialog.dismiss();
							}
						});
					发送.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v)
							{		
								EditText 标题=(EditText)fullscreenDialog.findViewById(R.id.标题_编辑);
								EditText 内容=(EditText)fullscreenDialog.findViewById(R.id.内容_编辑);
								String edit_标题=标题.getText().toString();
								String edit_内容=内容.getText().toString();
								if (edit_标题.isEmpty() || edit_内容.isEmpty())
								{
									Toast.makeText(MessageActivity.this, "内容或标题不能为空！", Toast.LENGTH_SHORT).show();
								}
								else
								{
									MessageBmob mess = new MessageBmob();
									mess.setTitle(edit_标题);
									mess.setMessage(edit_内容);
									mess.setNickname(name);
									mess.save(new SaveListener<String>() {
											@Override
											public void done(String objectId, BmobException e)
											{
												if (e == null)
												{
													刷新.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
															@Override
															public void onRefresh()
															{
																refreshMessage();
															}
														});
													Toast.makeText(MessageActivity.this, "发送成功：" + objectId, Toast.LENGTH_SHORT).show();
												}
												else
												{
													Toast.makeText(MessageActivity.this, "发送失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
												}
											}
										});
								}
								fullscreenDialog.dismiss();
							}
						});
					fullscreenDialog.show();
				}
			});
	}
	private void getMessage()
	{
		messlist.clear();
		BmobQuery<MessageBmob> query=new BmobQuery<MessageBmob>();
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
        @Override
        public void handleMessage(Message msg)
		{
            switch (msg.what)
			{
                case 0:
                    List<MessageBmob> list= (List<MessageBmob>)msg.obj;
					//Toast.makeText(MessageActivity.this, "查询成功：共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
					for (MessageBmob m : list)
					{
						String 用户名=m.getNickname();
						String 标题=m.getTitle();
						String 内容=m.getMessage();
						String id=m.getObjectId();
						String 时间=m.getCreatedAt();
						Mess mess=new Mess(用户名, 标题, 内容, 时间, id);
						messlist.add(mess);
						Toast.makeText(MessageActivity.this, "" + messlist, Toast.LENGTH_SHORT).show();
						re.setAdapter(adapter);
					}
					
                    break;
            }
        }
    };
	private void refreshMessage()
	{
		new Thread(new Runnable() {
				public void run()
				{		
					adapter.notifyDataSetChanged();
						刷新.setRefreshing(false);
				}			
			}).start();
	}
	
}
