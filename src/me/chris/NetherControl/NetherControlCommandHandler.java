
package me.chris.NetherControl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Chris
 *
 */
public class NetherControlCommandHandler implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String idk, String[] args)
	{
		Player p = (Player) sender;
		
		p.sendMessage("§5=====================================================");
		p.sendMessage("§a Welcome to §cNetherControl §aPlugin §9(" + Vars.version + ")");
		p.sendMessage("§a Designed and Programmed by §9Hotshot2162");
		p.sendMessage("§5=====================================================");
		return true;
	}
}
