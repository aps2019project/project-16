package models.net;

public abstract class RequestPacket {
    private String authToken;

    public abstract void run();

    // TODO mostafa call and manage
    public String getAuthToken() {
        return authToken;
    }

    // TODO mostafa call and manage
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
