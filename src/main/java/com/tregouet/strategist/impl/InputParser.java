package com.tregouet.strategist.impl;

import com.google.common.base.CharMatcher;
import com.tregouet.strategist.ICriterion;
import com.tregouet.strategist.IInputParser;
import com.tregouet.strategist.IStrategy;

public class InputParser implements IInputParser {

	public static final InputParser INSTANCE = new InputParser();

	private InputParser() {
	}

	public ICriterion[] parseCriteria(String input) {
		String cleanInput = CharMatcher.whitespace().removeFrom(input);
		String[] stringArray = cleanInput.split(";");
		ICriterion[] critArray = new ICriterion[stringArray.length];
		for (int i = 0 ; i < stringArray.length ; i++) {
			critArray[i] = new Criterion(stringArray[i], i);
		}
		return critArray;
	}

	public IStrategy parseStrategy(String input) {
		String cleanInput = CharMatcher.whitespace().removeFrom(input);
		String[] stringArray = cleanInput.split("#");
		String name = stringArray[0];
		String vectorString = stringArray[1];
		String[] vectorStrings = vectorString.split(";");
		int[] vector = new int[vectorStrings.length];
		for (int i = 0 ; i < vector.length ; i++) {
			vector[i] = Integer.parseInt(vectorStrings[i]);
		}
		return new Strategy(name, vector);
	}

}
