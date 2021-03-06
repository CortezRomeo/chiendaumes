package me.Cortez.Romeo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import commands.chiendaumes;
import commands.chiso;
import commands.toglichsu;
import daucatmoi.a;
import daucatmoi.b;
import daucatmoi.c;
import daucatmoi.d;
import daucatmoi.e;
import daucatmoi.f;
import daucatmoi.g;
import daucatmoi.h;
import daucatmoi.i;
import ultis.check;
import ultis.damage;
import ultis.registerMes;
import ultis.utils;

public class Main extends JavaPlugin implements Listener{
	
	public static List<UUID> toggle = new ArrayList<UUID>();
	
	public void autoSave() {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				utils.logger("[ChienDauMes] Lưu database...");
				Database.get().set("totalDamage", totaldmg);
				for(Player p : Bukkit.getOnlinePlayers()) {
				Database.get().set("Player."+p.getName(), getsave().getplayerDmg(p));
				}
				Database.save();
				utils.logger("[ChienDauMes] Lưu database thành công!");
				
			}
		}.runTaskTimer(this, 900*20, 900*20);
		// lưu dữ liệu mỗi 15p
	}
	
	@Override
	public void onEnable() {
		
		
		enable();
		check();
		loadConfig();
		loadCommands();				
		loadMessage();
		load();
		
	}
	
	public double totaldmg = 0;
	
	public double gettotaldmg() {
		return totaldmg;
	}			
	
	public void check() {
		
		if(getConfig().getBoolean("database.enable")==true) {
			Database.setup();
			 new placeholderapi(this).register();
			autoSave();
			
			totaldmg=Database.get().getDouble("totalDamage");
			new i(this);
			new damage(this);			
			
		}
		
	}
	
	public void enable() {

		String author = String.valueOf(getDescription().getAuthors());
		author = author.replace("[", "");
		author = author.replace("]", "");		
		
		String q = "§2§l§m==========================";
		String w = "";
		String e = "      §a§l§lChienDauMes";
		String r = "§fVersion: §e"+getDescription().getVersion();
		String t = "§fAuthor: §e"+author;	
		String y = "";
		String u = "§2§l§m==========================";		
		
		utils.logger(q);
		utils.logger(w);
		utils.logger(e);
		utils.logger(r);			
		utils.logger(t);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
           
        utils.logger("§aHooked PlaceholderAPI");
        utils.logger("§2Đã tích hợp PlaceholderAPI");        
      } else {
    	  
    	  utils.logger("§cPlaceholderAPI not detected");
    	  utils.logger("§4Không phát hiện PlaceholderAPI");    	  
    	  
      }			
        if(getConfig().getBoolean("database.enable")==true) {
        	
			utils.logger("§aLoaded database");
	  	  	utils.logger("§2Đã kết nối dữ liệu"); 	
	  	  	
        } else {
        	
			utils.logger("§cUn-Loaded database");
	  	  	utils.logger("§4Hủy cài dữ liệu"); 	        	
        	
        }	
		utils.logger(y);
		utils.logger(u);		
		
	}	
	
	public void loadConfig() {	
        
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
	}	
	
	public void loadCommands() {			
        
		new chiendaumes(this);
		new toglichsu(this);
		new chiso(this);
		
	}		
	
	
	public void loadMessage() {
		
		Message.setup();
		new registerMes(this);
		
	}
	
	public void load() {
		
		if(getConfig().getBoolean("activeWorld.enable")==true) {
		new a(this);
		new b(this);
		new c(this);
		new d(this);	
		} else {
		new e(this);			
		new f(this);
		new g(this);
		new h(this);
		}		
		new check(this);
		new utils();
		
		
	}	
	
	@Override
	public void onDisable() {

		disable();
		if(getConfig().getBoolean("database.enable")==true) {
		saveDatabase();
		}
		
	}	
	
	public void disable() {
		
		String author = String.valueOf(getDescription().getAuthors());
		author = author.replace("[", "");
		author = author.replace("]", "");
		
		String q = "§4§l§m==========================";
		String w = "";
		String e = "      §c§l§lChienDauMes";
		String r = "§fVersion: §e"+getDescription().getVersion();
		String t = "§fAuthor: §e"+author;
		String y = "";
		String u = "§4§l§m==========================";		
		
		utils.logger(q);
		utils.logger(w);
		utils.logger(e);
		utils.logger(r);			
		utils.logger(t);
		utils.logger(y);
		utils.logger(u);
			
	}
	
	public void saveDatabase() {		
		
		Database.get().set("totalDamage", totaldmg);
		Database.save();
		
	}
	
	public damage getsave() {
		return new damage(this);
	}
	  
    
}
