package christmas.domain.event.discountEvent;

import christmas.domain.order.Planner;

public enum D_DayEvent implements Event<Planner, Long> {

    DISCOUNT_DAY(EventConfig.START_DATE, EventConfig.END_DATE);

    private final int startDate;
    private final int endDate;

    D_DayEvent(final int startDate, final int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean support(final Planner planner) {
        int date = planner.getDate();
        return date >= startDate && date <= endDate;
    }

    @Override
    public Long discount(final Planner planner) {
        return EventConfig.INITAL_DISCOUNT_AMOUNT + EventConfig.DAILY_DISCOUNT_INCREASE * (planner.getDate() - 1);
    }
}
