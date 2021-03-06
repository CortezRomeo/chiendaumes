package me.Cortez.Romeo;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class placeholderapi extends PlaceholderExpansion{
	
	private Main plugin;
	public  placeholderapi(Main plugin) {

this.plugin=plugin;
		
	}

	@Override
	public String getAuthor() {
		return "Cortez Romeo";
	}

	@Override
	public String getIdentifier() {
		return "chiendaumes";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
	
    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }

        if(identifier.equals("playerdmg")){
            return String.format("%."+plugin.getConfig().getString("formatDamage")+"f", plugin.getsave().getplayerDmg(player));
        }

        if(identifier.equals("serverdmg")){
            return String.format("%."+plugin.getConfig().getString("formatDamage")+"f", plugin.gettotaldmg());
        }

        return null;
    }
	
}
