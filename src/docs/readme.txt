
Kontextfreie Grammatik der akzeptierten Formeln

	Operatoren priorität absteigend
		not
		and
		or
		if
		iff
		xor
		nand
		nor

	G_Interpretation = (Ni, Ti, Pi, Interpretation)
	
	Ni = { Interpretation, Variable-Interpretation-List, Variable, Boolean }
	
	Ti = { I, =, (, ), 0, 1, A, .., Z }
	
	Pi = {
		Interpretation					:= "I" "=" "(" [ Variable-Interpretation-List ] ")"
		Variable-Interpretation-List	:= Variable = Boolean  { "," Variable = Boolean }
		Boolean							:= "0" | "1"
		Variable						:= "A" | .. | "Z"
	}
	
	G_Formula = (N, T, P, Formula)
	
	N = { Formula, Conditional, Disjunct, Conjunct, Operand, Variable }
	
	T = { "a", "n", "d", "o", "r", "i", "f", "t", "e", "p", "x", "0", "1", "(", ")", "A", .., "Z" }
	
	P = {
		Formula							:= NandConjunct [ "nor" Formula ]
		NandConjunct 					:= XorDisjunct [ "nand" NandConjunct ]
		XorDisjunct						:= Biconditional [ "xor" XorDisjunct ]
		Biconditional					:= Conditional [ "iff" Biconditional ]
		Conditional						:= Disjunct [ "if" Conditional ]
		Disjunct						:= Conjunct [ "or" Disjunct ]
		Conjunct						:= Operand [ "and" Conjunct ]
		Operand							:= [ "not" ] ( "(" Formula ")" | Variable | Boolean)
		Variable						:= "A" | .. | "Z"
		Boolean							:= "0" | "1"
	}
	
Parsing Schema
	
	Die Formeln werden anhand eines rekursiven Abstiegs geparst. Beachten Sie,
	dass die Implikation nicht assoziativ ist, wodurch Ausdrücke der Form
	(A if B if C) mehrdeutig sind. Die spezielle bauert der Produktion
	Conditional deutet aufgrund der Rekursion die intendierte 
	Auswertungsreihenfolge (A if (B if C)) vor.
