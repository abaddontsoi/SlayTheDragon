package card;

public enum CardName {
	ATTACK_BASIC(Level.BASIC),
    ATTACK_ADVANCED(Level.ADVANCED),
    DEFEND_BASIC(Level.BASIC),
    DEFEND_ADVANCED(Level.ADVANCED),
    SKILL_BASIC_HEAL(Level.BASIC),
    SKILL_ADVANCED_HEAL(Level.ADVANCED),
    SKILL_BASIC_POISON(Level.BASIC),
    SKILL_ADVANCED_POISON(Level.ADVANCED),
    SKILL_BASIC_DRAW(Level.BASIC);
    private final Level level;

    CardName(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public enum Level {
        BASIC,
        ADVANCED
    }
}
