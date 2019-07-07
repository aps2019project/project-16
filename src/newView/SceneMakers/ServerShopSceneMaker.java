package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.GameContents;
import models.Shop;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.card.SpellCard;
import models.item.Item;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.util.ArrayList;

public class ServerShopSceneMaker extends SceneMaker {
    private VBox vBox = new VBox();
    private Shop shop = GameContents.getShop();
    private ScrollPane scrollPane = new ScrollPane();

    public ServerShopSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane root = new Pane();
        scrollPane.setContent(vBox);
        scrollPane.setMaxHeight(600);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        ScaleTool.relocate(scrollPane, 20, 50);
        scrollPane.setStyle("-fx-background-color: white");

        vBox.setSpacing(10);
        show();
        Text back = new Text("BACK");
        back.setStyle("-fx-font-size: 20");
        back.setFill(Color.BLACK);
        back.setOnMouseClicked(event -> new ServerMenuSceneMaker(getPrimaryStage()).set());
        ScaleTool.relocate(back, 20, 20);

        root.getChildren().addAll(back, scrollPane);


        return new MyScene(root);
    }

    private void show() {
        ArrayList<Object> cards = new ArrayList();
        cards.addAll(shop.getCards());
        cards.addAll(shop.getItems());

        for (Object object : cards) {
            Text cardText = new Text();
            if (object instanceof Hero) {
                cardText.setText(((Card) object).getName() + "     Hero     " + ((Card) object).getCapacity());
            } else if (object instanceof Minion) {
                cardText.setText(((Minion) object).getName() + "     MINION      " + ((Minion) object).getCapacity());
            } else if (object instanceof SpellCard) {
                cardText.setText(((SpellCard) object).getName() + "      SPELL       " + ((SpellCard) object).getCapacity());
            } else if (object instanceof Item) {
                cardText.setText(((Item) object).getName() + "     ITEM    " + ((Item) object).getCapacity());
            }
            cardText.setFill(Color.BLACK);
            cardText.setStyle("-fx-font-size: 20");
            vBox.getChildren().add(cardText);
        }
    }
}
