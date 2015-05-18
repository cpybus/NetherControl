package me.chris.NetherControl;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class NetherControlListener implements Listener
{
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onSignChange(SignChangeEvent event)
	{
				Player p = event.getPlayer();
		String[] msg = event.getLines();
		
		System.out.print("Test1");
		

		if (msg[1] != null && msg[2] != null)
		{
			System.out.print("Test2");
			if (msg[1].equalsIgnoreCase("[NetherControl]"))
			{
				System.out.print("Test3");
				if (msg[2].equals("OfficialPortal"))
				{
					System.out.print("Test4");
					if (Vars.perms.has(p, "nethercontrol.createportal"))
					{
						p.sendMessage("§a[NetherControl]§3 Official portal created.");
					}
					else
					{
						event.setLine(0, "");
						event.setLine(1, "§4NO");
						event.setLine(2, "§4PERMS");
						event.setLine(3, "");
						p.sendMessage("§a[NetherControl]§4 You dont have permission. ");
					}
				}
				else
				{
					event.setLine(0, "");
					event.setLine(1, "§4INCORRECT");
					event.setLine(2, "§4SYNTAX");
					event.setLine(3, "");
					p.sendMessage("§a[NetherControl]§4 Incorrect syntax. ");
				}
			}
		}
		else
		{
			return;
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerPortalEvent(PlayerPortalEvent event)
	{
		
		Location locOfPortal = event.getFrom();
		
		double xOfPortal = locOfPortal.getX();
		double yOfPortal = locOfPortal.getY();
		double zOfPortal = locOfPortal.getZ();
		
		double xStart = xOfPortal - 3;
		double yStart = yOfPortal - 5;
		double zStart = zOfPortal - 3;
		
		double xEnd = xOfPortal + 3;
		double yEnd = yOfPortal + 5;
		double zEnd = zOfPortal + 3;
		
		for(double yIndex = yStart; yIndex<=yEnd; yIndex++)
		{
			for(double zIndex = zStart; zIndex<=zEnd; zIndex++)
			{
				
				for(double xIndex = xStart; xIndex<=xEnd; xIndex++)
				{
					Location searching = new Location(locOfPortal.getWorld(), xIndex, yIndex, zIndex);
					Block block = searching.getBlock();
					
					if(block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)
					{
						Sign sign = (Sign) block.getState();
						String[] msg = sign.getLines();
						
						if (msg[1].equalsIgnoreCase("[NetherControl]") && msg[2].equals("OfficialPortal"))
						{
							return;
						}
						else
						{
							continue;
						}
							
					}
					
					
				}
			}
		}
		
		event.setCancelled(true);
		killPortal(locOfPortal);
		
		event.getPlayer().sendMessage("§a[NetherControl]§4 Illegal portals are not allowed. Any online OPs have been notified.");
		for(Player p : Vars.plugin.getServer().getOnlinePlayers())
		{
			if(p.isOp())
			{
				p.sendMessage("§a[NetherControl]§6 " + event.getPlayer().getName() + " §4attempted to use an illegal portal.");
				
			}
		}
		
	}

	private void killPortal(Location locOfPortal)
	{
		double xOfPortal = locOfPortal.getX();
		double yOfPortal = locOfPortal.getY();
		double zOfPortal = locOfPortal.getZ();
		
		double xStart = xOfPortal - 3;
		double yStart = yOfPortal - 5;
		double zStart = zOfPortal - 3;
		
		double xEnd = xOfPortal + 3;
		double yEnd = yOfPortal + 5;
		double zEnd = zOfPortal + 3;
		
		for(double yIndex = yStart; yIndex<=yEnd; yIndex++)
		{
			for(double zIndex = zStart; zIndex<=zEnd; zIndex++)
			{
				
				for(double xIndex = xStart; xIndex<=xEnd; xIndex++)
				{
					Location searching = new Location(locOfPortal.getWorld(), xIndex, yIndex, zIndex);
					Block block = searching.getBlock();
					
					if(block.getType() == Material.OBSIDIAN || block.getType() == Material.PORTAL || block.getType() == Material.ENDER_PORTAL_FRAME || block.getType() == Material.ENDER_PORTAL)
					{
						block.setType(Material.AIR);
					}
					
				}
			}
		}
		
	}
	
}
