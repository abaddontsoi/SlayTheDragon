package testings.effect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import effect.EffectInTurns;
import effect.PermanentExtraDefenseEffect;
import entity.Entity;

class PermanentExtraDefenseEffectTest {
    private PermanentExtraDefenseEffect effect;
    private Entity testEntity;
    private static final double INITIAL_EXTRA_DEFENSE = 10.0;
    
    @BeforeEach
    void setUp() {
        effect = new PermanentExtraDefenseEffect(INITIAL_EXTRA_DEFENSE);
        testEntity = new Entity(0, 0, 0, null) {

    		@Override
    		public String getName() {
    			// TODO Auto-generated method stub
    			return "Test";
    		}
    		
    	};
    }
    
    @Nested
    class Construction {
        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0})
        void testConstructorWithVariousDefenseValues(double defense) {
            PermanentExtraDefenseEffect testEffect = new PermanentExtraDefenseEffect(defense);
            assertEquals("Extra Defense (Permanent)", testEffect.getName());
            assertEquals(-1, testEffect.getRoundsLeft());
        }
        
        @Test
        void testConstructorPermanentDuration() {
            assertEquals(-1, effect.getRoundsLeft());
        }
    }
    
    @Nested
    class Application {
        @Test
        void testBasicApply() {
            double initialDefense = testEntity.getDefense();
            effect.apply(testEntity);
            assertEquals(initialDefense + INITIAL_EXTRA_DEFENSE, testEntity.getDefense());
        }
        
        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0})
        void testApplyWithVariousDefenseValues(double defenseAmount) {
            PermanentExtraDefenseEffect testEffect = new PermanentExtraDefenseEffect(defenseAmount);
            double initialDefense = testEntity.getDefense();
            testEffect.apply(testEntity);
            assertEquals(initialDefense + (int) defenseAmount, testEntity.getDefense());
        }
        
    }
    
    @Nested
    class Removal {
        @Test
        void testBasicRemove() {
            effect.apply(testEntity);
            double defenseAfterApply = testEntity.getDefense();
            effect.remove(testEntity);
            assertEquals(defenseAfterApply - INITIAL_EXTRA_DEFENSE, testEntity.getDefense());
        }
        
        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0})
        void testRemoveWithVariousDefenseValues(double defenseAmount) {
            PermanentExtraDefenseEffect testEffect = new PermanentExtraDefenseEffect(defenseAmount);
            testEffect.apply(testEntity);
            double defenseAfterApply = testEntity.getDefense();
            testEffect.remove(testEntity);
            assertEquals((int) (defenseAfterApply - defenseAmount), testEntity.getDefense());
        }

    }
    
    @Nested
    class MultipleApplications {
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 5, 10})
        void testMultipleApplyAndRemove(int times) {
            double initialDefense = testEntity.getDefense();
            
            // Multiple applications
            for (int i = 0; i < times; i++) {
                effect.apply(testEntity);
            }
            assertEquals(initialDefense + (INITIAL_EXTRA_DEFENSE * times), testEntity.getDefense());
            
            // Multiple removals
            for (int i = 0; i < times; i++) {
                effect.remove(testEntity);
            }
            assertEquals(initialDefense, testEntity.getDefense());
        }
    }
    
    @Nested
    class Stacking {
        @Test
        void testStackWithSameEffect() {
            PermanentExtraDefenseEffect otherEffect = new PermanentExtraDefenseEffect(INITIAL_EXTRA_DEFENSE);
            effect.stack(otherEffect);
            effect.apply(testEntity);
            assertEquals(INITIAL_EXTRA_DEFENSE + testEntity.getDefense() - INITIAL_EXTRA_DEFENSE, 
                       testEntity.getDefense());
        }
        
        @Test
        void testStackWithDifferentEffect() {
            EffectInTurns differentEffect = new EffectInTurns("Different Effect", 5) {
                @Override
                public void apply(Entity target) {}
                @Override
                public void remove(Entity target) {}
                @Override
                public String getFormattedEffectInfo() { return ""; }
                @Override
                public void stack(EffectInTurns other) {}
            };
            
            effect.stack(differentEffect);
            effect.apply(testEntity);
            assertEquals(INITIAL_EXTRA_DEFENSE + testEntity.getDefense() - INITIAL_EXTRA_DEFENSE, 
                       testEntity.getDefense());
        }
        
        @Test
        void testStackWithNull() {
            effect.stack(null);
            effect.apply(testEntity);
            assertEquals(INITIAL_EXTRA_DEFENSE + testEntity.getDefense() - INITIAL_EXTRA_DEFENSE, 
                       testEntity.getDefense());
        }
    }
    
    @Nested
    class Formatting {
        @ParameterizedTest
        @CsvSource({
            "10.0, 'Extra Defense (Permanent) (10.00 defense)'",
            "15.5, 'Extra Defense (Permanent) (15.50 defense)'",
            "0.0, 'Extra Defense (Permanent) (0.00 defense)'",
            "-5.0, 'Extra Defense (Permanent) (-5.00 defense)'",
            "100.0, 'Extra Defense (Permanent) (100.00 defense)'"
        })
        void testGetFormattedEffectInfo(double defense, String expectedOutput) {
            PermanentExtraDefenseEffect testEffect = new PermanentExtraDefenseEffect(defense);
            assertEquals(expectedOutput, testEffect.getFormattedEffectInfo());
        }
    }
    
    @Nested
    class Equality {
        @Test
        void testEqualsWithSameValues() {
            PermanentExtraDefenseEffect otherEffect = new PermanentExtraDefenseEffect(INITIAL_EXTRA_DEFENSE);
            assertTrue(effect.equals(otherEffect));
        }
        
        @Test
        void testEqualsWithDifferentDefense() {
            PermanentExtraDefenseEffect otherEffect = new PermanentExtraDefenseEffect(INITIAL_EXTRA_DEFENSE + 5.0);
            assertTrue(effect.equals(otherEffect));
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