package com.kirby.runanjing.activity;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import com.kirby.runanjing.*;
import java.util.*;
import com.kirby.runanjing.untils.*;
import com.kirby.runanjing.adapter.*;
public class MainActivity extends AppCompatActivity
{
	private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private View view1, view2, view3;//页卡视图
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
		SharedPreferences.Editor y=getSharedPreferences("string", MODE_PRIVATE).edit();
		y.putString("主机名称", "0");
		y.putString("游戏或模拟器名称", "0");
		y.apply();

		final Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		init();	
		init2();
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
		mInflater = LayoutInflater.from(this);
        view1 = mInflater.inflate(R.layout.viewpager_1, null);
        view2 = mInflater.inflate(R.layout.viewpager_2, null);
        view3 = mInflater.inflate(R.layout.viewpager_3, null);
		//添加页卡视图
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
		//添加页卡标题
        mTitleList.add("游戏");
        mTitleList.add("模拟器");
        mTitleList.add("我");
		mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
		MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
		RecyclerView r = (RecyclerView) view1.findViewById(R.id.主机列表); 
		RecyclerView r1 = (RecyclerView) view2.findViewById(R.id.模拟器列表);
		//final RecyclerView r=(RecyclerView)findViewById(R.id.主机列表);
		GridLayoutManager layoutManager=new GridLayoutManager(this, 1);
		r.setLayoutManager(layoutManager);
		adapter = new ConsoleAdapter(consolelist);
		r.setAdapter(adapter);	

		GridLayoutManager layoutManager2=new GridLayoutManager(this, 1);
		r1.setLayoutManager(layoutManager2);
		adapter2 = new GameAdapter(moniqilist);
		r1.setAdapter(adapter2);	
	}
	private void init()
	{
		int index = 0;
		while (index < 主机.length)
		{       	
			consolelist.add(主机[index++]);
		}
	}
	private void init2()
	{
		int in = 0;
		while (in < 模拟器.length)
		{       	
			moniqilist.add(模拟器[in++]);
		}
	}

	@Override
	protected void onDestroy()
	{	
		super.onDestroy();
		SharedPreferences.Editor y=getSharedPreferences("string", MODE_PRIVATE).edit();
		y.putString("主机名称", "0");
		y.putString("游戏或模拟器名称", "0");
		y.apply();
    }

	public boolean onCreateOptionsMenu(Menu menu)
	{
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.about:
				Intent intent=new Intent(MainActivity.this, about.class);
				startActivity(intent);
				break;
			default:
		}
		return true;
	}
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

