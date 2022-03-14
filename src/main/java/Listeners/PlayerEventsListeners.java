package Listeners;

import Extras.EventosItems;
import Extras.Items;
import Extras.Sacrificios;
import Utilities.*;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import io.papermc.paper.event.entity.ElderGuardianAppearanceEvent;
import org.bukkit.Material;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import java.util.*;

import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class PlayerEventsListeners implements Listener {

    private final TLL2 plugin;

    public PlayerEventsListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    private final HashMap<UUID, Long> cooldownSacrifice = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(Format.format("&7Daga Ceremonial"))) {
                var data = p.getPersistentDataContainer();
                var inventory = p.getInventory();
                var dataSacrifices = data.get(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER);
                var totalSacrifices = (dataSacrifices == null ? 1 : ++dataSacrifices);

                if (cooldownSacrifice.containsKey(p.getUniqueId())) {
                    if (cooldownSacrifice.get(p.getUniqueId()) > System.currentTimeMillis()) {
                        p.sendMessage(Format.format(String.format("&cTe encuentras en cooldown, espera %d segundo(s).", (cooldownSacrifice.get(p.getUniqueId()) - System.currentTimeMillis()) / 1000)));
                        return;
                    } else
                        cooldownSacrifice.remove(p.getUniqueId());
                }

                if (dataSacrifices != null && totalSacrifices > 5) {
                    p.sendMessage(Format.format("&cLos Dioses rechazan tu sacrificio, has perdido mucha sangre y ya no eres digno para un sacrificio más."));
                    return;
                }

                if (p.getInventory().firstEmpty() == -1) {
                    p.sendMessage(Format.format("&c¡Ofreciste un sacrificio! pero tu inventario esta lleno."));
                }

                data.set(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER, totalSacrifices);

                var areaEffectCloud = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);

                areaEffectCloud.setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.fromBGR(1, 0, 156), 2.0F));
                areaEffectCloud.setColor(Color.RED);
                areaEffectCloud.setDuration(40);
                areaEffectCloud.setRadius(1.3F);

                p.damage(0.2D);
                Sacrificios.start(p);
                p.sendMessage(Format.format(String.format("&cHaz hecho un sacrificio #%s", totalSacrifices)));

                cooldownSacrifice.put(p.getUniqueId(), System.currentTimeMillis() + (10 * 1000));
                p.getPersistentDataContainer().set(Utils.key("NEGATIVE_HEALTH"), PersistentDataType.INTEGER, p.getPersistentDataContainer().get(Utils.key("NEGATIVE_HEALTH"), PersistentDataType.INTEGER) + 2);
                giveReward(p);
                }
            }

            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (p.getInventory().getItemInMainHand().equals(Items.crystalHeart())) {
                    if (p.hasCooldown(Material.RED_DYE)) {
                        event.setCancelled(true);
                    } else {
                        EventosItems.crystalHealthup(p);
                        p.getInventory().removeItem(Items.crystalHeart());
                        p.getInventory().remove(Material.RED_DYE);
                    }
                }
            }
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (p.getInventory().getItemInMainHand().equals(Items.varaDis())) {
                    if (p.hasCooldown(Material.STICK)) {
                        event.setCancelled(true);
                    } else {
                        EventosItems.discordxd(p);
                        p.setCooldown(Material.STICK, 12000);
                    }
                }
            }

            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (p.getInventory().getItemInMainHand().equals(Items.termometroItem())) {
                    try {
                        if (p.hasCooldown(Material.AMETHYST_SHARD)) {
                            event.setCancelled(true);
                        } else {
                            EventosItems.temperatura(p);
                            p.setCooldown(Material.AMETHYST_SHARD, 60);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Warn.Mutant(e);
                    }
                }
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4455) {
                        if (p.hasCooldown(Material.PRISMARINE_CRYSTALS)) {
                            event.setCancelled(true);
                        } else {
                            EventosItems.totemrestorerEvent(p, plugin);
                            p.setCooldown(Material.PRISMARINE_CRYSTALS, 400);
                            //alguien puede hacer que al usar esto se saque 1 totem restorer si es que esta stackeado? gracias
                            p.getInventory().removeItem(new ItemStack(Material.PRISMARINE_CRYSTALS, 1));
                        }
                    }
                }
                if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4455) {
                        PersistentDataContainer data = p.getPersistentDataContainer();
                        int i = data.get(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER);
                        p.sendMessage(Format.PREFIX, format("&7&l¡Tienes &e&l" + i + "% &7&lporcentaje de Totems!"));
                    }
                }


            /*if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                if((p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_AXE)) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.TELEPHATY))){
                    ItemStack pickaxe = p.getInventory().getItemInMainHand();
                    List<String> lore;
                    if(pickaxe.getItemMeta().hasLore()){
                        lore = pickaxe.getLore();
                    }else{
                        lore = new ArrayList<>();
                    }
                    lore.add(format("&6Encantamiento Ancestral: &eTelephaty"));
                    pickaxe.addUnsafeEnchantment(CustomEnchants.TELEPHATY, 1);
                    pickaxe.setLore(lore);
                    p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));
                    p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                    p.getInventory().getItemInOffHand().setType(Material.AIR);
                }else if(p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_PICKAXE)  && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.SMELTING_TOUCH))){
                    ItemStack pickaxx = p.getInventory().getItemInMainHand();
                    List<String> lorez;
                    if(pickaxx.getItemMeta().hasLore()){
                        lorez = pickaxx.getLore();
                    }else{
                        lorez = new ArrayList<>();
                    }
                    lorez.add(format("&6Encantamiento Ancestral: &eSmelting Touch"));
                    pickaxx.addUnsafeEnchantment(CustomEnchants.SMELTING_TOUCH, 1);
                    pickaxx.setLore(lorez);
                    p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));
                    p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                    p.getInventory().getItemInOffHand().setType(Material.AIR);
                }else if(p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)  && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.REVENGE))){
                    ItemStack pickaxe3 = p.getInventory().getItemInMainHand();
                    List<String> lorve;
                    if(pickaxe3.getItemMeta().hasLore()){
                        lorve = pickaxe3.getLore();
                    }else{
                        lorve  = new ArrayList<>();
                    }
                    lorve.add(format("&6Encantamiento Ancestral: &eRevenge"));
                    pickaxe3.addUnsafeEnchantment(CustomEnchants.REVENGE, 1);
                    pickaxe3.setLore(lorve);
                    p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));
                    p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                    p.getInventory().getItemInOffHand().setType(Material.AIR);
                }else if(p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)  && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.CRITICAL_HIT))) {
                    ItemStack pickax43 = p.getInventory().getItemInMainHand();
                    List<String> lorvez;
                    if(pickax43.getItemMeta().hasLore()){
                        lorvez = pickax43.getLore();
                    }else{
                        lorvez= new ArrayList<>();
                    }
                    lorvez.add(format("&6Encantamiento Ancestral: &eCritical Hit"));
                    pickax43.addUnsafeEnchantment(CustomEnchants.CRITICAL_HIT, 1);
                    pickax43.setLore(lorvez);
                    p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));
                    p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                    p.getInventory().getItemInOffHand().setType(Material.AIR);
                } else if (p.getInventory().getItemInMainHand().getType().equals(Material.BOW) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.BULLSEYE))) {
                    List<String> lorvez;
                    ItemStack pickax43 = p.getInventory().getItemInMainHand();
                    if(pickax43.getItemMeta().hasLore()){
                        lorvez = pickax43.getLore();
                    }else{
                        lorvez= new ArrayList<>();
                    }
                    lorvez.add(format("&6Encantamiento Ancestral: &eBullsEye"));
                    pickax43.addUnsafeEnchantment(CustomEnchants.BULLSEYE, 1);
                    pickax43.setLore(lorvez);
                    p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));
                    p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                    p.getInventory().getItemInOffHand().setType(Material.AIR);
                }
            }*/

        }
    }


    private void giveReward(Player player){
        var random = new Random();
        var randomInt = random.nextInt(100)+1;

        var data = player.getPersistentDataContainer();

        if (randomInt > 1 && randomInt <= 20){
            Bukkit.broadcast(Component.text(Format.format(String.format("&bEl jugador %s ha subido un nivel.", player.getName()))));

            player.giveExp(400);

        }else if (randomInt >= 20 && randomInt <= 45){
            Bukkit.broadcast(Component.text(Format.format(String.format("&7El jugador %s recibió una BloodStone extra.", player.getName()))));

            player.getInventory().addItem(Items.bloodShard());
        } else if (randomInt >= 45 && randomInt <= 70){
            Bukkit.broadcast(Component.text(Format.format(String.format("&7El jugador %s recibió 10 piedras de diamante.", player.getName()))));

            player.getInventory().addItem(new ItemStack(Material.DIAMOND, 10));
        } else if (randomInt >= 70 && randomInt <= 80) {
            Bukkit.broadcast(Component.text(Format.format(String.format("&bDentro de las profundidades... %s has conseguido una misteriosa manzana.", player.getName()))));

            player.getInventory().addItem(Items.crystalApple(15));
        } else if (randomInt >= 80 && randomInt <= 85) {
            Bukkit.broadcast(Component.text(Format.format(String.format("&cLas llamas del infierno han otorgado su fuerza al jugador %s.", player.getName()))));

            player.getInventory().addItem(Items.brimStone());
        } else if (randomInt >= 85 && randomInt <= 90) {
            Bukkit.broadcast(Component.text(Format.format(String.format("&cEl jugador %s creo un Anillo Misterioso de su propia sangre...", player.getName()))));

            player.getInventory().addItem(Items.ringOfAbaddon());
        } else if (randomInt >= 90 && randomInt <= 95 ) {
            Bukkit.broadcast(Component.text(Format.format(String.format("&cEl jugador %s recibió la bendición de sangre por su sacrificio...", player.getName()))));

            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + 1.0D);
            player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(player.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() + 1.0D);
        } else if (randomInt >= 95 && randomInt < 99) {
            Bukkit.broadcast(Component.text(Format.format(String.format("&cEl sacrificio del jugador %s no fue digno para una recompensa...", player.getName()))));
        } else {
            Bukkit.getLogger().info("qpro");
        }

        player.getInventory().addItem(Items.bloodShard());
    }

    @EventHandler
    public void tormentaDesgastar(PlayerItemDamageEvent e){
        if(e.getPlayer().getWorld().isThundering()){
            e.setDamage(e.getDamage() * 2);
        }
    }

    @EventHandler
    public void riptideFallo(PlayerRiptideEvent e){
        //TODO OK NO PUDE HACER QUE EL EVENTO SE CANCELE RIP XD
        if(e.getPlayer().getWorld().isThundering()){
            int tridentechance = new Random().nextInt(100);
            if(tridentechance > 90){
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,400,0,false,false,false));
            }
        }
    }

    @EventHandler
    public void aldeanosnoNether(InventoryClickEvent e){
        if(e.getWhoClicked().getWorld().getEnvironment() == World.Environment.NETHER){
            if(e.getClickedInventory() instanceof Merchant){
                e.setCancelled(true);
                e.getWhoClicked().sendMessage(PREFIX,format("&c&lEl Aldeano se Niega a Tradear!"));
            }
        }
    }

    /*
    @EventHandler
    public void hambreAgotar(EntityExhaustionEvent e) {
        e.setExhaustion(e.getExhaustion() * 2);
    }
     */

    /*
    @EventHandler
    public void elderGuardianXd(ElderGuardianAppearanceEvent event){
        var p = event.getAffectedPlayer();
        p.setRemainingAir(0);
    }

     */



    public boolean isAchievedInSacrifice(PersistentDataContainer data, String value){
        return data.get(new NamespacedKey(plugin, "sacrifices_"+value), PersistentDataType.INTEGER) != 1;
    }


    public boolean isATotemRestorer(Player p){
        if(p.getInventory().getItemInMainHand() != null || p.getInventory().getItemInOffHand() != null){
            if(p.getInventory().getItemInMainHand().hasItemMeta() || p.getInventory().getItemInOffHand().hasItemMeta()){
                if(p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() || p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
                    return p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4455 || p.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 4455;
                }
            }
        }
        return false;
    }

    public boolean hasCustomModelData(Player p){
        return ((p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) || (p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()));
    }

}
