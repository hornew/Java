/**
 * ---------------------------------------------------------------------------
 * File name: CalculatorWindow.java<br/>
 * Project name: Calculator<br/>
 * ---------------------------------------------------------------------------
 * Creator's name and email: William Horne, hornew@goldmail.etsu.edu<br/>
 * Course:  CSCI 1260<br/>
 * Creation Date: Apr 9, 2012<br/>
 * Date of Last Modification: Apr 24, 2012
 * ---------------------------------------------------------------------------
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*; 	
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Graphical user interface for the Calculator. This frame is built with a 2 row, 1 column Grid
 * Layout. Text and function panel are added to headerPanel which is then added to the top of the 
 * Grid. footerPanel has components added to it directly and it is then added to the bottom of the
 * Grid. The interface allows input using both the buttons and keyboard. To make use of the 
 * keyboard, a KeyListener class is added along with the ActionListeners. Chain calculations are not
 * allowed at this time but may be implemented in a future version.<br>
 *
 * <hr>
 * Date created: Apr 6, 2012<br>
 * Date last modified: Apr 25, 2012<br>
 * <hr>
 * @author William Horne
 */
public class CalculatorWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static final int 	WINDOW_WIDTH = 300,//Base Window width set at construction
								WINDOW_HEIGHT = 475;//Base Window height set at construction
	private static final int ENTRYFONTSIZE = 24;	//font point size of the text field
	private static final int BUTTONFONTSIZE = 16;	//font point size of all buttons
	
	//Layout which is to be used on CalculatorWindow 
	private GridLayout faceGrid = new GridLayout(2,1);
	
	//Reference to Calculations class used to perform calculations
	private Calculations calc = new Calculations();	
	private Function Fun;		//Enumerator which is used to determine the current arithmetical 
	private char append;		/*holds the value returned by numeric buttons and decimal point
								 *button to be appended to the text field*/
	private byte base;
	private int vGap;			//vertical gap between panels in GridLayout of the face panel
	private double 	result;		//result of a function 
	private double 	leftValue,	//current left value of an arithmetical operation
					rightValue;	//current right value of an arithmetical operation
	private String 	strParse;	//Holds text from the text field to be parsed 
	private String updateText = "";	//used to set the text in the text field to a new value
	private boolean incognito;//used to determine whether calculator is currently in incognito mode
	
	/* Colors used to reset to default colors or change to black and white for incognito	*/
	private Color 	defaultButtonForeground,
					defaultButtonBackground,
					defaultMenuBackground,
					defaultMenuForeground,
					black = Color.BLACK,
					white = Color.WHITE;
	
	/* Fonts references that will be used to change the fonts of the textfield and buttons	*/
	private Font 	entryFont,
					buttonFont,
					italicButtonFont;
	
	private BufferedImage windowIcon;	//Icon used in the title bar and taskbar
	
	/* Menus with self-explanatory names which are added to menuBar in the order they are defined */
	private JMenu 	fileMenu,	
					toolsMenu,
					helpMenu;					
	private JMenuBar	menuBar;	//Menu bar
	
	/* Menu items with self-explanatory names. The exit menu item is added to fileMenu, readme and 
	 * about are added to helpMenu, and settings is added to toolsMenu */
	private JMenuItem 	exit,		
						readme,
						about,
						settings;
	private JTextField entry;	  //Textfield used for display and calculation
	
	private JPanel  headerPanel,  //panel to which the textPanel and functionPanel are added
					footerPanel,  //panel to which the last 20 buttons are added
					textPanel,	  //panel to which the text field is added
					functionPanel;//panel to which the first 10 buttons are added
	
	/* JButton components used for the buttons on the face of the calculator */
	private JButton eToPower, //e raised to a power entered to the text field
					twoPower, //decimal two raised to a power entered to the text field
					ln, 	  //natural log of a number entered in the text field
					xCube,	  //cube of a number entered in the text field	
					xSquare,  //square of a number entered in the text field
					del,	  //delete the character to the left of the cursor
					cubeRoot,//third root of the number entered in the text field
					C, 		  //clear all text from text field and values in Calculations class	
					negate,	  //negate the number entered in the text field
					squareRoot,//take the square root of the number entered in the text field
					nine,      //decimal 9
					eight,	   //decimal 8	
					seven,	   //decimal 7
					six,	   //decimal 6	
					five,	   //decimal 5
					four,	   //decimal 4
					three,	   //decimal 3
					two,	   //decimal 2
					one,	   //decimal 1
					zero,	   //0
					divide,    //division arithmetical function
					percent,   //x percentage of left value
					multiply,  //multiplication arithmetical function
					reciprocal,//reciprocal of the number entered in the text field
					add,	   //addition arithmetical function
					subtract,  //subtraction arithmetical function
					decimalPoint, //decimal point
					equals,		  //equate the current arithmetical function being performed
					PI,			  //mathematical constant PI
					e;			  //mathematical constant e
	private Dimension d = new Dimension(375,300);	
	
	/**
	 * Constructor <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 9, 2012 <br>
	 *
	 * <hr>
	 */
	public CalculatorWindow ( )
	{
		/* Name of the window set using the super constructor. Size of the window initialized to 
		 * default values of WINDOW_WIDTH and WINDOW_HEIGHT. Default layout set to the value of
		 * faceGrid which is defined as a GridLayout with 2 rows and 1 column. Vertical gap between
		 * components set to a default of 3 using the vGap variable. */
		super("Calculator");
		this.setSize (WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		this.setLayout(faceGrid);		
		vGap = 3;
		faceGrid.setVgap(vGap);
		
		/* Icon loaded and set, components added, menu bar, menus, and menu items created, all 
		 * panels created. 	*/
		loadAndSetIcon();
		addComponents();
		createMenu();
		this.setJMenuBar(menuBar);
		createTextPanel();
		createFunctionPanel();
		createHeaderPanel();
		createFooterPanel();
		
		/* Main panels added to the window	*/
		add(headerPanel);
		add(footerPanel);		
		
		/* All listeners created here */ 
		addMenuListeners();
		addNumberListeners();
		addFunctionListeners();
		addKeyListeners();
		
		/* Visible option initialized to true, resizable option initialized to false, location set
		 * relative to null so that the window is centered on the screen, default action when enter
		 * pressed is set to equals */
		this.setVisible (true);		
		this.setResizable(false);
		this.setLocationRelativeTo (null);
		getRootPane().setDefaultButton(equals);		
		base = 10;		
	}//end CalculatorWindow()
	
	/**
	 * Attempts to load and set the icon that is to be used for icon on the window and on the 
	 * taskbar. If load is unsuccessful, no action is taken and icon setting is ignored.  
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void loadAndSetIcon()
	{		
		try
		{
			windowIcon = ImageIO.read (getClass().getResource ("calculator.png"));
			setIconImage(windowIcon);
		}
		catch(Exception e)
		{
			
		}		
	}//end loadAndSetIcon()
	
	/**
	 * Sets all background colors of components to black and all foreground colors to white. This is
	 * defined as the epic and super stealth "Incognito mode".
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void setIncognito()
	{
			eToPower.setBackground (black);
			twoPower.setBackground (black);
			ln.setBackground (black);
			xCube.setBackground (black);
			xSquare.setBackground (black);
			del.setBackground (black);
			cubeRoot.setBackground (black);
			C.setBackground (black);
			negate.setBackground (black);
			squareRoot.setBackground (black);
			nine.setBackground (black);
			eight.setBackground (black);
			seven.setBackground (black);
			six.setBackground (black);
			five.setBackground (black);
			four.setBackground (black);
			three.setBackground (black);
			two.setBackground (black);
			one.setBackground (black);
			zero.setBackground (black);
			divide.setBackground (black);
			percent.setBackground (black);
			multiply.setBackground (black);
			reciprocal.setBackground (black);
			add.setBackground (black);
			subtract.setBackground (black);
			decimalPoint.setBackground (black);
			equals.setBackground (black);
			e.setBackground (black);
			PI.setBackground (black);
			
			eToPower.setForeground (white);
			twoPower.setForeground (white);
			ln.setForeground (white);
			xCube.setForeground (white);
			xSquare.setForeground (white);
			del.setForeground (white);
			cubeRoot.setForeground (white);
			C.setForeground (white);
			negate.setForeground (white);
			squareRoot.setForeground (white);
			nine.setForeground (white);
			eight.setForeground (white);
			seven.setForeground (white);
			six.setForeground (white);
			five.setForeground (white);
			four.setForeground (white);
			three.setForeground (white);
			two.setForeground (white);
			one.setForeground (white);
			zero.setForeground (white);
			divide.setForeground (white);
			percent.setForeground (white);
			multiply.setForeground (white);
			reciprocal.setForeground (white);
			add.setForeground (white);
			subtract.setForeground (white);
			decimalPoint.setForeground (white);
			equals.setForeground (white);
			e.setForeground (white);
			PI.setForeground (white);
					
			entry.setBackground (black);
			entry.setForeground(white);

			menuBar.setBackground(black);
			menuBar.setForeground(white);
			fileMenu.setForeground(white);
			helpMenu.setForeground (white);
			toolsMenu.setForeground(white);
			
			exit.setBackground(black);
			settings.setBackground(black);
			readme.setBackground(black);
			about.setBackground(black);
						
			exit.setForeground(white);
			settings.setForeground(white);
			readme.setForeground(white);
			about.setForeground(white);
	}//end setIncognito()
	
	/**
	 * Incognito Reset method. Resets the background and foreground of all components to the default
	 * values determined by getting colors of background and foreground at construction.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void resetIncognito()
	{
		eToPower.setBackground (defaultButtonBackground);
		twoPower.setBackground (defaultButtonBackground);
		ln.setBackground (defaultButtonBackground);
		xCube.setBackground (defaultButtonBackground);
		xSquare.setBackground (defaultButtonBackground);
		del.setBackground (defaultButtonBackground);
		cubeRoot.setBackground (defaultButtonBackground);
		C.setBackground (defaultButtonBackground);
		negate.setBackground (defaultButtonBackground);
		squareRoot.setBackground (defaultButtonBackground);
		nine.setBackground (defaultButtonBackground);
		eight.setBackground (defaultButtonBackground);
		seven.setBackground (defaultButtonBackground);
		six.setBackground (defaultButtonBackground);
		five.setBackground (defaultButtonBackground);
		four.setBackground (defaultButtonBackground);
		three.setBackground (defaultButtonBackground);
		two.setBackground (defaultButtonBackground);
		one.setBackground (defaultButtonBackground);
		zero.setBackground (defaultButtonBackground);
		divide.setBackground (defaultButtonBackground);
		percent.setBackground (defaultButtonBackground);
		multiply.setBackground (defaultButtonBackground);
		reciprocal.setBackground (defaultButtonBackground);
		add.setBackground (defaultButtonBackground);
		subtract.setBackground (defaultButtonBackground);
		decimalPoint.setBackground (defaultButtonBackground);
		equals.setBackground (defaultButtonBackground);
		e.setBackground (defaultButtonBackground);
		PI.setBackground (defaultButtonBackground);
		
		eToPower.setForeground (defaultButtonForeground);
		twoPower.setForeground (defaultButtonForeground);
		ln.setForeground (defaultButtonForeground);
		xCube.setForeground (defaultButtonForeground);
		xSquare.setForeground (defaultButtonForeground);
		del.setForeground (defaultButtonForeground);
		cubeRoot.setForeground (defaultButtonForeground);
		C.setForeground (defaultButtonForeground);
		negate.setForeground (defaultButtonForeground);
		squareRoot.setForeground (defaultButtonForeground);
		nine.setForeground (defaultButtonForeground);
		eight.setForeground (defaultButtonForeground);
		seven.setForeground (defaultButtonForeground);
		six.setForeground (defaultButtonForeground);
		five.setForeground (defaultButtonForeground);
		four.setForeground (defaultButtonForeground);
		three.setForeground (defaultButtonForeground);
		two.setForeground (defaultButtonForeground);
		one.setForeground (defaultButtonForeground);
		zero.setForeground (defaultButtonForeground);
		divide.setForeground (defaultButtonForeground);
		percent.setForeground (defaultButtonForeground);
		multiply.setForeground (defaultButtonForeground);
		reciprocal.setForeground (defaultButtonForeground);
		add.setForeground (defaultButtonForeground);
		subtract.setForeground (defaultButtonForeground);
		decimalPoint.setForeground (defaultButtonForeground);
		equals.setForeground (defaultButtonForeground);
		e.setForeground (defaultButtonForeground);
		PI.setForeground (defaultButtonForeground);
				
		entry.setBackground (white);
		entry.setForeground(black);

		menuBar.setBackground(defaultMenuBackground);
		menuBar.setForeground(defaultMenuForeground);
		fileMenu.setForeground(defaultMenuForeground);
		helpMenu.setForeground (defaultMenuForeground);
		toolsMenu.setForeground(defaultMenuForeground);
				
		exit.setBackground(defaultButtonBackground);
		settings.setBackground(defaultButtonBackground);
		readme.setBackground(defaultButtonBackground);
		about.setBackground(defaultButtonBackground);
				
		exit.setForeground(defaultMenuForeground);
		settings.setForeground(defaultMenuForeground);
		readme.setForeground(defaultMenuForeground);
		about.setForeground(defaultMenuForeground);
	}//end resetIncognito()
	
	/**
	 * Used by the SettingsDialog to change the size of the window
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void setResize(int i)
	{
		if(i==0)
		{
			setResizable(false);
		}
		else if(i==1)
		{
			setResizable(true);
			setMinimumSize(d);
		}
		else if(i==2)
		{
			setSize (WINDOW_WIDTH, WINDOW_HEIGHT);
		}
	}//end of setResize()
	
	/**
	 * Used by the SettingsDialog to change the color of the components used by the Window
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void setBgColor(Color c)
	{
		eToPower.setBackground (c);
		twoPower.setBackground (c);
		ln.setBackground (c);
		xCube.setBackground (c);
		xSquare.setBackground (c);
		del.setBackground (c);
		cubeRoot.setBackground (c);
		C.setBackground (c);
		negate.setBackground (c);
		squareRoot.setBackground (c);
		nine.setBackground (c);
		eight.setBackground (c);
		seven.setBackground (c);
		six.setBackground (c);
		five.setBackground (c);
		four.setBackground (c);
		three.setBackground (c);
		two.setBackground (c);
		one.setBackground (c);
		zero.setBackground (c);
		divide.setBackground (c);
		percent.setBackground (c);
		multiply.setBackground (c);
		reciprocal.setBackground (c);
		add.setBackground (c);
		subtract.setBackground (c);
		decimalPoint.setBackground (c);
		equals.setBackground (c);
		e.setBackground (c);
		PI.setBackground (c);
		entry.setBackground (c);
		
		menuBar.setBackground (c);
		fileMenu.setBackground (c);
		toolsMenu.setBackground (c);
		helpMenu.setBackground (c);
		
		exit.setBackground (c);
		settings.setBackground (c);
		about.setBackground (c);
		readme.setBackground (c);
	}//end of setBgColor
		
	/**
	 * Menu items created and added to menus and menus added to menu bar. 
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void createMenu()
	{
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		toolsMenu = new JMenu("Tools");
		
		menuBar = new JMenuBar();
		
		exit = new JMenuItem("Exit");		
		readme = new JMenuItem("Readme");
		about = new JMenuItem("About");
		settings = new JMenuItem("Settings");
				
		fileMenu.add (exit);
		
		helpMenu.add (readme);
		helpMenu.add (about);	
		
		toolsMenu.add (settings);
		
		menuBar.add(fileMenu);
		menuBar.add(toolsMenu);
		menuBar.add(helpMenu);		
				
		defaultMenuBackground = menuBar.getBackground();
		defaultMenuForeground = fileMenu.getForeground();
		
	}//end createMenu()			
	
	/**
	 * headerPanel layout set and components added
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void createHeaderPanel()
	{
		headerPanel.setLayout (new GridLayout(2,1));
		headerPanel.add(textPanel);
		headerPanel.add(functionPanel);
					
	}//end createHeaderPanel()
	
	/**
	 * Footer panel layout created and buttons added
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void createFooterPanel()
	{
		GridLayout footerGrid = new GridLayout(4,5);
		footerGrid.setHgap (5);
		footerGrid.setVgap (5);
		footerPanel.setLayout(footerGrid);
		footerPanel.add (seven);
		footerPanel.add (eight);
		footerPanel.add (nine);
		footerPanel.add (divide);
		footerPanel.add (percent);
		footerPanel.add (four);
		footerPanel.add (five);
		footerPanel.add (six);
		footerPanel.add (multiply);
		footerPanel.add (reciprocal);
		footerPanel.add (one);
		footerPanel.add (two);
		footerPanel.add (three);
		footerPanel.add (subtract);
		footerPanel.add(PI);
		footerPanel.add (zero);
		footerPanel.add (decimalPoint);
		footerPanel.add (equals);
		footerPanel.add (add);
		footerPanel.add(e);
	}//end createFooterPanel()
	
	/**
	 * textPanel layout creator
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void createTextPanel()
	{		
		textPanel.setLayout (new BorderLayout());
		textPanel.add(entry, BorderLayout.CENTER);
		
	}//end createTextPanel()
	
	/**
	 * Function components added to the functionPanel. Layout, Hgap, and Vgap also set. 
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void createFunctionPanel()
	{
		GridLayout funcGrid = new GridLayout(2,5);
		funcGrid.setHgap (5);
		funcGrid.setVgap (5);
		functionPanel.setLayout(funcGrid);
		functionPanel.add (eToPower);
		functionPanel.add (ln);
		functionPanel.add (twoPower);
		functionPanel.add (xCube);
		functionPanel.add (xSquare);
		functionPanel.add (del);
		functionPanel.add (C);
		functionPanel.add (negate);
		functionPanel.add (cubeRoot);		
		functionPanel.add (squareRoot);
		
	}//end createFunctionPanel()
	
	/**
	 * Builds the components to be added to textPanel, functionPanel, and footerPanel. Also applies
	 * font specifications and sets the default behaviors of the "entry" JTextField. incognito 
	 * is initialized to false. This method is intended to be used in the constructor to initialize
	 * the state of the components at startup.
	 * 
	 * NOTE: Following are the unicode digits used for special characters and their meanings. 
	 * - eToPower (e raised to the power n): \u207F is unicode for superscript n. \u212F is e
	 * - twoPower(decimal 2 raised to the power n):	"\u207F" is unicode for superscript n.
	 * - del (delete last character): "\u2190"
	 * - negate: "\u00B1"
	 * - xCube(x raised to the power 3): "\u00B3" is unicode for superscript 3
	 * - xSquare(x raised to the power 2): "\u00B2" is unicode for superscript 2
	 * - squareRoot: "\u221A" is unicode for square root symbol
	 * - cubeRoot: "\u221B" 
	 * - PI (ratio of circle's circumference to its diameter): "\u03C0" unicode for lower case pi
	 * - e: \u212F is e
	 * 
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void addComponents()
	{
		headerPanel = new JPanel();
		footerPanel = new JPanel();
		functionPanel = new JPanel();
		textPanel = new JPanel();
						
		eToPower = new JButton("\u212F\u207F");
		twoPower = new JButton("2\u207F");
		ln = new JButton("ln");
		xCube = new JButton("x\u00B3");
		xSquare = new JButton("x\u00B2");
		del = new JButton("\u2190");
		cubeRoot = new JButton("\u221B");
		C = new JButton("c");
		negate = new JButton("\u00B1");
		squareRoot = new JButton("\u221A");
		nine = new JButton("9");
		eight = new JButton("8");
		seven = new JButton("7");
		six = new JButton("6");
		five = new JButton("5");
		four = new JButton("4");
		three = new JButton("3");
		two = new JButton("2");
		one = new JButton("1");
		zero = new JButton("0");
		divide = new JButton("/");
		multiply = new JButton("*");
		percent = new JButton("%");
		reciprocal = new JButton("1/x");
		subtract = new JButton("-");
		add = new JButton("+");
		decimalPoint = new JButton(".");
		equals = new JButton("=");
		PI = new JButton("\u03C0");
		e = new JButton("\u212F");
		
		defaultButtonBackground = C.getBackground ( );
		defaultButtonForeground = C.getForeground ( );
		
		buttonFont = new Font("SansSerif", Font.PLAIN, BUTTONFONTSIZE);
		entryFont = new Font("SansSerif", Font.PLAIN, ENTRYFONTSIZE);
		italicButtonFont = new Font ("SanSerif", Font.ITALIC, BUTTONFONTSIZE);
		entry = new JTextField("0", 1);
				
		add.setFont(buttonFont);
		subtract.setFont(buttonFont);
		equals.setFont(buttonFont);
		decimalPoint.setFont(buttonFont);
		multiply.setFont(buttonFont);
		divide.setFont(buttonFont);
		negate.setFont(buttonFont);
		percent.setFont(buttonFont);
		del.setFont(buttonFont);
		squareRoot.setFont(buttonFont);
		eToPower.setFont(buttonFont);
		twoPower.setFont(buttonFont);
		ln.setFont(buttonFont);
		xCube.setFont(buttonFont);
		xSquare.setFont(buttonFont);
		C.setFont(buttonFont);
		cubeRoot.setFont(buttonFont);
		reciprocal.setFont(buttonFont);
		nine.setFont(buttonFont);
		eight.setFont(buttonFont);
		seven.setFont(buttonFont);
		six.setFont(buttonFont);
		five.setFont(buttonFont);
		four.setFont(buttonFont);
		three.setFont(buttonFont);
		two.setFont(buttonFont);
		one.setFont(buttonFont);
		zero.setFont(buttonFont);
		PI.setFont(buttonFont);
		e.setFont(italicButtonFont);
		
		
		entry.setFont(entryFont);
		entry.setHorizontalAlignment (JTextField.RIGHT);
		entry.grabFocus ( );
		entry.setHighlighter(null);
		entry.selectAll ( );
		
		incognito = false;
	}//end addButtons()
	
	/**
	 * Action Listeners added for all number buttons (0-9). numberListener class serves as listener
	 * class for these components
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void addNumberListeners()
	{
		nine.addActionListener (new numberListener());
		eight.addActionListener (new numberListener());
		seven.addActionListener (new numberListener());
		six.addActionListener (new numberListener());
		five.addActionListener (new numberListener());
		four.addActionListener (new numberListener());
		three.addActionListener (new numberListener());
		two.addActionListener (new numberListener());
		one.addActionListener (new numberListener());
		zero.addActionListener (new numberListener());
		
	}//end of addListeners()
	
	/**
	 * Action listeners added for all functions. functionListener class serves as listener class for
	 * these compoenents.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void addFunctionListeners()
	{
		add.addActionListener (new functionListener());
		subtract.addActionListener (new functionListener());
		multiply.addActionListener (new functionListener());
		divide.addActionListener (new functionListener());
		equals.addActionListener (new functionListener());
		negate.addActionListener (new functionListener());
		percent.addActionListener (new functionListener());
		squareRoot.addActionListener (new functionListener());
		C.addActionListener (new functionListener());
		cubeRoot.addActionListener (new functionListener());
		xSquare.addActionListener (new functionListener());
		xCube.addActionListener (new functionListener());
		del.addActionListener (new functionListener());
		reciprocal.addActionListener (new functionListener());	
		PI.addActionListener (new functionListener());
		e.addActionListener (new functionListener());
		twoPower.addActionListener (new functionListener());
		ln.addActionListener (new functionListener());
		eToPower.addActionListener (new functionListener());
		decimalPoint.addActionListener (new functionListener());
	}//end addFunctionListeners()
	
	/**
	 * Listeners added for all menu items. menuListener class servers as listener class for these 
	 * components.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void addMenuListeners()
	{
		exit.addActionListener (new menuListener());
		readme.addActionListener (new menuListener());
		about.addActionListener (new menuListener());
		settings.addActionListener (new menuListener());
	}
	
	/**
	 * listener added for Key events applying to the "entry" JTextField. This includes keyboard keys
	 * pressed, released, and typed. keyListener class serves as the listener class for this 
	 * component
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void addKeyListeners()
	{
		entry.addKeyListener (new keyListener());
	}
	
	/**
	 * Simply gets digits currently in "entry" by parsing a Double. Adds the value parsed to the
	 * leftValue attribute in the Calculations class.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void setLeftValue()
	{
		strParse = entry.getText();
		leftValue = Double.parseDouble (strParse);
		calc.setLeftValue (leftValue);
	}
	
	/**
	 * Splits the text in "entry" around the add, subtract, multiply, and divide operators and sets 
	 * the digits to the right of the operators as the rightValue in the Calculations class. Catches
	 * NumberFormatException but does nothing as a result. This prevents chain calculations. 
	 * Future versions to possibly revise the methods of getting left and right values with the 
	 * intent of allowing chain calculations.  
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void setRightValue()
	{	
		String[] split = null;	//String array created to hold individual Strings generated by the
								//split method
		
		/* Determines the arithmetic symbol that is being used in calculation	*/
		if(Fun != null)
		{
			strParse = entry.getText();
			switch (Fun)
			{
				case ADD:
					split = strParse.split ("\\+");
					break;
				case SUBTRACT:
					split = strParse.split ("\\-");
					break;
				case DIVIDE:
					split = strParse.split ("\\/");
					break;
				case MULTIPLY:
					split = strParse.split ("\\*");
					break;
			}
			
			try
			{
				rightValue = Double.parseDouble (split[1]);
				calc.setRightValue (rightValue);
			}
			catch(Exception ex)
			{
				
			}
		}//end if
		
	}//end setRightValue()
	
	/**
	 * This method is called by functionListener when the add (+) JButton is pressed or by the 
	 * keyListener when + key is entered in the JTextField. Intended to determine that the ADD 
	 * function will be performed and then set leftValue in the Calculations class.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void addition()
	{
		Fun = Function.ADD;	//Function set to determine what action should be taken later. 
		
			setLeftValue();
			entry.grabFocus();
		
	}//end of addition()
	
	/**
	 * This method is called by functionListener when the subtract (-) JButton is pressed or by the 
	 * keyListener when - key is entered in the JTextField. Intended to determine that the SUBTRACT
	 * function will be performed and then set leftValue in the Calculations class.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void subtraction()
	{
		Fun = Function.SUBTRACT; //Function set to determine what action should be taken later.
		
			setLeftValue();
			entry.grabFocus();
		
	}//end of subtract()
	
	/**
	 * This method is called by functionListener when the multiply (*) JButton is pressed or by the 
	 * keyListener when * key is entered in the JTextField. Intended to determine that the MULTIPLY
	 * function will be performed and then set leftValue in the Calculations class.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void multiplication()
	{
		Fun = Function.MULTIPLY; //Function set to determine what action should be taken later.
		
			setLeftValue();
			entry.grabFocus();
		
	}//end multiplication()
	
	/**
	 * This method is called by functionListener when the divide (/) JButton is pressed or by the 
	 * keyListener when / key is entered in the JTextField. Intended to determine that the DIVIDE
	 * function will be performed and then set leftValue in the Calculations class.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void division()
	{
		Fun = Function.DIVIDE; //Function set to determine what action should be taken later.
		
			setLeftValue();
			entry.grabFocus();
		
	}//end division()
	
	/**
	 * Takes the square root of the number currently in the text field. Calls the updateText() and
	 * setLeftValue() methods so that the result of the calculation is displayed and the program is 
	 * immediately ready to perform another calculation. Only does this when the Function variable 
	 * "Fun" is set to null. This ensures that there is not another calculation waiting to be done.
	 * Future version may allow this to be included in chain calculations.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void squareRoot()
	{
		if(Fun == null)
		{
			setLeftValue();
			result = calc.squareRoot ();
			updateText();
			setLeftValue();
		}
	}//end squareRoot()
	
	/**
	 * Takes the third root of the number currently in the text field. Calls the updateText() and
	 * setLeftValue() methods so that the result of the calculation is displayed and the program is 
	 * immediately ready to perform another calculation. Only does this when the Function variable 
	 * "Fun" is set to null. This ensures that there is not another calculation waiting to be done.
	 * Future version may allow this to be included in chain calculations.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void cubeRoot()
	{
		if(Fun == null)
		{
			setLeftValue();
			result = calc.cubeRoot ();
			updateText();
			setLeftValue();
		}
	}//end squareRoot()
	
	/**
	 * Squares the number currently in the text field. Calls the updateText() and setLeftValue()
	 * methods so that the result of the calculation is displayed and the program is immediately 
	 * ready to perform another calculation. Only does this when the Function variable "Fun" is set
	 * to null. This ensures that there is not another calculation waiting to be done. Future 
	 * version may allow this to be included in chain calculations.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void xSquared()
	{
		if(Fun == null)
		{
			setLeftValue();
			result = calc.squared ();
			updateText();
			setLeftValue();
		}
	}//end xSquared()
	
	/**
	 * Cubes the number currently in the text field. Calls the updateText() and setLeftValue()
	 * methods so that the result of the calculation is displayed and the program is immediately 
	 * ready to perform another calculation. Only does this when the Function variable "Fun" is set
	 * to null. This ensures that there is not another calculation waiting to be done. Future 
	 * version may allow this to be included in chain calculations.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void xCubed()
	{
		if(Fun == null)
		{
			setLeftValue();
			result = calc.cubed ();
			updateText();
			setLeftValue();
		}
	}//end xCubed()
	
	/**
	 * Performs arithmetic on a left value and a percentage of that left value. I.e., percentage 
	 * button pressed when text in the text field reads 10 + 20 will return 10 + 2. 
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void percent()
	{
		Double percent;		//holds the right value percentage of the left value
		String newText = "";//Holds the updated text 
		if(calc.getLeftValue ( ) != 0.0 && Fun != null)
		{
			setRightValue();
			percent = calc.getRightValue() * 0.01 * calc.getLeftValue();
			leftValue = calc.getLeftValue();
			newText += leftValue;
			switch (Fun)
			{
				case ADD:
					newText += " + ";
					break;
				case SUBTRACT:
					newText += " - ";
					break;
				case DIVIDE:
					newText += " / ";
					break;
				case MULTIPLY:
					newText += " * ";
					break;
			}//end switch
			
			newText += " " + percent;
			entry.setText(newText);
			
		}//end if
		
	}//end of percent()
	
	/**
	 * Inserts the value of PI into the appropriate place in the current calculation, left or right
	 * of an arithmetic function, if any. 
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void PI()
	{	
		String PI = "";			//Holds double which is approximated to the value of PI
		String fieldText = "";	//Holds the text currently in "entry". Used when leftValue is full.
		double PIE = Math.PI;	//Decimal approximation of PI.
		PI += PIE;				//Decimal approximation of PI inserted into a String
		
		if(Fun == null && calc.getLeftValue ( ) == 0.0)
		{
			entry.setText (PI);
			setLeftValue();
		}
		else if (calc.getLeftValue ( ) != 0.0)
		{
			fieldText = entry.getText();
			fieldText += " " + PI;
			entry.setText(fieldText);
			setRightValue();
		}
	}//end PI()
	
	/**
	 * Inserts the value of e (Euler's number) into the appropriate place in the current
	 * calculation, left or right of an arithmetic function, if any. 
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void E()
	{	
		String E = "";			//Holds double which is approximated to the value of e
		String fieldText = "";	//Holds the text currently in "entry". Used when leftValue is full.
		double e = Math.E;		//Decimal approximation of e.
		E += e;					//Decimal approximation of e inserted into a String
		
		if(Fun == null && calc.getLeftValue ( ) == 0.0)
		{
			entry.setText (E);
			setLeftValue();
		}
		else if (calc.getLeftValue ( ) != 0.0 && calc.getRightValue ( ) == 0.0)
		{
			fieldText = entry.getText();
			fieldText += " " + E;
			entry.setText(fieldText);
			setRightValue();
		}
	}//end PI()
	
	/**
	 * Raises decimal 2 to the power of the number currently in the text field. Calls the 
	 * updateText() and setLeftValue() methods so that the result of the calculation is displayed 
	 * and the program is immediately ready to perform another calculation. Only does this when the
	 * Function variable "Fun" is set to null. This ensures that there is not another calculation
	 * waiting to be done. Future version may allow this to be included in chain calculations.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void twoPower()
	{
		if(Fun == null)
		{
			setLeftValue();
			result = calc.twoPower ( );
			updateText();
		}
	}//end twoPower()
	
	/**
	 * Takes the natural log of the number currently in the text field. Calls the updateText() and
	 * setLeftValue() methods so that the result of the calculation is displayed and the program is 
	 * immediately ready to perform another calculation. Only does this when the Function variable 
	 * "Fun" is set to null. This ensures that there is not another calculation waiting to be done.
	 * Future version may allow this to be included in chain calculations.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void naturalLog()
	{
		if(Fun == null)
		{
			setLeftValue();
			result = calc.naturalLog ( );
			updateText();
		}
	}//end twoPower()
	
	/**
	 * Raises e to the power of the number currently in the text field. Calls the updateText() and
	 * setLeftValue() methods so that the result of the calculation is displayed and the program is 
	 * immediately ready to perform another calculation. Only does this when the Function variable 
	 * "Fun" is set to null. This ensures that there is not another calculation waiting to be done.
	 * Future version may allow this to be included in chain calculations.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void eToPower()
	{
		if(Fun == null)
		{
			setLeftValue();
			result = calc.eToPower ();
			updateText();
			setLeftValue();
		}
	}//end xCubed()
	
	/**
	 * Performs negation on the current number if there is no arithmetic currently being performed. 
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void negate()
	{
		if(Fun == null)
		{
			setLeftValue();
			result = calc.negate();
			updateText();
			setLeftValue();
		}
	}//end of negate()
	
	/**
	 * Takes the reciprocal of a number if there is no arithmetic currently being performed.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */

	private void reciprocal()
	{
		if(Fun == null)
		{
			setLeftValue();
			result = calc.reciprocal ( );
			updateText();
			setLeftValue();
		}
	}//end reciprocal()
		
	/**
	 * Determines which arithmetic function needs to be performed and evaluates that function
	 * using the Calculations class. Updates the text of the calculator text field and sets that 
	 * text as the leftValue. Resets the Function value Fun to null after the arithmetic is 
	 * performed.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void equate()
	{
			if(Fun == Function.ADD)
			{			
				result = calc.sum ( );
				updateText();
				setLeftValue();
				Fun = null;
			}//end ADD condition
			
			else if(Fun == Function.SUBTRACT)
			{
				result = calc.subtract ();
				updateText();
				setLeftValue();
				Fun = null;
			}//end SUBTRACT condition
			
			else if (Fun == Function.MULTIPLY)
			{
				result = calc.multiply ();
				updateText();
				setLeftValue();
				Fun = null;
			}//end MULTIPLY condition
			
			else if (Fun == Function.DIVIDE)
			{
				result = calc.divide ();
				updateText();
				setLeftValue();
				Fun = null;
			}
				
	}//end of equate()
	
	/**
	 * Determines whether the double that is returned has any values to the right of the decimal
	 * place by taking the value mod 1 (I.e., is there a remainder when dividing by 1?). If there
	 * is no remainder, the value is casted to a long and appended to a String. (This ensures that
	 * integer values are simply displayed as integers with no decimal points.) Otherwise, the value
	 * is appended to the String as a double. Text in the calculator text field is then set to the 
	 * value of the String. 
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void updateText()
	{
		long longResult;		//holds the value of result casted to type long
		if(result % 1 == 0)
		{
			longResult = (long)result;
			updateText += longResult;
		}
		else
		{
			updateText += result;
		}
		entry.setText ("");
		entry.setText (updateText);
		updateText = "";
		entry.grabFocus();
	}//end of updateText()
	
	/**
	 * This is used with number buttons and the decimal point button. When one of those buttons is 
	 * pressed, the character value "append" is set to the appropriate character and added to the 
	 * text field.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void appendText()
	{
		String fieldText = "";
		String newText = "";
		fieldText = entry.getText();
		
		if(fieldText.equals ("0"))
		{
			newText += append;
			fieldText = newText;
			entry.setText (fieldText);
		}
		else
		{
			fieldText += append;
			entry.setText (fieldText);
		}
		
		entry.grabFocus ( );
		
	}//end appendText()
	
	/**
	 * Listener class which serves all of the buttons which represent whole numbers. Sole purpose
	 * of this class is to set the character variable "append" to the appropriate value (I.e. '1' 
	 * when button 1 is pressed) and call the appendText() method. This will add the value to the 
	 * text displayed by the calculator and perform a live update. 
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private class numberListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			if(e.getSource ( ) == one)
			{
				append = '1';
				appendText();
			}
			else if(e.getSource ( ) == two)
			{
				append = '2';
				appendText();
			}
			else if(e.getSource ( ) == three)
			{
				append = '3';
				appendText();
			}
			else if(e.getSource ( ) == four)
			{
				append = '4';
				appendText();
			}
			else if(e.getSource ( ) == five)
			{
				append = '5';
				appendText();
			}
			else if(e.getSource ( ) == six)
			{
				append = '6';
				appendText();
			}
			else if(e.getSource ( ) == seven)
			{
				append = '7';
				appendText();
			}
			else if(e.getSource ( ) == eight)
			{
				append = '8';
				appendText();
			}
			else if(e.getSource ( ) == nine)
			{
				append = '9';
				appendText();
			}
			else if(e.getSource ( ) == zero)
			{
				append = '0';
				appendText();
			}
		}
	}//end of numberListener()
	
	/**
	 * This listener class serves all Menu selections. 
	 * - When the "About" menu item is selected, the 
	 * 	 actionPerformed method creates an instance of the AboutDialog class and displays it. 
	 * - When the "Exit" menu item is selected, a confirm dialog is displayed. If the user selects
	 * 	 "Yes", the dispose() method, which is inherited from java.awt.Window, releases all native
	 * 	 screen resources used by CalculatorWindow, its subcomponents, and its children. (Full exit
	 * 	 is performed). 
	 * - When the "Settings" menu item is selected, an instance of the SettingsDialog class is 
	 * 	 created. Methods getting the values of Settings selections follow the instance creation.
	 * - When the "Readme" menu item is selected, The Readme text file is opened for viewing by the
	 * 	 user.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private class menuListener implements ActionListener
	{
		int exitChoice;	//holds value returned by the confirm dialog called by the "Exit" menu item
		Color bgColor;	//Holds the color choice returned from the color menu in the Settings dialog
		boolean isIncognito;//True if the user selected Incognito mode, false if selected default
		
		public void actionPerformed (ActionEvent e)
		{
			if(e.getActionCommand().equals("About") )
			{
				//Instance of the AboutDialog class created here
				new AboutDialog(CalculatorWindow.this);
			}
			else if (e.getActionCommand ( ).equals ("Exit"))
			{
				exitChoice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
						"Exit calculator", JOptionPane.YES_NO_OPTION);
				if(exitChoice == JOptionPane.YES_OPTION)
				{
					dispose();
				}
			}
			else if (e.getActionCommand ( ).equals ("Settings"))
			{
				//instance of the SettingsDialog class created here
				SettingsDialog settings = new SettingsDialog(CalculatorWindow.this);
					
				isIncognito = settings.isIncognito();
				bgColor = settings.getColor ( );
				if(settings.getSizeOption()==0)
				{
					setResize(0);
				}
				else if(settings.getSizeOption()==1)
				{
					setResize(1);
				}
				else if(settings.getSizeOption()==2)
				{
					setResize (2);
				}
				if(bgColor == white)
				{
					resetIncognito();
				}
				else
				{
					setBgColor(bgColor);
				}			
				if(isIncognito == true && incognito == false)
				{
					setIncognito();
					incognito = true;
				}
				else if (isIncognito == false && incognito == true 
								&& settings.getColorChosen ( ) == false)
				{
					resetIncognito();
					incognito = false;
				}
			}
			else if (e.getActionCommand().equals("Readme"))
			{
				try
				{
					Runtime.getRuntime().exec("notepad src/Readme.txt");
				}
				catch(IOException ex)
				{
					
				}
			}
		}//end of actionPerformed()
		
	}//end of menuListener class
	
	/**
	 * This listener serves all button functions of the Calculator. Arithmetic functions are only
	 * performed when the Function variable is set to null. This prevents chain calculations. Chain
	 * calculations may be implemented in a future release. 
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private class functionListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			String fieldText;
			String strAction = e.getActionCommand();
			if (strAction.equals("+") && Fun == null)
			{
				try
				{
					addition();
					fieldText = entry.getText();
					fieldText += " + ";
					entry.setText(fieldText);
				}
				catch(Exception ex)
				{
					
				}
				
			}
			else if (strAction.equals ("-") && Fun == null)
			{
				try
				{
					subtraction();
					fieldText = entry.getText ( );
					fieldText += " - ";
					entry.setText(fieldText);
				}
				catch(Exception ex)
				{
					
				}
			}
			else if (strAction.equals ("*") && Fun == null)
			{
				try
				{
					multiplication();
					fieldText = entry.getText ( );
					fieldText += " * ";
					entry.setText(fieldText);
				}
				catch(Exception ex)
				{
					
				}
			}
			else if (strAction.equals ("/") && Fun == null)
			{
				try
				{
					division();
					fieldText = entry.getText ( );
					fieldText += " / ";
					entry.setText(fieldText);
				}
				catch(Exception ex)
				{
					
				}
			}
			else if(strAction.equals("="))
			{
				if(calc.getLeftValue() != 0.0)
				{
					setRightValue();
					equate();
				}
			}
			else if (e.getSource() == C)
			{
				entry.setText("0");
				entry.grabFocus ( );
				entry.selectAll();
				Fun = null;
				calc.reset();
			}
			else if(e.getSource ( ) == decimalPoint)
			{
				append = '.';
				appendText();
			}
			else if(e.getSource ( ) == PI)
			{
				 PI();
			}
			else if(strAction.equals ("\u212F") )
			{
				E();
			}
			else if (e.getSource() == negate)
			{
				negate();
			}
			else if (e.getSource() == squareRoot)
			{
				squareRoot();
			}
			else if (e.getSource() == cubeRoot)
			{
				cubeRoot();
			}
			else if (e.getSource() == xSquare)
			{
				xSquared();
			}
			else if (e.getSource() == xCube)
			{
				xCubed();
			}
			else if(e.getSource() == twoPower)
			{
				twoPower();
			}
			else if (e.getSource() == ln)
			{
				naturalLog();
			}
			else if (e.getSource() == eToPower)
			{
				eToPower();
			}
			else if (e.getSource ( ) == percent)
			{
				percent();
			}
			else if (e.getSource() == del)
			{
				Document doc = entry.getDocument ( );	//gets the text in the document "entry"
				int position;							//holds the length of the text in "entry"
				String strDoc = "";								
				position = doc.getLength() - 1;
				
				try
				{
					doc.remove (position, 1);
					strDoc = doc.getText(0, doc.getLength());
					if(strDoc.equals(""))
					{
						entry.setText ("0");
						entry.grabFocus ( );
						entry.selectAll();
					}
					else 
					{
						entry.setText(strDoc);
					}
					
					
				}
				catch(BadLocationException ex)
				{
					
				}
			}
			else if	(e.getSource ( ) == reciprocal)
			{
				reciprocal();
			}
			entry.grabFocus();				
		}//end of actionPerformed
		
	}//end of functionListener
	
	/**
	 * This class serves all registered KeyListeners. 
	 * Declares a constant String of characters that will be used to disallow certain characters 
	 * from being entered into the "entry" text field. This listener also disallows arithmetic 
	 * functions from being performed when the Function variable Fun is not null. Also uses the 
	 * consume method to destroy the arithmetic characters input when Fun is not null.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 7, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private class keyListener implements KeyListener
	{
		//This constant String represents all characters which will not be allowed into the 
		//calculator text field.
		final static String illegalChars = "!@#$%^&()_{}[]:\"\\;'<>?,`~ ";
		
		@Override
		public void keyPressed (KeyEvent e)
		{
			
			
		}//end of keyPressed()

		@Override
		public void keyReleased (KeyEvent e)
		{
			
			
		}//end of keyReleased

		@Override
		public void keyTyped (KeyEvent e)
		{
			String fieldText = "";			//holds the text in the text field of the calculator
			char keyChar = e.getKeyChar();	//gets the KeyChar from KeyEvent
			
			fieldText = entry.getText();				
			
			if(fieldText.equals (""))
			{
				entry.setText ("0");
				entry.grabFocus ( );
				entry.selectAll ( );
			}
									
			if(Character.isLetter (keyChar) || illegalChars.indexOf(keyChar) > -1)
			{
				e.consume();
			}
			else if(keyChar == '+')
			{
				if(Fun != null)
				{		
					e.consume ( );
				}
				else
				{
					addition();
				}
			}
			else if(keyChar == '-')
			{
				if(Fun != null)
				{		
					e.consume ( );
				}
				else
				{
					subtraction();
				}
			}
			else if(keyChar == '*')
			{
				if(Fun != null)
				{		
					e.consume ( );
				}
				else
				{
					multiplication();
				}
			}
			else if(keyChar == '/')
			{
				if(Fun != null)
				{		
					e.consume ( );
				}
				else
				{
					division();
				}
			}
			else if(keyChar == '=')
			{
				equate();		
			}
			
		}//end of keyTyped()
		
	}//end of keyListener()
	
}//end CalculatorWindow.java