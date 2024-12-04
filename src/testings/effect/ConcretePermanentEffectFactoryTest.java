package testings.effect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import effect.ConcretePermanentEffectFactory;

class ConcretePermanentEffectFactoryTest {
	
	private ConcretePermanentEffectFactory factory;
	private static final String INITIAL_PERMA_HEALTH_EFFECT = "PermanentExtraMaxHealthEffect";
	private static final String INITIAL_PERMA_DEFENSE_EFFECT = "PermanentExtraDefenseEffect";
	
	@BeforeEach
	void setUp() throws Exception {
		factory = new ConcretePermanentEffectFactory();
	}

	@Nested
	class CreateEffect {
		
		@Test
		void testCreateEffect() {
			assertNotNull(factory.createEffect(INITIAL_PERMA_HEALTH_EFFECT));
			assertDoesNotThrow(() -> factory.createEffect(INITIAL_PERMA_HEALTH_EFFECT));
			
			assertNotNull(factory.createEffect(INITIAL_PERMA_DEFENSE_EFFECT));
			assertDoesNotThrow(() -> factory.createEffect(INITIAL_PERMA_DEFENSE_EFFECT));
		}
		
		@ParameterizedTest
		@ValueSource(strings = {INITIAL_PERMA_HEALTH_EFFECT, INITIAL_PERMA_DEFENSE_EFFECT})
		void testCreateEffect_DoesNotThrow(String type) {
			assertDoesNotThrow(() -> factory.createEffect(type));
		}
		
		@ParameterizedTest
		@ValueSource(strings = {"Other"})
		void testCreateEffect_Throw(String type) {
			assertThrows(IllegalArgumentException.class, () -> factory.createEffect(type));
		}
		
		@Test
		void testCreateEffect_Throw() {
			assertThrows(NullPointerException.class, () -> factory.createEffect(null));
		}
	}
}
