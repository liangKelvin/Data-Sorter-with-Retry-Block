import java.util.TimerTask;
/* WatchDog timer taken from eClass
   @author dick
  */


public class WatchDog extends TimerTask {

	Thread watched;

	public WatchDog (Thread target) {
		watched = target;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		watched.stop();
	}
}