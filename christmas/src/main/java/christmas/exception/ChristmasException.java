package christmas.exception;

public class ChristmasException extends IllegalArgumentException {
    private ChristmasException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public static ChristmasException of(final ErrorMessage errorMessage) {
        return new ChristmasException(errorMessage);
    }

}
