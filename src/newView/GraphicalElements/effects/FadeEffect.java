package newView.GraphicalElements.effects;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FadeEffect {
    private Duration duration;
    private ImageView toChange;

    public FadeEffect(double time, ImageView toChange, Image purpose) {
        duration = Duration.millis(time);
        this.toChange = toChange;

        FadeTransition fade = fadePlay(true);

        fade.setOnFinished(event -> {
            toChange.setImage(purpose);
            fadePlay(false);
        });
    }

    private FadeTransition fadePlay(boolean fadeOut) {
        double fromValue, endValue;
        if (fadeOut) {
            fromValue = 10;
            endValue = 0;
        } else {
            fromValue = 0;
            endValue = 10;
        }
        FadeTransition fade = new FadeTransition();
        fade.setDuration(duration);
        fade.setFromValue(fromValue);
        fade.setToValue(endValue);
        fade.setCycleCount(1);
        fade.setAutoReverse(false);
        fade.setNode(toChange);
        fade.play();
        return fade;
    }
}
