package Comandos;

import Eventos.StartBlastStormEvent;
import Extras.Items;
import Utilidades.Format;
import Utilidades.TotemsBar;
import Utilidades.Warn;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tlldos.tll2.TLL2;


import static Utilidades.Format.format;

public class ComandosStaff implements CommandExecutor{
    TLL2 plugin;

    String prefix = ChatColor.translateAlternateColorCodes('&',"&6&lThe&c&lLast&6&lLife &7➤ ");
    public static BossBar tormenta;


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.getServer().getConsoleSender().sendMessage("No se puede usar comandos en consola!");
        }
        Server s = sender.getServer();
        Player pa = (Player) sender;

        if(sender.isOp()) {
            switch (args[0]) {
                ///aqui no tengo idea de que hacer, intente verme un tutorial acerca de eso pero me rendi :P
                case "sacrificios":
                    if (args[1].equalsIgnoreCase("addSacrifice")) {
                        pa.sendMessage("debug");
                    }
                    if (args[1].equalsIgnoreCase("removeSacrifice")) {
                        pa.sendMessage("debug");
                    }
                    if (args[1].equalsIgnoreCase("clearSacrifice")) {
                        pa.sendMessage("debug");
                    }
                    if (args[1].equalsIgnoreCase("resetSacrifice")) {
                        pa.sendMessage("debug");
                    }
                    break;
                case "alerta":
                    s.getConsoleSender().sendMessage("Alguien hizo el coso de Alerta @Mutant te llaman xdxdxd");
                    break;

                case "testdias":
                    if (plugin.getConfig().getBoolean("dia6")) {
                        pa.sendMessage(format("&cSI ESTA EL DIA 6 GAY XD"));
                    } else {
                        pa.sendMessage(format("&cNO ESTA EL DIA 6 GAY XD"));
                    }
                    break;

                case "totemBar": {

                    int res = TotemsBar.getPorcentaje(pa);

                    String var0;

                    if (res < 50) {
                        var0 = "&7¡La barra actual se encuentra en &c&l" + res + "%";
                    } else {
                        var0 = "&7¡La barra actual se encuentra en &e&l" + res + "%";
                    }

                    pa.playSound(pa.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    pa.sendMessage(format(prefix + var0));
                }
                break;
                case "totemsclear":
                    try {
                        TotemsBar.resetAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Warn.Mutant(e);
                        pa.sendMessage(Format.format(prefix + "&7¡Ha ocurrido un &c&lerror &7al resetear los tótems."));
                    }
                    break;

                case "debug":
                    if (args[1].equalsIgnoreCase("blastStormTest")) {
                        StartBlastStormEvent start = new StartBlastStormEvent();
                        s.getPluginManager().callEvent(start);
                    }
                    if (args[1].equalsIgnoreCase("totemTest")) {
                        pa.playSound(pa.getLocation(), Sound.ITEM_TOTEM_USE, 10.0F, 1.0F);
                        pa.playEffect(EntityEffect.TOTEM_RESURRECT);
                    }
                    break;
                case "sacrificiostest":
                    try {
                        Bukkit.getLogger().info("xd");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Warn.Mutant(e);
                        pa.sendMessage(Format.format(prefix + "&7¡Ha ocurrido un &c&lerror &7al mandar la GUI."));
                    }
                    break;
                case "give":
                    if(args[1] == null){
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Debes colocar el item que te quieres givear.");
                        return false;
                    }
                    if (args[1].equalsIgnoreCase("FUNGAL_CLUMPS")) {
                        pa.getInventory().addItem(Items.FungaClu());
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                    if (args[1].equalsIgnoreCase("WEIRD_DAGGER")) {
                        pa.getInventory().addItem(Items.createDaga());
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                    if (args[1].equalsIgnoreCase("CATACLYSM_PEARL")) {
                        pa.getInventory().addItem(Items.CataclysPear());
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                    if (args[1].equalsIgnoreCase("BERSERKER_TOTEM")) {
                        pa.getInventory().addItem(Items.ToteBeserk());
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                    if (args[1].equalsIgnoreCase("CLOUDY_MARSH")) {
                        pa.getInventory().addItem(Items.CloudMarsh());
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                    if (args[1].equalsIgnoreCase("BLOOD_STONE")) {
                        pa.getInventory().addItem(Items.BloodShard());
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                    if (args[1].equalsIgnoreCase("BLOOD_SHARD")) {
                        pa.getInventory().addItem(Items.createFragmentoSangre(1));
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                    if (args[1].equalsIgnoreCase("TEMPERATURE_METER")) {
                        pa.getInventory().addItem(Items.termometroItem());
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                    if (args[1].equalsIgnoreCase("TOTEM_RESTORER")) {
                        pa.getInventory().addItem(Items.totemRestorer());
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                    if (args[1].equalsIgnoreCase("FALLEN_SWORD")) {
                        pa.getInventory().addItem(Items.fallenSword());
                        pa.sendMessage(prefix + ChatColor.YELLOW + "Has Recibido el Item! Si no lo Recibiste es por tener el Inventario lleno");
                    }
                default:
                    pa.sendMessage(format("&7No has indicado ningun subcomando."));
                    pa.sendMessage(format("&7Si no sabes los comandos ejecuta el comando /tllstaff commandlist."));
                    break;
            }
        } else {
            sender.sendMessage(prefix + ChatColor.RED + "No tienes Permisos para Ejecutar este comando");
        }
        return false;
    }
}
