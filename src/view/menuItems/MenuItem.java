package view.menuItems;

import view.commands.Command;

import java.util.ArrayList;

public abstract class MenuItem {
    protected String menuName;
    protected MenuItem parentMenu;
    protected ArrayList<Command> commands = new ArrayList<>();
    protected ArrayList<String> subMenuNames = new ArrayList<>();

    {
        initMenuItem();
    }

    protected abstract void initMenuItem();
}