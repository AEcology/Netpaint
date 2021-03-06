package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 * Class NPRectangle. Inherits draw and updateExplicitShape methods from superclass {@link NPShape}.<br>
 * This class draws a rectangle shape using specified Points and Colors. 
 * 
 * @author Anthony Rodriguez, Jonathan Snavely
 */
public class NPRectangle extends NPShape{
	


	/**
	 * Sets coordinates to be drawn, as well as the color.
	 * @param start: Point where shape draw began
	 * @param end: Point where shape draw ended
	 * @param color: Color used to draw
	 */	
	public NPRectangle(Point start, Point end, Color color) {
		super(start, end, color);
		int upperLeftX = (int)Math.min(start.getX(), end.getX());
		int upperLeftY = (int)Math.min(start.getY(), end.getY());
		int lowerRightX = (int)Math.max(start.getX(), end.getX());
		int lowerRightY = (int)Math.max(start.getY(), end.getY());
		this.setExplicitShape(new Rectangle2D.Double(upperLeftX, upperLeftY, (lowerRightX-upperLeftX), (lowerRightY-upperLeftY)));
	}

	/**
	 * Uses the graphics object to draw a rectangle shape using {@link NPRectangle} attributes
	 */
	@Override
	public void draw(Graphics g) {
		//((Graphics2D)g).draw(this.getExplicitShape());
		int upperLeftX = (int)Math.min(getStartPt().getX(), getEndPt().getX());
		int upperLeftY = (int)Math.min(getStartPt().getY(), getEndPt().getY());
		int lowerRightX = (int)Math.max(getStartPt().getX(), getEndPt().getX());
		int lowerRightY = (int)Math.max(getStartPt().getY(), getEndPt().getY());
		g.setColor(this.getColor());
		g.fillRect(upperLeftX, upperLeftY, (lowerRightX-upperLeftX), (lowerRightY-upperLeftY));
	}

	/**
	 * Used to update the coordinates of the {@link NPRectangle}
	 */
	@Override
	public void updateExplicitShape() {
		int upperLeftX = (int)Math.min(getStartPt().getX(), getEndPt().getX());
		int upperLeftY = (int)Math.min(getStartPt().getY(), getEndPt().getY());
		int lowerRightX = (int)Math.max(getStartPt().getX(), getEndPt().getX());
		int lowerRightY = (int)Math.max(getStartPt().getY(), getEndPt().getY());
		this.setExplicitShape(new Rectangle2D.Double(upperLeftX, upperLeftY, (lowerRightX-upperLeftX), (lowerRightY-upperLeftY)));
	}

}
