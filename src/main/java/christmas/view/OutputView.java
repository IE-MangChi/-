package christmas.view;

import christmas.domain.event.designationEvent.BadgeEvent;
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
            System.out.println(entry.getKey().getDish() + " " + entry.getValue());
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
        System.out.println(gift.getDish() + " 1개");
    }

    public void printDiscountResultIntro() {
        System.out.println("혜택 내역");

    }

    public void printDiscountEmpty() {
        System.out.println("없음");
    }

    public void printD_DayDiscount(Integer amount) {
        System.out.println("크리스마스 디데이 할인: -" + amount + "원");
    }

    public void printWeekdayDiscount(Integer amount) {
        System.out.println("평일 할인: -" + amount + "원");
    }

    public void printWeekendDiscount(Integer amount) {
        System.out.println("주말 할인: -" + amount + "원");
    }

    public void printSpecialDiscount(Integer amount) {
        System.out.println("특별 할인:: -" + amount + "원");
    }

    public void printGiftDiscount(MenuCategory gift) {
        System.out.println("증정 이벤트: -" + gift.getPrice() + "원");
    }

    public void printTotalBenefitAmount(Integer amount) {
        System.out.println("<총혜택 금액>");
        System.out.println("-" + amount + "원");
    }

    public void printAfterDiscountAmount(Integer amount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(amount + "원");
    }

    public void printBadge(BadgeEvent badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getBadge());
    }
}
