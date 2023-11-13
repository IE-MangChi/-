package christmas.domain.event;

import christmas.domain.menu.MenuCategory;

public class GiftEvent implements Event<Integer, MenuCategory, Integer>{
    @Override
    public boolean support(Integer OrderAmountBeforeDiscount) {
        if (OrderAmountBeforeDiscount > EventConfig.MINIMUM_ORDER_AMOUNT_FOR_GIFT_EVENT ) {
            return true;
        }
        return false;
    }

    @Override
    public MenuCategory discount(Integer amount) {
        return MenuCategory.CHAMPAGNE;
    }
}
