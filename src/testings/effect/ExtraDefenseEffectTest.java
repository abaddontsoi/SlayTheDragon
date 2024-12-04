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
import effect.ExtraDefenseEffect;
import entity.Entity;

class ExtraDefenseEffectTest {
    
    private ExtraDefenseEffect effect;
    private TestEntity testEntity;
    private static final double INITIAL_DEFENSE = 10.0;
    private static final int INITIAL_DURATION = 3;
    
    private static class TestEntity extends Entity {
        public TestEntity(double maxHealth, int defense, int strength, List<ICard> deck) {
			super(maxHealth, defense, strength, deck);
			// TODO Auto-generated constructor stub
		}

		private double defense = 0.0;
        
        @Override
        public void increaseDefense(double amount) {
            this.defense += amount;
        }
        
        @Override
        public void decreaseDefense(double amount) {
            this.defense -= amount;
        }
        
        public double getDefense() {
            return defense;
        }

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}
    }
    
    @BeforeEach
    void setUp() {
        effect = new ExtraDefenseEffect(INITIAL_DEFENSE, INITIAL_DURATION);
        testEntity = new TestEntity(0, 0, 0, null);
    }
    
    @Nested
    class Construction {
        @ParameterizedTest
        @CsvSource({
            "10.0, 3",
            "0.0, 1",
            "-5.0, 5",
            "15.5, 10"
        })
        void testConstructor(double defense, int duration) {
            ExtraDefenseEffect testEffect = new ExtraDefenseEffect(defense, duration);
            assertEquals("Extra Max Health", testEffect.getName());
            assertEquals(duration, testEffect.getRoundsLeft());
        }
    }
    
    @Nested
    class Application {
        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0})
        void testApply(double defenseAmount) {
            ExtraDefenseEffect testEffect = new ExtraDefenseEffect(defenseAmount, INITIAL_DURATION);
            double initialDefense = testEntity.getDefense();
            testEffect.apply(testEntity);
            assertEquals(initialDefense + defenseAmount, testEntity.getDefense());
        }
        
        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0})
        void testRemove(double defenseAmount) {
            ExtraDefenseEffect testEffect = new ExtraDefenseEffect(defenseAmount, INITIAL_DURATION);
            testEffect.apply(testEntity);
            double defenseAfterApply = testEntity.getDefense();
            
            testEffect.remove(testEntity);
            assertEquals(defenseAfterApply - defenseAmount, testEntity.getDefense());
        }
        
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 5, 10})
        void testApplyAndRemoveMultipleTimes(int times) {
            double initialDefense = testEntity.getDefense();
            
            // Apply multiple times
            for (int i = 0; i < times; i++) {
                effect.apply(testEntity);
            }
            assertEquals(initialDefense + (INITIAL_DEFENSE * times), testEntity.getDefense());
            
            // Remove multiple times
            for (int i = 0; i < times; i++) {
                effect.remove(testEntity);
            }
            assertEquals(initialDefense, testEntity.getDefense());
        }
    }
    
    @Nested
    class Formatting {
        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0, -100.0})
        void testGetFormattedEffectInfo(double defense) {
            ExtraDefenseEffect testEffect = new ExtraDefenseEffect(defense, INITIAL_DURATION);
            String expected = String.format("Extra Max Health (%d rounds, %.2f defense)", 
                                         INITIAL_DURATION, defense);
            assertEquals(expected, testEffect.getFormattedEffectInfo());
        }
    }
    
    @Nested
    class Stacking {
        @ParameterizedTest
        @ValueSource(ints = {4, 5, 10, 100})
        void testStackWithHigherDuration(int higherDuration) {
            ExtraDefenseEffect otherEffect = new ExtraDefenseEffect(INITIAL_DEFENSE, higherDuration);
            effect.stack(otherEffect);
            assertEquals(higherDuration, effect.getRoundsLeft());
        }
        
        @ParameterizedTest
        @ValueSource(ints = {1, 2})
        void testStackWithLowerDuration(int lowerDuration) {
            ExtraDefenseEffect otherEffect = new ExtraDefenseEffect(INITIAL_DEFENSE, lowerDuration);
            effect.stack(otherEffect);
            assertEquals(INITIAL_DURATION, effect.getRoundsLeft());
        }
        
        @ParameterizedTest
        @ValueSource(doubles = {5.0, 15.0, 20.0})
        void testStackWithDifferentDefense(double differentDefense) {
            ExtraDefenseEffect otherEffect = new ExtraDefenseEffect(differentDefense, INITIAL_DURATION);
            int originalDuration = effect.getRoundsLeft();
            effect.stack(otherEffect);
            assertEquals(originalDuration, effect.getRoundsLeft());
        }
        
        @Test
        void testCannotStack() {
        	EffectInTurns otherEffect = new EffectInTurns(null, 0) {

				@Override
				public void apply(Entity target) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void remove(Entity target) {
					// TODO Auto-generated method stub
					
				}

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
            int originalDuration = effect.getRoundsLeft();
            effect.stack(otherEffect);
            assertEquals(originalDuration, effect.getRoundsLeft());
        }
    }
    
    @Nested
    class Equality {
        @ParameterizedTest
        @CsvSource({
            "10.0, 3, true",    // Same values
            "15.0, 3, true",    // Different defense but same class and name
            "10.0, 5, true",    // Different duration but same class and name
            "15.0, 5, true"     // Different defense and duration but same class and name
        })
        void testEquals(double defense, int duration, boolean expectedResult) {
            ExtraDefenseEffect otherEffect = new ExtraDefenseEffect(defense, duration);
            assertEquals(expectedResult, effect.equals(otherEffect));
        }

        @Test
        void testEqualsWithNull() {
            assertFalse(effect.equals(null));
        }

        @Test
        void testEqualsWithDifferentClass() {
            assertFalse(effect.equals(new Object()));
        }
    }
}