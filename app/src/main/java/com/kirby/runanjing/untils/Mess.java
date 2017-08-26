package com.kirby.runanjing.untils;
import java.net.*;

public class Mess
{
	private String name;
	private String title;
	private String message;
	private String time;
	public Mess(String name, String title, String message, String time)
	{
		this.name = name;
		this.title = title;
		this.time = time;
		this.message = message;
	}
	public String getName()
	{
		return name;
	}
	public String getTitle()
	{
		return title;
	}
	public String getTime()
	{
		return time;
	}
	public String getMessage()
	{
		return message;
	}
}
