import java.util.*;
import java.lang.IllegalArgumentException;
import java.lang.Integer;


public class Executive {


	public static void main(String args[]) {

		// translate arguments
		if(args.length != 5) {
			throw new IllegalArgumentException("Incorrect number of inputs given. Expected: Input Filename, Output Filename, Failure Rate1, Failure Rate2 and Time to Failure");
		}

		String fileIn = args[0];
		String fileOut = args[1];
		double primaryFailure = Double.parseDouble(args[2]);
		double secondaryFailure = Double.parseDouble(args[3]);
		int timeLimit = Integer.parseInt(args[4]);

		// try primary V
		PrimaryV primary = new PrimaryV(fileIn, fileOut, primaryFailure);
		// timer
		Timer t = new Timer();
		WatchDog w = new WatchDog(primary);
		t.schedule(w, timeLimit);
		primary.start();

		try {
			primary.join();
			t.cancel();

			 if(!(primary.isPass())) {
				// local exception
				System.out.println("Local Exception Primary Variant has failed, now switching to Secondary Variant");
			 } else {
				// pass!
				System.out.println("Primary Variant has passed, success!");
				return;
			}
		} catch (InterruptedException e) {}

		// run secondary V
		SecondaryV secondary = new SecondaryV(fileIn, fileOut, secondaryFailure);
		t = new Timer();
		w = new WatchDog(secondary);
		t.schedule(w, timeLimit);
		secondary.start();

		try {
			secondary.join();
			t.cancel();

			 if(!(secondary.isPass())) {
				// local exception
				System.out.println("Secondary Variant has failed, System Failure, Exiting");
				FileRW.deleteFile(fileOut);

			 } else {
				// pass!
				System.out.println("Secondary variant has passed, success!");
				return;
			}
		} catch (InterruptedException e) {}
	}
}