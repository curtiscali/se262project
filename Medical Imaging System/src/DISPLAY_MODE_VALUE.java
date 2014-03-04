/**
 * A simple enum to help encapsulate various display modes for studies
 * @author Curtis Cal
 *
 */
public enum DISPLAY_MODE_VALUE {
	ONE_IMAGE, FOUR_IMAGE;
	
	/**
	 * Gets the equivalent integer value given a display mode
	 * @param value the value whose integer value will be returned
	 * @return the integer value of the display Mode
	 */
	public static int getValue(DISPLAY_MODE_VALUE value) {
		if(value == ONE_IMAGE)
			return 1;
		else
			return 4;
	}
}
