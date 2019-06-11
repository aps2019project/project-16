package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class UseSpecialPowerCommand extends Command {
    {
        name = "use special power [x, y]";
        pattern = Pattern.compile("use special power \\[(\\d+), *(\\d+)\\]",Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int x = Integer.parseInt(matcher.group(1));
        int y = Integer.parseInt(matcher.group(2));
        new InGameController().useSpecialPower(x, y);
    }
}
