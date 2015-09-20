import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * Class BooleanSAT : Class contains data structures and functions to check
 * 						if boolean solution exist for given 3-NF
 */ 
public class BooleanSAT {
	Scanner scan = new Scanner(System.in);
	private int[][] clauses;
	private int nVar=0, nClauses=0, values[];
	private boolean checkTrue[];
	Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
	
	/**
	 * 	Method to read input from a file/console and store it in 2*2 matrix
	 */ 
	public void readInput(){
		nVar = scan.nextInt();
		nClauses = scan.nextInt();
		checkTrue = new boolean[nClauses];
		values = new int[nVar];
		initializeValues();
		clauses= new int[nClauses][3];
		for(int i = 0; i<nClauses;i++) {
			for(int j=0;j<3;j++) {
				clauses[i][j] = scan.nextInt();
				addEntry(clauses[i][j], i);				
			}
		}	
	}
	
	/**
	 * 	Method to initialize values array to -1. This array will contain boolean solution for given 3-CNF
	 */ 
	public void initializeValues(){
		for(int i=0; i< nVar; i++)
			values[i] = -1;
	}
	
	/**
	 * 	Method to set boolean list values to true for all those next clauses which contains
	 *  the current variables. So next time when clause contains this variable, no need to check that 
	 *  clause as the value of that clause would be anyways 1. This reduced large number of comparisons  
	 */
	public void setClauseTrue(int var){
			List<Integer> list = map.get(var);
			Iterator<Integer> itr = list.iterator();
			while(itr.hasNext()){
				int i = itr.next();
				checkTrue[i] = true;				
			}
	}

	/**
	 * 	Method will iterate through all the clauses to check for the solution. If the clause is already
	 *  true by some previous variable, no need to check it again.
	 */ 
	public void booleanSatisfiability(){
		boolean flag = false;
		for(int i=0; i<nClauses; i++){
			flag=false;
			if(checkTrue[i])
					continue;
			for(int j=0; j<3; j++){
				int var = clauses[i][j];
				if(var < 0){
					if(values[(0-var)-1] != -1)					
						continue;
					values[(0-var)-1] = 0;
					flag = true;
					setClauseTrue(var);				// Set all the clauses to true which contains this literal
					break;
				}
				else {
					if(values[var-1] != -1)
						continue;
					values[var-1]=1;
					flag = true;
					setClauseTrue(var);				// Set all the clauses to true which contains this literal
					break;
				}											
			}
			if(!flag){								//If assigned values doesn't satisfy clauses, print not satisfiable.
				System.out.println("not satisfiable");
				System.exit(1);
			}
		}
		for(int i=0;i<nVar;i++){ 					//Print the solution. If value is -1, that value could be 0 or 1.
			if(values[i] == -1)
				values[i]=0;
			System.out.println(values[i]);
		}
	}
	
	/**
	 * 	Method to add the entries in HashMap of each variable and the clauses number which contains this variable.
	 *  HashMap<literal, List of Clauses number which contains this literal> 
	 */ 
	public void addEntry(Integer clause, int val){
		if(map.containsKey(clause)){
			map.get(clause).add(val);
		}else {
			List<Integer> list = new ArrayList<Integer>();
			list.add(val);
			map.put(clause, list);
		}		
	}
	
	/**
	 * 	Driver code for the program. 
	 */ 
	public static void main(String args[]){			
		BooleanSAT d = new BooleanSAT();
		d.readInput();
		d.booleanSatisfiability();
	}
}
