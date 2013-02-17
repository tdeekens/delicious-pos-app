package delicious.pos.util;

import java.awt.Component;

import javax.swing.ImageIcon;

public class ImageLoader {

	public static ImageIcon loadImageIcon(Component comp, String icon) {
		return new ImageIcon(comp.getClass().getResource("../icons/" + icon + ".png"));
	}
}
