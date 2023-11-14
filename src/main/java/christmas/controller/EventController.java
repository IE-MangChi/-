package christmas.controller;

import christmas.domain.event.discountEvent.D_DayEvent;
import christmas.domain.event.discountEvent.GiftEvent;
import christmas.domain.event.discountEvent.SpecialEvent;
import christmas.domain.event.discountEvent.WeekdayEvent;
import christmas.domain.menu.MenuCategory;
import christmas.domain.order.Order;
import christmas.domain.order.OrderAmount;
import christmas.domain.order.dto.AmountDto;
import christmas.view.OutputView;

public class EventController {

    private final OutputView outputView = new OutputView();

    private final GiftEvent giftEvent;

    public EventController() {
        this.giftEvent = new GiftEvent();
    }

    public AmountDto event(Order order, int date) {
        long totalAmount = OrderAmount.of(order).getTotalAmount();
        outputView.printTotalAmount(totalAmount);

        if (totalAmount < 10_000) {
            outputView.printGiftMenu(null);
            outputView.printDiscountResultIntro();
            outputView.printDiscountEmpty();
            outputView.printTotalBenefitAmount(0L);
            return new AmountDto(totalAmount, 0L);
        }

        MenuCategory gift = giftMenu(totalAmount);

        outputView.printDiscountResultIntro();
        Long d_DayDiscount = d_DayEvent(date);
        Long weekdayDiscount = weekdayEvent(order, date);
        Long weekendDiscount = weekendEvent(order, date);
        Long specialDiscount = specialEvent(date);
        Long giftDiscount = giftEvent(gift);

        Long discount = d_DayDiscount+weekdayDiscount+weekendDiscount+specialDiscount+giftDiscount;
        outputView.printTotalBenefitAmount(discount);
        outputView.printAfterDiscountAmount(totalAmount - discount);
        return new AmountDto(totalAmount, discount);
    }

    private MenuCategory giftMenu(Long totalAmount) {
        if (giftEvent.support(totalAmount)) {
            MenuCategory gift = giftEvent.discount(totalAmount);
            outputView.printGiftMenu(gift);
            return gift;
        }
        outputView.printGiftMenu(null);
        return null;
    }

    private Long d_DayEvent(int date) {
        if (D_DayEvent.DISCOUNT_DAY.support(date)) {
            Long discountAmount = D_DayEvent.DISCOUNT_DAY.discount(date);
            outputView.printD_DayDiscount(discountAmount);
            return discountAmount;
        }
        return 0L;
    }

    private Long weekdayEvent(Order order, int date) {
        if (WeekdayEvent.WEEKDAY_EVENT.support(date)) {
            Long discountAmount = WeekdayEvent.WEEKDAY_EVENT.discount(order);
            outputView.printWeekdayDiscount(discountAmount);
            return discountAmount;
        }
        return 0L;
    }

    private Long weekendEvent(Order order, int date) {
        if (WeekdayEvent.WEEKDAY_EVENT.support(date)) {
            Long discountAmount = WeekdayEvent.WEEKDAY_EVENT.discount(order);
            outputView.printWeekendDiscount(discountAmount);
            return discountAmount;
        }
        return 0L;
    }

    private Long specialEvent(int date) {
        if (SpecialEvent.STAR.support(date)) {
            Long specialDiscount = SpecialEvent.STAR.discount(date);
            outputView.printSpecialDiscount(specialDiscount);
            return specialDiscount;
        }
        return 0L;
    }

    private Long giftEvent(MenuCategory gift) {
        if (gift == null) {
            return null;
        }
        long giftDiscount = gift.getPrice();
        outputView.printGiftDiscount(giftDiscount);
        return giftDiscount;
    }
}
