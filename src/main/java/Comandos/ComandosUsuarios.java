package Comandos;

import Eventos.Muerte;
import Utilidades.Contador;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ComandosUsuarios implements CommandExecutor{
    String prefix = ChatColor.translateAlternateColorCodes('&',"&6&lThe&c&lLast&6&lLife &7➤ ");
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.getServer().getConsoleSender().sendMessage("Eres barja o te haces");
        }

        Server s = sender.getServer();

        if (args[0].equalsIgnoreCase("info")) {

            TextComponent creditos = new TextComponent();
            creditos.setText(ChatColor.YELLOW + "Creditos! (has click aqui)");
            creditos.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/thelastlife creditos"));

            TextComponent listadecmd = new TextComponent();
            listadecmd.setText(ChatColor.YELLOW + "Lista de Comandos (has click aqui)");
            listadecmd.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/thelastlife comandoslista"));
            sender.sendMessage(ChatColor.GOLD + "----- TLL T2 -----");
            sender.sendMessage(ChatColor.YELLOW + "Plugin para THE LAST LIFE T2, hecho por Mr_StupidMutant");
            sender.sendMessage(ChatColor.YELLOW + "Prefix de Comando: /thelastlife");
            sender.sendMessage(creditos);
            sender.sendMessage(listadecmd);
            sender.sendMessage(ChatColor.YELLOW + "Discord: https://discord.gg/PnDUcABq9m");
            sender.sendMessage(ChatColor.GOLD + "----- Version del Plugin: v1.1 -----");
        }
        if(args[0].equalsIgnoreCase("creditos")){
            sender.sendMessage(ChatColor.DARK_GRAY + "------ CREDITOS ------");
            sender.sendMessage(ChatColor.AQUA + "iTsContrasMC: " + ChatColor.GRAY + "Creador de TLL");
            sender.sendMessage(ChatColor.AQUA + "WickedDroid, Mr_StupidMutant & LePepos: " + ChatColor.GRAY + "Desarrolladores del Plugin");
            sender.sendMessage(ChatColor.AQUA + "JohanBigCum: " + ChatColor.GRAY + "Hostear el Server!");
            sender.sendMessage(ChatColor.AQUA + "Carrot, Seven, GusGus y Tom_: " + ChatColor.GRAY + "Diseños, Modelos y Texturas ");
            sender.sendMessage(ChatColor.AQUA + "Mikel_Craft: " + ChatColor.GRAY + "Estructuras del server");
            sender.sendMessage(ChatColor.AQUA + "Apinga2 y Yisus " + ChatColor.GRAY + "Beta Testers de TLL T2");
            sender.sendMessage(ChatColor.AQUA + "Antonio, Wither, Alex y Admi9 " + ChatColor.GRAY + "Ayuda Principal, y Moderacion");
            sender.sendMessage(ChatColor.DARK_GRAY + "----------------------");
        }
        if(args[0].equalsIgnoreCase("comandoslista")) {
            sender.sendMessage(ChatColor.DARK_GRAY + "------ COMANDOS ------");
            sender.sendMessage(ChatColor.GOLD + "creditos: " + ChatColor.GRAY + "Muestra los creditos de TLL");
            sender.sendMessage(ChatColor.GOLD + "receta <ITEM>: " + ChatColor.GRAY + "Muestra la receta del item seleccionado");
            sender.sendMessage(ChatColor.GOLD + "mob <MOB>: " + ChatColor.GRAY + "Muestra algo de Info del Mob");
            sender.sendMessage(ChatColor.GOLD + "dia: " + ChatColor.GRAY + "Muestra el dia donde nos encontramos");
            sender.sendMessage(ChatColor.GOLD + "tps: " + ChatColor.GRAY + "Muestra los TPS del servidor");
            sender.sendMessage(ChatColor.GOLD + "sacrificios: " + ChatColor.GRAY + "Muestra la Cantidad de Sacrificios que has Hecho");
            sender.sendMessage(ChatColor.DARK_GRAY + "----------------------");
        }
        if(args[0].equalsIgnoreCase("tps")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix +"&cHay " + s.getTPS()[0] + " TPS Actuales"));
        }
        if(args[0].equalsIgnoreCase("dia")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "&7Nos Encontramos en el Dia: &6" + Muerte.Dia()));
        }
        if(args[0].equalsIgnoreCase("sacrificios")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "&7Nos Encontramos en el Dia: &6" + Contador.ContadorS.contador));
        }
        return false;
    }
}
