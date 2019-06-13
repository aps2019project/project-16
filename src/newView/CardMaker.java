package newView;

import com.dd.plist.PropertyListFormatException;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.Shop;
import models.card.Card;
import models.card.Hero;
import models.card.Unit;
import newView.AnimationMaker;
import newView.GraphicalElements.ScaleTool;
import newView.Type;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class CardMaker {
    Shop shop = new Shop();
    private String name;
    private File backGround;
    private Text hp;
    private Text ap;
    private Text nameOfCard;
    private Text typeOfCard;
    private Type type;
    private ImageView sprite;


    public CardMaker(String name, Type type) throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        this.name = name;
        this.type = type;
        backGround = new File("src/newView/resources/cardBackGrounds/" + type.getName() + ".png");
        setAp();
        setHp();
        setNameOfCard();
        setTypeOfCard();
        setSprite();

    }

    public void setHp() {
        hp = new Text();
        hp.setFill(Color.WHITE);
        Card card;
        if (type != Type.SPELL && type != Type.ITEM) {
            card = (Unit) shop.getCard(name);
            hp.setText(String.valueOf(((Unit) card).getHp()));
        }
    }

    public void setAp() {
        ap = new Text();
        ap.setFill(Color.WHITE);
        Card card;
        if (type != Type.SPELL && type != Type.ITEM) {
            card = (Unit) shop.getCard(name);
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

        stackPaneName.setPrefHeight(200);//todo must be * scale
        stackPaneName.setPrefWidth(150);//todo must be * scale

        stackPaneType.setPrefHeight(200);//todo must be * scale
        stackPaneType.setPrefWidth(150);//todo must be * scale

        stackPaneName.getChildren().add(name);
        stackPaneType.getChildren().add(type);
        stackPaneType.setLayoutY(40);//todo must be * scale
        stackPaneName.setLayoutY(65);//todo must be * scale

        root.getChildren().add(backGround);
        root.getChildren().add(sprite);
        root.getChildren().add(stackPaneName);
        root.getChildren().add(stackPaneType);
        root.getChildren().add(ap);
        root.getChildren().add(hp);

        return root;
    }

    public Pane getSpellCardView() throws FileNotFoundException {
        Pane root = new Pane();
        StackPane stackPaneName = new StackPane();
        StackPane stackPaneType = new StackPane();

        ImageView backGround = new ImageView(new Image(new FileInputStream(getBackGround())));
        ImageView sprite = getSprite();

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

        stackPaneName.setPrefHeight(200);//todo must be * scale
        stackPaneName.setPrefWidth(150);//todo must be * scale

        stackPaneType.setPrefHeight(200);//todo must be * scale
        stackPaneType.setPrefWidth(150);//todo must be * scale

        stackPaneName.getChildren().add(name);
        stackPaneType.getChildren().add(type);
        stackPaneType.setLayoutY(40);//todo must be * scale
        stackPaneName.setLayoutY(65);//todo must be * scale

        root.getChildren().add(backGround);
        root.getChildren().add(sprite);
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
        ScaleTool.resizeImageView(sprite, 150, 150);

        sprite.setY(-30); //todo must be * scale

        Text name = nameOfCard;
        Text type = typeOfCard;

        name.setX(75); //todo must be * scale
        type.setX(50);//todo must be * scale

        name.setY(160);//todo must be * scale
        type.setY(140);//todo must be * scale

        stackPaneName.setPrefHeight(200);//todo must be * scale
        stackPaneName.setPrefWidth(150);//todo must be * scale

        stackPaneType.setPrefHeight(200);//todo must be * scale
        stackPaneType.setPrefWidth(150);//todo must be * scale

        stackPaneName.getChildren().add(name);
        stackPaneType.getChildren().add(type);
        stackPaneType.setLayoutY(40);//todo must be * scale
        stackPaneName.setLayoutY(65);//todo must be * scale

        root.getChildren().add(backGround);
        root.getChildren().add(sprite);
        root.getChildren().add(stackPaneType);
        root.getChildren().add(stackPaneName);
        return root;
    }

}
