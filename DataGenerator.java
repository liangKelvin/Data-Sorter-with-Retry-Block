import java.util.Random;
import java.security.InvalidParameterException;
import java.util.ArrayList;


public class DataGenerator {

	
	public static void main(String[] args) {

		if(args.length != 2) {
			throw new InvalidParameterException("Number of arguments is incorrect");
		}

		String filename = args[0];
		int num_values = Integer.parseInt(args[1]);

		Random rn = new Random();
		//int values[] = new int[num_values];
		ArrayList<Integer> values = new ArrayList<Integer>(num_values);
		for(int i = 0; i < num_values; i++) {
			values.add(rn.nextInt());
		}

		FileRW.writeFile(filename, values);

		// //test
		// for(int i = 0; i < num_values-1; i++) {
		// 	if(!(newValues.get(i) <= newValues.get(i+1))) {
		// 		System.out.println("WRONG");
		// 	}
		// }
	}
}