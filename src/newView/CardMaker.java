package newView;

import com.dd.plist.PropertyListFormatException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.GameContents;
import models.Shop;
import models.card.Card;
import models.card.Unit;
import models.item.Item;
import newView.GraphicalElements.ScaleTool;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class CardMaker {
    private String name;
    private File backGround;
    private Text hp;
    private ImageView mana;
    private Text manaValue;
    private Text ap;
    private Text nameOfCard;
    private Text typeOfCard;
    private Type type;
    private ImageView sprite;
    private Card card;

    public CardMaker(String name, Type type, Card card) throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        this.name = name;
        this.type = type;
        this.card = card;
        backGround = new File("src/newView/resources/cardBackgrounds/" + type.getName() + ".png");
        setAp();
        setHp();
        setMana();
        setNameOfCard();
        setTypeOfCard();
        setSprite();
    }

    public CardMaker(String name) throws Exception  {
        this.name = name;
        this.type = Type.ITEM;
        backGround = new File("src/newView/resources/cardBackgrounds/" + type.getName() + ".png");
        setNameOfCard();
        setTypeOfCard();
        setSprite();
    }


    private void setMana() {
        manaValue = new Text();
        manaValue.setFill(Color.BLACK);
        if (type != Type.ITEM) {
            manaValue.setText(String.valueOf(card.getManaCost()));
        }
    }


    private StackPane getManaView() throws FileNotFoundException {
        mana = new ImageView(new Image(new FileInputStream("src/newView/resources/cardBackgrounds/mana.png")));
        ScaleTool.resizeImageView(mana, 30, 30);
        StackPane manaView = new StackPane();

        manaView.getChildren().addAll(mana, manaValue);
        return manaView;
    }


    public void setHp() {
        hp = new Text();
        hp.setFill(Color.WHITE);
        if (type != Type.SPELL && type != Type.ITEM) {
            hp.setText(String.valueOf(((Unit) card).getHp()));
        }
    }

    public void setAp() {
        ap = new Text();
        ap.setFill(Color.WHITE);
        if (type != Type.SPELL && type != Type.ITEM) {
            ap.setText(String.valueOf(((Unit) card).getAp()));
        }
    }

    private void setNameOfCard() {
        nameOfCard = new Text();
        nameOfCard.setFill(Color.WHITE);
        nameOfCard.setText(name);
    }

    private void setTypeOfCard() {
        typeOfCard = new Text();
        typeOfCard.setFill(Color.WHITE);
        typeOfCard.setText(type.getName().toUpperCase());
    }

    private void setSprite() throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        if (type != Type.SPELL && type != Type.ITEM) {
            if (type == Type.HERO)
                sprite = AnimationMaker.getBreathingAnimation(name, "hero");
            if (type == Type.MINION)
                sprite = AnimationMaker.getBreathingAnimation(name, "minion");
        } else {
            if (type == Type.SPELL)
                sprite = AnimationMaker.getNothingAnimation(name, "spell");
            if (type == Type.ITEM)
                sprite = AnimationMaker.getNothingAnimation(name, "item");
        }
    }

    public String getName() {
        return name;
    }

    public File getBackGround() {
        return backGround;
    }

    public Text getHp() {
        return hp;
    }

    public Text getAp() {
        return ap;
    }

    public Text getNameOfCard() {
        return nameOfCard;
    }

    public Text getTypeOfCard() {
        return typeOfCard;
    }

    public Type getType() {
        return type;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public Pane getUnitCardView() throws FileNotFoundException {
        Pane root = new Pane();
        StackPane stackPaneName = new StackPane();
        StackPane stackPaneType = new StackPane();
        StackPane mana = getManaView();

        ImageView backGround = new ImageView(new Image(new FileInputStream(getBackGround())));
        ImageView sprite = getSprite();

        ScaleTool.resizeImageView(backGround, 150, 200);
        ScaleTool.resizeImageView(sprite, 150, 150);

        sprite.setY(-30); //todo must be * scale

        Text name = nameOfCard;
        Text type = typeOfCard;
        Text ap = getAp();
        Text hp = getHp();

        name.setX(75); //todo must be * scale
        type.setX(50);//todo must be * scale
        ap.setX(29);//todo must be * scale
        hp.setX(108);//todo must be * scale

        name.setY(160);//todo must be * scale
        type.setY(140);//todo must be * scale
        ap.setY(124);//todo must be * scale
        hp.setY(124);//todo must be * scale

        stackPaneName.setPrefWidth(150);//todo must be * scale

        stackPaneType.setPrefWidth(150);//todo must be * scale

        stackPaneName.getChildren().add(name);
        stackPaneType.getChildren().add(type);
        stackPaneType.setLayoutY(135);//todo must be * scale
        stackPaneName.setLayoutY(160);//todo must be * scale

        root.getChildren().addAll(backGround, sprite, stackPaneName, stackPaneType, ap, hp);
        if (this.type == Type.MINION)
            root.getChildren().add(mana);
        return root;
    }

    public Pane getSpellCardView() throws FileNotFoundException {
        Pane root = new Pane();
        StackPane stackPaneName = new StackPane();
        StackPane stackPaneType = new StackPane();


        ImageView backGround = new ImageView(new Image(new FileInputStream(getBackGround())));
        ImageView sprite = getSprite();

        StackPane mana = getManaView();

        ScaleTool.resizeImageView(backGround, 150, 200);
        ScaleTool.resizeImageView(sprite, 100, 100);

        sprite.setY(15); //todo must be * scale
        sprite.setX(25);

        Text name = nameOfCard;
        Text type = typeOfCard;

        name.setX(75); //todo must be * scale
        type.setX(50);//todo must be * scale

        name.setY(160);//todo must be * scale
        type.setY(140);//todo must be * scale

        stackPaneName.setPrefWidth(150);//todo must be * scale

        stackPaneType.setPrefWidth(150);//todo must be * scale

        stackPaneName.getChildren().add(name);
        stackPaneType.getChildren().add(type);
        stackPaneType.setLayoutY(135);//todo must be * scale
        stackPaneName.setLayoutY(160);//todo must be * scale

        root.getChildren().add(backGround);
        root.getChildren().add(sprite);
        root.getChildren().add(mana);
        root.getChildren().add(stackPaneType);
        root.getChildren().add(stackPaneName);

        return root;
    }

    public Pane getItemCardView() throws FileNotFoundException {
        Pane root = new Pane();
        StackPane stackPaneName = new StackPane();
        StackPane stackPaneType = new StackPane();

        ImageView backGround = new ImageView(new Image(new FileInputStream(getBackGround())));
        ImageView sprite = getSprite();

        ScaleTool.resizeImageView(backGround, 150, 200);
        ScaleTool.resizeImageView(sprite, 110, 110);

        sprite.setY(10); //todo must be * scale
        sprite.setX(20);

        Text name = nameOfCard;
        Text type = typeOfCard;

        name.setX(75); //todo must be * scale
        type.setX(50);//todo must be * scale

        name.setY(160);//todo must be * scale
        type.setY(140);//todo must be * scale

        stackPaneName.setPrefWidth(150);//todo must be * scale

        stackPaneType.setPrefWidth(150);//todo must be * scale

        stackPaneName.getChildren().add(name);
        stackPaneType.getChildren().add(type);
        stackPaneType.setLayoutY(135);//todo must be * scale
        stackPaneName.setLayoutY(160);//todo must be * scale

        root.getChildren().add(backGround);
        root.getChildren().add(sprite);
        root.getChildren().add(stackPaneType);
        root.getChildren().add(stackPaneName);
        return root;
    }

}
