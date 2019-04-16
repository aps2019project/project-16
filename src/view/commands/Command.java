package view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    private Pattern pattern;
    private Matcher matcher;

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(String string) {
        this.matcher = pattern.matcher(string);
    }

    public abstract void doIt();
}