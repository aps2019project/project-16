package newView;

import javafx.application.Platform;
import javafx.scene.media.AudioClip;

import java.io.File;

public class SoundPlayer {
    public static void playByPath(String relativePath) {
        AudioClip sound;
        sound = new AudioClip(new File(relativePath).toURI().toString());
        sound.play();
    }

    public static void playCardNameSound(String name, Type type) {
        try {
            new Thread(() -> {
                AudioClip sound;
                File file = new File("src/newView/resources/cards/" + type.getName() + "/" + name + ".m4a");
                try {
                    if (!file.exists())
                        throw new Exception();
                } catch (Exception e) {
                    file = new File("src/new/view/resources/cards/custom/" + type.getName() + "/1.m4a");
                }
                sound = new AudioClip(file.toURI().toString());
                Platform.runLater(sound::play);
            }).start();
        } catch (Exception ignored)  {
        }
        //  type mitoone "spell" ya "minion" ya "hero" bashe
    }

    public static void playCardAttackSound(String name, Type type) {
        AudioClip sound;
        File file = new File("src/newView/resources/cards/" + type.getName() + "/" + name + "_attack.m4a");
        try {
            if (!file.exists())
                throw new Exception();
        } catch (Exception e) {
            file = new File("src/new/view/resources/cards/custom/" + type.getName() + "/1_attack.m4a");
        }
        sound = new AudioClip(file.toURI().toString());
        sound.play();
    }

    public static void playCardHitSound(String name, Type type) {
        AudioClip sound;
        File file = new File("src/newView/resources/cards/" + type.getName() + "/" + name + "_hit.m4a");
        try {
            if (!file.exists())
                throw new Exception();
        } catch (Exception e) {
            file = new File("src/new/view/resources/cards/custom/" + type.getName() + "/1_hit.m4a");
        }
        sound = new AudioClip(file.toURI().toString());
        sound.play();
    }

    public static void playCradDeathSound(String name, Type type) {
        AudioClip sound;
        File file = new File("src/newView/resources/cards/" + type.getName() + "/" + name + "_death.m4a");
        try {
            if (!file.exists())
                throw new Exception();
        } catch (Exception e) {
            file = new File("src/new/view/resources/cards/custom/" + type.getName() + "/1_death.m4a");
        }
        sound = new AudioClip(file.toURI().toString());
        sound.play();
    }

}
