package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ChristmasException;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest extends IOTest{

    private InputView inputView = new InputView();

    @AfterEach
    void after() {
        Console.close();
    }

    @Test
    @DisplayName("날짜는 1에서 31이하의 숫자만 가능하다")
    void inputDateMinus() {
        // given
        systemIn("-1");
        // when && then
        Assertions.assertThatThrownBy(() -> inputView.readDate())
                .isInstanceOf(ChristmasException.class);
    }

    @Test
    @DisplayName("날짜는 1에서 31이하의 숫자만 가능하다")
    void inputDateOver() {
        // given
        systemIn("32");
        // when && then
        Assertions.assertThatThrownBy(() -> inputView.readDate())
                .isInstanceOf(ChristmasException.class);
    }

    @Test
    @DisplayName("메뉴 주문시 입력 형식에 맞지 않으면 에러를 발생시킨다.")
    void readMenuFormat() {
        // given
        systemIn("짬뽕3");
        // when & then
        Assertions.assertThatThrownBy(() -> inputView.readMenu())
                .isInstanceOf(ChristmasException.class);
    }

    @Test
    @DisplayName("메뉴 주문시 중복이 있으면 에러를 발생시킨다.")
    void readMenuDuplicate() {
        // given
        systemIn("스파게티-5,마라탕-2,마라탕-3");
        // when & then
        Assertions.assertThatThrownBy(() -> inputView.readMenu())
                .isInstanceOf(ChristmasException.class);
    }
}