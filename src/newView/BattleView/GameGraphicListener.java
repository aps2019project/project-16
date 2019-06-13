package newView.BattleView;

import newView.BattleView.gameActs.GameAct;

import java.util.concurrent.LinkedBlockingQueue;

// TODO: 6/14/19 use it
public class GameGraphicListener extends Thread {
    private final LinkedBlockingQueue<GameAct> inQueueGameActs = new LinkedBlockingQueue<>();
    public static final int GAME_ACT_TIME = 1000;//we can increase game speed by it!!! :))

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
        GameAct gameAct = inQueueGameActs.poll();
        if (gameAct == null) {
            synchronized (inQueueGameActs) {
                inQueueGameActs.wait();
            }
            gameAct = inQueueGameActs.poll();
        }
        gameAct.showAction();
    }
}
