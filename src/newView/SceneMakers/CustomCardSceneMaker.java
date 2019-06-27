package newView.SceneMakers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.GameContents;
import models.SpecialPowerCastTime;
import models.attackType.AttackType;
import models.attackType.Hybrid;
import models.attackType.Melee;
import models.attackType.Ranged;
import models.card.Hero;
import models.card.Minion;
import models.card.SpellCard;
import models.magic.Buff;
import models.magic.Spell;
import models.targetsociety.TargetSociety;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.util.ArrayList;

public class CustomCardSceneMaker extends SceneMaker {

    public static final String HERO = "HERO";
    public static final String MINION = "MINION";
    public static final String SPELL = "SPELL";
    private TextField ap;
    private TextField hp;
    private TextField range;
    private ChoiceBox specialPowerActivation;
    private TextField specialPowerCoolDown;
    private TextField cost;
    private ChoiceBox attackType;
    private TextField buffName;
    private ChoiceBox buffType;
    private TextField effectValue;
    private TextField delay;
    private TextField last;
    private ChoiceBox friendOrEnemy;

    private ArrayList<Buff> buffs;
    private ChoiceBox targetSociety;

    public CustomCardSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    private boolean showingUnit = true;

    public static final String MELEE = "MELEE";

    public static final String RANGED = "RANGED";

    public static final String HYBRID = "HYBRID";

    public static final String ON_ATTACK = "ON_ATTACK";

    public static final String ON_DEATH = "ON_DEATH";

    public static final String PASSIVE = "PASSIVE";

    public static final String ON_SPAWN = "ON_SPAWN";

    public static final String HOLY = "holy";

    public static final String HP_CHANGE = "hp";

    public static final String AP_CHANGE = "ap";

    public static final String POISON = "poison";

    {
        ap = new TextField();
        ap.setPromptText("ENTER AP");
        ap.setStyle("-fx-prompt-text-fill: gray");

        hp = new TextField();
        hp.setPromptText("ENTER HP");
        hp.setStyle("-fx-prompt-text-fill: gray");

        attackType = new ChoiceBox();
        attackType.setItems(FXCollections.observableArrayList(
                MELEE, RANGED, HYBRID
        ));

        range = new TextField();
        range.setPromptText("ENTER RANGE");
        range.setStyle("-fx-prompt-text-fill: gray");

        specialPowerActivation = new ChoiceBox();
        specialPowerActivation.setItems(FXCollections.observableArrayList(
                ON_ATTACK, ON_DEATH, PASSIVE, ON_SPAWN
        ));

        specialPowerCoolDown = new TextField();
        specialPowerCoolDown.setStyle("-fx-prompt-text-fill: gray");
        specialPowerCoolDown.setPromptText("ENTER SPECIAL POWER COOL DOWN");

        cost = new TextField();
        cost.setPromptText("ENTER COST");
        cost.setStyle("-fx-prompt-text-fill: gray");

        buffName = new TextField();
        buffName.setStyle("-fx-prompt-text-fill: gray");
        buffName.setPromptText("ENTER BUFF NAME");

        buffType = new ChoiceBox();
        buffType.setItems(FXCollections.observableArrayList(
                HOLY, HP_CHANGE, AP_CHANGE, POISON
        ));

        effectValue = new TextField();
        effectValue.setPromptText("ENTER EFFECT VALUE");
        effectValue.setStyle("-fx-prompt-text-fill: gray");

        delay = new TextField();
        delay.setStyle("-fx-prompt-text-fill: gray");
        delay.setPromptText("ENTER DELAY");

        last = new TextField();
        last.setPromptText("ENTER LAST");
        last.setStyle("-fx-prompt-text-fill: gray");

        targetSociety = new ChoiceBox();
        targetSociety.setItems(FXCollections.observableArrayList(
                "AnyUnit", "EnemiesInColumn", "OneRandomNearestUnit", "OneRandomUnit", "OneUnit",
                "RandomEnemyMinionAdjacentToHero", "SquareOfCell", "UnitsAdjacentToCell", "UnitsInHeroRow",
                "UnitInHeroRow", "UnitInRange", "UnitTargetSociety"
        ));

        friendOrEnemy = new ChoiceBox();
        friendOrEnemy.setItems(FXCollections.observableArrayList(
                "enemy", "friendly"
        ));
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
        ScaleTool.relocate(name, 100, 100);

        ChoiceBox type = new ChoiceBox();
        type.setItems(FXCollections.observableArrayList(
                HERO, MINION, SPELL
        ));

        type.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                pane.getChildren().remove(spell);
                pane.getChildren().remove(unit);
                switch (new_value.intValue()) {
                    case 2:
                        pane.getChildren().add(spell);
                        break;
                    default:
                        pane.getChildren().add(unit);
                }
            }
        });
        ScaleTool.relocate(type, 100, 200);

        //for spell
        addingSpellProperties(spell, spellBuff);
        ScaleTool.relocate(spell, 200, 300);
        //for unit
        addingUnitProperties(unit, specialPower);
        ScaleTool.relocate(unit, 200, 300);

        ImageView create = new ImageView(new Image(new FileInputStream("src/newView/resources/customCard/create.png")));
        create.setOnMouseClicked(event -> {

            switch ((String) type.getValue()) {
                case HERO:
                    GameContents.getShop().addCard(new Hero.HeroBuilder()
                            .setSpell(new Spell.SpellBuilder()
                                    .addBuffs(buffs)
                                    .setTargetSociety(getTargetSociety())
                                    .create())
                            .setSpellCoolDown(Integer.parseInt(specialPowerCoolDown.getText()))
                            .setSpellManaCost(3)
                            .setAp(Integer.parseInt(ap.getText()))
                            .setHp(Integer.parseInt(hp.getText()))
                            .setAttackType(getAttackType())
                            .setName(name.getText())
                            .setBuyPrice(Integer.parseInt(cost.getText()))
                            .setManaCost(6)//todo
                            .create());
                    break;
                case MINION:
                    GameContents.getShop().addCard(new Minion.MinionBuilder()
                            .setSpecialPower(new Spell.SpellBuilder()
                                    .addBuffs(buffs)
                                    .setTargetSociety(getTargetSociety())
                                    .create())
                            .setSpecialPowerCastTime(getSpecialPowerCastTime())
                            .setAp(Integer.parseInt(ap.getText()))
                            .setHp(Integer.parseInt(hp.getText()))
                            .setAttackType(getAttackType())
                            .setName(name.getText())
                            .setBuyPrice(Integer.parseInt(cost.getText()))
                            .setManaCost(6)//todo
                            .create());
                    break;
                case SPELL:
                    GameContents.getShop().addCard(new SpellCard.SpellCardBuilder()
                            .setSpell(new Spell.SpellBuilder()
                                    .addBuffs(buffs)
                                    .setTargetSociety(getTargetSociety())
                                    .create())
                            .setName(name.getText())
                            .setBuyPrice(Integer.parseInt(cost.getText()))
                            .setManaCost(6)//todo
                            .create());
                    break;
            }
        });
        create.setOnMouseEntered(event -> {
            create.setEffect(new Glow(1));
        });
        create.setOnMouseExited(event -> {
            create.setEffect(new Glow(0));
        });
        ScaleTool.resizeImageView(create, 100, 40);
        ScaleTool.relocate(create, 400, 500);

        if (showingUnit)
            pane.getChildren().add(unit);
        else
            pane.getChildren().add(spell);

        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/customCard/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());

        gettingBuffProperties(buff);
        ScaleTool.relocate(buff, 700, 300);

        ImageView addBuff = new ImageView(new Image(new FileInputStream("src/newView/resources/customCard/add buff.png")));
        ScaleTool.resizeImageView(addBuff, 100, 40);
        ScaleTool.relocate(addBuff, 700, 500);
        addBuff.setOnMouseClicked(event -> {
            Buff.BuffBuilder builder = new Buff.BuffBuilder();
            switch ((String) buffType.getValue()) {
                case HOLY:
                    builder.setHoly(Integer.parseInt(effectValue.getText()));
                    break;
                case POISON:
                    builder.setPoison(Integer.parseInt(effectValue.getText()));
                    break;
                case AP_CHANGE:
                    builder.setDeltaAP(Integer.parseInt(effectValue.getText()));
                    break;
                case HP_CHANGE:
                    builder.setDeltaHP(Integer.parseInt(effectValue.getText()));
                    break;
            }
            buffs.add(builder
                    .setDuration(Integer.parseInt(last.getText()))
                    .setDurationToStart(Integer.parseInt(delay.getText()))
                    .create());
        });

        pane.getChildren().add(type);
        pane.getChildren().add(name);
        pane.getChildren().add(create);
        pane.getChildren().add(back);
        pane.getChildren().add(buff);
        pane.getChildren().add(addBuff);

        return new MyScene(pane);
    }

    private TargetSociety getTargetSociety() {
        return null; //todo
    }

    private SpecialPowerCastTime getSpecialPowerCastTime() {
        SpecialPowerCastTime specialPowerCastTime;
        switch ((String) this.attackType.getValue()) {
            case ON_ATTACK:
                specialPowerCastTime = SpecialPowerCastTime.ON_ATTACK;
                break;
            case ON_DEATH:
                specialPowerCastTime = SpecialPowerCastTime.ON_DEATH;
                break;
            case PASSIVE:
                specialPowerCastTime = SpecialPowerCastTime.PASSIVE;
                break;
            case ON_SPAWN:
            default:
                specialPowerCastTime = SpecialPowerCastTime.ON_SPAWN;
        }
        return specialPowerCastTime;
    }

    private AttackType getAttackType() {
        AttackType attackType;
        switch ((String) this.attackType.getValue()) {
            case RANGED:
                attackType = new Ranged(Integer.parseInt(range.getText()));
                break;
            case HYBRID:
                attackType = new Hybrid(Integer.parseInt(range.getText()));
                break;
            case MELEE:
            default:
                attackType = new Melee();
        }
        return attackType;
    }

    private void addingSpellProperties(VBox spell, VBox spellBuff) {
        spell.getChildren().addAll(targetSociety/*, gettingBuffProperties(spellBuff)*/);
    }

    private void addingUnitProperties(VBox unit, VBox specialPower) {
        unit.getChildren().addAll(ap, hp, attackType, range, targetSociety, /*gettingBuffProperties(specialPower),*/ cost);
    }

    private VBox gettingBuffProperties(VBox buff) {
        buff.getChildren().addAll(buffName, buffType, effectValue, delay, last, friendOrEnemy);
        return buff;
    }
}
