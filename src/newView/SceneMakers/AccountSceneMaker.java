package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import newView.GraphicalElements.*;

import java.io.FileNotFoundException;

public class AccountSceneMaker extends SceneMaker {
    private double x = 0, y = 0;

    @Override
    public Scene makeScene() throws FileNotFoundException {
        BorderPane borderPane = new BorderPane();
        BackgroundMaker.setBackgroundFor(borderPane, 1, "account");

        ImageView lionView = ForegroundMaker.getForeground(2, 600, 600, "account");
        ScaleTool.relocate(lionView, -100, 100);

        Fog fog = new Fog(800, 150);
        ScaleTool.relocate(fog.getView(), -100, 550);

        ImageView warriorManView = ForegroundMaker.getForeground(1, 800, 250, "account");
        ScaleTool.relocate(warriorManView, 600, 480);

        borderPane.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            if (e.getX() < x) {
                warriorManView.setX(warriorManView.getX() + 0.1);
                lionView.setX(lionView.getX() + 0.1);
            } else if (e.getX() > x) {
                warriorManView.setX(warriorManView.getX() - 0.1);
                lionView.setX(lionView.getX() - 0.1);
            }
            if (e.getY() < y) {
                warriorManView.setY(warriorManView.getY() + 0.1);
                lionView.setY(lionView.getY() + 0.1);
            } else if (e.getY() > y) {
                warriorManView.setY(warriorManView.getY() - 0.1);
                lionView.setY(lionView.getY() - 0.1);
            }
            x = e.getX();
            y = e.getY();
        });

        borderPane.getChildren().addAll(lionView, fog.getView(), warriorManView);
        borderPane.setCursor(SceneMaker.GAME_CURSOR);
        return new MyScene(borderPane);
    }
}
