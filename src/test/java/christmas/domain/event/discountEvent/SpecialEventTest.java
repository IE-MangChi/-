package christmas.domain.event.discountEvent;

import christmas.domain.order.Planner;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialEventTest {

    private Map<String, Integer> menus = new HashMap<>();

    @BeforeEach
    void setUp() {
        menus.put("티본스테이크", 2);
        menus.put("제로콜라", 2);
    }

    @ParameterizedTest
    @CsvSource({"3","10","17","24","25","31"})
    @DisplayName("특별 할인은 이벤트 달력에 별이 있는 날만 적용될수있다.")
    void specialEventCorrectSupportTest(Integer date) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(SpecialEvent.STAR.support(planner)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1","2","4","26","18","26","30"})
    @DisplayName("특별 할인은 이벤트 달력에 별이 없는 날은 적용될수없다.")
    void specialEventWrongSupportTest(Integer date) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(SpecialEvent.STAR.support(planner)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"3","10","17","24","25","31"})
    @DisplayName("특별 할인은 1000원 할인이 적용된다.")
    void specialEventDiscountTest(Integer date) {
        // given
        Planner planner = Planner.of(menus, date);
        // when && then
        Assertions.assertThat(SpecialEvent.STAR.discount(planner)).isEqualTo(1000L);
    }
}