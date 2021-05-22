/*
 * ADD LATER:
 * SCYTHES
 * POISONED SWORDS (gives poison effect)
 * ANTI-WITHER ARMOR
 * HAZMAT PROTECTS AGAINST BOMB
 * ICE ARMOR
 * VOLTAGE TRAP (turns joules into area damage)
 * CHAT OUTPUT DEVICE (Personal and public, public chatbot takes much more energy to prevent spam)
 * 
 */

package tk.dexie.slimefuncombat;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class Addon extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // Read something from your config.yml
        @SuppressWarnings("unused")
		Config cfg = new Config(this);

//        if (cfg.getBoolean("options.auto-update")) {
//            // You could start an Auto-Updater for example
//        }
        
        Category acCategory = new Category(
        		new NamespacedKey(this, "ac_category"),
        		new CustomItem(Material.DIAMOND_SWORD, "&cAdvanced Combat", "", "&a> Click to open")
        );
        
        Category acmCategory = new Category(
        		new NamespacedKey(this, "acm_category"),
        		new CustomItem(Material.COAL, "&cAC Resources", "", "&a> Click to open")
        );
        
        
        SlimefunItemStack atomBombItem = new SlimefunItemStack("NUCLEAR_BOMB", Material.IRON_BLOCK, "&c&lNuclear Bomb", "&cHahaha noob go boom");
        ItemStack[] atomBombRecipe = {
    new ItemStack(Material.EMERALD),	null,								new ItemStack(Material.EMERALD),
    null,								SlimefunItems.CARBONADO,			null,
    new ItemStack(Material.EMERALD),	null,								new ItemStack(Material.EMERALD) };

        /*
         * 4. Registering the Item
         * Now you just have to register the item.
         * RecipeType.ENHANCED_CRAFTING_TABLE refers to the machine in
         * which this item is crafted in.
         * Recipe Types from Slimefun itself will automatically add the recipe to that machine.
         */
        AtomBomb atomBomb = new AtomBomb(acCategory, atomBombItem,
        		RecipeType.ENHANCED_CRAFTING_TABLE, atomBombRecipe);
        atomBomb.register(this);
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

}
