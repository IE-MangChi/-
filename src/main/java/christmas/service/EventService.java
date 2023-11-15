package christmas.service;

import christmas.domain.event.discountEvent.D_DayEvent;
import christmas.domain.event.discountEvent.GiftEvent;
import christmas.domain.event.discountEvent.SpecialEvent;
import christmas.domain.event.discountEvent.WeekdayEvent;
import christmas.domain.event.discountEvent.WeekendEvent;
import christmas.domain.menu.MenuCategory;
import christmas.domain.order.Order;
import christmas.domain.order.OrderAmount;
import christmas.domain.order.dto.MenuQuantityDto;
import java.util.Map.Entry;

public class EventService {
    private final Long EMPTY_DISCOUNT = 0L;

    private final GiftEvent giftEvent;

    public EventService() {
        this.giftEvent = new GiftEvent();
    }

    public Long calculateTotalAmount(Order order) {
        return OrderAmount.of(order).getTotalAmount();
    }

    public MenuQuantityDto findGiftMenu(Long totalAmount) {
        if (giftEvent.support(totalAmount)) {
            return giftEvent.discount(totalAmount);
        }
        return null;
    }

    public Long calculateD_DayEvent(int date) {
        if (D_DayEvent.DISCOUNT_DAY.support(date)) {
            return D_DayEvent.DISCOUNT_DAY.discount(date);
        }
        return EMPTY_DISCOUNT;
    }

    public Long calculateWeekdayEvent(Order order, int date) {
        if (WeekdayEvent.WEEKDAY_EVENT.support(date)) {
            return WeekdayEvent.WEEKDAY_EVENT.discount(order);
        }
        return EMPTY_DISCOUNT;
    }

    public Long calculateWeekendEvent(Order order, int date) {
        if (WeekendEvent.WEEKEND_EVENT.support(date)) {
            return WeekendEvent.WEEKEND_EVENT.discount(order);
        }
        return EMPTY_DISCOUNT;
    }

    public Long calculateSpecialEvent(int date) {
        if (SpecialEvent.STAR.support(date)) {
            return SpecialEvent.STAR.discount(date);
        }
        return EMPTY_DISCOUNT;
    }

    public Long calculateGiftEvent(MenuQuantityDto gifts) {
        if (hasGift(gifts)) {
            return EMPTY_DISCOUNT;
        }

        Long sum = 0L;
        for (Entry<MenuCategory, Integer> giftStore : gifts.order().entrySet()) {
            sum += giftStore.getKey().getPrice() * giftStore.getValue();
        }
        return sum;
    }

    private static boolean hasGift(MenuQuantityDto gifts) {
        return gifts == null;
    }
}
