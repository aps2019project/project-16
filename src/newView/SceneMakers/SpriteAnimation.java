package newView.SceneMakers;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class SpriteAnimation extends Transition {
    private final ImageView imageView;
    private ArrayList<PictureCoordination> pictureCoordinations = new ArrayList<>();
    private int count;

    private int lastIndex;

    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            ArrayList<PictureCoordination> pictureCoordinations) {
        this.pictureCoordinations = pictureCoordinations;
        this.imageView = imageView;
        count = pictureCoordinations.size();
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            PictureCoordination coordinate = pictureCoordinations.get(index);
            imageView.setViewport(new Rectangle2D(coordinate.getX(), coordinate.getY(), coordinate.getWidth(), coordinate.getHeight()));
            lastIndex = index;
        }
    }
}



