package Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

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
	
	private ArrayList<NPShape> shapesOnScreen;
	
	public Canvas(){
		super();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(800,600));
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
	public void paintComponent(Graphics2D g){
		for(NPShape s:shapesOnScreen)
			s.draw(g);
	}

}
