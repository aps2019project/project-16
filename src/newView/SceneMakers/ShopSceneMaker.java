package newView.SceneMakers;

import contracts.ShopContract;
import controllers.ShopController;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.card.*;
import models.item.Item;
import newView.CardMaker;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;
import newView.SoundPlayer;
import newView.Type;

import java.io.FileInputStream;
import java.util.*;

public class ShopSceneMaker extends SceneMaker implements ShopContract.View {
    private ShopContract.Controller controller = new ShopController(this);

    private Type visibleType = Type.HERO;

    private List<Object> collection;
    private GridPane visibleCards;

    private int collectionCounter;


    private ArrayList<Card> heroes = new ArrayList<>();
    private HashMap<String, Pane> heroPanes = new HashMap<>();

    private ArrayList<Card> minions = new ArrayList<>();
    private HashMap<String, Pane> minionPanes = new HashMap<>();

    private ArrayList<Card> spells = new ArrayList<>();
    private HashMap<String, Pane> spellPanes = new HashMap<>();

    private ArrayList<Item> items = new ArrayList<>();
    private HashMap<String, Pane> itemPanes = new HashMap<>();

    private int itemCounter;
    private int minionCounter;
    private int spellCounter;
    private int heroCounter;

    private boolean inShop = true;

    {
        controller.loadShop();
        controller.loadCollection();
        try {
            for (Card hero : heroes)
                heroPanes.put(hero.getName(), new CardMaker(hero.getName(), Type.HERO, hero).getUnitCardViewInShop());
            for (Card minion : minions)
                minionPanes.put(minion.getName(), new CardMaker(minion.getName(), Type.MINION, minion).getUnitCardViewInShop());
            for (Card spell : spells)
                spellPanes.put(spell.getName(), new CardMaker(spell.getName(), Type.SPELL, spell).getSpellCardView());
            for (Item item : items)
                itemPanes.put(item.getName(), new CardMaker(item.getName(), item).getItemCardViewInShop());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SoundPlayer.playByPath("src/newView/resources/sounds/shop.wav");
    }


    public ShopSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {

        Pane pane = new Pane();
        HBox type = new HBox();
        visibleCards = new GridPane();
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
            showCardList(visibleCards, this.collection);
        });

        setGlowOnMouseOver(hero);
        setGlowOnMouseOver(minion);
        setGlowOnMouseOver(spell);
        setGlowOnMouseOver(usableItem);
        setGlowOnMouseOver(collection);
        type.getChildren().addAll(hero, minion, spell, usableItem, collection);

        visibleCards.setMinSize(850, 450);
        visibleCards.setHgap(10);
        ScaleTool.relocate(visibleCards, 200, 150);


        ScaleTool.relocate(cardsBackground, 180, 130);
        ScaleTool.resizeRectangle(cardsBackground, 830, 450);
        cardsBackground.setFill(Color.rgb(0, 0, 0, 0.4));

        search.setPromptText("ENTER CARD NAME");
        ScaleTool.relocate(search, 10, 120);
        search.setStyle("-fx-arc-height: 100; -fx-arc-width: 100; -fx-background-color: rgba(80,150,255,1)");
        search.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (inShop)
                    controller.searchInShop(search.getText());
            }
        });


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

    private void showCardList(GridPane visibleCards, List<Object> cards) {
        visibleCards.getChildren().removeIf(node -> true);
        System.gc();
        try {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    if (cards.size() > 5 * i + j + collectionCounter) {
                        Object card = cards.get(5 * i + j + collectionCounter);
                        Pane cardView;
                        String name;
                        final int cardId;
                        if (card instanceof Item) {
                            name = ((Item) card).getName();
                            cardId = ((Item) card).getCollectionID();
                            cardView = new CardMaker(name, (Item) card).getItemCardViewInShop();
                            visibleCards.add(cardView, j, i);
                        } else if (card instanceof Hero) {
                            name = ((Hero) card).getName();
                            cardId = ((Hero) card).getCollectionID();
                            cardView = new CardMaker(name, Type.HERO, (Card) card).getUnitCardViewInShop();
                            visibleCards.add(cardView, j, i);
                        } else if (card instanceof Minion) {
                            name = ((Minion) card).getName();
                            cardId = ((Minion) card).getCollectionID();
                            cardView = new CardMaker(name, Type.MINION, (Card) card).getUnitCardViewInShop();
                            visibleCards.add(cardView, j, i);
                        } else {
                            name = ((SpellCard) card).getName();
                            cardId = ((SpellCard) card).getCollectionID();
                            cardView = new CardMaker(name, Type.SPELL, (Card) card).getSpellCardView();
                            visibleCards.add(cardView, j, i);
                        }
                        cardView.setOnMouseClicked(event -> {
                            if (!inShop) {
                                controller.sellCard(cardId);
                                controller.loadCollection();
                            } else {
                                controller.buyCard(name);
                            }
                            showCardList(visibleCards, this.collection);
                        });
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
            showCardList(gridPane, this.collection);
        }
    }

    private void nextAction(GridPane gridPane) {
        if (inShop) {
            addCounter();
            showingCards(gridPane);
        } else {
            addCollectionCounter();
            showCardList(gridPane, this.collection);
        }
    }

    private void showingCards(GridPane gridPane) {
        try {
            gridPane.getChildren().removeIf(node -> true);
            System.gc();
            if (visibleType == Type.HERO) {
                showHeroes(gridPane, heroes, heroCounter);
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
                    Pane itemCardView = itemPanes.get(name);
                    setBuyOnMouseClick(gridPane, itemCardView, item);
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
                Pane spellCardView = spellPanes.get(name);
                setBuyOnMouseClick(gridPane, spellCardView, spell);
                gridPane.add(spellCardView, j, i);
            }
        }
    }

    private void showMinions(GridPane gridPane, ArrayList<Card> minions, int minionCounter) throws Exception {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Card minion = minions.get(5 * i + j + minionCounter);
                String name = minion.getName();
                Pane minionCardView = minionPanes.get(minion.getName());
                setBuyOnMouseClick(gridPane, minionCardView, minion);
                gridPane.add(minionCardView, j, i);
            }
        }
    }

    private void showHeroes(GridPane gridPane, ArrayList<Card> heroes, int heroCounter) throws Exception {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Card hero = heroes.get(5 * i + j + heroCounter);
                String name = hero.getName();
                Pane heroCardView = heroPanes.get(name);
                setBuyOnMouseClick(gridPane, heroCardView, hero);
                gridPane.add(heroCardView, j, i);
            }
        }
    }

    private void setBuyOnMouseClick(GridPane gridPane, Pane cardPane, Object card) {
        cardPane.setOnMouseClicked(event -> {
            try {
                if (card instanceof Item) {
                    controller.buyCard(((Item) card).getName());
                    itemPanes.put(((Item) card).getName(), new CardMaker(((Item) card).getName(), (Item) card).getItemCardViewInShop());
                } else if (card instanceof Hero) {
                    controller.buyCard(((Card) card).getName());
                    heroPanes.put(((Hero) card).getName(), new CardMaker(((Hero) card).getName(), Type.HERO, (Card) card).getUnitCardViewInShop());
                } else if (card instanceof Minion) {
                    controller.buyCard(((Minion) card).getName());
                    minionPanes.put(((Minion) card).getName(), new CardMaker(((Minion) card).getName(), Type.MINION, (Card) card).getUnitCardViewInShop());
                } else if (card instanceof SpellCard) {
                    controller.buyCard(((SpellCard) card).getName());
                    spellPanes.put(((SpellCard) card).getName(), new CardMaker(((SpellCard) card).getName(), Type.SPELL, (Card) card).getSpellCardView());
                }
            } catch (Exception e) {
            }
            showingCards(gridPane);
        });
    }


    private void addCounter() {
        if (visibleType == Type.MINION && minionCounter < minions.size() - 10)
            minionCounter += 10;
        else if (visibleType == Type.SPELL && spellCounter < spells.size() - 10)
            spellCounter += 10;
        else if (visibleType == Type.ITEM && itemCounter < items.size() - 10)
            itemCounter += 10;
        else if (visibleType == Type.HERO && heroCounter < heroes.size() - 10)
            heroCounter += 10;
    }

    private void minusCounter() {
        if (visibleType == Type.MINION && minionCounter >= 10)
            minionCounter -= 10;
        else if (visibleType == Type.SPELL && spellCounter >= 10)
            spellCounter -= 10;
        else if (visibleType == Type.ITEM && itemCounter >= 10)
            itemCounter -= 10;
        else if (visibleType == Type.HERO && heroCounter >= 10)
            heroCounter -= 10;
    }

    private void addCollectionCounter() {
        if (collectionCounter + 10 <= collection.size()) {
            collectionCounter += 10;
        }
    }

    private void minusCollectionCounter() {
        if (collectionCounter - 10 >= 0) {
            collectionCounter -= 10;
        }
    }


    @Override
    public void setController(ShopContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showShop(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        this.heroes = new ArrayList<>();
        this.items = new ArrayList<>();
        this.heroes.addAll(heroes);
        for (Card card : cards)
            if (card instanceof Minion)
                this.minions.add(card);
            else if (card instanceof SpellCard)
                this.spells.add(card);
        this.items.addAll(items);
    }

    @Override
    public void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        collection = new ArrayList<>();
        collection.addAll(cards);
        collection.addAll(heroes);
        collection.addAll(items);
    }

    @Override
    public void showCard(Object card) {
        inShop = true;
        showCardList(visibleCards, Arrays.asList(card));
    }
}
