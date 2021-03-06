package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import color.mau;
import me.Cortez.Romeo.Database;
import me.Cortez.Romeo.Main;
import me.Cortez.Romeo.Message;
import ultis.utils;

public class chiso implements CommandExecutor{
	private Main plugin;
	public chiso(Main plugin) {
		
		this.plugin = plugin;
		plugin.getCommand("chiso").setExecutor(this);
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(plugin.getConfig().getBoolean("database.enable")) {
		if(sender instanceof Player) {
		
			Player p = (Player)sender;
			
		if(args.length==0) {
			
			if(!p.hasPermission("chiso.all")) {
				p.sendMessage("§c/chiso all");	
			} else p.sendMessage("§a/chiso all");	
			if(!p.hasPermission("chiso.view")) {
				p.sendMessage("§c/chiso view <player>");
			} else p.sendMessage("§a/chiso view <player>");			
			if(!p.hasPermission("chiso.reset")) {
				p.sendMessage("§c/chiso reset <player>");
			} else p.sendMessage("§a/chiso reset <player>");
			if(!p.hasPermission("chiso.resetall")) {
				p.sendMessage("§c/chiso resetAll");				
			} else p.sendMessage("§a/chiso resetAll");	
			
			
			
			}
		else if(args.length==1) {
			
			if(args[0].equals("view")) {
				p.sendMessage("§e/chiso view <player>");				
				return false;
			}
			if(args[0].equals("reset")) {
				p.sendMessage("§e/chiso reset <player>");	
				return false;
			}					
			
			if(args[0].equalsIgnoreCase("all")) {
				if(p.hasPermission("chiso.all")) {
				p.sendMessage(mau.chu(Message.get().getString("chiSo.all").replace("%dmg%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", plugin.gettotaldmg()))));
				return false;
				} else p.sendMessage(mau.chu(Message.get().getString("no-permission")));
			}
				
			
			else if(args[0].equalsIgnoreCase("resetall")) {
				
				if(p.hasPermission("chiso.resetall")) {					
					double old= plugin.totaldmg;					
					p.sendMessage(mau.chu(Message.get().getString("chiSo.resetAll").replace("%old%", 
							String.format("%."+plugin.getConfig().getString("formatDamage")+"f", old))));

					utils.logger("§a[ChienDauMes] Tiến hành reset database...");
					utils.logger("   §7§oProceed to reinstall data...");
					plugin.totaldmg = 0;
					old = 0;
					Database.get().set("Player", null);
					Database.get().set("totalDamage", 0);
					Database.save();
					Database.reload();
					for (Player player : Bukkit.getOnlinePlayers()) {
						
						plugin.getsave().setDmg(player, 0);				
					
					}
					utils.logger("§a[ChienDauMes] Reset database thành công!");
					utils.logger("    §7§oSuccessful data reset");						
					return false;
					
				} else p.sendMessage(mau.chu(Message.get().getString("no-permission")));	
				
			}	 else p.sendMessage(mau.chu(Message.get().getString("chiSo.null")));		
			
			
			}
		else if(args.length==2) {
			

			if(args[0].equalsIgnoreCase("view")) {
				if(p.hasPermission("chiso.view")) {
				
				if(Bukkit.getPlayer(args[1]) != null) {
					
					String mes = Message.get().getString("chiSo.player");
					mes = mes.replace("%dmg%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f",plugin.getsave().getplayerDmg(Bukkit.getPlayer(args[1]))));
					mes = mes.replace("%player%", Bukkit.getPlayer(args[1]).getName());		
					
					p.sendMessage(mau.chu(mes));
					} else if(Database.get().getString("Player."+args[1]) != null) {
						
						
						String mes = Message.get().getString("chiSo.player");
						mes = mes.replace("%dmg%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f",
								Database.get().getDouble("Player."+args[1])));
						mes = mes.replace("%player%", args[1]);		
						
						p.sendMessage(mau.chu(mes));						
						
					}else p.sendMessage(mau.chu(Message.get().getString("chiSo.notFound").replace("%player%", args[1])));
				
				} else p.sendMessage(mau.chu(Message.get().getString("no-permission")));
			}  else if(args[0].equalsIgnoreCase("reset")) {
					
					if(p.hasPermission("chiso.reset")) {					
					if(Bukkit.getPlayer(args[1]) != null) {						
						plugin.getsave().setDmg(Bukkit.getPlayer(args[1]), 0);
						p.sendMessage(mau.chu(Message.get().getString("chiSo.reset").replace("%player%", Bukkit.getPlayer(args[1]).getName())));
						
						} else p.sendMessage(mau.chu(Message.get().getString("chiSo.notFound").replace("%player%", args[1])));
					} else p.sendMessage(mau.chu(Message.get().getString("no-permission")));
				

				
					
				} else 
					p.sendMessage(mau.chu(Message.get().getString("chiSo.null")));
	
			} else
				 p.sendMessage(mau.chu(Message.get().getString("chiSo.null")));
		
		} else if(sender instanceof ConsoleCommandSender) {
			
			if(args.length==0) {
				utils.logger("§cConsole thì sử dụng:");				
			utils.logger("§a/chiso all");
			utils.logger("§a/chiso view <player>");
			utils.logger("§a/chiso reset <player>");			
			utils.logger("§a/chiso resetall");						
			} else if(args.length==1) {
				
				if(args[0].equalsIgnoreCase("all")) {
					
					utils.logger(mau.chu(Message.get().getString("chiSo.all").replace("%dmg%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", plugin.gettotaldmg()))));					
					
				} else if(args[0].equalsIgnoreCase("view")) {
					
					utils.logger("§e/chiso view <player>");					
					
				} else if(args[0].equalsIgnoreCase("reset")) {
					
					utils.logger("§e/chiso reset <player>");					
					
				} else if(args[0].equalsIgnoreCase("resetall")) {
					
					double old= plugin.totaldmg;					

					utils.logger("§a[ChienDauMes] Tiến hành reset database...");
					utils.logger("   §7§oProceed to reinstall data...");
					plugin.totaldmg = 0;
					Database.get().set("Player", null);
					Database.get().set("totalDamage", 0);
					Database.save();
					Database.reload();
					for (Player player : Bukkit.getOnlinePlayers()) {
						
						plugin.getsave().setDmg(player, 0);										
					
					}
					utils.logger("§a[ChienDauMes] Reset database thành công!");
					utils.logger("    §7§oSuccessful data reset");
					utils.logger(mau.chu(Message.get().getString("chiSo.resetAll").replace("%old%", 
							String.format("%."+plugin.getConfig().getString("formatDamage")+"f", old))));	
					old = 0;					
					return false;
					
				}
				
			} else if(args.length == 2) {
				
				if(args[0].equalsIgnoreCase("view")) {
					
					if(Bukkit.getPlayer(args[1]) != null) {
						
						String mes = Message.get().getString("chiSo.player");
						mes = mes.replace("%dmg%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f",plugin.getsave().getplayerDmg(Bukkit.getPlayer(args[1]))));
						mes = mes.replace("%player%", Bukkit.getPlayer(args[1]).getName());		
						
						utils.logger(mau.chu(mes));
						} else if(Database.get().getString("Player."+args[1]) != null) {
							
							
							String mes = Message.get().getString("chiSo.player");
							mes = mes.replace("%dmg%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f",
									Database.get().getDouble("Player."+args[1])));
							mes = mes.replace("%player%", args[1]);		
							
							utils.logger(mau.chu(mes));						
							
						}else utils.logger(mau.chu(Message.get().getString("chiSo.notFound").replace("%player%", args[1])));				
					
				} if(args[0].equalsIgnoreCase("reset")) {
					
					if(Bukkit.getPlayer(args[1]) != null) {						
						plugin.getsave().setDmg(Bukkit.getPlayer(args[1]), 0);
						utils.logger(mau.chu(Message.get().getString("chiSo.reset").replace("%player%", Bukkit.getPlayer(args[1]).getName())));
						
						} else utils.logger(mau.chu(Message.get().getString("chiSo.notFound").replace("%player%", args[1])));
					
				}
				
			}
			
			
		}
		} else sender.sendMessage("§cTính năng này đang tắt!");
		return true;
		
		
	}
}
