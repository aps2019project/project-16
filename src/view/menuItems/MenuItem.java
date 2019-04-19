package view.menuItems;

import view.commands.Command;

import java.util.ArrayList;

public abstract class MenuItem {
    protected String name;
    protected MenuItem parentMenu;
    protected ArrayList<Command> commands = new ArrayList<>();
    protected ArrayList<MenuItem> subMenus = new ArrayList<>();

    {
        initMenuItem();
    }

    public String getName() {
        return name;
    }

    public MenuItem getParentMenu() {
        return parentMenu;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public ArrayList<MenuItem> getSubMenus() {
        return subMenus;
    }

    protected abstract void initMenuItem();
}