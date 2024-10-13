package effect;

public class ConcretePermanentEffectFactory implements IEffectFactory {

	@Override
	public IEffect createEffect(String effectType) {
		 switch (effectType) {
         case "PermanentExtraMaxHealthEffect":
             return new PermanentExtraMaxHealthEffect(60);
         case "PermanentExtraDefenseEffect":
             return new PermanentExtraDefenseEffect(20);
         // Add more cases for different effects
         default:
             throw new IllegalArgumentException("Unknown effect type: " + effectType);
		 }
	}

}
