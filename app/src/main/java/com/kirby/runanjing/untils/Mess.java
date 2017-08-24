package com.kirby.runanjing.untils;
import java.net.*;

public class Mess
{
	private String name;
	private String title;
	private String message;
	private String time;
	private String id;
	public Mess(String name, String title, String message, String time, String id)
	{
		this.name = name;
		this.title = title;
		this.time = time;
		this.message = message;
		this.id = id;
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
	public String getId()
	{
		return id;
	}
}
