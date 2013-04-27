/**
 * ---------------------------------------------------------------------------
 * File name: SettingsDialog.java<br/>
 * Project name: Calculator<br/>
 * ---------------------------------------------------------------------------
 * Creator's name and email: Jeremy Musick, MusickJ@Goldmail.ETSU.edu
 * 							 William Horne, hornew@goldmail.etsu.edu <br/>
 * Course:  CSCI 1260<br/>
 * Creation Date: Apr 15, 2012<br/>
 * Date of Last Modification: Apr 24, 2012
 * ---------------------------------------------------------------------------
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Dialog created to display a settings selection window to the user. Options include "Incognito" 
 * mode, color appearance of the calculator, and size / resizing options.<br>
 *
 * <hr>
 * Date created: Apr 15, 2012<br>
 * Date last modified: Apr 15, 2012<br>
 * <hr>
 * @author Jeremy Musick
 */


public class SettingsDialog extends JDialog
{
	private static final long serialVersionUID = 1L;  //Allows extension from JFrame
	private JPanel 	facePanel,						  //Holds the panels
					northWestPanel,					  
					northEastPanel,
					southWestPanel,
					southEastPanel;
	private String[] sizes = {"Default", "Enabled", "Disabled"};//Holds the names for size states
	private String[] sizeChange={"", "Large", "Small"};             //Holds the names for the sizes
	@SuppressWarnings ({ "rawtypes" })								
	private JComboBox sizeBox;										//Holds the size box
	@SuppressWarnings ({ "rawtypes" })
	private JComboBox sizeBox2;										//Holds the other size box
	private GridLayout faceLayout = new GridLayout(2,2);			//Holds the layout
	private JButton colorsChoice;									//Holds the background option
	private JRadioButton 	defaultAppearance,						//Holds the radio buttons
							incognitoAppearance;					
	private ButtonGroup group;								//Holds the radio button group
	private Color selectedColor = Color.WHITE;				//hold the currently selected color
	private boolean incognito,								//Turns "incognito" on and off
					colorChosen = false;					//determines if color has been chosen
	private int iSizeOption=0;								//Keeps up with size options
	private String strSizeOption=null;						//Keeps size option in string format
	private int iSizeOption2=0;								//Keeps up with more size options
	
	/**
	 * Constructor <br>        
	 *
	 * <hr>
	 * Date created: Apr 13, 2012 <br>
	 * Date last modified: Apr 13, 2012 <br>
	 *
	 * <hr>
	 */
	public SettingsDialog(JFrame parent)
	{
		super(parent, "Settings", true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(1,1));		
				
		addComponents();
		createPanels();
		registerComboListener();
		add(facePanel);
				
		this.setResizable(false);
		pack();
		setLocationRelativeTo(parent);
		setVisible (true);
		
	}//end of Constructor
	
	/**
	 * Adds the components to be used  <br>        
	 *
	 * <hr>
	 * Date created: Apr 13, 2012 <br>
	 * Date last modified: Apr 13, 2012 <br>
	 *
	 * <hr>
	 */
	@SuppressWarnings ({ "unchecked", "rawtypes" })
	private void addComponents()
	{
		sizeBox2=new JComboBox(sizeChange);
		
		sizeBox = new JComboBox (sizes);
		defaultAppearance = new JRadioButton("Default");
		incognitoAppearance = new JRadioButton("Incognito");
		incognitoAppearance.setBackground(Color.WHITE);
		defaultAppearance.setBackground(Color.WHITE);
		colorsChoice = new JButton("Pick A Color");
		group = new ButtonGroup();
		group.add(defaultAppearance);
		group.add (incognitoAppearance);
		
		incognito = false;
	}//end addComponents()
	

	/**
	 * Adds the panels to be used <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 */
	private void createPanels()
	{
		facePanel = new JPanel();
		northWestPanel = new JPanel();
		northEastPanel = new JPanel();
		southWestPanel = new JPanel();
		southEastPanel = new JPanel();	
		
		faceLayout.setVgap (5);
		faceLayout.setHgap(5);
		facePanel.setLayout(faceLayout);
		facePanel.setBackground(Color.GRAY );
		
		northEastPanel.setPreferredSize(new Dimension(100, 100));
		
		northEastPanel.setLayout (new GridLayout(2,2));
		southWestPanel.setLayout(new GridLayout(2,1));
		northWestPanel.setBackground(Color.WHITE );
		northEastPanel.setBackground(Color.WHITE );
		southWestPanel.setBackground(Color.WHITE );
		southEastPanel.setBackground(Color.WHITE );
		
		northWestPanel.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2), "Color Choice"
			, TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.ITALIC, 14)));
		northEastPanel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2), "Window size"
				, TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.ITALIC, 14)));
		southWestPanel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2), "Appearance"
				, TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.ITALIC, 14)));
		southEastPanel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2), "Resize"
				, TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.ITALIC, 14)));
		
		northWestPanel.add(colorsChoice);
		northEastPanel.add (sizeBox);
		southWestPanel.add (defaultAppearance);
		southWestPanel.add(incognitoAppearance);
		southEastPanel.add(sizeBox2);

		facePanel.add(northWestPanel);
		facePanel.add(northEastPanel);
		facePanel.add(southWestPanel);
		facePanel.add(southEastPanel);
	}//end of createPanels()
	
	
	/**
	 * Returns the value of incognito<br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public boolean isIncognito ( )
	{
		return incognito;
	}//end of isIncognito()
	
	/**
	 * Returns the value of selectedColor <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return selectedColor
	 */
	public Color getColor()
	{
		return selectedColor;
	}//end of getColor()
	
	/**
	 * Sets the value of the boolean variable colorChosen  <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 */
	public void setColorChosen(boolean color)
	{
		colorChosen = color;
	}//end of setColorChosen(boolean)
	
	/**
	 * Returns the value of the boolean variable colorChosen  <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 */
	public boolean getColorChosen()
	{
		return colorChosen;
	}//end of getColorChosen()
	
	/**
	 * Returns the value of iSizeOption <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public int getSizeOption()
	{
		return iSizeOption;
	}//end of getSizeOption()
	
	/**
	 * returns the value of iSizeOption2 <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 * @return
	 */
	public int getSizeOption2()
	{
		return iSizeOption2;
	}//end of getSizeOption2()
	
	/**
	 * Registers the listeners <br>        
	 *
	 * <hr>
	 * Date created: Apr 25, 2012 <br>
	 * Date last modified: Apr 25, 2012 <br>
	 *
	 * <hr>
	 */
	private void registerComboListener()
	{
		sizeBox2.addActionListener(new ComboBoxListener3());
		sizeBox.addActionListener(new ComboBoxListener2());
		incognitoAppearance.addActionListener(new RadioBtnListener());
		defaultAppearance.addActionListener(new RadioBtnListener());
		
		colorsChoice.addActionListener(new ColorListener());
	}//end of registerComboListener()
	
	
	/**
	 * Listener for sizeBox2<br>
	 *
	 * <hr>
	 * Date created: Apr 25, 2012<br>
	 * Date last modified: Apr 25, 2012<br>
	 * <hr>
	 * @author Jeremy Musick
	 */
	private class ComboBoxListener3 implements ActionListener
	{
		/**
		 * Actions performed for sizeBox2 <br>        
		 *
		 * <hr>
		 * Date created: Apr 25, 2012 <br>
		 * Date last modified: Apr 25, 2012 <br>
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String strSizeOption1= (String) sizeBox2.getSelectedItem();
			if(strSizeOption1.equals (""))
			{
				iSizeOption2=0;
			}//end of if
			else if(strSizeOption1.equals("Large"))
			{
				iSizeOption2=1;
			}//end of else if
			else if(strSizeOption1.equals ("Small"))
			{
				iSizeOption2=2;
			}//end of else if
		}//end of actionPerformed(ActionEvent)
	}//end of ComboBoxListener3
	
	
	/**
	 * Listeners for sizeBox<br>
	 *
	 * <hr>
	 * Date created: Apr 25, 2012<br>
	 * Date last modified: Apr 25, 2012<br>
	 * <hr>
	 * @author Jeremy Musick
	 */
	private class ComboBoxListener2 implements ActionListener
	{

		/**
		 * Actions performed for sizeBox <br>        
		 *
		 * <hr>
		 * Date created: Apr 25, 2012 <br>
		 * Date last modified: Apr 25, 2012 <br>
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			strSizeOption= (String) sizeBox.getSelectedItem ( );
			if(strSizeOption.equals("Enabled"))
			{
				iSizeOption=1;
			}//end of if
			else if(strSizeOption.equals("Disabled"))
			{
				iSizeOption=0;
			}//end of else if
			else if(strSizeOption.equals("Default"))
			{
				iSizeOption=2;
			}//end of else if
		}//end of actionPerformed(ActionEvent)
	}//end of ComboBoxListener2
	
	/**
	 * Listener for colorsChoice<br>
	 *
	 * <hr>
	 * Date created: Apr 25, 2012<br>
	 * Date last modified: Apr 25, 2012<br>
	 * <hr>
	 * @author Jeremy Musick
	 */
	private class ColorListener implements ActionListener
	{
		/**
		 * Actions performed for colorsChoice <br>        
		 *
		 * <hr>
		 * Date created: Apr 25, 2012 <br>
		 * Date last modified: Apr 25, 2012 <br>
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@SuppressWarnings ("static-access")
		public void actionPerformed(ActionEvent e)
		{
			JColorChooser choice=new JColorChooser();
			selectedColor=choice.showDialog(null, "Pick A Color", Color.WHITE);
			if(selectedColor != null)
			{
				colorChosen = true;
			}
		}//end of actionPerformed(ActionEvent)
	}//end of ColorListener
	
	
	
	/**
	 * Radio button listener<br>
	 *
	 * <hr>
	 * Date created: Apr 25, 2012<br>
	 * Date last modified: Apr 25, 2012<br>
	 * <hr>
	 * 
	 */
	private class RadioBtnListener implements ActionListener
	{

		/**
		 * Actions performed for the radio buttons <br>        
		 *
		 * <hr>
		 * Date created: Apr 25, 2012 <br>
		 * Date last modified: Apr 25, 2012 <br>
		 *
		 * <hr>
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed (ActionEvent e)
		{
			
			if(e.getSource() == defaultAppearance);
			{
				incognito = false;
			}//end of if
			if(e.getSource() == incognitoAppearance)
			{
				incognito = true;
			}//end of if
		}//end actionPerformed()		
	}//end RadioBtnListener class	
}//end SettingsDialog.java