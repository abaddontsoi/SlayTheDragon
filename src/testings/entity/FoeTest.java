package testings.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.ICard;
import entity.Foe;

class FoeTest {
	private Foe foe;
	private List<ICard> foeDeck;
	
	private static final String FOE_NAME = "Test foe";
	private static final String FOE_TYPE = "Test type";
    private static final double DELTA = 0.001;
	private static final double INITIAL_MAX_HEALTH = 100;
	private static final int INITIAL_DEFENSE = 0;
	private static final int INITIAL_STRENGTH = 0;
	
	@BeforeEach
	void setUp() throws Exception {
		foeDeck = new ArrayList<>();
		foe = new Foe(FOE_NAME, FOE_TYPE, INITIAL_MAX_HEALTH, INITIAL_DEFENSE, INITIAL_STRENGTH, foeDeck);
	}

	@Test
	void testGetName() {
		String result = foe.getName();
		assertEquals(FOE_NAME, result);
	}

	@Test
	void testConstructor() {
        assertEquals(INITIAL_MAX_HEALTH, foe.getMaxHealth(), DELTA);
        assertEquals(INITIAL_MAX_HEALTH, foe.getHealth(), DELTA);
        assertEquals(INITIAL_DEFENSE, foe.getDefense(), DELTA);
        assertEquals(INITIAL_STRENGTH, foe.getStrength(), DELTA);
        assertTrue(foe.getEffects().isEmpty());
        assertEquals(FOE_NAME, foe.getName());

	}

	@Test
	void testGetType() {
		String result = foe.getType();
		assertEquals(FOE_TYPE, result);
	}

}
