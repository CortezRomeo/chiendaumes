package daucatmoi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import color.mau;
import me.Cortez.Romeo.Main;
import me.Cortez.Romeo.Message;

public class d implements Listener{
	
	private Main plugin;
	public d(Main plugin) {
		
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
						} else if (e.getDamager() instanceof Arrow) {
							
								
						      Arrow a1 = (Arrow)e.getDamager();

						      if(a1.getShooter() instanceof Monster) {
						    	  
						    	  Monster shooter = (Monster)a1.getShooter();
						    	  Damageable dp = (Damageable)e.getEntity();
						    	  
						    	  if (dp instanceof Player) {
						    		  
							          Player chiuDame = (Player)dp;
							          
							          try {
							        	  							        	  
							        	  
							        	  String prefixz = Message.get().getString("sendMessage.prefix");
							            	double dmg = e.getDamage();
											if(plugin.getConfig().getBoolean("database.enable")) {
												
												if(plugin.getConfig().getBoolean("database.receiveDmgFromMonster")==true) {
													plugin.totaldmg=plugin.totaldmg+dmg;
												}
												
												
											}							            	
							            	
							            	String ChiuDmgm=Message.get().getString("sendMessage.ChiuDmg");
							            	ChiuDmgm = ChiuDmgm.replace("%damage%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", dmg));
							            	ChiuDmgm = ChiuDmgm.replace("%player%", shooter.getName());		
							            	ChiuDmgm = ChiuDmgm.replace("%crit%", "");		
							            	
							            	if (shooter.getEquipment().getItemInHand().getItemMeta().hasDisplayName()) {
							            		
							            		ChiuDmgm = ChiuDmgm.replace("%weapon%", shooter.getEquipment().getItemInHand().getItemMeta().getDisplayName());
							            		
							            	} else {
							            		
							            		ChiuDmgm = ChiuDmgm.replace("%weapon%", "Cung");
							            		
							            	}
							            	
							            	
							            	String GayDmgm=Message.get().getString("sendMessage.GayDmg");
							            	GayDmgm = GayDmgm.replace("%damage%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", dmg));
							            	GayDmgm = GayDmgm.replace("%player%", chiuDame.getName());		
							            	GayDmgm = GayDmgm.replace("%crit%", "");		
							            	
							            	if (shooter.getEquipment().getItemInHand().getItemMeta().hasDisplayName()) {
							            		
								            	GayDmgm = GayDmgm.replace("%weapon%", shooter.getEquipment().getItemInHand().getItemMeta().getDisplayName());
							            		
							            	} else {
							            		
								            	GayDmgm = GayDmgm.replace("%weapon%", "Cung");
							            		
							            	}
							            	
							              if(Main.toggle.contains(chiuDame.getUniqueId())) {
							              chiuDame.sendMessage(mau.chu(prefixz+ChiuDmgm));
							              }
   	  
							          } catch(Exception ex) {
							        	  
							        	  
							          }
							          
						    	  }
						    	  
						      } else if (a1.getShooter() instanceof Player) {
						    	  
						    	  
						    	  Player shooter = (Player)a1.getShooter();
						    	  Damageable dp = (Damageable)e.getEntity();
						    	  
						    	  if (dp instanceof Player) { 
						    	  } else {
						    		  						    		  
							          LivingEntity chiuDame = (LivingEntity)dp;
							          
							          try {
							        	  							        	  
							        	  
							        	  String prefixz = Message.get().getString("sendMessage.prefix");
							            	double dmg = e.getDamage();
											if(plugin.getConfig().getBoolean("database.enable")) {
							            	plugin.getsave().addDmg(shooter, dmg);
							            	plugin.totaldmg=plugin.totaldmg+dmg;					
													}
							            	
							            	String ChiuDmgm=Message.get().getString("sendMessage.ChiuDmg");
							            	ChiuDmgm = ChiuDmgm.replace("%damage%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", dmg));
							            	ChiuDmgm = ChiuDmgm.replace("%player%", shooter.getName());		
							            	ChiuDmgm = ChiuDmgm.replace("%crit%", "");		
							            	
							            	if (shooter.getEquipment().getItemInHand().getItemMeta().hasDisplayName()) {
							            		
							            		ChiuDmgm = ChiuDmgm.replace("%weapon%", shooter.getEquipment().getItemInHand().getItemMeta().getDisplayName());
							            		
							            	} else {
							            		
							            		ChiuDmgm = ChiuDmgm.replace("%weapon%", "Cung");
							            		
							            	}
							            	
							            	
							            	String GayDmgm=Message.get().getString("sendMessage.GayDmg");
							            	GayDmgm = GayDmgm.replace("%damage%", String.format("%."+plugin.getConfig().getString("formatDamage")+"f", dmg));
							            	GayDmgm = GayDmgm.replace("%player%", chiuDame.getName());		
							            	GayDmgm = GayDmgm.replace("%crit%", "");		
							            	
							            	if (shooter.getEquipment().getItemInHand().getItemMeta().hasDisplayName()) {
							            		
								            	GayDmgm = GayDmgm.replace("%weapon%", shooter.getEquipment().getItemInHand().getItemMeta().getDisplayName());
							            		
							            	} else {
							            		
								            	GayDmgm = GayDmgm.replace("%weapon%", "Cung");
							            		
							            	}
							            
							              if(Main.toggle.contains(shooter.getUniqueId())) {
							              shooter.sendMessage(mau.chu(prefixz+GayDmgm));
							              }
							              if(chiuDame == (Player) chiuDame && Main.toggle.contains(chiuDame.getUniqueId())) {
							            	
							            	  chiuDame.sendMessage(mau.chu(prefixz+ChiuDmgm));
							            	  
							              }
																	              
							             
   	  
							          } catch(Exception ex) {
							        	  
							        	  
							          }
						    	  }
						    	  
						      }
						}
						
						
					}
					
				}
				
			}
			
		}
		
	}
}
