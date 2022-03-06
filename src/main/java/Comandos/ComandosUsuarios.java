package Comandos;

import Utilities.Format;
import Utilities.TotemsBar;
import Utilities.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
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

import static Utilities.Format.format;

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

        var data = player.getPersistentDataContainer();

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
        /*if (args[0].equalsIgnoreCase("totems")) {
            var totems = data.get(new NamespacedKey(plugin, "TOTEM_BAR"), PersistentDataType.INTEGER);

            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
            player.sendMessage(Format.PREFIX + format("&7&l¡Tienes &e&l" + totems + "% &7&lporcentaje de Totems!"));
        }*/

        if(args[0].equalsIgnoreCase("creditos")){
            sender.sendMessage(ChatColor.DARK_GRAY + "------ CREDITOS ------");
            sender.sendMessage(ChatColor.AQUA + "MutantFm: " + ChatColor.GRAY + "Organizador de TLL.");
            sender.sendMessage(ChatColor.AQUA + "WickedDroid, ItzFel17 & LePepos: " + ChatColor.GRAY + "Desarrolladores del Plugin.");
            sender.sendMessage(ChatColor.AQUA + "JohanBigCum y Null1390: " + ChatColor.GRAY + "Hostear el Server.");
            sender.sendMessage(ChatColor.AQUA + "Carrotw, SeVeN_007, GusGus, TheSmol_T, SkarbyPalace y NovaKingdom: " + ChatColor.GRAY + "Diseños, Modelos, Texturas y Sonidos.");
            sender.sendMessage(ChatColor.AQUA + "cBaguette y wHermes: " + ChatColor.GRAY + "Estructuras del server.");
            sender.sendMessage(ChatColor.AQUA + "Storm_WaterTime, kennyelduro, 5r_i4n, Pepe_3012, Mikel, SalvaGamer, Wither y Gatin72: " + ChatColor.GRAY + "Beta Testers de TLL T2.");
            sender.sendMessage(ChatColor.AQUA + "Alex, LechugaMC, Blackstamp y Tom_: " + ChatColor.GRAY + "Ayuda Principal, y Moderacion.");
            sender.sendMessage(ChatColor.AQUA + "Fabo, Diegot y OmkSpar: " + ChatColor.GRAY + "Ideas.");
            sender.sendMessage(ChatColor.DARK_GRAY + "----------------------");
        }
        if(args[0].equalsIgnoreCase("comandoslista")) {
            sender.sendMessage(ChatColor.DARK_GRAY + "------ COMANDOS ------");
            sender.sendMessage(ChatColor.GOLD + "creditos: " + ChatColor.GRAY + "Muestra los creditos de TLL");
            sender.sendMessage(ChatColor.GOLD + "dia: " + ChatColor.GRAY + "Muestra el dia donde nos encontramos");
            sender.sendMessage(ChatColor.GOLD + "tps: " + ChatColor.GRAY + "Muestra los TPS del servidor");
            sender.sendMessage(ChatColor.GOLD + "sacrificios: " + ChatColor.GRAY + "Muestra Info sobre los Sacrificios");
            sender.sendMessage(ChatColor.GOLD + "maestria: " + ChatColor.GRAY + "Muestra tu nivel de maestria");
            sender.sendMessage(ChatColor.DARK_GRAY + "----------------------");
        }
        if(args[0].equalsIgnoreCase("tps")) {
            String tps = "" + s.getTPS()[0];

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Format.PREFIX +"&cHay " + tps.substring(0, 5) + " TPS."));
        }
        if(args[0].equalsIgnoreCase("dia")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Format.PREFIX + "&7Nos encontramos en el día: &6" + Utils.getDay()));
        }
        if(args[0].equalsIgnoreCase("sacrificios")) {
            int amount;

            if (player.getPersistentDataContainer().has(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER)) {
                amount = player.getPersistentDataContainer().get(new NamespacedKey(plugin, "sacrificios"), PersistentDataType.INTEGER);
            } else {
                amount = 0;
            }

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Format.PREFIX + "&7Te encuentras con: &6" + amount + "&7 sacrificios hechos."));
        }
        if(args[0].equalsIgnoreCase("maestria")){
            var dataMaestria = data.get(new NamespacedKey(plugin,"maestrialvl"),PersistentDataType.INTEGER);
            var dataMaestriaExp = data.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);
            sender.sendMessage(format("&7-------------&b&lMAESTRIA&7-------------"));
            sender.sendMessage(format("&cNivel de Maestria: &e&l" + dataMaestria));
            sender.sendMessage(format("&cEXP de Maestria: &e&l" + dataMaestriaExp));
            sender.sendMessage(format("&7&lDaño: " + player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()));
            sender.sendMessage(format("&7&lDefensa: " + player.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue()));
            sender.sendMessage(format("&7&lVida: " + player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()));
            sender.sendMessage(format("&7----------------------------------"));
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> complements = new ArrayList<>();

        String[] commands = {
                "creditos", "comandoslista", "tps", "dia", "sacrificios", "info","maestria"
        };

        StringUtil.copyPartialMatches(args[0], List.of(commands), complements);

        Collections.sort(complements);

        return complements;
    }
}
