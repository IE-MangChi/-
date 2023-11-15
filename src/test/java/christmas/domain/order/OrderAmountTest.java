package christmas.domain.order;

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

}