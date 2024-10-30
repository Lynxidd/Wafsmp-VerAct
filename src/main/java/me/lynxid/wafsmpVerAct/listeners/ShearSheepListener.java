package me.lynxid.wafsmpVerAct.listeners;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class ShearSheepListener implements Listener {

    @EventHandler
    public void onSheepShear(PlayerShearEntityEvent e){

        Player player = e.getPlayer();
        Entity entity = e.getEntity();

        if (entity.getType() == EntityType.SHEEP){
            player.sendMessage("This is a sheep. WHat are you DOING?");
            e.setCancelled(true);
            player.kickPlayer("Dude waht the fuck that was a sheep :(");
        }{
            player.sendMessage("This is not a sheep!");
        }
    }

}
