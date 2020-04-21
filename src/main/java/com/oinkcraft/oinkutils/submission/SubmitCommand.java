package com.oinkcraft.oinkutils.submission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class SubmitCommand implements CommandExecutor, TabCompleter {
    private String prefix;

    public SubmitCommand() {
        this.prefix = Main.getInstance().getPrefix();
    }

    private static HashMap<Player, String> submitConfirmations = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "Sorry, you must be a player to use this command!");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("submit")) {
            if (args.length == 0) {
                sender.sendMessage(prefix + "Too few arguments provided!");
                sender.sendMessage(prefix + "Use " + GREEN + "/submit builder " + GRAY + "for Builder submissions, " + GREEN + "/submit redstone " + GRAY + "for Redstone Rank submissions, or " + GREEN + "/submit parkour" + GRAY + " for parkour map submissions.");
                return true;
            }
            if (args.length > 3) {
                sender.sendMessage(prefix + "Too many arguments provided!");
                sender.sendMessage(prefix + "Use " + GREEN + "/submit builder " + GRAY + "for Builder submissions, " + GREEN + "/submit redstone " + GRAY + "for Redstone Rank submissions, or " + GREEN + "/submit parkour" + GRAY + " for parkour map submissions.");
                return true;
            }
            if (args[0].equalsIgnoreCase("builder")) {
                String playerName = player.getName();
                if (Main.getInstance().getSubmissions().getStringList("submissions.builder").contains(playerName)) {
                    sender.sendMessage(prefix + "You already have a submission pending. Please wait for an Admin to contact you. Thanks!");
                    return true;
                }
                if (!Main.getInstance().getSubmissions().getStringList("submissions.builder").contains(playerName)) {
                    if (submitConfirmations.containsKey(player)) {
                        player.sendMessage(this.prefix + "You already have a " + submitConfirmations.get(player) + " submission pending.");
                        return true;
                    }
                    confirmBuilderSubmission(player);
                    return true;
                }
                sender.sendMessage(prefix + "Sorry, something went wrong. Please try again or contact an Admin if this problem persists.");
                return true;
            }
            if (args[0].equalsIgnoreCase("redstone")) {
                String playerName = player.getName();
                if (Main.getInstance().getSubmissions().getStringList("submissions.redstone").contains(playerName)) {
                    sender.sendMessage(prefix + "You already have a submission pending. Please wait for an Admin to contact you. Thanks!");
                    return true;
                }
                if (!Main.getInstance().getSubmissions().getStringList("submissions.redstone").contains(playerName)) {
                    if (submitConfirmations.containsKey(player)) {
                        player.sendMessage(this.prefix + "You already have a " + submitConfirmations.get(player) + " submission pending.");
                        return true;
                    }
                    confirmRedstoneSubmission(player);
                    return true;
                }
                sender.sendMessage(prefix + "Sorry, something went wrong. Please try again or contact an Admin if this problem persists.");
                return true;
            }
            if (args[0].equalsIgnoreCase("parkour")) {
                String playerName = player.getName();
                if (Main.getInstance().getSubmissions().getStringList("submissions.parkour").contains(playerName)) {
                    player.sendMessage(prefix + "You already have a submission pending. Please wait for an Admin to contact you. Thanks!");
                    return true;
                }
                if (!Main.getInstance().getSubmissions().getStringList("submissions.parkour").contains(playerName)) {
                    if (submitConfirmations.containsKey(player)) {
                        player.sendMessage(this.prefix + "You already have a " + submitConfirmations.get(player) + " submission pending.");
                        return true;
                    }
                    confirmParkourSubmission(player);
                    return true;
                }
                player.sendMessage(prefix + "Sorry, something went wrong. Please try again or contact an Admin if this problem persists.");
                return true;
            }
            if ((args[0].equalsIgnoreCase("reload")) && (sender.hasPermission("mibutils.submissions.reload"))) {
                sender.sendMessage(prefix + "Reloading the submissions.yml file...");
                Main.getInstance().reloadSubmissions();
                sender.sendMessage(prefix + "Successfully reloaded the submissions.yml file!");
                return true;
            }
            if ((args[0].equalsIgnoreCase("reload")) && (!sender.hasPermission("mibutils.submissions.reload"))) {
                sender.sendMessage(prefix + "You do not have access to " + RED + "mibutils.submissions.reload");
                return true;
            }
            if ((args[0].equalsIgnoreCase("remove")) && (sender.hasPermission("mibutils.submissions.remove"))) {
                if (args.length != 3) {
                    player.sendMessage(prefix + "Incorrect usage! /submit remove <playername> <builder/redstone/parkour>");
                    return true;
                }
                if (args[2].equalsIgnoreCase("builder")) {
                    List<String> builderNames = Main.getInstance().getSubmissions().getStringList("submissions.builder");
                    if (builderNames.contains(args[1])) {
                        builderNames.remove(args[1]);
                        Main.getInstance().getSubmissions().set("submissions.builder", builderNames);
                        Main.getInstance().saveSubmissions();
                        player.sendMessage(prefix + "Successfully removed " + args[1] + " from the " + DARK_GREEN + BOLD + "builder list.");
                        return true;
                    }
                    if (!builderNames.contains(args[1])) {
                        player.sendMessage(prefix + "Could you not find " + args[1] + " in the " + DARK_GREEN + BOLD + "Builder list.");
                        return true;
                    }
                    return true;
                }
                if (args[2].equalsIgnoreCase("redstone")) {
                    List<String> redstoneNames = Main.getInstance().getSubmissions().getStringList("submissions.redstone");
                    if (redstoneNames.contains(args[1])) {
                        redstoneNames.remove(args[1]);
                        Main.getInstance().getSubmissions().set("submissions.redstone", redstoneNames);
                        Main.getInstance().saveSubmissions();
                        player.sendMessage(prefix + "Successfully removed " + args[1] + " from the " + RED + BOLD + "redstone list.");
                        return true;
                    }
                    if (!redstoneNames.contains(args[1])) {
                        player.sendMessage(prefix + "Could not find " + args[1] + " in the " + RED + BOLD + "redstone list.");
                        return true;
                    }
                    return true;
                }
                if (args[2].equalsIgnoreCase("parkour")) {
                    List<String> parkourNames = Main.getInstance().getSubmissions().getStringList("submissions.parkour");
                    if (parkourNames.contains(args[1])) {
                        parkourNames.remove(args[1]);
                        Main.getInstance().getSubmissions().set("submissions.parkour", parkourNames);
                        Main.getInstance().saveSubmissions();
                        player.sendMessage(prefix + "Successfully removed " + args[1] + " from the " + GRAY + BOLD + "parkour list.");
                        return true;
                    }
                    if (!parkourNames.contains(args[1])) {
                        player.sendMessage(prefix + "Could not find " + args[1] + " in the " + GRAY + BOLD + "parkour list.");
                        return true;
                    }
                }
                return true;
            }
            if ((args[0].equalsIgnoreCase("list")) && (sender.hasPermission("mibutils.submissions.list"))) {
                if (args.length < 2) {
                    player.sendMessage(this.prefix + "Command usage: /submit list <builder/redstone/parkour>");
                    return true;
                }
                if (args[1].equalsIgnoreCase("builder")) {
                    player.sendMessage(this.prefix + " Players awaiting builder submissions: " + Main.getInstance().getSubmissions().getStringList("submissions.builder").toString());
                    return true;
                }
                if (args[1].equalsIgnoreCase("redstone")) {
                    player.sendMessage(this.prefix + " Players awaiting redstone submissions: " + Main.getInstance().getSubmissions().getStringList("submissions.redstone").toString());
                    return true;
                }
                if (args[1].equalsIgnoreCase("parkour")) {
                    player.sendMessage(this.prefix + " Players awaiting parkour submissions: " + Main.getInstance().getSubmissions().getStringList("submissions.parkour").toString());
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("yes")) {
                if (!submitConfirmations.containsKey(player)) {
                    player.sendMessage(this.prefix + "You have no pending submissions to confirm.");
                    return true;
                }
                if (submitConfirmations.containsKey(player)) {
                    if ((submitConfirmations.get(player)).equals("BUILDER")) {
                        addToBuilder(player);
                        submitConfirmations.remove(player, "BUILDER");
                        return true;
                    }
                    if ((submitConfirmations.get(player)).equals("REDSTONE")) {
                        addToRedstone(player);
                        submitConfirmations.remove(player, "REDSTONE");
                        return true;
                    }
                    if ((submitConfirmations.get(player)).equals("PARKOUR")) {
                        addToParkour(player);
                        submitConfirmations.remove(player, "PARKOUR");
                        return true;
                    }
                    player.sendMessage(this.prefix + "Something appears to have gone wrong. Please contact a staff member.");
                    return true;
                }
            }
            if ((args[0].equalsIgnoreCase("list")) && (!sender.hasPermission("mibutils.submissions.list"))) {
                sender.sendMessage(prefix + "You do not have access to " + RED + "mibutils.submissions.list");
                return true;
            }
            if ((args[0].equalsIgnoreCase("remove")) && (!sender.hasPermission("mibutils.submissions.remove"))) {
                sender.sendMessage(prefix + "You do not have access to " + RED + "mibutils.submissions.remove");
                return true;
            }
        }
        return true;
    }

    public static HashMap<Player, String> getSubmitConfirmations() {
        return submitConfirmations;
    }

    private void confirmBuilderSubmission(Player player) {
        player.sendMessage(this.prefix + "Please be aware of the requirements.");
        player.sendMessage(this.prefix + "Builder requirements: https://goo.gl/56ntiL");
        player.sendMessage(this.prefix + "Type /submit yes if you are sure.");
        submitConfirmations.put(player, "BUILDER");
        ConfirmationTimer ct = new ConfirmationTimer(player);
        ct.runTaskTimer(Main.getInstance(), 0L, 400L);
    }

    private void confirmRedstoneSubmission(Player player) {
        player.sendMessage(this.prefix + "Please be aware of the requirements.");
        player.sendMessage(this.prefix + "Redstone rank requirements: https://goo.gl/N6Ks9D");
        player.sendMessage(this.prefix + "Type /submit yes if you are sure.");
        submitConfirmations.put(player, "REDSTONE");
        ConfirmationTimer ct = new ConfirmationTimer(player);
        ct.runTaskTimer(Main.getInstance(), 400L, 20L);
    }

    private void confirmParkourSubmission(Player player) {
        player.sendMessage(this.prefix + "Please be aware of the requirements.");
        player.sendMessage(this.prefix + "Parkour rank requirements: [No official documentation (yet...), check in with staff]");
        player.sendMessage(this.prefix + "Type /submit yes if you are sure.");
        submitConfirmations.put(player, "PARKOUR");
        ConfirmationTimer ct = new ConfirmationTimer(player);
        ct.runTaskTimer(Main.getInstance(), 0L, 400L);
    }

    private void addToBuilder(Player player) {
        String playerName = player.getName();
        List<String> builderNames = Main.getInstance().getSubmissions().getStringList("submissions.builder");
        builderNames.add(playerName);
        Main.getInstance().getSubmissions().set("submissions.builder", builderNames);
        Main.getInstance().saveSubmissions();
        player.sendMessage(this.prefix + "You have successfully submitted for Builder!");
    }

    private void addToRedstone(Player player) {
        String playerName = player.getName();
        List<String> redstoneNames = Main.getInstance().getSubmissions().getStringList("submissions.redstone");
        redstoneNames.add(playerName);
        Main.getInstance().getSubmissions().set("submissions.redstone", redstoneNames);
        Main.getInstance().saveSubmissions();
        player.sendMessage(this.prefix + "You have successfully submitted for the Redstone Rank!");
    }

    private void addToParkour(Player player) {
        String playerName = player.getName();
        List<String> parkourNames = Main.getInstance().getSubmissions().getStringList("submissions.parkour");
        parkourNames.add(playerName);
        Main.getInstance().getSubmissions().set("submissions.parkour", parkourNames);
        Main.getInstance().saveSubmissions();

        player.sendMessage(this.prefix + "You have successfully submitted your parkour map!");
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> tabCompleteList = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("submit")) {
            if (args.length == 1) {
                if (!submitConfirmations.containsKey(commandSender)) {
                    tabCompleteList.add("builder");
                    tabCompleteList.add("redstone");
                    tabCompleteList.add("parkour");
                }
                tabCompleteList.add("yes");
                if (commandSender.hasPermission("mibutils.submissions.reload")) {
                    tabCompleteList.add("reload");
                }
                if (commandSender.hasPermission("mibutils.submissions.list")) {
                    tabCompleteList.add("list");
                }
                if (commandSender.hasPermission("mibutils.submissions.remove")) {
                    tabCompleteList.add("remove");
                }

                tabCompleteList.removeIf(item -> !item.startsWith(args[0]));
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("list") && commandSender.hasPermission("mibutils.submissions.list")) {
                    tabCompleteList.add("builder");
                    tabCompleteList.add("redstone");
                    tabCompleteList.add("parkour");
                }
                if (args[0].equalsIgnoreCase("remove") && commandSender.hasPermission("mibutils.submissions.remove")) {
                    tabCompleteList.addAll(Main.getInstance().getSubmissions().getStringList("submissions.builder"));
                    tabCompleteList.addAll(Main.getInstance().getSubmissions().getStringList("submissions.redstone"));
                    tabCompleteList.addAll(Main.getInstance().getSubmissions().getStringList("submissions.parkour"));
                    tabCompleteList.sort(String::compareToIgnoreCase);

                    tabCompleteList.removeIf(item -> !item.startsWith(args[1]));
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("remove")) {
                    if (Main.getInstance().getSubmissions().getStringList("submissions.builder").contains(args[1])) tabCompleteList.add("builder");
                    if (Main.getInstance().getSubmissions().getStringList("submissions.redstone").contains(args[1])) tabCompleteList.add("redstone ");
                    if (Main.getInstance().getSubmissions().getStringList("submissions.parkour").contains(args[1])) tabCompleteList.add("parkour");
                    tabCompleteList.removeIf(item -> !item.startsWith(args[2]));

                }
            }
        }
        return tabCompleteList;
    }
}
