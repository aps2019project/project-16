package newView.GraphicalElements.globalChat;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.SceneMakers.SceneMaker.HEIGHT;


public class GlobalChatPane extends Pane {
    private VBox messages = new VBox();
    private ScrollPane scroller = new ScrollPane();
    private Pane messagesBackgroundPane = new Pane();


    public GlobalChatPane() {
        try {
            BackgroundMaker.setBackgroundFor(this, 1, "globalChat");
            scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            ScaleTool.relocate(scroller, 800, 0);
            ScaleTool.resizeRegion(scroller, 320, HEIGHT);

            messagesBackgroundPane.setMaxWidth(scroller.getMaxWidth());
            messagesBackgroundPane.setStyle("-fx-background-color: rgb(24 ,24 , 32)");
            scroller.setContent(messagesBackgroundPane);

            messages.setSpacing(20);
            messages.setPadding(new Insets(0, 10, 0, 10));

            //todo must delete
            addMessage();
            addMessage();
            addMessage();

            messagesBackgroundPane.getChildren().add(messages);
            this.getChildren().add(scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMessage() throws FileNotFoundException {
        Pane pane = getMessage("ali", "salam");
        messages.getChildren().add(0, pane);
    }

    public void addMassage(String playerName, String message) {
        Platform.runLater(() -> {
            try {
                Pane pane = getMessage(playerName, message);
                messages.getChildren().add(0, pane);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private Pane getMessage(String playerName, String message) throws FileNotFoundException {
        Pane messagePane = new Pane();

        ImageView messageBackGround = new ImageView(new Image(new FileInputStream("src/newView/resources/globalChat/messageBg.png")));
        ScaleTool.resizeImageView(messageBackGround, 300, 80);

        Text playerNameText = new Text(playerName + " :");
        Text messageText = new Text(message);

        ScaleTool.relocate(playerNameText, 60, 10);
        ScaleTool.relocate(messageText, 60, 40);

        playerNameText.setStyle("-fx-font-size: 18");
        messageText.setStyle("-fx-font-size: 18");

        messageText.setFill(Color.WHITE);
        playerNameText.setFill(Color.WHITE);

        messagePane.getChildren().addAll(messageBackGround, messageText, playerNameText);
        return messagePane;
    }

}
