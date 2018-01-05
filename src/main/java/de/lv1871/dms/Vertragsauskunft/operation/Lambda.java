package de.lv1871.dms.Vertragsauskunft.operation;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda {

	public static <T> Function<Optional<T>, T> orThrow(Supplier<String> exceptionMessageSupplier) {
		return (optional) -> optional.orElseThrow(() -> new RuntimeException(exceptionMessageSupplier.get()));
	}

	public static <T> Function<Optional<T>, T> orNull() {
		return optional -> optional.orElse(null);
	}

	public static <T> Predicate<T> notNull() {
		return value -> value != null;
	}

	public static <T, R> Predicate<T> notEqual(Function<T, R> fieldMapper, Supplier<R> testValueSupplier) {
		return value -> !fieldMapper.apply(value).equals(testValueSupplier.get());
	}

	public static <T, R> Predicate<T> equal(Function<T, R> fieldMapper, Supplier<R> testValueSupplier) {
		return value -> fieldMapper.apply(value).equals(testValueSupplier.get());
	}

	public static <F, T> Predicate<F> isPresent(Function<F, T> supplier) {
		return (value) -> notNull().test(supplier.apply(value));
	}

	public static <T, F> Function<T, T> doIf(Predicate<T> condition, Function<T, T> transformator) {
		// @formatter:off
		return value -> Optional.of(value)
							.filter(condition)
							.map(transformator)
							.orElse(value);
		// @formatter:on
	}

	public static <T, F> Function<T, T> getTransformSet(BiConsumer<T, F> setter,
			BiFunction<T, Function<T, F>, F> transformator, Function<T, F> supplier) {
		return value -> {
			setter.accept(value, transformator.apply(value, supplier));
			return value;
		};
	}

}
