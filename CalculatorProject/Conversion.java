/**
 * ---------------------------------------------------------------------------
 * File name: Conversion.java<br/>
 * Project name: BaseConversion<br/>
 * ---------------------------------------------------------------------------
 * Creator's name and email: William Horne, hornew@goldmail.etsu.edu<br/>
 * Course:  CSCI 1260<br/>
 * Creation Date: Apr 5, 2012<br/>
 * Date of Last Modification: Apr 5, 2012
 * ---------------------------------------------------------------------------
 */
import java.util.ArrayList;
/**
 * Enter type purpose here<br>
 *
 * <hr>
 * Date created: Apr 5, 2012<br>
 * Date last modified: Apr 5, 2012<br>
 * <hr>
 * @author William Horne
 */
public class Conversion
{
		byte base;
		int iBase;	//The base to convert to
		int iVal;	//The value to convert to the base of iBase
		int iMod;	//The remainder 
		int i;		//Loop variable
		ArrayList<Integer> numList = new ArrayList<Integer>();
		
		/**
		 * @return base
		 */
		private byte getBase ( )
		{
			return base;
		}
		/**
		 * @param base the base to set
		 */
		private void setBase (byte base)
		{
			this.base = base;
		}
		
		
}//end Conversion.java