package com.tregouet.strategist.impl;

import com.tregouet.strategist.ICriterion;

public class Criterion implements ICriterion {

	private final String name;
	private final int idx;

	public Criterion(String name, int idx) {
		this.name = name;
		this.idx = idx;
	}

	public int compareTo(ICriterion o) {
		return Integer.compare(this.getIdx(), o.getIdx());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Criterion other = (Criterion) obj;
		if (idx != other.idx)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getCriterionName() {
		return name;
	}

	public int getIdx() {
		return idx;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idx;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

}
