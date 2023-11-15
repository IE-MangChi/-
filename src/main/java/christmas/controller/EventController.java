package christmas.controller;

import christmas.domain.order.Planner;
import christmas.domain.order.dto.AmountDto;
import christmas.domain.order.dto.MenuQuantityDto;
import christmas.service.EventService;
import christmas.view.output.EventView;

public class EventController {

    private final EventView eventView = new EventView();
    private final EventService eventService;

    public EventController() {
        this.eventService = new EventService();
    }

    public AmountDto event(final Planner planner) {
        Long totalAmount = printTotalAmount(planner);
        if (totalAmount < 10_000) {
            printEventNothing();
            return new AmountDto(totalAmount, 0L);
        }

        MenuQuantityDto gifts = giftMenu(planner);

        eventView.printDiscountResultIntro();
        Long eventDiscount = eventBenefitResult(planner, gifts);
        eventView.printTotalBenefitAmount(eventDiscount);
        return new AmountDto(totalAmount, eventDiscount);
    }

    private Long eventBenefitResult(final Planner planner, final MenuQuantityDto gifts) {
        Long D_DayDiscount = printD_DayEventDiscount(planner);
        Long weekdayDiscount = printWeekdayEventDiscount(planner);
        Long weekendDiscount = printWeekendEventDiscount(planner);
        Long specialDiscount = printSpecialEventDiscount(planner);
        Long giftDiscount = printGiftEventDiscount(gifts);

        return D_DayDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giftDiscount;
    }

    private Long printTotalAmount(final Planner planner) {
        Long totalAmount = eventService.calculateTotalAmount(planner);
        eventView.printTotalAmount(totalAmount);
        return totalAmount;
    }

    private void printEventNothing() {
        eventView.printGiftMenu(null);
        eventView.printDiscountResultIntro();
        eventView.printDiscountEmpty();
        eventView.printTotalBenefitAmount(0L);
    }

    private MenuQuantityDto giftMenu(final Planner planner) {
        MenuQuantityDto gift = eventService.findGiftMenu(planner);
        eventView.printGiftMenu(gift);
        return gift;
    }

    private Long printD_DayEventDiscount(final Planner planner) {
        Long discount = eventService.calculateD_DayEvent(planner);
        eventView.printD_DayDiscount(discount);
        return discount;
    }

    private Long printWeekdayEventDiscount(final Planner planner) {
        Long discount = eventService.calculateWeekdayEvent(planner);
        eventView.printWeekdayDiscount(discount);
        return discount;
    }

    private Long printWeekendEventDiscount(final Planner planner) {
        Long discount = eventService.calculateWeekendEvent(planner);
        eventView.printWeekendDiscount(discount);
        return discount;
    }

    private Long printSpecialEventDiscount(final Planner planner) {
        Long discount = eventService.calculateSpecialEvent(planner);
        eventView.printSpecialDiscount(discount);
        return discount;
    }

    private Long printGiftEventDiscount(final MenuQuantityDto gifts) {
        Long discount = eventService.calculateGiftEvent(gifts);
        eventView.printGiftDiscount(discount);
        return discount;
    }
}
