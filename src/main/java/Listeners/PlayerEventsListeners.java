package Listeners;

import Extras.EventosItems;
import Extras.Items;
import Utilities.*;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import player.CustomPlayer;
import player.PlayerData;
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
    private final HashMap<UUID, Long> undyingCooldown = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.getItem() == null)return;
            if(!event.getItem().hasItemMeta())return;
            if (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(Format.format("&7Daga Ceremonial"))) {
                var data = p.getPersistentDataContainer();
                var inventory = p.getInventory();
                var dataSacrifices = data.get(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER);
                var totalSacrifices = (dataSacrifices == null ? 1 : ++dataSacrifices);
                p.sendMessage(format("&cLo Sentimos! los sacrificios ya no pueden ser realizados debido a un bug masivo y abusable"));
                /*if (cooldownSacrifice.containsKey(p.getUniqueId())) {
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
                }*/
            }
            }
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(event.getItem().hasItemMeta()){
                    if(event.getItem().getItemMeta().hasCustomModelData()){
                        if(event.getItem().getItemMeta().getCustomModelData() == 938745122){
                            if(p.hasCooldown(Material.BLAZE_ROD)){
                                p.sendMessage(PREFIX,format("&c&lEsta habilidad esta en Cooldown!"));
                                return;
                            }else{
                                p.sendMessage(PREFIX,format("&6¡Has usado la Ember Sceptre"));
                                var minifireball1 = (SmallFireball)p.launchProjectile(SmallFireball.class);
                                minifireball1.setCustomName("ember_projectile");
                                Bukkit.getScheduler().runTaskLater(plugin,()->{
                                    var minifireball2 = (SmallFireball)p.launchProjectile(SmallFireball.class);
                                    minifireball2.setCustomName("ember_projectile");
                                },10L);
                                Bukkit.getScheduler().runTaskLater(plugin,() ->{
                                    var minifireball3 = (SmallFireball)p.launchProjectile(SmallFireball.class);
                                    minifireball3.setCustomName("ember_projectile");
                                },20L);
                            }
                        }
                    }
                }
            }
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(p.getInventory().getItemInMainHand().hasItemMeta()){
                    if(p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
                     if(p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 84984389){
                         final HashSet hashSet = new HashSet<Material>();
                         hashSet.add(Material.AIR);
                         final Block block = p.getTargetBlock((Set<Material>) hashSet, 10);

                         final Location playerLocation = p.getLocation();
                         final Location teleportLocation = new Location(block.getWorld(), block.getX(),
                                 block.getY(), block.getZ(), playerLocation.getYaw(), playerLocation.getPitch());

                         if (teleportLocation.getBlock().getType() != Material.AIR) {
                             teleportLocation.setY(teleportLocation.getY() + 1);
                         }

                         if (new Location(teleportLocation.getWorld(), teleportLocation.getX(), teleportLocation.getY() + 1,
                                 teleportLocation.getZ()).getBlock().getType() == Material.AIR
                                 && p.getLocation().add(p.getLocation().getDirection()).getBlock().getType() == Material.AIR) {
                             teleportLocation.setX(teleportLocation.getX() - 0.5D);
                             teleportLocation.setZ(teleportLocation.getZ() - 0.5D);
                         } else {
                             teleportLocation.setX(teleportLocation.getX() + 0.5D);
                             teleportLocation.setZ(teleportLocation.getZ() + 0.5D);
                         }
                         p.teleport(teleportLocation);
                         p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,p.getLocation(),1);
                         p.playSound(p.getLocation(),Sound.ENTITY_ZOMBIE_VILLAGER_CURE,SoundCategory.HOSTILE,5.0F,0.7F);
                         p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,1200,9,false,false,false));
                         p.getWorld().getNearbyEntities(p.getLocation(),10,10,10, entity -> entity instanceof LivingEntity).forEach(entity -> {
                             LivingEntity livingEntity = (LivingEntity) entity;
                             if(livingEntity instanceof Player || livingEntity instanceof Villager)return;
                             livingEntity.damage(600);
                         });
                     }
                    }
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
                if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                    if(p.getInventory().getItemInMainHand().hasItemMeta() || p.getInventory().getItemInOffHand().hasItemMeta()){
                        if(p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() || p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()){
                            if(p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 8736689 || p.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 8736689){
                                PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                                if(p.hasCooldown(Material.BLAZE_ROD)){
                                    p.sendMessage(PREFIX,format("&c&lEsta habilidad esta en Cooldown!"));
                                    return;
                                }else{
                                    data.setImmunity(1);
                                    p.sendMessage(PREFIX, format("&6Has usado la &e&lUndying Sceptre"));
                                    p.setCooldown(Material.BLAZE_ROD,6000);
                                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                        data.setImmunity(0);
                                    }, 300L);
                                }
                            }
                        }
                    }
                }
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4455) {
                        if (p.hasCooldown(Material.PRISMARINE_CRYSTALS)) {
                            event.setCancelled(true);
                        } else {
                            EventosItems.totemrestorerEvent(p, plugin);
                            p.setCooldown(Material.PRISMARINE_CRYSTALS, 400);
                            p.getInventory().removeItem(new ItemStack(Material.PRISMARINE_CRYSTALS, 1));
                        }
                    }
                }
                if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4455) {
                        PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                        var totems = data.getTotemPercentage();
                        p.sendMessage(Format.PREFIX, format("&7&l¡Tienes &e&l" + totems + "% &7&lporcentaje de Totems!"));
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
                //Todo quizas lo tepeas a donde estaba y le quitas la velocity ?
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

    @EventHandler
    public void umbraRod(PlayerFishEvent event){
        Player p = event.getPlayer();
        if(p.getInventory().getItemInMainHand().hasItemMeta() || p.getInventory().getItemInOffHand().hasItemMeta()){
            if(p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() || p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()){
                if(p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 102038461 || p.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 102038461){
                    int entitychance = new Random().nextInt(100);
                    if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH){
                        if(entitychance > 70){
                            int reward = new Random().nextInt(7) +1;
                            if(reward == 1){
                                p.sendMessage(format("&6&l¡ENTIDAD!: &7Tu Umbra Rod a atrapado un &8Lost Golem"));
                                IronGolem ironGolem = event.getHook().getWorld().spawn(event.getHook().getLocation(),IronGolem.class);
                                Mobs.lostGolem(ironGolem);
                                Vector vector = ironGolem.getEyeLocation().getDirection().multiply(3);
                                ironGolem.setVelocity(vector);
                            }else if(reward == 2){
                                p.sendMessage(format("&6&l¡ENTIDAD!: &7Tu Umbra Rod a atrapado un &8Nightmare"));
                                Ghast ghast = event.getHook().getWorld().spawn(event.getHook().getLocation(),Ghast.class);
                                Mobs.Nightmare(ghast);
                                Vector vector = ghast.getEyeLocation().getDirection().multiply(3);
                                ghast.setVelocity(vector);
                            }else if(reward == 3){
                                p.sendMessage(format("&6&l¡ENTIDAD!: &7Tu Umbra Rod a atrapado un &8OverScream"));
                                Creeper creeper = event.getHook().getWorld().spawn(event.getHook().getLocation(),Creeper.class);
                                Mobs.Overscream(creeper);
                                Vector vector = creeper.getEyeLocation().getDirection().multiply(3);
                                creeper.setVelocity(vector);
                            }
                        }
                    }
                }
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
