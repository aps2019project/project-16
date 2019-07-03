package newView.battleView;

import newView.battleView.gameActs.GameAct;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class GameGraphicListener extends Thread {
    private final LinkedBlockingQueue<GameAct> inQueueGameActs = new LinkedBlockingQueue<>();
    private final static int INIT_TIME = 3000;
    private static double rate = 1.0;
    public static int GAME_ACT_TIME = INIT_TIME;

    private static boolean colorAnimationOn = true;
    private static boolean mustShow = true;

    public void addGameAct(GameAct gameAct) {
        inQueueGameActs.add(gameAct);
        synchronized (inQueueGameActs) {
            inQueueGameActs.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (mustShow) {
                try {
                    showAGameAct();
                    Thread.sleep(GAME_ACT_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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

    public void fastShow(ArrayList<GameAct> gameActs) throws InterruptedException {
        colorAnimationOn = false;
        int real_time = GAME_ACT_TIME;
        GAME_ACT_TIME = 50;
        for (GameAct gameAct : gameActs) {
            gameAct.passToPlatform();
            Thread.sleep(GAME_ACT_TIME);
        }
        GAME_ACT_TIME = real_time;
        colorAnimationOn = true;
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

    public static boolean isColorAnimationOn() {
        return colorAnimationOn;
    }

    public static void setMustShow(boolean mustShow) {
        GameGraphicListener.mustShow = mustShow;
    }
}
