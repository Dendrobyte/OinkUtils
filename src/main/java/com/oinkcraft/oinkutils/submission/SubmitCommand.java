package com.oinkcraft.oinkutils.submission;

import java.util.HashMap;
import java.util.List;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SubmitCommand implements CommandExecutor {
    private String prefix;

    public SubmitCommand() {
        this.prefix = Main.getInstance().getPrefix();
    }

    private static HashMap<Player, String> submitConfirmations = new HashMap();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (!(sender instanceof Player)) {
            sender.sendMessage("�cSorry, you must be a player to use this command!");
            return false;
        }
        if (command.getName().equalsIgnoreCase("submit")) {
            if (args.length == 0) {
                sender.sendMessage("�8<�2MibUtils�8>�7 �cToo few arguments provided!");
                sender.sendMessage("�8<�2MibUtils�8>�7 �cUse �a/submit builder �cfor Builder submissions, �a/submit redstone �cfor Redstone Rank submissions, or �a/submit parkour�c for parkour map submissions.");
                return false;
            }
            if (args.length > 3) {
                sender.sendMessage("�8<�2MibUtils�8>�7 �cToo many arguments provided!");
                sender.sendMessage("�8<�2MibUtils�8>�7 �cUse �a/submit builder �cfor Builder submissions, �a/submit redstone �cfor Redstone Rank submissions, or �a/submit parkour�c for parkour map submissions.");
                return false;
            }
            if (args[0].equalsIgnoreCase("builder")) {
                String playerName = player.getName();
                if (Main.getInstance().getSubmissions().getStringList("submissions.builder").contains(playerName)) {
                    sender.sendMessage("�8<�2MibUtils�8>�7 �cYou already have a submission pending. Please wait for an Admin to contact you. Thanks!");
                    return false;
                }
                if (!Main.getInstance().getSubmissions().getStringList("submissions.builder").contains(playerName)) {
                    if (submitConfirmations.containsKey(player)) {
                        player.sendMessage(this.prefix + "You already have a " + (String)submitConfirmations.get(player) + " submission pending.");
                        return true;
                    }
                    confirmBuilderSubmission(player);
                    return true;
                }
                sender.sendMessage("�8<�2MibUtils�8>�7 �cSorry, something went wrong. Please try again or contact an Admin if this problem persists.");
                return true;
            }
            if (args[0].equalsIgnoreCase("redstone")) {
                String playerName = player.getName();
                if (Main.getInstance().getSubmissions().getStringList("submissions.redstone").contains(playerName)) {
                    sender.sendMessage("�8<�2MibUtils�8>�7 �cYou already have a submission pending. Please wait for an Admin to contact you. Thanks!");
                    return false;
                }
                if (!Main.getInstance().getSubmissions().getStringList("submissions.redstone").contains(playerName)) {
                    if (submitConfirmations.containsKey(player)) {
                        player.sendMessage(this.prefix + "You already have a " + (String)submitConfirmations.get(player) + " submission pending.");
                        return true;
                    }
                    confirmRedstoneSubmission(player);
                    return true;
                }
                sender.sendMessage("�8<�2MibUtils�8>�7 �cSorry, something went wrong. Please try again or contact an Admin if this problem persists.");
                return true;
            }
            if (args[0].equalsIgnoreCase("parkour")) {
                String playerName = player.getName();
                if (Main.getInstance().getSubmissions().getStringList("submissions.parkour").contains(playerName)) {
                    player.sendMessage("�8<�2MibUtils�8>�7 �cYou already have a submission pending. Please wait for an Admin to contact you. Thanks!");
                    return false;
                }
                if (!Main.getInstance().getSubmissions().getStringList("submissions.parkour").contains(playerName)) {
                    if (submitConfirmations.containsKey(player))
                    {
                        player.sendMessage(this.prefix + "You already have a " + (String)submitConfirmations.get(player) + " submission pending.");
                        return true;
                    }
                    confirmParkourSubmission(player);
                    return true;
                }
                player.sendMessage("�8<�2MibUtils�8>�7 �cSorry, something went wrong. Please try again or contact an Admin if this problem persists.");
                return true;
            }
            if ((args[0].equalsIgnoreCase("reload")) && (sender.hasPermission("mibutils.submissions.reload"))) {
                sender.sendMessage("�8<�2MibUtils�8>�7 �7Reloading the submissions.yml file...");
                Main.getInstance().reloadSubmissions();
                sender.sendMessage("�8<�2MibUtils�8>�7 �7Successfully reloaded the submissions.yml file!");
                return true;
            }
            if ((args[0].equalsIgnoreCase("reload")) && (!sender.hasPermission("mibutils.submissions.reload"))) {
                sender.sendMessage("�8<�2MibUtils�8>�7 You do not have access to �cmibutils.submissions.reload");
                return true;
            }
            if ((args[0].equalsIgnoreCase("remove")) && (sender.hasPermission("mibutils.submissions.remove"))) {
                if ((args.length < 3) || (args.length > 3)) {
                    player.sendMessage("�8<�2MibUtils�8>�7 �cIncorrect usage! /submit remove <playername> <builder/redstone/parkour>");
                    return false;
                }
                if (args.length == 3) {
                    if (args[2].equalsIgnoreCase("builder")) {
                        List<String> builderNames = Main.getInstance().getSubmissions().getStringList("submissions.builder");
                        if (builderNames.contains(args[1])) {
                            builderNames.remove(args[1]);
                            Main.getInstance().getSubmissions().set("submissions.builder", builderNames);
                            Main.getInstance().saveSubmissions();
                            player.sendMessage("�8<�2MibUtils�8>�7 �aSuccessfully removed " + args[1] + " from the �2�lbuilder�r�a list.");
                            return true;
                        }
                        if (!builderNames.contains(args[1])) {
                            player.sendMessage("�8<�2MibUtils�8>�7 �cCould you not find " + args[1] + " in the �2�lbuilder�r�c list.");
                            return false;
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("redstone")) {
                        List<String> redstoneNames = Main.getInstance().getSubmissions().getStringList("submissions.redstone");
                        if (redstoneNames.contains(args[1])) {
                            redstoneNames.remove(args[1]);
                            Main.getInstance().getSubmissions().set("submissions.redstone", redstoneNames);
                            Main.getInstance().saveSubmissions();
                            player.sendMessage("�8<�2MibUtils�8>�7 �aSuccessfully removed " + args[1] + " from the �4�lredstone�r�a list.");
                            return true;
                        }
                        if (!redstoneNames.contains(args[1])) {
                            player.sendMessage("�8<�2MibUtils�8>�7 �cCould not find " + args[1] + " in the �4�lredstone�r�c list.");
                            return false;
                        }
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("parkour")) {
                        List<String> parkourNames = Main.getInstance().getSubmissions().getStringList("submissions.parkour");
                        if (parkourNames.contains(args[1])) {
                            parkourNames.remove(args[1]);
                            Main.getInstance().getSubmissions().set("submissions.parkour", parkourNames);
                            Main.getInstance().saveSubmissions();
                            player.sendMessage("�8<�2MibUtils�8>�7 �aSuccessfully removed " + args[1] + " from the �7�lparkour�r�a list.");
                            return true;
                        }
                        if (!parkourNames.contains(args[1])) {
                            player.sendMessage("�8<�2MibUtils�8>�7 �cCould not find " + args[1] + " in the �7�lparkour�r�a list.");
                            return false;
                        }
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
                    if (((String)submitConfirmations.get(player)).equals("BUILDER")) {
                        addToBuilder(player);
                        return true;
                    }
                    if (((String)submitConfirmations.get(player)).equals("REDSTONE")) {
                        addToRedstone(player);
                        return true;
                    }
                    if (((String)submitConfirmations.get(player)).equals("PARKOUR")) {
                        addToParkour(player);
                        return true;
                    }
                    player.sendMessage(this.prefix + "Something appears to have gone wrong. Please contact a staff member.");
                    return true;
                }
            }
            if ((args[0].equalsIgnoreCase("list")) && (!sender.hasPermission("mibutils.submissions.list"))) {
                sender.sendMessage("�8<�2MibUtils�8>�7 �7You do not have access to �cmibutils.submissions.list");
                return false;
            }
            if ((args[0].equalsIgnoreCase("remove")) && (!sender.hasPermission("mibutils.submissions.remove"))) {
                sender.sendMessage("�8<�2MibUtils�8>�7 �7You do not have access to �cmibutils.submissions.remove");
                return false;
            }
        }
        return false;
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
        ct.runTaskTimer(Main.getInstance(), 0L, 20L);
    }

    private void confirmRedstoneSubmission(Player player) {
        player.sendMessage(this.prefix + "Please be aware of the requirements.");
        player.sendMessage(this.prefix + "Redstone rank requirements: https://goo.gl/N6Ks9D");
        player.sendMessage(this.prefix + "Type /submit yes if you are sure.");
        submitConfirmations.put(player, "REDSTONE");
        ConfirmationTimer ct = new ConfirmationTimer(player);
        ct.runTaskTimer(Main.getInstance(), 0L, 20L);
    }

    private void confirmParkourSubmission(Player player) {
        player.sendMessage(this.prefix + "Please be aware of the requirements.");
        player.sendMessage(this.prefix + "Parkour rank requirements: [No official documentation (yet...), check in with staff]");
        player.sendMessage(this.prefix + "Type /submit yes if you are sure.");
        submitConfirmations.put(player, "PARKOUR");
        ConfirmationTimer ct = new ConfirmationTimer(player);
        ct.runTaskTimer(Main.getInstance(), 0L, 20L);
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
}
