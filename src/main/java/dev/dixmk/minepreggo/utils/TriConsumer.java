package dev.dixmk.minepreggo.utils;

@FunctionalInterface
public interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v);

    default TriConsumer<T, U, V> andThen(TriConsumer<? super T, ? super U, ? super V> after) {
        if (after == null) throw new NullPointerException("After consumer is null");
        return (t, u, v) -> {
            accept(t, u, v);
            after.accept(t, u, v);
        };
    }
}
