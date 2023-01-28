package com.tregouet.strategist;

import java.util.Set;

public interface IOptimalStrategies {

	boolean equals(Object o);

	Set<IStrategy> getOptimalStrategies();

	int hashCode();

	void removeNonOptimal(ICriterion criterion);

	IStrategy returnUniqueOptimalStrategyOrNull();

}
