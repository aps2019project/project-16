package view.menuItems;

import view.commands.Command;

import java.util.ArrayList;

public abstract class MenuItem {
    protected ArrayList<Command> commands = new ArrayList<>();

    {
        initCommands();
    }

    protected abstract void initCommands();
}