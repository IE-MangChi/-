package christmas.domain.event.discountEvent;

import christmas.domain.order.Planner;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekendEventTest {

    private Map<String, Integer> menus = new HashMap<>();

    @BeforeEach
    void setUp() {
        menus.put("아이스크림", 3);
        menus.put("티본스테이크", 2);
        menus.put("제로콜라", 2);
    }

    @ParameterizedTest
    @CsvSource({"3","5","7","17","21","25","28","31"})
    @DisplayName("일요일~목요일에는 주말 할인이 적용될수없다.")
    void weekendEventCorrectSupportTest(Integer date) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(WeekendEvent.WEEKEND_EVENT.support(planner)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"1","2","8","15","22","23","29","30"})
    @DisplayName("금요일~ 토요일에만 주말 할인이 적용될수있다.")
    void weekendEventCorrectWrongTest(Integer date) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(WeekendEvent.WEEKEND_EVENT.support(planner)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"3","5","7","17","21","25","28","31"})
    @DisplayName("일요일~ 목요일에는 메인메뉴 하나당 2023원이 할인된다.")
    void WeekdayEventDiscountTest(Integer date) {
        Planner planner = Planner.of(menus, date);
        // when && then
        Long discountAmount = WeekendEvent.WEEKEND_EVENT.discount(planner);
        // then
        Assertions.assertThat(discountAmount).isEqualTo(4046);
    }

}