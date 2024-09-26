package exception;

public class NotEnoughMoneyException extends RuntimeException {
    @Override
    public String getMessage() {
        return "У покупателя недостаточно денег, для приобретения авто !";
    }
}
