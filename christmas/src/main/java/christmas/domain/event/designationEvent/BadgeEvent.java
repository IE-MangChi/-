package christmas.domain.event.designationEvent;

public enum BadgeEvent {

    NOTHING("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String badge;

    BadgeEvent(String badge) {
        this.badge = badge;
    }

    public static BadgeEvent getBadgeByTotalBenefitAmount(final long amount) {
        if (amount >= EventConfig.SANTA_BADGE_MINIMUM_AMOUNT) {
            return SANTA;
        }
        if (amount >= EventConfig.TREE_BADGE_MINIMUM_AMOUNT) {
            return TREE;
        }
        if (amount >= EventConfig.STAR_BADGE_MINIMUM_AMOUNT) {
            return STAR;
        }
        return NOTHING;
    }

    public String getBadge() {
        return badge;
    }
}
