package com.tregouet.strategist.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.tregouet.strategist.ICriterion;
import com.tregouet.strategist.IMenu;
import com.tregouet.strategist.IRule;

public class Menu implements IMenu {

	private static final String nl = System.lineSeparator();
	private final Scanner entry = new Scanner(System.in);

	public Menu() {
		setCriteria();
	}

	private void mainMenu() {
		System.out.println(nl);
		System.out.println("1 : Enter a new strategy.");
		System.out.println("2 : print rules.");
		System.out.println(nl);
		int choice = 0;
		try {
			choice = entry.nextInt();
			entry.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid input.");
			mainMenu();
		}
		if (choice == 1)
			setStrategy();
		else if (choice == 2) {
			printRules();
		}
		else {
			System.out.println("Invalid input.");
			mainMenu();
		}
	}

	private String printCriteria(List<ICriterion> criteria) {
		StringBuffer sB = new StringBuffer();
		Iterator<ICriterion> critIte = criteria.iterator();
		int idx = 1;
		while (critIte.hasNext()) {
			sB.append(idx++ + "-" + critIte.next().getCriterionName());
			if (critIte.hasNext())
				sB.append(" ; ");
		}
		return sB.toString();
	}

	private void printRules() {
		int idx = 1;
		System.out.println("RULES FOR ACTION :");
		for (IRule rule : Strategist.INSTANCE.getRules()) {
			System.out.print("Rule " + idx++ + " : ");
			System.out.print(printCriteria(rule.getOrderedCriteria()));
			System.out.print(" => ");
			System.out.println(rule.getOptimalStrategy().getName());
		}
	}

	private void setCriteria() {
		System.out.println("*****STRATEGIST*****" + System.lineSeparator());
		System.out.println("Enter criteria separated by a semicolon.");
		String input = null;
		try {
			input = entry.nextLine();
			Strategist.INSTANCE.setCriteria(InputParser.INSTANCE.parseCriteria(input));
		}
		catch (Exception e) {
			System.out.println("Invalid input.");
			setCriteria();
		}
		setStrategy();
	}

	private void setStrategy() {
		System.out.println("Enter a strategy in the format NAME#rank;rank;rank;...");
		String input = null;
		try {
			input = entry.nextLine();
			Strategist.INSTANCE.addStrategy(InputParser.INSTANCE.parseStrategy(input));
		}
		catch (Exception e) {
			System.out.println("Invalid input.");
			if (Strategist.INSTANCE.hasNoStrategy())
				setStrategy();
			else mainMenu();
		}
		mainMenu();
	}

}
