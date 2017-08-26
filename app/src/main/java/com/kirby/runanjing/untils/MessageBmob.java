package com.kirby.runanjing.untils;

import cn.bmob.v3.*;

public class MessageBmob extends BmobObject
{
    private String title;
    private String message;
	private String nickname;
	
    public String getTitle()
	{
        return title;
    }
    public void setTitle(String title)
	{
        this.title = title;
    }
    public String getMessage()
	{
        return message;
    }
    public void setMessage(String message)
	{
        this.message = message;
    }
	public String getNickname()
	{
        return nickname;
    }
    public void setNickname(String nickname)
	{
        this.nickname = nickname;
    }
}
