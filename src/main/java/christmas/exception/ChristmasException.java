package christmas.exception;

public class ChristmasException extends IllegalArgumentException {
    private ChristmasException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public static ChristmasException of(ErrorMessage errorMessage) {
        System.out.println(errorMessage.getMessage());
        return new ChristmasException(errorMessage);
    }

}
