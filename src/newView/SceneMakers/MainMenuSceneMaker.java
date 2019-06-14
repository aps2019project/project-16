package newView.SceneMakers;

import controllers.AccountController;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import newView.GraphicalElements.*;
import view.commands.mainMenuCommands.LogoutCommand;
import view.views.AccountView;

import java.io.FileNotFoundException;

public class MainMenuSceneMaker extends SceneMaker {
    private double x = 0, y = 0;

    public MainMenuSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }


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

        VBox commandsBox = new VBox();
        Pane commandsPane = new Pane(commandsBox);

        Text battle = new Text("Battle");

        Text shop = new Text("Shop");
        shop.setOnMouseClicked(event -> new ShopSceneMaker(getPrimaryStage()).set());

        Text collection = new Text("Collection");

        Text save = new Text("Save");
        save.setOnMouseClicked(event -> new AccountController().saveGameData());

        Text logout = new Text("Logout");
        save.setOnMouseClicked(event -> new LoginSceneMaker(getPrimaryStage()).set());

        Text exit = new Text("Exit");
        exit.setOnMouseClicked(event -> System.exit(0));

        commandsBox.getChildren().addAll(battle, shop, collection, save, logout, exit);
        commandsBox.getChildren().forEach(node -> {
            if (node instanceof Text) {
                setTextStyle((Text) node);
                setGlowOnMouseOver((Text) node);
            }
        });
        ScaleTool.relocate(commandsPane, 180, 200);

        borderPane.getChildren().addAll(lionView, fog.getView(), warriorManView);
        borderPane.getChildren().addAll(commandsPane);

        return new MyScene(borderPane);
    }

    private void setTextStyle(Text text) {
        text.setFont(new Font(ScaleTool.scale(35)));
        text.setFill(Color.WHITE);
    }
    private void setGlowOnMouseOver(Text text) {
        text.setOnMouseEntered(event -> text.setEffect(new Glow(0.8)));
        text.setOnMouseExited(event -> text.setEffect(null));
    }
}
