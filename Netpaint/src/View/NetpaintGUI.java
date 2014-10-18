package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

import Model.Canvas;
/**
 * @author Anthony Rodriguez, Jonathan Snavely
 *	The main super GUI for the Netpaint program view
 *	Includes "main"
 *  SR (Snavely, Rodriguez) Paint!
 */
public class NetpaintGUI extends JFrame{
	private Canvas canvas; 
	private JColorChooser pallette;
	
	//Main functionality
	public NetpaintGUI(){
		canvas = new Canvas();
		pallette = new JColorChooser();
		setParams();
		this.add(canvas, BorderLayout.CENTER);
		this.add(pallette, BorderLayout.PAGE_END);
		this.setVisible(true);
	}
	
	/**
	 * Instantiate parameters such as sizes and locations of GUI components
	 */
	private void setParams(){
		this.setTitle("SR Netpaint!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocation(200,100);
		canvas.setBackground(Color.WHITE);
		canvas.repaint();
	}
	
	public static void main(String[] args){
		new NetpaintGUI();
	}
}
