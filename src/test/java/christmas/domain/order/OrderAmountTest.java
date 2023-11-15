package christmas.domain.order;

import christmas.exception.ChristmasException;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderAmountTest {

    @Test
    @DisplayName("주문을 넘길시 총 주문 금액을 반환해야한다.")
    void orderAmountTest() {
        // given
        Map<String, Integer> menus = new HashMap<>();
        menus.put("티본스테이크", 2);
        menus.put("제로콜라", 2);
        // when
        Planner planner = Planner.of(menus, 24);
        // then
        Assertions.assertThat(planner.getTotalAmount()).isEqualTo(110_000+6_000);
    }

    @Test
    @DisplayName("음료만 주문할시 주문은 성사될수없다.")
    void onlyBeverageTest() {
        // given
        Map<String, Integer> menus = Map.of("제로콜라",2,"샴페인",3);
        int date = 23;
        // when && then
        Assertions.assertThatThrownBy(() -> Planner.of(menus, date))
                .isInstanceOf(ChristmasException.class).hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}