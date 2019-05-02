package contracts;

import models.Account;

public interface MultiPlayerContract {
    interface View {
        void setController(Controller controller);

        void showSecondAccount(Account secondAccount);
        void goToInGameMenu();
    }

    interface Controller {
        //todo when enter second player must be null
        void selectOppUser(String secondUserName);
        void loadSecondAccount();
        void startMultiGame(int mode, int numberOfFlags);
    }
}
