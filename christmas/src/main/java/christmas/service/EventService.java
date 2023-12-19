package christmas.service;

import christmas.domain.event.discountEvent.D_DayEvent;
import christmas.domain.event.discountEvent.GiftEvent;
import christmas.domain.event.discountEvent.SpecialEvent;
import christmas.domain.event.discountEvent.WeekdayEvent;
import christmas.domain.event.discountEvent.WeekendEvent;
import christmas.domain.menu.MenuCategory;
import christmas.domain.order.Order;
import christmas.domain.order.OrderAmount;
import christmas.domain.order.Planner;
import christmas.domain.order.dto.MenuQuantityDto;
import java.util.Map.Entry;

public class EventService {
    private final Long EMPTY_DISCOUNT = 0L;

    private final GiftEvent giftEvent;

    public EventService() {
        this.giftEvent = new GiftEvent();
    }

    public Long calculateTotalAmount(final Planner planner) {
        return planner.getTotalAmount();
    }

    public MenuQuantityDto findGiftMenu(final Planner planner) {
        if (giftEvent.support(planner)) {
            return giftEvent.discount(planner);
        }
        return null;
    }

    public Long calculateD_DayEvent(final Planner planner) {
        if (D_DayEvent.DISCOUNT_DAY.support(planner)) {
            return D_DayEvent.DISCOUNT_DAY.discount(planner);
        }
        return EMPTY_DISCOUNT;
    }

    public Long calculateWeekdayEvent(final Planner planner) {
        if (WeekdayEvent.WEEKDAY_EVENT.support(planner)) {
            return WeekdayEvent.WEEKDAY_EVENT.discount(planner);
        }
        return EMPTY_DISCOUNT;
    }

    public Long calculateWeekendEvent(final Planner planner) {
        if (WeekendEvent.WEEKEND_EVENT.support(planner)) {
            return WeekendEvent.WEEKEND_EVENT.discount(planner);
        }
        return EMPTY_DISCOUNT;
    }

    public Long calculateSpecialEvent(final Planner planner) {
        if (SpecialEvent.STAR.support(planner)) {
            return SpecialEvent.STAR.discount(planner);
        }
        return EMPTY_DISCOUNT;
    }

    public Long calculateGiftEvent(final MenuQuantityDto gifts) {
        if (hasGift(gifts)) {
            return EMPTY_DISCOUNT;
        }

        Long sum = 0L;
        for (Entry<MenuCategory, Integer> giftStore : gifts.order().entrySet()) {
            sum += giftStore.getKey().getPrice() * giftStore.getValue();
        }
        return sum;
    }

    private static boolean hasGift(final MenuQuantityDto gifts) {
        return gifts == null;
    }
}
