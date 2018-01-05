package de.lv1871.dms.Vertragsauskunft.operation;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Calculator {

	public static <T> BiFunction<T, Function<T, Double>, Double> multiplyWith12() {
		return (value, supplier) -> supplier.apply(value) * 12;
	}
}
