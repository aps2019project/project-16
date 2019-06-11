package newView.GraphicalElements.battle.effects;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.Random;

public class SnowPane extends Pane {
    private Random random = new Random();
    private static final int SNOW_COUNT = 500;

    public SnowPane() {
        Circle[] circles = new Circle[SNOW_COUNT];

        for (int i = 0; i < SNOW_COUNT; i++) {
            circles[i] = new Circle(1, 1, 1);
            circles[i].setRadius(random.nextDouble() * 3);
            Color color = Color.rgb(255, 255, 255, random.nextDouble());
            circles[i].setFill(color);
            this.getChildren().add(circles[i]);
            Raining(circles[i]);
        }
    }

    private void Raining(Circle c) {
        c.setCenterX(random.nextInt(1300));//Window width = 1300
        int time = 20 + random.nextInt(50);
        Animation fall = TranslateTransitionBuilder.create()
                .node(c)
                .fromY(-200)
                .toY(700 + 200) //WIndow height = 700
                .toX(random.nextDouble() * c.getCenterX())
                .duration(Duration.seconds(time))
                .onFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        Raining(c);
                    }
                }).build();
        fall.play();
    }
}
