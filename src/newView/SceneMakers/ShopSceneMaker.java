package newView.SceneMakers;

import com.dd.plist.PropertyListFormatException;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.Initializer;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.item.Item;
import models.magic.Spell;
import newView.CardMaker;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;
import newView.Type;
import newView.menu.Page;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ShopSceneMaker extends SceneMaker {
    private Type visibleType = Type.ITEM;

    private ArrayList<Card> heroes = new ArrayList<>();
    private ArrayList<Card> minions = new ArrayList<>();
    private ArrayList<Card> spells = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    private int itemCounter;
    private int minionCounter;
    private int usableCounter;
    private int spellCounter;

    public ShopSceneMaker(Page page) {
        super(page);
    }

    @Override
    public Scene makeScene() throws Exception {

        Initializer.initShopUsableItems(items);
        Initializer.addHeroes(heroes);
        Initializer.addMinions(minions);
        Initializer.addSpells(spells);

        Pane pane = new Pane();
        HBox type = new HBox();
        GridPane visibleCards = new GridPane();
        Rectangle cardsBackground = new Rectangle();

        type.setSpacing(20);
        ScaleTool.relocate(type, 100, 100);

        BackgroundMaker.setBackgroundFor(pane, 1, "shop");

        ImageView next = new ImageView(new Image(new FileInputStream("src/newView/resources/shopIcons/next.png")));
        ScaleTool.resizeImageView(next, 60, 60);
        ScaleTool.relocate(next, 700, 600);
        next.setOnMouseClicked(event -> {
            try {
                nextAction(visibleCards);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ImageView previous = new ImageView(new Image(new FileInputStream("src/newView/resources/shopIcons/previous.png")));
        ScaleTool.relocate(previous, 300, 600);
        ScaleTool.resizeImageView(previous, 60, 60);
        previous.setOnMouseClicked(event -> {
            try {
                previousAction(visibleCards);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/shopIcons/button_back.png")));
        ScaleTool.resizeImageView(back, 90, 90);


        Text usableItem = new Text();
        usableItem.setText("USABLE ITEMS");
        usableItem.setOnMouseClicked(event -> {
            visibleType = Type.ITEM;
            showingCards(visibleCards);
        });

        Text hero = new Text();
        hero.setText("HERO");
        hero.setOnMouseClicked(event -> {
            visibleType = Type.HERO;
            showingCards(visibleCards);
        });
        hero.setOnMouseEntered(event -> {
            hero.setEffect(new Glow(0.8));
        });


        Text minion = new Text();
        minion.setText("MINION");
        minion.setOnMouseClicked(event -> {
            visibleType = Type.MINION;
            showingCards(visibleCards);
        });

        Text spell = new Text();
        spell.setText("SPELL");
        spell.setOnMouseClicked(event -> {
            visibleType = Type.SPELL;
            showingCards(visibleCards);
        });

        type.getChildren().addAll(hero, minion, spell, usableItem);

        visibleCards.setMinSize(850, 450);
        visibleCards.setVgap(-65);
        visibleCards.setHgap(10);
        ScaleTool.relocate(visibleCards, 200, 150);


        ScaleTool.relocate(cardsBackground, 180, 130);
        ScaleTool.resizeRectangle(cardsBackground, 830, 470);


        showingCards(visibleCards);

        pane.getChildren().add(back);
        pane.getChildren().add(type);
        pane.getChildren().add(cardsBackground);
        pane.getChildren().add(visibleCards);
        pane.getChildren().addAll(next, previous);
        return new MyScene(pane);
    }

    private void previousAction(GridPane gridPane) {
        minusCounter();
        showingCards(gridPane);
    }

    private void nextAction(GridPane gridPane) {
        addCounter();
        showingCards(gridPane);
    }

    private void showingCards(GridPane gridPane) {
        try {
            gridPane.getChildren().removeIf(node -> true);
            if (visibleType == Type.HERO) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 5; j++) {
                        Card hero = heroes.get(5 * i + j);
                        String name = hero.getName();
                        Pane heroCardView = new CardMaker(name, Type.HERO).getUnitCardView();
                        gridPane.add(heroCardView, j, i);
                    }
                }
            } else if (visibleType == Type.MINION) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 5; j++) {
                        Card minion = minions.get(5 * i + j + minionCounter);
                        String name = minion.getName();
                        Pane minionCardView = new CardMaker(name, Type.MINION).getUnitCardView();
                        gridPane.add(minionCardView, j, i);
                    }
                }
            } else if (visibleType == Type.SPELL) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 5; j++) {
                        Card spell = spells.get(5 * i + j + spellCounter);
                        String name = spell.getName();
                        Pane spellCardView = new CardMaker(name, Type.SPELL).getSpellCardView();
                        gridPane.add(spellCardView, j, i);
                    }
                }
            } else if (visibleType == Type.ITEM) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (items.size() > 5 * i + j + itemCounter) {
                            Item item = items.get(5 * i + j + itemCounter);
                            String name = item.getName();
                            Pane itemCardView = new CardMaker(name, Type.ITEM).getItemCardView();
                            gridPane.add(itemCardView, j, i);
                        } else {
                            Pane pane = new Pane();
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }

    private void addCounter() {
        if (visibleType == Type.MINION && minionCounter < 30)
            minionCounter += 10;
        else if (visibleType == Type.SPELL && spellCounter < 10)
            spellCounter += 10;
        else if (visibleType == Type.ITEM && itemCounter < 10)
            itemCounter += 10;
    }

    private void minusCounter() {
        if (visibleType == Type.MINION && minionCounter >= 10)
            minionCounter -= 10;
        else if (visibleType == Type.SPELL && spellCounter >= 10)
            spellCounter -= 10;
        else if (visibleType == Type.ITEM && itemCounter >= 10)
            itemCounter -= 10;
    }


}
