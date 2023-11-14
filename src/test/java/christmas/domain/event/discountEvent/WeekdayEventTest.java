package christmas.domain.event.discountEvent;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayEventTest {

    @ParameterizedTest
    @CsvSource({"3","5","7","17","21","25","28","31"})
    @DisplayName("일요일~ 목요일에만 평일 할인이 적용될수있다.")
    void weekdayEventCorrectSupportTest(Integer date) {
        Assertions.assertThat(WeekdayEvent.WEEKDAY_EVENT.support(date)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1","2","8","15","22","23","29","30"})
    @DisplayName("금요일~토요일에는 평일 할인이 적용될수없다.")
    void weekdayEventCorrectWrongTest(Integer date) {
        Assertions.assertThat(WeekdayEvent.WEEKDAY_EVENT.support(date)).isFalse();
    }

    @Test
    @DisplayName("일요일~ 목요일에는 디저트 하나당 2023원이 할인된다.")
    void WeekdayEventDiscountTest() {
        // given
        Order order = new Order();
        order.plus("아이스크림", 2);
        order.plus("제로콜라",10);
        // when
        Long discountAmount = WeekdayEvent.WEEKDAY_EVENT.discount(order);
        System.out.println(discountAmount);
        // then
        Assertions.assertThat(discountAmount).isEqualTo(4046);
    }

}