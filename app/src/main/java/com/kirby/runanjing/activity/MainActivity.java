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
		toolbar.setSubtitle("游戏");
		init();
		final RecyclerView r=(RecyclerView)findViewById(R.id.主机列表);
		GridLayoutManager layoutManager=new GridLayoutManager(this, 1);
		r.setLayoutManager(layoutManager);
		adapter = new ConsoleAdapter(consolelist);
		r.setAdapter(adapter);	
	}
	private void init()
	{
		int index = 0;
		while (index < 主机.length)
		{       	
			consolelist.add(主机[index++]);
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
}

