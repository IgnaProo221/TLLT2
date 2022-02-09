package Comandos;

import Eventos.StartBlastStormEvent;
import Eventos.StopBlastStormEvent;
import Extras.Items;
import Utilidades.Format;
import Utilidades.TotemsBar;
import Utilidades.Utils;
import Utilidades.Warn;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tlldos.tll2.TLL2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Utilidades.Format.format;

public class ComandosStaff  implements CommandExecutor, TabCompleter {

    private final TLL2 plugin;

    public ComandosStaff(TLL2 plugin){
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.getServer().getConsoleSender().sendMessage("No se puede usar comandos en consola!");
            return true;
        }
        Server s = sender.getServer();
        Player pa = (Player) sender;

        if(sender.isOp()) {
            switch (args[0]) {
                case "dimension":
                    if (args[1].isEmpty()){
                        pa.sendMessage(Format.PREFIX + "Debes Colocar una Dimension!");
                    }
                    if(args[1].equalsIgnoreCase("builder_world")){
                        Location spawnbuilder = new Location(Bukkit.getWorld("build_world"), 0 , 64, 0);
                        pa.teleport(spawnbuilder);
                        pa.setGameMode(GameMode.SPECTATOR);
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("overworld")){
                        Location spawnover = new Location(Bukkit.getWorld("world"), 0 , 100, 0);
                        pa.teleport(spawnover);
                        pa.setGameMode(GameMode.SPECTATOR);
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("lost_cities")){
                    Location spawnlost = new Location(Bukkit.getWorld("lost_world"), 0 , 100, 0);
                    pa.teleport(spawnlost);
                    pa.setGameMode(GameMode.SPECTATOR);
                    return true;
                }
                    break;
                case "sacrificios":
                    if(args[1].isEmpty()){
                        pa.sendMessage(Format.PREFIX + "Debes colocar un subcomando valido (modify, clear, reset)");
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("modify")) {
                        try{
                            Player target = Bukkit.getPlayer(args[2]);
                            int modify = Integer.parseInt(args[3]);

                            if(target == null){
                                sender.sendMessage(Format.format("El usuario se encuentra desconectado."));
                                return true;
                            }

                            PersistentDataContainer data = target.getPersistentDataContainer();

                            if(!data.has(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER)){
                                sender.sendMessage(Format.format("&cEl usuario no tiene ningún sacrificio."));
                                return true;
                            }

                            if(modify > 5){
                                sender.sendMessage(Format.format("&cTe pasaste el número de sacrificios."));
                                return true;
                            }

                            data.set(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER, modify);

                            sender.sendMessage(Format.PREFIX + Format.format(String.format("Se ha modificado los sacrifios de &6%s &7a &6%d", target.getName(), modify)));

                            return true;
                        }catch(NumberFormatException e){
                            sender.sendMessage(Format.PREFIX + "Debes colocar un número valido.");
                        }
                    }else if (args[1].equalsIgnoreCase("clear")) {
                        Player target = Bukkit.getPlayer(args[1]);
                    }else if (args[1].equalsIgnoreCase("reset")) {
                        pa.sendMessage("debug");
                    }
                    break;
                case "alerta":
                    s.getConsoleSender().sendMessage("Alguien hizo el coso de Alerta @Mutant te llaman xdxdxd");
                    break;

                case "totem_bar": {
                    PersistentDataContainer data = ((Player) sender).getPersistentDataContainer();
                    int i = data.get(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER);

                    pa.playSound(pa.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    pa.sendMessage(Format.PREFIX, format("&7&l¡Tienes &e&l" +i + "% &7&lporcentaje de Totems!"));
                }
                break;

                case "vida_reset": {
                    pa.playSound(pa.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    pa.sendMessage(format(Format.PREFIX + "&eHas Reiniciado tu Vida Correctamente!"));
                    pa.setMaxHealth(20);
                }
                break;

                case "totems_clear":
                    try {
                        pa.playSound(pa.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                        pa.sendMessage(format(Format.PREFIX + "&eHas Reiniciado tu Porcentaje de Totems!"));
                        pa.getPersistentDataContainer().set(new NamespacedKey(plugin, "TOTEM_BAR"), PersistentDataType.INTEGER, 100);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Warn.Mutant(e);
                        pa.sendMessage(Format.format(Format.PREFIX + "&7¡Ha ocurrido un &c&lerror &7al resetear los tótems."));
                    }
                    break;

                case "debug":
                    if(args[1].isEmpty()){
                        pa.sendMessage(Format.PREFIX + "Debes colocar un debug valido (blastStormStart, blackStormEnd, totemTest)");
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("blastStormStart")) {
                        StartBlastStormEvent start = new StartBlastStormEvent();
                        s.getPluginManager().callEvent(start);
                    }else if(args[1].equalsIgnoreCase("blackStormEnd")){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear");

                        StopBlastStormEvent event = new StopBlastStormEvent(StopBlastStormEvent.Cause.COMMAND);
                        s.getPluginManager().callEvent(event);
                    }else if (args[1].equalsIgnoreCase("totemTest")) {
                        pa.playSound(pa.getLocation(), Sound.ITEM_TOTEM_USE, 10.0F, 1.0F);
                        pa.playEffect(EntityEffect.TOTEM_RESURRECT);
                    }
                    break;
                case "sacrificios_test":
                    try {
                        Bukkit.getLogger().info("xd");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Warn.Mutant(e);
                        pa.sendMessage(Format.format(Format.PREFIX + "&7¡Ha ocurrido un &c&lerror &7al mandar la GUI."));
                    }
                    break;
                case "give":
                    if(args[1].isEmpty()){
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Debes colocar el item que te quieres givear.");
                        return false;
                    }

                    if (args[1].equalsIgnoreCase("FUNGAL_CLUMPS")) {
                        pa.getInventory().addItem(Items.fungalClumps());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    } else if (args[1].equalsIgnoreCase("WEIRD_DAGGER")) {
                        pa.getInventory().addItem(Items.createDaga());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    } else if (args[1].equalsIgnoreCase("CATACLYSM_PEARL")) {
                        pa.getInventory().addItem(Items.cataclysmPearl());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    } else if (args[1].equalsIgnoreCase("BLOOD_SABER")) {
                        pa.getInventory().addItem(Items.bloodSaber());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    } else if (args[1].equalsIgnoreCase("BERSERKER_TOTEM")) {
                        pa.getInventory().addItem(Items.totemBerserk());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    } else if (args[1].equalsIgnoreCase("CRYSTAL_HEART")) {
                        pa.getInventory().addItem(Items.crystalHeart());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    } else if (args[1].equalsIgnoreCase("DISCORD")) {
                        pa.getInventory().addItem(Items.varaDis());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    } else if (args[1].equalsIgnoreCase("CLOUDY_MARSH")) {
                        pa.getInventory().addItem(Items.cloudMarsh());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    } else if (args[1].equalsIgnoreCase("BLOOD_STONE")) {
                        pa.getInventory().addItem(Items.bloodShard());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    } else if (args[1].equalsIgnoreCase("BLOOD_SHARD")) {
                        pa.getInventory().addItem(Items.createFragmentoSangre(1));
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    } else if (args[1].equalsIgnoreCase("TEMPERATURE_METER")) {
                        pa.getInventory().addItem(Items.termometroItem());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }else if (args[1].equalsIgnoreCase("TOTEM_RESTORER")) {
                        pa.getInventory().addItem(Items.totemRestorer());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }else if (args[1].equalsIgnoreCase("FROSTBITE")) {
                        pa.getInventory().addItem(Items.fallenSword());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }else if (args[1].equalsIgnoreCase("CELULA_ENERGIA")) {
                        pa.getInventory().addItem(Items.celulaEnergia());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }else if (args[1].equalsIgnoreCase("METAL_DESC")) {
                        pa.getInventory().addItem(Items.metaldes());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }else if (args[1].equalsIgnoreCase("EXO_SHIELD")) {
                        pa.getInventory().addItem(Items.exoShield());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }else if (args[1].equalsIgnoreCase("ICESHOT")) {
                        pa.getInventory().addItem(Items.iceShot());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }else if (args[1].equalsIgnoreCase("BLOOD_ARMOR")) {
                        pa.getInventory().addItem(Items.bloodyHelmet());
                        pa.getInventory().addItem(Items.bloodyChestplate());
                        pa.getInventory().addItem(Items.bloodyLeggings());
                        pa.getInventory().addItem(Items.bloodyBoots());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }else if (args[1].equalsIgnoreCase("EXO_DRILL")) {
                        pa.getInventory().addItem(Items.exoDrill());
                        pa.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                default:
                    pa.sendMessage(format("&7No has indicado ningun subcomando."));
                    pa.sendMessage(format("&7Si no sabes los comandos ejecuta el comando /tllstaff commandlist."));
                    break;
            }
        } else {
            sender.sendMessage(Format.PREFIX + ChatColor.RED + "No tienes Permisos para Ejecutar este comando");
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        List<String> commands = new ArrayList<>();

        if(commandSender.isOp()) {
            if(args.length == 1){
                String[] argsArray = {
                        "sacrificios", "alerta", "give", "sacrificios_test", "debug", "totem_bar", "totems_clear", "vida_reset", "dimension"
                };

                commands.addAll(Arrays.asList(argsArray));
                StringUtil.copyPartialMatches(args[0], commands, completions);
            }else if(args.length == 2){
                if(args[0].equals("dimension")){
                    commands.add("builder_world");
                    commands.add("overworld");
                    commands.add("lost_cities");
                }
                if(args[0].equals("sacrificios")){
                    commands.add("modify");
                    commands.add("clear");
                    commands.add("reset");
                }else if(args[0].equals("debug")){
                    commands.add("blastStormStart");
                    commands.add("blackStormStart");
                    commands.add("totemTest");
                }else if(args[0].equals("give")){
                    String[] items = {
                      "FUNGAL_CLUMPS","WEIRD_DAGGER", "CATACLYSM_PEARL", "BLOOD_SABER", "BERSERKER_TOTEM", "CRYSTAL_HEART", "DISCORD", "CLOUDY_MARSH", "BLOOD_STONE", "BLOOD_SHARD", "TEMPERATURE_METER", "TOTEM_RESTORER", "FROSTBITE","CELULA_ENERGIA","METAL_DESC","EXO_SHIELD","ICE_SHOT","BLOOD_ARMOR","EXO_DRILL"
                    };

                    commands.addAll(Arrays.asList(items));
                }
                StringUtil.copyPartialMatches(args[1], commands, completions);
            }else if(args.length == 3){
                if(args[1].equals("modify") || args[1].equals("clear") || args[1].equals("reset")){
                   Bukkit.getOnlinePlayers().forEach(player -> commands.add(player.getName()));
                }
                StringUtil.copyPartialMatches(args[2], commands, completions);
            }else if(args.length == 4){
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(args[2].equals(player.getName())){
                        String[] ints = {
                                "1", "2", "3", "4", "5"
                        };

                        commands.addAll(Arrays.asList(ints));
                    }
                }
                StringUtil.copyPartialMatches(args[3], commands, completions);
            }
        }


        Collections.sort(completions);

        return completions;
    }
}
