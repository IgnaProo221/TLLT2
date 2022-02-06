package Eventos;

import Extras.EventosItems;
import Extras.Items;
import Extras.Sacrificios;
import Utilidades.Format;
import Utilidades.Warn;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import io.papermc.paper.event.entity.ElderGuardianAppearanceEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import java.util.HashMap;
import java.util.Random;

public class PlayerEvents implements Listener {

    private final TLL2 plugin;

    public PlayerEvents(TLL2 plugin){
        this.plugin = plugin;
    }

    private final HashMap<Player, Integer> cooldownSacrifice = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(Format.format("&7Daga Ceremonial"))) {
                Sacrificios.start(p);
                var data = p.getPersistentDataContainer();
                var inventory = p.getInventory();
                var dataSacrifices = data.get(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER);

                var totalSacrifices = (dataSacrifices == null ? 1 : ++dataSacrifices);

                if(dataSacrifices != null && totalSacrifices > 5){
                    p.sendMessage(Format.format("&cLos Dioses rechazan tu sacrificio, ya has perdido mucha sangre y ya no eres digno."));
                    return;
                }

                if(p.getInventory().firstEmpty() == -1){
                    p.sendMessage(Format.format("&cOfreciste un sacrificio pero tu inventario esta lleno."));
                    return;
                }

                data.set(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER, totalSacrifices);

                var areaEffectCloud = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);

                areaEffectCloud.setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.fromBGR(1, 0, 156), 2.0F));
                areaEffectCloud.setColor(Color.RED);
                areaEffectCloud.setDuration(40);
                areaEffectCloud.setRadius(1.3F);

                p.damage(0.2D);
                p.sendMessage(Format.format(String.format("&cHaz hecho tu sacrificio #%s", totalSacrifices)));
                p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()-1.0D);
                giveReward(p);
            }
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(p.getInventory().getItemInMainHand().equals(Items.crystalHeart())) {
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
            if(p.getInventory().getItemInMainHand().equals(Items.varaDis())){
                if(p.hasCooldown(Material.STICK)){
                    event.setCancelled(true);
                }else{
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
            if (p.getInventory().getItemInMainHand().equals(Items.totemRestorer())) {
                try {
                    if (p.hasCooldown(Material.PRISMARINE_CRYSTALS)) {
                        event.setCancelled(true);
                    } else {
                        p.getPersistentDataContainer().set(new NamespacedKey(plugin, "TOTEM_BAR"), PersistentDataType.INTEGER, 100);
                        p.setCooldown(Material.PRISMARINE_CRYSTALS, 24000);
                        p.getInventory().removeItem(new ItemStack(Material.PRISMARINE_CRYSTALS, 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Warn.Mutant(e);
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        var player = e.getPlayer();

        player.getInventory().addItem(Items.createDaga());
    }

    private void giveReward(Player player){
        var random = new Random();
        var randomInt = random.nextInt(100)+1;

        var data = player.getPersistentDataContainer();

        if (randomInt > 1 && randomInt <= 20){
            Bukkit.broadcast(Component.text(Format.format(String.format("&bEl jugador %s ha subido un nivel.", player.getName()))));

            player.giveExp(400);

        }else if (randomInt >= 20 && randomInt <= 45){
            Bukkit.broadcast(Component.text(Format.format(String.format("&7El jugador %s recibió BloodStone extra.", player.getName()))));

            player.getInventory().addItem(Items.bloodShard());
        } else if (randomInt >= 45 && randomInt <= 70){
            Bukkit.broadcast(Component.text(Format.format(String.format("&7El jugador %s recibió 10 piedras de diamante.", player.getName()))));

            player.getInventory().addItem(new ItemStack(Material.DIAMOND, 10));
        } else if (randomInt >= 70 && randomInt <= 80) {
            Bukkit.broadcast(Comp   onent.text(Format.format(String.format("&bDentro de las profundidades... %s a conseguido una misteriosa manzana.", player.getName()))));

            player.getInventory().addItem(Items.crystalApple(15));
        } else if (randomInt >= 80 && randomInt <= 85) {
            Bukkit.broadcast(Component.text(Format.format(String.format("&cLas llamas del infierno han otorgado su fuerza a %s", player.getName()))));

            player.getInventory().addItem(Items.brimStone());
        } else if (randomInt >= 85 && randomInt <= 90) {
            Bukkit.broadcast(Component.text(Format.format(String.format("&cEl jugador %s creo un Anillo Misterioso de su propia sangre...", player.getName()))));

            player.getInventory().addItem(Items.ringOfAbaddon());
        } else if (randomInt >= 90 && randomInt <= 95 ) {
            Bukkit.broadcast(Component.text(Format.format(String.format("&cEl jugador %s recibió la bendición de sangre por su sacrificio...", player.getName()))));

            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + 2.0D);
        } else if (randomInt >= 95 && randomInt < 99) {
            Bukkit.broadcast(Component.text(Format.format(String.format("&cEl sacrificio del jugador %s no fue digno de una recompensa...", player.getName()))));
        } else {
            Bukkit.getLogger().info("qpro");
        }

        player.getInventory().addItem(Items.bloodShard());
    }

    @EventHandler
    public void hambreAgotar(EntityExhaustionEvent e) {
        e.setExhaustion(e.getExhaustion() * 2);
    }

    @EventHandler
    public void elderGuardianXd(ElderGuardianAppearanceEvent event){
        var p = event.getAffectedPlayer();
        p.setRemainingAir(0);
    }

    public boolean isAchievedInSacrifice(PersistentDataContainer data, String value){
        return data.get(new NamespacedKey(plugin, "sacrifices_"+value), PersistentDataType.INTEGER) != 1;
    }
}
