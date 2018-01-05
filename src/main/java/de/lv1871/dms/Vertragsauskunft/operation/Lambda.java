package de.lv1871.dms.Vertragsauskunft.operation;

import java.util.function.Predicate;

public class Lambda {

	public static <T> Predicate<T> notNull() {
		return value -> value != null;
	}
}
