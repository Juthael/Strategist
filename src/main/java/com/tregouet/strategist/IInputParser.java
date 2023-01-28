package com.tregouet.strategist;

import java.io.IOException;

public interface IInputParser {

	ICriterion[] parseCriteria(String input);

	IStrategy parseStrategy(String input) throws IOException;

}
