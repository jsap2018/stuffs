import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


import org.client.Client;

public class Reflection {

	protected static org.client.Client c = null;
	private static Method[] methods = null;
	private static Field[] fields = null;
	private static org.a.e player = null;
	private static org.f.b stream = null;
	private static org.j.b.e idk = null;
	
	protected static boolean controlToggle = false;
		
	public static void main(String[] args) {
		try 
		{
			Class<org.client.Client> clazz = (Class<org.client.Client>) Client.class;
			methods = clazz.getDeclaredMethods();
			fields = clazz.getDeclaredFields();
			
			for (int i = 0; i < fields.length; i++) 
			{
				fields[i].setAccessible(true);
			}
			for (int i = 0; i < methods.length; i++) 
			{
				methods[i].setAccessible(true);
			}
			
			Client.main(new String[] { "a", "a" });
			
			Thread.sleep(5000);
			Reflection.hookCustomKeys();

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	protected static void hookCustomKeys()
	{
		try 
		{
			preCommand();
			c.ad.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) 
				{
					switch (arg0.getKeyCode()) 
					{
					case KeyEvent.VK_CONTROL:
						try 
						{
							controlToggle = !controlToggle;
							if(controlToggle)
							{
								bank();
								new BankThread().start();
							}
							else if(!controlToggle)
							{
								closeInterfaces();
							}
						} 
						catch (IllegalArgumentException | IllegalAccessException e) 
						{
							e.printStackTrace();
						}
						break;
					}
				}		
				@Override
				public void keyReleased(KeyEvent arg0) {}
				@Override
				public void keyTyped(KeyEvent arg0) {}			
			});
		} 
		catch (IllegalArgumentException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected static int getCurrentInterface()
	{		
		try 
		{
			preCommand();

			Field currentInterface = idk.getClass().getSuperclass().getDeclaredField("a");

			currentInterface.setAccessible(true);

			return (Integer) currentInterface.get(idk);
		} 
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) 
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	protected static void bank() throws IllegalArgumentException, IllegalAccessException
	{
		preCommand();
		
		int x = getField("eh").getInt(c) + (player.ab - 6 >> 7);
		int y = getField("ei").getInt(c) + (player.ac - 6 >> 7);

		stream.a(132);
		stream.m(x);
		stream.j(2213);
		stream.k(y);
	}
	
	protected static void closeInterfaces() throws IllegalArgumentException, IllegalAccessException
	{
		preCommand();
		
		try 
		{
			Field currentInterface = idk.getClass().getSuperclass().getDeclaredField("a");
			currentInterface.setAccessible(true);
			currentInterface.set(idk, -1);
			
			Field currentInvInterface = getField("gD");
			currentInvInterface.set(c, -1);
		}
		catch (NoSuchFieldException | SecurityException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void preCommand() throws IllegalArgumentException, IllegalAccessException
	{
		if (c == null) 
		{
			c = org.c.e.b;
		}
		if (stream == null) 
		{
			stream = (org.f.b) getField("gG").get(c);
		}
		if(idk == null)
		{
			idk =  (org.j.b.e) getField("jc").get(c);
		}
		if (player == null) 
		{
			player = (org.a.e) getField("d").get(c);
		}
	}
	
	public static Field getField(String s) 
	{
		for (int i = 0; i < fields.length; i++) 
		{
			if (fields[i].getName().equals(s)) 
			{
				return fields[i];
			}
		}
		return null;
	}

	public static Method getMethod(String s) 
	{
		for (int i = 0; i < methods.length; i++) 
		{
			if (methods[i].getName().equals(s)) 
			{
				return methods[i];
			}
		}
		return null;
	}
}

class BankThread extends Thread
{
	@Override
	public void run()
	{
		while(Reflection.controlToggle)
		{
			if(Reflection.getCurrentInterface() != 5292)
			{
				try 
				{
					Reflection.bank();
					Thread.sleep(100);
				} 
				catch (IllegalArgumentException | IllegalAccessException | InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
	}
}
