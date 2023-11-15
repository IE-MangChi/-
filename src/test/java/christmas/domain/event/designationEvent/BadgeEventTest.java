package christmas.domain.event.designationEvent;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeEventTest {

    @ParameterizedTest
    @CsvSource(value = {
            "0:NOTHING", "2000:NOTHING", "4999:NOTHING",
            "5000:STAR", "7000:STAR", "9999:STAR",
            "10000:TREE", "15000:TREE", "19999:TREE",
            "20000:SANTA", "22000:SANTA", "30000:SANTA"}, delimiter = ':')
    @DisplayName("금액에 따른 이벤트 뱃지를 지급해야한다.")
    void BadgeEventTest(long amount, BadgeEvent expected) {
        Assertions.assertThat(BadgeEvent.getBadgeByTotalBenefitAmount(amount)).isEqualTo(expected);
    }

}