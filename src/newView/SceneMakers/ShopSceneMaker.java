package newView.SceneMakers;

import contracts.ShopContract;
import controllers.ShopController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Initializer;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.card.SpellCard;
import models.item.Item;
import newView.CardMaker;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;
import newView.Type;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ShopSceneMaker extends SceneMaker implements ShopContract.View {
    private ShopContract.Controller controller = new ShopController(this);

    private Type visibleType = Type.HERO;

    private List<Object> collection;

    {
        controller.loadShop();
        controller.loadCollection();
    }

    private int collectionCounter;


    private ArrayList<Card> heroes = new ArrayList<>();
    private ArrayList<Card> minions = new ArrayList<>();
    private ArrayList<Card> spells = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    private int itemCounter;
    private int minionCounter;
    private int spellCounter;

    private boolean inShop = true;

    public ShopSceneMaker(Stage primaryStage) {
        super(primaryStage);
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
        TextField search = new TextField();
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
        back.setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());

        Text usableItem = new Text();
        usableItem.setText("USABLE ITEMS");
        usableItem.setOnMouseClicked(event -> {
            inShop = true;
            visibleType = Type.ITEM;
            showingCards(visibleCards);
        });

        Text hero = new Text();
        hero.setText("HERO");
        hero.setOnMouseClicked(event -> {
            inShop = true;
            visibleType = Type.HERO;
            showingCards(visibleCards);
        });
        hero.setOnMouseEntered(event -> {
            hero.setEffect(new Glow(0.8));
        });


        Text minion = new Text();
        minion.setText("MINION");
        minion.setOnMouseClicked(event -> {
            inShop = true;
            visibleType = Type.MINION;
            showingCards(visibleCards);
        });

        Text spell = new Text();
        spell.setText("SPELL");
        spell.setOnMouseClicked(event -> {
            inShop = true;
            visibleType = Type.SPELL;
            showingCards(visibleCards);
        });

        Text collection = new Text();
        collection.setText("COLLECTION");
        collection.setOnMouseClicked(event -> {
            inShop = false;
            controller.loadCollection();
            showCollection(visibleCards);
        });

        setGlowOnMouseOver(hero);
        setGlowOnMouseOver(minion);
        setGlowOnMouseOver(spell);
        setGlowOnMouseOver(usableItem);
        setGlowOnMouseOver(collection);
        type.getChildren().addAll(hero, minion, spell, usableItem, collection);

        visibleCards.setMinSize(850, 450);
        visibleCards.setVgap(-55);
        visibleCards.setHgap(10);
        ScaleTool.relocate(visibleCards, 200, 150);


        ScaleTool.relocate(cardsBackground, 180, 130);
        ScaleTool.resizeRectangle(cardsBackground, 830, 450);
        cardsBackground.setFill(Color.rgb(0, 0, 0, 0.4));

        search.setPromptText("ENTER CARD NAME");
        ScaleTool.relocate(search, 10, 120);
        search.setStyle("-fx-arc-height: 100; -fx-arc-width: 100; -fx-background-color: rgba(80,150,255,1)");
//        search.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                if ()
//                controller.searchInShop();
//            }
//        });
        //todo by mostafa piadesazie search


        showingCards(visibleCards);

        pane.getChildren().add(back);
        pane.getChildren().add(type);
        pane.getChildren().add(cardsBackground);
        pane.getChildren().add(visibleCards);
        pane.getChildren().addAll(next, previous);
        pane.getChildren().add(search);

        //todo a shop picture for showing shop cards is needed and must be implemented

        return new MyScene(pane);
    }

    private void showCollection(GridPane visibleCards) {
        visibleCards.getChildren().removeIf(node -> true);
        try {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    if (collection.size() > 5 * i + j + collectionCounter) {
                        Object card = collection.get(5 * i + j + collectionCounter);
                        Pane cardView = new Pane();
                        int cardId = 0;
                        if (card instanceof Item) {
                            String name = ((Item) card).getName();
                            cardId= ((Item) card).getCollectionID();
                            cardView = new CardMaker(name, Type.ITEM).getItemCardView();
                            visibleCards.add(cardView, j, i);
                        } else if (card instanceof Hero) {
                            String name = ((Hero) card).getName();
                            cardId = ((Hero) card).getCollectionID();
                            cardView = new CardMaker(name, Type.HERO).getUnitCardView();
                            visibleCards.add(cardView, j, i);
                        } else if (card instanceof Minion) {
                            String name = ((Minion) card).getName();
                            cardId = ((Minion) card).getCollectionID();
                            cardView = new CardMaker(name, Type.MINION).getUnitCardView();
                            visibleCards.add(cardView, j, i);
                        } else if (card instanceof SpellCard) {
                            String name = ((SpellCard) card).getName();
                            cardId = ((SpellCard) card).getCollectionID();
                            cardView = new CardMaker(name, Type.SPELL).getSpellCardView();
                            visibleCards.add(cardView, j, i);
                        }
                        setSellOnMouseClick(cardView, cardId);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void previousAction(GridPane gridPane) {
        if (inShop) {
            minusCounter();
            showingCards(gridPane);
        } else {
            minusCollectionCounter();
            showCollection(gridPane);
        }
    }

    private void nextAction(GridPane gridPane) {
        if (inShop) {
            addCounter();
            showingCards(gridPane);
        } else {
            addCollectionCounter();
            showCollection(gridPane);
        }
    }

    private void showingCards(GridPane gridPane) {
        try {
            gridPane.getChildren().removeIf(node -> true);
            if (visibleType == Type.HERO) {
                showHero(gridPane, heroes);
            } else if (visibleType == Type.MINION) {
                showMinions(gridPane, minions, minionCounter);
            } else if (visibleType == Type.SPELL) {
                showSpells(gridPane, spells, spellCounter);
            } else if (visibleType == Type.ITEM) {
                showItems(gridPane, items, itemCounter);
            }
        } catch (Exception ignored) {
        }
    }

    private void showItems(GridPane gridPane, ArrayList<Item> items, int itemCounter) throws Exception {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                if (items.size() > 5 * i + j + itemCounter) {
                    Item item = items.get(5 * i + j + itemCounter);
                    String name = item.getName();
                    Pane itemCardView = new CardMaker(name, Type.ITEM).getItemCardView();
                    setBuyOnMouseClick(itemCardView, name);
                    gridPane.add(itemCardView, j, i);
                }
            }
        }
    }

    private void showSpells(GridPane gridPane, ArrayList<Card> spells, int spellCounter) throws Exception {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Card spell = spells.get(5 * i + j + spellCounter);
                String name = spell.getName();
                Pane spellCardView = new CardMaker(name, Type.SPELL).getSpellCardView();
                setBuyOnMouseClick(spellCardView, name);
                gridPane.add(spellCardView, j, i);
            }
        }
    }

    private void showMinions(GridPane gridPane, ArrayList<Card> minions, int minionCounter) throws Exception {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Card minion = minions.get(5 * i + j + minionCounter);
                String name = minion.getName();
                Pane minionCardView = new CardMaker(name, Type.MINION).getUnitCardView();
                setBuyOnMouseClick(minionCardView, name);
                gridPane.add(minionCardView, j, i);
            }
        }
    }

    private void showHero(GridPane gridPane, ArrayList<Card> heroes) throws Exception {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Card hero = heroes.get(5 * i + j);
                String name = hero.getName();
                Pane heroCardView = new CardMaker(name, Type.HERO).getUnitCardView();
                setBuyOnMouseClick(heroCardView, name);
                gridPane.add(heroCardView, j, i);
            }
        }
    }

    private void setBuyOnMouseClick(Pane card, String name) {
        card.setOnMouseClicked(event -> controller.buyCard(name));
    }

    private void setSellOnMouseClick(Pane card, int id) {
        card.setOnMouseClicked(event -> controller.sellCard(id));
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

    private void addCollectionCounter() {
        if (collectionCounter + 10 <= collection.size()) {
            collectionCounter += 10;
        }
    }

    private void minusCollectionCounter() {
        if (collectionCounter - 10 > 0) {
            collectionCounter -= 10;
        }
    }


    @Override
    public void setController(ShopContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showShop(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        //todo correct program to use this
    }

    @Override
    public void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        collection = new ArrayList<>();
        collection.addAll(cards);
        collection.addAll(heroes);
        collection.addAll(items);
    }
}
