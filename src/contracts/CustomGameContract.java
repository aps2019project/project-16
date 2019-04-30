package contracts;

public interface CustomGameContract {
    interface View {
        void setController(Controller controller);

        void goToInGameMenu();
    }

    interface Controller {
        void startGame(int mode, int flags);
    }
}
