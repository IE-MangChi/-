package christmas.domain.event;

import christmas.domain.menu.MenuCategory;

public class GiftEvent implements Event<Long, MenuCategory, Long>{
    @Override
    public boolean support(Long OrderAmountBeforeDiscount) {
        if (OrderAmountBeforeDiscount > EventConfig.MINIMUM_ORDER_AMOUNT_FOR_GIFT_EVENT ) {
            return true;
        }
        return false;
    }

    @Override
    public MenuCategory discount(Long amount) {
        return MenuCategory.CHAMPAGNE;
    }
}
