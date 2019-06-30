package newView.GraphicalElements;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import newView.SceneMakers.SceneMaker;

import static newView.SceneMakers.SceneMaker.HEIGHT;
import static newView.SceneMakers.SceneMaker.WIDTH;

public class ZoomablePane extends BorderPane {
    private static final int MIN_PIXELS = 300;
    private static final double RESET_VIEWPOINT_TO_CAMERA_MULTIPLIER = 1;
    private Rectangle2D imageProperties;
    private Rectangle2D viewPort;
    private Scale scale = new Scale(1, 1);
    private Translate translate = new Translate(0, 0);

    public ZoomablePane(Node... children) {
        super();
        this.getChildren().addAll(children);
        getTransforms().addAll(translate, scale);
        this.setMaxHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
        this.initialize();
    }


    public void initialize() {
        imageProperties = new Rectangle2D(0, 0, getMaxWidth(), getMaxHeight());
        viewPort = new Rectangle2D((imageProperties.getWidth() - SceneMaker.WIDTH *
                RESET_VIEWPOINT_TO_CAMERA_MULTIPLIER) / 2,
                (imageProperties.getHeight() - HEIGHT *
                        RESET_VIEWPOINT_TO_CAMERA_MULTIPLIER) / 2,
                SceneMaker.WIDTH * RESET_VIEWPOINT_TO_CAMERA_MULTIPLIER,
                HEIGHT * RESET_VIEWPOINT_TO_CAMERA_MULTIPLIER);

        reset(SceneMaker.WIDTH, HEIGHT);

        ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();

        setOnMousePressed(e -> {
            Point2D mousePress = imageViewToImage(new Point2D(e.getSceneX(), e.getSceneY()));
            mouseDown.set(mousePress);
        });

        setOnMouseDragged(e -> {
            setCursor(Cursor.CLOSED_HAND);
            Point2D dragPoint = imageViewToImage(new Point2D(e.getSceneX(), e.getSceneY()));
            shift(dragPoint.subtract(mouseDown.get()));
//            mouseDown.set(dragPoint);
        });

        setOnMouseReleased(e -> {
            setCursor(SceneMaker.GAME_CURSOR);
        });

        setOnScroll(e -> {
            double delta = -e.getDeltaY();

            double scale =
                    clamp(Math.pow(1.01, delta),

                            // don't scale so we're zoomed in to fewer than MIN_PIXELS in any direction:
                            Math.min(MIN_PIXELS / viewPort.getWidth(), MIN_PIXELS / viewPort.getHeight()),

                            // don't scale so that we're bigger than image dimensions:
                            Math.min(imageProperties.getWidth() / viewPort.getWidth(), imageProperties.getHeight() / viewPort.getHeight())

                    );

            Point2D mouse = imageViewToImage(new Point2D(e.getSceneX(), e.getSceneY()));

            double newWidth = viewPort.getWidth() * scale;
            double newHeight = viewPort.getHeight() * scale;

            // To keep the visual point under the mouse from moving, we need
            // (x - newViewportMinX) / (x - currentViewportMinX) = scale
            // where x is the mouse X coordinate in the image

            // solving this for newViewportMinX gives

            // newViewportMinX = x - (x - currentViewportMinX) * scale

            // we then clamp this value so the image never scrolls out
            // of the imageview:

            double newMinX = clamp(mouse.getX() - (mouse.getX() - viewPort.getMinX()) * scale,
                    0, imageProperties.getWidth() - newWidth);
            double newMinY = clamp(mouse.getY() - (mouse.getY() - viewPort.getMinY()) * scale,
                    0, imageProperties.getHeight() - newHeight);

            this.viewPort = (new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
            fixTranslation();
        });

    }

    // reset to the top left:
    private void reset(double width, double height) {
        viewPort = new Rectangle2D((imageProperties.getWidth() - width * RESET_VIEWPOINT_TO_CAMERA_MULTIPLIER) / 2,
                (imageProperties.getHeight() - height * RESET_VIEWPOINT_TO_CAMERA_MULTIPLIER) / 2,
                width * RESET_VIEWPOINT_TO_CAMERA_MULTIPLIER, height * RESET_VIEWPOINT_TO_CAMERA_MULTIPLIER );
        fixTranslation();
    }


    // shift the viewport of the imageView by the specified delta, clamping so
    // the viewport does not move off the actual image:
    private void shift(Point2D delta) {

        double width = imageProperties.getWidth();
        double height = imageProperties.getHeight();

        double maxX = width - viewPort.getWidth();
        double maxY = height - viewPort.getHeight();

        double minX = clamp(viewPort.getMinX() - delta.getX(), 0, maxX);
        double minY = clamp(viewPort.getMinY() - delta.getY(), 0, maxY);

        this.viewPort = (new Rectangle2D(minX, minY, viewPort.getWidth(), viewPort.getHeight()));
        fixTranslation();
    }

    private double clamp(double value, double min, double max) {

        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    // convert mouse coordinates in the imageView to coordinates in the actual image:
    private Point2D imageViewToImage(Point2D imageViewCoordinates) {
        double xProportion = imageViewCoordinates.getX() / WIDTH;
        double yProportion = imageViewCoordinates.getY() / HEIGHT;

        return new Point2D(
                viewPort.getMinX() + xProportion * viewPort.getWidth(),
                viewPort.getMinY() + yProportion * viewPort.getHeight());
    }

    private void fixTranslation() {
        translate.setX(-viewPort.getMinX());
        translate.setY(-viewPort.getMinY());
        scale.setPivotX(viewPort.getMinX());
        scale.setPivotY(viewPort.getMinY());
        scale.setX(WIDTH / viewPort.getWidth());
        scale.setY(HEIGHT / viewPort.getHeight());
    }
}
