package me.AdityaTD.ClusterAPI;

import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
  public void onEnable()  {
    try{
      Metrics metrics = new Metrics(this);
      metrics.start();
    }
    
    catch (IOException e){
    	
      ConsoleMSG(ChatColor.DARK_RED + "ClusterAPI couldn't send data to MCSTATS.ORG");
      ConsoleMSG(ChatColor.AQUA + "Stack Trace of the Error > START!");
      e.printStackTrace();
      ConsoleMSG(ChatColor.AQUA + "Stack Trace of the Error > END!");
      
    }
    
    ConsoleMSG(ChatColor.LIGHT_PURPLE + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    ConsoleMSG(ChatColor.GOLD + "ClusterAPI enabled. | Version: 0.1");
    ConsoleMSG(ChatColor.GOLD + "By: AdityaTD | YouTube: YouTube.com/c/AdityaTD");
    ConsoleMSG(ChatColor.LIGHT_PURPLE + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
  }
  
  public void onDisable(){
    ConsoleMSG(ChatColor.LIGHT_PURPLE + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    ConsoleMSG(ChatColor.GOLD + "ClusterAPI" + ChatColor.RED + "disabled. " + ChatColor.GOLD + "| Version: 0.1");
    ConsoleMSG(ChatColor.GOLD + "By: AdityaTD | YouTube: YouTube.com/c/AdityaTD");
    ConsoleMSG(ChatColor.LIGHT_PURPLE + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
  }
  
  public void ConsoleMSG(String msg){
    getServer().getConsoleSender().sendMessage( "["+ getPlugin(Main.class).getName() +"] " + msg);
  }
}
