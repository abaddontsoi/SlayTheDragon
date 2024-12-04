package testings.effect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import card.ICard;

import org.junit.jupiter.params.provider.CsvSource;

import effect.EffectInTurns;
import effect.ExtraMaxHealthEffect;
import entity.Entity;

class ExtraMaxHealthEffectTest {
    
    private ExtraMaxHealthEffect effect;
    private TestEntity testEntity;
    private static final double INITIAL_MAX_HEALTH = 10.0;
    private static final int INITIAL_DURATION = 3;
    
    private static class TestEntity extends Entity {
        public TestEntity(double maxHealth, int defense, int strength, List<ICard> deck) {
			super(maxHealth, defense, strength, deck);
			// TODO Auto-generated constructor stub
		}

		private double maxHealth = 100.0;
        
        @Override
        public void increaseMaxHealth(double amount) {
            this.maxHealth += amount;
        }
        
        @Override
        public void decreaseMaxHealth(double amount) {
            this.maxHealth -= amount;
        }
        
        public double getMaxHealth() {
            return maxHealth;
        }

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}
    }
    
    @BeforeEach
    void setUp() {
        effect = new ExtraMaxHealthEffect(INITIAL_MAX_HEALTH, INITIAL_DURATION);
        testEntity = new TestEntity(0, 0, 0, null);
    }
    
    @Nested
    class Construction {
        @ParameterizedTest
        @CsvSource({
            "10.0, 3",
            "0.0, 1",
            "-5.0, 5",
            "15.5, 10",
            "100.0, 2"
        })
        void testConstructor(double maxHealth, int duration) {
            ExtraMaxHealthEffect testEffect = new ExtraMaxHealthEffect(maxHealth, duration);
            assertEquals("Extra Max Health", testEffect.getName());
            assertEquals(duration, testEffect.getRoundsLeft());
        }
    }
    
    @Nested
    class Application {
        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0})
        void testApply(double maxHealthAmount) {
            ExtraMaxHealthEffect testEffect = new ExtraMaxHealthEffect(maxHealthAmount, INITIAL_DURATION);
            double initialMaxHealth = testEntity.getMaxHealth();
            testEffect.apply(testEntity);
            assertEquals(initialMaxHealth + maxHealthAmount, testEntity.getMaxHealth());
        }
        
        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0})
        void testRemove(double maxHealthAmount) {
            ExtraMaxHealthEffect testEffect = new ExtraMaxHealthEffect(maxHealthAmount, INITIAL_DURATION);
            testEffect.apply(testEntity);
            double maxHealthAfterApply = testEntity.getMaxHealth();
            
            testEffect.remove(testEntity);
            assertEquals(maxHealthAfterApply - maxHealthAmount, testEntity.getMaxHealth());
        }
        
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 5, 10})
        void testApplyAndRemoveMultipleTimes(int times) {
            double initialMaxHealth = testEntity.getMaxHealth();
            
            // Apply multiple times
            for (int i = 0; i < times; i++) {
                effect.apply(testEntity);
            }
            assertEquals(initialMaxHealth + (INITIAL_MAX_HEALTH * times), testEntity.getMaxHealth());
            
            // Remove multiple times
            for (int i = 0; i < times; i++) {
                effect.remove(testEntity);
            }
            assertEquals(initialMaxHealth, testEntity.getMaxHealth());
        }
    }
    
    @Nested
    class Formatting {
        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0, -100.0})
        void testGetFormattedEffectInfo(double maxHealth) {
            ExtraMaxHealthEffect testEffect = new ExtraMaxHealthEffect(maxHealth, INITIAL_DURATION);
            String expected = String.format("Extra Max Health (%d rounds, %.2f max health)", 
                                         INITIAL_DURATION, maxHealth);
            assertEquals(expected, testEffect.getFormattedEffectInfo());
        }
    }
    
    @Nested
    class Stacking {
        @ParameterizedTest
        @ValueSource(ints = {4, 5, 10, 100})
        void testStackWithHigherDuration(int higherDuration) {
            ExtraMaxHealthEffect otherEffect = new ExtraMaxHealthEffect(INITIAL_MAX_HEALTH, higherDuration);
            effect.stack(otherEffect);
            assertEquals(higherDuration, effect.getRoundsLeft());
        }
        
        @ParameterizedTest
        @ValueSource(ints = {1, 2})
        void testStackWithLowerDuration(int lowerDuration) {
            ExtraMaxHealthEffect otherEffect = new ExtraMaxHealthEffect(INITIAL_MAX_HEALTH, lowerDuration);
            effect.stack(otherEffect);
            assertEquals(INITIAL_DURATION, effect.getRoundsLeft());
        }
        
        @ParameterizedTest
        @ValueSource(doubles = {5.0, 15.0, 20.0})
        void testStackWithDifferentMaxHealth(double differentMaxHealth) {
            ExtraMaxHealthEffect otherEffect = new ExtraMaxHealthEffect(differentMaxHealth, INITIAL_DURATION);
            int originalDuration = effect.getRoundsLeft();
            effect.stack(otherEffect);
            assertEquals(originalDuration, effect.getRoundsLeft());
        }
        
        @Test
        void testStackWithDifferentEffect() {
            EffectInTurns differentEffect = new EffectInTurns("Different Effect", 5) {
                @Override
                public void apply(Entity target) {}
                
                @Override
                public void remove(Entity target) {}
                
                @Override
                public String getFormattedEffectInfo() {
                    return "";
                }
                
                @Override
                public void stack(EffectInTurns other) {}
            };
            
            int originalDuration = effect.getRoundsLeft();
            effect.stack(differentEffect);
            assertEquals(originalDuration, effect.getRoundsLeft());
        }
    }
    
    @Nested
    class Equality {
        @ParameterizedTest
        @CsvSource({
            "10.0, 3, true",    // Same values
            "15.0, 3, true",    // Different max health but same class and name
            "10.0, 5, true",    // Different duration but same class and name
            "15.0, 5, true"     // Different max health and duration but same class and name
        })
        void testEquals(double maxHealth, int duration, boolean expectedResult) {
            ExtraMaxHealthEffect otherEffect = new ExtraMaxHealthEffect(maxHealth, duration);
            assertEquals(expectedResult, effect.equals(otherEffect));
        }

    }
}