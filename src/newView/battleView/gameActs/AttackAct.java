package newView.battleView.gameActs;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import models.Cell;
import models.card.Card;
import models.card.Hero;
import newView.AnimationMaker;
import newView.battleView.GameGraphicData;
import newView.GraphicalElements.battle.Tile;
import newView.SoundPlayer;
import newView.Type;

import static newView.battleView.GameGraphicListener.GAME_ACT_TIME;

public class AttackAct extends GameAct {
    private String attackerName;
    private Type attackerType;
    private String defenderName;
    private Type defenderType;
    private int attackerRow;
    private int attackerColumn;
    private int defenderRow;
    private int defenderColumn;

    public AttackAct(Cell attackCell, Cell defendCell, Card attacker, Card defender) {
        attackerRow = attackCell.getRow();
        attackerColumn = attackCell.getColumn();
        defenderRow = defendCell.getRow();
        defenderColumn = defendCell.getColumn();

        attackerName = attacker.getName();
        attackerType = attacker instanceof Hero ? Type.HERO : Type.MINION;

        defenderName = defender.getName();
        defenderType = defender instanceof Hero ? Type.HERO : Type.MINION;
    }

    @Override
    public void showAction() {
        try {
            makeAnimation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeAnimation() throws Exception {
        ImageView attackView = AnimationMaker.getAttackAnimation(attackerName, attackerType.getName());
        ImageView breathingView1 = AnimationMaker.getBreathingAnimation(attackerName, attackerType.getName());

        ImageView hitView = AnimationMaker.getHitAnimation(defenderName, defenderType.getName());
        ImageView breathingView2 = AnimationMaker.getBreathingAnimation(defenderName, defenderType.getName());

        SoundPlayer.playByPath("src/newView/resources/sounds/hit.wav");

        Tile source = GameGraphicData.getTilesPane().getTile(attackerRow, attackerColumn);
        source.enableColorAnimation(Color.rgb(255, 255, 0));
        Tile destination = GameGraphicData.getTilesPane().getTile(defenderRow, defenderColumn);
        destination.enableColorAnimation(Color.rgb(255, 0, 0));

        source.setImageView(attackView, source.getUnit());
        destination.setImageView(hitView, destination.getUnit());
        ImageView unitView1 = source.getImageView();

        KeyValue xKeyValue = new KeyValue(unitView1.scaleXProperty(), unitView1.getScaleX() * 1.5);
        KeyValue yKeyValue = new KeyValue(unitView1.scaleYProperty(), unitView1.getScaleY() * 1.5);
        Timeline timeline = AnimationMaker.makeTimeline(
                Duration.millis(GAME_ACT_TIME * 0.45)
                , true, 2
                , xKeyValue, yKeyValue);
        timeline.play();

        timeline.setOnFinished(event -> {
            source.setImageView(breathingView1, source.getUnit());
            destination.setImageView(breathingView2, destination.getUnit());
        });
    }
}
