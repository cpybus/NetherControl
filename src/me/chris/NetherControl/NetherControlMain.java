package me.chris.NetherControl;

import java.util.logging.Level;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class NetherControlMain extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		new Vars(this);
    	
		/*
    	try
		{
			firstRun();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	
    	loadYamls();
    	*/
    	
    	if (!setupPermissions())
		{
			Vars.log.log(Level.SEVERE, "[NetherControl] No Permission found! Disabling plugin!");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
    	
    	getServer().getPluginManager().registerEvents(new NetherControlListener(), this);
		
		NetherControlCommandHandler commandHandler = new NetherControlCommandHandler();
		getCommand("nethercontrol").setExecutor(commandHandler);
		getCommand("nc").setExecutor(commandHandler);
				
		Vars.log.log(Level.INFO, "[NetherControl] Version " + Vars.version.substring(10));
		Vars.log.log(Level.INFO, "[NetherControl] Started successfully.");		
	}
	
	@Override
	public void onDisable()
	{
		Vars.log.log(Level.INFO, "[NetherControl] Stopped.");	
	}
	
	/*
	private void firstRun() throws Exception
	{
		if (!Vars.configFile.exists())
		{
			Vars.log.log(Level.INFO, "[NetherControl] No config.yml file found. Attempting to make one. ");
			Vars.configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), Vars.configFile);
			Vars.log.log(Level.INFO, "[NetherControl] File Made Successfully ");
		}
		else
		{
			Vars.log.log(Level.INFO, "[NetherControl] Config Found. Using it.  ");
		}
		
	}
	
	private void copy(InputStream in, File file)
	{
		try
		{
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0)
			{
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadYamls()
	{
		try
		{
			Vars.config.load(Vars.configFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		

		Vars.importVariables();
	}
	
	public void saveYamls()
	{
		Vars.exportVariables();
		
		try
		{
			Vars.config.save(Vars.configFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	*/
	
	
	private Boolean setupPermissions()
	{
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null)
		{
			Vars.perms = permissionProvider.getProvider();
		}
		return (Vars.perms != null);
	}	
	
	
}
