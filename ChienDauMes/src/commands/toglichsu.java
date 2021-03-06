package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import color.mau;
import me.Cortez.Romeo.Main;
import me.Cortez.Romeo.Message;

public class toglichsu implements CommandExecutor{
	@SuppressWarnings("unused")
	private Main plugin;
	public toglichsu(Main plugin) {
		
		this.plugin = plugin;
		plugin.getCommand("toglichsu").setExecutor(this);
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		
		if(sender instanceof Player) {
			
			Player p = (Player)sender;
			
			if(!(Main.toggle.contains(p.getUniqueId()))) {
				
				p.sendMessage(mau.chu(Message.get().getString("toglichsu.enable")));
				Main.toggle.add(p.getUniqueId());
				
			} else {
				
				p.sendMessage(mau.chu(Message.get().getString("toglichsu.disable")));
				Main.toggle.remove(p.getUniqueId());
				
			}
			
		} else sender.sendMessage("§cLệnh này chỉ dành cho người chơi(player)");
		
		return false;
	}

}
