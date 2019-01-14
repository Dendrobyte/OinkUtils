package com.oinkcraft.oinkutils.submission;

import com.oinkcraft.oinkutils.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ConfirmationTimer
        extends BukkitRunnable
{
    private int counter = 20;
    private Player player;

    public ConfirmationTimer(Player player) {
        this.player = player;
    }

    public void run() {
        if (this.counter == 0) {
            if (SubmitCommand.getSubmitConfirmations().containsKey(this.player)) {
                this.player.sendMessage(Main.getInstance().getPrefix() + "Your submission confirmation has timed out.");
                SubmitCommand.getSubmitConfirmations().remove(this.player);
            }
        }
        else {
            this.counter -= 1;
        }
    }
}
