package ultis;

import me.Cortez.Romeo.Main;
import me.Cortez.Romeo.Message;

public class registerMes {
	@SuppressWarnings("unused")
	private Main plugin;
	public registerMes(Main plugin) {
			
		register();
		
	}
	
	String[] staticsIndex = new String[]{"  &2&l&nTHỐNG KÊ SÁT THƯƠNG", " ", "&fTổng số sát thương của máy chủ: &a%serverdmg%", "&fSố sát thương bạn đã gây ra: &a%playerdmg%"};	
	
	public void register() {		
        Message.get().addDefault("no-permission", "&cBạn không có quyền để dùng lệnh này");    		
        Message.get().addDefault("Critical", "&4&l(Crit) ");          
        Message.get().addDefault("toglichsu.enable", "&aBật lịch sử chiến đấu");      
        Message.get().addDefault("toglichsu.disable", "&cTắt lịch sử chiến đấu");      
        Message.get().addDefault("toglichsu.isDisable", "&fBạn chưa bật lịch sử chiến đấu, sử dụng lệnh &e/toglichsu&f để bật!");
		Message.get().addDefault("sendMessage.prefix", "&f[CHIẾN ĐẤU] ");        
        Message.get().addDefault("sendMessage.GayDmg", "&a-%damage% HP %crit%&f[&e%weapon%&f] &f(&3%player%&f)");
        Message.get().addDefault("sendMessage.ChiuDmg", "&c-%damage% HP %crit%&f[&3%weapon%&f] &f(&6%player%&f)");
        Message.get().addDefault("chiSo.all", "&fTổng số sát thương của máy chủ hiện tại là: &a%dmg%");
        Message.get().addDefault("chiSo.player", "&fTổng số sát thương của %player% đã gây ra là: &a%dmg%");        
        Message.get().addDefault("chiSo.notFound", "&cNgười chơi &e%player% &ckhông online hoặc không có trong cơ sở dữ liệu!");      
        Message.get().addDefault("chiSo.reset", "&fĐã reset số sát thương mà &a%player% &fđã gây ra");
        Message.get().addDefault("chiSo.resetAll", "&fĐã reset tổng số sát thương của máy chủ (&e%old%&f)");  
        Message.get().addDefault("chiSo.null", "&cSai cú pháp!");          
        Message.get().addDefault("staticsIndex", staticsIndex);
		Message.get().options().copyDefaults(true);
		Message.save();
	}
	
}
