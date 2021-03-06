package ultis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import color.mau;
import me.Cortez.Romeo.Main;
import me.Cortez.Romeo.Message;

public class check implements Listener{

	private Main plugin;
	public check(Main plugin) {
		
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);		
		
	}	
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void join(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(plugin.getConfig().getBoolean("autoSet_enable")==true) {
		
		if(!Main.toggle.contains(p.getUniqueId())) {
			
			Main.toggle.add(p.getUniqueId());
			
			} 
		} else {
			
			if(plugin.getConfig().getBoolean("alertPlayer_ifDisable")==true) {
			
			if(!Main.toggle.contains(p.getUniqueId())) {
				
				p.sendMessage(mau.chu(Message.get().getString("toglichsu.isDisable")));
				
				} 
			}
			
		}
		
	}	
	
}
