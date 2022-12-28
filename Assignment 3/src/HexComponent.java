
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * A six sided chamber. This is not guaranteed to be a perfect hexagon, it is
 * just guaranteed to have six sides in the form of a hexagon. To be a perfect
 * hexagon the size of this component must have a height to width ratio of 1 to
 * 0.866
 *
 * @author keang, cs1027
 *
 *
 */

public class HexComponent extends JComponent {
	private static final long serialVersionUID = 4865976127980106774L;

    // Object that represents a chamber
	private Polygon hexagon = new Polygon();

	private final int nPoints = 6;
	
	// Coordinates of the chamber
	private int[] hexX = new int[nPoints];
	private int[] hexY = new int[nPoints];

	private Color defaultColor;
	
	// Variable to select the image drawn on the map
	private int treasureType = 0;

	@Override
	// Returns true if the given point is in the chamber
	public boolean contains(Point p) {
		return hexagon.contains(p);
	}

	@Override
	// returns true if the chamber includes a point with the given coordinates
	public boolean contains(int x, int y) {
		return hexagon.contains(x, y);
	}

	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
		calculateCoords();
	}

	@Override
	public void setSize(int w, int h) {
		super.setSize(w, h);
		calculateCoords();
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		calculateCoords();
	}

	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		calculateCoords();
	}

	@Override
	protected void processMouseEvent(MouseEvent e) {
		if (contains(e.getPoint()))
			super.processMouseEvent(e);
	}

	/**
	 * Returns the treasure type defining the image used in the display
	*/
	public int getTreasureType() {
		return treasureType;
	}
	
	public void setTreasureType(int newType) {
		treasureType = newType;
	}
	
	/* Computes the coordinates of a hexagonal chamber */
	private void calculateCoords() {
		int w = getWidth() - 1;
		int h = getHeight() - 1;

		int ratio = (int) (h * .25);

		agressiveCoords(w, h, ratio);

		hexagon = new Polygon(hexX, hexY, nPoints);
	}

	private void agressiveCoords(int w, int h, int ratio) {
		hexX[0] = w / 2;
		hexY[0] = 0;

		hexX[1] = w;
		hexY[1] = ratio;

		hexX[2] = w;
		hexY[2] = h - ratio;

		hexX[3] = w / 2;
		hexY[3] = h;

		hexX[4] = 0;
		hexY[4] = h - ratio;

		hexX[5] = 0;
		hexY[5] = ratio;
	}

	@Override
	/* Draws a chamber on the screen */
	protected void paintComponent(Graphics g) {
		HexColors palette = new HexColors();
		Color c = getBackground();
		Graphics2D g2d = (Graphics2D) g;
		defaultColor = (Color) g2d.getPaint();
		boolean displayed = false;
		GradientPaint gp;
		int width = getWidth();
		int height = getHeight();

		if (c == HexColors.SEALED) {
			try {
				BufferedImage texture;
				texture = ImageIO.read(new File("wall2.jpg"));
				TexturePaint tp = new TexturePaint(texture, new Rectangle(0, 0, 50, 50));
				g2d.setPaint(tp);
				g2d.fillPolygon(hexagon);
				displayed = true;
			} catch (IOException e) {
				System.out.println("Error opening file wall2.jpg");
			}
		} else if (c == HexColors.TREASURE_PROCESSED || c == HexColors.TREASURE_PROCESSED1 || c == HexColors.ENTRANCE_PROCESSED || c == HexColors.ENTRANCE_POPPED) {
			Image img;
			gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon);
			if (c == HexColors.TREASURE_PROCESSED || c == HexColors.TREASURE_PROCESSED1) { 
				if (c == HexColors.TREASURE_PROCESSED)
					if (treasureType == 1)					
						img = new ImageIcon("treasure1Push.jpg").getImage();	
					else img = new ImageIcon("treasure3Push.jpg").getImage();
				else img = new ImageIcon("treasure2Push.jpg").getImage();
				g2d.drawImage(img, width / 8, height / 5, 3*width / 4, 3*height / 5, null);
			} else {
				img = new ImageIcon("entrance.jpg").getImage();
				g2d.drawImage(img, 3 * width / 8, height / 3, width / 4, height / 3, null);
			}
			displayed = true;
		} else if (c == HexColors.TREASURE || c == HexColors.TREASURE2) {
			Image img;
			gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon);
			if (c == HexColors.TREASURE) 
				if (treasureType == 1)
					img = new ImageIcon("treasure1.jpg").getImage();
				else img = new ImageIcon("treasure3.jpg").getImage();
			else img = new ImageIcon("treasure2.jpg").getImage();	
				
			g2d.drawImage(img, width / 8, height / 5, 3*width / 4, 3*height / 5, null);

			displayed = true;
		} else if (c == HexColors.DARK || c == HexColors.DARK_PUSHED || c == HexColors.DARK_POPPED) {
			try {
				gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
						palette.gradientColor(c), true);				
				g2d.setPaint(gp);				
				g2d.fillPolygon(hexagon);
				displayed = true;
			} catch (Exception e) {
				System.out.println("Error opening image file");
			}
		} else if (c == HexColors.DIM || c == HexColors.DIM_PUSHED || c == HexColors.DIM_POPPED) {
			try {
				gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
						palette.gradientColor(c), true);				
				g2d.setPaint(gp);
				g2d.fillPolygon(hexagon);
				displayed = true;
			} catch (Exception e) {
				System.out.println("Error opening image file");
			}
		}
		else if (c == HexColors.LIGHTED) {
			Image img;
			gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon); 
			img = new ImageIcon("sun1.jpg").getImage();
				
			g2d.drawImage(img, width / 4, height/3, width / 2, height/3, null);

			displayed = true;
		}
		else if (c == HexColors.PUSHED) {
			Image img;
			gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon); 
			img = new ImageIcon("sun1Push.jpg").getImage();
				
			g2d.drawImage(img, width / 4, height/3, width / 2, height/3, null);

			displayed = true;
		}		
		else if (c == HexColors.POPPED) {
			Image img;
			gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon); 
			img = new ImageIcon("sun1Pop.jpg").getImage();
				
			g2d.drawImage(img, width / 4, height/3, width / 2, height/3, null);

			displayed = true;
		}		
		if (!displayed) {
			gp = new GradientPaint(hexX[5], hexY[5], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon);
		}
		g2d.setPaint(defaultColor);

	}

	@Override
	protected void paintBorder(Graphics g) {
		// do not paint a border
	}

}
