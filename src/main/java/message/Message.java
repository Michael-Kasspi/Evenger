package message;

public interface Message<T> {
    String getId();

    Class<? extends T> getType();

    T getPayload();

}
