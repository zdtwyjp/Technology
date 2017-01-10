package org.tech.java.puzzlers.puzzlers_7_library.puzzle_57;

import java.util.*;

public class Name {
	private String first, last;

	public Name(String first, String last) {
		this.first = first;
		this.last = last;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}

	public static void main(String[] args) {
		Set<Name> s = new HashSet<Name>();
		s.add(new Name("Mickey", "Mouse"));
		System.out.println(s.contains(new Name("Mickey", "Mouse")));
	}
}
