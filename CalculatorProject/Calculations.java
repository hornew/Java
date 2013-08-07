/**
 * ---------------------------------------------------------------------------
 * File name: Calculations.java<br/>
 * Project name: 1260-088-CalculatorProject-MusickJeremy<br/>
 * ---------------------------------------------------------------------------
 * Creator's name and email: Jeremy Musick, MusickJ@Goldmail.ETSU.edu,
 * 							 William Horne, hornew@goldmail.etsu.edu <br/>
 * Course:  CSCI 1260<br/>
 * Creation Date: Apr 12, 2012<br/>
 * Date of Last Modification: Apr 24, 2012
 * ---------------------------------------------------------------------------
 */

/**
 * To perform calculations on the numbers passed to the methods<br>
 *
 * <hr>
 * Date created: Apr 12, 2012<br>
 * Date last modified: Apr 12, 2012<br>
 * <hr>
 * @author Jeremy Musick
 */
import java.lang.Math;
import java.io.Serializable;

public class Calculations implements Serializable
{
	private static final long	serialVersionUID	= 1L;	//Allows extension from JFrame
	private double leftValue;								//Holds the left value
	private double rightValue;								//Holds the right value
	private double defaultValue = 0.0;						//Holds the default value
	
	/**
	 * Constructor <br>        
	 *
	 * <hr>
	 * Date created: Apr 16, 2012 <br>
	 * Date last modified: Apr 16, 2012 <br>
	 *
	 * <hr>
	 */
	public Calculations ( )
	{
		setLeftValue(defaultValue);
		setRightValue(defaultValue);
	}//end of Calculations()

	
	/**
	 * Gets the left value <br>        
	 *
	 * <hr>
	 * Date created: Apr 2vv5, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double getLeftValue ( )
	{
		return leftValue;
	}//end getLeftValue()

	
	/**
	 * sets the left value <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @param leftValue
	 */
	public void setLeftValue (double leftValue)
	{
		this.leftValue = leftValue;
	}//end setLeftValue()

	
	/**
	 * Gets the right value <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double getRightValue ( )
	{
		return rightValue;
	}//end getRightValue()

	
	/**
	 * Sets the right value <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @param rightValue
	 */
	public void setRightValue (double rightValue)
	{
		this.rightValue = rightValue;
	}//end setRightValue()

	/**
	 * Resets both the leftValue and rightValue attributes to 0.0
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	public void reset()
	{
		setLeftValue(defaultValue);
		setRightValue(defaultValue);
	}//end of reset()
	
	/**
	 * Addition function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double sum()
	{
		double s;
		s = getLeftValue() + getRightValue();
		return s;
	}//end sum()
	
	/**
	 * Subtraction function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double subtract()
	{
		double s;
		s = getLeftValue() - getRightValue();
		return s;
	}//end subtract()
	
	/**
	 * Division function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double divide()
	{
		double s;
		s = getLeftValue() / getRightValue();
		return s;
	}//end divide()
	
	/**
	 * Multiplication function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double multiply()
	{
		double s;
		s = getLeftValue() * getRightValue();
		return s;
	}//end multiply()
	
	/**
	 * Reciprocal function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double reciprocal()
	{
		double s;
		
		s = 1 / getLeftValue();
		
		return s;
	}//end reciprocal()
	
	/**
	 * Flips the number value across 0<br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double negate()
	{
		double s;
		
		s = 0 - getLeftValue();
		
		return s;
	}//end negate()
	
	/**
	 * Square root function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double squareRoot()
	{
		double s;
		//if(rightValue == 0.0)
		//{
			s = Math.sqrt(leftValue);
		//}
		/*else 
		{
			s = Math.sqrt(rightValue);
		}*/
		return s;
	}//end squareRoot()
	
	/**
	 * Cube root function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double cubeRoot()
	{
		double s;
			s = Math.cbrt(leftValue);
		return s;
	}//end squareRoot()
	
	/**
	 * Finds pi <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double PI()
	{
		double s = Math.PI;
		if(leftValue == 0.0)
		{
			setLeftValue(Math.PI);
		}
		else if(rightValue == 0.0)
		{
			setRightValue(Math.PI);
		}
		return s;
	}//end PI()
	
	/**
	 * Square function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double squared()
	{
		double s;
		
		s = Math.pow (leftValue, 2);
		
		return s;
	}//end of squared()
	
	/**
	 * Cube function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double cubed()
	{
		double s;
		
		s = Math.pow (leftValue, 3);
		
		return s;
	}//end of cubed()
	
	/**
	 * Two-power function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double twoPower()
	{
		double s;
		
		s = Math.pow (2, leftValue);
		
		return s;
	}//end of twoPower()
	
	/**
	 * Natural log function <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double naturalLog()
	{
		double s;
		
		s = Math.log (leftValue);
		
		return s;
	}//end of naturalLog()
	
	/**
	 * does e to the power of x <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public double eToPower()
	{
		double s;
		
		s = Math.exp (leftValue);
		
		return s;
	}//end of eToPower()
}//end of Calculations.java