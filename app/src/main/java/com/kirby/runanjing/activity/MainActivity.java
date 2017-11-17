package com.kirby.runanjing.activity;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import cn.bmob.v3.*;
import cn.bmob.v3.datatype.*;
import cn.bmob.v3.exception.*;
import cn.bmob.v3.listener.*;
import com.allattentionhere.fabulousfilter.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.fragment.main.*;
import com.kirby.runanjing.untils.*;
import java.util.*;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import com.kirby.runanjing.R;
import java.io.*;
public class MainActivity extends AppCompatActivity implements AAH_FabulousFragment.AnimationListener 
{
	private NavigationView navView;
	private DrawerLayout drawerLayout;
	private String name;
	private RelativeLayout notok;
	private TextView user_name;
	private RelativeLayout ok;
	private Button user;
	private Button login;
	private MyUser u;
	private String email;
	private String id;

	private Toolbar toolbar;

	private Context gameContext;

	private ProgressDialog progressDialog;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= 21)
		{
			View decorView=getWindow().getDecorView();
			decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			getWindow().setStatusBarColor(Color.TRANSPARENT);
		}
		Theme.setClassTheme(this);
        setContentView(R.layout.main);
		//初始化bmob
		Bmob.initialize(this, "e39c2e15ca40b358b0dcc933236c1165");
		//跳转GameListActivity要用的数据
		setApply();	
		//配置toolbar
		toolbar = (Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		toolbar.setSubtitle("资源");
		replaceFragment(new MainGameFragment());
		//使用BmobUser类获取部分用户数据
		u = BmobUser.getCurrentUser(MyUser.class);
		//侧滑
		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_main);
		navView = (NavigationView)findViewById(R.id.nav_view);
		initNav();
		ActionBar actionBar=getSupportActionBar();
		if (actionBar != null)
		{
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeAsUpIndicator(R.drawable.menu);
		}
		navView.setCheckedItem(R.id.game);
		navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

				@Override
				public boolean onNavigationItemSelected(MenuItem item)
				{
					drawerLayout.closeDrawers();
					switch (item.getItemId())
					{
						case R.id.game:
							toolbar.setSubtitle("资源");
							replaceFragment(new MainGameFragment());
							break;
						case R.id.jsz:
							toolbar.setSubtitle("金手指");
							replaceFragment(new MainJszFragment());
							break;
						case R.id.mess:
							toolbar.setSubtitle("闲聊");
							replaceFragment(new MainMessFragment());			
							break;
					}
					return true;
				}
			});
	}

	private void initNav()
	{
		View navview_header = navView.inflateHeaderView(R.layout.nav_header);
        //ImageView user_pic = (ImageView) drawview.findViewById(R.id.imageViewIcon);		
		ok = (RelativeLayout)navview_header.findViewById(R.id.ok);
		notok = (RelativeLayout)navview_header.findViewById(R.id.notok);
		user_name = (TextView)navview_header.findViewById(R.id.user_name);
		user = (Button)navview_header.findViewById(R.id.user);
		login = (Button)navview_header.findViewById(R.id.login);
		user.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					initUser();
				}
			});
		login.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					drawerLayout.closeDrawers();
					replaceFragment(new MainLoginFragment());
				}
			});
		if (null == u)
		{
			navView.getMenu().removeItem(R.id.mess);	
			ok.setVisibility(View.GONE);
		}
		else
		{
			notok.setVisibility(View.GONE);
			user_name.setText(u.getUsername());
		}
	}
	public void replaceFragment(Fragment fragment)
	{
		FragmentManager fragmentManager=getSupportFragmentManager();
		FragmentTransaction transaction=fragmentManager.beginTransaction();
		transaction.replace(R.id.main_fragment, fragment);
		transaction.commit();
	}
	private void setApply()
	{
		SharedPreferences.Editor y=getSharedPreferences("string", MODE_PRIVATE).edit();
		y.putString("主机名称", "0");
		y.putString("游戏或模拟器名称", "0");
		y.apply();
	}
	@Override
	protected void onDestroy()//在退出程序时恢复数据
	{	
		super.onDestroy();
		SharedPreferences.Editor y=getSharedPreferences("string", MODE_PRIVATE).edit();
		y.putString("主机名称", "0");
		y.putString("游戏或模拟器名称", "0");
		y.apply();
    }

	public void setCustomTheme(int i)
	{
		Theme.setTheme(MainActivity.this, i);
		SharedPreferences.Editor y=getSharedPreferences("customtheme", MODE_PRIVATE).edit();
		y.putInt("id", i);
		y.apply();
		open();
	}
	public void open()
	{
		Intent intent = getIntent();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);//假装没退出过...
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		startActivity(intent);
	}
	//初始化toolbar菜单
	public boolean onCreateOptionsMenu(Menu menu)
	{
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
	@Override
	public void onOpenAnimationStart()
	{
		// TODO: Implement this method
	}

	@Override
	public void onOpenAnimationEnd()
	{
		// TODO: Implement this method
	}

	@Override
	public void onCloseAnimationStart()
	{
		SharedPreferences console=getSharedPreferences("string", Context.MODE_WORLD_READABLE);
		String edit_内容= console.getString("Message", "");
		//自定义MessBmob发送留言
		MessageBmob mess = new MessageBmob();
		mess.setMessage(edit_内容);
		mess.setNickname(name);
		mess.save(new SaveListener<String>() {
				@Override
				public void done(String objectId, BmobException e)
				{
					if (e == null)
					{		
						MainMessFragment main_mess=(MainMessFragment)getSupportFragmentManager().findFragmentById(R.id.main_fragment);
						main_mess.getMessage();
						Toast.makeText(MainActivity.this, "发送成功：" + objectId, Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(MainActivity.this, "发送失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
					}
				}
			});
	}

	@Override
	public void onCloseAnimationEnd()
	{
		// TODO: Implement this method
	}
	private void initUser()
	{
		name = u.getUsername();
		email = u.getEmail();
		id = u.getObjectId();
		new AlertDialog.Builder(MainActivity.this)
			.setTitle("用户信息")
			.setMessage("用户名:" + name + "\n邮箱:" + email)
			.setPositiveButton("修改邮箱", new
			DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					LayoutInflater lay_1 = getLayoutInflater();
					final View modification_email_layout = lay_1.inflate(R.layout.dialog_modification_email, null);
					new AlertDialog.Builder(MainActivity.this)
						.setTitle("修改邮箱(成功后需要重新登录)")
						.setView(modification_email_layout) 
						.setPositiveButton("确定", new
						DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								EditText 修改邮箱_原邮箱=(EditText)modification_email_layout.findViewById(R.id.修改邮箱_原邮箱);
								EditText 修改邮箱_新邮箱=(EditText)modification_email_layout.findViewById(R.id.修改邮箱_新邮箱);
								String edit_原邮箱=修改邮箱_原邮箱.getText().toString();
								String edit_新邮箱=修改邮箱_新邮箱.getText().toString();
								if (edit_原邮箱.isEmpty() || edit_新邮箱.isEmpty())
								{
									Toast.makeText(MainActivity.this, "不能为空！", Toast.LENGTH_SHORT).show();
								}
								else
								{
									if (email.equals(edit_原邮箱))
									{
										MyUser 邮箱=new MyUser();
										邮箱.setEmail(edit_新邮箱);
										邮箱.update(id, new UpdateListener() {

												@Override
												public void done(BmobException e)
												{
													if (e == null)
													{
														Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
														u.logOut();
														open();
													}
													else
													{
														Toast.makeText(MainActivity.this, "修改失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
													}
												}

											});
									}
									else
									{
										Toast.makeText(MainActivity.this, "原邮箱出错", Toast.LENGTH_SHORT).show();
									}
								}
							}
						}
					)					
						.setNegativeButton("取消", null)
						.show();
				}
			}
		)
			.setNegativeButton("修改密码", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					LayoutInflater lay_2 = getLayoutInflater();
					final View modification_password_layout = lay_2.inflate(R.layout.dialog_modification_password, null);
					new AlertDialog.Builder(MainActivity.this)
						.setTitle("修改密码(成功后需要重新登录)")
						.setView(modification_password_layout) 
						.setPositiveButton("确定", new
						DialogInterface.OnClickListener()
						{

							private int text;
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								EditText 修改密码_原密码=(EditText)modification_password_layout.findViewById(R.id.修改密码_原密码);
								EditText 修改密码_新密码=(EditText)modification_password_layout.findViewById(R.id.修改密码_新密码);
								EditText 修改密码_验证=(EditText)modification_password_layout.findViewById(R.id.修改密码_验证);
								String edit_原密码=修改密码_原密码.getText().toString();
								String edit_新密码=修改密码_新密码.getText().toString();
								String edit_验证=修改密码_验证.getText().toString();
								if (edit_原密码.isEmpty() || edit_新密码.isEmpty() || edit_验证.isEmpty())
								{
									Toast.makeText(MainActivity.this, "不能为空！", Toast.LENGTH_SHORT).show();
								}
								else
								{
									if (edit_新密码.equals(edit_验证))
									{
										final MyUser pas = new MyUser();
										pas.updateCurrentUserPassword(edit_原密码, edit_新密码, new UpdateListener(){
												@Override
												public void done(BmobException e)
												{
													if (e == null)
													{
														Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
														u.logOut();
														open();
													}
													else
													{
														Toast.makeText(MainActivity.this, "修改失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
													}
												}
											});
									}
									else
									{
										Toast.makeText(MainActivity.this, "两次输入的新密码不同", Toast.LENGTH_SHORT).show();
									}
								}
							}
						}
					)					
						.setNegativeButton("取消", null)
						.show();
				}
			}
		)
			.setNeutralButton("账号登出", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					u.logOut();
					Toast.makeText(MainActivity.this, "登出成功", Toast.LENGTH_SHORT).show();
					open();
				}
			}
		).show();
	}
	@Override
	//获取toolbar菜单id执行事件
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				drawerLayout.openDrawer(GravityCompat.START);
				break;
			case R.id.theme:
				SharedPreferences c=getSharedPreferences("customtheme", Context.MODE_WORLD_READABLE);
				int itemSelected=c.getInt("id", 0);
				String[] singleChoiceItems = {"冰冻蓝","中国红","基佬紫","颐堤蓝","水鸭青","酷安绿","伊藤橙","古铜棕","低调灰"};
                new AlertDialog.Builder(MainActivity.this)
					.setTitle("主题")
					.setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i)
						{
							setCustomTheme(i);
							dialogInterface.dismiss();
						}
					})
					.setNegativeButton("取消", null)
					.show();
				break;
			case R.id.about:
				//跳转AboutActivity
				Intent about=new Intent(MainActivity.this, AboutActivity.class);
				startActivity(about);
				break;
			case R.id.app:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("推荐工具");
				String[] items={"ZArchiver\n一款好用的解压工具可以用来解压下载的游戏文件"};
				builder.setItems(items, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i)
						{
							switch (i)
							{
								case 0:
									downloadappApk("ZArchiver");
									break;
							}
						}
					});
				builder.create();
				builder.show();
				break;
			default:
		}
		return true;	
	}
	public void downloadappApk(final String app_name)
	{
		Toast.makeText(MainActivity.this, "连", Toast.LENGTH_SHORT).show();
		progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setMessage("正在连接服务器");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMax(100);
		progressDialog.show();
		BmobQuery<moniqi> query = new BmobQuery<moniqi>();
        query.addWhereEqualTo("name", app_name);
        query.findObjects(new FindListener<moniqi>(){
				private BmobFile moniqiApk;
				@Override
				public void done(List<moniqi> p1, BmobException p2)
				{
					if (p2 == null)
					{
						for (moniqi apk: p1)
						{
							moniqiApk = apk.getApk();
						}
						appFileDownload(moniqiApk, app_name);
					}
					else
					{
						progressDialog.dismiss();
						Toast.makeText(MainActivity.this, "连接服务器失败:" + p2, Toast.LENGTH_SHORT).show();
					}
				}
			});
	}
	private void appFileDownload(BmobFile moniqiApk, final String app_name)
	{
		File saveFile = new File("/storage/emulated/0/Android/data/com.kirby.runanjing/files");
		moniqiApk.download(saveFile, new DownloadFileListener() {
				@Override
				public void onStart()
				{
					progressDialog.setMessage("正在下载:" + app_name);
				}
				@Override
				public void done(String savePath, BmobException e)
				{
					if (e == null)
					{
						progressDialog.dismiss();
						Toast.makeText(MainActivity.this, "下载完成，保存在:" + savePath, Toast.LENGTH_SHORT).show();
						File file=new File(savePath);
						installAppApk(file);
					}
					else
					{
						progressDialog.dismiss();
						Toast.makeText(MainActivity.this, "下载失败:" + e.getMessage() + "\n错误码:" + e.getErrorCode(), Toast.LENGTH_SHORT).show();
					}
				}
				@Override
				public void onProgress(Integer value, long newworkSpeed)
				{
					progressDialog.setProgress(value);
				}
			});
	}
	protected void installAppApk(File file)
	{
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);       
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");       
        startActivity(intent);
	}
	public void theDownload(Context con, String game_name)
	{
		gameContext = con;
		switch (game_name)
		{
			case "星之卡比 梦之泉DX":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3kURIBIZ", "https://eyun.baidu.com/s/3o86TXDS", "https://eyun.baidu.com/s/3dF22BWP");
				break;
			case "星之卡比 镜之大迷宫":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3hs7Mjsg", "https://eyun.baidu.com/s/3c5qBl8", "https://eyun.baidu.com/s/3i5t6Z3J");
				break;
			case "星之卡比 3":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3pKTD8EZ", "https://eyun.baidu.com/s/3gfwui2n", "");
				break;
			case "星之卡比 超豪华版":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3qXEc4Xm", "https://eyun.baidu.com/s/3nu8IVpv", "");
				break;
			case "星之卡比 卡比梦幻都":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3hsvCjfI", "https://eyun.baidu.com/s/3jHCmNps", "");
				break;
			case "[仅美国]星之卡比 卡比魔方气泡":
				showDownloadDialog(game_name, "[限定]仅存在美版", "美版" , null ,  null, "https://eyun.baidu.com/s/3eSuusSi", "", "");
				break;
			case "[仅日本]星之卡比 卡比宝石星DX":
				showDownloadDialog(game_name, "[限定]仅存在日版", "日版", null, null, "https://eyun.baidu.com/s/3kVDhaS3", "", "");
				break;
			case "星之卡比 64":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3jHPKdMY", "https://eyun.baidu.com/s/3jHPKdMY", "");
				break;
			case "星之卡比 飞天赛车":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3qYAoXGC", "", "");
				break;
			case "星之卡比 重返梦幻岛":
				showDownloadDialog(game_name, "[注意有单/多文件之分]\n请选择你需要的版本", "日版（单）", "美版（多）", "汉化（多）", "https://eyun.baidu.com/s/3skEbla1", "https://eyun.baidu.com/s/3gf5Oxe7", "https://eyun.baidu.com/s/3gfqpuin");
				break;
			case "星之卡比 毛线卡比":
				showDownloadDialog(game_name, "[注意有单/多文件之分]\n请选择你需要的版本", "美版（多）", "美版（单）", "汉化（单）", "https://eyun.baidu.com/s/3c2inVEC", "https://eyun.baidu.com/s/3dFACfWd", "https://eyun.baidu.com/s/3eRYayD8");
				break;
			case "星之卡比 触摸卡比":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3hsqS3S4", "https://eyun.baidu.com/s/3c27V89i", "https://eyun.baidu.com/s/3i5Pwsxn");
				break;
			case "星之卡比 超究豪华版":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3i4Ricbb", "https://eyun.baidu.com/s/3nvCwXlB", "https://eyun.baidu.com/s/3c2EblZi");
				break;
			case "星之卡比 呐喊团":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3bo4Z5TH", "https://eyun.baidu.com/s/3czmilC", "https://eyun.baidu.com/s/3hr4PxbA");
				break;
			case "星之卡比 集合！卡比":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3geO4mbx", "https://eyun.baidu.com/s/3eSijdHS", "https://eyun.baidu.com/s/3o80PA6e");
				break;
			case "星之卡比 1":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3pKN6dIz", "https://eyun.baidu.com/s/3pKZHpaF", "");
				break;
			case "星之卡比 2":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3i57Kjjv", "https://eyun.baidu.com/s/3jI4urlW", "");
				break;
			case "星之卡比 卡比宝石星":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3miFgbtI", "https://eyun.baidu.com/s/3nvtzunn", "");
				break;
			case "星之卡比 卡比打砖块":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3i5Dkqah", "https://eyun.baidu.com/s/3ge7808r", "");
				break;
			case "星之卡比 卡比弹珠台":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3i48QqMh", "https://eyun.baidu.com/s/3eSwv1DK", "");
				break;
			case "星之卡比 滚滚卡比":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3pKP9eav", "https://eyun.baidu.com/s/3nuQZavJ", "");
				break;
			case "星之卡比 梦之泉物语":
				showDownloadDialog(game_name, "请选择你需要的版本", "日版", "美版", "汉化", "https://eyun.baidu.com/s/3pKXFx8n", "https://eyun.baidu.com/s/3pKZHpaF", "https://eyun.baidu.com/s/3i4HC8FN");
				break;
			case "GBA模拟器\nMy Boy!":
				showOtherDownloadDialog("gba", game_name);
				break;
			case "SFC模拟器\nSnes9x EX+":
				showOtherDownloadDialog("sfc", game_name);
				break;
			case "N64模拟器\nTendo64":
				showOtherDownloadDialog("n64", game_name);
				break;
			case "NDS模拟器\nDraStic":
				showOtherDownloadDialog("nds", game_name);
				break;
			case "NGC&WII模拟器\nDolphin":
				showOtherDownloadDialog("wii", game_name);
				break;
			case "GB&GBC模拟器\nMy OldBoy!":
				showOtherDownloadDialog("gb", game_name);
				break;
			case "FC模拟器\nNES.emu":
				showOtherDownloadDialog("fc", game_name);
				break;
		}
	}
	public void downloadMoniqiApk(final String game_name)
	{
		progressDialog = new ProgressDialog(gameContext);
		progressDialog.setMessage("正在连接服务器");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMax(100);
		progressDialog.show();
		BmobQuery<moniqi> query = new BmobQuery<moniqi>();
        query.addWhereEqualTo("name", game_name);
        query.findObjects(new FindListener<moniqi>(){
				private BmobFile moniqiApk;
				@Override
				public void done(List<moniqi> p1, BmobException p2)
				{
					if (p2 == null)
					{
						for (moniqi apk: p1)
						{
							moniqiApk = apk.getApk();
						}
						moniqiFileDownload(moniqiApk, game_name);
					}
					else
					{
						progressDialog.dismiss();
						Toast.makeText(gameContext, "连接服务器失败:" + p2, Toast.LENGTH_SHORT).show();
					}
				}
			});
	}
	private void moniqiFileDownload(BmobFile moniqiApk, final String game_name)
	{
		File saveFile = new File("/storage/emulated/0/Android/data/com.kirby.runanjing/files");
		moniqiApk.download(saveFile, new DownloadFileListener() {
				@Override
				public void onStart()
				{
					progressDialog.setMessage("正在下载:" + game_name);
				}
				@Override
				public void done(String savePath, BmobException e)
				{
					if (e == null)
					{
						progressDialog.dismiss();
						Toast.makeText(gameContext, "下载完成，保存在:" + savePath, Toast.LENGTH_SHORT).show();
						File file=new File(savePath);
						installMoniqiApk(file);
					}
					else
					{
						progressDialog.dismiss();
						Toast.makeText(gameContext, "下载失败:" + e.getMessage() + "\n错误码:" + e.getErrorCode(), Toast.LENGTH_SHORT).show();
					}
				}
				@Override
				public void onProgress(Integer value, long newworkSpeed)
				{
					progressDialog.setProgress(value);
				}
			});
	}
	private void showOtherDownloadDialog(final String downloadName, String game_name)
	{
		AlertDialog.Builder dialog = new
			AlertDialog.Builder(gameContext)
			.setTitle(game_name)
			.setMessage("咱只给你汉化版(◦˙▽˙◦)")
			.setPositiveButton("下载", new
			DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					downloadMoniqiApk(downloadName);
				}			
			}
		);dialog.show();
	}
	protected void installMoniqiApk(File file)
	{
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);       
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");       
        gameContext.startActivity(intent);
	}
	public void showDownloadDialog(String name, String mess, String pos, String neg, String neu, final String pos_url, final String neg_url, final String neu_url)
	{
		AlertDialog.Builder dialog = new
			AlertDialog.Builder(gameContext)
			.setTitle(name)
			.setMessage(mess)
			.setPositiveButton(pos, new
			DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Intent web = new Intent();        
					web.setAction("android.intent.action.VIEW");    
					Uri content_url = Uri.parse(pos_url);   
					web.setData(content_url);  
					gameContext.startActivity(web);  			
				}
			}
		)
			.setNegativeButton(neg, new DialogInterface.OnClickListener()
			{
				@Override

				public void onClick(DialogInterface dialog, int which)
				{
					Intent web = new Intent();        
					web.setAction("android.intent.action.VIEW");    
					Uri content_url = Uri.parse(neg_url);   
					web.setData(content_url);  
					gameContext.startActivity(web);  							
				}
			}
		)
			.setNeutralButton(neu, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Intent web = new Intent();        
					web.setAction("android.intent.action.VIEW");    
					Uri content_url = Uri.parse(neu_url);   
					web.setData(content_url);  
					gameContext.startActivity(web);  			
				}
			}
		);
		dialog.show();
	}
}

