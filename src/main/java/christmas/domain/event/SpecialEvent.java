package christmas.domain.event;

import java.util.Arrays;

public enum SpecialEvent implements Event<Integer, Integer, Integer>{

    STAR(EventConfig.SPECIAL_DISCOUNT_DAY);

    private int[] dayWithStar;

    SpecialEvent(int... dayWithStar) {
        this.dayWithStar = dayWithStar;
    }



    @Override
    public boolean support(Integer day) {
        return Arrays.stream(dayWithStar)
                .anyMatch(dayWithStar -> dayWithStar == day);
    }

    @Override
    public Integer discount(Integer day) {
        return EventConfig.SPECIAL_DISCOUNT_AMOUNT;
    }
}
