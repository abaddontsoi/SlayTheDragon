package testings.effect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import effect.EffectInTurns;
import entity.Entity;

class EffectInTurnsTest {

    EffectInTurns effect;
    
    private static final String INITIAL_EFFECT_NAME = "Effect";
    private static final int INITIAL_ROUNDS_LEFT = 1;
    private static final String INITIAL_FORMATTED_EFFECT_INFO = "Info";

    @BeforeEach
    void setUp() throws Exception {
        effect = new EffectInTurns(INITIAL_EFFECT_NAME, INITIAL_ROUNDS_LEFT) {
            @Override
            public void apply(Entity target) {
                // Test implementation
            }

            @Override
            public void remove(Entity target) {
                // Test implementation
            }

            @Override
            public void stack(EffectInTurns other) {
                // Test implementation
            }

            @Override
            public String getFormattedEffectInfo() {
                return INITIAL_FORMATTED_EFFECT_INFO;
            }
        };
    }

    @Nested
    class Constructor {
        @Test
        void testConstructorAndGetters() {
            assertEquals(INITIAL_EFFECT_NAME, effect.getName());
            assertEquals(INITIAL_ROUNDS_LEFT, effect.getRoundsLeft());
            assertEquals(INITIAL_FORMATTED_EFFECT_INFO, effect.getFormattedEffectInfo());
        }
    }

    @Nested
    class Expiration {
        
        @Test
        void testExpire() {
            assertFalse(effect.isExpired());
            assertEquals(INITIAL_ROUNDS_LEFT, effect.getRoundsLeft());
            
            for (int i=0; i<INITIAL_ROUNDS_LEFT; i++) {
                effect.decrementDuration();
            }
            
            assertTrue(effect.getRoundsLeft() == 0);
            assertTrue(effect.isExpired());
        }

        @Test
        void testNegativeRoundsExpiration() {
            // Decrement beyond zero
            for (int i = 0; i < INITIAL_ROUNDS_LEFT + 1; i++) {
                effect.decrementDuration();
            }
            assertTrue(effect.isExpired());
            assertTrue(effect.getRoundsLeft() < 0);
        }

        @Test
        void testInitialZeroRounds() {
            EffectInTurns zeroEffect = new EffectInTurns(INITIAL_EFFECT_NAME, 0) {
                @Override
                public void apply(Entity target) {}
                @Override
                public void remove(Entity target) {}
                @Override
                public void stack(EffectInTurns other) {}
                @Override
                public String getFormattedEffectInfo() {
                    return INITIAL_FORMATTED_EFFECT_INFO;
                }
            };
            assertTrue(zeroEffect.isExpired());
        }
    }
    
    @Nested
    class Equality {
        
        private EffectInTurns otherEffect_sameName;
        private EffectInTurns otherEffect_diffName;
        private static final String INITIAL_OTHER_EFFECT_NAME = "Other Effect";
        private static final int INITIAL_OTHER_ROUNDS_LEFT = 2;
        private static final String INITIAL_OTHER_FORMATTED_EFFECT_INFO = "Other Info";
        
        @BeforeEach
        void setUp() throws Exception {
            otherEffect_diffName = new EffectInTurns(INITIAL_OTHER_EFFECT_NAME, INITIAL_OTHER_ROUNDS_LEFT) {
                @Override
                public void apply(Entity target) {}
                @Override
                public void remove(Entity target) {}
                @Override
                public void stack(EffectInTurns other) {}
                @Override
                public String getFormattedEffectInfo() {
                    return INITIAL_OTHER_FORMATTED_EFFECT_INFO;
                }
            };
            
            otherEffect_sameName = new EffectInTurns(INITIAL_EFFECT_NAME, INITIAL_ROUNDS_LEFT) {
                @Override
                public void apply(Entity target) {}
                @Override
                public void remove(Entity target) {}
                @Override
                public void stack(EffectInTurns other) {}
                @Override
                public String getFormattedEffectInfo() {
                    return INITIAL_FORMATTED_EFFECT_INFO;
                }
            };
        }
        
        @Test
        void testIsEqual() {
            assertTrue(effect.equals(effect));
        }

        
        @Test
        void testNotEqual() {
        	// False due to anonymous class 
            assertFalse(effect.equals(otherEffect_diffName));
            assertFalse(effect.equals(new Object()));
            assertFalse(effect.equals(otherEffect_sameName));
            assertFalse(otherEffect_diffName.equals(otherEffect_sameName));
        }

        @Test
        void testNullEquality() {
            assertFalse(effect.equals(null));
        }

    }
}