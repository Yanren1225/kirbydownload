package com.kirby.runanjing.adapter;
import android.content.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.bumptech.glide.*;
import com.kirby.runanjing.*;
import com.kirby.runanjing.activity.*;
import java.util.*;
import com.kirby.runanjing.untils.*;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>
{
    private Context mContext;
    private List<Console> mGameList;
    static class ViewHolder extends RecyclerView.ViewHolder
	{
        LinearLayout LinearLayout;
		CardView cardView;
        ImageView gameImage;
        TextView gameName;
        public ViewHolder(View view)
		{
            super(view);
			LinearLayout = (LinearLayout)view.findViewById(R.id.LinearLayout);
            cardView = (CardView) view.findViewById(R.id.cardview);
            gameImage = (ImageView) view.findViewById(R.id.console_image);
            gameName = (TextView) view.findViewById(R.id.console_text);
        }
    }
    public GameAdapter(List<Console> gamelist)
	{
        mGameList = gamelist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
        if (mContext == null)
		{
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.console_list, parent, false);
		final ViewHolder holder=new ViewHolder(view);
		holder.LinearLayout.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					int position = holder.getAdapterPosition();
					Console game = mGameList.get(position);
					Intent intent = new Intent(mContext, GameActivity.class);
					intent.putExtra(GameActivity.GAME_NAME, game.getName());
					intent.putExtra(GameActivity.GAME_IMAGE_ID, game.getImageId());
					mContext.startActivity(intent);		
					String  input=game.getName().toString();	
					SharedPreferences.Editor t=mContext.getSharedPreferences("string",0).edit();
					t.putString("游戏或模拟器名称",input);
					t.apply();
				}
			}
		);
		return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
	{
        Console fr = mGameList.get(position);
        holder.gameName.setText(fr.getName());
		Glide.with(mContext).load(fr.getImageId()).into(holder.gameImage);
    }

    @Override
    public int getItemCount()
	{
        return mGameList.size();
    }

}
