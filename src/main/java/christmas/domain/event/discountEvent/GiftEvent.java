package christmas.domain.event.discountEvent;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.OrderDto;
import java.util.HashMap;

public class GiftEvent implements Event<Long, OrderDto, Long> {

    private final MenuCategory gift = MenuCategory.CHAMPAGNE;

    @Override
    public boolean support(Long OrderAmountBeforeDiscount) {
        return OrderAmountBeforeDiscount > EventConfig.MINIMUM_ORDER_AMOUNT_FOR_GIFT_EVENT;
    }

    @Override
    public OrderDto discount(Long amount) {
        HashMap<MenuCategory, Integer> giftStore = new HashMap<>();
        giftStore.put(gift, 1);
        return new OrderDto(giftStore);
    }
}
