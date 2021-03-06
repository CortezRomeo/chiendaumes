package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import color.mau;
import me.Cortez.Romeo.Database;
import me.Cortez.Romeo.Main;
import me.Cortez.Romeo.Message;
import ultis.utils;

public class chiendaumes implements CommandExecutor{
	private Main plugin;
	public chiendaumes(Main plugin) {
		
		this.plugin = plugin;
		plugin.getCommand("chiendaumes").setExecutor(this);
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player)sender;
			if(p.hasPermission("chiendaumes.admin") && p.isOp()==true) {
				
				plugin.reloadConfig();
				Message.reload();
				if(plugin.getConfig().getBoolean("database.enable")) {
					Database.reload();
					loadDatabase();			
					}
				
				
				p.sendMessage("§aReloaded!");
				p.sendTitle("", "§aChienDauMes Reloaded", 0, 30, 0);
				
				
			}  else p.sendMessage(mau.chu(Message.get().getString("no-permission")));
			
			
		} else if(sender instanceof CommandSender) {
			
			plugin.reloadConfig();
			Message.reload();
			if(plugin.getConfig().getBoolean("database.enable")) {
			Database.reload();
			loadDatabase();			
			}
			
			utils.logger("§aChienDauMes reloaded!");
			
		}
		
		return true;
	}
	
	public void loadDatabase() {
		
		plugin.totaldmg=Database.get().getDouble("totalDamage");
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			plugin.getsave().setDmg(p, Database.get().getDouble("Player."+p.getName()));
			
		}
		
	}
	
	
	
}
