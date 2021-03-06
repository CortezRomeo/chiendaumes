package daucatmoi;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import color.mau;
import me.Cortez.Romeo.Database;
import me.Cortez.Romeo.Main;
import me.Cortez.Romeo.Message;

public class i {
	
	private Main plugin;
	public i(Main plugin) {
		
		this.plugin = plugin;
		sendmes();
		
	}
	
	public void sendmes() {
		
		if(plugin.getConfig().getBoolean("staticsIndex.enable")==true)  {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(Main.toggle.contains(p.getUniqueId())) { 
					 
					if(plugin.getConfig().getBoolean("activeWorld.enable")) {
						
						for(String getworld : plugin.getConfig().getStringList("activeWorld.worlds")) {
						
						if(p.getWorld() == Bukkit.getServer().getWorld(getworld)) {
							
							
							List<String> si = Message.get().getStringList("staticsIndex");
							
							for(String staticsindex : si) {
								
								staticsindex = staticsindex.replace("%serverdmg%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", plugin.gettotaldmg()));
								staticsindex = staticsindex.replace("%playerdmg%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", 
										plugin.getsave().getplayerDmg(p)));								
								
								p.sendMessage(mau.chu(staticsindex));
							
								
							}
							
							
							}
						}
					} else {
						
						List<String> si = Message.get().getStringList("staticsIndex");
						
						for(String staticsindex : si) {
							
							staticsindex = staticsindex.replace("%serverdmg%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", plugin.gettotaldmg()));
							staticsindex = staticsindex.replace("%playerdmg%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", 
									plugin.getsave().getplayerDmg(p)));								
							
							p.sendMessage(mau.chu(staticsindex));
							
						}			
						
					}
					 
				}
				}
				Database.get().set("totalDamage", plugin.totaldmg);
			}
			
		}.runTaskTimer(plugin, 0, plugin.getConfig().getInt("staticsIndex.delay")*20);
		
		
		} else return;
		
	}
	
}
