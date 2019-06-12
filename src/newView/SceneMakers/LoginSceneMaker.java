package newView.SceneMakers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.io.FileNotFoundException;

public class LoginSceneMaker extends SceneMaker {

    @Override
    public Scene makeScene() throws FileNotFoundException {
        BorderPane borderPane = new BorderPane();
        //BackgroundMaker.setBackgroundFor(borderPane, 1, "login");

        VBox loginBox = new VBox();
        TextField userNameField = new TextField();
        PasswordField passwordField = new PasswordField();
        userNameField.setStyle("-fx-background-color: Black; -fx-text-fill: White");
        passwordField.setStyle("-fx-background-color: Black; -fx-text-fill: White");
        loginBox.setSpacing(SCALE * 30);

        loginBox.getChildren().addAll(userNameField, passwordField);

        Rectangle rectangle = new Rectangle();

        ScaleTool.resizeRectangle(rectangle, 400, 400);
        rectangle.setFill(Color.rgb(0, 0, 0, 0.6));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, loginBox);
        ScaleTool.resizeRegion(stackPane, 400, 400);
        ScaleTool.resizeRegion(loginBox, 300, 300);
        borderPane.setCenter(stackPane);

        BorderPane.setAlignment(stackPane, Pos.CENTER);

        return new MyScene(borderPane);
    }
}
