package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 * Class NPOval. Inherits draw and updateExplicitShape methods from superclass {@link NPShape}.<br>
 * This class draws an oval shape using specified Points and Colors. 
 * 
 * @author Anthony Rodriguez, Jonathan Snavely
 */
public class NPOval extends NPShape{
	
	/**
	 * Sets coordinates to be drawn, as well as the color.
	 * @param <strong>start:</strong> Point where shape draw began
	 * @param <strong>end:</strong> Point where shape draw ended
	 * @param <strong>color:</strong> Color used to draw
	 */	
	public NPOval(Point start, Point end, Color color) {
		super(start, end, color);
		int upperLeftX = (int)Math.min(start.getX(), end.getX());
		int upperLeftY = (int)Math.min(start.getY(), end.getY());
		int lowerRightX = (int)Math.max(start.getX(), end.getX());
		int lowerRightY = (int)Math.max(start.getY(), end.getY());
		this.setExplicitShape(new Rectangle2D.Double(upperLeftX, upperLeftY, (lowerRightX-upperLeftX), (lowerRightY-upperLeftY)));
	}

	/**
	 * Uses the graphics object to draw an oval shape using {@link NPOval} attributes
	 */
	@Override
	public void draw(Graphics g) {
		//((Graphics2D)g).draw(this.getExplicitShape());
		int upperLeftX = (int)Math.min(getStartPt().getX(), getEndPt().getX());
		int upperLeftY = (int)Math.min(getStartPt().getY(), getEndPt().getY());
		int lowerRightX = (int)Math.max(getStartPt().getX(), getEndPt().getX());
		int lowerRightY = (int)Math.max(getStartPt().getY(), getEndPt().getY());
		g.setColor(this.getColor());
		g.fillOval(upperLeftX, upperLeftY, (lowerRightX-upperLeftX), (lowerRightY-upperLeftY));
	}

	/**
	 * Used to update the coordinates of the {@link NPOval}
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
