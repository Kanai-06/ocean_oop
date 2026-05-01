public class InvalidValueException extends Exception {
    public InvalidValueException(String m){
        super("Invalid value : " + m);
    }
}
