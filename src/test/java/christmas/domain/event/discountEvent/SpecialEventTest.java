package christmas.domain.event.discountEvent;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialEventTest {

    @ParameterizedTest
    @CsvSource({"3","10","17","24","25","31"})
    @DisplayName("특별 할인은 이벤트 달력에 별이 있는 날만 적용될수있다.")
    void specialEventCorrectSupportTest(Integer date) {
        Assertions.assertThat(SpecialEvent.STAR.support(date)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1","2","4","26","18","26","30"})
    @DisplayName("특별 할인은 이벤트 달력에 별이 없는 날만 적용될수없다.")
    void specialEventWrongSupportTest(Integer date) {
        Assertions.assertThat(SpecialEvent.STAR.support(date)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"3","10","17","24","25","31"})
    @DisplayName("특별 할인은 1000원 할인이 적용된다.")
    void specialEventDiscountTest(Integer date) {
        Assertions.assertThat(SpecialEvent.STAR.discount(date)).isEqualTo(1000L);
    }
}