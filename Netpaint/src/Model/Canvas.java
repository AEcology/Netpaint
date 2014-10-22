package Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import Shapes.NPImage;
import Shapes.NPLine;
import Shapes.NPOval;
import Shapes.NPRectangle;
import Shapes.NPShape;

/**
 * @author Anthony Rodriguez, Jonathan Snavely <br>
 * Canvas GUI <br>
 * Also acts as the action listener for {@link Shape} radio buttons
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel{
	/*
	 * What we need: scrollable JPanel portion, with shape selectors at the bottom
	 */
	
	//Mouse variables
	private int oldX, oldY, newX, newY;
	private boolean currentlyDrawing;
	
	
	private String currDrawMode = "Line";
	private Color currColor;
	private NPShape shapeBeingDrawn;
	private ArrayList<NPShape> shapesOnScreen;
	
	public Canvas(){
		super();
		shapesOnScreen = new ArrayList<NPShape>();
		currColor = Color.WHITE;
		currentlyDrawing = false;
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(800,600));
		MouseListener mListener = new MouseListen();
		MouseMotionListener motionmListener = new MouseListen();
		this.addMouseListener(mListener);
		this.addMouseMotionListener(motionmListener);
	}

	/**Inform canvas what color and shape the GUI
	 * is configured to draw 
	 */
	public void setDrawMode(Color c, String type){
		currDrawMode = type;
		currColor = c;
		System.out.println("DEBUG: Draw " + currDrawMode + " mode");
	}
	
	public void setColor(Color c){
		currColor = c;
	}
	
	/**
	 * Add a new shape to the Canvas arraylist of NPShapes <br>
	 * Shapes are added to canvas once the user has finished painting it
	 * @param shape: The {@link NPShape} to add to the canvas (permanently!)
	 */
	@SuppressWarnings("unused")
	private void addShape(NPShape shape){
		shapesOnScreen.add(shape);
		repaint();
	}
	
	/**
	 * Repaint all shapes on the canvas
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//TODO: This is throwing a null pointer exception
		for(NPShape s:shapesOnScreen)
			s.draw(g);
		
		if (currentlyDrawing && shapeBeingDrawn!=null)
			shapeBeingDrawn.draw(g);
	}
	
	private class MouseListen implements MouseMotionListener, MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			newX = arg0.getX();
			newY = arg0.getY();
			System.out.println(newX + " Clicked " + newY);
			if (!currentlyDrawing){
				switch(currDrawMode){
					case("Line"):
						shapeBeingDrawn = new NPLine(new Point(newX,newY), new Point(newX,newY), currColor);
						break;
					case("Rectangle"):
						shapeBeingDrawn = new NPRectangle(new Point(newX,newY), new Point(newX,newY), currColor);
						break;
					case("Oval"):
						shapeBeingDrawn = new NPOval(new Point(newX,newY), new Point(newX,newY), currColor);
						break;
					case("Image"):
						shapeBeingDrawn = new NPImage(new Point(newX,newY), new Point(newX,newY), currColor);
						break;
					default:
						shapeBeingDrawn = null;
						break;
				}
				currentlyDrawing = true;
			}
			
			//Add shapeBeingDrawn to arrayList & stop drawing
			else{
				shapesOnScreen.add(shapeBeingDrawn);
				currentlyDrawing = false;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
	        newX = arg0.getX();
	        newY = arg0.getY();
	        if (currentlyDrawing && shapeBeingDrawn!=null){
	        	shapeBeingDrawn.setEndPt(new Point(newX,newY));
	        	repaint();
	        }
		}
	}

}
