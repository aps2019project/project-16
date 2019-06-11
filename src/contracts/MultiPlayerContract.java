package contracts;

import models.Account;

public interface MultiPlayerContract {
    interface View {
        void setController(Controller controller);

        void showSecondAccount(Account secondAccount);
        void goToInGameMenu();
    }

    interface Controller {
        void selectOppUser(String secondUserName);
        void loadSecondAccount();
        void startMultiGame(int mode, int numberOfFlags);
    }
}
