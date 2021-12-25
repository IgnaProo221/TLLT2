package Comandos;

import Eventos.Muerte;
import Eventos.StartBlastStormEvent;
import Extras.Items;
import Utilidades.Format;
import Utilidades.GUIs;
import Utilidades.Warn;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

import static Utilidades.Format.format;

public class ComandosStaff implements CommandExecutor{
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
                        pa.openInventory(GUIs.getCa().build());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Warn.Mutant(e);
                        pa.sendMessage(Format.format(prefix + "&7¡Ha ocurrido un &c&lerror &7al mandar la GUI."));
                    }
                    break;
                case "give":
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
