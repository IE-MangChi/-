package christmas.domain.event.discountEvent;

import christmas.domain.menu.MenuCategory;
import java.util.List;

public class EventConfig {

    protected static final int YEAR = 2023;
    protected static final int MONTH = 12;

    // 디데이 할인
    protected static final int INITAL_DISCOUNT_AMOUNT = 1_000;
    protected static final int DAILY_DISCOUNT_INCREASE = 100;
    protected static final int START_DATE = 1;
    protected static final int END_DATE = 25;

    // 증정 이벤트
    protected static final int MINIMUM_ORDER_AMOUNT_FOR_GIFT_EVENT = 120_000;

    // 특별 할인
    protected static final int[] SPECIAL_DISCOUNT_DAY = {3, 10, 17, 24, 25, 31};
    protected static final Integer SPECIAL_DISCOUNT_AMOUNT = 1_000;

    // 평일 할인
    protected static final List<String> WEEKDAY_DISCOUNT_DAY_OF_WEEK = List.of("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY");
    protected static final List<MenuCategory> WEEKDAY_DISCOUNT_MENU = List.of(MenuCategory.CHOCO_CAKE, MenuCategory.ICE_CREAM);
    protected static final long WEEKDAY_DISCOUNT_AMOUNT = 2_023;

    // 주말 할인
    protected static final List<String> WEEKEND_DISCOUNT_DAY_OF_WEEK = List.of("FRIDAY", "SATURDAY");
    protected static final List<MenuCategory> WEEKEND_DISCOUNT_MENU = List.of(MenuCategory.CHOCO_CAKE, MenuCategory.ICE_CREAM);
    protected static final long WEEKEND_DISCOUNT_AMOUNT = 2_023;
}
