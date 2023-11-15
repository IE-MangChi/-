package christmas.domain.event.discountEvent;

import christmas.domain.order.Planner;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class D_DayEventTest {

    Map<String, Integer> menus = new HashMap<>();

    @BeforeEach
    void setUp() {
        menus.put("아이스크림", 2);
        menus.put("제로콜라", 1);
        menus.put("티본스테이크", 3);
    }

    @ParameterizedTest
    @CsvSource(value = {"1","2","5","10","15","20","24","25"})
    @DisplayName("1일 ~ 25일 에만 디데이 할인이 적용될수있다.")
    void d_DayEventSupportCorrectTest(Integer date) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(D_DayEvent.DISCOUNT_DAY.support(planner)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"26","27","28","30","31"})
    @DisplayName("25일 이후에는 디데이 할인이 적용될수없다.")
    void d_DayEventSupportWrongTest(Integer date) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(D_DayEvent.DISCOUNT_DAY.support(planner)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"3:1200", "4:1300", "14:2300", "25:3400"}
            ,delimiter = ':')
    void d_DayEventDiscountTest(Integer date, Long expected) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(D_DayEvent.DISCOUNT_DAY.discount(planner)).isEqualTo(expected);
    }

}