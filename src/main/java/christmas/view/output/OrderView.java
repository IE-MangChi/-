package christmas.view.output;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.OrderDto;
import java.util.Map;
import java.util.Map.Entry;

public class OrderView {

    public void printIntro() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printPreview() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printMenu(OrderDto orderDto) {
        Map<MenuCategory, Integer> order = orderDto.order();

        System.out.println("\n<주문 메뉴>");
        for (Entry<MenuCategory, Integer> entry : order.entrySet()) {
            System.out.printf("%s %d개\n", entry.getKey().getDish(), entry.getValue());
        }
    }
}
