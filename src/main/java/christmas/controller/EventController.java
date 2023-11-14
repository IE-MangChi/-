package christmas.controller;

import christmas.domain.event.discountEvent.D_DayEvent;
import christmas.domain.event.discountEvent.GiftEvent;
import christmas.domain.event.discountEvent.SpecialEvent;
import christmas.domain.event.discountEvent.WeekdayEvent;
import christmas.domain.menu.MenuCategory;
import christmas.domain.order.Order;
import christmas.domain.order.OrderAmount;
import christmas.domain.order.dto.AmountDto;
import christmas.view.output.EventView;

public class EventController {

    private final EventView eventView = new EventView();

    private final GiftEvent giftEvent;

    public EventController() {
        this.giftEvent = new GiftEvent();
    }

    public AmountDto event(Order order, int date) {
        long totalAmount = printTotalAmount(order);

        if (totalAmount < 10_000) {
            printEventNothing();
            return new AmountDto(totalAmount, 0L);
        }
        MenuCategory gift = giftMenu(totalAmount);

        eventView.printDiscountResultIntro();
        Long discount = applyEvent(order, date, gift);

        eventView.printTotalBenefitAmount(discount);
        return new AmountDto(totalAmount, discount);
    }

    private long printTotalAmount(Order order) {
        long totalAmount = OrderAmount.of(order).getTotalAmount();
        eventView.printTotalAmount(totalAmount);
        return totalAmount;
    }

    private Long applyEvent(Order order, int date, MenuCategory gift) {
        Long d_DayDiscount = d_DayEvent(date);
        Long weekdayDiscount = weekdayEvent(order, date);
        Long weekendDiscount = weekendEvent(order, date);
        Long specialDiscount = specialEvent(date);
        Long giftDiscount = giftEvent(gift);

        return d_DayDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giftDiscount;
    }

    private void printEventNothing() {
        eventView.printGiftMenu(null);
        eventView.printDiscountResultIntro();
        eventView.printDiscountEmpty();
        eventView.printTotalBenefitAmount(0L);
    }

    private MenuCategory giftMenu(Long totalAmount) {
        if (giftEvent.support(totalAmount)) {
            MenuCategory gift = giftEvent.discount(totalAmount);
            eventView.printGiftMenu(gift);
            return gift;
        }
        eventView.printGiftMenu(null);
        return null;
    }

    private Long d_DayEvent(int date) {
        if (D_DayEvent.DISCOUNT_DAY.support(date)) {
            Long discountAmount = D_DayEvent.DISCOUNT_DAY.discount(date);
            eventView.printD_DayDiscount(discountAmount);
            return discountAmount;
        }
        eventView.printD_DayDiscount(0L);
        return 0L;
    }

    private Long weekdayEvent(Order order, int date) {
        if (WeekdayEvent.WEEKDAY_EVENT.support(date)) {
            Long discountAmount = WeekdayEvent.WEEKDAY_EVENT.discount(order);
            eventView.printWeekdayDiscount(discountAmount);
            return discountAmount;
        }
        eventView.printWeekdayDiscount(0L);
        return 0L;
    }

    private Long weekendEvent(Order order, int date) {
        if (WeekdayEvent.WEEKDAY_EVENT.support(date)) {
            Long discountAmount = WeekdayEvent.WEEKDAY_EVENT.discount(order);
            eventView.printWeekendDiscount(discountAmount);
            return discountAmount;
        }
        eventView.printWeekendDiscount(0L);
        return 0L;
    }

    private Long specialEvent(int date) {
        if (SpecialEvent.STAR.support(date)) {
            Long specialDiscount = SpecialEvent.STAR.discount(date);
            eventView.printSpecialDiscount(specialDiscount);
            return specialDiscount;
        }
        eventView.printSpecialDiscount(0L);
        return 0L;
    }

    private Long giftEvent(MenuCategory gift) {
        if (gift == null) {
            eventView.printGiftDiscount(0L);
            return 0L;
        }
        long giftDiscount = gift.getPrice();
        eventView.printGiftDiscount(giftDiscount);
        return giftDiscount;
    }
}
