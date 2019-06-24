package newView.SceneMakers;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;

public class CustomCardSceneMaker extends SceneMaker {
    public CustomCardSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane pane = new Pane();

        VBox buff = new VBox();

        VBox unit = new VBox();
        VBox spell = new VBox();

        VBox specialPower = new VBox();
        VBox spellBuff = new VBox();


        BackgroundMaker.setBackgroundFor(pane, 1, "customCard");

        TextField name = new TextField();
        name.setPromptText("ENTER YOUR NAME");
        name.setStyle("-fx-prompt-text-fill: gray");


        ChoiceBox type = new ChoiceBox();
        type.setItems(FXCollections.observableArrayList(
                "HERO", "MINION", "ITEM"
        ));


        gettingSpellProperties(spell, spellBuff);

        //vase unit

        gettingUnitProperties(unit, specialPower);

        //vase buff

        gettingBuffProperties(buff);


        pane.getChildren().add(name);
        pane.getChildren().add(type);
        pane.getChildren().add(gettingSpellProperties(spell, spellBuff));
//        pane.getChildren().addAll(gettingUnitProperties(unit, specialPower));


        return new MyScene(pane);
    }

    private VBox gettingSpellProperties(VBox spell, VBox spellBuff) {
        ChoiceBox targetSociety = new ChoiceBox();
        targetSociety.setItems(FXCollections.observableArrayList(
                "AnyUnit", "EnemiesInColumn", "OneRandomNearestUnit", "OneRandomUnit", "OneUnit",
                "RandomEnemyMinionAdjacentToHero", "SquareOfCell", "UnitsAdjacentToCell", "UnitsInHeroRow",
                "UnitInHeroRow", "UnitInRange", "UnitTargetSociety"
        ));

        spell.getChildren().addAll(targetSociety, gettingBuffProperties(spellBuff));
        return spell;
    }

    private VBox gettingUnitProperties(VBox unit, VBox specialPower) {
        TextField ap = new TextField();
        ap.setPromptText("ENTER AP");
        ap.setStyle("-fx-prompt-text-fill: gray");

        TextField hp = new TextField();
        hp.setPromptText("ENTER HP");
        hp.setStyle("-fx-prompt-text-fill: gray");

        ChoiceBox attackType = new ChoiceBox();
        attackType.setItems(FXCollections.observableArrayList(
                "MELEE", "RANGED", "HYBRID"
        ));

        TextField range = new TextField();
        range.setPromptText("ENTER RANGE");
        range.setStyle("-fx-prompt-text-fill: gray");

        ChoiceBox specialPowerActivition = new ChoiceBox();
        specialPowerActivition.setItems(FXCollections.observableArrayList(
                "ON_ATTACK", "ON_DEATH", "PASSIVE", "ON_SPAWN"
        ));

        TextField specialPowerCooldown = new TextField();
        specialPowerCooldown.setStyle("-fx-prompt-text-fill: gray");
        specialPowerCooldown.setPromptText("ENTER SPECIAL POWER COOL DOWN");

        TextField cost = new TextField();
        cost.setPromptText("ENTER COST");
        cost.setStyle("-fx-prompt-text-fill: gray");

        unit.getChildren().addAll(ap, hp, attackType, range, gettingBuffProperties(specialPower), cost);
        return unit;
    }

    private VBox gettingBuffProperties(VBox buff) {
        TextField buffName = new TextField();
        buffName.setStyle("-fx-prompt-text-fill: gray");
        buffName.setPromptText("ENTER BUFF NAME");

        ChoiceBox buffType = new ChoiceBox();
        buffType.setItems(FXCollections.observableArrayList(
                "holy", "power", "poison", ""
        ));

        TextField effectValue = new TextField();
        effectValue.setPromptText("ENTER EFFECT VALUE");
        effectValue.setStyle("-fx-prompt-text-fill: gray");

        TextField delay = new TextField();
        delay.setStyle("-fx-prompt-text-fill: gray");
        delay.setPromptText("ENTER DELAY");

        TextField last = new TextField();
        last.setPromptText("ENTER LAST");
        last.setStyle("-fx-prompt-text-fill: gray");


        ChoiceBox friendOrEnemy = new ChoiceBox();
        friendOrEnemy.setItems(FXCollections.observableArrayList(
                "enemy", "friendly"
        ));

//        buff.getChildren().addAll(buffName, buffType, effectValue, delay, last, friendOrEnemy);
        return buff;
    }
}