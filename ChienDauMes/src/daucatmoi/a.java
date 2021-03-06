package daucatmoi;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import color.mau;
import me.Cortez.Romeo.Main;
import me.Cortez.Romeo.Message;

public class a implements Listener{
	
	private Main plugin;
	public a(Main plugin) {
		
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
						} else if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
															
							
				
							try {
										
								Player GayDame = (Player) e.getDamager();
								Player ChiuDame = (Player) e.getEntity();
								
								String GayDameName = e.getDamager().getName();
								String ChiuDameName = e.getEntity().getName();
								String prefix = Message.get().getString("sendMessage.prefix");
											
								double dmg = e.getDamage();
											
								String ChiuDameMessage = Message.get().getString("sendMessage.ChiuDmg");
								ChiuDameMessage = ChiuDameMessage.replace("%damage%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", dmg));
								ChiuDameMessage = ChiuDameMessage.replace("%player%", GayDameName);							
				
								String GayDameMessage = Message.get().getString("sendMessage.GayDmg");					
								GayDameMessage = GayDameMessage.replace("%damage%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", dmg));
								GayDameMessage = GayDameMessage.replace("%player%", ChiuDameName);
								
								if(GayDame.getFallDistance() > 0.0F) {
									
									ChiuDameMessage=ChiuDameMessage.replace("%crit%", Message.get().getString("Critical"));
									GayDameMessage=GayDameMessage.replace("%crit%", Message.get().getString("Critical"));
									
								} else {
									
									ChiuDameMessage=ChiuDameMessage.replace("%crit%", "");
									GayDameMessage=GayDameMessage.replace("%crit%", "");							
									
								}
								
								String str = GayDame.getItemInHand().getType().name().replace("_", " ").toLowerCase();
								
										StringBuilder b = new StringBuilder(str);
										int i = 0;
										
										do {
											
											b.replace(i, i + 1, b.substring(i, i + 1).toUpperCase());
											i = b.indexOf(" ", i) + 1;
											
										} while (i > 0 && i < b.length());
													
										if(GayDame.getItemInHand().getType().name() == "AIR") {

											GayDameMessage=GayDameMessage.replace("%weapon%", "Tay trần");
											ChiuDameMessage=ChiuDameMessage.replace("%weapon%", "Tay trần");
											
										} else {
										
										if(GayDame.getItemInHand().getItemMeta().hasDisplayName() == true) {
											
											GayDameMessage=GayDameMessage.replace("%weapon%", GayDame.getItemInHand().getItemMeta().getDisplayName());
											ChiuDameMessage=ChiuDameMessage.replace("%weapon%", GayDame.getItemInHand().getItemMeta().getDisplayName());	
											
										} else {
											
											GayDameMessage=GayDameMessage.replace("%weapon%", b);
											ChiuDameMessage=ChiuDameMessage.replace("%weapon%", b);
											
											}
										}
								
								
										
																										
								
								if(Main.toggle.contains(GayDame.getUniqueId())) {
									
								GayDame.sendMessage(mau.chu(prefix+GayDameMessage));
														

								}
								
								if(Main.toggle.contains(ChiuDame.getUniqueId())) {
									
								ChiuDame.sendMessage(mau.chu(prefix+ChiuDameMessage));
								
								}
								
								if(plugin.getConfig().getBoolean("database.enable")==true) {
								plugin.totaldmg=plugin.totaldmg+dmg;
								plugin.getsave().addDmg(GayDame, dmg);
								}

								
							
								

										
							} catch(Exception x) {
								
								
								
							}	
							
							
						}
						
						
					}
					
				}
			}
				
			}
								
	}
	
	

	
	

}
