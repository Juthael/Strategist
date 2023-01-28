package com.tregouet.strategist.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tregouet.strategist.ICriterion;
import com.tregouet.strategist.IOptimalStrategies;
import com.tregouet.strategist.IRule;
import com.tregouet.strategist.IStrategist;
import com.tregouet.strategist.IStrategy;

public class Strategist implements IStrategist {

	public static final Strategist INSTANCE = new Strategist();
	private final Set<IStrategy> strategies = new HashSet<IStrategy>();
	private ICriterion[] criteria = null;
	private final Set<IRule> rules = new HashSet<IRule>();

	private Strategist() {
	}

	public boolean addStrategy(IStrategy strategy) throws Exception {
		if (criteria != null && strategy.getNbOfCriteria() != criteria.length)
			throw new Exception("Invalid vector lenght.");
		return strategies.add(strategy);
	}

	public List<IRule> getRules() {
		if (rules.isEmpty())
			buildRules(new ArrayList<ICriterion>(), new OptimalStrategies(strategies));
		List<IRule> sortedRules = new ArrayList<IRule>(rules);
		Collections.sort(sortedRules);
		return sortedRules;
	}

	public boolean hasNoStrategy() {
		return strategies.isEmpty();
	}

	public void setCriteria(ICriterion[] criteria) {
		this.criteria = criteria;
	}

	protected Set<IRule> buildRules(List<ICriterion> incompleteList, IOptimalStrategies strategies){
		List<ICriterion> remainingCriteria = new ArrayList<ICriterion>();
		for (ICriterion criterion : criteria) {
			if (!incompleteList.contains(criterion))
				remainingCriteria.add(criterion);
		}
		for (ICriterion criterion : remainingCriteria) {
			IOptimalStrategies optStrats = new OptimalStrategies(strategies);
			List<ICriterion> extendedCritList = new ArrayList<ICriterion>(incompleteList);
			extendedCritList.add(criterion);
			optStrats.removeNonOptimal(criterion);
			IStrategy optimalStrat = optStrats.returnUniqueOptimalStrategyOrNull();
			if (optimalStrat != null)
				rules.add(new Rule(extendedCritList, optimalStrat));
			else rules.addAll(buildRules(extendedCritList, optStrats));
		}
		return rules;
	}

}
