package christmas.domain.order.dto;


import christmas.domain.menu.MenuCategory;
import java.util.Map;

public record MenuQuantityDto(Map<MenuCategory, Integer> order) {
}
