package com.kirby.runanjing.activity;

import android.content.*;
import android.net.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.bumptech.glide.*;
import com.kirby.runanjing.*;

import android.support.v7.widget.Toolbar;

public class GameActivity extends AppCompatActivity
{
	public static final String GAME_NAME = "game_name";
    public static final String GAME_IMAGE_ID = "game_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        Intent intent = getIntent();
        String GameName = intent.getStringExtra(GAME_NAME);
        int GameImageId = intent.getIntExtra(GAME_IMAGE_ID, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.gameactivitylayoutCollapsingToolbarLayout);
        ImageView ImageView = (ImageView) findViewById(R.id.gameactivitylayoutImageView);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
		{
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
		RelativeLayout 金手指=(RelativeLayout)findViewById(R.id.金手指);
		金手指.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					Intent jsz_intent=new Intent(GameActivity.this, JszActivity.class);
					startActivity(jsz_intent);
				}
			}
		);
        collapsingToolbar.setTitle(GameName);
        Glide.with(this).load(GameImageId).into(ImageView);
		SharedPreferences console=getSharedPreferences("string", Context.MODE_WORLD_READABLE);
		final String game= console.getString("游戏或模拟器名称", "");
		FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.download);
		if (game == "星之卡比 梦之泉DX")
		{	
			金手指.setVisibility(View.VISIBLE);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3kURIBIZ");   
									web.setData(content_url);  
									startActivity(web);
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3o86TXDS");   
									web.setData(content_url);  
									startActivity(web);  							
								}
							}
						)
							.setNeutralButton("汉化", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3dF22BWP");   
									web.setData(content_url);  
									startActivity(web);  			
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 镜之大迷宫")
		{
			金手指.setVisibility(View.VISIBLE);
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3hs7Mjsg");   
									web.setData(content_url);  
									startActivity(web);  		
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3c5qBl8");   
									web.setData(content_url);  
									startActivity(web);  		
								}
							}
						)
							.setNeutralButton("汉化", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3i5t6Z3J");   
									web.setData(content_url);  
									startActivity(web);  
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 3")
		{
			金手指.setVisibility(View.VISIBLE);
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3pKTD8EZ");   
									web.setData(content_url);  
									startActivity(web);  				
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3gfwui2n");   
									web.setData(content_url);  
									startActivity(web);  					
								}
							}				
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 超豪华版")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3qXEc4Xm");   
									web.setData(content_url);  
									startActivity(web); 					
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3nu8IVpv");   
									web.setData(content_url);  
									startActivity(web);  							
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 卡比梦幻都")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3hsvCjfI");   
									web.setData(content_url);  
									startActivity(web);  							
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3jHCmNps");   
									web.setData(content_url);  
									startActivity(web);  							
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "[仅美国]星之卡比 卡比魔方气泡")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("这TM是米国限定的，你说选啥")
							.setPositiveButton("美版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3eSuusSi");   
									web.setData(content_url);  
									startActivity(web);
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "[仅日本]星之卡比 卡比宝石星DX")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("这TM是十一区限定的，你说选啥")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3kVDhaS3");   
									web.setData(content_url);  
									startActivity(web); 
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 64")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3jHPKdMY");   
									web.setData(content_url);  
									startActivity(web); 			
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3jHPKdMY");   
									web.setData(content_url);  
									startActivity(web);  							
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 飞天赛车")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("蛤！咱这里只找到了美版")
							.setPositiveButton("美版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3qYAoXGC");   
									web.setData(content_url);  
									startActivity(web); 					
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 重返梦幻岛")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本（均为wbfs文件,注意有单/多文件之分)")
							.setPositiveButton("美版(单)", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3skEbla1");   
									web.setData(content_url);  
									startActivity(web);  							
								}
							}
						)
							.setNegativeButton("美版(多)", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3gf5Oxe7");   
									web.setData(content_url);  
									startActivity(web);  						
								}
							}
						)
							.setNeutralButton("汉化(多)", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3gfqpuin");   
									web.setData(content_url);  
									startActivity(web); 				
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 毛线卡比")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本(均为wbfs文件，注意有单/多文件之分")
							.setPositiveButton("美版(多)", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3c2inVEC");   
									web.setData(content_url);  
									startActivity(web); 
								}
							}
						)
							.setNegativeButton("美版(单)", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3dFACfWd");   
									web.setData(content_url);  
									startActivity(web); 				
								}
							}
						)
							.setNeutralButton("汉化(单)", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3eRYayD8");   
									web.setData(content_url);  
									startActivity(web);  
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 触摸卡比")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3hsqS3S4");   
									web.setData(content_url);  
									startActivity(web);  				
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3c27V89i");   
									web.setData(content_url);  
									startActivity(web);  		
								}
							}
						)
							.setNeutralButton("汉化", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3i5Pwsxn");   
									web.setData(content_url);  
									startActivity(web);  
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 超究豪华版")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3i4Ricbb");   
									web.setData(content_url);  
									startActivity(web);  						
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3nvCwXlB");   
									web.setData(content_url);  
									startActivity(web);  		
								}
							}
						)
							.setNeutralButton("汉化", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3c2EblZi");   
									web.setData(content_url);  
									startActivity(web);				
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 呐喊团")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3bo4Z5TH");   
									web.setData(content_url);  
									startActivity(web); 						
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3czmilC");   
									web.setData(content_url);  
									startActivity(web);  					
								}
							}
						)
							.setNeutralButton("汉化", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3hr4PxbA");   
									web.setData(content_url);  
									startActivity(web);  				
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 集合！卡比")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3geO4mbx");   
									web.setData(content_url);  
									startActivity(web);  								
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3eSijdHS");   
									web.setData(content_url);  
									startActivity(web);  					
								}
							}
						)
							.setNeutralButton("汉化", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3o80PA6e");   
									web.setData(content_url);  
									startActivity(web);  					
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 1")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3pKN6dIz");   
									web.setData(content_url);  
									startActivity(web); 						
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3pKZHpaF");   
									web.setData(content_url);  
									startActivity(web);  						
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 2")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3i57Kjjv");   
									web.setData(content_url);  
									startActivity(web); 			
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3jI4urlW");   
									web.setData(content_url);  
									startActivity(web);				
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 卡比宝石星")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3miFgbtI");   
									web.setData(content_url);  
									startActivity(web);  
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3nvtzunn");   
									web.setData(content_url);  
									startActivity(web);  			
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 卡比打砖块")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3i5Dkqah");   
									web.setData(content_url);  
									startActivity(web); 		
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3ge7808r");   
									web.setData(content_url);  
									startActivity(web); 
						    	}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 卡比弹珠台")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3i48QqMh");   
									web.setData(content_url);  
									startActivity(web); 			
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3eSwv1DK");   
									web.setData(content_url);  
									startActivity(web); 
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 滚滚卡比")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3pKP9eav");   
									web.setData(content_url);  
									startActivity(web); 	
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3nuQZavJ");   
									web.setData(content_url);  
									startActivity(web); 				
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "星之卡比 梦之泉物语")
		{
			金手指.setVisibility(View.VISIBLE);
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("请选择你需要的版本")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3pKXFx8n");   
									web.setData(content_url);  
									startActivity(web);				
								}
							}
						)
							.setNegativeButton("美版", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3pKZHpaF");   
									web.setData(content_url);  
									startActivity(web);  	
								}
							}
						)
							.setNeutralButton("汉化", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3i4HC8FN");   
									web.setData(content_url);  
									startActivity(web);  				
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "GBA模拟器\nMy Boy!")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("咱这里只给你汉化版(o｀ε´o)")
							.setPositiveButton("汉化", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3jIDXu9w");   
									web.setData(content_url);  
									startActivity(web); 
								}
							}
						);		
						dialog.show();
					}
				}
			);
		}
		if (game == "SFC模拟器\nSnes9x EX+")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("咱这里只给你汉化版(o｀ε´o)")
							.setPositiveButton("汉化", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3o87d9cI");   
									web.setData(content_url);  
									startActivity(web); 
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "N64模拟器\nTendo64")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("咱这里只给你汉化版(o｀ε´o)")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3jIijslK");   
									web.setData(content_url);  
									startActivity(web);
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "NDS模拟器\nDraStic")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("咱这里只给你汉化版(o｀ε´o)")
							.setPositiveButton("日版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3i5knqk9");   
									web.setData(content_url);  
									startActivity(web); 
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "NGC&WII模拟器\nDolphin")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("这东西我推荐去官网下载，不过，我还是给你一个吧o(´^｀)o")
							.setPositiveButton("英文版", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3pL9d02R");   
									web.setData(content_url);  
									startActivity(web);
								}
							}
						)
							.setNegativeButton("官网(推荐)", new DialogInterface.OnClickListener()
							{
								@Override

								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://cn.dolphin-emu.org/download/");   
									web.setData(content_url);  
									startActivity(web);  	
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "GB&GBC模拟器\nMy OldBoy!")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("咱这里只给你汉化版(o｀ε´o)")
							.setPositiveButton("汉化", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3nvA34PN");   
									web.setData(content_url);  
									startActivity(web);
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
		if (game == "FC模拟器\nNES.emu")
		{
			toolbar.setTitle(game);
			fab.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						AlertDialog.Builder dialog = new
							AlertDialog.Builder(GameActivity.this)
							.setTitle(game)
							.setMessage("咱这里只给你汉化版(o｀ε´o)")
							.setPositiveButton("汉化", new
							DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									Intent web = new Intent();        
									web.setAction("android.intent.action.VIEW");    
									Uri content_url = Uri.parse("https://eyun.baidu.com/s/3jIFZrhG");   
									web.setData(content_url);  
									startActivity(web);
								}
							}
						);
						dialog.show();
					}
				}
			);
		}
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
	{
        switch (item.getItemId())
		{
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
