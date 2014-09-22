package at.skagen.apps.sat.ui.cli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.ui.cli.factories.BooleanEvaluationFactory;
import at.skagen.apps.sat.ui.cli.factories.CnfEvaluationFactory;
import at.skagen.apps.sat.ui.cli.factories.DnfEvaluationFactory;
import at.skagen.apps.sat.ui.cli.factories.EvaluationFactory;
import at.skagen.apps.sat.ui.cli.factories.FactoryException;
import at.skagen.apps.sat.ui.cli.factories.TableauEvaluationFactory;

public class Sat {

	private static Map<String, EvaluationFactory> factories = new HashMap<String, EvaluationFactory>();
	static {
		factories.put("evaluate", new BooleanEvaluationFactory());
		factories.put("tableau",  new TableauEvaluationFactory());
		factories.put("cnf",      new CnfEvaluationFactory());
		factories.put("dnf",      new DnfEvaluationFactory());
	}
	
	public static void main(String[] args) {
		if (args.length >= 1) {
			String command = args[0];
			String[] arguments = args.length < 2 ? null : Arrays.copyOfRange(args, 1, args.length);
			try {
				if (factories.containsKey(command)) {
					System.out.println(factories.get(command).create(arguments).evaluate());
				} else {
					System.out.println("invalid command : " + command);
				}
			} catch (FactoryException e) {
				System.out.println("invalid command : " + e.getMessage());
			} catch (EvaluatorException e) {
				System.out.println("error : " + e.getMessage());
			}
		} else {
			System.out.println(usage());
		}
	}
	
	private static String usage() {
		
		String usage = "";
		
		usage += "sat evaluate <formula> <interpretation>" + "\n"
				+ "\t" + "evaluates a formula for an interpretation" + "\n\n";
		usage += "sat evaluate formal <formula> <interpretation>" + "\n"
				+ "\t" + "shows the formal evaluation of a formula for an interpretation" + "\n\n";
		usage += "sat tableau <formula>" + "\n"
				+ "\t" + "uses tableau calculus to determine satisfiability of a formula" + "\n\n";
		usage += "sat cnf <formula>" + "\n"
				+ "\t" + "calculates the formulas conjunctive normal form" + "\n\n";
		usage += "sat dnf <formula>" + "\n" 
				+ "\t" + "calculates the formulas conjunctive normal form" + "\n\n";
		usage += "Examples :" + "\n\n"
				+ "\t" + "sat evaluate \"A and not B\" \"I=(A=1,B=0)\"" + "\n"
				+ "\t" + "sat tableau \"A nand B\"" + "\n"
				+ "\t" + "sat dnf \"(A if 0) xor (A iff C)\"";
		
		return usage;
	}
}
