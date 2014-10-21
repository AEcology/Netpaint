package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

//TODO: Adjust for color fill: (g.fillRect())
public class NPRectangle extends NPShape{
	
	public NPRectangle(Point start, Point end, Color color) {
		super(start, end, color);
		int upperLeftX = (int)Math.min(start.getX(), end.getX());
		int upperLeftY = (int)Math.min(start.getY(), end.getY());
		int lowerRightX = (int)Math.max(start.getX(), end.getX());
		int lowerRightY = (int)Math.max(start.getY(), end.getY());
		this.setExplicitShape(new Rectangle2D.Double(upperLeftX, upperLeftY, (lowerRightX-upperLeftX), (lowerRightY-upperLeftY)));
	}

	@Override
	public void draw(Graphics2D g) {
				g.draw(this.getExplicitShape());
	}

}
