package tower.commands;

import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.ui.Menus;
import tower.Bundle;

public class Info {

    private static final int menu = Menus.registerMenu(Info::handleMenuOption);
    private static final String[][] buttons = {
            { "[gray]Close" },
            { "[accent]Next" }
    };
    private static int currentMessageIndex = 0;
    private static String[] messages;

    public static void execute(Player player) {

        initializeMessages(player.locale);
        openGui(player);
    }

    public static void openGui(Player player) {

        Call.menu(player.con, menu, Bundle.get("settings.title", player.locale), messages[currentMessageIndex],
                buttons);
    }

    private static void nextMessage(Player player) {
        currentMessageIndex = (currentMessageIndex + 1) % messages.length;

        openGui(player);
    }

    private static void initializeMessages(String locale) {

        messages = new String[] {
                Bundle.get("settings.message", locale),
                Bundle.get("info.Cash", locale),
                Bundle.get("info.Buypoint", locale),
                Bundle.get("info.powerup", locale),
                Bundle.get("info.superpower", locale),
                Bundle.get("info.units", locale)
        };
    }

    private static void handleMenuOption(Player player, int option) {
        if (option == 1) {
            nextMessage(player); // Next
        }
    }
}