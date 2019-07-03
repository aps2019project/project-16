package newView.SceneMakers;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import newView.AnimationMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;

public class LoadingSceneMaker extends SceneMaker {

    public LoadingSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() {
        Pane pane = getLoadingPane();
        new Thread(() -> {
            try {
                Thread.sleep(2500);
                Platform.runLater(() -> new LoginSceneMaker(getPrimaryStage()).set());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return new MyScene(pane);
    }

    public static Pane getLoadingPane() {
        Pane pane = new Pane();

        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.BLACK);
        ScaleTool.resizeRectangle(rectangle, WIDTH, HEIGHT);

        pane.getChildren().add(rectangle);

        ImageView demon = null;
        ImageView brand = null;
        try {
            demon = AnimationMaker.getSimpleAnimation("demon", "src/newView/resources/loading/");
            brand = new ImageView(new Image(new FileInputStream("src/newView/resources/loading/brand_duelyst_preloading.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Text text = new Text();
        text.setText("THIS MAY TAKE SOME MOMENTS");
        text.setFill(Color.rgb(81, 82, 82));
        text.setStyle("-fx-font: 24 arial;");
        ScaleTool.resizeImageView(brand, 200, 50);
        ScaleTool.resizeImageView(demon, 85, 85);

        ScaleTool.relocate(brand, 500, 200);
        ScaleTool.relocate(text, 420, 350);
        ScaleTool.relocate(demon, 550, 240);

        pane.getChildren().add(text);
        pane.getChildren().add(demon);
        pane.getChildren().add(brand);
        pane.setStyle("-fx-background-color: rgb(0,0,0); -fx-text-fill: White");
        return pane;
    }

}