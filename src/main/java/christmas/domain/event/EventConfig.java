package christmas.domain.event;

import christmas.domain.menu.MenuCategory;
import java.util.List;

public class EventConfig {

    public static final int YEAR = 2023;
    public static final int MONTH = 12;
    public static final int INITAL_DISCOUNT_AMOUNT = 1_000;
    public static final int DAILY_DISCOUNT_INCREASE = 100;
    public static final int START_DATE = 1;
    public static final int END_DATE = 25;
    public static final int MINIMUM_ORDER_AMOUNT_FOR_GIFT_EVENT = 120_000;
    public static final int[] SPECIAL_DISCOUNT_DAY = {3, 10, 17, 24, 25, 31};
    public static final Integer SPECIAL_DISCOUNT_AMOUNT = 1_000;
    public static final List<String> WEEKDAY_DISCOUNT_DAY_OF_WEEK = List.of("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY");
    public static final List<MenuCategory> WEEKDAY_DISCOUNT_MENU = List.of(MenuCategory.CHOCO_CAKE, MenuCategory.ICE_CREAM);
    public static final long WEEKDAY_DISCOUNT_AMOUNT = 2_023;
    public static final List<String> WEEKEND_DISCOUNT_DAY_OF_WEEK = List.of("FRIDAY", "SATURDAY");
    public static final List<MenuCategory> WEEKEND_DISCOUNT_MENU = List.of(MenuCategory.CHOCO_CAKE, MenuCategory.ICE_CREAM);
    public static final long WEEKEND_DISCOUNT_AMOUNT = 2_023;
}
