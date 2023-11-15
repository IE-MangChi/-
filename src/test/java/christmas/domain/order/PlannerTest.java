package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.MenuQuantityDto;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlannerTest {

    @Test
    @DisplayName("메뉴이름을 넘기면 해당하는 메뉴의 개수를 더한값을 반환해야한다")
    void getMenuCountByMenuNamesTest() {
        // given
        Map<String, Integer> menus = Map.of("아이스크림",2, "시저샐러드", 4);
        Planner planner = Planner.of(menus, 24);
        List<MenuCategory> menuNames = List.of(MenuCategory.ICE_CREAM);
        // when
        int count = planner.getMenuCountByMenuNames(menuNames);
        // then
        Assertions.assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("메뉴이름을 넘기면 해당하는 메뉴의 개수를 더한값을 반환해야한다")
    void getMenuCountByMenuNamesZeroTest() {
        // given
        Map<String, Integer> menus = Map.of("아이스크림",2, "시저샐러드", 4);
        Planner planner = Planner.of(menus, 24);
        List<MenuCategory> menuNames = List.of(MenuCategory.SEAFOOD_PASTA,MenuCategory.ZERO_COLA);
        // when
        int count = planner.getMenuCountByMenuNames(menuNames);
        // then
        Assertions.assertThat(count).isEqualTo(0);
    }

    @Test
    @DisplayName("아이스크림을 3개 주문하면 15,000을 반환해야한다")
    void getTotalAmountTest() {
        // given
        Map<String, Integer> menus = Map.of("아이스크림",3);
        Planner planner = Planner.of(menus, 24);
        // when
        long totalAmount = planner.getTotalAmount();
        // then
        Assertions.assertThat(totalAmount).isEqualTo(15000);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2:SATURDAY",
            "3:SUNDAY",
            "4:MONDAY",
            "5:TUESDAY",
            "6:WEDNESDAY",
            "7:THURSDAY",
            "8:FRIDAY"
    }, delimiter = ':')
    @DisplayName("날짜를 넣으면 해당하는 요일을 반환해야한다")
    void getWeekOfDayTest(int date, DayOfWeek expected) {
        // given
        Map<String, Integer> menus = Map.of("아이스크림",3);
        Planner planner = Planner.of(menus, date);
        // then
        Assertions.assertThat(planner.getWeekOfDay()).isEqualTo(expected);
    }

    @Test
    @DisplayName("아이스크림 2개, 시저샐러드 3개 주문시 해당 내용만 주문에 담겨야한다")
    void getOrderTest() {
        // given
        Map<String, Integer> menus = Map.of("아이스크림",2, "시저샐러드", 3);
        Planner planner = Planner.of(menus, 24);
        // when
        MenuQuantityDto order = planner.getOrder();
        // then
        Assertions.assertThat(order.order().size()).isEqualTo(2);
        Assertions.assertThat(order.order().get(MenuCategory.ICE_CREAM)).isEqualTo(2);
        Assertions.assertThat(order.order().get(MenuCategory.CAESAR_SALAD)).isEqualTo(3);
    }
}