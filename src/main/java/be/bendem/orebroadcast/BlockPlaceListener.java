package be.bendem.orebroadcast;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockPlaceListener implements Listener {

    public OreBroadcast plugin;

    BlockPlaceListener(OreBroadcast plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Block block = e.getBlock();
        if(plugin.isWhitelisted(block.getType())
                && plugin.isWorldWhitelisted(block.getWorld().getName())
                && !plugin.isBlackListed(block)
                && (e.getPlayer().getGameMode() != GameMode.CREATIVE
                    || !plugin.getConfig().getBoolean("broadcast-creative-placed-blocks", true))) {
            plugin.blackList(block);
        }
    }

}
