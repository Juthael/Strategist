package com.tregouet.strategist.impl;

import java.util.Arrays;

import com.tregouet.strategist.IStrategy;

public class Strategy implements IStrategy {

	private final String name;
	private final int[] complianceVector;

	public Strategy(String name, int[] complianceVector) {
		this.name = name;
		this.complianceVector = complianceVector;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Strategy other = (Strategy) obj;
		if (!Arrays.equals(complianceVector, other.complianceVector))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public int getNbOfCriteria() {
		return complianceVector.length;
	}

	public int getRank(int criterionIdx) {
		return complianceVector[criterionIdx];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(complianceVector);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

}
