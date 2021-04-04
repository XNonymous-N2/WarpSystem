package de.xnonymous.warpsystem.inventorys;

import de.xnonymous.warpsystem.Main;
import de.xnonymous.warpsystem.utils.ItemBuilder;
import de.xnonymous.warpsystem.utils.ListHandler;
import de.xnonymous.warpsystem.warp.Warp;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class WarpMenu implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("warpmenü")
            .provider(new WarpMenu())
            .manager(Main.getInstance().getInventoryManager())
            .size(6, 9)
            .title("§0Warp Menü")
            .closeable(true).build();

    @Override
    public void init(Player player, InventoryContents inventoryContents) {
        Pagination pagination = inventoryContents.pagination();

        ArrayList<Warp> warps = Main.getInstance().getWarpManager().getWarps();


        ClickableItem[] items = new ClickableItem[warps.size()];

        for (int i = 0; i < items.length; i++) {
            int finalI = i;
            items[i] = ClickableItem.of(ItemBuilder.builder().material(Material.PAPER).displayName(warps.get(i).getName()).build().toItemStack(), event -> {
                if (!event.getWhoClicked().hasPermission("warpsystem.no.cooldown"))
                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> event.getWhoClicked().teleport(warps.get(finalI).getLocation()), 20 * 3);
                else
                    event.getWhoClicked().teleport(warps.get(finalI).getLocation());
            });
        }

        pagination.setItems(items);
        pagination.setItemsPerPage(4 * 9);

        pagination.addToIterator(inventoryContents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 0).allowOverride(false));

        while (inventoryContents.firstEmpty().isPresent()) {
            inventoryContents.set(inventoryContents.firstEmpty().get(), ClickableItem.empty(ItemBuilder.builder().material(Material.STAINED_GLASS_PANE)
                    .color(14).build().toItemStack()));
        }
        inventoryContents.fillRow(0, ClickableItem.empty(ItemBuilder.builder().material(Material.STAINED_GLASS_PANE)
                .color(15).build().toItemStack()));
        inventoryContents.fillRow(5, ClickableItem.empty(ItemBuilder.builder().material(Material.STAINED_GLASS_PANE)
                .color(15).build().toItemStack()));

        inventoryContents.set(5, 3, ClickableItem.of(ItemBuilder.builder()
                        .material(Material.ARROW)
                        .displayName("§eVorherige Seite").build().toItemStack(),
                e -> INVENTORY.open(player, pagination.previous().getPage())));
        inventoryContents.set(5, 5, ClickableItem.of(ItemBuilder.builder()
                        .material(Material.ARROW)
                        .displayName("§eNächste Seite").build().toItemStack(),
                e -> INVENTORY.open(player, pagination.next().getPage())));
        inventoryContents.set(5, 4, ClickableItem.of(ItemBuilder.builder()
                        .material(Material.ARROW)
                        .displayName("§4Schließen")
                        .lores(ListHandler.getList("§7", "§eSeite: " + (pagination.getPage() + 1))).build().toItemStack(),
                e -> e.getWhoClicked().closeInventory()));
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }
}
