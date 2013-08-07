/**
 * ---------------------------------------------------------------------------
 * File name: AboutDialog.java<br/>
 * Project name: Calculator<br/>
 * ---------------------------------------------------------------------------
 * Creator's name and email: William Horne, hornew@goldmail.etsu.edu<br/>
 * Course:  CSCI 1260<br/>
 * Creation Date: Apr 13, 2012<br/>
 * Date of Last Modification: Apr 13, 2012
 * ---------------------------------------------------------------------------
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
/**
 * Serves as an "about" dialog for the calculator. Displays information about the program and the
 * developers.<br>
 *
 * <hr>
 * Date created: Apr 13, 2012<br>
 * Date last modified: Apr 13, 2012<br>
 * <hr>
 * @author William Horne
 */
public class AboutDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	private JLabel 	version, //displays the version number of the program
	 				jeremy,	 //displays Jeremy Musick's name and major
	 				jEmail,	 //displays Jeremy's e-mail address 
	 				aEmail,  //displays Austin's e-mail address
	 				austin,  //displays William (Austin) Horne's name and major
	 				blank,   //divider label placed between the information of the 2 developers
	 				copyright;//displays copyright information
	
	private JPanel 	devPanel, //JLabels which describe developer information placed on this panel
					versionPanel,//JLabel which describes version placed on this panel
					copyrightPanel;//copyright JLabel placed on this panel
	
	/**
	 * Constructor <br>        
	 * super constructor called to set parent window (which has been set as CalculatorWindow when 
	 * this dialog is called in the CalculatorWindow class), name of dialog, and modal. Default 
	 * close operation and layout set here. All panels and components created and added here. 
	 * Resizable set to false and location set relative to the parent window (CalculatorWindow). 
	 * Visibility set to true.
	 * <hr>
	 * Date created: Apr 13, 2012 <br>
	 * Date last modified: Apr 13, 2012 <br>
	 *
	 * <hr>
	 */
	public AboutDialog(JFrame parent)
	{
		super(parent, "About", true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		createPanels();
		createComponents();
		
		add(versionPanel, BorderLayout.NORTH );
		add(devPanel, BorderLayout.CENTER);
		add(copyrightPanel, BorderLayout.SOUTH);
		
		this.setResizable (false);
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
		
	}//end constructor
	
	/**
	 * All labels created here and added to the appropriate panels
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void createComponents()
	{
		version = new JLabel("1.1");
		
		jeremy = new JLabel("Jeremy Musick - CS / IT");
		jEmail = new JLabel("	musickj@goldmail.etsu.edu");
		austin = new JLabel("William (Austin) Horne - CS");
		aEmail = new JLabel("hornew@goldmail.etsu.edu");
		copyright = new JLabel("\u00A9 2012");
		blank = new JLabel(" ");
		
		versionPanel.add(version);
		
		devPanel.add(jeremy);
		devPanel.add (jEmail);
		devPanel.add(blank);
		devPanel.add(austin);
		devPanel.add(aEmail);
		copyrightPanel.add(copyright);
	}//end createComponents()
	
	/**
	 * All panels created here with layouts, background, and borders set. BorderFactory class used
	 * to set a titled border.
	 *  <br>        
	 *
	 * <hr>
	 * Date created: Apr 6, 2012 <br>
	 * Date last modified: Apr 10, 2012 <br>
	 *
	 * <hr>
	 */
	private void createPanels()
	{
		devPanel = new JPanel();
		versionPanel = new JPanel();
		copyrightPanel = new JPanel();
		
		devPanel.setLayout (new GridLayout(5,1) );
		versionPanel.setLayout(new GridLayout(1,1));
		copyrightPanel.setLayout(new GridLayout(1,1));
		
		devPanel.setBackground(Color.WHITE );
		versionPanel.setBackground(Color.WHITE );
		copyrightPanel.setBackground(Color.WHITE );
		
		versionPanel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2), "Version"
				, TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.ITALIC, 14)));
		
		devPanel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2), "Developers"
				, TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.ITALIC, 14)));
		
		copyrightPanel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2), "Copyright"
				, TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.ITALIC, 14)));
	}
}//end AboutDialog.java