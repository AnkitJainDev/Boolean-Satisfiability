# Boolean-Satisfiability
Java program to solve 3-CNF Satisfiability problems.

For example, if we have a problem with one clause, and three variables (we'll call A, B, and C), the problem might be "(A + !B + !C)." The equation evaluates to true if A is true, or if B or C are false.

The input might look like this.
  
  3 1
  
  1 -2 -3
  
The first variable, A, is true (and positive). The next two are negated, (and are negative).
This program reads the input file, and try to find a setting of the variables that makes the entire equation evaluate to true.
