package bid;

public class Clock {

	private long startTime;
	private static Clock singleton = null;
	
	public static Clock getSingleton() {
		if (singleton == null)
			singleton = new Clock ();
		
		return singleton;
		
	}
	
	private Clock () {
		startTime = System.currentTimeMillis();
	}
	
	public long getTime () {
		return System.currentTimeMillis() - startTime;
	}
	
	public void addOneYear () {
		this.startTime -= 1000 * 3600 * 24 * 365;
	}
}
