package christmas.domain.event.discountEvent;

public enum D_DayEvent implements Event<Integer, Long, Integer>{

    DISCOUNT_DAY(EventConfig.START_DATE, EventConfig.END_DATE);

    private final int startDate;
    private final int endDate;

    D_DayEvent(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean support(Integer date) {
        return date >= startDate && date <= endDate;
    }

    @Override
    public Long discount(Integer date) {
        return EventConfig.INITAL_DISCOUNT_AMOUNT + EventConfig.DAILY_DISCOUNT_INCREASE * date;
    }
}
