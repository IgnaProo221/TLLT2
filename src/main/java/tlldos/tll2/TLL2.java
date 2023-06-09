package tlldos.tll2;

import Comandos.ComandosStaff;
import Comandos.ComandosUsuarios;
import Listeners.*;
import Extras.*;
import Utilities.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import player.CustomPlayer;
import player.PlayerData;
import tasks.SpawnTask;
import tasks.TemperatureBlocks;
import tasks.TemperatureTask;
import tasks.TemperatureY;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public final class TLL2 extends JavaPlugin implements Listener{

    private ArrayList<Location> locations;

    public World world;
    //private Configuration blockConfig;
    private ReplaceListeners replaceListeners;
    public static TLL2 instance;
    public static boolean mantenimiento = true;

    @Override
    public void onEnable() {
        try {
            instance = this;
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
            locations = new ArrayList<>();

           /* if(!new File(getDataFolder() + "/blocks.yml").exists())
                blockConfig = new Configuration(this, "blocks", ".yml");
              if(!new File(getDataFolder() + "/blocks.yml").exists()){
                YamlConfiguration blocksConf = new YamlConfiguration();
                blocksConf.options().copyDefaults(true);

            }

            if(blockConfig.getConfigurationSection("blocks") != null) {
                for (String location : blockConfig.getConfigurationSection("blocks").getKeys(false)) {
                    //Bukkit.getLogger().info(location);
                }
            }

            */

            CustomEnchants.register();
            new DeathListeners(this);

            cargarEventos();
            tick();
            tickTormenta();
            dementetemperatura();
            maestriaLol();
            getCommand("thelastlife").setExecutor(new ComandosUsuarios(this));
            getCommand("tllstaff").setExecutor(new ComandosStaff(this));

            getCommand("thelastlife").setTabCompleter(new ComandosUsuarios(this));
            getCommand("tllstaff").setTabCompleter(new ComandosStaff(this));

            new Teams();

            new TemperatureTask(this).runTaskTimer(this, 0L, 2400L);
            new TemperatureBlocks(this).runTaskTimer(this,0L,200L);
            //QUITE ESTO A PETICION DE LECHUGA POR EL ULTIMO DIA
            //new TemperatureY(this).runTaskTimer(this,0L,1200);
            //NO USEN ESTO FELIPE YA LO TIENE
            //new SpawnTask(this).runTaskTimer(this,0L,200);
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
            //esta cosa no funciona xd xd xd xd xd xd xd
            Bukkit.getScheduler().runTaskLater(this,()->{
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
        },200L);
        }
    }

    @Override
    public void onDisable() {
        /*if(getBlockConfig() != null) {
            getBlockConfig().set("blocks", locations);


            try {
                getBlockConfig().save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

         */

        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "¡El Plugin se deshabilito correctamente!");
    }


    public static TLL2 getInstance() {
        return instance;
    }

    public void cargarEventos(){
        registerListeners(
                new TotemListeners(this),
                new EatListeners(this),
                new EnderPearlListeners(this),
                new SleepListeners(this),
                new ExplosionListeners(this),
                new DamageListeners(this),
                new PlayerEventsListeners(this),
                new SpawnerListeners(this),
                new SpawnListeners(this),
                new EntityListeners(this),
                new BlastStormListeners(),
                new BlocksListeners(this),
                new MobsTeleports(this),
                new DropsListeners(this),
                new BossesListeners(this),
                new ChatListeners(this),
                //new NMSSpawn(this),
                new ReplaceListeners(this),
                //new WorldEventsListeners(this),
                new JoinListeners(this),
                new RPListeners(this),
                new MaestriaExp(this),
                new BowListeners(this), 
                new CustomEnchants()
        );
    }

    private void registerListeners(Listener... listeners){
        for(Listener listener : listeners){
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public void tick(){
        /*for (World worlds : Bukkit.getWorlds()) {
                for (LivingEntity liv : worlds.getLivingEntities()) {
                    replaceListeners.remplazoMob(liv);
                }
            }*/
        Bukkit.getScheduler().runTaskTimer(this, this::pichaXd,0L,20L);

    }

    public void maestriaLol(){
        Bukkit.getScheduler().runTaskTimer(this,()->{
        if(Bukkit.getOnlinePlayers().size() < 1)return;
        for(Player p : Bukkit.getOnlinePlayers()){

            PlayerData data = CustomPlayer.fromName(p.getName()).getData();
            //var level10 = data.get(new NamespacedKey(this,"reachedlvl10"),PersistentDataType.INTEGER);
            //var level20 = data.get(new NamespacedKey(this,"reachedlvl20"),PersistentDataType.INTEGER);
            //var level30 = data.get(new NamespacedKey(this,"reachedlvl30"),PersistentDataType.INTEGER);


            if(data.hasReachedLevel10()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 0, true, false, true));
            }
            if(data.hasReachedLevel20()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 0, true, false, true));
            }
            if (data.hasReachedLevel30()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,200,0,true,false,true));
            }
        }
        },0L,20L);
    }

    public void tickTormenta(){
        if(world.getWeatherDuration() != 0){
            Bukkit.getScheduler().runTaskTimer(this, ()->{
                if(world.isThundering()) {
                    Player randomplayer = (Player) Bukkit.getOnlinePlayers().toArray()[new Random().nextInt(Bukkit.getOnlinePlayers().size())];
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        demente(randomplayer,player);
                    });
                }
            },0L, 1200L);

        }
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void dementetemperatura(){
        Bukkit.getScheduler().runTaskTimer(this,()->{
        if(Bukkit.getOnlinePlayers().size() < 1)return;
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.getGameMode() == GameMode.SURVIVAL) {
                var data = CustomPlayer.fromName(player.getName()).getData();
                var dataTemperatura = data.getTemperature();
                Player randomplayer = (Player) Bukkit.getOnlinePlayers().toArray()[new Random().nextInt(Bukkit.getOnlinePlayers().size())];
                ////var dataTemperatura = data.get(new NamespacedKey(this, "temperatura"), PersistentDataType.INTEGER);
                if (dataTemperatura >= 220 || dataTemperatura <= -180) {
                    demente(randomplayer,player);
                }
            }
        }
        },0L,600L);
    }

    public void pichaXd() {
        Random r = new Random();
        if (Bukkit.getOnlinePlayers().size() < 1) return;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                Location l = player.getLocation().clone();
                PlayerData data = CustomPlayer.fromName(player.getName()).getData();

                double health = 20;
                /*if (r.nextInt(2) == 1) {
                    int pX = (r.nextBoolean() ? -1 : 1) * (r.nextInt(25)) + 15;
                    int pZ = (r.nextBoolean() ? -1 : 1) * (r.nextInt(25)) + 15;
                    int y = (int) l.getY();

                    Block block = l.getWorld().getBlockAt(l.getBlockX() + pX, y, l.getBlockZ() + pZ);
                    Block up = block.getRelative(BlockFace.UP);

                    if (block.getType() != Material.AIR && up.getType() == Material.AIR && !(block.isLiquid() && !(block.isSolid()) && player.getWorld().getEnvironment() == World.Environment.NORMAL)) {
                        spawnMobNaturally(player, block);
                    }
                }*/
                if(player.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)){
                    player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                }

                /*if (hasBloodstainedArmor(player)) {
                    health += 12;
                }*/
                /*if (hasExoArmor(player)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0, true, false, true));
                    health += 6;
                }*/
                if(hasUmbraArmor(player)){
                    if(!(Utils.getWorld().isDayTime())){
                        health += 16;
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1, true, false, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1, true, false, true));
                    }else{
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0, true, false, true));
                    health += 8;
                    }
                }

                if(hasBurnlightArmor(player)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0, true, false, true));
                    health += 16;
                }

                health += data.getExtraHealth();
                health -= data.getNegativeHealth();
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);


                var dataTemperatura = data.getTemperature();
                //Hipertermia
                if (dataTemperatura >= 120 && dataTemperatura <= 180) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &6&l" + dataTemperatura + "° &7|| &4¡Hipertermia I!")));
                    if(player.getEquipment().getHelmet() != null && player.getEquipment().getHelmet().hasItemMeta() && player.getEquipment().getHelmet().getItemMeta().hasEnchant(CustomEnchants.ADAPTATIVE)){
                        return;
                    } else {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0, true, false, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 4, true, false, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 2, true, false, true));
                        player.setFireTicks(20);
                    }
                } else if (dataTemperatura >= 180 && dataTemperatura <= 220) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &6&l" + dataTemperatura + "° &7|| &4¡Hipertermia II!")));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 3, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 9, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 4, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 2, true, false, true));
                    player.setFireTicks(20);
                } else if (dataTemperatura >= 220) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &6&l" + dataTemperatura + "° &7|| &4¡Hipertermia III! &7|| &4&l¡Demente!")));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 9, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 9, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 9, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 3, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 2, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 9, true, false, true));
                    player.setFireTicks(20);


                    //Hipotermia
                } else if (dataTemperatura <= -70 && dataTemperatura >= -120) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &b&l" + dataTemperatura + "° &7|| &b¡Hipotermia I!")));
                    if (player.getEquipment().getHelmet() != null && player.getEquipment().getHelmet().hasItemMeta() && player.getEquipment().getHelmet().getItemMeta().hasEnchant(CustomEnchants.ADAPTATIVE)) {
                        return;
                    } else {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 4, true, false, true));
                          player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0, true, false, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 2, true, false, true));
                      player.setFreezeTicks(20);
                }
                } else if (dataTemperatura <= -120 && dataTemperatura >= -180) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &b&l" + dataTemperatura + "° &7|| &b¡Hipotermia II!")));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 9, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 2, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 4, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 7, true, false, true));
                    player.setFreezeTicks(20);
                } else if (dataTemperatura <= -180) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &b&l" + dataTemperatura + "° &7|| &b¡Hipotermia III! &7|| &4&l¡Demente!")));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 9, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 2, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 4, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 7, true, false, true));
                    player.setFreezeTicks(20);
                } else {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.format("&4&l[&6&lTemperatura&4&l] &c" + dataTemperatura + "°")));
                }
            }
        }
    }



    public void demente(Player random,Player p){
        int clase = new Random().nextInt(22);
        if(clase == 1){
            EventosItems.animacion(random,p);
        }else if(clase == 2){
            p.playEffect(EntityEffect.TOTEM_RESURRECT);
        }else if(clase == 3){
            p.sendMessage(Format.format("&7&o??? te susurra: ¿Hola?"));
        }else if(clase == 4){
            p.playEffect(EntityEffect.HURT);
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
        }else if(clase == 12){
            p.sendMessage(Format.format("&7&o??? te susurra: ¿Me pasas las Coordenadas de tu Base?"));
        }else if(clase == 13){
            p.sendMessage(Format.format("&7&o??? te susurra: ¿Ya viste los nuevos Cambios de Dificultad?")); //XD
            // XD
        }else if(clase == 14){
            p.playSound(p.getLocation(),Sound.ENTITY_ELDER_GUARDIAN_CURSE,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 15){
            p.playSound(p.getLocation(),Sound.ENTITY_WITHER_SPAWN,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 16){
            p.playSound(p.getLocation(),Sound.ENTITY_ENDER_DRAGON_GROWL,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 17){
            p.playSound(p.getLocation(),Sound.ENTITY_SPIDER_AMBIENT,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 18){
            p.playSound(p.getLocation(),Sound.ENTITY_EVOKER_PREPARE_SUMMON,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 19){
            p.playSound(p.getLocation(),Sound.ENTITY_GENERIC_EXPLODE,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 20){
            p.playSound(p.getLocation(),Sound.ENTITY_WITHER_AMBIENT,SoundCategory.MASTER,10.0F,1.0F);
        }else if(clase == 21){
            p.sendMessage(Format.format("&7&o??? te susurra: Como estas " + p.getName() + " :)?"));
        }else{
            EventosItems.deathmessagefake(random,p);
        }

        }


    public boolean hasBloodstainedArmor(Player p){
        if(p.getInventory().getHelmet() != null && p.getInventory().getChestplate() != null && p.getInventory().getLeggings() != null && p.getInventory().getBoots() != null){
            if(p.getInventory().getHelmet().hasItemMeta() && p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getLeggings().hasItemMeta() && p.getInventory().getBoots().hasItemMeta()){
                if(p.getInventory().getHelmet().getItemMeta().hasCustomModelData() && p.getInventory().getChestplate().getItemMeta().hasCustomModelData() && p.getInventory().getLeggings().getItemMeta().hasCustomModelData() && p.getInventory().getBoots().getItemMeta().hasCustomModelData()){
                    return p.getInventory().getHelmet().getItemMeta().getCustomModelData() == 8010 && p.getInventory().getChestplate().getItemMeta().getCustomModelData() == 8010 && p.getInventory().getLeggings().getItemMeta().getCustomModelData() == 8010 && p.getInventory().getBoots().getItemMeta().getCustomModelData() == 8010;
                }
            }
        }
        return false;
    }

    /*public Configuration getBlockConfig() {
        return blockConfig;
    }

     */

    public static boolean hasExoArmor(Player p){
        if(p.getInventory().getHelmet() != null && p.getInventory().getChestplate() != null && p.getInventory().getLeggings() != null && p.getInventory().getBoots() != null){
            if(p.getInventory().getHelmet().hasItemMeta() && p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getLeggings().hasItemMeta() && p.getInventory().getBoots().hasItemMeta()){
                if(p.getInventory().getHelmet().getItemMeta().hasCustomModelData() && p.getInventory().getChestplate().getItemMeta().hasCustomModelData() && p.getInventory().getLeggings().getItemMeta().hasCustomModelData() && p.getInventory().getBoots().getItemMeta().hasCustomModelData()){
                    return p.getInventory().getHelmet().getItemMeta().getCustomModelData() == 47399 && p.getInventory().getChestplate().getItemMeta().getCustomModelData() == 47399 && p.getInventory().getLeggings().getItemMeta().getCustomModelData() == 47399 && p.getInventory().getBoots().getItemMeta().getCustomModelData() == 47399;
                }
            }
        }
        return false;
    }
    public static boolean hasUmbraArmor(Player p){
        if(p.getInventory().getHelmet() != null && p.getInventory().getChestplate() != null && p.getInventory().getLeggings() != null && p.getInventory().getBoots() != null){
            if(p.getInventory().getHelmet().hasItemMeta() && p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getLeggings().hasItemMeta() && p.getInventory().getBoots().hasItemMeta()){
                if(p.getInventory().getHelmet().getItemMeta().hasCustomModelData() && p.getInventory().getChestplate().getItemMeta().hasCustomModelData() && p.getInventory().getLeggings().getItemMeta().hasCustomModelData() && p.getInventory().getBoots().getItemMeta().hasCustomModelData()){
                    return p.getInventory().getHelmet().getItemMeta().getCustomModelData() == 6761618 && p.getInventory().getChestplate().getItemMeta().getCustomModelData() == 6761618 && p.getInventory().getLeggings().getItemMeta().getCustomModelData() == 6761618 && p.getInventory().getBoots().getItemMeta().getCustomModelData() == 6761618;
                }
            }
        }
        return false;
    }
    public boolean hasBurnlightArmor(Player p){
        if(p.getInventory().getHelmet() != null && p.getInventory().getChestplate() != null && p.getInventory().getLeggings() != null && p.getInventory().getBoots() != null){
            if(p.getInventory().getHelmet().hasItemMeta() && p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getLeggings().hasItemMeta() && p.getInventory().getBoots().hasItemMeta()){
                if(p.getInventory().getHelmet().getItemMeta().hasCustomModelData() && p.getInventory().getChestplate().getItemMeta().hasCustomModelData() && p.getInventory().getLeggings().getItemMeta().hasCustomModelData() && p.getInventory().getBoots().getItemMeta().hasCustomModelData()){
                    return p.getInventory().getHelmet().getItemMeta().getCustomModelData() == 3537921 && p.getInventory().getChestplate().getItemMeta().getCustomModelData() == 3537921 && p.getInventory().getLeggings().getItemMeta().getCustomModelData() == 3537921 && p.getInventory().getBoots().getItemMeta().getCustomModelData() == 3537921;
                }
            }
        }
        return false;
    }

    public void spawnMobNaturally(Player player, Block block) {
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



    //@ItzFel17
    //int i = true;
    //int j = true;
}
