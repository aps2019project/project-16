package newView.SceneMakers;

import com.dd.plist.PropertyListFormatException;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import newView.AnimationMaker;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;
import newView.menu.LoginPage;
import newView.menu.Page;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

public class LoadingSceneMaker extends SceneMaker {

    public LoadingSceneMaker(Page page) {
        super(page);
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane pane = new Pane();


        ImageView demon = AnimationMaker.getSimpleAnimation("demon", "src/newView/resources/loading/");
        ImageView brand = new ImageView(new Image(new FileInputStream("src/newView/resources/loading/brand_duelyst_preloading.png")));
        Text text = new Text();
        text.setText("THIS MAY TAKE SOME MOMENTS");
        text.setFill(Color.rgb(81, 82, 82));
        text.setStyle("-fx-font: 24 arial;");
        ScaleTool.resizeImageView(brand, 200, 50);
        ScaleTool.resizeImageView(demon, 85, 85);

        ScaleTool.relocate(brand , 500, 200);
        ScaleTool.relocate(text, 420, 350);
        ScaleTool.relocate(demon , 550 , 240);

        pane.getChildren().add(text);
        pane.getChildren().add(demon);
        pane.getChildren().add(brand);
        pane.setStyle("-fx-background-color: rgb(0,0,0); -fx-text-fill: White");
        new Thread(() -> {
            try {
                Thread.sleep(2500);
                Platform.runLater(() -> {
                    try {
                        new LoginPage(getPage(), getPage().getStage()).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return new MyScene(pane);
    }
}