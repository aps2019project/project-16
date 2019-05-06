package models;

import java.util.Date;

public class MatchResult {
    private String opponentName;
    private boolean win ;
    private Date matchTime ;

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return null;
        //phase 2
    }
}
