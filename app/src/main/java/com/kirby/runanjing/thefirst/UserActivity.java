package com.kirby.runanjing.thefirst;

import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import cn.bmob.v3.*;
import cn.bmob.v3.exception.*;
import cn.bmob.v3.listener.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.activity.*;
import com.kirby.runanjing.untils.*;

public class UserActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.thefirst_user);
		Bmob.initialize(this, "e39c2e15ca40b358b0dcc933236c1165");
		Button next=(Button)findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Intent i=new Intent(UserActivity.this,FinishActivity.class);
					startActivity(i);
					finish();
				}
			});
		Button 登录=(Button)findViewById(R.id.登录);
		Button 注册=(Button)findViewById(R.id.注册);
		//从edittext里获取字符串
		final EditText 登录_用户名=(EditText)findViewById(R.id.用户名);
		final EditText 登录_密码=(EditText)findViewById(R.id.密码);
		登录.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					//获取的字符串转化为string数据类型
					String editText_用户名=登录_用户名.getText().toString();
					String editText_密码=登录_密码.getText().toString();
					//判断是否为空
					if (editText_用户名.isEmpty() || editText_密码.isEmpty())
					{
						Toast.makeText(UserActivity.this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
					}
					else
					{
						//使用BmobUser类进行登陆
						final MyUser bu2 = new MyUser();
						bu2.setUsername(editText_用户名);
						bu2.setPassword(editText_密码);
						bu2.login(new SaveListener<BmobUser>() {
								@Override
								public void done(BmobUser bmobUser, BmobException e)
								{
									if (e == null)
									{
										Toast.makeText(UserActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
										Intent me=new Intent(UserActivity.this, FinishActivity.class);
										startActivity(me);				
									}
									else
									{
										Toast.makeText(UserActivity.this, "登录失败，请检查用户名或密码", Toast.LENGTH_SHORT).show();
									}
								}
							});
					}
				}
			});
		注册.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					//实例化布局
					LayoutInflater inflater = getLayoutInflater();
					final View 注册_layout = inflater.inflate(R.layout.dialog_register, null);
					new AlertDialog.Builder(UserActivity.this)
						.setTitle("注册")
						.setView(注册_layout)
						.setPositiveButton("确定", new
						DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								//从实例化布局的edittext中获取字符串并转化为string数据
								EditText 注册_用户名=(EditText)注册_layout.findViewById(R.id.注册_用户名);
								EditText 邮箱=(EditText)注册_layout.findViewById(R.id.邮箱);
								EditText 注册_密码=(EditText)注册_layout.findViewById(R.id.注册_密码);
								EditText 重复密码=(EditText)注册_layout.findViewById(R.id.重复密码);
								String editText_用户名=注册_用户名.getText().toString();
								String editText_邮箱=邮箱.getText().toString();
								String editText_密码=注册_密码.getText().toString();
								String editText_重复密码=重复密码.getText().toString();
								//判断是否为空
								if (editText_用户名.isEmpty() || editText_邮箱.isEmpty() || editText_密码.isEmpty() || editText_重复密码.isEmpty())
								{
									Toast.makeText(UserActivity.this, "你可能有哪一项没输入!", Toast.LENGTH_SHORT).show();
								}
								else
								{
									//判断两次的密码是否一样
									if (editText_密码.equalsIgnoreCase(editText_重复密码))
									{
										//使用BmobUser类进行注册
										MyUser myUser=new MyUser();
										myUser.setUsername(editText_用户名);
										myUser.setPassword(editText_密码);
										myUser.setEmail(editText_邮箱);
										myUser.signUp(new SaveListener<MyUser>() {
												@Override
												public void done(MyUser s, BmobException e)
												{
													if (e == null)
													{
														Toast.makeText(UserActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
													}
													else
													{
														Toast.makeText(UserActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
													}
												}
											});
									}
									else
									{
										Toast.makeText(UserActivity.this, "两次输入的密码不同", Toast.LENGTH_SHORT).show();
									}
								}
							}
						}
					)
						.setNegativeButton("取消", null).show();
				}
			});
	}
}
