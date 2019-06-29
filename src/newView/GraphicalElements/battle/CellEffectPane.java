package newView.GraphicalElements.battle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CellEffectPane extends GridPane {
    private static Image fireImage;
    private static Image holyImage;
    private static Image poisonImage;

    static {
        try {
            fireImage = new Image(new FileInputStream("src/newView/resources/cellEffects/fire.png"));
            holyImage = new Image(new FileInputStream("src/newView/resources/cellEffects/holy.png"));
            poisonImage = new Image(new FileInputStream("src/newView/resources/cellEffects/poison.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ImageView fireView = getFireView();
    private ImageView holyView = getHolyView();
    private ImageView poisonView = getPoisonView();
    private int numberOfFires = 0;
    private int numberOfHolies = 0;
    private int numberOfPoisons = 0;
    private Text fireText = new Text();
    private Text holyText = new Text();
    private Text poisonText = new Text();

    {
        Text[] texts = {fireText, holyText, poisonText};
        for (Text text : texts) {
            text.setFill(Color.BLACK);
            text.setScaleX(1.5);
            text.setScaleY(1.5);
        }
    }

    private final static double EFFECT_LENGTH = 30;

    public CellEffectPane() {
        super();
    }

    public void addFire() {
        if (numberOfFires == 0) {
            this.add(fireView, 0, 0);
            this.add(fireText, 0, 0);
        }
        numberOfFires++;
        fireText.setText("  " + numberOfFires + "");
    }

    public void addHoly() {
        if (numberOfHolies == 0) {
            this.add(holyView, 0, 1);
            this.add(holyText, 0, 1);
        }
        numberOfHolies++;
        holyText.setText("  " + numberOfHolies + "");
    }

    public void addPoison() {
        if (numberOfPoisons == 0) {
            this.add(poisonView, 1, 1);
            this.add(poisonText, 1, 1);
        }
        numberOfPoisons++;
        poisonText.setText("  " + numberOfPoisons + "");
    }

    public void decreaseFire() {
        if (numberOfFires == 1) {
            this.getChildren().remove(fireView);
            this.getChildren().remove(fireText);
        }
        numberOfFires--;
        fireText.setText("  " + numberOfFires + "");
    }

    public void decreaseHoly() {
        if (numberOfHolies == 1) {
            this.getChildren().remove(holyView);
            this.getChildren().remove(holyText);
        }
        numberOfHolies--;
        holyText.setText("  " + numberOfHolies + "" + "");
    }

    public void decreasePoison() {
        if (numberOfPoisons == 1) {
            this.getChildren().remove(poisonView);
            this.getChildren().remove(poisonText);
        }
        numberOfPoisons--;
        poisonText.setText("  " + numberOfPoisons);
    }


    private ImageView getFireView() {
        ImageView imageView = new ImageView(fireImage);
        ScaleTool.resizeImageView(imageView, EFFECT_LENGTH, EFFECT_LENGTH);
        ScaleTool.relocate(imageView, EFFECT_LENGTH * 0, 0);
        return imageView;
    }

    private ImageView getHolyView() {
        ImageView imageView = new ImageView(holyImage);
        ScaleTool.resizeImageView(imageView, EFFECT_LENGTH, EFFECT_LENGTH);
        ScaleTool.relocate(imageView, EFFECT_LENGTH * 1, 0);
        return imageView;
    }

    private ImageView getPoisonView() {
        ImageView imageView = new ImageView(poisonImage);
        ScaleTool.resizeImageView(imageView, EFFECT_LENGTH, EFFECT_LENGTH);
        ScaleTool.relocate(imageView, EFFECT_LENGTH * 2, 0);
        return imageView;
    }
}
