package christmas.domain.event.discountEvent;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekendEventTest {

    @ParameterizedTest
    @CsvSource({"3","5","7","17","21","25","28","31"})
    @DisplayName("금요일~ 토요일에만 주말 할인이 적용될수있다.")
    void weekendEventCorrectSupportTest(Integer date) {
        Assertions.assertThat(WeekendEvent.WEEKEND_EVENT.support(date)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1","2","8","15","22","23","29","30"})
    @DisplayName("일요일~목요일에는 주말 할인이 적용될수없다.")
    void weekendEventCorrectWrongTest(Integer date) {
        Assertions.assertThat(WeekendEvent.WEEKEND_EVENT.support(date)).isFalse();
    }

    @Test
    @DisplayName("일요일~ 목요일에는 메인메뉴 하나당 2023원이 할인된다.")
    void WeekdayEventDiscountTest() {
        // given
        Order order = new Order();
        order.plus("티본스테이크", 2);
        order.plus("바비큐립",1);
        order.plus("제로콜라",10);
        // when
        Long discountAmount = WeekendEvent.WEEKEND_EVENT.discount(order);
        // then
        Assertions.assertThat(discountAmount).isEqualTo(6069);
    }

}