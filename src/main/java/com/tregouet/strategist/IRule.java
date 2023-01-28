package com.tregouet.strategist;

import java.util.List;

public interface IRule extends Comparable<IRule> {

	boolean equals(Object o);

	IStrategy getOptimalStrategy();

	List<ICriterion> getOrderedCriteria();

	int hashCode();

}
