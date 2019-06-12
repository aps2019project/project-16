package newView.menu;

import javafx.stage.Stage;

public abstract class Page {
        private Page parent;
        protected Stage stage;

        public abstract void start();

        protected void back() {
            parent.start();
        }
}
