package message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class AbstractInMemoryMessageRepository<T extends Message<?>> implements MessageRepository<T> {
    private final BlockingQueue<T> repository = new LinkedBlockingQueue<>();

    @Override
    public boolean add(T message) {
        return repository.offer(message);
    }

    @Override
    public T next() {
        try { return repository.take(); }
        catch (InterruptedException ignored) { return null; }
    }

    @Override
    public T peek() {
        return repository.peek();
    }
}
