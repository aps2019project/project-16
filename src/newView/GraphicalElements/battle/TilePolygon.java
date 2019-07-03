package newView.GraphicalElements.battle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import static newView.SceneMakers.SceneMaker.SCALE;

public class TilePolygon extends Polygon {
    private double Uly;
    private double Ury;
    private double Lly;
    private double Lry;

    private double Ulx;
    private double Urx;
    private double Llx;
    private double Lrx;

    public void setUly(double uly) {
        Uly = uly;
    }

    public void setUry(double ury) {
        Ury = ury;
    }

    public void setLly(double lly) {
        Lly = lly;
    }

    public void setLry(double lry) {
        Lry = lry;
    }

    public void setUlx(double ulx) {
        Ulx = ulx;
    }

    public void setUrx(double urx) {
        Urx = urx;
    }

    public void setLlx(double llx) {
        Llx = llx;
    }

    public void setLrx(double lrx) {
        Lrx = lrx;
    }

    public TilePolygon() {
        this.setFill(Color.WHITE);
        this.setStroke(Color.LIGHTBLUE);
        this.setStrokeWidth(3 * SCALE);
    }

    public void setPoints() {
        this.getPoints().addAll(
                Ulx, Uly
                , Urx, Ury
                , Lrx, Lry
                , Llx, Lly);
    }
}
