package entity.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import effect.EffectInTurns;
import entity.Entity;
import entity.EntityStatus;

import org.junit.jupiter.api.Nested;

class EntityStatusTestV2 {
    private EntityStatus entityStatus;
    private static final double INITIAL_MAX_HEALTH = 100.0;
    private static final int INITIAL_DEFENSE = 10;
    private static final int INITIAL_STRENGTH = 20;
    private static final double DELTA = 0.001;

    @BeforeEach
    void setUp() {
        entityStatus = new EntityStatus(INITIAL_MAX_HEALTH, INITIAL_DEFENSE, INITIAL_STRENGTH);
    }

    @Nested
    class InitializationTests {
        @Test
        void shouldInitializeWithCorrectValues() {
            assertEquals(INITIAL_MAX_HEALTH, entityStatus.getMaxHealth(), DELTA);
            assertEquals(INITIAL_MAX_HEALTH, entityStatus.getHealth(), DELTA);
            assertEquals(INITIAL_DEFENSE, entityStatus.getDefense());
            assertEquals(INITIAL_STRENGTH, entityStatus.getStrength());
            assertTrue(entityStatus.getEffectsInRounds().isEmpty());
            assertTrue(entityStatus.getPermanentEffectsInRounds().isEmpty());
        }
    }

    @Nested
    class HealthManagementTests {
        @Test
        void shouldTakeDamageCorrectly() {
            double damage = 30.0;
            entityStatus.takeDamage(damage);
            assertEquals(INITIAL_MAX_HEALTH - damage, entityStatus.getHealth(), DELTA);
        }

        @Test
        void shouldHealCorrectly() {
            entityStatus.takeDamage(50.0);
            entityStatus.heal(20.0);
            assertEquals(70.0, entityStatus.getHealth(), DELTA);
        }

        @Test
        void shouldNotHealBeyondMaxHealth() {
            entityStatus.takeDamage(20.0);
            entityStatus.heal(30.0);
            assertEquals(INITIAL_MAX_HEALTH, entityStatus.getHealth(), DELTA);
        }

        @Test
        void shouldIncreaseMaxHealthCorrectly() {
            double increase = 20.0;
            entityStatus.increaseMaxHealth(increase);
            assertEquals(INITIAL_MAX_HEALTH + increase, entityStatus.getMaxHealth(), DELTA);
        }
    }

    @Nested
    class DefenseAndStrengthTests {
        @Test
        void shouldIncreaseDefenseCorrectly() {
            double defenseIncrease = 5.0;
            entityStatus.increaseDefense(defenseIncrease);
            assertEquals(INITIAL_DEFENSE + defenseIncrease, entityStatus.getDefense(), DELTA);
        }

        @Test
        void shouldIncreaseStrengthCorrectly() {
            double strengthIncrease = 5.0;
            entityStatus.increaseStrength(strengthIncrease);
            assertEquals(INITIAL_STRENGTH + strengthIncrease, entityStatus.getStrength(), DELTA);
        }
    }

    @Nested
    class StatusTests {
        @Test
        void shouldBeAliveWithPositiveHealth() {
            assertTrue(entityStatus.isAlive());
        }

        @Test
        void shouldNotBeAliveWithZeroOrNegativeHealth() {
            entityStatus.takeDamage(INITIAL_MAX_HEALTH);
            assertFalse(entityStatus.isAlive());

            entityStatus.takeDamage(10);
            assertFalse(entityStatus.isAlive());
        }
    }

    @Nested
    class EffectTests {
        private TestEffect testEffect;

        class TestEffect extends EffectInTurns {
            private int applyCalls = 0;
            private int removeCalls = 0;

            public TestEffect(int duration) {
                super("", duration);
            }

            @Override
            public void apply(Entity entity) {
                applyCalls++;
            }

            @Override
            public void remove(Entity entity) {
                removeCalls++;
            }

            @Override
            public void stack(EffectInTurns other) {
                // Implementation for testing
            }

            public int getApplyCalls() {
                return applyCalls;
            }

            public int getRemoveCalls() {
                return removeCalls;
            }

			@Override
			public String getFormattedEffectInfo() {
				// TODO Auto-generated method stub
				return null;
			}
        }

        @BeforeEach
        void setUpEffect() {
            testEffect = new TestEffect(3);
        }

        @Test
        void shouldAddEffect() {
            entityStatus.addEffect(testEffect);
            assertEquals(1, entityStatus.getEffectsInRounds().size());
        }

        @Test
        void shouldStackSameTypeEffects() {
            TestEffect anotherEffect = new TestEffect(2);
            entityStatus.addEffect(testEffect);
            entityStatus.addEffect(anotherEffect);
            assertEquals(1, entityStatus.getEffectsInRounds().size());
        }

        @Test
        void shouldAddPermanentEffect() {
            Entity mockEntity = new Entity(0, 0, 0, null) {
                @Override
                public void takeDamage(double damage) {}
                // Implement other required methods

				@Override
				public String getName() {
					// TODO Auto-generated method stub
					return null;
				}
            };

            entityStatus.addPermanentEffectInRounds(testEffect, mockEntity);
            assertEquals(1, entityStatus.getPermanentEffectsInRounds().size());
            assertEquals(1, testEffect.getApplyCalls());
        }
    }

    @Nested
    class CopyTests {
        @Test
        void shouldCreateCorrectCopy() {
            EntityStatus copy = entityStatus.getStatusCopy();
            
            assertEquals(entityStatus.getMaxHealth(), copy.getMaxHealth(), DELTA);
            assertEquals(entityStatus.getHealth(), copy.getHealth(), DELTA);
            assertEquals(entityStatus.getDefense(), copy.getDefense());
            assertEquals(entityStatus.getStrength(), copy.getStrength());
            assertEquals(entityStatus.getEffectsInRounds(), copy.getEffectsInRounds());
            assertEquals(entityStatus.getPermanentEffectsInRounds(), copy.getPermanentEffectsInRounds());
        }
    }

    @Nested
    class BuffTests {
        @Test
        void shouldApplyAttackBuff() {
            double multiplier = 1.5;
            entityStatus.attackbuff(multiplier);
            // Add assertions based on how attack buff affects the entity
        }

        @Test
        void shouldApplyDefenseBuff() {
            double multiplier = 1.5;
            entityStatus.defensebuff(multiplier);
            // Add assertions based on how defense buff affects the entity
        }
    }
}