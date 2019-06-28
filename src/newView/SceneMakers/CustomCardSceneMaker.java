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
import models.targetsociety.*;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.util.ArrayList;

public class CustomCardSceneMaker extends SceneMaker {

    private static final String HERO = "HERO";
    private static final String MINION = "MINION";
    private static final String SPELL = "SPELL";

    private TextField ap;
    private TextField hp;
    private TextField range;
    private TextField specialPowerCoolDown;
    private TextField cost;
    private TextField buffName;
    private TextField effectValue;
    private TextField delay;
    private TextField last;
    private ChoiceBox<String> specialPowerActivation;
    private ChoiceBox<String> attackType;
    private ChoiceBox<String> buffType;

    private ArrayList<Buff> buffs = new ArrayList<>();
    private ChoiceBox<String> targetSociety;
    private ChoiceBox<String> targetTeam;
    private ChoiceBox<String> targetType;

    public CustomCardSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    private static final String MELEE = "MELEE";
    private static final String RANGED = "RANGED";
    private static final String HYBRID = "HYBRID";

    private static final String ON_ATTACK = "ON_ATTACK";
    private static final String ON_DEATH = "ON_DEATH";
    private static final String PASSIVE = "PASSIVE";
    private static final String ON_SPAWN = "ON_SPAWN";

    private static final String HOLY = "holy";
    private static final String HP_CHANGE = "hp";
    private static final String AP_CHANGE = "ap";
    private static final String POISON = "poison";

    private static final String ENEMY = "ENEMY";
    private static final String FRIEND = "FRIEND";
    private static final String ANY = "ANY";

    private static final String UNIT = "UNIT";

    {
        ap = new TextField();
        ap.setPromptText("ENTER AP");
        ap.setStyle("-fx-prompt-text-fill: gray");

        hp = new TextField();
        hp.setPromptText("ENTER HP");
        hp.setStyle("-fx-prompt-text-fill: gray");

        attackType = new ChoiceBox<>();
        attackType.setItems(FXCollections.observableArrayList(
                MELEE, RANGED, HYBRID
        ));
        attackType.setValue(MELEE);

        range = new TextField();
        range.setPromptText("ENTER RANGE");
        range.setStyle("-fx-prompt-text-fill: gray");

        specialPowerActivation = new ChoiceBox<>();
        specialPowerActivation.setItems(FXCollections.observableArrayList(
                ON_ATTACK, ON_DEATH, PASSIVE, ON_SPAWN
        ));
        specialPowerActivation.setValue(ON_SPAWN);

        specialPowerCoolDown = new TextField();
        specialPowerCoolDown.setStyle("-fx-prompt-text-fill: gray");
        specialPowerCoolDown.setPromptText("ENTER SPECIAL POWER COOL DOWN");

        cost = new TextField();
        cost.setPromptText("ENTER COST");
        cost.setStyle("-fx-prompt-text-fill: gray");

        buffName = new TextField();
        buffName.setStyle("-fx-prompt-text-fill: gray");
        buffName.setPromptText("ENTER BUFF NAME");

        buffType = new ChoiceBox<>();
        buffType.setItems(FXCollections.observableArrayList(
                HOLY, HP_CHANGE, AP_CHANGE, POISON
        ));
        buffType.setValue(HOLY);

        effectValue = new TextField();
        effectValue.setPromptText("ENTER EFFECT VALUE");
        effectValue.setStyle("-fx-prompt-text-fill: gray");

        delay = new TextField();
        delay.setStyle("-fx-prompt-text-fill: gray");
        delay.setPromptText("ENTER DELAY");

        last = new TextField();
        last.setPromptText("ENTER LAST");
        last.setStyle("-fx-prompt-text-fill: gray");

        targetSociety = new ChoiceBox<>();
        targetSociety.setItems(FXCollections.observableArrayList(
                "AnyUnit"/*, "EnemiesInColumn"*/, "OneRandomNearestUnit", "OneRandomUnit", "OneUnit",
                /* "RandomEnemyMinionAdjacentToHero",*/ /*"SquareOfCell",*/ "UnitsAdjacentToCell"/*"UnitsInHeroRow",*/
                /*"UnitInHeroRow"*/
        ));
        targetSociety.setValue("AnyUnit");

        targetTeam = new ChoiceBox<>();
        targetTeam.setItems(FXCollections.observableArrayList(
                ENEMY, FRIEND, ANY
        ));
        targetTeam.setValue(ENEMY);

        targetType = new ChoiceBox<>();
        targetType.setItems(FXCollections.observableArrayList(
                MINION, HERO, UNIT
        ));
        targetType.setValue(UNIT);
    }

    private TargetSociety getTargetSociety() {
        switch (this.targetSociety.getValue()) {
            case "AnyUnit":
                return new AnyUnit(getTargetType(), getTargetTeam(), UnitTargetSociety.TargetAttackType.ANY);
            case "OneRandomNearestUnit":
                return new OneRandomNearestUnit(getTargetType(), getTargetTeam(), UnitTargetSociety.TargetAttackType.ANY);
            case "OneRandomUnit":
                return new OneRandomUnit(getTargetType(), getTargetTeam(), UnitTargetSociety.TargetAttackType.ANY);
            case "OneUnit":
                return new OneUnit(getTargetType(), getTargetTeam(), UnitTargetSociety.TargetAttackType.ANY);
            case "UnitsAdjacentToCell":
                return new UnitsAdjacentToCell(getTargetType(), getTargetTeam(),
                        UnitTargetSociety.TargetAttackType.ANY, false);
        }
        return new AnyUnit(getTargetType(), getTargetTeam(), UnitTargetSociety.TargetAttackType.ANY);
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane pane = new Pane();

        VBox buff = new VBox();


        BackgroundMaker.setBackgroundFor(pane, 1, "customCard");

        TextField name = new TextField();
        name.setPromptText("ENTER YOUR NAME");
        name.setStyle("-fx-prompt-text-fill: gray");
        ScaleTool.relocate(name, 100, 100);

        ChoiceBox<String> type = new ChoiceBox<>();
        type.setItems(FXCollections.observableArrayList(
                HERO, MINION, SPELL
        ));

        type.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            VBox vBox = new VBox();
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                pane.getChildren().remove(vBox);
                vBox = new VBox();
                switch (newValue) {
                    case HERO:
                        addingHeroProperties(vBox);
                        break;
                    case MINION:
                        addingMinionProperties(vBox);
                        break;
                    case SPELL:
                        addingSpellProperties(vBox);
                        break;
                }
                ScaleTool.relocate(vBox, 200, 300);
                pane.getChildren().add(vBox);
            }
        });

        ScaleTool.relocate(type, 100, 200);

        ImageView create = new ImageView(new Image(new FileInputStream("src/newView/resources/customCard/create.png")));
        create.setOnMouseClicked(event -> {

            switch (type.getValue()) {
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
        create.setOnMouseEntered(event -> create.setEffect(new Glow(1)));
        create.setOnMouseExited(event -> create.setEffect(new Glow(0)));
        ScaleTool.resizeImageView(create, 100, 40);
        ScaleTool.relocate(create, 400, 500);

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
            switch (buffType.getValue()) {
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

    private SpecialPowerCastTime getSpecialPowerCastTime() {
        SpecialPowerCastTime specialPowerCastTime;
        switch (this.specialPowerActivation.getValue()) {
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
        switch (this.attackType.getValue()) {
            case RANGED:
                return new Ranged(Integer.parseInt(range.getText()));
            case HYBRID:
                return new Hybrid(Integer.parseInt(range.getText()));
            case MELEE:
            default:
                return new Melee();
        }
    }

    private UnitTargetSociety.TargetType getTargetType() {
        switch (targetType.getValue()) {
            case MINION:
                return UnitTargetSociety.TargetType.MINION;
            case HERO:
                return UnitTargetSociety.TargetType.HERO;
            case UNIT:
            default:
                return UnitTargetSociety.TargetType.UNIT;
        }
    }

    private UnitTargetSociety.TargetTeam getTargetTeam() {
        switch (targetType.getValue()) {
            case FRIEND:
                return UnitTargetSociety.TargetTeam.FRIEND;
            case ENEMY:
                return UnitTargetSociety.TargetTeam.ENEMY;
            case ANY:
            default:
                return UnitTargetSociety.TargetTeam.ANY;
        }
    }

    private void addingSpellProperties(VBox spell) {
        spell.getChildren().addAll(targetSociety, targetTeam, targetType, cost);
    }

    private void addingMinionProperties(VBox unit) {
        unit.getChildren().addAll(ap, hp, attackType, range, targetSociety, targetTeam, targetType, cost);
    }

    private void addingHeroProperties(VBox hero) {
        hero.getChildren().addAll(ap, hp, attackType, range, specialPowerCoolDown, targetSociety, targetTeam,
                targetType, cost);
    }

    private void gettingBuffProperties(VBox buff) {
        buff.getChildren().addAll(buffName, buffType, effectValue, delay, last);
    }
}
