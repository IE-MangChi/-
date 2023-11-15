package christmas.domain.order;

import static christmas.domain.menu.MenuCategory.TAPAS;
import static christmas.domain.menu.MenuCategory.ZERO_COLA;

import christmas.domain.menu.MenuCategory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    @DisplayName("정상적인 메뉴 주문시 orderItem 저장소에 담겨야한다.")
    void menuOrderResultTest() {
        // given
        HashMap<String, Integer> menus = new HashMap<>();
        menus.put("타파스", 2);
        menus.put("제로콜라", 1);
        // when
        Order order = Order.of(menus);
        Map<MenuCategory, Integer> store = order.getOrder().order();
        // then
        Assertions.assertThat(store.get(TAPAS)).isEqualTo(2);
        Assertions.assertThat(store.get(ZERO_COLA)).isEqualTo(1);
    }

    @Test
    @DisplayName("비정상적인 메뉴 주문시 에러를 발생시켜야한다.")
    void wrongMenuOrderResultTest() {
        // given
        HashMap<String, Integer> menus = new HashMap<>();
        menus.put("타파스", 2);
        menus.put("뿌슝뿌슝", 1);
        // when && then
        Assertions.assertThatThrownBy(() -> Order.of(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("20개 초과하는 메뉴를 주문시 에러를 발생시켜야한다.")
    void menuOrderResultOverTest() {
        // given
        HashMap<String, Integer> menus = new HashMap<>();
        menus.put("타파스", 2);
        menus.put("제로콜라", 10);
        menus.put("티본스테이크", 10);
        // when && then
        Assertions.assertThatThrownBy(() -> Order.of(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴 리스트를 넘기면 총 주문 개수 합을 반환해야한다.")
    void getMenuByMenuNamesTest() {
        // given
        HashMap<String, Integer> menus = new HashMap<>();
        menus.put("타파스", 2);
        menus.put("제로콜라", 10);
        Order order = Order.of(menus);
        // when
        List<MenuCategory> cola = List.of(ZERO_COLA, TAPAS);
        int count = order.getMenuCountByMenuNames(cola);
        // then
        Assertions.assertThat(count).isEqualTo(12);
    }
}