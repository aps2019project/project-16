package view.menuItems;

import view.commands.commonCommands.EnterMenuCommand;
import view.commands.commonCommands.HelpCommand;
import view.commands.commonCommands.ShowMenuCommand;
import view.commands.inGameCommands.*;

import static view.menuItems.MenuConstants.IN_GAME_MENU;

public class InGameMenu extends MenuItem {
    @Override
    protected void initMenuItem() {
        name = IN_GAME_MENU;

        commands.add(new EnterMenuCommand());

        commands.add(new GameInfoCommand());
        commands.add(new ShowMinionsCommand());
        commands.add(new ShowCardInfoCommand());
        commands.add(new SelectCardCommand());
        commands.add(new MoveCommand());
        commands.add(new AttackCommand());
        commands.add(new AttackComboCommand());
        commands.add(new UseSpecialPowerCommand());
        commands.add(new ShowHandCommand());
        commands.add(new InsertCardCommmand());
        commands.add(new EndTurnCommand());
        commands.add(new ShowCollectablesCommand());
        commands.add(new SelectCollectableCommand());
        commands.add(new ShowCollectableInfoCommand());
        commands.add(new UseItemCommand());
        commands.add(new ShowNextCardCommand());
        commands.add(new ShowTableCommand());
        commands.add(new RefuseGameCommand());

        commands.add(new HelpCommand());
        commands.add(new ShowMenuCommand());


        subMenus.add(new GraveyardMenu());
    }
}
