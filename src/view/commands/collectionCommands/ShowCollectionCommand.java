package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowCollectionCommand extends Command {
    {
        name = "show";
        pattern = Pattern.compile("show", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        CollectionController controller = new CollectionController();
        controller.loadCollection();
    }
}
