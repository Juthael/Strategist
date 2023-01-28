package com.tregouet.strategist.impl;

import java.util.Iterator;
import java.util.List;

import com.tregouet.strategist.ICriterion;
import com.tregouet.strategist.IRule;
import com.tregouet.strategist.IStrategy;

public class Rule implements IRule {

	private final List<ICriterion> orderedCriteria;
	private final IStrategy optimalStrategy;

	public Rule(List<ICriterion> orderedCriteria, IStrategy optimalStrategy) {
		this.orderedCriteria = orderedCriteria;
		this.optimalStrategy = optimalStrategy;
	}

	public int compareTo(IRule o) {
		if (this.equals(o))
			return 0;
		Iterator<ICriterion> thisIte = orderedCriteria.iterator();
		Iterator<ICriterion> otherIte = o.getOrderedCriteria().iterator();
		while (thisIte.hasNext()) {
			if (otherIte.hasNext()) {
				ICriterion othCrit = otherIte.next();
				ICriterion thisCriterion = thisIte.next();
				int comparison = thisCriterion.compareTo(othCrit);
				if (comparison != 0)
					return comparison;
			}
			else return 1;
		}
		return -1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Rule other = (Rule) obj;
		if (optimalStrategy == null) {
			if (other.optimalStrategy != null)
				return false;
		} else if (!optimalStrategy.equals(other.optimalStrategy))
			return false;
		if (orderedCriteria == null) {
			if (other.orderedCriteria != null)
				return false;
		} else if (!orderedCriteria.equals(other.orderedCriteria))
			return false;
		return true;
	}

	public IStrategy getOptimalStrategy() {
		return optimalStrategy;
	}

	public List<ICriterion> getOrderedCriteria() {
		return orderedCriteria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((optimalStrategy == null) ? 0 : optimalStrategy.hashCode());
		result = prime * result + ((orderedCriteria == null) ? 0 : orderedCriteria.hashCode());
		return result;
	}

}
