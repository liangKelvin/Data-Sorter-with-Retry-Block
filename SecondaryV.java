import java.util.*;

public class SecondaryV extends Thread {

	ArrayList<Integer> values = new ArrayList<Integer>();
	int time;
	double failureRate;
	String outFile;
	String inFile;
	int length;
	int mem;
	boolean pass;
	Adjudicator adj = new Adjudicator();

	public SecondaryV(String fileIn, String fileOut, double failure) {
		
		this.values = FileRW.readFile(fileIn);
		this.failureRate = failure;
		this.outFile = fileOut;
		this.inFile = fileIn;
		this.length = values.size();
		this.pass = false;
	}

	public boolean isPass() {
		return this.pass;
	}

	@Override
	public void run() {

		try{

			int[] tempArray = new int[values.size()];

			// convert to int[]
			for(int i = 0; i < values.size(); i++) {
				tempArray[i] = values.get(i);
			}

			// time to sort
			InsertionSort sorter = new InsertionSort();
			System.loadLibrary("InsertionSort");
			int[] tempArray2 = sorter.insertionSort(tempArray);

			// convert to ArrayList and parse last element as mem acc
			ArrayList<Integer> actualValues = new ArrayList<Integer>();
			for(int i = 0; i < tempArray2.length; i++) {
				if(i == tempArray2.length - 1) {
					this.mem = tempArray2[i];
				}
				if(i != tempArray2.length -1) {
					actualValues.add(tempArray2[i]);
				}
			}

			// Hazards 
			Random rand = new Random();
			double n = rand.nextDouble();
			double hazard = this.failureRate * mem;

			System.out.print("random: ");
			System.out.println(n);
			System.out.print("hazard: ");
			System.out.println(hazard);
			System.out.print("memcount: ");
			System.out.println(this.mem);
			// check for error
			if(n >= 0.5 && n <= 0.5 + hazard) {
				System.out.println("transient hardware fault");
				this.pass = false; 
				return;
			}

			// else no error
			FileRW.writeFile(this.outFile, actualValues);

			// adjudicator
			if(adj.AcceptanceTest(inFile, outFile)) {
				this.pass = true;
			}

		}catch(ThreadDeath td) {
			System.out.println("Primary has timed out");
			this.pass = false;
			throw new ThreadDeath();
		}
	}
}