package view.commands;

import java.util.regex.Pattern;

public class EnterMenuCommand extends Command {
    {
        name = "Enter (menu name)";
        pattern = Pattern.compile("enter (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String menuName = matcher.group(1);
        // TODO: 4/19/19 foreach on currentMenu subMenus
    }
}
