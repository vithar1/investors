package cs.vsu.investor.service.exception;

public class GameNotFoundException extends Exception{
    public GameNotFoundException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
