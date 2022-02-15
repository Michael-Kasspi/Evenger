package message.api;

public interface MessageBusFactory<B extends MessageBus<?,?>> {
     B get(String type);
}
