package christmas.controller;

import christmas.domain.event.discountEvent.GiftEvent;
import christmas.domain.menu.MenuCategory;
import christmas.domain.order.Order;
import christmas.domain.order.dto.AmountDto;
import christmas.service.EventService;
import christmas.view.output.EventView;

public class EventController {

    private final EventView eventView = new EventView();
    private final EventService eventService;

    public EventController() {
        this.eventService = new EventService();
    }

    public AmountDto event(Order order, int date) {
        Long totalAmount = printTotalAmount(order);

        if (totalAmount < 10_000) {
            printEventNothing();
            return new AmountDto(totalAmount, 0L);
        }
        MenuCategory gift = giftMenu(totalAmount);

        eventView.printDiscountResultIntro();
        Long eventDiscount = eventBenefitResult(order, date, gift);
        eventView.printTotalBenefitAmount(eventDiscount);

        return new AmountDto(totalAmount, eventDiscount);
    }

    private Long eventBenefitResult(Order order, int date, MenuCategory gift) {
        Long D_DayDiscount = printD_DayEventDiscount(date);
        Long weekdayDiscount = printWeekdayEventDiscount(order, date);
        Long weekendDiscount = printWeekendEventDiscount(order, date);
        Long specialDiscount = printSpecialEventDiscount(date);
        Long giftDiscount = printGiftEventDiscount(gift);

        return D_DayDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giftDiscount;
    }

    private Long printTotalAmount(Order order) {
        Long totalAmount = eventService.calculateTotalAmount(order);
        eventView.printTotalAmount(totalAmount);
        return totalAmount;
    }

    private void printEventNothing() {
        eventView.printGiftMenu(null);
        eventView.printDiscountResultIntro();
        eventView.printDiscountEmpty();
        eventView.printTotalBenefitAmount(0L);
    }

    private MenuCategory giftMenu(Long totalAmount) {
        MenuCategory gift = eventService.findGiftMenu(totalAmount);
        eventView.printGiftMenu(gift);
        return gift;
    }

    private Long printD_DayEventDiscount(int date) {
        Long discount = eventService.calculateD_DayEvent(date);
        eventView.printD_DayDiscount(discount);
        return discount;
    }

    private Long printWeekdayEventDiscount(Order order, int date) {
        Long discount = eventService.calculateWeekdayEvent(order, date);
        eventView.printWeekdayDiscount(discount);
        return discount;
    }

    private Long printWeekendEventDiscount(Order order, int date) {
        Long discount = eventService.calculateWeekendEvent(order, date);
        eventView.printWeekendDiscount(discount);
        return discount;
    }

    private Long printSpecialEventDiscount(int date) {
        Long discount = eventService.calculateSpecialEvent(date);
        eventView.printSpecialDiscount(discount);
        return discount;
    }

    private Long printGiftEventDiscount(MenuCategory gift) {
        Long discount = eventService.calculateGiftEvent(gift);
        eventView.printGiftDiscount(discount);
        return discount;
    }
}
