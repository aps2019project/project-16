package contracts;

import models.GameLevel;

import java.util.ArrayList;

public interface StoryContract {
    interface View {
        void setController(Controller controller);

        void goToLevelInGame();
        void showAllLevels(ArrayList<GameLevel> gameLevels);
    }

    interface Controller {
        void loadLevel(int levelNumber);
        void loadAllLevels();
    }
}
