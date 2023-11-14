package christmas.domain.event.discountEvent;

import java.util.Arrays;

public enum SpecialEvent implements Event<Integer, Long, Integer>{

    STAR(EventConfig.SPECIAL_DISCOUNT_DAY);

    private int[] dayWithStar;

    SpecialEvent(int... dayWithStar) {
        this.dayWithStar = dayWithStar;
    }



    @Override
    public boolean support(Integer date) {
        return Arrays.stream(dayWithStar)
                .anyMatch(dayWithStar -> dayWithStar == date);
    }

    @Override
    public Long discount(Integer date) {
        return EventConfig.SPECIAL_DISCOUNT_AMOUNT;
    }
}
