package view.menuItems;

import view.commands.collectionCommands.*;
import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;

import static view.menuItems.MenuConstants.COLLECTION_MENU;

public class CollectionMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = COLLECTION_MENU;

        commands.add(new ShowCollectionCommand());
        commands.add(new SearchCommand());
        commands.add(new SaveCollectionCommand());
        commands.add(new CreateDeckCommand());
        commands.add(new DeleteDeckCommand());
        commands.add(new AddCardToDeckCommand());
        commands.add(new RemoveCardFromDeckCommand());
        commands.add(new ValidateDeckCommand());
        commands.add(new SelectDeckCommand());
        commands.add(new ShowAllDecksCommand());
        commands.add(new ShowDeckCommand());

        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        //no subMenu
    }
}
