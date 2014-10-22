package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

/**
 * 
 * @author Anthony Rodriguez, Jonathan Snavely
 * Abstract superclass to all shapes
 * NP: "NetPaint", used so that names don't overlap with actual Java Shapes
 */
public abstract class NPShape {
	private Point startPt;
	private Point endPt;
	private Color color;
	private Shape explicit;
	
	/**
	 * Superclass constructor
	 * @param start: where shape draw began
	 * @param end: where shape draw ended
	 * @param color: color used to draw
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
	 */
	public abstract void draw(Graphics g);
	/**
	 * Update the explicit shape model in response to a change
	 * Specifically, call super.setExplicitShape with new shape
	 */
	public abstract void updateExplicitShape();
}
