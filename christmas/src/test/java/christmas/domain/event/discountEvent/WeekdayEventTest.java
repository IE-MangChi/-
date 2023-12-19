package christmas.domain.event.discountEvent;

import christmas.domain.order.Planner;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayEventTest {

    Map<String, Integer> menus = new HashMap<>();

    @BeforeEach
    void setUp() {
        menus.put("아이스크림", 2);
        menus.put("제로콜라", 1);
        menus.put("티본스테이크", 3);
    }

    @ParameterizedTest
    @CsvSource({"3","5","7","17","21","25","28","31"})
    @DisplayName("일요일~ 목요일에만 평일 할인이 적용될수있다.")
    void weekdayEventCorrectSupportTest(Integer date) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(WeekdayEvent.WEEKDAY_EVENT.support(planner)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1","2","8","15","22","23","29","30"})
    @DisplayName("금요일~토요일에는 평일 할인이 적용될수없다.")
    void weekdayEventCorrectWrongTest(Integer date) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(WeekdayEvent.WEEKDAY_EVENT.support(planner)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"1","2","8","15","22","23","29","30"})
    @DisplayName("일요일~ 목요일에는 디저트 하나당 2023원이 할인된다.")
    void WeekdayEventDiscountTest(Integer date) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(WeekdayEvent.WEEKDAY_EVENT.support(planner)).isFalse();
        Assertions.assertThat(WeekdayEvent.WEEKDAY_EVENT.discount(planner)).isEqualTo(4046);
    }

}