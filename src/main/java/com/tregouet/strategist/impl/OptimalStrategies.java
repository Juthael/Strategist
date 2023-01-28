package com.tregouet.strategist.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.tregouet.strategist.ICriterion;
import com.tregouet.strategist.IOptimalStrategies;
import com.tregouet.strategist.IStrategy;

public class OptimalStrategies implements IOptimalStrategies {

	private final Set<IStrategy> strategies;

	public OptimalStrategies(IOptimalStrategies strategies) {
		this.strategies = new HashSet<IStrategy>(strategies.getOptimalStrategies());
	}

	public OptimalStrategies(Set<IStrategy> strategies) {
		this.strategies = strategies;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		OptimalStrategies other = (OptimalStrategies) obj;
		if (strategies == null) {
			if (other.strategies != null)
				return false;
		} else if (!strategies.equals(other.strategies))
			return false;
		return true;
	}

	public Set<IStrategy> getOptimalStrategies(){
		return strategies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((strategies == null) ? 0 : strategies.hashCode());
		return result;
	}

	public void removeNonOptimal(ICriterion criterion) {
		int criterionIdx = criterion.getIdx();
		Set<IStrategy> optimalStrategies = new HashSet<IStrategy>();
		Iterator<IStrategy> stratIte = strategies.iterator();
		IStrategy firstStrat = stratIte.next();
		optimalStrategies.add(firstStrat);
		int optimalRank = firstStrat.getRank(criterionIdx);
		while (stratIte.hasNext()) {
			IStrategy nextStrat = stratIte.next();
			int rank = nextStrat.getRank(criterionIdx);
			if (rank <= optimalRank) {
				if (rank < optimalRank) {
					optimalStrategies.clear();
					optimalRank = rank;
				}
				optimalStrategies.add(nextStrat);
			}
		}
		strategies.retainAll(optimalStrategies);
	}

	public IStrategy returnUniqueOptimalStrategyOrNull() {
		if (strategies.size() == 1)
			return strategies.iterator().next();
		else return null;
	}

}
