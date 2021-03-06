package daucatmoi;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import color.mau;
import me.Cortez.Romeo.Main;
import me.Cortez.Romeo.Message;

public class c implements Listener{
	
	private Main plugin;
	public c(Main plugin) {
		
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);		
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)	
	public void aa(EntityDamageByEntityEvent e) {
		
		if(plugin.getConfig().getBoolean("sendMessage.enable")==true) {
			
			if(plugin.getConfig().getBoolean("activeWorld.enable")==true) {
				
				for(String getWorldName : plugin.getConfig().getStringList("activeWorld.worlds")) {
					
					if(e.getEntity().getWorld() == Bukkit.getServer().getWorld(getWorldName) && 
							e.getDamager().getWorld() == Bukkit.getServer().getWorld(getWorldName)) {
						
						if(e.getEntity().getType() == EntityType.ARMOR_STAND) {				
						} 
						
						if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
						} else {
							
							try {
								
								double dmg = e.getDamage();
								
								String prefix = Message.get().getString("sendMessage.prefix");
								String ChiuDmgMes = Message.get().getString("sendMessage.ChiuDmg");
								ChiuDmgMes = ChiuDmgMes.replace("%damage%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", dmg));
								ChiuDmgMes = ChiuDmgMes.replace("%player%", e.getDamager().getName());								
								
								String GayDmgMes = Message.get().getString("sendMessage.GayDmg");
								
								GayDmgMes = GayDmgMes.replace("%damage%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", dmg));
								GayDmgMes = GayDmgMes.replace("%player%", e.getEntity().getName());
								
								if(e.getDamager().getFallDistance() > 0.0F) {
									
									ChiuDmgMes=ChiuDmgMes.replace("%crit%", Message.get().getString("Critical"));
									GayDmgMes=GayDmgMes.replace("%crit%", Message.get().getString("Critical"));
									
								} else {
									
									ChiuDmgMes=ChiuDmgMes.replace("%crit%", "");
									GayDmgMes=GayDmgMes.replace("%crit%", "");							
									
								}	
								
								if(e.getDamager().getType() != EntityType.PLAYER) {
									
									LivingEntity gd = (LivingEntity) e.getDamager();
									
									String str = gd.getEquipment().getItemInHand().getType().name().replace("_", " ").toLowerCase();
									
									StringBuilder b = new StringBuilder(str);
									int i = 0;
									
									do {
										
										b.replace(i, i + 1, b.substring(i, i + 1).toUpperCase());
										i = b.indexOf(" ", i) + 1;
										
									} while (i > 0 && i < b.length());
												
									if(gd.getEquipment().getItemInHand().getType().name() == "AIR") {

										GayDmgMes=GayDmgMes.replace("%weapon%", "Tay trần");
										ChiuDmgMes=ChiuDmgMes.replace("%weapon%", "Tay trần");
										
									} else {
									
									if(gd.getEquipment().getItemInHand().getItemMeta().hasDisplayName() == true) {
										
										GayDmgMes=GayDmgMes.replace("%weapon%", gd.getEquipment().getItemInHand().getItemMeta().getDisplayName());
										ChiuDmgMes=ChiuDmgMes.replace("%weapon%", gd.getEquipment().getItemInHand().getItemMeta().getDisplayName());	
										
									} else {
										
										GayDmgMes=GayDmgMes.replace("%weapon%", b);
										ChiuDmgMes=ChiuDmgMes.replace("%weapon%", b);
										
										}
									}
									if(Main.toggle.contains(e.getEntity().getUniqueId()))	 {
									e.getEntity().sendMessage(mau.chu(prefix+ChiuDmgMes));
									} else return;
									if(plugin.getConfig().getBoolean("database.true")) {
										
										if(plugin.getConfig().getBoolean("database.receiveDmgFromMonster")==true) {
										plugin.totaldmg=plugin.totaldmg+dmg;
										}
										
									}
									
								} else {
									if(plugin.getConfig().getBoolean("database.enable")==true) {
										plugin.getsave().addDmg((Player) e.getDamager(), dmg);		
						            	plugin.totaldmg=plugin.totaldmg+dmg;
										}
																		
									String str = ((Player) e.getDamager()).getItemInHand().getType().name().replace("_", " ").toLowerCase();
									
									StringBuilder b = new StringBuilder(str);
									int i = 0;
									
									do {
										
										b.replace(i, i + 1, b.substring(i, i + 1).toUpperCase());
										i = b.indexOf(" ", i) + 1;
										
									} while (i > 0 && i < b.length());
									
								
									
									if(((Player) e.getDamager()).getItemInHand().getType().name() == "AIR") {

										GayDmgMes=GayDmgMes.replace("%weapon%", "Tay trần");
										ChiuDmgMes=ChiuDmgMes.replace("%weapon%", "Tay trần");
										
									} else {
									
									if(((Player) e.getDamager()).getItemInHand().getItemMeta().hasDisplayName() == true) {
										
										GayDmgMes=GayDmgMes.replace("%weapon%", ((Player) e.getDamager()).getItemInHand().getItemMeta().getDisplayName());
										ChiuDmgMes=ChiuDmgMes.replace("%weapon%", ((Player) e.getDamager()).getItemInHand().getItemMeta().getDisplayName());	
										
									} else {
										
										GayDmgMes=GayDmgMes.replace("%weapon%", b);
										ChiuDmgMes=ChiuDmgMes.replace("%weapon%", b);
										
										}
									}
								}
								
								if(Main.toggle.contains(e.getDamager().getUniqueId())) {
								e.getDamager().sendMessage(mau.chu(prefix+GayDmgMes ));
								} else return;
																
							} catch(Exception ex) {
								
										
								
							}
							
						}
						
						
					}
					
				}
				
			}
			
		}
		
	}
}
