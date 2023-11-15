package christmas.service;

import christmas.domain.event.discountEvent.D_DayEvent;
import christmas.domain.event.discountEvent.GiftEvent;
import christmas.domain.event.discountEvent.SpecialEvent;
import christmas.domain.event.discountEvent.WeekdayEvent;
import christmas.domain.event.discountEvent.WeekendEvent;
import christmas.domain.menu.MenuCategory;
import christmas.domain.order.Order;

public class EventService {
    private final Long EMPTY_DISCOUNT = 0L;

    private final GiftEvent giftEvent;

    public EventService() {
        this.giftEvent = new GiftEvent();
    }

    public MenuCategory giftMenu(Long totalAmount) {
        if (giftEvent.support(totalAmount)) {
            return giftEvent.discount(totalAmount);
        }
        return null;
    }

    public Long d_DayEvent(int date) {
        if (D_DayEvent.DISCOUNT_DAY.support(date)) {
            return D_DayEvent.DISCOUNT_DAY.discount(date);
        }
        return EMPTY_DISCOUNT;
    }

    public Long weekdayEvent(Order order, int date) {
        if (WeekdayEvent.WEEKDAY_EVENT.support(date)) {
            return WeekdayEvent.WEEKDAY_EVENT.discount(order);
        }
        return EMPTY_DISCOUNT;
    }

    public Long weekendEvent(Order order, int date) {
        if (WeekendEvent.WEEKEND_EVENT.support(date)) {
            return WeekendEvent.WEEKEND_EVENT.discount(order);
        }
        return EMPTY_DISCOUNT;
    }

    public Long specialEvent(int date) {
        if (SpecialEvent.STAR.support(date)) {
            return SpecialEvent.STAR.discount(date);
        }
        return EMPTY_DISCOUNT;
    }

    public Long giftEvent(MenuCategory gift) {
        if (hasGift(gift)) {
            return EMPTY_DISCOUNT;
        }
        return gift.getPrice();
    }

    private static boolean hasGift(MenuCategory gift) {
        return gift == null;
    }
}
