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
                default:
                    pa.sendMessage("&7No has indicado ningun subcomando.");
                    break;
            }
        } else {
            sender.sendMessage(prefix + ChatColor.RED + "No tienes Permisos para Ejecutar este comando");
        }
        return false;
    }
}
