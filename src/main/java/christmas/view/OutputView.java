package christmas.view;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.OrderDto;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    public void printIntro() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printMenu(OrderDto orderDto) {
        Map<MenuCategory, Integer> order = orderDto.order();

        System.out.println("주문 메뉴");
        for (Entry<MenuCategory, Integer> entry : order.entrySet()) {
            System.out.println(entry.getKey().getDish() +" "+ entry.getValue());
        }
    }

    public void printTotalAmount(long amount) {
        System.out.println("할인 전 총주문 금액");
        System.out.println(amount);
    }

    public void printGiftMenu(MenuCategory gift) {
        System.out.println("증정 메뉴");
        if (gift == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(gift.getDish());
    }

    public void printDiscountResultIntro() {
        System.out.println("혜택 내역");

    }

    public void printD_DayDiscount(Integer amount) {
        if (amount == null) {
            System.out.println("크리스마스 디데이 할인: 없음");
            return;
        }
        System.out.println("크리스마스 디데이 할인: -"+ amount);
    }

    public void printGiftDiscount(MenuCategory gift) {
        if (gift == null) {
            System.out.println("증정 이벤트: 없음");
            return;
        }
        System.out.println("증정 이벤트: -"+ gift.getPrice());
    }

    // 주간 할인 추가 예정

    public void printSpecialDiscount(Integer amount) {
        if (amount == null) {
            System.out.println("특별 할인:: 없음");
            return;
        }
        System.out.println("특별 할인:: -"+ amount);
    }
}
