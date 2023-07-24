package game;

public class Response {


    private final String message;
    private final boolean flag;


    public String getMessage() {
        return this.message;
    }

    public boolean getFlag() {
        return this.flag;
    }


    public Response(String message, boolean flag) {
        this.message = message;
        this.flag = flag;
    }

}
