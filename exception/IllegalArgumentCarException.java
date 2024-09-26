package exception;

public class IllegalArgumentCarException extends RuntimeException {
    public IllegalArgumentCarException() {
        super("Нет такой марки авто в гараже!");
    }
    public IllegalArgumentCarException(String message) {
        super(message);
    }
}
