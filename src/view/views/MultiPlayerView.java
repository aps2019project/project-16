package view.views;

import contracts.MultiPlayerContract;

public class MultiPlayerView implements MultiPlayerContract.View {
    private MultiPlayerContract.Controller controller;

    @Override
    public void setController(MultiPlayerContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showSecondAccount() {
        // TODO: 5/1/19 implement
    }
}
