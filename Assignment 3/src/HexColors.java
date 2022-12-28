import java.awt.Color;

public class HexColors {
	public static final Color SEALED = Color.BLACK;
	public static final Color ENTRANCE = new Color(250, 250, 0);
	public static final Color TREASURE = new Color(100,255,101);
	public static final Color TREASURE2 = new Color(100,255,102);	
	public static final Color LIGHTED = new Color(100, 255, 100);
	public static final Color PUSHED = Color.CYAN;
	public static final Color TREASURE_PROCESSED = new Color(0, 190, 190);
	public static final Color TREASURE_PROCESSED1 = new Color(0, 190, 191);	
	public static final Color ENTRANCE_PROCESSED = new Color(200, 255, 255);
	public static final Color ENTRANCE_POPPED = new Color(50, 255, 255);
	public static final Color POPPED = Color.LIGHT_GRAY;
	public static final Color DRAGON = Color.YELLOW;
	public static final Color DARK = new Color(1,1,1);
	public static final Color DIM = new Color(0,150,0);
	public static final Color DARK_PUSHED = Color.RED.darker();
	public static final Color DIM_PUSHED = new Color(0,255,254);
	public static final Color DARK_POPPED = Color.RED.brighter();
	public static final Color DIM_POPPED = new Color(210,210,210);

	public Color gradientColor(Color c) {
		if (c == SEALED)
			return Color.DARK_GRAY.brighter();
		else if (c == PUSHED || c == DIM_PUSHED)
			return c.darker().darker();
		else if (c == TREASURE_PROCESSED || c == TREASURE_PROCESSED1)
			return  Color.CYAN.darker().darker();
		else if (c == ENTRANCE_PROCESSED)
			return Color.CYAN.darker();
		else if (c == ENTRANCE_POPPED)
			return Color.GRAY;
		return c.darker();
	}

	public Color initialGradient(Color c) {
		if (c == TREASURE_PROCESSED || c == TREASURE_PROCESSED1)
			return new Color(150, 250, 250);
		else if (c == ENTRANCE_PROCESSED)
			return new Color(250, 250, 80);
		else if (c == ENTRANCE_POPPED)
			return new Color(250, 250, 80);
		else if (c == TREASURE || c == TREASURE2)
			return LIGHTED;
		else
			return c;
	}
}
