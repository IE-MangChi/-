package christmas.domain.event.discountEvent;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.Planner;
import christmas.domain.order.dto.MenuQuantityDto;
import java.util.HashMap;

public class GiftEvent implements Event<Planner, MenuQuantityDto> {

    private final MenuCategory gift = MenuCategory.CHAMPAGNE;

    @Override
    public boolean support(final Planner planner) {
        return planner.getTotalAmount() > EventConfig.MINIMUM_ORDER_AMOUNT_FOR_GIFT_EVENT;
    }

    @Override
    public MenuQuantityDto discount(final Planner planner) {
        HashMap<MenuCategory, Integer> giftStore = new HashMap<>();
        giftStore.put(gift, 1);
        return new MenuQuantityDto(giftStore);
    }
}
