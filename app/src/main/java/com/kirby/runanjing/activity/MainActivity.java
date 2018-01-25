package com.kirby.runanjing.activity;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
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
import com.base.bj.trpayjar.domain.*;
import com.base.bj.trpayjar.listener.*;
import com.base.bj.trpayjar.utils.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.adapter.*;
import com.kirby.runanjing.bmob.*;
import com.kirby.runanjing.fragment.main.*;
import com.kirby.runanjing.untils.*;
import com.nightonke.boommenu.*;
import com.nightonke.boommenu.BoomButtons.*;
import java.io.*;
import java.util.*;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import com.kirby.runanjing.R;

public class MainActivity extends BaseActivity implements AAH_FabulousFragment.AnimationListener 
{
	private final static String appkey = "0b658d2e3de040d188119d3d03e45019";
	String userid = "trpay@52yszd.com";//商户系统用户ID(如：trpay@52yszd.com，商户系统内唯一)
	String outtradeno = UUID.randomUUID() + "";//商户系统订单号(为便于演示，此处利用UUID生成模拟订单号，商户系统内唯一)
	String tradename = "Kirby download捐赠";
	String backparams = "name=2&age=22";//商户系统回调参数
	String notifyurl = "http://101.200.13.92/notify/alipayTestNotify";//商户系统回调地址


	private DrawerLayout drawerLayout;
	private String name;
	private MyUser u;
	private String email;
	private String id;

	private Toolbar toolbar;

	private Context gameContext;

	private ProgressDialog progressDialog;

	private BoomMenuButton bmb;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		Theme.setClassTheme(this);
        setContentView(R.layout.activity_main);
		TrPay.getInstance(this).initPaySdk(appkey, "coolapk");
		//跳转GameListActivity要用的数据
		setApply();	
		//配置toolbar
		toolbar = (Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		toolbar.setSubtitle(R.string.ziyuan);
		replaceFragment(new MainGameFragment());
		//使用BmobUser类获取部分用户数据
		u = BmobUser.getCurrentUser(MyUser.class);
		bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
		initBmb();
	}

	private void initBmb()
	{

		if (null == u)
		{
			HamButton.Builder user = new HamButton.Builder()
				.normalTextRes(R.string.ziyuan)
				.normalImageRes(R.drawable.ic_account)
				.listener(new OnBMClickListener(){
					@Override
					public void onBoomButtonClick(int p1)
					{
						replaceFragment(new MainLoginFragment());
						toolbar.setSubtitle(R.string.login_title);
					}
				});
			bmb.addBuilder(user);
		}
		else
		{
			HamButton.Builder user = new HamButton.Builder()
				.normalText(getResources().getString(R.string.hello) + u.getUsername())
				.normalImageRes(R.drawable.ic_account)
				.listener(new OnBMClickListener(){
					@Override
					public void onBoomButtonClick(int p1)
					{
						Intent user=new Intent(MainActivity.this, UserActivity.class);
						startActivity(user);
					}
				});
			bmb.addBuilder(user);
		}
		HamButton.Builder game = new HamButton.Builder()
			.normalTextRes(R.string.ziyuan)
			.normalImageRes(R.drawable.ic_game)
			.listener(new OnBMClickListener(){
				@Override
				public void onBoomButtonClick(int p1)
				{
					toolbar.setSubtitle(R.string.ziyuan);
					replaceFragment(new MainGameFragment());
				}
			});
		bmb.addBuilder(game);
		HamButton.Builder jsz = new HamButton.Builder()
			.normalTextRes(R.string.jsz_title)
			.listener(new OnBMClickListener(){
				@Override
				public void onBoomButtonClick(int p1)
				{
					toolbar.setSubtitle(R.string.jsz_title);
					replaceFragment(new MainJszFragment());
				}
			});
		bmb.addBuilder(jsz);
		HamButton.Builder video = new HamButton.Builder()
			.normalTextRes(R.string.video_title)
			.listener(new OnBMClickListener(){
				@Override
				public void onBoomButtonClick(int p1)
				{
					toolbar.setSubtitle(R.string.video_title);
					replaceFragment(new MainVideoFragment());
				}
			});
		bmb.addBuilder(video);
		HamButton.Builder mess = new HamButton.Builder()
			.normalTextRes(R.string.talk)
			.normalImageRes(R.drawable.ic_talk2)
			.listener(new OnBMClickListener(){
				@Override
				public void onBoomButtonClick(int p1)
				{
					toolbar.setSubtitle(R.string.talk);
					replaceFragment(new MainMessFragment());			
				}
			});
		bmb.addBuilder(mess);
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
		SharedPreferences.Editor y=getSharedPreferences("string", 0).edit();
		y.putString("主机名称", "0");
		y.putString("游戏或模拟器名称", "0");
		y.apply();
	}
	@Override
	protected void onDestroy()//在退出程序时恢复数据
	{	
		super.onDestroy();
		SharedPreferences.Editor y=getSharedPreferences("string", 0).edit();
		y.putString("主机名称", "0");
		y.putString("游戏或模拟器名称", "0");
		y.apply();
    }

	public void setCustomTheme(int i)
	{
		Theme.setTheme(MainActivity.this, i);
		SharedPreferences.Editor y=getSharedPreferences("customtheme", 0).edit();
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

	}

	@Override
	public void onOpenAnimationEnd()
	{
		// TODO: Implement this method
	}

	@Override
	public void onCloseAnimationStart()
	{
		SharedPreferences console=getSharedPreferences("string", 0);
		String edit_内容= console.getString("Message", "");
		//自定义MessBmob发送留言
		MessageBmob mess = new MessageBmob();
		mess.setMessage(edit_内容);
		mess.setNickname(u.getUsername());
		mess.save(new SaveListener<String>() {
				@Override
				public void done(String objectId, BmobException e)
				{
					if (e == null)
					{		
						MainMessFragment main_mess=(MainMessFragment)getSupportFragmentManager().findFragmentById(R.id.main_fragment);
						main_mess.getMessage();
						Toast.makeText(MainActivity.this, getResources().getString(R.string.mess_true) + objectId, Toast.LENGTH_SHORT).show();
						SharedPreferences y=getSharedPreferences("string", 0);
						SharedPreferences.Editor edit=y.edit();
						edit.putString("Message", "");
						edit.apply();
					}
					else
					{
						Toast.makeText(MainActivity.this, getResources().getString(R.string.mess_false) + e.getMessage(), Toast.LENGTH_SHORT).show();
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
			.setTitle(R.string.edit_email)
			.setMessage(getResources().getString(R.string.user_name) + name + "\n" + getResources().getString(R.string.user_email) + email)
			.setPositiveButton(R.string.user_email, new
			DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					LayoutInflater lay_1 = getLayoutInflater();
					final View modification_email_layout = lay_1.inflate(R.layout.dialog_modification_email, null);
					new AlertDialog.Builder(MainActivity.this)
						.setTitle(R.string.email_title)
						.setView(modification_email_layout) 
						.setPositiveButton(R.string.dia_yes, new
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
									Toast.makeText(MainActivity.this, R.string.is_null, Toast.LENGTH_SHORT).show();
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
														Toast.makeText(MainActivity.this, R.string.edit_true, Toast.LENGTH_SHORT).show();
														u.logOut();
														open();
													}
													else
													{
														Toast.makeText(MainActivity.this, R.string.edit_false + e.getMessage(), Toast.LENGTH_SHORT).show();
													}
												}

											});
									}
									else
									{
										Toast.makeText(MainActivity.this, R.string.email_false, Toast.LENGTH_SHORT).show();
									}
								}
							}
						}
					)					
						.setNegativeButton(R.string.dia_cancel, null)
						.show();
				}
			}
		)
			.setNegativeButton(R.string.edit_password, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					LayoutInflater lay_2 = getLayoutInflater();
					final View modification_password_layout = lay_2.inflate(R.layout.dialog_modification_password, null);
					new AlertDialog.Builder(MainActivity.this)
						.setTitle(R.string.password_title)
						.setView(modification_password_layout) 
						.setPositiveButton(R.string.dia_yes, new
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
									Toast.makeText(MainActivity.this, R.string.is_null, Toast.LENGTH_SHORT).show();
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
														Toast.makeText(MainActivity.this, R.string.edit_true, Toast.LENGTH_SHORT).show();
														u.logOut();
														open();
													}
													else
													{
														Toast.makeText(MainActivity.this, R.string.edit_false + e.getMessage(), Toast.LENGTH_SHORT).show();
													}
												}
											});
									}
									else
									{
										Toast.makeText(MainActivity.this, R.string.password_false, Toast.LENGTH_SHORT).show();
									}
								}
							}
						}
					)					
						.setNegativeButton(R.string.dia_cancel, null)
						.show();
				}
			}
		)
			.setNeutralButton(R.string.user_logout, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					u.logOut();
					Toast.makeText(MainActivity.this, R.string.logout_true, Toast.LENGTH_SHORT).show();
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
				SharedPreferences c=getSharedPreferences("customtheme", 0);
				final int itemSelected=c.getInt("id", 0);
				AlertDialog.Builder theme = new AlertDialog.Builder(MainActivity.this);
				theme.setTitle(R.string.theme_title);
				Integer[] res = new Integer[]{
					R.drawable.buletheme,
					R.drawable.redtheme,
					R.drawable.purpletheme,
					R.drawable.lindigotheme,
					R.drawable.tealtheme,
					R.drawable.greentheme,
					R.drawable.orangetheme,
					R.drawable.browntheme,
					R.drawable.bluegreytheme,
					R.drawable.yellowtheme,
					R.drawable.kirbytheme,
					R.drawable.darktheme
				};
				List<Integer> list = Arrays.asList(res);
				ColorListAdapter adapter = new ColorListAdapter(MainActivity.this, list);
				adapter.setCheckItem(itemSelected);
				GridView gridView = (GridView) LayoutInflater.from(MainActivity.this).inflate(R.layout.colors_panel_layout, null);
				gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
				gridView.setCacheColorHint(0);
				gridView.setAdapter(adapter);
				theme.setView(gridView);
				final AlertDialog dialog = theme.show();
				gridView.setOnItemClickListener(
					new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id)
						{
							dialog.dismiss();
							if (itemSelected != position)
							{
								setCustomTheme(position);
							}
						}
					}

				);
				break;
			case R.id.about:
				//跳转AboutActivity
				Intent about=new Intent(MainActivity.this, AboutActivity.class);
				startActivity(about);
				break;
			case R.id.app:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(R.string.tj_app);
				String[] items={"ZArchiver\n" + getResources().getString(R.string.app_ZArchiver)};
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
			case R.id.Download:
				TrPay.getInstance(this).callAlipay(tradename, outtradeno, 50L, backparams, notifyurl, userid, new PayResultListener() {
						@Override
						public void onPayFinish(Context context, String outtradeno, int resultCode, String resultString, int payType, Long amount, String tradename)
						{
							if (resultCode == TrPayResult.RESULT_CODE_SUCC.getId())
							{//1：支付成功回调
								TrPay.getInstance((Activity) context).closePayView();//关闭快捷支付页面
								Toast.makeText(MainActivity.this, resultString, Toast.LENGTH_LONG).show();
								//支付成功逻辑处理
							}
							else if (resultCode == TrPayResult.RESULT_CODE_FAIL.getId())
							{//2：支付失败回调
								Toast.makeText(MainActivity.this, resultString, Toast.LENGTH_LONG).show();
								//支付失败逻辑处理
							}
						}
					});
				break;
			default:
		}
		return true;	
	}
	public void downloadappApk(final String app_name)
	{
		progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setMessage(getResources().getString(R.string.link_bmob));
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
						Toast.makeText(MainActivity.this, getResources().getString(R.string.link_fail) + p2, Toast.LENGTH_SHORT).show();
					}
				}
			});
	}
	private void appFileDownload(BmobFile moniqiApk, final String app_name)
	{
		File saveFile = new File("/storage/emulated/0/Android/data/com.kirby.runanjing/files/" + moniqiApk.getFilename());
		moniqiApk.download(saveFile, new DownloadFileListener() {
				@Override
				public void onStart()
				{
					progressDialog.setMessage(getResources().getString(R.string.downloading) + app_name);
				}
				@Override
				public void done(String savePath, BmobException e)
				{
					if (e == null)
					{
						progressDialog.dismiss();
						Toast.makeText(MainActivity.this, getResources().getString(R.string.download_susses) + savePath, Toast.LENGTH_SHORT).show();
						File file=new File(savePath);
						installAppApk(file);
					}
					else
					{
						progressDialog.dismiss();
						Toast.makeText(MainActivity.this, getResources().getString(R.string.download_fail) + e.getMessage() , Toast.LENGTH_SHORT).show();
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
				showDownloadDialog(game_name, R.string.game_name, R.string.jp, R.string.us, R.string.zh, "https://eyun.baidu.com/s/3kURIBIZ", "https://eyun.baidu.com/s/3o86TXDS", "https://eyun.baidu.com/s/3dF22BWP");
				break;
			case "星之卡比 镜之大迷宫":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.zh, "https://eyun.baidu.com/s/3hs7Mjsg", "https://eyun.baidu.com/s/3c5qBl8", "https://eyun.baidu.com/s/3i5t6Z3J");
				break;
			case "星之卡比 3":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3pKTD8EZ", "https://eyun.baidu.com/s/3gfwui2n", "");
				break;
			case "星之卡比 超豪华版":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3qXEc4Xm", "https://eyun.baidu.com/s/3nu8IVpv", "");
				break;
			case "星之卡比 卡比梦幻都":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3hsvCjfI", "https://eyun.baidu.com/s/3jHCmNps", "");
				break;
			case "[仅美国]星之卡比 卡比魔方气泡":
				showDownloadDialog(game_name, R.string.game_name1, R.string.us , R.string.nu ,  R.string.nu, "https://eyun.baidu.com/s/3eSuusSi", "", "");
				break;
			case "[仅日本]星之卡比 卡比宝石星DX":
				showDownloadDialog(game_name, R.string.game_name2, R.string.jp, R.string.nu, R.string.nu, "https://eyun.baidu.com/s/3kVDhaS3", "", "");
				break;
			case "星之卡比 64":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3jHPKdMY", "https://eyun.baidu.com/s/3jHPKdMY", "");
				break;
			case "星之卡比 飞天赛车":
				showDownloadDialog(game_name,  R.string.game_name, R.string.us, R.string.nu, R.string.nu, "https://eyun.baidu.com/s/3qYAoXGC", "", "");
				break;
			case "星之卡比 重返梦幻岛":
				//showDownloadDialog(game_name, "[注意有单/多文件之分]\n请选择你需要的版本", "日版（单）", "美版（多）", "汉化（多）", "https://eyun.baidu.com/s/3skEbla1", "https://eyun.baidu.com/s/3gf5Oxe7", "https://eyun.baidu.com/s/3gfqpuin");
				break;
			case "星之卡比 毛线卡比":
				//showDownloadDialog(game_name, "[注意有单/多文件之分]\n请选择你需要的版本", "美版（多）", "美版（单）", "汉化（单）", "https://eyun.baidu.com/s/3c2inVEC", "https://eyun.baidu.com/s/3dFACfWd", "https://eyun.baidu.com/s/3eRYayD8");
				break;
			case "星之卡比 触摸卡比":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.zh, "https://eyun.baidu.com/s/3hsqS3S4", "https://eyun.baidu.com/s/3c27V89i", "https://eyun.baidu.com/s/3i5Pwsxn");
				break;
			case "星之卡比 超究豪华版":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.zh, "https://eyun.baidu.com/s/3i4Ricbb", "https://eyun.baidu.com/s/3nvCwXlB", "https://eyun.baidu.com/s/3c2EblZi");
				break;
			case "星之卡比 呐喊团":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.zh, "https://eyun.baidu.com/s/3bo4Z5TH", "https://eyun.baidu.com/s/3czmilC", "https://eyun.baidu.com/s/3hr4PxbA");
				break;
			case "星之卡比 集合！卡比":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.zh, "https://eyun.baidu.com/s/3geO4mbx", "https://eyun.baidu.com/s/3eSijdHS", "https://eyun.baidu.com/s/3o80PA6e");
				break;
			case "星之卡比 1":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3pKN6dIz", "https://eyun.baidu.com/s/3pKZHpaF", "");
				break;
			case "星之卡比 2":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3i57Kjjv", "https://eyun.baidu.com/s/3jI4urlW", "");
				break;
			case "星之卡比 卡比宝石星":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3miFgbtI", "https://eyun.baidu.com/s/3nvtzunn", "");
				break;
			case "星之卡比 卡比打砖块":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3i5Dkqah", "https://eyun.baidu.com/s/3ge7808r", "");
				break;
			case "星之卡比 卡比弹珠台":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3i48QqMh", "https://eyun.baidu.com/s/3eSwv1DK", "");
				break;
			case "星之卡比 滚滚卡比":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3pKP9eav", "https://eyun.baidu.com/s/3nuQZavJ", "");
				break;
			case "星之卡比 梦之泉物语":
				showDownloadDialog(game_name,  R.string.game_name, R.string.jp, R.string.us, R.string.nu, "https://eyun.baidu.com/s/3pKXFx8n", "https://eyun.baidu.com/s/3pKZHpaF", "https://eyun.baidu.com/s/3i4HC8FN");
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
		progressDialog.setMessage(gameContext.getString(R.string.link_bmob));
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
						Toast.makeText(gameContext, gameContext.getString(R.string.link_fail) + p2, Toast.LENGTH_SHORT).show();
					}
				}
			});
	}
	private void moniqiFileDownload(BmobFile moniqiApk, final String game_name)
	{
		File saveFile = new File("/storage/emulated/0/Android/data/com.kirby.runanjing/files/" + moniqiApk.getFilename());
		moniqiApk.download(saveFile, new DownloadFileListener() {
				@Override
				public void onStart()
				{
					progressDialog.setMessage(gameContext.getString(R.string.downloading) + game_name);
				}
				@Override
				public void done(String savePath, BmobException e)
				{
					if (e == null)
					{
						progressDialog.dismiss();
						Toast.makeText(gameContext, gameContext.getString(R.string.download_susses) + savePath, Toast.LENGTH_SHORT).show();
						File file=new File(savePath);
						installMoniqiApk(file);
					}
					else
					{
						progressDialog.dismiss();
						Toast.makeText(gameContext, gameContext.getString(R.string.download_fail) + e.getMessage() , Toast.LENGTH_SHORT).show();
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
			.setMessage(R.string.download_dia_mess)
			.setPositiveButton(R.string.dia_download, new
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
	public void showDownloadDialog(String name, int mess, Integer pos, Integer neg, Integer neu, final String pos_url, final String neg_url, final String neu_url)
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
					/*Intent kirby_web=new Intent(gameContext,KirbyWebActivity.class);
					 kirby_web.putExtra("url",pos_url);
					 gameContext.startActivity(kirby_web);*/
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

