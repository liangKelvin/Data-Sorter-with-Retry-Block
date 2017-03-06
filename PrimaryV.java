import java.util.*;


public class PrimaryV extends Thread{

	ArrayList<Integer> values = new ArrayList<Integer>();
	int time;
	double failureRate;
	String outFile;
	String inFile;
	Adjudicator adj = new Adjudicator();
	boolean pass;

	public PrimaryV(String fileIn, String fileOut, double failure) {
		
		this.values = FileRW.readFile(fileIn);
		this.failureRate = failure;
		this.inFile = fileIn;
		this.outFile = fileOut;
		pass = false;
	}

	public boolean isPass() {
		return this.pass;
	}

	@Override
	public void run() {

		try{

			// time to sort
			HeapSort sorter = new HeapSort();
			ArrayList<Integer> newValues = sorter.sort(this.values);

			// Hazards
			Random rand = new Random();
			double n = rand.nextDouble();
			double hazard = this.failureRate * sorter.getMemCount();

			System.out.print("random: ");
			System.out.println(n);
			System.out.print("hazard: ");
			System.out.println(hazard);
			System.out.print("memcount: ");
			System.out.println(sorter.getMemCount());
			// check for error
			if(n >= 0.5 && n <= 0.5 + hazard) {
				System.out.println("transient hardware fault");
				this.pass = false;
				return;
			}
			// else no error
			FileRW.writeFile(this.outFile, newValues);

			// adjudicator
			if(adj.AcceptanceTest(inFile, outFile)) {
				this.pass = true;
			}
		} catch(ThreadDeath td) {
			System.out.println("Primary has timed out");
			this.pass = false;
			throw new ThreadDeath();
		}
	}
}