package org.megion.xapp.core.bridges;

import java.lang.reflect.Method;

public class Person implements Comparable<Person> {

	private int value;

	public static void main(String[] args) {
		for (Method m : Person.class.getMethods()) {
			if (m.getName().equals("compareTo")) {
				System.out.println(m.toGenericString());
			}
		}
	}

	@Override
	public int compareTo(Person o) {
		return (value < o.value) ? -1 : (value == o.value) ? 0 : 1;
	}

}
