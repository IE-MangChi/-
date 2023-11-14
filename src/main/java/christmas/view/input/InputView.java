package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {

    private final Pattern datePattern = Pattern.compile("^[1-9]|[1-2][0-9]|[3][0-1]$");
    private final Pattern orderPattern = Pattern.compile("^\\D-\\d");

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        validateDate(input);
        return Integer.parseInt(input);
    }

    public Map<String, Integer> readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(order -> order.split("-"))
                    .collect(Collectors.toMap(order -> order[0], order -> Integer.parseInt(order[1])));
        } catch (NumberFormatException | IllegalStateException | ArrayIndexOutOfBoundsException exception) {
            throw ChristmasException.of(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateDate(String input) {
        if (!datePattern.matcher(input).matches()) {
            throw ChristmasException.of(ErrorMessage.INVALID_DATE);
        }
    }
}
