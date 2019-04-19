package view.commands.collectionCommands;

import view.commands.Command;

import java.util.regex.Pattern;

public class SearchCommand extends Command {
    {
        name = "search (card name | item name)";
        pattern = Pattern.compile("search (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        // TODO: 4/19/19 search
    }
}
