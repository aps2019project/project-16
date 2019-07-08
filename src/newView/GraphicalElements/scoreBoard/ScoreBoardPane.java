package newView.GraphicalElements.scoreBoard;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.AccountProperty;
import newView.GraphicalElements.ScaleTool;
import newView.SceneMakers.MainMenuSceneMaker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

import static ir.pas.ClientApp.getPrimaryStage;

public class ScoreBoardPane extends Pane {
    private Pane background = new Pane();
    private ArrayList<AccountProperty> accountProperties = new ArrayList<>();
    private VBox accounts = new VBox();
    private ImageView back;

    public ScoreBoardPane() {
        try {
            setBackground();
            setBack();
            background.getChildren().add(accounts);
            this.getChildren().addAll(back, background);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateScoreBoard(ArrayList<AccountProperty> accountProperties) {
        Platform.runLater(() -> {
            this.accountProperties = accountProperties;
            sort();
            setAccounts();
        });
    }

    private void setBackground() {
        ScaleTool.resizeRegion(background, 500, 600);
        ScaleTool.relocate(background, 380, 30);
        background.setStyle("-fx-background-color: rgb(0,0 ,0,0.5)");

    }

    private void setBack() throws FileNotFoundException {
        back = new ImageView(new Image(new FileInputStream("src/newView/resources/scoreBoard/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());
    }

    private void setAccounts() {
        accounts.getChildren().removeIf(e -> true);
        accounts.setSpacing(20);
        accounts.setPadding(new Insets(20, 20, 20, 20));
        ScaleTool.resizeRegion(accounts, 400, 400);
//        ScaleTool.relocate(accounts, 300, 300);
        for (AccountProperty accountProperty : accountProperties) {
            Text account = new Text();
            account.setText(accountProperty.getAccountName() + "        " + accountProperty.getScore() +
                    "        " + accountProperty.getMoney() + "      " + accountProperty.getIsOnline());
            accounts.getChildren().add(account);
            account.setFill(Color.WHITE);
        }
    }

    private void sort() {
        accountProperties.sort(Comparator.comparing(AccountProperty::getScore, Comparator.reverseOrder()));
    }
}
