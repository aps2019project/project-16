package view.commands.inGameCommands;

import contracts.InGameContract;
import controllers.InGameController;
import view.Notify;
import view.commands.Command;

import java.util.regex.Pattern;

import static view.commands.inGameCommands.CardIDRegex.CARD_ID_REGEX;


public class SelectCommand extends Command {
    {
        name = "select (card ID | collectable ID)";
        pattern = Pattern.compile("select (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
//         this command is for two use:
//         1- selecting card
//         2- selecting collectable
        String cardID = matcher.group(1);
        InGameContract.Controller controller = new InGameController();

        if (cardID.matches(CARD_ID_REGEX)) {
            controller.selectCard(cardID);
        } else if (cardID.matches("\\d+")) {
            controller.selectCollectable(Integer.parseInt(cardID));
        } else {
            Notify.logError("invalid ID format.");
        }
    }
}
