package Extras;

import Utilities.CustomEnchants;
import Utilities.Format;
import Utilities.Utils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import player.CustomPlayer;
import tlldos.tll2.TLL2;

import java.util.Random;

public class DamageListeners implements Listener {
    private TLL2 plugin;

    public DamageListeners(TLL2 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void cancelledDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();

        if (!(entity instanceof Player)) {
            if (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
                e.setCancelled(true);
            }
            if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                e.setCancelled(true);
            }
        }

        if (entity instanceof Blaze || entity instanceof Enderman) {
            if (e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)) {
                e.setCancelled(true);
            }
        }

        if (entity instanceof IronGolem ironGolem) {
            if (ironGolem.getPersistentDataContainer().has(Utils.key("WARDEN"), PersistentDataType.STRING)) {
                if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
                    e.setCancelled(true);
                }
            }
        }
    }


    @EventHandler
    public void danoProdos(EntityDamageEvent e) {
        if (e.isCancelled()) {
            return;
        }

        if (e.getEntity() instanceof Player p) {
            if (p.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                if (((EntityDamageByEntityEvent) p.getLastDamageCause()).getDamager() instanceof LivingEntity) {
                    LivingEntity monster = (LivingEntity) ((EntityDamageByEntityEvent) p.getLastDamageCause()).getDamager();
                    if (monster.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SOLAR_SCORPION"), PersistentDataType.STRING)) {
                        if (e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || e.getCause() == EntityDamageEvent.DamageCause.FIRE) {
                            e.setDamage(e.getDamage() * 3);
                        }
                    }
                }
            }
        }
    }


    @EventHandler
    public void danoPro(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player player) {
            var data = CustomPlayer.fromName(player.getName()).getData();
            var dataTemperatura = data.getTemperature();
            var inmunity = data.getImmunity();
            if (e.getEntity().getLastDamageCause() == null) {
                return;
            }
            if (inmunity >= 1) {
                if (e.getCause() != EntityDamageEvent.DamageCause.SUICIDE) {
                    e.setCancelled(true);
                }
            }
            if(player.getInventory().getItemInMainHand() != null || player.getInventory().getItemInMainHand() != null){
                if(player.getInventory().getItemInMainHand().hasItemMeta() || player.getInventory().getItemInOffHand().hasItemMeta()){
                    if(player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() || player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()){
                        if(player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 11282349 || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 11282349){
                            if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
                                e.setCancelled(true);
                            }
                            if(e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING){
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }

            if (player.getInventory().contains(Items.luminiteArtifact())) {
                int damagenegate = new Random().nextInt(100);
                if (damagenegate == 1) {
                    if (player.hasCooldown(Material.NETHERITE_SCRAP)) return;
                    e.setCancelled(true);
                    player.sendMessage(Format.PREFIX, Format.format("&bTu Medallon de Luminita a Negado el Daño Recibido!"));
                    player.setCooldown(Material.NETHERITE_SCRAP, 1200);
                }
            }

            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().hasItemMeta() && player.getInventory().getChestplate().getItemMeta().hasEnchant(CustomEnchants.WITHER_JUSTICE)) {
                if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                    if(player.getWorld().isThundering())return;
                    e.setDamage(e.getDamage() - 10);
                    //nose de mates perdon
                }
            }

            if (player.getGameMode() == GameMode.SPECTATOR) {
                if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    e.setCancelled(true);
                }
            } else if (Utils.getWorld().isThundering()) {
                if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                    e.setDamage(e.getDamage() * 3);
                }
                if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                    e.setDamage(e.getDamage() * 16);
                }
                if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
                    e.setDamage(e.getDamage() * 2);
                }
            }
            if (player.hasPotionEffect(PotionEffectType.UNLUCK)) {
                if (player.getPotionEffect(PotionEffectType.UNLUCK).getAmplifier() == 0 ) {
                    if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                        e.setDamage(e.getDamage() * 3);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.FREEZE) {
                        e.setDamage(e.getDamage() * 23);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                        e.setDamage(e.getDamage() * 7);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                        e.setDamage(e.getDamage() * 8);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                        e.setDamage(e.getDamage() * 8);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
                        e.setDamage(1000000);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION){
                        e.setDamage(e.getDamage() * 21);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                        e.setDamage(1000000);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
                        e.setDamage(e.getDamage() * 8);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                        e.setDamage(e.getDamage() * 21);
                    } else if (e.getCause() != EntityDamageEvent.DamageCause.SUICIDE || e.getCause() != EntityDamageEvent.DamageCause.THORNS || e.getCause() != EntityDamageEvent.DamageCause.POISON || e.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK || e.getCause() != EntityDamageEvent.DamageCause.VOID) {
                        e.setDamage(e.getDamage() * 2);
                    }
                }else if (player.getPotionEffect(PotionEffectType.UNLUCK).getAmplifier() == 1) {
                        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                            e.setDamage(e.getDamage() * 4);
                        } else if (e.getCause() == EntityDamageEvent.DamageCause.FREEZE) {
                            e.setDamage(e.getDamage() * 24);
                        } else if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                            e.setDamage(e.getDamage() * 8);
                        } else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                            e.setDamage(e.getDamage() * 25);
                        } else if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                            e.setDamage(e.getDamage() * 25);
                        } else if (e.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
                            e.setDamage(1000000);
                        }else if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION){
                            e.setDamage(e.getDamage() * 22);
                        } else if (e.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                            e.setDamage(1000000);
                        } else if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
                            e.setDamage(e.getDamage() * 9);
                        } else if (e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                            e.setDamage(e.getDamage() * 22);
                        } else if (e.getCause() != EntityDamageEvent.DamageCause.SUICIDE || e.getCause() != EntityDamageEvent.DamageCause.THORNS || e.getCause() != EntityDamageEvent.DamageCause.POISON || e.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK || e.getCause() != EntityDamageEvent.DamageCause.VOID) {
                            e.setDamage(e.getDamage() * 3);
                        }
                    } else if (player.getPotionEffect(PotionEffectType.UNLUCK).getAmplifier() == 2) {
                    if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                        e.setDamage(e.getDamage() * 5);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.FREEZE) {
                        e.setDamage(e.getDamage() * 25);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                        e.setDamage(e.getDamage() * 9);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                        e.setDamage(e.getDamage() * 25);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                        e.setDamage(e.getDamage() * 25);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
                        e.setDamage(1000000);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION){
                        e.setDamage(e.getDamage() * 23);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                        e.setDamage(1000000);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
                        e.setDamage(e.getDamage() * 10);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                        e.setDamage(e.getDamage() * 23);
                    } else if (e.getCause() != EntityDamageEvent.DamageCause.SUICIDE || e.getCause() != EntityDamageEvent.DamageCause.THORNS || e.getCause() != EntityDamageEvent.DamageCause.POISON || e.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK || e.getCause() != EntityDamageEvent.DamageCause.VOID) {
                        e.setDamage(e.getDamage() * 4);
                    }
                }else if (player.getPotionEffect(PotionEffectType.UNLUCK).getAmplifier() == 3) {
                    if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                        e.setDamage(e.getDamage() * 6);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.FREEZE) {
                        e.setDamage(e.getDamage() * 26);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                        e.setDamage(e.getDamage() * 10);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                        e.setDamage(e.getDamage() * 25);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                        e.setDamage(e.getDamage() * 25);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
                        e.setDamage(1000000);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
                        e.setDamage(e.getDamage() * 24);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                        e.setDamage(1000000);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
                        e.setDamage(e.getDamage() * 11);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                        e.setDamage(e.getDamage() * 24);
                    } else if (e.getCause() != EntityDamageEvent.DamageCause.SUICIDE || e.getCause() != EntityDamageEvent.DamageCause.THORNS || e.getCause() != EntityDamageEvent.DamageCause.POISON || e.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK || e.getCause() != EntityDamageEvent.DamageCause.VOID) {
                        e.setDamage(e.getDamage() * 6);
                    }
                }else if(player.getPotionEffect(PotionEffectType.UNLUCK).getAmplifier() == 4) {
                    if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                        e.setDamage(e.getDamage() * 7);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.FREEZE) {
                        e.setDamage(e.getDamage() * 27);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                        e.setDamage(e.getDamage() * 11);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                        e.setDamage(e.getDamage() * 25);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                        e.setDamage(e.getDamage() * 25);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
                        e.setDamage(1000000);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
                        e.setDamage(e.getDamage() * 25);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                        e.setDamage(1000000);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
                        e.setDamage(e.getDamage() * 12);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                        e.setDamage(e.getDamage() * 25);
                    } else if (e.getCause() != EntityDamageEvent.DamageCause.SUICIDE || e.getCause() != EntityDamageEvent.DamageCause.THORNS || e.getCause() != EntityDamageEvent.DamageCause.POISON || e.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK || e.getCause() != EntityDamageEvent.DamageCause.VOID) {
                        e.setDamage(e.getDamage() * 7);
                    }
                    }



                } else {
                    if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                        e.setDamage(e.getDamage() * 2);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.FREEZE) {
                        e.setDamage(e.getDamage() * 20);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                        e.setDamage(e.getDamage() * 6);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
                        e.setDamage(1000000);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION){
                        e.setDamage(e.getDamage() * 20);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                        e.setDamage(1000000);
                    } else if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
                        e.setDamage(e.getDamage() * 7);
                    }else if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                                e.setDamage(e.getDamage() * 20);
                            } else if (e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                                e.setDamage(e.getDamage() * 20);
                            } else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                                e.setDamage(e.getDamage() * 20);
                            }
                        }

            if (dataTemperatura <= -180) {
                if (e.getCause() == EntityDamageEvent.DamageCause.FREEZE) {
                    e.setDamage(e.getDamage() * 10);
                }
            }
            if (dataTemperatura >= 220) {
                if (e.getCause() == EntityDamageEvent.DamageCause.LAVA || e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                    e.setDamage(e.getDamage() * 10);
                }
            }
                }
            }
        }


