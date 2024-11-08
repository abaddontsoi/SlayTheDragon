package card;

public interface ICard extends Cloneable {
    String getName();
    String getDescription();
    ICard clone();
}
