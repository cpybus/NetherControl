/**
 * 
 */
package me.chris.NetherControl;

import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

/**
 * @author Christopher Pybus
 * @date Mar 25, 2012
 * @file SimpleChatVariables.java
 * @package me.chris.SimpleChat
 * 
 * @purpose
 */

public class Vars
{
	//public static FileConfiguration config;
	public static Permission perms;
	public static Logger log;
	public static NetherControlMain plugin;

	//public static File configFile;

	public static final String version = "NetherControl 1.0";

	public Vars(NetherControlMain plugin)
	{
		Vars.plugin = plugin;
		log = Logger.getLogger("Minecraft");

		//configFile = new File(plugin.getDataFolder(), "config.yml");

		//config = new YamlConfiguration();
	}

	public static void importVariables()
	{

	}

	public static void exportVariables()
	{

	}
}
