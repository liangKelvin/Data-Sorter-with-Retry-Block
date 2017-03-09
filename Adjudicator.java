import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Adjudicator {

	ArrayList<Integer> inputValues = new ArrayList<Integer>();
	ArrayList<Integer> outputValues = new ArrayList<Integer>();

	public Boolean AcceptanceTest(String fileIn, String fileOut) {
		// check if files exist
		if(!FileRW.fileExists(fileIn) || !FileRW.fileExists(fileOut)) {
			System.out.println("file(s) do not exist");
			return false;
		} 
		// read files
		inputValues = FileRW.readFile(fileIn);
		outputValues = FileRW.readFile(fileOut);
		// check size 
		if(inputValues.size() != outputValues.size()) {
			System.out.println("Error output array and input array not the same size");
			return false;
		}

		// is sorting correct?
		int sumIn = 0;
		int sumOut = 0;
		for(int i = 0; i < outputValues.size()-1; i++) {
			sumIn += inputValues.get(i);
			sumOut += outputValues.get(i);
			if(outputValues.get(i) > outputValues.get(i+1)) {
				System.out.println("Sorting incorrect");
				return false; 
			}
		}
		// // sumcheck
		// sumIn += inputValues.get(inputValues.size()-1);
		// sumOut += inputValues.get(outputValues.size()-1);
		// if(sumOut != sumIn) {
		// 	System.out.println("Sum Check incorrect");
		// 	return false;
		// }
		return true;
	}
}