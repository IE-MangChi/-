package christmas.domain.menu;

import java.util.Arrays;
import java.util.Optional;

public enum MenuCategory {

    // 에피타이저
    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),

    // 음료
    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIB("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),

    // 디저트
    CHOCO_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);

    private final String dish;
    private final int price;

    MenuCategory(String dish, int price) {
        this.dish = dish;
        this.price = price;
    }


    public static Optional<MenuCategory> getDishDataByName(String menuName) {
        return Arrays.stream(MenuCategory.values()).filter(menuCategory -> menuCategory.dish.equals(menuName))
                .findFirst();
    }

    public String getDish() {
        return dish;
    }

    public int getPrice() {
        return price;
    }

}
