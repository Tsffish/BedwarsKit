package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.levelupShopOpenModeEntityName;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.shopItemEntityName;
import static github.tsffish.bedwarskit.util.player.RelEditGame.isEditGamePlayer;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class EditPlayerLookEntity {
    private static final String entityInfoItemName = "Left Edit Mode (Right Click)";
    private static final String entitySetNameItemName = "SetNameTo: {name} (Right Click)";
    private static final String entitySetNameItemName2 = "SetNameTo: {name} (Right Click)";
    private static final String entityRemoveNameItemName = "RemoveName: {name} (Right CLick)";
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    public static void runTask(Player player) {
        if (player == null || !player.isOnline() || !isEditGamePlayer(player.getUniqueId())) {
            return;
        }
        ItemStack entityInfoItem = new ItemStack(Material.PAPER);
        ItemMeta entityInfoItemMeta = entityInfoItem.getItemMeta();
        entityInfoItemMeta.setDisplayName(entityInfoItemName);
        entityInfoItem.setItemMeta(entityInfoItemMeta);

        ItemStack entitySetNameItem = new ItemStack(Material.NAME_TAG);
        ItemMeta entitySetNameItemMeta = entitySetNameItem.getItemMeta();
        entitySetNameItemMeta.setDisplayName(entitySetNameItemName);
        entitySetNameItem.setItemMeta(entitySetNameItemMeta);

        ItemStack entitySetNameItem2 = new ItemStack(Material.ITEM_FRAME);
        ItemMeta entitySetNameItem2Meta = entitySetNameItem2.getItemMeta();
        entitySetNameItem2Meta.setDisplayName(entitySetNameItemName2);
        entitySetNameItem2.setItemMeta(entitySetNameItem2Meta);

        ItemStack entityRemoveNameItem = new ItemStack(Material.REDSTONE);
        ItemMeta entityRemoveNameItemMeta = entityRemoveNameItem.getItemMeta();
        entityRemoveNameItemMeta.setDisplayName(entityRemoveNameItemName);
        entityRemoveNameItem.setItemMeta(entityRemoveNameItemMeta);


        UUID playerUUID = player.getUniqueId();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (player == null || !player.isOnline()) {
                    cancel();
                }

                if (!isEditGamePlayer(playerUUID)) {
                    PlayerInventory pi = player.getInventory();
                    pi.remove(Material.PAPER);
                    pi.remove(Material.NAME_TAG);
                    pi.remove(Material.REDSTONE);
                    pi.remove(Material.ITEM_FRAME);
                    cancel();
                }

                if (getLookEntity(player) != null) {

                    Entity entity = getLookEntity(player);

                    if (entity != null) {
                        PlayerInventory pi = player.getInventory();
                        pi.remove(Material.PAPER);
                        pi.remove(Material.NAME_TAG);
                        pi.remove(Material.REDSTONE);
                        pi.remove(Material.ITEM_FRAME);

                        ItemStack newEntityInfoItem = new ItemStack(Material.PAPER);
                        ItemMeta newEntityInfoItemMeta = newEntityInfoItem.getItemMeta();
                        String entityCustomName = entity.getCustomName() != null ? entity.getCustomName() : "none";
                        String newDisplayName = "Left Edit Mode " + " (o)";
                        newEntityInfoItemMeta.setDisplayName(newDisplayName);
                        newEntityInfoItem.setItemMeta(newEntityInfoItemMeta);
                        pi.setItem(8, newEntityInfoItem);

                        ItemStack newEntitySetNameItem2 = new ItemStack(Material.ITEM_FRAME);
                        ItemMeta newEntitySetNameItem2Meta = newEntitySetNameItem2.getItemMeta();
                        newDisplayName = "SetNameTo: " + shopItemEntityName;
                        newEntitySetNameItem2Meta.setDisplayName(newDisplayName);
                        newEntitySetNameItem2.setItemMeta(newEntitySetNameItem2Meta);
                        pi.setItem(0, newEntitySetNameItem2);

                        ItemStack newEntitySetNameItem = new ItemStack(Material.NAME_TAG);
                        ItemMeta newEntitySetNameItemMeta = newEntitySetNameItem.getItemMeta();
                        newDisplayName = "SetNameTo: " + levelupShopOpenModeEntityName;
                        newEntitySetNameItemMeta.setDisplayName(newDisplayName);
                        newEntitySetNameItem.setItemMeta(newEntitySetNameItemMeta);
                        pi.setItem(1, newEntitySetNameItem);

                        ItemStack newEntityRemoveNameItem = new ItemStack(Material.REDSTONE);
                        ItemMeta newEntityRemoveNameItemMeta = newEntityRemoveNameItem.getItemMeta();
                        newDisplayName = "RemoveName: " + entityCustomName;
                        newEntityRemoveNameItemMeta.setDisplayName(newDisplayName);
                        newEntityRemoveNameItem.setItemMeta(newEntityRemoveNameItemMeta);
                        pi.setItem(2, newEntityRemoveNameItem);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private static Entity getLookEntity(Player player) {
        List<Entity> nearbyEntities = player.getNearbyEntities(16, 16, 16);
        Vector playerEye = player.getEyeLocation().toVector();
        Vector playerDirection = player.getLocation().getDirection();

        for (Entity entity : nearbyEntities) {
            if (entity.getType() != EntityType.DROPPED_ITEM) {
                Vector toEntity = entity.getLocation().toVector().subtract(playerEye);
                double angle = playerDirection.angle(toEntity);

                if (angle < 90) {
                    return entity;
                }
            }
        }

        return null;
    }
}
