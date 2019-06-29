package newView.BattleView;

import newView.BattleView.gameActs.GameAct;

import java.util.concurrent.LinkedBlockingQueue;

public class GameGraphicListener extends Thread {
    private final LinkedBlockingQueue<GameAct> inQueueGameActs = new LinkedBlockingQueue<>();
    private final static int INIT_TIME = 3000;
    private static double rate = 1.0;
    public static int GAME_ACT_TIME = INIT_TIME;


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
        //noinspection ConstantConditions: NullPointer warning handled by wait and notify methods
        gameAct.passToPlatform();
    }

    public static String increaseVelocity() {
        if (rate < 5.9) {
            rate += 0.5;
            GAME_ACT_TIME = (int) (INIT_TIME / rate);
        }
        return rate + " X";
    }

    public static String decreaseVelocity() {
        if (rate > 0.6) {
            rate -= 0.5;
            GAME_ACT_TIME = (int) (INIT_TIME / rate);
        }
        return rate + " X";
    }
}
