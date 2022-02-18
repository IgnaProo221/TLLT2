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
import tasks.TemperatureBlocks;
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
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "THE LAST LIFE T2 >>> " + ChatColor.YELLOW + "¡TheLastLifeT2Test.jar se cargo correctamente!");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "_______________________________________________________________________");
            world = Bukkit.getWorld("world");
            new Muerte(this);

            cargarEventos();
            //tormentaTick();
            sinofuncionamemato();
            tickTormenta();
            pichaXd();
            dementetemperatura();
            getCommand("thelastlife").setExecutor(new ComandosUsuarios(this));
            getCommand("tllstaff").setExecutor(new ComandosStaff(this));

            getCommand("thelastlife").setTabCompleter(new ComandosUsuarios(this));
            getCommand("tllstaff").setTabCompleter(new ComandosStaff(this));

            new TemperatureTask(this).runTaskTimer(this, 0L, 400L);
            new TemperatureBlocks(this).runTaskTimer(this,0L,200L);
        } catch (Error e){
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("HA OCURRIDO UN ERROR FATAL Y EL PLUGIN NO PUDO SER INICIADO");
            getServer().getConsoleSender().sendMessage("EL SERVER SE AUTO-CERRARA PARA EVITAR ALGÚN TIPO DE EXPLOIT");
            getServer().getConsoleSender().sendMessage("ERROR: " + e);
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/stop");
        }
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "¡El Plugin se deshabilito correctamente!");
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

    public void dementetemperatura(){
        Bukkit.getScheduler().runTaskTimer(this,()->{
        if(Bukkit.getOnlinePlayers().size() < 1)return;
        for(Player player : Bukkit.getOnlinePlayers()){
            var data = player.getPersistentDataContainer();
            var dataTemperatura = data.get(new NamespacedKey(this, "temperatura"), PersistentDataType.INTEGER);
            if(dataTemperatura >= 220 || dataTemperatura <= -180){
                demente(player, player);
            }
        }
        },0L,1200L);
    }
    public void pichaXd() {
        Random r = new Random();
        if (Bukkit.getOnlinePlayers().size() < 1) return;
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location l = player.getLocation().clone();
            if (r.nextInt(2) == 1) {
                int pX = (r.nextBoolean() ? -1 : 1) * (r.nextInt(25)) + 15;
                int pZ = (r.nextBoolean() ? -1 : 1) * (r.nextInt(25)) + 15;
                int y = (int) l.getY();

                Block block = l.getWorld().getBlockAt(l.getBlockX() + pX, y, l.getBlockZ() + pZ);
                Block up = block.getRelative(BlockFace.UP);

                if (block.getType() != Material.AIR && up.getType() == Material.AIR && !(block.isLiquid())) {
                    spawnMobNaturally(player,block);
                }
            }
            if(hasBloodstainedArmor(player)){
                player.setMaxHealth(32);
            }else{
                player.setMaxHealth(20);
            }
            if (hasExoArmor(player)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100,0, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,100,0, true, false,true));
            }

            var data = player.getPersistentDataContainer();
            var dataTemperatura = data.get(new NamespacedKey(this, "temperatura"), PersistentDataType.INTEGER);
            //Hipertermia
            if(dataTemperatura >= 120 && dataTemperatura <= 180) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &6&l" + dataTemperatura + "° &7|| &4¡Hipertermia I!")));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 0, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300, 4, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 300, 2, true, false, true));
                player.setFireTicks(200);
            }else if(dataTemperatura >= 180 && dataTemperatura <= 220) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &6&l" + dataTemperatura + "° &7|| &4¡Hipertermia II!")));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 3, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300, 9, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 300, 4, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 2, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 300, 2, true, false, true));
                player.setFireTicks(200);
            }else if(dataTemperatura >= 220) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &6&l" + dataTemperatura + "° &7|| &4¡Hipertermia III! &7|| &4&l¡Demente!")));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 9, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300, 9, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 300, 9, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 3, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 2, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 300, 9, true, false, true));
                player.setFireTicks(200);



                //Hipotermia
            }else if(dataTemperatura <= -70 && dataTemperatura >= -120) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &b&l" + dataTemperatura + "° &7|| &b¡Hipotermia I!")));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 300, 4, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 0, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 300, 2, true, false, true));
                player.setFreezeTicks(204);
            }else if(dataTemperatura <= -120 && dataTemperatura >= -180) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &b&l" + dataTemperatura + "° &7|| &b¡Hipotermia II!")));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 300, 9, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 2, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 300, 4, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 2, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300, 7, true, false, true));
                player.setFreezeTicks(204);
            }else if(dataTemperatura <= -180){
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &b&l" + dataTemperatura + "° &7|| &b¡Hipotermia III! &7|| &4&l¡Demente!")));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,300,9, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 2, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 300, 4, true,false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 2, true, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300, 7, true, false, true));
                player.setFreezeTicks(204);
            }else{
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &c" + dataTemperatura + "°")));
            }
        }
    }


    public void demente(Player p, Player players){
        int clase = new Random().nextInt(12);
        if(clase == 1){
            EventosItems.animacion(p,players);
        }else if(clase == 2){
            p.playEffect(EntityEffect.TOTEM_RESURRECT);
        }else if(clase == 3){
            p.sendMessage(Format.format("&7&o??? te susurra: ¿Hola?"));
        }else if(clase == 4){
            p.playEffect(EntityEffect.GUARDIAN_TARGET);
        }else if(clase == 5){
            p.playSound(p.getLocation(),Sound.ENTITY_CREEPER_PRIMED,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 6){
            p.playSound(p.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 7){
            p.playSound(p.getLocation(),Sound.ENTITY_GHAST_SHOOT,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 8){
            p.playSound(p.getLocation(),Sound.ENTITY_SKELETON_SHOOT,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 9){
            p.playSound(p.getLocation(),Sound.ENTITY_ZOMBIE_AMBIENT,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 10){
            p.playSound(p.getLocation(),Sound.ENTITY_BLAZE_AMBIENT,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 11){
            p.playSound(p.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,SoundCategory.MASTER,10.0F,1.0F);
        }else{
            p.sendMessage(Format.format("&7&o??? te susurra: ¿Me pasas las Coordenadas de tu Base?"));
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


    public boolean hasExoArmor(Player p){
        if(p.getInventory().getHelmet() != null && p.getInventory().getChestplate() != null && p.getInventory().getLeggings() != null && p.getInventory().getBoots() != null){
            if(p.getInventory().getHelmet().hasItemMeta() && p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getLeggings().hasItemMeta() && p.getInventory().getBoots().hasItemMeta()){
                if(p.getInventory().getHelmet().getItemMeta().hasCustomModelData() && p.getInventory().getChestplate().getItemMeta().hasCustomModelData() && p.getInventory().getLeggings().getItemMeta().hasCustomModelData() && p.getInventory().getBoots().getItemMeta().hasCustomModelData()){
                    if(p.getInventory().getHelmet().getItemMeta().getCustomModelData() == 47399 && p.getInventory().getChestplate().getItemMeta().getCustomModelData() == 47399 && p.getInventory().getLeggings().getItemMeta().getCustomModelData() == 47399 && p.getInventory().getBoots().getItemMeta().getCustomModelData() == 47399){
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

    public void spawnMobNaturally(Player player, Block block){
        int mobtype = new Random().nextInt(6);
        if(mobtype == 1){
            var vorlolxdxdxd = player.getLocation().getWorld().spawn(block.getLocation(), Creeper.class);
            Mobs.vortice(vorlolxdxdxd);
        }else if(mobtype == 2){
            var pillager = player.getLocation().getWorld().spawn(block.getLocation(),Pillager.class);
        }else if(mobtype == 3){
            var withers = player.getLocation().getWorld().spawn(block.getLocation(),WitherSkeleton.class);
            Mobs.blightedWitherSkeleton(withers);
        }else if(mobtype == 4){
            var ghastlol = player.getLocation().getWorld().spawn(block.getLocation(),Ghast.class);
            Mobs.riftedGhast(ghastlol);
        }else if(mobtype == 5){
            var phantomxd = player.getLocation().getWorld().spawn(block.getLocation(),Phantom.class);
            Mobs.blightedPhantom(phantomxd);
        }else{
            var blazelol = player.getLocation().getWorld().spawn(block.getLocation(),Blaze.class);
        }
    }

}
