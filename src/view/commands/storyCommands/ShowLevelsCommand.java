package view.commands.storyCommands;

import controllers.StoryController;
import view.commands.Command;
import view.views.StoryView;

import java.util.regex.Pattern;

public class ShowLevelsCommand extends Command {
    {
        name = "show levels";
        pattern = Pattern.compile("show levels", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new StoryController(new StoryView()).loadAllLevels();
    }
}
