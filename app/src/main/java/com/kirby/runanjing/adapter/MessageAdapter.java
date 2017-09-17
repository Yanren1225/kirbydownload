package com.kirby.runanjing.adapter;
import android.widget.*;
import com.kirby.runanjing.untils.*;
import android.content.*;
import java.util.*;
import android.view.*;
import com.kirby.runanjing.*;
import android.support.v7.widget.*;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>
{
	private Context mContext;
	private List<Mess> mMesslist;

	static class ViewHolder extends RecyclerView.ViewHolder
	{
		CardView cardview;
		TextView 用户名;
		TextView 内容;
		TextView 时间;

		public ViewHolder(View view)
		{
			super(view);
			cardview = (CardView)view.findViewById(R.id.cardview);
			用户名 = (TextView)view.findViewById(R.id.用户名);
			内容 = (TextView)view.findViewById(R.id.内容);
			时间 = (TextView)view.findViewById(R.id.时间);
		}
	}
	public MessageAdapter(List<Mess>messlist)
	{
		mMesslist = messlist;
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
		Mess mess=mMesslist.get(position);
		holder.用户名.setText(mess.getName());
		holder.内容.setText(mess.getMessage());
		holder.时间.setText(mess.getTime());
	}

	@Override
	public int getItemCount()
	{
		return mMesslist.size();
	}


}
