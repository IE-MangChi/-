package christmas.domain.event.discountEvent;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class D_DayEventTest {

    @ParameterizedTest
    @CsvSource(value = {"1","2","5","10","15","20","24","25"})
    @DisplayName("1일 ~ 25일 에만 디데이 할인이 적용될수있다.")
    void d_DayEventSupportCorrectTest(Integer date) {
        Assertions.assertThat(D_DayEvent.DISCOUNT_DAY.support(date)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"26","27","28","30","31"})
    @DisplayName("25일 이후에는 디데이 할인이 적용될수없다.")
    void d_DayEventSupportWrongTest(Integer date) {
        Assertions.assertThat(D_DayEvent.DISCOUNT_DAY.support(date)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"3:1200", "4:1300", "14:2300", "25:3400"}
            ,delimiter = ':')
    void d_DayEventDiscountTest(Integer date, Long expected) {
        Assertions.assertThat(D_DayEvent.DISCOUNT_DAY.discount(date)).isEqualTo(expected);
    }

}