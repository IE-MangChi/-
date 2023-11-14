package christmas.domain.order;

import static christmas.domain.menu.MenuCategory.TAPAS;
import static christmas.domain.menu.MenuCategory.ZERO_COLA;

import christmas.domain.menu.MenuCategory;
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
        Order order = new Order();
        // when
        order.plus("타파스", 2);
        order.plus("제로콜라", 1);
        Map<MenuCategory, Integer> store = order.getOrder().order();
        // then
        Assertions.assertThat(store.get(TAPAS)).isEqualTo(2);
        Assertions.assertThat(store.get(ZERO_COLA)).isEqualTo(1);
    }

    @Test
    @DisplayName("비정상적인 메뉴 주문시 에러를 발생시켜야한다.")
    void wrongMenuOrderResultTest() {
        // given
        Order order = new Order();
        // when && then
        Assertions.assertThatThrownBy(() -> order.plus("뿌쓩뿌쓩",2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("20개 초과하는 메뉴를 주문시 에러를 발생시켜야한다.")
    void menuOrderResultOverTest() {
        // given
        Order order = new Order();
        // when && then
        Assertions.assertThatThrownBy(() -> order.plus("뿌쓩뿌쓩",25))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴 리스트를 넘기면 총 주문 개수 합을 반환해야한다.")
    void getMenuByMenuNamesTest() {
        // given
        Order order = new Order();
        order.plus("제로콜라",3);
        order.plus("타파스",7);
        // when
        List<MenuCategory> menus = List.of(ZERO_COLA, TAPAS);
        int count = order.getMenuByMenuNames(menus);
        // then
        Assertions.assertThat(count).isEqualTo(10);
    }
}