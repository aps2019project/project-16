package view.commands;

import java.util.regex.Pattern;

public class ShowCommand extends Command {
    {
        name = "show";
        pattern = Pattern.compile("show", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        // TODO: 4/19/19 switch on currentMenuName collection or shop
    }
}
