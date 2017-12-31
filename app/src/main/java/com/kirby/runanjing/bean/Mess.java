package com.kirby.runanjing.bean;
import java.net.*;

public class Mess
{
	private String name;
	private String message;
	private String time;
	private String message_full;
	public Mess(String name,  String message, String time,String message_full)
	{
		this.name = name;
		this.time = time;
		this.message = message;
		this.message_full=message_full;
	}
	public String getName()
	{
		return name;
	}
	public String getTime()
	{
		return time;
	}
	public String getMessage()
	{
		return message;
	}
	public String getFullMessage()
	{
		return message_full;
	}
}
