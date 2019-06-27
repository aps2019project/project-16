package newView.GraphicalElements.battle;

import com.dd.plist.PropertyListFormatException;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import newView.AnimationMaker;
import newView.CardMaker;
import newView.GraphicalElements.ScaleTool;
import newView.Type;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.text.ParseException;

import static newView.SceneMakers.SceneMaker.HEIGHT;

public class GraveyardPane extends ScrollPane {
    private VBox cards = new VBox();

    public GraveyardPane() throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        ScaleTool.relocate(this, 0, HEIGHT * 0.6);

        // IT IS JUST FOR TEST ***
        Pane crd = new CardMaker("kave", Type.HERO).getUnitCardView();
        ScaleTool.homothety(crd, 0.5);
        cards.getChildren().add(crd);
        //*******
        this.setContent(cards);
    }

    public void addCard(String name, Type type) throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        Pane cardView = new Pane();
        if (type == Type.SPELL) {
            cardView = new CardMaker(name, type).getSpellCardView();
        } else if (type == Type.MINION) {
            cardView = new CardMaker(name, type).getUnitCardView();
        } else if (type == Type.HERO) {
            cardView = new CardMaker(name, type).getUnitCardView();
        }
        ScaleTool.homothety(cardView, 0.5);
        //animation + name
        cards.getChildren().add(cardView);

    }
}
