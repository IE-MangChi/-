package christmas.domain.event.discountEvent;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.Planner;
import christmas.domain.order.dto.MenuQuantityDto;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftEventTest {

    @Test
    @DisplayName("할인 전 총주문금액이 12만원 이상일 경우 증정이벤트 가능하다")
    void giftSupportCorrectTest() {
        // given
        Map<String, Integer> menus = new HashMap<>();
        menus.put("티본스테이크", 5);
        Planner planner = Planner.of(menus, 24);
        // when
        GiftEvent giftEvent = new GiftEvent();
        boolean isSupport = giftEvent.support(planner);
        // then
        Assertions.assertThat(isSupport).isTrue();
    }

    @Test
    @DisplayName("할인 전 총주문금액이 12만원 미만일 경우 증정이벤트 불가능하다")
    void giftSupportWrongTest() {
        // given
        Map<String, Integer> menus = new HashMap<>();
        menus.put("티본스테이크", 1);
        Planner planner = Planner.of(menus, 24);
        // when
        GiftEvent giftEvent = new GiftEvent();
        boolean isSupport = giftEvent.support(planner);
        // then
        Assertions.assertThat(isSupport).isFalse();
    }

    @Test
    @DisplayName("증정 이벤트 할인 적용시 샴페인이 포함되어야한다.")
    void giftDiscountTest() {
        // given
        Map<String, Integer> menus = new HashMap<>();
        menus.put("티본스테이크", 5);
        Planner planner = Planner.of(menus, 24);
        GiftEvent giftEvent = new GiftEvent();
        // when
        MenuQuantityDto discount = giftEvent.discount(planner);
        // then
        Assertions.assertThat(discount.order().containsKey(MenuCategory.CHAMPAGNE)).isTrue();
    }

}