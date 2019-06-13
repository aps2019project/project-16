package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;
import view.views.CollectionView;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class SaveCollectionCommand extends Command {
    {
        name = "save";
        pattern = Pattern.compile("save", CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        CollectionController controller = new CollectionController(new CollectionView());
        controller.saveCollection();
    }
}
