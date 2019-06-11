package view.commands.graveyardCommands;

import controllers.GraveyardController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowCardsCommand extends Command {
    {
        name = "show cards";
        pattern = Pattern.compile("show cards" , Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new GraveyardController().loadCards();
    }
}
