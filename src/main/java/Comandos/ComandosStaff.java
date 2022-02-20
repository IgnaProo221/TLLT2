package Comandos;

import CustomMobs.HostileTest;
import Eventos.StartBlastStormEvent;
import Eventos.StopBlastStormEvent;
import Extras.EventosItems;
import Extras.Items;
import Extras.Teams;
import Utilidades.Format;
import Utilidades.Warn;
import net.minecraft.server.level.WorldServer;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tlldos.tll2.TLL2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static Utilidades.Format.format;

public class ComandosStaff  implements CommandExecutor, TabCompleter {

    private final TLL2 plugin;

    public ComandosStaff(TLL2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command alias, @NotNull String string, @NotNull String[] args) {
        if(!(sender instanceof Player player)) {
            Bukkit.getLogger().info("No puedes usar comandos en la consola, pidele ayuda a un staff dentro del juego!");
            return false;
        }

        if(!sender.isOp()) {
            sender.sendMessage(Format.PREFIX + "¡No puedes usar este comando!");

            return false;
        }

        if(args.length < 1) {
            sender.sendMessage(Format.PREFIX + "Uso del comando incorrecto, usa: /tllstaff commandList");
            return false;
        }

        var data = player.getPersistentDataContainer();

        switch (args[0]) {
            case "sacrificios" -> {
                if (args.length < 2) {
                    sender.sendMessage(Format.PREFIX + "Debes colocar un subcomando valido (modify, clear, reset).");
                    return false;
                }

                if (args[1].equalsIgnoreCase("modify")) {
                    try {
                        if (args.length < 4) {
                            sender.sendMessage(Format.PREFIX + "Debes colocar un argumentos validos. Uso del comando: /tllstaff sacrificios modify <player> <sacrificios>");
                            return false;
                        }

                        Player target = Bukkit.getPlayer(args[2]);
                        int modify = Integer.parseInt(args[3]);

                        if (target == null) {
                            sender.sendMessage(Format.PREFIX + Format.format("El usuario mencionado no se encuentra dentro del servidor."));
                            return true;
                        }

                        if (!data.has(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER)) {
                            sender.sendMessage(Format.PREFIX + Format.format("&cEl usuario mencionado no tiene ningún sacrificio."));
                            return true;
                        }

                        if (modify > 5) {
                            sender.sendMessage(Format.PREFIX + Format.format("&cTe pasaste el número de sacrificios. Revisa de nuevo."));
                            return true;
                        }

                        data.set(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER, modify);

                        sender.sendMessage(Format.PREFIX + Format.format(String.format("Se ha modificado los sacrifios de &6%s &7a &6%d.", target.getName(), modify)));

                        return true;
                    } catch (NumberFormatException e) {
                        sender.sendMessage(Format.PREFIX + "Debes colocar un número valido.");
                    }
                }

            }
            case "dimension" -> {
                if (args.length < 2) {
                    sender.sendMessage(Format.PREFIX + "Debes asignar una dimensión.");
                    return false;
                }

                var world = Bukkit.getWorld(args[1]);

                if (world == null) {
                    sender.sendMessage(Format.PREFIX + "El mundo no existe!");

                    return false;
                }

                player.sendMessage(Format.PREFIX + String.format("Fuiste tepeado a la dimensión %s", world.getName()));
                player.teleport(world.getSpawnLocation());
            }
            case "alerta" -> Bukkit.getLogger().info("Alguien hizo el coso de Alerta @Mutant te llaman xdxdxd");
            case "totem_bar" -> {
                var totems = data.get(new NamespacedKey(plugin, "TOTEM_BAR"), PersistentDataType.INTEGER);

                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                player.sendMessage(Format.PREFIX + format("&7&l¡Tienes &e&l" + totems + "% &7&lporcentaje de Totems!"));

            }
            case "vida_reset" -> {
                var health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                player.sendMessage(Format.PREFIX + format("&eHas reiniciado tu vida correctamente."));

                if (health != null)
                    health.setBaseValue(20.0D);

            }
            case "god_mode" -> {
                if (args.length < 2) {
                    player.sendMessage(Format.PREFIX + "Debes colocar un subcomando valido.");
                    return false;
                }

                var immunityKey = new NamespacedKey(plugin, "inmunity");
                var immunity = data.get(immunityKey, PersistentDataType.INTEGER);

                if (immunity == null) {
                    data.set(immunityKey, PersistentDataType.INTEGER, 1);
                    player.sendMessage(Format.PREFIX + format("¡&7Se ha activo el modo &cDios!"));

                    return false;
                }

                var activate = (immunity == 1);

                if (args[1].equalsIgnoreCase("on")) {
                    if (activate) {
                        player.sendMessage(Format.PREFIX + format("¡&7El modo &cDios &7ya esta activo!"));
                        return false;
                    }

                    data.set(immunityKey, PersistentDataType.INTEGER, 1);
                    player.sendMessage(Format.PREFIX + format("¡&7Se ha activo el modo &cDios!"));
                } else if (args[1].equalsIgnoreCase("off")) {
                    if (!activate) {
                        player.sendMessage(Format.PREFIX + format("¡&7El modo &cDios &7ya esta desactivado!"));
                        return false;
                    }

                    data.set(immunityKey, PersistentDataType.INTEGER, 0);
                    player.sendMessage(Format.PREFIX + format("¡&7Se ha desactivado el modo &cDios!"));
                }
            }
            case "totems_clear" -> {
                try {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    player.sendMessage(Format.PREFIX + format("&eHas reiniciado tu porcentaje de tótems."));

                    player.getPersistentDataContainer().set(new NamespacedKey(plugin, "TOTEM_BAR"), PersistentDataType.INTEGER, 100);
                } catch (Exception e) {
                    e.printStackTrace();
                    Warn.Mutant(e);

                    player.sendMessage(Format.format(Format.PREFIX + "&7¡Ha ocurrido un &c&lerror &7al resetear los tótems."));
                }
            }
            case "temperatura" -> {
                var temperatureKey = new NamespacedKey(plugin, "temperatura");

                if (args.length < 2) {
                    player.sendMessage(Format.PREFIX + "¡Debes colocar un subcomando valido!");
                    return true;
                }

                if (args[1].equalsIgnoreCase("clear")) {
                    player.sendMessage(Format.PREFIX + format("&7Reiniciaste tu Temperatura a 30°"));
                    data.set(temperatureKey, PersistentDataType.INTEGER, 30);
                } else if (args[1].equalsIgnoreCase("hipotermia")) {
                    if (args[2].equalsIgnoreCase("1")) {
                        player.sendMessage(Format.PREFIX + format("&7Pusisiste tu Temperatura a -70°"));
                        data.set(temperatureKey, PersistentDataType.INTEGER, -70);
                    } else if (args[2].equalsIgnoreCase("2")) {
                        player.sendMessage(Format.PREFIX + format("&7Pusisiste tu Temperatura a -120°"));
                        data.set(temperatureKey, PersistentDataType.INTEGER, -120);
                    } else if (args[2].equalsIgnoreCase("3")) {
                        player.sendMessage(Format.PREFIX + format("&7Pusisiste tu Temperatura a -180°"));
                        data.set(temperatureKey, PersistentDataType.INTEGER, -180);
                    }
                } else if (args[1].equalsIgnoreCase("hipertermia")) {
                    if (args[2].equalsIgnoreCase("1")) {
                        player.sendMessage(Format.PREFIX + format("&7Pusisiste tu Temperatura a 120°"));
                        data.set(temperatureKey, PersistentDataType.INTEGER, 120);
                    } else if (args[2].equalsIgnoreCase("2")) {
                        player.sendMessage(Format.PREFIX + format("&7Pusisiste tu Temperatura a 180°"));
                        data.set(temperatureKey, PersistentDataType.INTEGER, 180);
                    } else if (args[2].equalsIgnoreCase("3")) {
                        player.sendMessage(Format.PREFIX + format("&7Pusisiste tu Temperatura a 220°"));
                        data.set(temperatureKey, PersistentDataType.INTEGER, 220);
                    }
                }
            }
            case "teams" -> {
                if (args.length < 2) {
                    player.sendMessage(Format.PREFIX + format("&cHas indicado un subcomando invalido."));
                    return true;
                }

                if (args[1].equalsIgnoreCase("chat")) {
                    for (Player a : Bukkit.getOnlinePlayers()) {
                        if (Teams.forPlayer(player).getMembers().contains(a)) {
                            StringBuilder msg = new StringBuilder();

                            for (String arg : args) {
                                if (!arg.equalsIgnoreCase(args[0]) && !arg.equalsIgnoreCase(args[1])) {

                                    msg.append(" ").append(arg);
                                }
                            }

                            a.sendMessage(format("&8[&6&lTEAM&8] &7" + player.getName() + "&8>&d" + msg));
                        }
                    }
                }

                if (args[1].equalsIgnoreCase("info")) {

                    if (!Teams.isInTeam(player)) {
                        player.sendMessage(Format.PREFIX + format("&7Actualmente no conformas ningún team."));
                        return false;
                    }

                    Teams.Team team = Teams.get(Teams.getTeamName(player));

                    StringBuilder members = new StringBuilder();

                    for (Player var1 : Bukkit.getOnlinePlayers()) {

                        if (members.length() > 0) {
                            members.append('\n');
                        }

                        members.append(format("&7- &a" + var1.getName()));
                    }

                    player.sendMessage(format("&aEstas viendo la información de tu Team actual."));
                    player.sendMessage(format("&8- &7Nombre del Team: &6" + team.getName()));
                    player.sendMessage(format("&8- &7Owner del Team: &6" + team.getOwner()));
                    player.sendMessage(format("&8- &7Numero de miembros: &6" + team.getSize()));
                    player.sendMessage(format("&8- &7Miembros:"));
                    player.sendMessage(format(members.toString()));
                }

                if (args[1].equalsIgnoreCase("join")) {
                    Player a = Bukkit.getPlayer(args[2]);

                    Teams.Team selected = Teams.get(args[3]);

                    boolean isValid = false;

                    for (String t : Teams.allTeams()) {
                        if (Teams.get(t) != null) {
                            isValid = true;
                        }
                    }

                    if (isValid) {

                        player.sendMessage(Format.PREFIX + format("&7El team que has seleccionado no es valido."));

                        return false;
                    }

                    if (a != null) {
                        selected.joinMember(a.getName(), false);

                        player.sendMessage(Format.PREFIX + format("&eHas cambiado al jugador &b" + a.getName() + "&e al Team: &7" + selected.getName()));
                    } else {

                        player.sendMessage(Format.PREFIX + format("&7El jugador indicado es invalido."));
                        return false;
                    }
                }

                if (args[1].equalsIgnoreCase("remove")) {

                    Player b = Bukkit.getPlayer(args[2]);

                    if (b != null && Teams.isInTeam(b)) {

                        Teams.Team g = Teams.get(Teams.getTeamName(b));

                        g.loosePlayer(b.getName());

                        player.sendMessage(format("&7Has eliminado al jugador &e" + b.getName() + "&7 de su Team. &8(&5" + g.getName() + "&8)"));
                    }
                }


            }
            case "debug" -> {
                if (args.length < 2) {
                    player.sendMessage(Format.PREFIX + "Debes colocar un debug valido (blastStormStart, blackStormEnd, totemTest, muerteFake).");
                    return true;
                }

                if (args[1].equalsIgnoreCase("blastStormStart")) {
                    Bukkit.getPluginManager().callEvent(new StartBlastStormEvent());
                } else if (args[1].equalsIgnoreCase("blackStormEnd")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear");

                    Bukkit.getPluginManager().callEvent(new StopBlastStormEvent(StopBlastStormEvent.Cause.COMMAND));
                } else if (args[1].equalsIgnoreCase("totemTest")) {
                    player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 10.0F, 1.0F);
                    player.playEffect(EntityEffect.TOTEM_RESURRECT);
                } else if (args[1].equalsIgnoreCase("muerteFake")) {
                    EventosItems.animacion(player, player);
                } else if (args[1].equalsIgnoreCase("dementeTest")) {
                    plugin.demente(player, player);
                }
            }
            case "sacrificios_test" -> Bukkit.getLogger().info("xd");
            case "spawn" -> {
                if(args.length < 2){
                    player.sendMessage(Format.PREFIX + ChatColor.YELLOW + "¡Debes colocar un mob!");
                    return false;
                }

                if(args[1].equalsIgnoreCase("HOSTILE_COW")){
                    try {
                        HostileTest hostileTest = new HostileTest(player.getLocation());
                        WorldServer worldServer = ((CraftWorld) player.getLocation().getWorld()).getHandle();

                        worldServer.addEntity(hostileTest, CreatureSpawnEvent.SpawnReason.CUSTOM);

                        player.sendMessage(Format.PREFIX + ChatColor.YELLOW + "¡Invocaste al mob correctamente!");
                    }catch (Exception e){
                        e.printStackTrace();
                        Warn.Mutant(e);
                    }
                }else{
                    player.sendMessage(Format.PREFIX + ChatColor.YELLOW + "¡Debes colocar un mob valido!");
                    return false;
                }
            }

            case "give" -> {
                if(args.length < 2){
                    player.sendMessage(Format.PREFIX + ChatColor.YELLOW + "Debes asignar un item que quieres tener.");
                    return false;
                }
                player.sendMessage(Format.PREFIX + ChatColor.YELLOW + "¡Has recibido el item! (Si no lo tienes en tu inventario es porque probablemente tengas el inventario lleno).");

                if (args[1].equalsIgnoreCase("FUNGAL_CLUMPS")) {
                    player.getInventory().addItem(Items.fungalClumps());
                } else if (args[1].equalsIgnoreCase("WEIRD_DAGGER")) {
                    player.getInventory().addItem(Items.createDaga());
                } else if (args[1].equalsIgnoreCase("CATACLYSM_PEARL")) {
                    player.getInventory().addItem(Items.cataclysmPearl());
                } else if (args[1].equalsIgnoreCase("BLOOD_SABER")) {
                    player.getInventory().addItem(Items.bloodSaber());
                } else if (args[1].equalsIgnoreCase("BERSERKER_TOTEM")) {
                    player.getInventory().addItem(Items.totemBerserk());
                } else if (args[1].equalsIgnoreCase("CRYSTAL_HEART")) {
                    player.getInventory().addItem(Items.crystalHeart());
                } else if (args[1].equalsIgnoreCase("DISCORD")) {
                    player.getInventory().addItem(Items.varaDis());
                } else if (args[1].equalsIgnoreCase("CLOUDY_MARSH")) {
                    player.getInventory().addItem(Items.cloudMarsh());
                } else if (args[1].equalsIgnoreCase("BLOOD_STONE")) {
                    player.getInventory().addItem(Items.bloodShard());
                } else if (args[1].equalsIgnoreCase("BLOOD_SHARD")) {
                    player.getInventory().addItem(Items.createFragmentoSangre(1));
                } else if (args[1].equalsIgnoreCase("TEMPERATURE_METER")) {
                    player.getInventory().addItem(Items.termometroItem());
                }else if (args[1].equalsIgnoreCase("TOTEM_RESTORER")) {
                    player.getInventory().addItem(Items.totemRestorer());
                }else if (args[1].equalsIgnoreCase("FROSTBITE")) {
                    player.getInventory().addItem(Items.fallenSword());
                }else if (args[1].equalsIgnoreCase("CELULA_ENERGIA")) {
                    player.getInventory().addItem(Items.celulaEnergia());
                }else if (args[1].equalsIgnoreCase("METAL_DESC")) {
                    player.getInventory().addItem(Items.metaldes());
                }else if (args[1].equalsIgnoreCase("EXO_SHIELD")) {
                    player.getInventory().addItem(Items.exoShield());
                }else if (args[1].equalsIgnoreCase("ICESHOT")) {
                    player.getInventory().addItem(Items.iceShot());
                }else if (args[1].equalsIgnoreCase("BLOOD_ARMOR")) {
                    player.getInventory().addItem(Items.bloodyHelmet());
                    player.getInventory().addItem(Items.bloodyChestplate());
                    player.getInventory().addItem(Items.bloodyLeggings());
                    player.getInventory().addItem(Items.bloodyBoots());
                }else if (args[1].equalsIgnoreCase("EXO_DRILL")) {
                    player.getInventory().addItem(Items.exoDrill());
                }else if(args[1].equalsIgnoreCase("EXO_TOTEM")){
                    player.getInventory().addItem(Items.exoTotem());
                }else if(args[1].equalsIgnoreCase("PYROCROSS")){
                    player.getInventory().addItem(Items.pyroCross());
                }else if(args[1].equalsIgnoreCase("COOLER_FRUIT")){
                    player.getInventory().addItem(Items.temperatureCooler());
                }else if(args[1].equalsIgnoreCase("HOT_FRUIT")){
                    player.getInventory().addItem(Items.temperatureHot());
                }else if(args[1].equalsIgnoreCase("EXO_SWORD")){
                    player.getInventory().addItem(Items.exoSword());
                }else if(args[1].equalsIgnoreCase("EXO_BOW")){
                    player.getInventory().addItem(Items.exoBow());
                }else if(args[1].equalsIgnoreCase("EXO_ARMOR")){
                    player.getInventory().addItem(Items.exoHelmet());
                    player.getInventory().addItem(Items.exoChestplate());
                    player.getInventory().addItem(Items.exoLeggings());
                    player.getInventory().addItem(Items.exoBoots());
                }else if(args[1].equalsIgnoreCase("INMUNITY_SIGIL")){
                    player.getInventory().addItem(Items.sigilodeInmunidad());
                }else if(args[1].equalsIgnoreCase("ANCIENT_TOME_1")){
                    player.getInventory().addItem(Items.teleTome());
                }else if(args[1].equalsIgnoreCase("ANCIENT_TOME_2")){
                    player.getInventory().addItem(Items.smeltTome());
                }else if(args[1].equalsIgnoreCase("ANCIENT_TOME_3")){
                    player.getInventory().addItem(Items.lucktreeTome());
                }else if(args[1].equalsIgnoreCase("ANCIENT_TOME_4")){
                    player.getInventory().addItem(Items.revengeTome());
                }
            }

            default -> sender.sendMessage(Format.PREFIX + "Uso del comando incorrecto, usa: /tllstaff commandList");
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] args) {
        List<String> commands = new ArrayList<>();

        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            addToList(commands, "sacrificios", "alerta", "give", "sacrificios_test", "debug", "totem_bar", "totems_clear", "vida_reset", "dimension", "temperatura", "spawn", "teams", "god_mode" );

            StringUtil.copyPartialMatches(args[0], commands, completions);
        } else if (args.length == 2) {
            switch (args[0]) {
                case "dimension" -> Bukkit.getWorlds().forEach(world -> commands.add(world.getName()));
                case "sacrificios" -> addToList(commands, "modify", "clear", "reset");
                case "god_mode" -> addToList(commands, "on", "off");
                case "teams" -> addToList(commands, "chat", "join");
                case "debug" -> addToList(commands, "blastStormStart", "blackStormEnd", "totemTest", "muerteFake", "dementeTest");
                case "temperatura" -> addToList(commands, "clear", "hipotermia", "hipertermia");
                case "give" -> addToList(commands, "FUNGAL_CLUMPS","WEIRD_DAGGER", "CATACLYSM_PEARL", "BLOOD_SABER", "BERSERKER_TOTEM", "CRYSTAL_HEART", "DISCORD", "CLOUDY_MARSH", "BLOOD_STONE", "BLOOD_SHARD", "TEMPERATURE_METER", "TOTEM_RESTORER", "FROSTBITE","CELULA_ENERGIA","METAL_DESC","EXO_SHIELD","ICESHOT","BLOOD_ARMOR","EXO_DRILL",
                        "EXO_TOTEM","PYROCROSS","COOLER_FRUIT","HOT_FRUIT","EXO_SWORD","EXO_BOW","EXO_ARMOR","INMUNITY_SIGIL","ANCIENT_TOME_1");
            }

            StringUtil.copyPartialMatches(args[1], commands, completions);
        }else if(args.length == 3){
            switch (args[1]) {
                case "modify", "clear", "reset", "join" -> Bukkit.getOnlinePlayers().forEach(player -> commands.add(player.getName()));
                case "hipertermia", "hipotermia" -> addToList(commands, "1", "2", "3");
            }
            StringUtil.copyPartialMatches(args[2], commands, completions);
        }else if(args.length == 4){
            for(Player player : Bukkit.getOnlinePlayers()) {
                if (args[2].equals(player.getName()) && args[1].equalsIgnoreCase("join")) addToList(commands, Teams.allTeams().toString());
                if (args[2].equals(player.getName()))
                    addToList(commands, "1", "2", "3", "4", "5");
            }
            StringUtil.copyPartialMatches(args[3], commands, completions);
        }

        Collections.sort(completions);

        return completions;
    }

    private void addToList(List<String> list, String... commands) {
        list.addAll(Arrays.asList(commands));
    }
}
