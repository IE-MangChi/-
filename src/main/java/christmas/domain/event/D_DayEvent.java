package christmas.domain.event;

public enum D_DayEvent implements Event<Integer, Integer, Integer>{

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
    public Integer discount(Integer date) {
        return EventConfig.INITAL_DISCOUNT_AMOUNT + EventConfig.DAILY_DISCOUNT_INCREASE * date;
    }
}
