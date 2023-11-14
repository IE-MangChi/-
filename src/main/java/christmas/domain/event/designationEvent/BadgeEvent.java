package christmas.domain.event.designationEvent;

public enum BadgeEvent {

    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String badge;

    BadgeEvent(String badge) {
        this.badge = badge;
    }

    public BadgeEvent getBadgeByTotalBenefitAmount(long amount) {
        if (amount >= EventConfig.STAR_BADGE_MINIMUM_AMOUNT) {
            return STAR;
        }
        if (amount >= EventConfig.TREE_BADGE_MINIMUM_AMOUNT) {
            return TREE;
        }
        if (amount >= EventConfig.SANTA_BADGE_MINIMUM_AMOUNT) {
            return SANTA;
        }
        return null;
    }

    public String getBadge() {
        return badge;
    }
}
