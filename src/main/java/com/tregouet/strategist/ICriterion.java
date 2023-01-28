package com.tregouet.strategist;

public interface ICriterion extends Comparable<ICriterion> {

	boolean equals(Object o);

	String getCriterionName();

	int getIdx();

	int hashCode();

}
