package com.kirby.runanjing.activity;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import cn.bmob.v3.*;
import cn.bmob.v3.exception.*;
import cn.bmob.v3.listener.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.adapter.*;
import com.kirby.runanjing.untils.*;
import java.util.*;

import android.support.v7.widget.Toolbar;
import android.util.*;
public class MainActivity extends AppCompatActivity
{
	private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private View view1, view2;//页卡视图
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
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		//初始化bmob
		Bmob.initialize(this, "e39c2e15ca40b358b0dcc933236c1165");
		//跳转GameListActivity要用的数据
		SharedPreferences.Editor y=getSharedPreferences("string", MODE_PRIVATE).edit();
		y.putString("主机名称", "0");
		y.putString("游戏或模拟器名称", "0");
		y.apply();
		//配置toolbar
		final Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		init();	//主机列表
		init2();//模拟器列表
		//实例化viewpager需要的东西
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
		mInflater = LayoutInflater.from(this);
        view1 = mInflater.inflate(R.layout.viewpager_1, null);
        view2 = mInflater.inflate(R.layout.viewpager_2, null);
		//添加页卡视图
        mViewList.add(view1);
        mViewList.add(view2);
		//添加页卡标题
        mTitleList.add("游戏");
        mTitleList.add("模拟器");
		mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
		MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
		//主机列表和模拟器列表需要的
		RecyclerView r = (RecyclerView) view1.findViewById(R.id.主机列表); 
		RecyclerView r1 = (RecyclerView) view2.findViewById(R.id.模拟器列表);
		//主机列表配置
		GridLayoutManager layoutManager=new GridLayoutManager(this, 1);
		r.setLayoutManager(layoutManager);
		adapter = new ConsoleAdapter(consolelist);
		r.setAdapter(adapter);
		//模拟器列表配置
		GridLayoutManager layoutManager2=new GridLayoutManager(this, 1);
		r1.setLayoutManager(layoutManager2);
		adapter2 = new GameAdapter(moniqilist);
		r1.setAdapter(adapter2);	
	}
	private void init()
	{
		int index = 0;//定义数值
		//遍历
		while (index < 主机.length)
		{       	
			consolelist.add(主机[index++]);
		}
	}
	private void init2()
	{
		int in = 0;//定义数值
		//遍历
		while (in < 模拟器.length)
		{       	
			moniqilist.add(模拟器[in++]);
		}
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
			case R.id.login:
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
			case R.id.about:
				//跳转AboutActivity
				Intent about=new Intent(MainActivity.this, AboutActivity.class);
				startActivity(about);
				break;
			default:
		}
		return true;
	}
	//viewpager适配器
	class MyPagerAdapter extends PagerAdapter
	{
        private List<View> mViewList;

        public MyPagerAdapter(List<View> mViewList)
		{
            this.mViewList = mViewList;
        }

        @Override
        public int getCount()
		{
            return mViewList.size();//页卡数
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
		{
            return view == object;//官方推荐写法
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
		{
            container.addView(mViewList.get(position));//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
		{
            container.removeView(mViewList.get(position));//删除页卡
        }

        @Override
        public CharSequence getPageTitle(int position)
		{
            return mTitleList.get(position);//页卡标题
        }

    }
}

