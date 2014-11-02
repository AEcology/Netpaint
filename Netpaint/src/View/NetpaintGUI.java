package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.Canvas;
import Shapes.NPShape;
/**
 *  SR (Snavely, Rodriguez) Paint!<br>
 * 
 *	NetpaintGUI is the frame for the Netpaint program view.
 *  Contains a drawing canvas, a selectable color pallette, and private inner classes
 *  to support {@link ActionListener} for radio select buttons, and {@link ChangeListener} for pallette color selection. 
 *  Inclused main method. 
 *  @author Anthony Rodriguez, Jonathan Snavely<br>
 *  
 */
@SuppressWarnings("serial")
public class NetpaintGUI extends JFrame{

	//For canvasGUI
	private Canvas canvas; 
	private JColorChooser pallette;
	private JScrollPane canvasView;
	private ShapeListener shapeListener;
	private JPanel shapePanel;
	private ButtonGroup buttons;
	private JRadioButton lineButton;
	private JRadioButton ovalButton;
	private JRadioButton rectangleButton;
	private JRadioButton imageButton;
	private static String lineString = "Line";
	private static String ovalString = "Oval";
	private static String rectangleString = "Rectangle";
	private static String imageString = "Image";
	
	//For loginGUI
	private JButton submit;
	private JPanel textView;
	private JTextField IPAddressField;
	private JTextField portField;
	private JTextField usernameField;
	private String IPAddress;
	private String port;
	private String username;
	
	
	/**
	 * Constructor arranges the components on the JFrame, and assigns listeners to the color pallette and 
	 * buttons.
	 */
	public NetpaintGUI(){
		setupLoginGUI();
	}
	
		
	/**
	 * This method is invoked to layout the beginning log in screen, which includes fields to enter IP address and 
	 * port of the server, and their username.
	 */
	public void setupLoginGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,200);
		setLocation(200,100);	
		setLayout(new BorderLayout());
		setTitle("Connect to Server!");
		initializeVariables();
		
		textView.setLayout(new GridLayout(3,2));
		textView.add(new JLabel("IP ADDRESS"));
		textView.add(IPAddressField);
		textView.add(new JLabel("PORT NUMBER"));
		textView.add(portField);
		textView.add(new JLabel("USERNAME"));
		textView.add(usernameField);		
		
		add(textView, BorderLayout.CENTER);
		add(submit, BorderLayout.SOUTH);
		
		//add(new StartupView(), BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	/**
	 * Initialize everything but the add commands
	 */
	public void initializeVariables(){
		submit = new JButton("Submit");
		textView = new JPanel();
		IPAddressField = new JTextField();
		portField = new JTextField();
		usernameField = new JTextField();
		submit.addActionListener(new ButtonListener());
	}
	
	/**
	 * Listener class for the submit button
	 */
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: verify all fields filled properly
			
			//TODO: send username String to the server
			IPAddress = IPAddressField.getText();
			port = portField.getText();
			username = usernameField.getText();
			
			//Do not continue if empty field still exists
			if(IPAddress.equals("") || port.equals("") || username.equals("")){
				System.out.println("Empty field");
				return;
			}	
			System.out.println(IPAddressField.getText());
			System.out.println(portField.getText());
			System.out.println(usernameField.getText());
			setupCanvasGUI();
		}	
	}	
	
	
	/**
	 * This method is invoked after the user has finished with the startup screen
	 */
	public void setupCanvasGUI(){
		//Destroy old window
		remove(textView);
		remove(submit);
				
		//Create new view
		shapeListener = new ShapeListener();
		canvas = new Canvas();
		pallette = new JColorChooser();
		pallette.getSelectionModel().addChangeListener(new PalletteListener());
		canvasView = new JScrollPane(canvas);
		this.setTitle("SR Netpaint!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocation(200,100);
		canvas.repaint();
		
		buttons = new ButtonGroup();
		lineButton = new JRadioButton(lineString);
		lineButton.setActionCommand(lineString);
		lineButton.addActionListener(shapeListener);
		ovalButton = new JRadioButton(ovalString);
		ovalButton.setActionCommand(ovalString);
		ovalButton.addActionListener(shapeListener);
		rectangleButton = new JRadioButton(rectangleString);
		rectangleButton.setActionCommand(rectangleString);
		rectangleButton.addActionListener(shapeListener);
		imageButton = new JRadioButton(imageString);
		imageButton.setActionCommand(imageString);
		imageButton.addActionListener(shapeListener);
		buttons.add(lineButton);
		buttons.add(ovalButton);
		buttons.add(rectangleButton);
		buttons.add(imageButton);
		shapePanel = new JPanel(new GridLayout(1,4));
		shapePanel.add(lineButton);
		shapePanel.add(ovalButton);
		shapePanel.add(rectangleButton);
		shapePanel.add(imageButton);
		
		canvas.setDrawMode(pallette.getColor(), lineString);
		
		this.add(canvasView, BorderLayout.CENTER);
		this.add(shapePanel, BorderLayout.PAGE_START);
		this.add(pallette, BorderLayout.PAGE_END);
		
		this.setVisible(true);	
	}

		
	/**
	 * A listener object used for detecting changes of selection in a color pallette
	 */
	class PalletteListener implements ChangeListener {
		
		/**
		 * This method is called whenever a user changes their selection on the color pallette. 
		 * The Canvas is updated accordingly.
		 */		
	    public void stateChanged(ChangeEvent e) {  	
	    	canvas.setColor(pallette.getColor());
	    }	
	}

	/**
	 * A listener object for detecting changes made to the shape selection button. 
	 * The canvas is updated accordingly.
	 */
	private class ShapeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.setDrawMode(pallette.getColor(), e.getActionCommand());
		}		
	}

	/**
	 * Used by subclasses to obtain reference to internal canvas object
	 * @return
	 */
	protected Canvas getCanvas(){
		return this.canvas;
	}
	
	/**
	 * Update GUI with shapes list <br>
	 * Called from server/client communication thread.<br>
	 * Relays shapes array to Canvas
	 * @param shapes
	 */
	public void update(ArrayList<NPShape> shapes) {
		canvas.update(shapes);		
	}
	
	public static void main(String[] args){
		new NetpaintGUI();
	}
}
