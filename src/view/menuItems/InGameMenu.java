package view.menuItems;

import view.commands.commonCommands.EnterMenuCommand;
import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;
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
        commands.add(new SelectCommand());
        commands.add(new MoveCommand());
        commands.add(new AttackCommand());
        commands.add(new AttackComboCommand());
        commands.add(new UseSpecialPowerCommand());
        commands.add(new ShowHandCommand());
        commands.add(new InsertCardCommmand());
        commands.add(new EndTurnCommand());
        commands.add(new ShowCollectablesCommand());
        //select collectable command is merged to select command
        commands.add(new ShowCollectableInfoCommand());
        commands.add(new UseItemCommand());
        commands.add(new ShowNextCardCommand());
        commands.add(new HintCommand());
        commands.add(new EndGameCommand());

        commands.add(new ExitCommand());
        commands.add(new HelpCommand());


        subMenus.add(new InGraveyardMenu());
    }
}
