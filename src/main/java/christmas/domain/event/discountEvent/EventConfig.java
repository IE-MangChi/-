package christmas.domain.event.discountEvent;

import christmas.domain.menu.MenuCategory;
import java.time.DayOfWeek;
import java.time.Instant;
import java.util.List;

public class EventConfig {

    public static final int YEAR = 2023;
    public static final int MONTH = 12;

    // 디데이 할인
    public static final Long INITAL_DISCOUNT_AMOUNT = 1_000L;
    public static final Long DAILY_DISCOUNT_INCREASE = 100L;
    public static final int START_DATE = 1;
    public static final int END_DATE = 25;

    // 증정 이벤트
    public static final Long MINIMUM_ORDER_AMOUNT_FOR_GIFT_EVENT = 120_000L;

    // 특별 할인
    public static final List<Integer> SPECIAL_DISCOUNT_DAY = List.of(3, 10, 17, 24, 25, 31);
    public static final Long SPECIAL_DISCOUNT_AMOUNT = 1_000L;

    // 평일 할인
    public static final List<DayOfWeek> WEEKDAY_DISCOUNT_DAY_OF_WEEK = List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);
    public static final List<MenuCategory> WEEKDAY_DISCOUNT_MENU = List.of(MenuCategory.CHOCO_CAKE, MenuCategory.ICE_CREAM);
    public static final long WEEKDAY_DISCOUNT_AMOUNT = 2_023;

    // 주말 할인
    public static final List<DayOfWeek> WEEKEND_DISCOUNT_DAY_OF_WEEK = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    public static final List<MenuCategory> WEEKEND_DISCOUNT_MENU = List.of(MenuCategory.T_BONE_STEAK, MenuCategory.BBQ_RIB, MenuCategory.CHRISTMAS_PASTA, MenuCategory.SEAFOOD_PASTA);
    public static final long WEEKEND_DISCOUNT_AMOUNT = 2_023;
}
