package testings.effect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import effect.PoisonEffect;
import effect.EffectInTurns;
import entity.Entity;

class PoisonEffectTest {
    
    private PoisonEffect effect;
    private Entity entity;
    private static final double INITIAL_POISON_DAMAGE = 10.0;
    private static final int INITIAL_DURATION = 3;
    private static final String INITIAL_ENTITY_NAME = "TestEntity";
    
    @BeforeEach
    void setUp() {
        effect = new PoisonEffect(INITIAL_POISON_DAMAGE, INITIAL_DURATION);
        entity = new Entity(100, 0, 0, null) {
            @Override
            public String getName() {
                return INITIAL_ENTITY_NAME;
            }
        };
    }
    
    @Nested
    class Construction {
        @ParameterizedTest
        @CsvSource({
            "10.0, 3",
            "0.0, 1",
            "5.5, 5",
            "-5.0, 2",
            "100.0, 10"
        })
        void testConstructorWithVariousValues(double damage, int duration) {
            PoisonEffect testEffect = new PoisonEffect(damage, duration);
            assertEquals("Poison", testEffect.getName());
            assertEquals(duration, testEffect.getRoundsLeft());
            assertEquals(String.format("Poison (%d rounds, %.2f damage)", duration, damage), 
                        testEffect.getFormattedEffectInfo());
        }

        @Test
        void testConstructorWithZeroDuration() {
            PoisonEffect testEffect = new PoisonEffect(INITIAL_POISON_DAMAGE, 0);
            assertEquals(0, testEffect.getRoundsLeft());
        }

        @Test
        void testConstructorWithNegativeDuration() {
            PoisonEffect testEffect = new PoisonEffect(INITIAL_POISON_DAMAGE, -1);
            assertEquals(-1, testEffect.getRoundsLeft());
        }
    }
    
    @Nested
    class Application {
        @Test
        void testBasicApply() {
            double initialHealth = entity.getHealth();
            effect.apply(entity);
            assertEquals(initialHealth - INITIAL_POISON_DAMAGE, entity.getHealth());
        }
        
        @ParameterizedTest
        @ValueSource(doubles = {0.0, 5.5, 10.0, 50.0, 100.0})
        void testApplyWithVariousDamageValues(double damage) {
            PoisonEffect testEffect = new PoisonEffect(damage, INITIAL_DURATION);
            double initialHealth = entity.getHealth();
            testEffect.apply(entity);
            assertEquals(initialHealth - damage, entity.getHealth());
        }
        
        @Test
        void testApplyToNullEntity() {
            assertThrows(NullPointerException.class, () -> effect.apply(null));
        }
        
        @Test
        void testMultipleApplications() {
            double initialHealth = entity.getHealth();
            effect.apply(entity);
            effect.apply(entity);
            assertEquals(initialHealth - (INITIAL_POISON_DAMAGE * 2), entity.getHealth());
        }
        
        @Test
        void testApplyWithZeroHealth() {
            entity.takeDamage(100); // Reduce health to 0
            effect.apply(entity); // Apply poison effect, new health is -10
            assertEquals(-10, entity.getHealth());
        }
    }
    
    @Nested
    class Removal {
        @Test
        void testRemove() {
            double initialHealth = entity.getHealth();
            effect.remove(entity);
            assertEquals(initialHealth, entity.getHealth());
        }
        
        @Test
        void testRemoveFromNullEntity() {
            assertDoesNotThrow(() -> effect.remove(null));
        }
    }
    
    @Nested
    class Stacking {
        @Test
        void testStackWithLongerDuration() {
            PoisonEffect longerEffect = new PoisonEffect(INITIAL_POISON_DAMAGE, INITIAL_DURATION + 2);
            effect.stack(longerEffect);
            assertEquals(INITIAL_DURATION + 2, effect.getRoundsLeft());
        }
        
        @Test
        void testStackWithShorterDuration() {
            PoisonEffect shorterEffect = new PoisonEffect(INITIAL_POISON_DAMAGE, INITIAL_DURATION - 1);
            effect.stack(shorterEffect);
            assertEquals(INITIAL_DURATION, effect.getRoundsLeft());
        }
        
        @Test
        void testStackWithNull() {
            int initialRounds = effect.getRoundsLeft();
            effect.stack(null);
            assertEquals(initialRounds, effect.getRoundsLeft());
        }
        
        @Test
        void testStackWithDifferentEffect() {
            EffectInTurns differentEffect = new EffectInTurns("Different", 5) {
                @Override
                public void apply(Entity target) {}
                
                @Override
                public void remove(Entity target) {}

				@Override
				public void stack(EffectInTurns other) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public String getFormattedEffectInfo() {
					// TODO Auto-generated method stub
					return null;
				}
            };
            int initialRounds = effect.getRoundsLeft();
            effect.stack(differentEffect);
            assertEquals(initialRounds, effect.getRoundsLeft());
        }
    }
    
    @Nested
    class Formatting {
        @ParameterizedTest
        @CsvSource({
            "10.0, 3, 'Poison (3 rounds, 10.00 damage)'",
            "5.5, 1, 'Poison (1 rounds, 5.50 damage)'",
            "0.0, 5, 'Poison (5 rounds, 0.00 damage)'",
            "-5.0, 2, 'Poison (2 rounds, -5.00 damage)'",
            "100.0, 10, 'Poison (10 rounds, 100.00 damage)'"
        })
        void testGetFormattedEffectInfo(double damage, int duration, String expected) {
            PoisonEffect testEffect = new PoisonEffect(damage, duration);
            assertEquals(expected, testEffect.getFormattedEffectInfo());
        }
    }
    
}