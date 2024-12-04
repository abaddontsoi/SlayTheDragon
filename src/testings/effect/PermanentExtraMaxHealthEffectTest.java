package testings.effect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import effect.PermanentExtraDefenseEffect;
import effect.PermanentExtraMaxHealthEffect;
import effect.EffectInTurns;
import entity.Entity;

class PermanentExtraMaxHealthEffectTest {
    
    PermanentExtraMaxHealthEffect effect;
    Entity entity;
    
    private static final double INITIAL_EXTRA_MAX_HEALTH = 20;
    private static final String INITIAL_ENTITY_NAME = "Entity";
    
    @BeforeEach
    void setUp() throws Exception {
        effect = new PermanentExtraMaxHealthEffect(INITIAL_EXTRA_MAX_HEALTH);
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
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0})
        void testConstructorWithVariousDefenseValues(double newMaxHealth) {
            PermanentExtraMaxHealthEffect testEffect = new PermanentExtraMaxHealthEffect(newMaxHealth);
            assertEquals("Extra Max Health (Permanent)", testEffect.getName());
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
            double initialMaxHealth = entity.getMaxHealth();
            double initialCurrentHealth = entity.getHealth();
            effect.apply(entity);
            assertEquals(initialMaxHealth + INITIAL_EXTRA_MAX_HEALTH, entity.getMaxHealth());
            assertEquals(initialCurrentHealth + INITIAL_EXTRA_MAX_HEALTH, entity.getHealth());
        }
        
        @ParameterizedTest
        @ValueSource(doubles = {10.0, 15.5, 0.0, -5.0, 100.0})
        void testApplyWithVariousHealthValues(double healthAmount) {
            PermanentExtraMaxHealthEffect testEffect = new PermanentExtraMaxHealthEffect(healthAmount);
            double initialMaxHealth = entity.getMaxHealth();
            double initialCurrentHealth = entity.getHealth();
            testEffect.apply(entity);
            assertEquals(initialMaxHealth + healthAmount, entity.getMaxHealth());
            assertEquals(Math.min(initialCurrentHealth + healthAmount, entity.getMaxHealth()), 
                        entity.getHealth());
        }

        
        @Test
        void testApplyWithLowHealth() {
            entity.takeDamage(50);
            double initialCurrentHealth = entity.getHealth();
            effect.apply(entity);
            assertEquals(initialCurrentHealth + INITIAL_EXTRA_MAX_HEALTH, entity.getHealth());
        }
        
        @Test
        void testMultipleApplications() {
            double initialMaxHealth = entity.getMaxHealth();
            effect.apply(entity);
            effect.apply(entity);
            assertEquals(initialMaxHealth + (INITIAL_EXTRA_MAX_HEALTH * 2), entity.getMaxHealth());
        }
    }
    
    @Nested
    class Removal {
        @Test
        void testBasicRemove() {
            effect.apply(entity);
            double maxHealthAfterApply = entity.getMaxHealth();
            effect.remove(entity);
            assertEquals(maxHealthAfterApply - INITIAL_EXTRA_MAX_HEALTH, entity.getMaxHealth());
        }
        
        @Test
        void testRemoveWithoutApply() {
            double initialMaxHealth = entity.getMaxHealth();
            effect.remove(entity);
            assertEquals(initialMaxHealth - INITIAL_EXTRA_MAX_HEALTH, entity.getMaxHealth());
        }

        
        @Test
        void testMultipleRemovals() {
            effect.apply(entity);
            double initialMaxHealth = entity.getMaxHealth();
            effect.remove(entity);
            effect.remove(entity);
            assertEquals(initialMaxHealth - (INITIAL_EXTRA_MAX_HEALTH * 2), entity.getMaxHealth());
        }
    }
    
    @Nested
    class Stacking {
        @Test
        void testStackWithSameEffect() {
            PermanentExtraMaxHealthEffect otherEffect = new PermanentExtraMaxHealthEffect(INITIAL_EXTRA_MAX_HEALTH);
            effect.stack(otherEffect);
            effect.apply(entity);
            assertEquals(INITIAL_EXTRA_MAX_HEALTH, entity.getMaxHealth() - 100);
        }
        
        @Test
        void testStackWithNull() {
            effect.stack(null);
            effect.apply(entity);
            assertEquals(INITIAL_EXTRA_MAX_HEALTH, entity.getMaxHealth() - 100);
        }
    }
    
    @Nested
    class Formatting {
        @ParameterizedTest
        @CsvSource({
            "10.0, 'Extra Max Health (Permanent) (10.00 max health)'",
            "15.5, 'Extra Max Health (Permanent) (15.50 max health)'",
            "0.0, 'Extra Max Health (Permanent) (0.00 max health)'",
            "-5.0, 'Extra Max Health (Permanent) (-5.00 max health)'",
            "100.0, 'Extra Max Health (Permanent) (100.00 max health)'"
        })
        void testGetFormattedEffectInfo(double health, String expectedOutput) {
            PermanentExtraMaxHealthEffect testEffect = new PermanentExtraMaxHealthEffect(health);
            assertEquals(expectedOutput, testEffect.getFormattedEffectInfo());
        }
    }

    @Test
    void testGetFormattedEffectInfo() {
        String expected = String.format("%s (%.2f max health)", "Extra Max Health (Permanent)", INITIAL_EXTRA_MAX_HEALTH);
        assertEquals(expected, effect.getFormattedEffectInfo());
    }
}