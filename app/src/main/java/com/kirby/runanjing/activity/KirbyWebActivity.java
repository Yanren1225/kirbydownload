package com.kirby.runanjing.activity;


import android.content.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import com.github.anzewei.parallaxbacklayout.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.untils.*;

import android.support.v7.widget.Toolbar;
import android.webkit.DownloadListener;
import com.kirby.runanjing.R;
import android.net.*;

@ParallaxBack
public class KirbyWebActivity extends BaseActivity
{
	private String url;

	private Toolbar toolbar;

	private WebView mWebView;

	private ProgressBar mProgressBar;

	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Theme.setClassTheme(this);
		setContentView(R.layout.activity_web);
		Intent kirby_web=getIntent();
	    url = kirby_web.getStringExtra("url");
		//配置toolbar
		toolbar = (Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		toolbar.setTitle(R.string.web_loading);
		toolbar.setSubtitle(url);
		mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
		mWebView=(WebView)findViewById(R.id.web_main);
		WebSettings settings = mWebView.getSettings();
// 设置是够支持js脚本
		settings.setJavaScriptEnabled(true);
		settings.setUseWideViewPort(true);
		settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		settings.setLoadWithOverviewMode(true);
		mWebView.setWebViewClient(new WebViewClient(){
				public boolean shouldOverrideUrlLoading(WebView view, String url) { 
					view.loadUrl(url);
					return true;
				}
		});
		mWebView.setDownloadListener(new DownloadListener() {
				@Override
				public void onDownloadStart(String dlurl, String userAgent, String contentDisposition,
											String mimetype, long contentLength) {
												Toast.makeText(KirbyWebActivity.this,dlurl,Toast.LENGTH_SHORT).show();
					Intent startApp=new Intent(Intent.ACTION_VIEW);
					startApp.setDataAndType(Uri.parse(dlurl),"void/*");
					startActivity(startApp);
				}

				});
				mWebView.setWebChromeClient(new WebChromeClient() {
				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					super.onProgressChanged(view, newProgress);
					if (newProgress != 100) {
						mProgressBar.setProgress(newProgress);
					} else {
						mProgressBar.setVisibility(View.GONE);
					}
				}
				@Override
				public void onReceivedTitle(WebView view, String title) {
					super.onReceivedTitle(view, title);
					toolbar.setTitle(title);
				}
			});
			mWebView.loadUrl(url);
	}
}
