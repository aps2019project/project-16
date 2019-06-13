package newView.BattleView;

import newView.BattleView.gameActs.GameAct;

import java.util.ArrayList;

public class GameGraphicListener extends Thread {
    private final ArrayList<GameAct> inQueueGameActs = new ArrayList<>();
    public static final int GAME_ACT_TIME = 1000;

    public void addGameAct(GameAct gameAct) {
        inQueueGameActs.add(gameAct);
        synchronized (inQueueGameActs) {
            inQueueGameActs.notify();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                showAGameAct();
                Thread.sleep(GAME_ACT_TIME);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showAGameAct() throws InterruptedException {
        GameAct gameAct = popGameAct();
        if (gameAct == null) {
            synchronized (inQueueGameActs) {
                inQueueGameActs.wait();
            }
            gameAct = popGameAct();
        }
        gameAct.showAction();
    }

    private GameAct popGameAct() {
        if (inQueueGameActs.size() == 0) {
            return null;
        } else {
            GameAct gameAct = inQueueGameActs.get(0);
            inQueueGameActs.remove(0);
            return gameAct;
        }
    }
}
