package newView.SceneMakers;

import contracts.AccountContract;
import controllers.AccountController;
import exception.AccountExistsException;
import exception.InvalidCredentialsException;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Account;
import newView.CardMaker;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;
import newView.Type;

import java.io.FileInputStream;
import java.util.ArrayList;

public class LoginSceneMaker extends SceneMaker implements AccountContract.View {

    public LoginSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        BorderPane borderPane = new BorderPane();
        BackgroundMaker.setBackgroundFor(borderPane, 1, "login");

        VBox box = new VBox();
        HBox userName = new HBox();
        HBox password = new HBox();


        TextField userNameField = new TextField();
        PasswordField passwordField = new PasswordField();

        ImageView singUp = new ImageView(new Image(new FileInputStream("src/newView/resources/menuBackGrounds/login/signupBotton.jpg")));
        ImageView login = new ImageView(new Image(new FileInputStream("src/newView/resources/menuBackGrounds/login/loginBottton.jpg")));

        ScaleTool.resizeImageView(singUp, 300, 40);
        ScaleTool.resizeImageView(login, 300, 40);

        userNameField.setStyle("-fx-background-color: rgba(0,0,0,0.51); -fx-text-fill: White");
        userNameField.setPromptText("ENTER YOUR USERNAME");
        Label userNameLabel = new Label();
        userNameField.setPrefWidth(235 * SCALE);
        userNameLabel.setText("username :");
        userNameLabel.setTextFill(Color.WHITE);

        passwordField.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-text-fill: White");
        passwordField.setPromptText("ENTER YOUR PASSWORD");
        passwordField.setPrefWidth(235 * SCALE);
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

        login.setOnMouseClicked(event -> {
            AccountController controller = new AccountController();
            try {
                controller.loginAccount(userNameField.getText(), passwordField.getText());
                new MainMenuSceneMaker(getPrimaryStage()).set();
            } catch (InvalidCredentialsException e) {
//                e.printStackTrace();//todo show error
            }
        });
        singUp.setOnMouseClicked(event -> {
            AccountController controller = new AccountController();
            try {
                controller.createAccount(userNameField.getText(), passwordField.getText());
            } catch (AccountExistsException e) {
//                e.printStackTrace();//todo show error
            }
        });


        Rectangle rectangle = new Rectangle();

        ScaleTool.resizeRectangle(rectangle, 400, 400);
        rectangle.setFill(Color.rgb(0, 0, 0, 0.6));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, box);
        ScaleTool.resizeRegion(stackPane, 400, 400);
        ScaleTool.resizeRegion(box, 300, 300);
        borderPane.setCenter(stackPane);

        BorderPane.setAlignment(stackPane, Pos.CENTER);
//        Pane pane = new CardMaker("esfandiar", Type.HERO).getUnitCardView();
//        Pane pane = new CardMaker("Fireball", Type.SPELL).getSpellCardView();
        Pane pane = new CardMaker("tire do shakh", Type.ITEM).getItemCardView();
        pane.setLayoutX(60);
        pane.setLayoutY(60);

        borderPane.getChildren().add(pane);
        return new MyScene(borderPane);
    }

    private void loginAction() {

    }

    private void signUpAction() {
        //todo must  be  implemented by mostafa
    }

    @Override
    public void setController(AccountContract.Controller controller) {

    }

    @Override
    public void showLeaderboard(ArrayList<Account> accounts) {

    }
}
