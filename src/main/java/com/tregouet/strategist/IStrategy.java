package com.tregouet.strategist;

public interface IStrategy {

	boolean equals(Object o);

	String getName();

	int getNbOfCriteria();

	int getRank(int criterionIdx);

	int hashCode();

}
