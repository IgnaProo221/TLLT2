package Listeners;

import Extras.EventosItems;
import Extras.Items;
import Utilities.*;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import player.CustomPlayer;
import player.PlayerData;
import tlldos.tll2.TLL2;

import java.util.*;

import static Extras.Items.createFragmentoSangre;
import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class EntityListeners implements Listener {
    private final TLL2 plugin;
    public static HashMap<Player, Integer> hash = new HashMap<>();

    public EntityListeners(TLL2 plugin) {
        this.plugin = plugin;
    }

    public static void addHash(Player p) {
        if (hash.containsKey(p)) {
            int value = hash.get(p);
            int result = value + 1;
            hash.remove(p);
            hash.put(p, result);
        }
        hash.put(p, 1);
    }

    public void cosmosMobs(Entity entity) {
        int cosmoschance = new Random().nextInt(12);
        if (cosmoschance == 1) {
            var vortilol = entity.getLocation().getWorld().spawn(entity.getLocation(), Creeper.class);
            Mobs.vortice(vortilol);
        } else if (cosmoschance == 2) {
            var evokerxdlol = entity.getLocation().getWorld().spawn(entity.getLocation(), Evoker.class);
            Mobs.evokerExplosive(evokerxdlol);
        } else if (cosmoschance == 3) {
            entity.getWorld().spawn(entity.getLocation(),Axolotl.class);
        } else if (cosmoschance == 4) {
            var esqueletomamado = entity.getLocation().getWorld().spawn(entity.getLocation(), WitherSkeleton.class);
            Mobs.abomination(esqueletomamado);
        } else if (cosmoschance == 5) {
            var jodidoghast = entity.getLocation().getWorld().spawn(entity.getLocation(), Ghast.class);
            Mobs.riftedGhast(jodidoghast);
        } else if (cosmoschance == 6) {
            var zombipendejo = entity.getLocation().getWorld().spawn(entity.getLocation(), Zombie.class);
            Mobs.blightedZombi(zombipendejo);
        } else if (cosmoschance == 7) {
            var brujapendeja = entity.getLocation().getWorld().spawn(entity.getLocation(), Witch.class);
            Mobs.blightedWitch(brujapendeja);
        } else if (cosmoschance == 8) {
            var blaze = entity.getLocation().getWorld().spawn(entity.getLocation(), Blaze.class);
            blaze.setCustomName(format("&cHellfire"));
            blaze.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            blaze.setHealth(40);
            blaze.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HELLFIRE"), PersistentDataType.STRING, "HELLFIRE");
        } else if (cosmoschance == 9) {
            var elderpene = entity.getLocation().getWorld().spawn(entity.getLocation(),ElderGuardian.class);
            Mobs.elderdestroyer(elderpene);
        } else if (cosmoschance == 10) {
            var magopendejo = entity.getLocation().getWorld().spawn(entity.getLocation(), Illusioner.class);
            Mobs.riftedMage(magopendejo);
        } else if (cosmoschance == 11) {
            var esqueletorarito = entity.getLocation().getWorld().spawn(entity.getLocation(), Skeleton.class);
            Mobs.blightedSkeleton(esqueletorarito);
        } else {
            var aranaranaaaa = entity.getLocation().getWorld().spawn(entity.getLocation(), Spider.class);
            Mobs.blightedSpider(aranaranaaaa);
        }
    }

    public void interEffects(Player p) {
        int effect = new Random().nextInt(3);
        if (effect == 1) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 4, true, true, true));
        } else if (effect == 2) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2, true, true, true));
        } else {
            p.setFireTicks(1200);
        }
    }

    public void blightedZombieffects(Player p) {
        int effect = new Random().nextInt(5);
        if (effect == 1) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 9, true, true, true));
        } else if (effect == 2) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 9, true, true, true));
        } else if (effect == 3) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 9, true, true, true));
        } else if (effect == 4) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 9, true, true, true));
        } else {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 9, true, true, true));
        }
    }

    @EventHandler
    public void witherTest(ExplosionPrimeEvent e) {
        if(e.getEntity() instanceof EnderCrystal){
            e.setRadius(20);
        }
        if (e.getEntity() instanceof Wither wither) {
            if (wither.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "UNDYING_TITAN"), PersistentDataType.STRING)) {
                e.setRadius(10);
            }
        }
    }

    @EventHandler
    public void damageEntity(EntityDamageByEntityEvent event) {

        Random random = new Random();
        int lifestealpercentage = random.nextInt(100);
        var entity = event.getEntity();
        var damager = event.getDamager();
        if (damager instanceof Player pa) {
            if (pa.getInventory().contains(Items.ringOfAbaddon())) {
                if (lifestealpercentage > 80) {
                    pa.sendMessage(PREFIX, format("&cTu Ring of Abaddon te otorgo Fuerza II por 3 segundos"));
                    pa.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 1, true, false, true));
                }
            }
            //if(!(pa.getInventory().getItemInMainHand().getItemMeta() == null))return;
            //if(!(pa.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()))return;
            if (pa.getInventory().getItemInMainHand() != null) {
                if (pa.getInventory().getItemInMainHand().hasItemMeta()) {
                    if (pa.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.REVENGE)) {
                        if (entity instanceof Raider || (entity instanceof Vex || entity instanceof Pillager || entity instanceof Vindicator || entity instanceof Evoker || entity instanceof Illusioner || entity instanceof Witch || entity instanceof Ravager)) {
                            event.setDamage(event.getDamage() * 1.25);
                        }
                    } else if (pa.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.CRITICAL_HIT)) {
                        int criticalchance = new Random().nextInt(100);
                        if (criticalchance >= 95) {
                            event.setDamage(event.getDamage() * 2);
                            pa.playSound(pa.getLocation(), Sound.ITEM_TRIDENT_THROW, 10.0F, 2.0F);
                            pa.sendMessage(PREFIX, format("&c¡Tu Espada a hecho un Critico! &7(Total de " + event.getDamage() + " de Daño)"));
                        }
                    } else if (pa.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.SHRIEK)) {
                        if (entity instanceof IronGolem ironGolem) {
                            if (ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "WARDEN"), PersistentDataType.STRING)) {
                                event.setDamage(event.getDamage() * 2);
                            }
                        }
                    }
                    if (pa.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        if (pa.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4006) {
                            entity.setFreezeTicks(400);
                        } else if (pa.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4010) {
                            pa.sendMessage(format("&c&lel Lifesteal no funciona :)"));

                        } else if (pa.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 18129) {
                            Monster monster = (Monster) entity;
                            monster.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1, false, false, false));
                            monster.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 1, false, false, false));
                        } else if (pa.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 382388) {
                            LivingEntity livingEntity = (LivingEntity) entity;
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 2, false, false, false));
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 2, false, false, false));
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 600, 2, false, false, false));
                        }
                    }
                }
            }
        }


        if (entity instanceof Player p) {
            if (damager instanceof EvokerFangs z) {
                if (z.getOwner() instanceof Evoker evoker) {
                    if (evoker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FIRE_EVOKER"), PersistentDataType.STRING)) {
                        entity.setFireTicks(1200);
                    } else if (evoker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZE_EVOKER"), PersistentDataType.STRING)) {
                        entity.setFreezeTicks(1200);
                    } else if (evoker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HEX_EVOKER"), PersistentDataType.STRING)) {
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0, true, true, true));
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 4, true, true, true));
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 0, true, true, true));
                    } else if (evoker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "WIND_EVOKER"), PersistentDataType.STRING)) {
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 9, false, false, false));

                    }
                }
            }
            if (damager instanceof CaveSpider caveSpider) {
                if (caveSpider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MUTACION"), PersistentDataType.STRING)) {
                    entity.getWorld().createExplosion(caveSpider, 6, false, true);
                }
            }
            if(damager instanceof Axolotl axolotl){
                axolotl.getWorld().createExplosion(axolotl.getLocation(),7,false,true);
            }
            if (damager instanceof Vex vex) {
                if (p.isBlocking()) return;
                if (vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "CINDER"), PersistentDataType.STRING)) {
                    p.setFireTicks(1200);
                }
                if (vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "JENGU"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 1, true, false, true));
                }
                if (vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DJIIN"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 400, 1, true, false, true));
                }
                if (vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "GRUE"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0, true, false, true));
                }
                if (vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "INFECTED_VEX"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 4, true, true, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 600, 4, true, true, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 4, true, true, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 600, 4, true, true, true));
                }
                if (vex.getPersistentDataContainer().has(Utils.key("BANSHEE"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,200,0,true,false,true));
                }
            }
            if (damager instanceof WitherSkeleton) {
                if (p.isBlocking()) return;
                var witherskeleton = (WitherSkeleton) damager;
                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 700, 3, true, false, true));
            }
            if (damager instanceof IronGolem ironGolem) {
                if (p.isBlocking()) return;
                if (ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "LAVA_GOLEM"), PersistentDataType.STRING)) {
                    p.setFireTicks(1200);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0, true, false, true));
                } else if (ironGolem.getPersistentDataContainer().has(Utils.key("YETI"), PersistentDataType.STRING)) {
                    p.setFreezeTicks(1000);
                }
            }

            if (damager instanceof Spider) {
                var spider = (Spider) damager;
                PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                if (p.isBlocking()) return;
                if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXO_MELEE"), PersistentDataType.STRING)) {
                    int parachance = new Random().nextInt(100);
                    if (data.getParalizis() >= 1) return;
                    if (TLL2.hasExoArmor(p)) return;
                    if (parachance > 90) {
                        EventosItems.paralizison(p, data);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            EventosItems.paralizisoff(p, data);
                        }, 40);
                    }
                }
                if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ICE_MOB"), PersistentDataType.STRING)) {
                    p.setFreezeTicks(400);
                }
                if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "JUNGLE_MOB"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0));
                }
                if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SAND_MOB"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 400, 0));
                }
                if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "AGILE_SPIDER"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 2, true, true, true));
                }
                if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "INTER_SPIDER"), PersistentDataType.STRING)) {
                    interEffects(p);
                }
                if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SOLAR_SCORPION"), PersistentDataType.STRING)) {
                    p.setFireTicks(400);
                }
                if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_SPIDER"), PersistentDataType.STRING)) {
                    p.getLocation().getWorld().setType(p.getLocation(), Material.COBWEB);
                }
                if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DESERT_SCORPION"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1200, 4, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1200, 4, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 4, true, false, true));
                }
            }
            if (damager instanceof Slime) {
                var slime = (Slime) damager;
                if (p.isBlocking()) return;
                if (slime.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZING_SLIME"), PersistentDataType.STRING)) {
                    p.setFreezeTicks(1200);
                }
            }
            if (damager instanceof PufferFish pufferFish) {
                if (pufferFish.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "RADIO_GLOBE"), PersistentDataType.STRING)) {
                    ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 3, false, false, false));
                }
            }
            if (damager instanceof Zombie) {
                var zombie = (Zombie) damager;
                PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                if (p.isBlocking()) return;
                if (zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXO_MELEE"), PersistentDataType.STRING)) {
                    int parachance = new Random().nextInt(100);
                    if (data.getParalizis() >= 1) return;
                    if (TLL2.hasExoArmor(p)) return;
                        EventosItems.paralizison(p, data);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            EventosItems.paralizisoff(p, data);
                        }, 40);
                    }
                if (zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SAND_MOB"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 400, 0));
                }
                if (zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ICE_MOB"), PersistentDataType.STRING)) {
                    p.setFreezeTicks(400);
                }
                if (zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "JUNGLE_MOB"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0));
                }
                if (zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ZOMBIE"), PersistentDataType.STRING)) {
                    blightedZombieffects(p);
                }
            }
            if (damager instanceof Enderman) {
                var enderman = (Enderman) damager;
                if (p.isBlocking()) return;
                if (enderman.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ENDERMAN"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0, true, true, true));
                }
                if (enderman.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ENDER_FLAME"), PersistentDataType.STRING)) {
                    p.setFireTicks(1200);
                }
                if (enderman.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ENDER_INFECTED"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 0, true, true, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 1, true, true, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0, true, true, true));
                }
            }
            if (damager instanceof Skeleton skeleton) {
                if (p.isBlocking()) return;
                if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SAND_MOB"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 400, 0));
                }
            }
            if (damager instanceof Phantom) {
                var phantom = (Phantom) damager;
                PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                if (phantom.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXO_MELEE"), PersistentDataType.STRING)) {
                    int parachance = new Random().nextInt(100);
                    if (data.getParalizis() >= 1) return;
                    if (TLL2.hasExoArmor(p)) return;
                    if (parachance > 90) {
                        EventosItems.paralizison(p, data);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            EventosItems.paralizisoff(p, data);
                        }, 40);
                    }
                }
                if (phantom.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_PHANTOM"), PersistentDataType.STRING)) {
                    if (p.isBlocking()) return;
                    p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 9, true, false, true));
                }
            }
            if (damager instanceof Vindicator vindicator) {
                if (vindicator.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "THIEF"), PersistentDataType.STRING)) {
                    var player = (Player) entity;
                    var inv = player.getInventory().getContents();
                    var pos = random.nextInt(inv.length);
                    var item = inv[pos];

                    if (item != null) {
                        var drop = item.clone();
                        player.getInventory().setItem(pos, null);
                        player.getWorld().dropItemNaturally(player.getLocation().add(0, 5, 0), drop);
                    }
                }
                if(vindicator.getPersistentDataContainer().has(Utils.key("KILLERSCREAM"),PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,200,1,true,false,true));
                }
            }
            if (damager instanceof Villager villager) {
                if (villager.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VILLAGER_TERRORISTA"), PersistentDataType.STRING)) {
                    var player = (Player) entity;
                    player.getLocation().getWorld().createExplosion(villager, 8, false, true);
                    villager.damage(1000);
                }
            }
        }

    }

    @EventHandler
    public void onShootbow(EntityShootBowEvent event) {

        int caca = new Random().nextInt(2);
        var entity = event.getEntity();
        if (entity instanceof Skeleton) {
            var skeleton = (Entity) entity;
            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "LUSH_SKELETON"), PersistentDataType.STRING)) {
                Arrow arrow = (Arrow) event.getProjectile();
                arrow.setDamage(23);
            }
        }
        if (entity instanceof Piglin piglin) {
            if (piglin.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FRANCO"), PersistentDataType.STRING)) {
                Arrow arrow = (Arrow) event.getProjectile();
                arrow.setDamage(60);
                arrow.setGravity(false);
            }
        }
        if (entity instanceof Illusioner) {
            var illusioner = (Entity) entity;
            if (illusioner.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_MAGE"), PersistentDataType.STRING)) {
                var fireball = (Fireball) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation().add(0, 1, 0), EntityType.FIREBALL);
                fireball.setYield(7);
                event.setProjectile(fireball);
            }
        }
        if (entity instanceof WitherSkeleton) {
            var witherskeleton = (Entity) entity;
            if (witherskeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_WITHER"), PersistentDataType.STRING)) {
                if (caca == 1) {
                    var skull = (WitherSkull) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation().add(0, 1, 0), EntityType.WITHER_SKULL);
                    event.setProjectile(skull);
                } else {
                    var fireball = (Fireball) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation().add(0, 1, 0), EntityType.FIREBALL);
                    fireball.setYield(4);
                    event.setProjectile(fireball);
                }
            } else if (witherskeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "POWERED_SKELETON"), PersistentDataType.STRING)) {
                var skull = (WitherSkull) event.getEntity().launchProjectile(WitherSkull.class);
                skull.setYield(10);
                skull.setCustomName("CACA");
                event.setProjectile(skull);
            } else if (witherskeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ABOMINATION"), PersistentDataType.STRING)) {
                var skull = (WitherSkull) event.getEntity().launchProjectile(WitherSkull.class);
                skull.setYield(10);
                skull.setCustomName(format("abom_skull"));
                skull.setCharged(true);
            }
        }
    }

    @EventHandler
    public void hellfireFix(ProjectileLaunchEvent e){
        var shooter = e.getEntity().getShooter();
        var projectile = e.getEntity();
        if (shooter instanceof Blaze) {
            if(projectile instanceof SmallFireball smallFireball){
                var blaze = (Entity) shooter;
                if (blaze.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HELLFIRE"), PersistentDataType.STRING)) {
                    smallFireball.setCustomName("hellfire_proj");
                }
            }
        }
    }

    @EventHandler
    public void onShotthit(ProjectileHitEvent event) {
        var hitblock = event.getHitBlock();
        var entity = event.getHitEntity();
        var shooter = event.getEntity().getShooter();
        var projectile = event.getEntity();

        if (projectile instanceof SmallFireball smallFireball) {
            if (smallFireball.getCustomName() == null) return;
            if (smallFireball.getCustomName().contains("ember_projectile")) {
                if (entity != null) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    if (livingEntity instanceof Player || livingEntity instanceof Villager || livingEntity instanceof ArmorStand)
                        return;
                    livingEntity.damage(15);
                    livingEntity.setFireTicks(400);
                }
            }else if(smallFireball.getCustomName().contains("hellfire_proj")){
                if(entity != null){
                    entity.getLocation().createExplosion(smallFireball, 4, false, true);
                }
                if(hitblock != null){
                    hitblock.getLocation().createExplosion(smallFireball, 4, false, true);
                }
            }
        }
        if(projectile instanceof Fireball fireball){
            if(shooter instanceof Ghast ghast){
                if(ghast.getPersistentDataContainer().has(Utils.key("STARDUST"),PersistentDataType.STRING)){
                    fireball.remove();
                    SpawnListeners.spawnRandomMob(projectile.getLocation());
                }
            }
        }

        if (shooter instanceof Wither wither) {
            if (projectile instanceof WitherSkull) {
                if (wither.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ADVANCED_WITHER"), PersistentDataType.STRING)) {
                    if (hitblock != null) {
                        hitblock.getLocation().createExplosion(wither, 6, false, true);
                    } else if (entity != null) {
                        entity.getLocation().createExplosion(wither, 6, false, true);
                    }
                }else if(wither.getPersistentDataContainer().has(Utils.key("UNDYING_TITAN"),PersistentDataType.STRING)){
                    if (hitblock != null) {
                        hitblock.getLocation().createExplosion(wither, 8, false, true);
                    } else if (entity != null) {
                        entity.getLocation().createExplosion(wither, 8, false, true);
                    }
                }
            }
        }
        if (projectile instanceof WitherSkull witherSkull) {
            if (witherSkull.getCustomName().contains("abom_skull")) {
                if (witherSkull.getCustomName().isEmpty()) return;
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(6, false, true);
                } else if (entity != null) {
                    entity.getLocation().createExplosion(6, false, true);
                }
            } else if (witherSkull.getCustomName().contains("CACA")) {
                if (witherSkull.getCustomName().isEmpty()) return;
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(4, false, true);
                } else if (entity != null) {
                    entity.getLocation().createExplosion(4, false, true);
                }
            }
        }

        if (shooter instanceof Shulker) {
            var shulker = (Entity) shooter;
            if (shulker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_SHULKER"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(shulker, 2, false, true);
                } else if (entity != null) {
                    entity.getLocation().createExplosion(shulker, 2, false, true);
                }
            }
        }
        if(shooter instanceof Drowned drowned){
            if(drowned.getPersistentDataContainer().has(Utils.key("PYRO_DEEP"),PersistentDataType.STRING)){
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(drowned, 5, false, true);
                    projectile.remove();
                } else if (entity != null) {
                    entity.getLocation().createExplosion(drowned, 5, false, true);
                    projectile.remove();
                }
            }
        }
        if (shooter instanceof Skeleton) {
            var skeleton = (Entity) shooter;
            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXO_DISTANCE"), PersistentDataType.STRING)) {
                Player p = (Player) entity;
                PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                int pararandom = new Random().nextInt(100);
                if (pararandom > 90) {
                    if (data.getParalizis() >= 1) return;
                    if (TLL2.hasExoArmor(p)) return;
                    EventosItems.paralizison(p, data);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        EventosItems.paralizisoff(p, data);
                    }, 40);
                }

            }
            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "IGNITED_SKELETON"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(skeleton, 2, false, true);
                    projectile.remove();
                } else if (entity != null) {
                    entity.getLocation().createExplosion(skeleton, 2, false, true);
                    projectile.remove();
                }
            }

            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_SKELETON"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(skeleton, 3, false, true);
                    hitblock.getLocation().getWorld().strikeLightning(hitblock.getLocation());
                    projectile.remove();
                } else if (entity != null) {
                    entity.getLocation().createExplosion(skeleton, 3, false, true);
                    entity.getLocation().getWorld().strikeLightning(entity.getLocation());
                    projectile.remove();
                }
            }

            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ICE_SKELETON"), PersistentDataType.STRING)) {
                if (entity != null) {
                    entity.setFreezeTicks(400);
                }
            }

            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "COPPER_SKELETON"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().getWorld().strikeLightning(hitblock.getLocation());
                } else if (entity != null) {
                    entity.getLocation().getWorld().strikeLightning(entity.getLocation());
                }
            }
            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ABOMINATION"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(skeleton, 6, false, true);
                    hitblock.getLocation().getWorld().strikeLightning(hitblock.getLocation());
                    projectile.remove();
                } else if (entity != null) {
                    entity.getLocation().createExplosion(skeleton, 6, false, true);
                    entity.getLocation().getWorld().strikeLightning(entity.getLocation());
                    projectile.remove();
                }
            }
        }
        if (shooter instanceof Pillager) {
            var pillager = (Entity) shooter;
            if (pillager.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DYNAMLLAGER"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(pillager, 2, false, true);
                    projectile.remove();
                } else if (entity != null) {
                    entity.getLocation().createExplosion(pillager, 2, false, true);
                    projectile.remove();
                }
            }
            if (pillager.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MOUNTLLAGER"), PersistentDataType.STRING)) {
                if (entity != null) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 3, false, false, false));
                }
            }
        }
    }

    @EventHandler
    public void explotarRadi(EntityExplodeEvent e) {
        var entity = (Entity) e.getEntity();
        if (e.blockList() != null) {
            e.blockList().forEach(block -> {
                if (block.getType() == Material.OBSIDIAN) {
                    block.breakNaturally();
                }
            });
        }
        if (entity instanceof Creeper creeper) {
            if (creeper.getPersistentDataContainer().has(new NamespacedKey(Utils.getPlugin(), "HELLFIRE_CREEPER"), PersistentDataType.STRING)) {
                entity.getLocation().getNearbyPlayers(10, 10, 10).forEach(player -> player.setFireTicks(1200));
/*                Player nearby2 = (Player)entity.getLocation().getWorld().getNearbyPlayers(entity.getLocation(), 10.0D,10.0D,10.0D);
                nearby2.sendMessage(format("TEST"));
                nearby2.setFireTicks(1200);
 */
            }
            if (creeper.getPersistentDataContainer().has(new NamespacedKey(Utils.getPlugin(), "ICE_CREEPER"), PersistentDataType.STRING)) {
                entity.getLocation().getNearbyPlayers(7, 7, 7).forEach(player -> player.setFreezeTicks(1200));
            }
            if (creeper.getPersistentDataContainer().has(new NamespacedKey(plugin, "EXO_EXPLODE"), PersistentDataType.STRING)) {
                for (Entity p : creeper.getNearbyEntities(5, 5, 5)) {
                    if (!(p instanceof Player)) return;
                    Player player = (Player) p;
                    if (TLL2.hasExoArmor(player)) return;
                    PlayerData data = CustomPlayer.fromName(player.getName()).getData();
                    EventosItems.paralizison(player, data);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        EventosItems.paralizisoff(player, data);
                    }, 40);
                }
            }
            if (creeper.getPersistentDataContainer().has(new NamespacedKey(Utils.getPlugin(), "MOSS_CREEPER"), PersistentDataType.STRING)) {
                entity.getLocation().getNearbyPlayers(7, 7, 7).forEach(player -> player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 4)));
            }
            if (creeper.getPersistentDataContainer().has(new NamespacedKey(Utils.getPlugin(), "SANDSTONE_CREEPER"), PersistentDataType.STRING)) {
                entity.getLocation().getNearbyPlayers(7, 7, 7).forEach(player -> player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 600, 3)));
            }
            if(creeper.getPersistentDataContainer().has(Utils.key("OVERSCREAM"),PersistentDataType.STRING)){
                entity.getLocation().getNearbyPlayers(7, 7, 7).forEach(player -> player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 400, 2)));
            }
            if (creeper.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HIVE_MIND"), PersistentDataType.STRING)) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    var bee1 = (Bee) creeper.getLocation().getWorld().spawn(creeper.getLocation(), Bee.class);
                    var bee2 = (Bee) creeper.getLocation().getWorld().spawn(creeper.getLocation(), Bee.class);
                    var bee3 = (Bee) creeper.getLocation().getWorld().spawn(creeper.getLocation(), Bee.class);
                    var bee4 = (Bee) creeper.getLocation().getWorld().spawn(creeper.getLocation(), Bee.class);
                    var bee5 = (Bee) creeper.getLocation().getWorld().spawn(creeper.getLocation(), Bee.class);
                    Mobs.thePlague(bee1);
                    Mobs.thePlague(bee2);
                    Mobs.thePlague(bee3);
                    Mobs.thePlague(bee4);
                    Mobs.thePlague(bee5);
                }, 4L);
            }
            if (creeper.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "COSMOS_CALAMITY"), PersistentDataType.STRING)) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    var cos1 = e.getLocation().getWorld().spawn(e.getLocation(), Silverfish.class);
                    var cos2 = e.getLocation().getWorld().spawn(e.getLocation(), Silverfish.class);
                    var cos3 = e.getLocation().getWorld().spawn(e.getLocation(), Silverfish.class);
                    Mobs.cosmicSilver(cos1);
                    Mobs.cosmicSilver(cos2);
                    Mobs.cosmicSilver(cos3);
                }, 4L);
            }
        }
    }


    @EventHandler
    public void onDeathxd(EntityDeathEvent event) {
        var e = event.getEntity();
        if (e instanceof Spider) {
            var spider = (Spider) e;
            if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "PLAGUE_SPIDER"), PersistentDataType.STRING)) {
                var nube = e.getLocation().getWorld().spawn(e.getLocation(), AreaEffectCloud.class);
                Mobs.plagueEntity(nube);
            }
        }
        if (e instanceof Silverfish silverfish) {
            if (silverfish.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "COSMIC_SILVERFISH"), PersistentDataType.STRING)) {
                cosmosMobs(silverfish);
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendMessage(Format.PREFIX + format("&c&l!El Cosmos ha invocado un mob aleatorio en X: " + e.getLocation().getBlockX() + " Y: " + e.getLocation().getBlockY() + " Z: " + e.getLocation().getBlockZ() + "!"));
                });
            }
        }
        if (e instanceof IronGolem ironGolem) {
            if (ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "LAVA_GOLEM"), PersistentDataType.STRING)) {
                ironGolem.getLocation().getBlock().setType(Material.LAVA);
            }
        }
        if (e instanceof Zombie) {
            var zombie = (Zombie) e;
            if (zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TNT_MONSTER"), PersistentDataType.STRING)) {
                var tnt = e.getLocation().getWorld().spawn(e.getLocation(), TNTPrimed.class);
                Mobs.tntZomb(tnt);
            }
        }
        if (e instanceof Bat) {
            var bat = (Bat) e;
            if (bat.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXPLOSIVE_BAT"), PersistentDataType.STRING)) {
                bat.getLocation().createExplosion(bat, 8, false, true);
            }
        }
        if(e instanceof Phantom phantom){
            int moblol = new Random().nextInt(4);
            if(moblol == 1){
                Creeper creeper = phantom.getWorld().spawn(phantom.getLocation(),Creeper.class);
                Mobs.roboMine(creeper);
            }else if(moblol == 2){
                Creeper creeper = phantom.getWorld().spawn(phantom.getLocation(),Creeper.class);
                Mobs.Overscream(creeper);
            }else if(moblol == 3){
                WitherSkeleton witherSkeleton = phantom.getWorld().spawn(phantom.getLocation(),WitherSkeleton.class);
                Mobs.abomination(witherSkeleton);
            }else{
                Ghast ghast = phantom.getWorld().spawn(phantom.getLocation(),Ghast.class);
                Mobs.blightedGhast(ghast);
            }
        }
    }


    @EventHandler
    public void entityDeath(EntityDeathEvent event) {

        Entity entity = event.getEntity();
        Entity killer = event.getEntity().getKiller();

        Location here = entity.getLocation();

        Random random = new Random();

        int size = random.nextInt(5) + 1;
        ItemStack dropFrag = createFragmentoSangre(size);

        int bookchance = random.nextInt(100);
        if (entity instanceof Evoker && killer instanceof Player playo) {
            if (bookchance >= 95) {
                playo.sendMessage(PREFIX, format("&e&lEnhorabuena! &ehas conseguido una &c&lToma Ancestral &epor asesinar a un Evoker."));
                int bookslol = random.nextInt(5);
                if (bookslol == 1) {
                    event.getDrops().add(Items.pichaTome());
                } else if (bookslol == 2) {
                    event.getDrops().add(Items.critihitTome());
                } else if (bookslol == 3) {
                    event.getDrops().add(Items.revengeTome());
                } else if (bookslol == 4) {
                    event.getDrops().add(Items.bullsEyeTome());
                } else {
                    event.getDrops().add(Items.teleTome());
                }
            }
        }

        if (entity instanceof IronGolem) {
            if (BlastStormListeners.isEnabled())
                here.getWorld().createExplosion(here, 3.0F);
        }

        if (entity instanceof Villager && killer instanceof Player p) {

            event.getDrops().add(dropFrag);

            addHash(p);
            p.sendMessage(format("&7¡Has sacrificado a un &6&lAldeano&7, has recibido &6&l" + size + " &cFragmento(s) de Sangre&7!"));
        }
        if (entity instanceof Silverfish silverfish && killer instanceof Player p) {
            if (silverfish.getPersistentDataContainer().has(Utils.key("MINER_LEVEL_1"), PersistentDataType.STRING)) {
                PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                data.setMasteryExp(data.getMasteryExp() + 350);
            }
            //PersistentDataContainer data = Data.get(p);
            //var dataMaestriaExp = data.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);
            //if(dataMaestriaExp == null)return;
            //data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, dataMaestriaExp + 350);
            //}
        }
        if (entity instanceof Zombie zombie && killer instanceof Player p) {
            if (zombie.getPersistentDataContainer().has(Utils.key("MINER_LEVEL_1"), PersistentDataType.STRING)) {
                PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                data.setMasteryExp(data.getMasteryExp() + 350);
                //PersistentDataContainer data = Data.get(p);
                //var dataMaestriaExp = data.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);
                //if(dataMaestriaExp == null)return;
                //data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, dataMaestriaExp + 350);
            }
        }
        if (entity instanceof Drowned drowned && killer instanceof Player p) {
            if (drowned.getPersistentDataContainer().has(Utils.key("MINER_LEVEL_1"), PersistentDataType.STRING)) {
                PersistentDataContainer data = Data.get(p);
                var dataMaestriaExp = data.get(new NamespacedKey(plugin, "maestriaexp"), PersistentDataType.INTEGER);
                if (dataMaestriaExp == null) return;
                data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, dataMaestriaExp + 350);
            }
        }
        if (entity instanceof Creeper creeper && killer instanceof Player p) {
            if (creeper.getPersistentDataContainer().has(Utils.key("MINER_LEVEL_2"), PersistentDataType.STRING)) {
                PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                data.setMasteryExp(data.getMasteryExp() + 750);
                //PersistentDataContainer data = Data.get(p);
                //var dataMaestriaExp = data.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);
                //if(dataMaestriaExp == null)return;
                //data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, dataMaestriaExp + 750);
            }
        }
        if (entity instanceof Skeleton skeleton && killer instanceof Player p) {
            if (skeleton.getPersistentDataContainer().has(Utils.key("MINER_LEVEL_2"), PersistentDataType.STRING)) {
                //PersistentDataContainer data = Data.get(p);
                //var dataMaestriaExp = data.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);
                //if(dataMaestriaExp == null)return;
                //data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, dataMaestriaExp + 750);
                PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                data.setMasteryExp(data.getMasteryExp() + 750);
            }
        }
        if (entity instanceof IronGolem ironGolem && killer instanceof Player p) {
            if (ironGolem.getPersistentDataContainer().has(Utils.key("MINER_LEVEL_3"), PersistentDataType.STRING)) {
                //PersistentDataContainer data = Data.get(p);
                //var dataMaestriaExp = data.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);
                //if(dataMaestriaExp == null)return;
                //data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, dataMaestriaExp + 1500);
                PlayerData data = CustomPlayer.fromName(p.getName()).getData();
                data.setMasteryExp(data.getMasteryExp() + 1500);
            }
        }
    }

    @EventHandler
    public void paralizsthing(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        PlayerData data = CustomPlayer.fromName(p.getName()).getData();
        if (data.getParalizis() >= 1) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void noZombPig(EntityTransformEvent e) {
        if (e.getTransformReason() == EntityTransformEvent.TransformReason.PIGLIN_ZOMBIFIED) {
            try {
                var piglin = (Piglin) e.getEntity();

                ItemStack[] contents = piglin.getEquipment().getArmorContents().clone();

                Bukkit.getScheduler().runTaskLater(plugin, () -> piglin.getEquipment().setArmorContents(contents)
                        , 10L);
            } catch (NullPointerException x) {
                x.printStackTrace();
                Warn.Mutant(x);
            }
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void witchThrow(ProjectileHitEvent e) {
        var entity = e.getEntity();
        var shooter = e.getEntity().getShooter();
        if (shooter instanceof Witch) {
            var witch = (Witch) shooter;
            if (witch.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_WITCH"), PersistentDataType.STRING)) {
                if (entity instanceof ThrownPotion) {
                    int potiontype = new Random().nextInt(3) + 1;
                    if (potiontype == 1) {
                        var throwpotion = (ThrownPotion) entity;
                        ItemStack s = new ItemStack(Material.SPLASH_POTION);
                        PotionMeta meta = (PotionMeta) s.getItemMeta();
                        meta.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 200, 9), true);
                        meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 1, 2), true);
                        s.setItemMeta(meta);
                        throwpotion.setItem(s);
                    } else if (potiontype == 2) {
                        var throwpotion = (ThrownPotion) entity;
                        ItemStack s = new ItemStack(Material.SPLASH_POTION);
                        PotionMeta meta = (PotionMeta) s.getItemMeta();
                        meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 9), true);
                        meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0), true);
                        s.setItemMeta(meta);
                        throwpotion.setItem(s);
                    } else {
                        var throwpotion = (ThrownPotion) entity;
                        ItemStack s = new ItemStack(Material.SPLASH_POTION);
                        PotionMeta meta = (PotionMeta) s.getItemMeta();
                        meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 400, 3), true);
                        meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 2), true);
                        meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 0), true);
                        s.setItemMeta(meta);
                        throwpotion.setItem(s);
                    }
                }
            }
        }
    }

    @EventHandler
    public void fangqueExplotan(EntitySpawnEvent e) {
        if (e.getEntity() instanceof EvokerFangs fangs)
            if (fangs.getOwner() instanceof Evoker evoker) {
                if (evoker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXPLOSIVE_EVOKER"), PersistentDataType.STRING)) {
                    fangs.getLocation().createExplosion(evoker, 3, false, true);
                }
            }
    }

    @EventHandler
    public void escudoHabiliadd(EntityDamageByEntityEvent e) {
        var entity = e.getEntity();
        var damager = e.getDamager();
        if (entity instanceof Player player) {
            if (player.isBlocking()) {
                if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                    if (player.getInventory().getItemInMainHand() != null || player.getInventory().getItemInOffHand() != null) {
                        if (player.getInventory().getItemInMainHand().hasItemMeta() || player.getInventory().getItemInOffHand().hasItemMeta()) {
                            if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TACKLE) || player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.TACKLE)) {
                                if (damager instanceof LivingEntity livingEntity) {
                                    Vector vector = livingEntity.getEyeLocation().getDirection().multiply(-2);
                                    livingEntity.setVelocity(vector);
                                }

                            }else if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.BRUTE_FORCE) || player.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.BRUTE_FORCE)){
                                if (damager instanceof LivingEntity livingEntity) {
                                    player.getWorld().spawnParticle(Particle.EXPLOSION_LARGE,player.getLocation(),1);
                                    player.playSound(player.getLocation(),Sound.ENTITY_GENERIC_EXPLODE,SoundCategory.MASTER,10.0F,-1.0F);
                                    Vector vector = livingEntity.getEyeLocation().getDirection().multiply(-4);
                                    livingEntity.setVelocity(vector);
                                    for(Entity entities : livingEntity.getWorld().getNearbyEntities(livingEntity.getLocation(),7,7,7)){
                                        LivingEntity nearbyentities = (LivingEntity) entities;
                                        if(nearbyentities instanceof Player || nearbyentities instanceof Villager || nearbyentities instanceof ArmorStand)return;
                                        nearbyentities.damage(20,player);
                                    }
                                }
                            }


                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() || player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5015 || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 5015) {
                                    if (damager instanceof Monster monster) {
                                        monster.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 99, false, false, false));
                                        monster.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0, false, false, false));
                                    }

                                    if (player.getEquipment().getItemInMainHand().getType().equals(Material.SHIELD)) {
                                        ItemStack shield = player.getEquipment().getItemInMainHand().clone();
                                        player.getEquipment().getItemInMainHand().setAmount(0);
                                        player.getEquipment().setItemInMainHand(shield);

                                    } else if (player.getEquipment().getItemInOffHand().getType().equals(Material.SHIELD)) {
                                        ItemStack shield = player.getEquipment().getItemInOffHand().clone();
                                        player.getEquipment().getItemInOffHand().setAmount(0);
                                        player.getEquipment().setItemInOffHand(shield);
                                    }
                                    player.setCooldown(Material.SHIELD, 400);
                                }else if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 11282349 || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 11282349) {
                                    if(damager instanceof LivingEntity livingEntity){
                                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,400,1,false,false,false));
                                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 2, false, false, false));
                                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 0, false, false, false));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void escudoajhajasjadj(EntityDamageByEntityEvent e){
        var entity = e.getEntity();
        var damager = e.getDamager();
        if (entity instanceof Player player) {
            if (player.isBlocking()) {
                if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                    if (player.getInventory().getItemInMainHand() != null || player.getInventory().getItemInOffHand() != null) {
                        if (player.getInventory().getItemInMainHand().hasItemMeta() || player.getInventory().getItemInOffHand().hasItemMeta()) {
                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() || player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 11282349 || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 11282349) {
                                    if(damager instanceof LivingEntity livingEntity){
                                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,400,1,false,false,false));
                                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 2, false, false, false));
                                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 0, false, false, false));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void arcoscustoms1(EntityShootBowEvent e) {
        var bow = e.getBow().getItemMeta();
        if (!bow.hasCustomModelData()) {
            return;
        }
        if (bow.getCustomModelData() == 389909) {
            e.getProjectile().setCustomName("explosive");
        } else if (bow.getCustomModelData() == 5080) {
            e.getProjectile().setCustomName("ice");
        } else if (bow.getCustomModelData() == 27289) {
            e.getProjectile().setCustomName("exo_proj");
        } else if(bow.getCustomModelData() == 91817){
            e.getProjectile().setCustomName("shade_proj");
        }
    }


    @EventHandler
    public void arcoAaaamemuero(ProjectileHitEvent e) {
        var block = e.getHitBlock();
        var damaged = e.getHitEntity();
        var shooter = e.getEntity().getShooter();
        var projectile = e.getEntity();
        if (shooter instanceof Player) {
            if (projectile instanceof Arrow) {
                if (projectile.getCustomName() != null) {
                    if (projectile.getCustomName().contains("explosive")) {
                        if (damaged != null) {
                            damaged.getLocation().createExplosion(projectile, 3, false, true);
                        } else if (block != null) {
                            block.getLocation().createExplosion(projectile, 3, false, true);
                        }
                        projectile.remove();
                    } else if (projectile.getCustomName().contains("ice")) {
                        if (damaged != null) {
                            damaged.setFreezeTicks(1200);
                        }
                    } else if (projectile.getCustomName().contains("exp_proj")) {
                        if (damaged != null) {
                            LivingEntity livingEntity = (LivingEntity) damaged;
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1, false, false, false));
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 1, false, true, false));
                        }
                    }else if(projectile.getCustomName().contains("shade_proj")){
                        if(damaged != null){
                            LivingEntity livingEntity = (LivingEntity) damaged;
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 2, false, false, false));
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 2, false, false, false));
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 600, 2, false, false, false));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void targetChange(EntityTargetEvent e) {
        Entity entity = e.getEntity();
        Entity entity1 = e.getTarget();
        if (entity instanceof IronGolem) {
            if (entity1 instanceof Player) return;
            e.setCancelled(true);
        } else if (entity instanceof Monster) {
            if (entity1 instanceof IronGolem) {
                e.setCancelled(true);
            }
        }
    }


}
