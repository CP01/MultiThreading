/**
 * 
 */
package charchit;

/**
 * @author cpatodi
 *
 */
public class ThreadStaticTest {
	private static Integer staticInt ;

	public static Integer getStaticInt() {
		return staticInt;
	}

	public static void setStaticInt(Integer staticInt) {
		ThreadStaticTest.staticInt = staticInt;
	}
	
	

}
