package tlldos.tll2;

import Comandos.ComandosStaff;
import Comandos.ComandosUsuarios;
import Eventos.*;
import Extras.*;
import Utilidades.Configuration;
import Utilidades.Format;
import Utilidades.Mobs;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tasks.TemperatureTask;

import javax.swing.plaf.LabelUI;
import java.util.Random;

public final class TLL2 extends JavaPlugin implements Listener{
    public World world;
    private Configuration config;
    ReplaceListeners replaceListeners;



    @Override
    public void onEnable() {
        try {
            saveDefaultConfig();
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "_______________________________________________________________________");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD +
                    "  _______ _      _        _______ ___  \n" +
                    " |__   __| |    | |      |__   __|__ \\ \n" +
                    "    | |  | |    | |         | |     ) |\n" +
                    "    | |  | |    | |         | |    / / \n" +
                    "    | |  | |____| |____     | |   / /_ \n" +
                    "    |_|  |______|______|    |_|  |____|");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "THE LAST LIFE T2 >>> " + ChatColor.YELLOW + "TheLastLifeT2Test.jar se cargo correctamente!");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "_______________________________________________________________________");
            world = Bukkit.getWorld("world");
            new Muerte(this);

            cargarEventos();
            //tormentaTick();
            sinofuncionamemato();
            tickTormenta();
            pichaXd();
            getCommand("thelastlife").setExecutor(new ComandosUsuarios(this));
            getCommand("tllstaff").setExecutor(new ComandosStaff(this));

            getCommand("thelastlife").setTabCompleter(new ComandosUsuarios(this));
            getCommand("tllstaff").setTabCompleter(new ComandosStaff(this));

            new TemperatureTask(this).runTaskTimer(this, 0L, 400L);
        } catch (Error e){
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("A OCURRIDO UN ERROR FATAL Y EL PLUGIN NO PUDO SER INICIADO");
            getServer().getConsoleSender().sendMessage("EL SERVER SE AUTO-CERRARA PARA EVITAR ALGUN TIPO DE EXPLOIT");
            getServer().getConsoleSender().sendMessage("ERROR: " + e);
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/stop");
        }
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "El Plugin se deshabilito correctamente!");
    }

    public void cargarEventos(){
        registerListeners(
                new alUsarTotem(this),
                new Comer(this),
                new EnderPearlEvent(this),
                new Dormir(this),
                new GhastExplosion(this),
                new DanoSinEnieEvento(this),
                new PlayerEvents(this),
                new SpawnerListeners(this),
                new SpawnListeners(this),
                new EntityListeners(this),
                new BlastStormListeners(),
                new BlocksListeners(this),
                new MobsTeleports(this),
                new DropsListeners(this),
                new ChatListeners(this),
                new NMSSpawn(this),
                new ReplaceListeners(this),
                new WorldEventsListeners(this),
                new AlEntrar(this)
        );
    }

    private void registerListeners(Listener... listeners){
        for(Listener listener : listeners){
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public void sinofuncionamemato(){
        Bukkit.getScheduler().runTaskTimer(this,()->{
            /*for (World worlds : Bukkit.getWorlds()) {
                for (LivingEntity liv : worlds.getLivingEntities()) {
                    replaceListeners.remplazoMob(liv);
                }
            }*/
            pichaXd();
        },0L,20L);

    }

    public void tickTormenta(){
        if(world.getWeatherDuration() != 0){
            Bukkit.getScheduler().runTaskTimer(this, ()->{
                if(world.isThundering()) {
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 100, 0, true, false, true));
                    });
                }
            },0L, 20L);

        }
    }
    public void pichaXd() {
        Random r = new Random();
        if (Bukkit.getOnlinePlayers().size() < 1) return;
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location l = player.getLocation().clone();
            if (r.nextInt(2) == 1) {
                int pX = (r.nextBoolean() ? -1 : 1) * (r.nextInt(15)) + 15;
                int pZ = (r.nextBoolean() ? -1 : 1) * (r.nextInt(15)) + 15;
                int y = (int) l.getY();

                Block block = l.getWorld().getBlockAt(l.getBlockX() + pX, y, l.getBlockZ() + pZ);
                Block up = block.getRelative(BlockFace.UP);

                if (block.getType() != Material.AIR && up.getType() == Material.AIR) {
                    var vorlolxdxdxd = player.getLocation().getWorld().spawn(block.getLocation(), Creeper.class);
                    Mobs.vortice(vorlolxdxdxd);
                }
            }
            if(hasBloodstainedArmor(player)){
                player.setMaxHealth(28);
            }else{
                player.setMaxHealth(20);
            }

            if(player.hasPotionEffect(PotionEffectType.UNLUCK)){
                if(r.nextInt(10000) == 1){
                    EventosItems.animacion(player,player);
                }
            }
            var data = player.getPersistentDataContainer();
            var dataTemperatura = data.get(new NamespacedKey(this, "temperatura"), PersistentDataType.INTEGER);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&f[&6&lTemperatura&f] &c" + dataTemperatura + "°")));
        }
    }

    public boolean hasBloodstainedArmor(Player p){
        if(p.getInventory().getHelmet() != null && p.getInventory().getChestplate() != null && p.getInventory().getLeggings() != null && p.getInventory().getBoots() != null){
            if(p.getInventory().getHelmet().hasItemMeta() && p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getLeggings().hasItemMeta() && p.getInventory().getBoots().hasItemMeta()){
                if(p.getInventory().getHelmet().getItemMeta().hasCustomModelData() && p.getInventory().getChestplate().getItemMeta().hasCustomModelData() && p.getInventory().getLeggings().getItemMeta().hasCustomModelData() && p.getInventory().getBoots().getItemMeta().hasCustomModelData()){
                    if(p.getInventory().getHelmet().getItemMeta().getCustomModelData() == 8010 && p.getInventory().getChestplate().getItemMeta().getCustomModelData() == 8010 && p.getInventory().getLeggings().getItemMeta().getCustomModelData() == 8010 && p.getInventory().getBoots().getItemMeta().getCustomModelData() == 8010){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
