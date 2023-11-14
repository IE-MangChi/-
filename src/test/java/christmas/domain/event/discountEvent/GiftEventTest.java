package christmas.domain.event.discountEvent;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.menu.MenuCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftEventTest {

    @Test
    @DisplayName("할인 전 총주문금액이 12만원 이상일 경우 증정이벤트 가능하다")
    void giftSupportCorrectTest() {
        // given
        Long amount = 130_000L;
        // when
        GiftEvent giftEvent = new GiftEvent();
        boolean isSupport = giftEvent.support(amount);
        // then
        Assertions.assertThat(isSupport).isTrue();
    }

    @Test
    @DisplayName("할인 전 총주문금액이 12만원 미만일 경우 증정이벤트 불가능하다")
    void giftSupportWrongTest() {
        // given
        Long amount = 90_000L;
        // when
        GiftEvent giftEvent = new GiftEvent();
        boolean isSupport = giftEvent.support(amount);
        // then
        Assertions.assertThat(isSupport).isFalse();
    }

    @Test
    @DisplayName("증정 이벤트 할인 적용시 샴페인을 증정해야한다.")
    void giftDiscountTest() {
        // given
        GiftEvent giftEvent = new GiftEvent();
        // when
        MenuCategory gift = giftEvent.discount(130_000L);
        // then
        Assertions.assertThat(gift).isEqualTo(MenuCategory.CHAMPAGNE);
    }

}