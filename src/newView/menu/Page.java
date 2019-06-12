package newView.menu;

import javafx.stage.Stage;

public abstract class Page {
    private Page parent;
    private Stage stage;

    public Page(Page parent, Stage stage) {
        this.parent = parent;
        this.stage = stage;
    }

    public abstract void start();

    protected void back() {
        parent.start();
    }

    protected Stage getStage() {
        return stage;
    }
}
