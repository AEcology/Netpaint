package Shapes;

import java.awt.Color;
import java.awt.Point;

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
	
	/**
	 * Superclass constructor
	 * @param start: where shape draw began
	 * @param end: where shape draw ended
	 * @param color: color used to draw
	 */
	public NPShape(Point start, Point end, Color color){
		this.startPt = start;
		this.endPt = end;
	}

	public Point getStartPt(){
		return this.startPt;
	}
	public Point getEndPt(){
		return this.endPt;
	}
	public Color getColor(){
		return this.color;
	}
	
	/**
	 * Describe how shape is drawn in Frame
	 */
	public abstract void draw();
}
