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
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;

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
        		new CustomItem(SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzEzYjc4YjUyMDc4MWI1OTcwYzRlMTE3Mjc0ZTkzNjNlNjY3MjFlZmQ3YmJlMTk1OWUyNjZiOThlMzc3NTljZSJ9fX0="),
        				"&c原子弹模型 &7- &5主页", "", "&a> 单击打开")
        );
        
        Category acmCategory = new Category(
        		new NamespacedKey(this, "acm_category"),
        		new CustomItem(Material.CHARCOAL, "&c原子弹模型 &7- &a材料", "", "&a> 单击打开")
        );
        
        // Items
        
        SlimefunItemStack diamondPlateIS = new SlimefunItemStack("CB_DIAMOND_PLATE", Material.PAPER,
        		"&a强化钻石板");
        
        SlimefunItemStack containerPieceIS = new SlimefunItemStack("CB_CONTAINER_PIECE", Material.IRON_BLOCK,
        		"&c原子弹外壳", "&7它是用来包裹原子弹核心的");
        
        SlimefunItemStack diamondPlateBlockIS = new SlimefunItemStack("CB_DIAMOND_PLATE_BLOCK", Material.IRON_BLOCK,
        		"&b反射层", "&4请注意反射层的辐射！", "&7千万不要长时间拿在手上！");
        
        SlimefunItemStack atomBombIS = new SlimefunItemStack("CB_NUCLEAR_BOMB", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzEzYjc4YjUyMDc4MWI1OTcwYzRlMTE3Mjc0ZTkzNjNlNjY3MjFlZmQ3YmJlMTk1OWUyNjZiOThlMzc3NTljZSJ9fX0=",
        		"&c&l原子弹模型", "&7快用这个去吓唬你的好基友!");
        
        
        // Recipes
        
        ItemStack[] atomBombRecipe = {
    containerPieceIS,			diamondPlateBlockIS,	containerPieceIS,
    SlimefunItems.CARBONADO,	SlimefunItems.URANIUM,		SlimefunItems.CARBONADO,
    containerPieceIS,			diamondPlateBlockIS,	containerPieceIS
        };
        
        ItemStack[] containerPieceRecipe = {
    diamondPlateIS,		diamondPlateIS,			diamondPlateIS,
    diamondPlateIS,		SlimefunItems.URANIUM,	diamondPlateIS,
    diamondPlateIS,		diamondPlateIS,			diamondPlateIS
        };
        
        ItemStack[] diamondPlateRecipe = {
    new ItemStack(Material.DIAMOND),		new ItemStack(Material.DIAMOND),		new ItemStack(Material.DIAMOND),
    SlimefunItems.URANIUM,	SlimefunItems.REINFORCED_ALLOY_INGOT,	SlimefunItems.URANIUM,
    new ItemStack(Material.DIAMOND),		new ItemStack(Material.DIAMOND),		new ItemStack(Material.DIAMOND)
        };
        
        ItemStack[] diamondPlateBlockRecipe = {
    diamondPlateIS,				diamondPlateIS,							diamondPlateIS,
    SlimefunItems.NEPTUNIUM,	SlimefunItems.REINFORCED_ALLOY_INGOT,	SlimefunItems.NEPTUNIUM,
    diamondPlateIS,				diamondPlateIS,							diamondPlateIS
        };

        /*
         * 4. Registering the Item
         * Now you just have to register the item.
         * RecipeType.ENHANCED_CRAFTING_TABLE refers to the machine in
         * which this item is crafted in.
         * Recipe Types from Slimefun itself will automatically add the recipe to that machine.
         */
        AtomBomb atomBomb = new AtomBomb(acCategory, atomBombIS,
        		RecipeType.ENHANCED_CRAFTING_TABLE, atomBombRecipe);
        atomBomb.register(this);
        
        ContainerPiece containerPiece = new ContainerPiece(acmCategory, containerPieceIS,
        		RecipeType.ENHANCED_CRAFTING_TABLE, containerPieceRecipe);
        containerPiece.register(this);
        
        SlimefunItem diamondPlate = new SlimefunItem(acmCategory, diamondPlateIS,
        		RecipeType.ENHANCED_CRAFTING_TABLE, diamondPlateRecipe);
        diamondPlate.register(this);
        
        DiamondPlateBlock diamondPlateBlock = new DiamondPlateBlock(acmCategory, diamondPlateBlockIS,
        		RecipeType.ARMOR_FORGE, diamondPlateBlockRecipe);
        diamondPlateBlock.register(this);
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

