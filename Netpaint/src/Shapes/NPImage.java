package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NPImage extends NPShape{
	BufferedImage dogImage;

	public NPImage(Point start, Point end, Color color) {
		super(start, end, color);
		int upperLeftX = (int)Math.min(start.getX(), end.getX());
		int upperLeftY = (int)Math.min(start.getY(), end.getY());
		int lowerRightX = (int)Math.max(start.getX(), end.getX());
		int lowerRightY = (int)Math.max(start.getY(), end.getY());
		this.setExplicitShape(new Rectangle2D.Double(upperLeftX, upperLeftY, (lowerRightX-upperLeftX), (lowerRightY-upperLeftY)));
		dogImage = null;
		try {
			dogImage = ImageIO.read(new File("Doggie.jpg"));
		} 
		catch (IOException e) {
			System.out.println("This shouldn't have happened");
		}
	}

	@Override
	public void draw(Graphics g) {
		//((Graphics2D)g).draw(this.getExplicitShape());
		int upperLeftX = (int)Math.min(getStartPt().getX(), getEndPt().getX());
		int upperLeftY = (int)Math.min(getStartPt().getY(), getEndPt().getY());
		int lowerRightX = (int)Math.max(getStartPt().getX(), getEndPt().getX());
		int lowerRightY = (int)Math.max(getStartPt().getY(), getEndPt().getY());
		g.drawImage(dogImage, upperLeftX, upperLeftY, (lowerRightX-upperLeftX), (lowerRightY-upperLeftY), null);
	}

	@Override
	public void updateExplicitShape() {
		int upperLeftX = (int)Math.min(getStartPt().getX(), getEndPt().getX());
		int upperLeftY = (int)Math.min(getStartPt().getY(), getEndPt().getY());
		int lowerRightX = (int)Math.max(getStartPt().getX(), getEndPt().getX());
		int lowerRightY = (int)Math.max(getStartPt().getY(), getEndPt().getY());
		this.setExplicitShape(new Rectangle2D.Double(upperLeftX, upperLeftY, (lowerRightX-upperLeftX), (lowerRightY-upperLeftY)));
	}
}


