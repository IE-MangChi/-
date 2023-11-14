package christmas.domain.order;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

class OrderAmountTest {

    @Test
    @DisplayName("주문을 넘길시 총 주문 금액을 반환해야한다.")
    void orderAmountTest() {
        // given
        Order order = new Order();
        order.plus("제로콜라",3);
        order.plus("타파스",4);
        // when
        OrderAmount orderAmount = OrderAmount.of(order);
        // then
        Assertions.assertThat(orderAmount.getTotalAmount()).isEqualTo(9000+22000);
    }

}