package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

/**
 * Abstract superclass to all shapes
 * NP: "NetPaint", used so that names don't overlap with actual Java Shapes.
 * Contains {@link Point} and {@link Color} properties, which can be altered through getters and setters.
 * 
 * @author Anthony Rodriguez, Jonathan Snavely
 */
public abstract class NPShape implements Serializable{

	private Point startPt;
	private Point endPt;
	private Color color;
	private Shape explicit;
	
	/**
	 * Superclass constructor
	 * @param start: Point where shape draw began
	 * @param end: Point where shape draw ended
	 * @param color: Color used to draw
	 */
	public NPShape(Point start, Point end, Color color){
		this.startPt = start;
		this.endPt = end;
		this.color = color;
	}

	public void setExplicitShape(Shape e){
		this.explicit = e;
	}
	public Shape getExplicitShape(){
		return explicit;
	}
	public Point getStartPt(){
		return this.startPt;
	}
	public Point getEndPt(){
		return this.endPt;
	}
	public void setEndPt(Point newPt){
		this.endPt = newPt;
		updateExplicitShape();
	}
	public Color getColor(){
		return this.color;
	}
	public void setColor(Color c){
		this.color = c;
	}
	
	/**
	 * Describe how shape is drawn in Frame
	 * @param g: Graphics object
	 */
	public abstract void draw(Graphics g);
	/**
	 * Update the explicit shape model in response to a change
	 * Specifically, call super.setExplicitShape with new shape
	 */
	public abstract void updateExplicitShape();
}
