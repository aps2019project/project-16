package newView.SceneMakers;

import contracts.CollectionContract;
import controllers.CollectionController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Account;
import models.Deck;
import models.GameContents;
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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollectionSceneMaker extends SceneMaker implements CollectionContract.View {

    private CollectionContract.Controller controller = new CollectionController(this);
    private boolean showingDecks = true;
    private ArrayList<Deck> decks;
    private Deck selectedDeck;
    private int collectionCounter;
    private final Pane rightPane = new Pane();

    private List<Object> collection;

    {
        controller.loadCollection();
    }

    public CollectionSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane root = new Pane();

        GridPane visibleCards = new GridPane();

        ScrollPane rightPart = new ScrollPane();

        rightPart.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightPart.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        BackgroundMaker.setBackgroundFor(root, 1, "collection");

        ScaleTool.relocate(rightPart, 1200 - 350 + 100, 0);
        ScaleTool.resizeRegion(rightPart, 350, HEIGHT);
        rightPart.setStyle("-fx-background-color: black");

        rightPane.setStyle("-fx-background-color: rgb(24,24,32)");
        rightPane.setMaxWidth(rightPart.getWidth());
        ScaleTool.setMinSize(rightPane, 350, 800);

        controller.loadAllDecks();
        rightPart.setContent(rightPane);

        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/collectionIcons/back.png")));
        ScaleTool.resizeImageView(back, 100, 100);
        back.setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());

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

        visibleCards.setMinSize(850, 450);
        visibleCards.setVgap(-55);
        visibleCards.setHgap(10);
        ScaleTool.relocate(visibleCards, 200, 150);

        Rectangle cardsBackground = new Rectangle();
        ScaleTool.relocate(cardsBackground, 70, 130);
        ScaleTool.resizeRectangle(cardsBackground, 830, 450);
        cardsBackground.setFill(Color.rgb(0, 0, 0, 0.4));

        TextField search = new TextField();
        search.setStyle("-fx-background-color: rgb(37, 45, 52,1)");
        ScaleTool.relocate(search, 20, 600); //todo must be relocated again
        search.setPromptText("SEARCH");
        search.setStyle("-fx-prompt-text-fill: gray");
        //todo mostafa karasho bokon!!! man dg hal nadaram  03:23 , 25 khordad 98 !!!
        showCollection(visibleCards, collection);

        TextField newDeckName = new TextField();
        newDeckName.setStyle("-fx-background-color: rgb(81 , 186, 255)");
        ScaleTool.relocate(newDeckName, 20, 630);
        newDeckName.setPromptText("ENTER YOUR NEW DECK NAME");
        newDeckName.setStyle("-fx-text-fill: gray");

        ImageView newDeckAccept = new ImageView(new Image(new FileInputStream("src/newView/resources/collectionIcons/newDeckAccept.png")));
        ScaleTool.relocate(newDeckAccept, 170, 630);
        ScaleTool.resizeImageView(newDeckAccept, 50, 50);


        Text ok = new Text();
        ok.setText("OK");
        ok.setFill(Color.WHITE);
        ScaleTool.relocate(ok, 180, 650);
        ok.setStyle("-fx-font-size: 24");
        ok.setOnMouseClicked(event -> {
            controller.createDeck(newDeckName.getText());
            controller.loadAllDecks();
            newDeckName.setText("");
        });


        root.getChildren().add(back);
        root.getChildren().add(rightPart);
        root.getChildren().add(cardsBackground);
        root.getChildren().add(visibleCards);
        root.getChildren().add(next);
        root.getChildren().add(previous);
        root.getChildren().add(search);
        root.getChildren().add(newDeckName);
        root.getChildren().add(newDeckAccept);
        root.getChildren().add(ok);

        return new MyScene(root);
    }

    private void updateRightPart() throws FileNotFoundException {
        VBox rightPartVBox;
        if (showingDecks) {
            rightPartVBox = getDecks(decks);
        } else {
            rightPartVBox = getCardsInDeck(selectedDeck);
        }
        rightPartVBox.setStyle("-fx-background-color: rgb(24, 24, 32)");
//        cardsInTheDeck.getChildren().add(deckPane);
//        rightPane.getChildren().add(cardsInTheDeck);
        rightPane.getChildren().removeIf(node -> true);
        rightPane.getChildren().add(rightPartVBox);
    }

    private void previousAction(GridPane visibleCards) {
        collectionCounter -= 10;
        if (collectionCounter < 0)
            collectionCounter -= 10;
        else
            showCollection(visibleCards, collection);
    }

    private void nextAction(GridPane visibleCards) {
        collectionCounter += 10;
        if (collectionCounter >= collection.size())
            collectionCounter -= 10;
        else
            showCollection(visibleCards, collection);
    }

    private VBox getDecks(ArrayList<Deck> accountDecks) throws FileNotFoundException {
        VBox decks = new VBox();
        decks.setPadding(new Insets(0, 10, 10, 10));
        decks.setSpacing(10);

        Pane temp = new Pane();
        ScaleTool.resizeRegion(temp, 350, 100);
        decks.getChildren().add(temp);

        for (Deck deck : accountDecks) {
            Pane deckPane = new Pane();
            ScaleTool.resizeRegion(deckPane, 315, 70);
            deckPane.setStyle("-fx-background-color: rgb(33, 49 ,64)");
            deckPane.setOnMouseClicked(event -> controller.loadDeck(deck.getName()));
            ImageView stash = new ImageView(new Image(new FileInputStream("src/newView/resources/collectionIcons/stash.png")));
            ScaleTool.relocate(stash, 270, 25);
            stash.setOnMouseClicked(event -> {
                controller.deleteDeck(deck.getName());
                controller.loadAllDecks();
            });

            int rand = Math.abs(new Random().nextInt()) % 21 + 1;
            ImageView deckIcon = new ImageView(new Image(new FileInputStream("src/newView/resources/collectionIcons/deckIcons/" + rand + ".png")));
            ScaleTool.resizeImageView(deckIcon, 85, 85);
            ScaleTool.relocate(deckIcon, -10, -15);

            Text deckName = new Text();
            deckName.setFill(Color.WHITE);
            deckName.setText(deck.getName());
            ScaleTool.relocate(deckName, 80, 15);
            deckName.setStyle("-fx-font: 18 TimesNewRoman;");

            Text statics = new Text();
            statics.setText(getNumberOfHero(deck) + " :Heroes" + getNumberOfSpells(deck) + ":Spells" + getNumberOfMinions(deck)
                    + ":Minions" + getNumberOfItems(deck) + ":Items");
            statics.setFill(Color.WHITE);
            ScaleTool.relocate(statics, 65, 40);

            deckPane.getChildren().addAll(stash, deckIcon, deckName, statics);
            decks.getChildren().add(deckPane);

            ///TODO MOSTAFA BIA TABEE DOKME HA RO BEZAN !!! :)))))
        }
        return decks;
    }


    private VBox getCardsInDeck(Deck deck) throws FileNotFoundException {
        VBox cardsInDeck = new VBox();
        cardsInDeck.setPadding(new Insets(0, 10, 10, 10));
        cardsInDeck.setSpacing(10);

        Pane temp = new Pane();
        ScaleTool.resizeRegion(temp, 350, 100);
        ImageView deleteDeck = new ImageView(new Image(new FileInputStream("src/newView/resources/collectionIcons/deleteDeckIcon.png")));
        ScaleTool.resizeImageView(deleteDeck, 30, 30);
        ScaleTool.relocate(deleteDeck, 30, 30);
        deleteDeck.setOnMouseClicked(event -> {
            controller.deleteDeck(deck.getName());
            controller.loadAllDecks();
        });
        temp.getChildren().add(deleteDeck);//todo mostafa dokmash

        Text deckName = new Text();
        deckName.setText(deck.getName());
        deckName.setFill(Color.WHITE);
        deckName.setStyle("-fx-font-size: 26");
        ScaleTool.relocate(deckName, 10, 10);
        temp.getChildren().add(deckName);

        cardsInDeck.getChildren().add(temp);

        ///todo must be check if there is any bug //mostafa check kon namoosan!!!!
        List<Object> cards = new ArrayList<>();
        if (deck.getHero() != null)
            cards.add(deck.getHero());
        if (deck.getItem() != null)
            cards.add(deck.getItem());
        cards.addAll(deck.getCards());

        for (Object card : cards) {
            int id;
            Pane cardPane;
            if (card instanceof Hero)
                cardPane = getHeroPane((Card) card);
            else
                cardPane = getNonHeroPane(card);
            if (card instanceof Item)
                id = ((Item) card).getCollectionID();
            else
                id = ((Card) card).getCollectionID();
            cardsInDeck.getChildren().add(cardPane);
            cardPane.setOnMouseClicked(event -> {
                controller.removeCardFromDeck(id, deck.getName());
                controller.loadDeck(deck.getName());
            });
        }

        return cardsInDeck;
    }

    private int getNumberOfSpells(Deck deck) {
        int counter = 0;
        for (Card card : deck.getCards()) {
            if (card instanceof SpellCard) {
                counter++;
            }
        }
        return counter;
    }

    private int getNumberOfMinions(Deck deck) {
        int counter = 0;
        for (Card card : deck.getCards()) {
            if (card instanceof Minion) {
                counter++;
            }
        }
        return counter;
    }

    private int getNumberOfHero(Deck deck) {
        if (deck.getHero() == null) {
            return 0;
        } else return 1;
    }

    private int getNumberOfItems(Deck deck) {
        if (deck.getItem() == null) {
            return 0;
        } else {
            return 1;
        }
    }

    private Pane getHeroPane(Card hero) throws FileNotFoundException {
        Pane heroPane = new Pane();
        ScaleTool.resizeRegion(heroPane, 315, 50);
//        heroPane.setStyle("-fx-background-color: rgb(33, 49 ,64)");

        ImageView heroCard = new ImageView(new Image(new FileInputStream("src/newView/resources/collectionIcons/heroCard.png")));
        ScaleTool.resizeImageView(heroCard, 320, 50);
        heroPane.getChildren().add(heroCard);

        Text heroName = new Text();
        heroName.setText(hero.getName());
        heroName.setFill(Color.WHITE);
        heroName.setStyle("-fx-font-size: 18");
        ScaleTool.relocate(heroName, 60, 18);
        heroPane.getChildren().add(heroName);

        return heroPane;
    }

    private Pane getNonHeroPane(Object card) throws FileNotFoundException {
        int manaCost = 0;
        String name = null;
        if (card instanceof Card) {
            manaCost = ((Card) card).getManaCost();
            name = ((Card) card).getName();
        } else if (card instanceof Item) {
            manaCost = 0;
            name = ((Item) card).getName();
        }

        Pane cardPane = new Pane();
        ScaleTool.resizeRegion(cardPane, 315, 50);
//        cardPane.setStyle("-fx-background-color: rgb(33, 49 ,64)");


        ImageView deck = new ImageView(new Image(new FileInputStream("src/newView/resources/collectionIcons/deck.png")));
        ScaleTool.resizeImageView(deck, 320, 50);
        cardPane.getChildren().add(deck);

        Text cardName = new Text();
        cardName.setText(name);
        ScaleTool.relocate(cardName, 60, 18);
        cardName.setFill(Color.WHITE);
        cardName.setStyle("-fx-font-size: 18");
        cardPane.getChildren().add(cardName);

        Text mana = new Text();
        mana.setText(Integer.toString(manaCost));
        mana.setStyle("-fx-font-size: 16");
        ScaleTool.relocate(mana, 17, 18);
        cardPane.getChildren().add(mana);

        return cardPane;
    }

    private void showCollection(GridPane visibleCards, List collection) {
        visibleCards.getChildren().removeIf(node -> true);
        try {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    if (collection.size() > 5 * i + j + collectionCounter) {
                        Object card = collection.get(5 * i + j + collectionCounter);
                        Pane cardView;
                        int cardId;
                        if (card instanceof Item) {
                            String name = ((Item) card).getName();
                            cardView = new CardMaker(name, Type.ITEM).getItemCardView();
                            cardId = ((Item) card).getCollectionID();
                        } else if (card instanceof Hero) {
                            String name = ((Hero) card).getName();
                            cardView = new CardMaker(name, Type.HERO).getUnitCardView();
                            cardId = ((Hero) card).getCollectionID();
                        } else if (card instanceof Minion) {
                            String name = ((Minion) card).getName();
                            cardView = new CardMaker(name, Type.MINION).getUnitCardView();
                            cardId = ((Minion) card).getCollectionID();
                        } else {
                            String name = ((SpellCard) card).getName();
                            cardView = new CardMaker(name, Type.SPELL).getSpellCardView();
                            cardId = ((SpellCard) card).getCollectionID();
                        }
                        cardView.setOnMouseClicked(event -> addCardToSelectedDeck(cardId));
                        visibleCards.add(cardView, j, i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCardToSelectedDeck(int cardId) {
        if (!showingDecks) {
            controller.addCardToDeck(cardId, selectedDeck.getName());
            controller.loadDeck(selectedDeck.getName());
        }
    }

    @Override
    public void setController(CollectionContract.Controller controller) {

    }

    @Override
    public void showAllDecks(Deck mainDeck, ArrayList<Deck> decks) {
        showingDecks = true;
        this.decks = decks;
//        decks.remove(mainDeck);
//        decks.add(0, mainDeck);
        try {
            updateRightPart();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDeck(Deck deck) {
        selectedDeck = deck;
        showingDecks = false;
        try {
            updateRightPart();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        collection = new ArrayList<>();
        collection.addAll(cards);
        collection.addAll(heroes);
        collection.addAll(items);
    }

    @Override
    public void goToBattleMenu() {

    }
}
