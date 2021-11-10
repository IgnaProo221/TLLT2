package Comandos;

import Eventos.Muerte;
import Extras.Items;
import Utilidades.GUIs;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

public class ComandosStaff implements CommandExecutor{
    String prefix = ChatColor.translateAlternateColorCodes('&',"&6&lThec&lLast&6&lLife &7➤ ");
    public static BossBar tormenta;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.getServer().getConsoleSender().sendMessage("No se puede usar comandos en consola!");
        }
        Server s = sender.getServer();
        Player pa = (Player) sender;
        if(sender.isOp()) {
            if (args[0].equalsIgnoreCase("alerta")) {
                s.getConsoleSender().sendMessage("Alguien hizo el coso de Alerta @Mutant te llaman xdxdxd");
            }
            if (args[0].equalsIgnoreCase("sacrificiostest")) {
                pa.openInventory(GUIs.getCa().build());
            }
            if (args[0].equalsIgnoreCase("give")) {
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
            }
        }else{
            sender.sendMessage(prefix + ChatColor.RED + "No tienes Permisos para Ejecutar este comando");
        }
        return false;
    }
}
