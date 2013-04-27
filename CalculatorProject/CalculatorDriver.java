/**
 * ---------------------------------------------------------------------------
 * File name: CalculatorDriver.java<br/>
 * Project name: Calculator<br/>
 * ---------------------------------------------------------------------------
 * Creator's name and email: William Horne, hornew@goldmail.etsu.edu<br/>
 * Course:  CSCI 1260<br/>
 * Creation Date: Apr 9, 2012<br/>
 * Date of Last Modification: Apr 9, 2012
 * ---------------------------------------------------------------------------
 */
import javax.swing.SwingUtilities;
/**
 * Driver for the CalculatorWindow class<br>
 *
 * <hr>
 * Date created: Apr 9, 2012<br>
 * Date last modified: Apr 9, 2012<br>
 * <hr>
 * @author William Horne
 */
public class CalculatorDriver
{

	/**
	 * Runs the CalculatorWindow class <br>        
	 *
	 * <hr>
	 * Date created: Apr 9, 2012 <br>
	 * Date last modified: Apr 9, 2012 <br>
	 *
	 * <hr>
	 * @param args
	 */

	public static void main (String [ ] args)
	{
		SwingUtilities.invokeLater
		(
			new Runnable()
			{
				public void run()
				{
					@SuppressWarnings ("unused")
					CalculatorWindow calc = new CalculatorWindow();
				}//end run()
				
			}//end Runnable()
		);

	}//end main

}//end CalculatorDriver.java
