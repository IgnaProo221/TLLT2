package Comandos;

import Utilidades.Format;
import Utilidades.TotemsBar;
import Utilidades.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tlldos.tll2.TLL2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComandosUsuarios implements CommandExecutor, TabCompleter {

    private final TLL2 plugin;

    public ComandosUsuarios(TLL2 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.getServer().getConsoleSender().sendMessage("Eres jiro o te haces");
            return true;
        }

        Server s = sender.getServer();

        if(args.length < 1){
            player.sendMessage(Format.PREFIX + "Debes colocar un comando valido.");
            return false;
        }

        if (args[0].equalsIgnoreCase("info")) {

            TextComponent creditos = new TextComponent();
            creditos.setText(ChatColor.YELLOW + "¡Creditos! (has click aquí)");
            creditos.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/thelastlife creditos"));

            TextComponent listadecmd = new TextComponent();
            listadecmd.setText(ChatColor.YELLOW + "Lista de Comandos (has click aqui)");
            listadecmd.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/thelastlife comandoslista"));
            sender.sendMessage(ChatColor.GOLD + "----- TLL T2 -----");
            sender.sendMessage(ChatColor.YELLOW + "Plugin para THE LAST LIFE T2, hecho por MutantFm.");
            sender.sendMessage(ChatColor.YELLOW + "Prefix del comando: /thelastlife");
            sender.sendMessage(creditos);
            sender.sendMessage(listadecmd);
            sender.sendMessage(ChatColor.YELLOW + "Discord: https://discord.gg/PnDUcABq9m");
            sender.sendMessage(ChatColor.GOLD + "----- Version del Plugin: v1.1 -----");
        }
        if (args[0].equalsIgnoreCase("totems")) {
            sender.sendMessage(ChatColor.GOLD + "----- " + TotemsBar.percentage + " -----");
        }

        if(args[0].equalsIgnoreCase("creditos")){
            sender.sendMessage(ChatColor.DARK_GRAY + "------ CREDITOS ------");
            sender.sendMessage(ChatColor.AQUA + "MutantFm: " + ChatColor.GRAY + "Organizador de TLL.");
            sender.sendMessage(ChatColor.AQUA + "WickedDroid, ItzFel17 & LePepos: " + ChatColor.GRAY + "Desarrolladores del Plugin.");
            sender.sendMessage(ChatColor.AQUA + "JohanBigCum y Null1390: " + ChatColor.GRAY + "Hostear el Server.");
            sender.sendMessage(ChatColor.AQUA + "Carrotw, SeVeN_007, GusGus, TheSmol_T, SkarbyPalace y NovaKingdom: " + ChatColor.GRAY + "Diseños, Modelos, Texturas y Sonidos.");
            sender.sendMessage(ChatColor.AQUA + "cBaguette y wHermes: " + ChatColor.GRAY + "Estructuras del server.");
            sender.sendMessage(ChatColor.AQUA + "Storm_WaterTime, kennyelduro, 5r_i4n, Pepe_3012, Mikel, SalvaGamer, Wither y Gatin72 " + ChatColor.GRAY + "Beta Testers de TLL T2.");
            sender.sendMessage(ChatColor.AQUA + "Alex, LechugaMC, Blackstamp y Tom_" + ChatColor.GRAY + "Ayuda Principal, y Moderacion.");
            sender.sendMessage(ChatColor.DARK_GRAY + "----------------------");
        }
        if(args[0].equalsIgnoreCase("comandoslista")) {
            sender.sendMessage(ChatColor.DARK_GRAY + "------ COMANDOS ------");
            sender.sendMessage(ChatColor.GOLD + "creditos: " + ChatColor.GRAY + "Muestra los creditos de TLL");
            sender.sendMessage(ChatColor.GOLD + "receta <ITEM>: " + ChatColor.GRAY + "Muestra la receta del item seleccionado");
            sender.sendMessage(ChatColor.GOLD + "dia: " + ChatColor.GRAY + "Muestra el dia donde nos encontramos");
            sender.sendMessage(ChatColor.GOLD + "tps: " + ChatColor.GRAY + "Muestra los TPS del servidor");
            sender.sendMessage(ChatColor.GOLD + "sacrificios: " + ChatColor.GRAY + "Muestra Info sobre los Sacrificios");
            sender.sendMessage(ChatColor.DARK_GRAY + "----------------------");
        }
        if(args[0].equalsIgnoreCase("tps")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Format.PREFIX +"&cHay " + s.getTPS()[0] + " TPS."));
        }
        if(args[0].equalsIgnoreCase("dia")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Format.PREFIX + "&7Nos encontramos en el día: &6" + Utils.getDay()));
        }
        if(args[0].equalsIgnoreCase("sacrificios")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Format.PREFIX + "&7Te encuentras con: &6" + player.getPersistentDataContainer().get(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER) + "&7 sacrificios hechos."));
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> complements = new ArrayList<>();

        String[] commands = {
                "totems", "creditos", "comandoslista", "tps", "dia", "sacrificios", "info"
        };

        StringUtil.copyPartialMatches(args[0], List.of(commands), complements);

        Collections.sort(complements);

        return complements;
    }
}
