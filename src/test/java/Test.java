import command.api.CommandBus;
import command.impl.BaseCommandBusFactory;
import command.impl.BasicCommandMessage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Deprecated
public class Test {

    private static final List<String> results = new ArrayList<>();
    private static boolean passed = true;

    final static List<UnitTest<?>> tests = new ArrayList<>();

    static {
        Stream.of(Test.class.getMethods()).filter(m -> m.getName().contains("test")).forEachOrdered(t -> {
            try {
                tests.add((UnitTest<?>) t.invoke(null));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        tests.forEach(test -> {
            results.add(test.test().toString());
            if (!test.pass) passed = false;
        });
        results.forEach(System.out::println);
        System.out.println(passed ? results.isEmpty() ? "No tests" : "Test passed" : "Test failed");
    }

    public static UnitTest<?> test1() {
        return new UnitTest<>("Activate bus > check status > expected: true", () -> {
            final CommandBus bus = new BaseCommandBusFactory().get("Simple");
            try {
                bus.init();
                return bus.isActive();
            } finally {
                bus.close();
            }
        }, r -> r);
    }

    public static UnitTest<?> test2() {
        return new UnitTest<>("Activate bus > check status > expected: false", () -> {
            final CommandBus bus = new BaseCommandBusFactory().get("Simple");
            bus.init();
            bus.close();
            return bus.isActive();
        }, r -> !r);
    }

    public static UnitTest<?> test3() {
        return new UnitTest<>("Activate bus > close bus > expected: Exception", () -> {
            final CommandBus bus = new BaseCommandBusFactory().get("Simple");
            bus.init();
            bus.close();
            bus.getDispatcher();
            return null;
        }, IllegalStateException.class);
    }

    public static UnitTest<?> test4() {
        return new UnitTest<>("Test AbstractMessageBus inheritance", () -> {
            final CommandBus bus = new BaseCommandBusFactory().get("Simple");
            bus.init();
            final boolean active = bus.isActive();
            bus.getDispatcher();
            bus.getHandlerStore();
            bus.close();
            final boolean inactive = bus.isActive();
            return active && !inactive;
        }, c -> c);
    }

    public static UnitTest<?> test5() {
        return new UnitTest<>("Test command registration and handling", () -> {
            final CommandBus bus = new BaseCommandBusFactory().get("Simple");
            bus.init();
            bus.getHandlerStore().register(EmptyTestCommand.class, cm -> {
                System.out.println(cm.getId());
            });
            bus.getDispatcher().dispatch(new BasicCommandMessage(UUID.randomUUID().toString(),EmptyTestCommand.class, new EmptyTestCommand()));
            bus.close();
            return true;
        }, c -> c);
    }

    static class UnitTest<R> {
        final String name;
        final Supplier<R> test;
        final Predicate<R> condition;
        String result;
        Class<? extends Throwable> ex;
        boolean pass;

        UnitTest(String name, Supplier<R> test, Class<? extends Throwable> ex) {
            this.name = name;
            this.test = test;
            this.ex = ex;
            this.condition = c -> false;
        }

        public UnitTest(String name, Supplier<R> test, Predicate<R> condition) {
            this.name = name;
            this.test = test;
            this.condition = condition;
        }

        UnitTest<?> test() {
            try {
                R r = test.get();
                pass = condition.test(r);
                result = r.toString();
            } catch (Throwable ex) {
                pass = ex.getClass().equals(this.ex);
                result = ex.toString();
                if (!pass) {
                    ex.printStackTrace();
                }
            }
            return this;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("name: ").append(name);
            sb.append(", result: ").append(result);
            sb.append(", pass: ").append(pass);
            return sb.toString();
        }
    }
}
