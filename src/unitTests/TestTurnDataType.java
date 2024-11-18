package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import record.turnDataType.TurnDataType;

class TestTurnDataType {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test_1() {
		TurnDataType t = TurnDataType.AttackType;
		assertEquals("Attack", t.toString());
	}

}
