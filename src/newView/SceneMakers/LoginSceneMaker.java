package newView.SceneMakers;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginSceneMaker extends SceneMaker {

    @Override
    public Scene makeScene() throws FileNotFoundException {
        BorderPane borderPane = new BorderPane();
        BackgroundMaker.setBackgroundFor(borderPane, 1, "login");

        VBox box = new VBox();
        HBox userName = new HBox();
        HBox password = new HBox();


        TextField userNameField = new TextField();
        PasswordField passwordField = new PasswordField();

        ImageView singUp = new ImageView(new Image(new FileInputStream("C:\\Users\\sepehr.p\\Desktop\\project phase1\\src\\newView\\resources\\menuBackGrounds\\login\\signupBotton.png")));
        ImageView login = new ImageView(new Image(new FileInputStream("C:\\Users\\sepehr.p\\Desktop\\project phase1\\src\\newView\\resources\\menuBackGrounds\\login\\loginBottton.png")));

        ScaleTool.resizeImageView(singUp, 300, 40);
        ScaleTool.resizeImageView(login, 300, 40);

        userNameField.setStyle("-fx-background-color: rgba(0,0,0,0.51); -fx-text-fill: White");
        userNameField.setPromptText("ENTER YOUR USERNAME");
        Label userNameLabel = new Label();
        userNameField.setPrefWidth(235*SCALE);
        userNameLabel.setText("username :");
        userNameLabel.setTextFill(Color.WHITE);

        passwordField.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-text-fill: White");
        passwordField.setPromptText("ENTER YOUR PASSWORD");
        passwordField.setPrefWidth(235*SCALE);
        Label passwordLabel = new Label();
        passwordLabel.setText("password :");
        box.setSpacing(SCALE * 30);
        passwordLabel.setTextFill(Color.WHITE);


        userName.getChildren().addAll(userNameLabel, userNameField);
        userName.setSpacing(SCALE * 5);

        password.getChildren().addAll(passwordLabel, passwordField);
        password.setSpacing(SCALE * 5);

        box.getChildren().addAll(userName, password);
        Node freeSpace1 = new Text("");
        Node freeSpace2 = new Text("");
        box.getChildren().addAll(freeSpace1, freeSpace2);
        box.getChildren().add(singUp);
        box.getChildren().add(login);

        login.setOnMouseClicked(event -> loginAction());
        singUp.setOnMouseClicked(event -> signUpAction());


        Rectangle rectangle = new Rectangle();

        ScaleTool.resizeRectangle(rectangle, 400, 400);
        rectangle.setFill(Color.rgb(0, 0, 0, 0.6));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, box);
        ScaleTool.resizeRegion(stackPane, 400, 400);
        ScaleTool.resizeRegion(box, 300, 300);
        borderPane.setCenter(stackPane);

        BorderPane.setAlignment(stackPane, Pos.CENTER);

        return new MyScene(borderPane);
    }

    private void loginAction() {
        //todo must be implemented by mostafa
    }

    private void signUpAction() {
        //todo must  be  implemented by mostafa
    }
}
