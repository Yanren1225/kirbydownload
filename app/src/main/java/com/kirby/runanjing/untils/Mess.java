package com.kirby.runanjing.untils;
import java.net.*;

public class Mess
{
	private String name;
	private String message;
	private String time;
	public Mess(String name,  String message, String time)
	{
		this.name = name;
		this.time = time;
		this.message = message;
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
}
