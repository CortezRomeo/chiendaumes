package ultis;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Cortez.Romeo.Database;
import me.Cortez.Romeo.Main;

public class damage implements Listener{
	
	private Main plugin;
	public damage(Main plugin) {
		
		this.plugin = plugin;
		if(plugin.getConfig().getBoolean("database.enable")) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		}
		
	}

	public static HashMap<Player, Double> playerdmg = new HashMap<Player, Double>();
	
	public double getplayerDmg(Player p) {
		
		if (!playerdmg.containsKey(p)) return 0;
		
		return playerdmg.get(p);
		
	}
	
	public void setDmg(Player p,double i) {
		
		playerdmg.put(p, i);
		
	}
	
	public void addDmg(Player p,double i) {
		
		 playerdmg.put(p, getplayerDmg(p)+i);
		
	}	
	


	@EventHandler
	public void saveDatabase(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		Database.get().set("totalDamage", plugin.totaldmg);
		Database.get().set("Player."+p.getName(), plugin.getsave().getplayerDmg(p));
		Database.save();
		
	}
	
	@EventHandler
	public void loadDatabase(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		plugin.getsave().setDmg(p, Database.get().getDouble("Player."+p.getName()));
		
	}	
	
	
}
