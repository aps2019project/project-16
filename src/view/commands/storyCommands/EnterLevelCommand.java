package view.commands.storyCommands;

import controllers.StoryController;
import view.Notify;
import view.commands.Command;
import view.views.StoryView;

import java.util.regex.Pattern;

public class EnterLevelCommand extends Command {
    {
        name = "enter level (level number)";
        pattern = Pattern.compile("enter level (\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int levelNumber = Integer.parseInt(matcher.group(1));
        if (levelNumber > 3 || levelNumber < 1) {
            Notify.logError("OOPS! Level number isn't valid!");
        } else {
            new StoryController(new StoryView()).loadLevel(levelNumber);
        }
    }
}
