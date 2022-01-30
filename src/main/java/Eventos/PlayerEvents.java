package Eventos;

import Extras.EventosItems;
import Extras.Items;
import Extras.Sacrificios;
import Utilidades.Format;
import Utilidades.ItemBuilder;
import Utilidades.Warn;
<<<<<<< HEAD
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
=======
import io.papermc.paper.event.entity.ElderGuardianAppearanceEvent;
import org.bukkit.Material;
>>>>>>> 9cab10ae0b401cb592c3f84ed1cdd85ae5d993b1
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
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
            if (p.getInventory().getItemInMainHand().equals(Items.createDaga())) {
                Sacrificios.start(p);
            }
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(p.getInventory().getItemInMainHand().equals(Items.crystalHeart())){
                if(p.hasCooldown(Material.RED_DYE)){
                    event.setCancelled(true);
                }else{
                    EventosItems.crystalHealthup(p);
                    p.getInventory().removeItem(Items.crystalHeart());
                    p.getInventory().remove(Material.RED_DYE);
                }
            }else if(p.getInventory().getItemInMainHand().equals(Items.createDaga())){
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
                        EventosItems.totemrestorerEvent(p);
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

        if(randomInt == 10){

        }else{
            Bukkit.broadcast(Component.text(Format.format(String.format("&cEl sacrificio del jugador %s no fue digno de una recompensa...", player.getName()))));
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
}
