package contracts;

public interface MultiPlayerContract {
    interface View {
        void setController(Controller controller);

        void showSecondAccount();
    }

    interface Controller {
        //todo when enter second player must be null
        void selectOppUser(String secondUserName);//todo check second player deck validation
        void loadSecondAccount();
        void startMultiGame(int mode, int numberOfFlags);
    }
}
