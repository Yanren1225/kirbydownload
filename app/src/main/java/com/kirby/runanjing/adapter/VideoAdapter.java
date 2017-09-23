package com.kirby.runanjing.adapter;


import android.content.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.adapter.*;
import com.kirby.runanjing.untils.*;
import java.util.*;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>
{
	private Context mContext;
	private List<Video> mVideolist;

	static class ViewHolder extends RecyclerView.ViewHolder
	{
		CardView cardview;
		TextView 用户名;
		public ViewHolder(View view)
		{
			super(view);
			cardview = (CardView)view.findViewById(R.id.cardview);
			用户名 = (TextView)view.findViewById(R.id.用户名);
		}
	}
	public VideoAdapter(List<Video>videolist)
	{
		mVideolist = videolist;
	}
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		if (mContext == null)
		{
			mContext = parent.getContext();
		}
		View view=LayoutInflater.from(mContext).inflate(R.layout.message_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		Video mess=mVideolist.get(position);
		holder.用户名.setText(mess.getName());
	}

	@Override
	public int getItemCount()
	{
		return mVideolist.size();
	}
}
