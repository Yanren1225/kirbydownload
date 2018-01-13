package com.kirby.runanjing.bmob;
import cn.bmob.v3.*;

public class BmobVideo extends BmobObject
{
    private String name;
	private String imageUrl;
	private String av;
    public String getName()
	{
        return name;
    }
    public void setName(String name)
	{
        this.name = name;
    }
	public String getImageUrl()
	{
        return imageUrl;
    }
    public void setImageUrl(String imageUrl)
	{
        this.imageUrl = imageUrl;
    }
	public String getAv()
	{
        return av;
    }
    public void setAv(String av)
	{
        this.av = av;
    }
}
