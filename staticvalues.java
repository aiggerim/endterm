package maria;

import java.awt.Image;
import java.awt.List;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class staticvalues {
	public static ArrayList<Image> mariao = new ArrayList<>();
	public static Image start = null;
	public static Image end = null;
	public static Image bgImage = null;
	public static ArrayList<Image> flower = new ArrayList<>();
	public static ArrayList<Image> trangel = new ArrayList<>();
	public static ArrayList<Image> turtel = new ArrayList<>();
	public static ArrayList<Image> obstruction = new ArrayList<>();
	public static Image die = null;

	public static void init() {
		try {
			for (int i = 1; i <= 10; i++) {
				Image image = ImageIO.read(new File("images//" + i + ".gif"));
				mariao.add(image);
			}
			start = ImageIO.read(new File("images//start.gif"));
			end = ImageIO.read(new File("images//firststageend.gif"));
			bgImage = ImageIO.read(new File("images//firststage.gif"));
			for (int i = 1; i <= 5; i++) {
				if (i <= 2) {
					Image image = ImageIO.read(new File("images//flower" + i + ".gif"));
					flower.add(image);
				}
				if(i <= 3)
				{
					Image image = ImageIO.read(new File("images//triangle" + i +".gif"));
					trangel.add(image);
				}
				Image image = ImageIO.read(new File("images//Turtle" + i +".gif"));
				turtel.add(image);
			}
			for(int i = 1;i <= 12;i++)
			{
				Image image = ImageIO.read(new File("images//ob" + i + ".gif"));
				obstruction.add(image);
			}
			die = ImageIO.read(new File("images//over.gif"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
