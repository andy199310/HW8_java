
public class HW8Timer {
	private int hour;	// 0 - 23
	private int minute;	// 0 - 59
	private int second;	// 0 - 59

	/** Timer no-argument constructor
	 */
	public HW8Timer(){
		this(0, 0, 0);
	}
	
	/** Timer with hour
	 */
	public HW8Timer(int h){
		this(h, 0, 0);
	}
	
	/** Timer with hour, minute
	 */
	public HW8Timer(int h, int m){
		this(h, m, 0);
	}
	
	/** Timer with hour, minute, second
	 */
	public HW8Timer(int h, int m, int s){
		setTime(h, m, s);
	}
	
	/** Timer with a timer
	 */
	public HW8Timer(HW8Timer timer){
		this(timer.getHour(), timer.getMinute(), timer.getSecond());
	}
	
	/** Set timer's time
	 * @param h - hour
	 * @param m - minute
	 * @param s - second
	 */
	public void setTime(int h, int m, int s){
		setHour(h);
		setMinute(m);
		setSecond(s);
		checkTime();
	}
	
	/**Set hour
	 * @param h - hour
	 */
	public void setHour(int h){
		if (h >= 0 && h<24)
			hour = h;
		else
			throw new IllegalArgumentException("hour must be 0-23");
	}
	
	/** Set minute
	 * @param m - minute
	 */
	public void setMinute(int m){
		if (m >= 0 && m < 60)
			minute= m;
		else
			throw new IllegalArgumentException("minute must be 0-59");
	}
	
	/** Set second
	 * @param s - second
	 */
	public void setSecond(int s){
		if (s >= 0 && s < 60)
			second = s;
		else
			throw new IllegalArgumentException("hour must be 0-23");
	}
	
	/** Get hour
	 * @return hour
	 */
	public int getHour(){
		return hour;
	}
	
	/** Get minute
	 * @return minute
	 */
	public int getMinute(){
		return minute;
	}
	
	/** Get second
	 * @return second
	 */
	public int getSecond(){
		return second;
	}
	
	/** Convert to String in universal-time format (HH:MM:SS)
	 * @return (HH:MM:SS)
	 */
	public String toUniversalString(){
		return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
	}
	
	/**Convert to String in universal-time format (H:MM:SS AM or PM)
	 * @return (H:MM:SS AM or PM)
	 */
	public String toString(){
		return String.format("%d:%02d:%02d %s", (getHour() == 0 || getHour() == 12) ? 12: getHour()%12, getMinute(), getSecond(), (getHour() <12 ? "AM" : "PM"));
	}
	
	/**
	 * Check if any number is out of range and fix it
	 */
	private void checkTime(){
		// Check minute
		if (second >= 60){
			minute += second / 60;
			second = second % 60;
		}
		
		// Check minute
		if(minute >= 60){
			hour += minute / 60;
			minute = minute % 60;
		}
		
		// Check hour
		if(hour >= 24){
			hour = hour % 24;
		}
	}
	
	/**
	 * Add one second
	 */
	public void tick(){
		second++;
		checkTime();
	}
	
	/**
	 * Increase one second
	 */
	public void incrementSecond(){
		second++;
		checkTime();
	}
	
	/**
	 * Increase (some) second
	 * @param s - Second want to increase
	 */
	public void incrementSecond(int s){
		second += s;
		checkTime();
	}
	
	/**
	 * Increase one minute
	 */
	public void incrementMinute(){
		minute++;
		checkTime();
	}
	
	/**
	 * Increase (some) minute
	 * @param m - Minute want to increase
	 */
	public void incrementMinute(int m){
		minute += m;
		checkTime();
	}
	
	/**
	 * Increase one hour
	 */
	public void incrementHour(){
		hour++;
		checkTime();
	}
	
	/**
	 * Increase (some) hour
	 * @param h - Hour want to increase
	 */
	public void incrementHour(int h){
		hour += h;
		checkTime();
	}
}
