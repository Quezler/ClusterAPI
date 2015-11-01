package me.AdityaTD.ClusterAPI;

import org.bukkit.entity.Player;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Actionbar extends JavaPlugin {
	public static boolean works = true;
	public static String nmsver;

    public void onEnable() { 
        nmsver = Bukkit.getServer().getClass().getPackage().getName();
		nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
    }

    public static Class<?> getNmsClass(String nmsClassName)
    	    throws ClassNotFoundException
    	  {
    	    return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    	  }
    	  
    	  public static String getServerVersion()
    	  {
    	    return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    	  }

    public static void sendActionBar(Player p, String msg) {
      try {
        if ((getServerVersion().equalsIgnoreCase("v1_8_R2")) || (getServerVersion().equalsIgnoreCase("v1_8_R3"))) {
          Object icbc = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{'text': '" + msg + "'}" });       
          Object ppoc = getNmsClass("PacketPlayOutChat").getConstructor(new Class[] { 
          getNmsClass("IChatBaseComponent"), Byte.TYPE }).newInstance(new Object[] { icbc, Byte.valueOf((byte) 2) }); 
          Object nmsp = p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
          Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
          pcon.getClass().getMethod("sendPacket", new Class[] { getNmsClass("Packet") }).invoke(pcon, new Object[] { ppoc });
        } else {
          Object icbc = getNmsClass("ChatSerializer").getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{'text': '" + msg + "'}" });
          Object ppoc = getNmsClass("PacketPlayOutChat").getConstructor(new Class[] { getNmsClass("IChatBaseComponent"), Byte.TYPE }).newInstance(new Object[] { icbc, Byte.valueOf((byte) 2) });
          Object nmsp = p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
          Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
          pcon.getClass().getMethod("sendPacket", new Class[] { getNmsClass("Packet") }).invoke(pcon, new Object[] { ppoc });
        }
      } catch (Exception ex){
        ex.printStackTrace();
      }
    }
}