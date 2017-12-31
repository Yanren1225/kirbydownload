package com.kirby.runanjing.activity;

import android.content.*;
import android.os.*;
import android.support.v7.widget.*;
import com.jaeger.library.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.adapter.*;
import com.kirby.runanjing.untils.*;
import java.util.*;

import com.kirby.runanjing.R;
import com.kirby.runanjing.bean.*;

public class GameListActivity extends BaseActivity
{
	private List<Console> gamelist=new ArrayList<>();
	private GameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        Theme.setClassTheme(this);
		setContentView(R.layout.gamelist_layout);
		//配置toolbar
		Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		//配置列表
		RecyclerView r=(RecyclerView)findViewById(R.id.主机列表);
		GridLayoutManager layoutManager=new GridLayoutManager(this, 1);
		r.setLayoutManager(layoutManager);
		adapter = new GameAdapter(gamelist);
		r.setAdapter(adapter);
		//获取数据
		SharedPreferences console=getSharedPreferences("string", 0);
		String game= console.getString("主机名称", "");
		toolbar.setSubtitle(game);
		//判断数据然后处理列表
		if (game == "gba")
		{	
			Console[] 游戏 = {
				new Console("星之卡比 梦之泉DX", R.drawable.mengzhiquandx),
				new Console("星之卡比 镜之大迷宫", R.drawable.jingmi),			
			}; 
			int index = 0;
			while (index < 游戏.length)
			{       	
				gamelist.add(游戏[index++]);
			}
		}
		if (game == "gb/gbc")
		{
			Console []游戏={		
				new Console("星之卡比 1", R.drawable.xing1),
				new Console("星之卡比 2", R.drawable.xing2),
				new Console("星之卡比 卡比宝石星", R.drawable.baoshixing),
				new Console("星之卡比 卡比打砖块", R.drawable.dazhuankuai),
				new Console("星之卡比 卡比弹珠台", R.drawable.danzhutai),
				new Console("星之卡比 滚滚卡比", R.drawable.gungun),
			};
			int index = 0;
			while (index < 游戏.length)
			{       	
				gamelist.add(游戏[index++]);
			}
		}
		if (game == "sfc")
		{
			Console[] 游戏 = {
				new Console("星之卡比 3", R.drawable.xing3),
				new Console("星之卡比 超豪华版", R.drawable.kss),
				new Console("星之卡比 卡比梦幻都", R.drawable.menghuandu),
				new Console("[仅美国]星之卡比 卡比魔方气泡", R.drawable.mofangqipao),
				new Console("[仅日本]星之卡比 卡比宝石星DX", R.drawable.banshixingdx),
			}; 
			int index = 0;
			while (index < 游戏.length)
			{       	
				gamelist.add(游戏[index++]);
			}
		}
		if (game == "n64")
		{

			Console[] 游戏 = {
				new Console("星之卡比 64", R.drawable.k64),
			}; 
			int index = 0;
			while (index < 游戏.length)
			{       	
				gamelist.add(游戏[index++]);
			}
		}
		if (game == "ngc")
		{

			Console[] 游戏 = {
				new Console("星之卡比 飞天赛车", R.drawable.feitian),
			}; 
			int index = 0;
			while (index < 游戏.length)
			{       	
				gamelist.add(游戏[index++]);
			}
		}
		if (game == "wii")
		{

			Console[] 游戏 = {
				new Console("星之卡比 重返梦幻岛", R.drawable.chongfan),
				new Console("星之卡比 毛线卡比", R.drawable.maoxian),
			}; 
			int index = 0;
			while (index < 游戏.length)
			{       	
				gamelist.add(游戏[index++]);
			}
		}
		if (game == "nds")
		{

			Console[] 游戏 = {
				new Console("星之卡比 触摸卡比", R.drawable.chumo),
				new Console("星之卡比 超究豪华版", R.drawable.kssu),
				new Console("星之卡比 呐喊团", R.drawable.nahantuan),
				new Console("星之卡比 集合！卡比", R.drawable.jihe),
			}; 
			int index = 0;
			while (index < 游戏.length)
			{       	
				gamelist.add(游戏[index++]);
			}
		}
		if (game == "fc")
		{

			Console[] 游戏 = {
				new Console("星之卡比 梦之泉物语", R.drawable.mengzhiquan),
			}; 
			int index = 0;
			while (index < 游戏.length)
			{       	
				gamelist.add(游戏[index++]);
			}
		}
	}
}
