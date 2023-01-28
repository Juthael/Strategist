package com.tregouet.strategist;

import java.util.List;

public interface IStrategist {

	boolean addStrategy(IStrategy strategy) throws Exception;

	List<IRule> getRules();

	boolean hasNoStrategy();

	void setCriteria(ICriterion[] criteria);

}
