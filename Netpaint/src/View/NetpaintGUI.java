package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import Model.Canvas;
import Shapes.NPShape;
/**
 * @author Anthony Rodriguez, Jonathan Snavely
 *	The main super GUI for the Netpaint program view
 *	Includes "main"
 *  SR (Snavely, Rodriguez) Paint!
 */
public class NetpaintGUI extends JFrame{
	private Canvas canvas; 
	private JColorChooser pallette;
	private JScrollPane canvasView;
	
	private ShapeListener shapeListener;
	private JPanel shapePanel;
	private JPanel drawPanel;
	private ButtonGroup buttons;
	private JRadioButton lineButton;
	private JRadioButton ovalButton;
	private JRadioButton rectangleButton;
	private JRadioButton imageButton;
	private static String lineString = "Line";
	private static String ovalString = "Oval";
	private static String rectangleString = "Rectangle";
	private static String imageString = "Image";
	
	
	//Main functionality
	public NetpaintGUI(){
		//Mouse
		
		shapeListener = new ShapeListener();
		canvas = new Canvas();
		pallette = new JColorChooser();
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
		
		
	
	private class ShapeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.setDrawMode(pallette.getColor(), e.getActionCommand());
		}		
	}
	
	public static void main(String[] args){
		new NetpaintGUI();
	}
}
