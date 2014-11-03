package Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Network.AddObjectCommand;
import Network.NetpaintGUI;
import Shapes.NPImage;
import Shapes.NPLine;
import Shapes.NPOval;
import Shapes.NPRectangle;
import Shapes.NPShape;

/**
 * @author Anthony Rodriguez, Jonathan Snavely <br>
 * Canvas JPanel <br>
 * Contains a drawing canvas, a selectable color pallette, and private inner classes
 * to support {@link MouseListener}, and {@link actionListener} for {@link Shape} radio buttons
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel{

	//Mouse variables
	private int newX, newY;
	private boolean currentlyDrawing;
	
	//User state variables
	private String currDrawMode = "Line";
	private Color currColor;
	private NPShape shapeBeingDrawn;
	
	//Previously drawn shapes (need to have to overlap old shapes with new)
	private ArrayList<NPShape> shapesOnScreen;
	
	//Connected GUI
	private NetpaintGUI gui;
	
	/**
	 * Adding appropriate mouse listeners, and adjusting settings for the JPanel look.
	 */
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

	/**
	 * Inform the canvas what color and shape are selected by the user
	 */
	public void setDrawMode(Color c, String type){
		currDrawMode = type;
		currColor = c;
		System.out.println("DEBUG: Draw " + currDrawMode + " mode");
	}
	
	public void setColor(Color c){
		currColor = c;
	}
	
	public void registerGUI(NetpaintGUI gui){
		this.gui = gui;
	}
	
	/**
	 * Add a new shape to the Canvas arraylist of NPShapes <br>
	 * Shapes are added to canvas once the user has finished painting it
	 * @param shape: The {@link NPShape} to add to the canvas (permanently!)
	 */
	public void addShape(NPShape shape){
		shapesOnScreen.add(shape);
		repaint();
	}
	
	/**
	 * Repaint all shapes on the canvas
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//Re-draw previously drawn shapes
		for(NPShape s:shapesOnScreen)
			s.draw(g);
		
		/**
		 * Draw if a shape is selected and only one mouse button click. This means that as the user moves the mouse
		 * cursor around the shape will be drawn according to the mouse position. 
		 */
		if (currentlyDrawing && shapeBeingDrawn!=null)
			shapeBeingDrawn.draw(g);
	}
	
	/**
	 * A mouse listener class that triggers a shape to start drawing upon the detection of the first mouse click. The shape
	 * stops drawing upon the second. Additionally if one mouse click has happened, a mouse motion listener forces the current
	 * shape on the canvas to be redrawn to scale.
	 */
	private class MouseListen implements MouseMotionListener, MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			newX = arg0.getX();
			newY = arg0.getY();
			
			//On first mouse click, create the shape to start drawing
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
			
			//On second mouse click, add shapeBeingDrawn to arrayList & stop drawing
			else{
				//TODO: send AddObjectCommand to the server
				System.out.println(shapeBeingDrawn == null);
				gui.sendCommand(new AddObjectCommand(shapeBeingDrawn));
				//shapesOnScreen.add(shapeBeingDrawn);
				currentlyDrawing = false;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// Unimplemented	
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// Unimplemented
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// Unimplemented
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// Unimplemented
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// Unimplemented
		}

		/**
		 * Redraw the current shape if only 1 mouse click has happened.
		 */
		@Override
		public void mouseMoved(MouseEvent arg0) {
	        newX = arg0.getX();
	        newY = arg0.getY();
	        if (currentlyDrawing && shapeBeingDrawn!=null){
	        	shapeBeingDrawn.setEndPt(new Point(newX,newY));
	        	repaint();
	        }
		}
	}

	/**
	 * Update shapesOnScreen private variable.<br>
	 * Allows drawing of all shapes on screen.
	 * Overloads existing update function
	 * @param shapes
	 */
	public void update(ArrayList<NPShape> shapes) {
		this.shapesOnScreen = shapes;
		repaint();	//TODO: Might need to add shape being drawn to this end of this, otherwise will be lost
	}

}
