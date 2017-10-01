package com.kirby.runanjing.activity;

import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import cn.bmob.v3.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.adapter.*;
import com.kirby.runanjing.untils.*;
import java.util.*;

import android.support.v7.widget.Toolbar;
import com.kirby.runanjing.R;
import android.content.pm.*;
import android.support.v4.widget.*;
import com.kirby.runanjing.fragment.*;
import android.support.v4.app.*;
public class MainActivity extends AppCompatActivity
{
	private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private View view1, view2 ,view3;//页卡视图
    private List<View> mViewList = new ArrayList<>();//页卡视图集合
	private Console[]主机={
		new Console("gba", R.drawable.gba),
		new Console("sfc", R.drawable.sfc),
		new Console("n64", R.drawable.n64),
		new Console("ngc", R.drawable.ngc),
		new Console("wii", R.drawable.wii),
		new Console("nds", R.drawable.nds),
		new Console("gb/gbc", R.drawable.gbc),
		new Console("fc", R.drawable.fc)};
	private List<Console> consolelist=new ArrayList<>();
	private List<Console> moniqilist=new ArrayList<>();
	private List<Video> videolist=new ArrayList<>();
	private ConsoleAdapter adapter;
	//给模拟器准备的
	private Console[] 模拟器 = {
		new Console("GBA模拟器\nMy Boy!", R.drawable.moniqi_gba),
		new Console("SFC模拟器\nSnes9x EX+", R.drawable.moniqi_sfc),
		new Console("N64模拟器\nTendo64", R.drawable.moniqi_n64),
		new Console("NDS模拟器\nDraStic", R.drawable.moniqi_nds),
		new Console("NGC&WII模拟器\nDolphin", R.drawable.moniqi_wii),
		new Console("GB&GBC模拟器\nMy OldBoy!", R.drawable.moniqi_gb_gbc),
		new Console("FC模拟器\nNES.emu", R.drawable.moniqi_fc),
	}; 
	private GameAdapter adapter2;
	private VideoAdapter adapter3;
	private long firstTime;
	private NavigationView navView;
	private DrawerLayout drawerLayout;
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
		final Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		replaceFragment(new MainGameFragment());
		getUser();//验证账户
		//侧滑
		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_main);
		navView = (NavigationView)findViewById(R.id.nav_view);
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
							break;
						case R.id.mess:
							MyUser u = BmobUser.getCurrentUser(MyUser.class);//检验用户数据是否存在
							if (null == u)
							{
								//不存在
								Toast.makeText(MainActivity.this, "没有登录信息", Toast.LENGTH_LONG).show();
								Intent user=new Intent(MainActivity.this, LoginActivity.class);
								startActivity(user);
							}
							else
							{
								//存在
								Intent me=new Intent(MainActivity.this, MessageActivity.class);
								startActivity(me);
							}					
							break;
						case R.id.video:
							break;
						case R.id.tech:
							break;
					}
					return true;
				}
			});
	}
	private void replaceFragment(Fragment fragment)
	{
		FragmentManager fragmentManager=getSupportFragmentManager();
		FragmentTransaction transaction=fragmentManager.beginTransaction();
		transaction.replace(R.id.main_fragment, fragment);
		transaction.commit();
	}
	private void getUser()
	{
		LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		View nav_view=inflater.inflate(R.layout.nav_header, null);
		MyUser u = BmobUser.getCurrentUser(MyUser.class);//检验用户数据是否存在
		if (null == u)
		{
			RelativeLayout ok=(RelativeLayout)nav_view.findViewById(R.id.ok);
			ok.setVisibility(View.GONE);
		}
		else
		{
			Toast.makeText(MainActivity.this, u.getUsername(), Toast.LENGTH_SHORT).show();
			RelativeLayout notok=(RelativeLayout)nav_view.findViewById(R.id.notok);
			TextView user_name=(TextView)nav_view.findViewById(R.id.user_name);
			notok.setVisibility(View.GONE);
			user_name.setText(u.getUsername());
		}
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
	//双击
	@Override
    public void onBackPressed()
	{
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000)
		{
			Snackbar.make(mTabLayout, "再按一次退出程序", Snackbar.LENGTH_SHORT).show();
			firstTime = secondTime;
        }
		else
		{
            System.exit(0);
        }
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
				String[] singleChoiceItems = {"知乎蓝","姨妈红","基佬紫","颐堤蓝","水鸭青","酷安绿","伊藤橙","古铜棕","低调灰"};
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
			default:
		}
		return true;
	}
	
}

