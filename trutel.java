
package maria;

import java.awt.Image;

public class trutel implements Runnable {
	public int x;
	public int y;
	public Image image;
	public int speed = 5;
	public int type = 2;
	Thread thread;

	public trutel(int x, int y) {
		this.x = x;
		this.y = y;
		image = StaticValues.turtel.get(0);
		thread = new Thread(this);
		thread.start();
	}

	public boolean a = false;


	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (type == 1)
			{
				if (a == false) {
					image = StaticValues.turtel.get(0);
					a = true;
				} else {
					image = StaticValues.turtel.get(1);
					a = false;
				}
				x -= speed;
			}
			if (type == 2)
			{
				if (a == false) {
					image = StaticValues.turtel.get(2);
					a = true;
				} else {
					image = StaticValues.turtel.get(3);
					a = false;
				}
				x += speed;
			}
			if (type == 3)
			{
				image = StaticValues.turtel.get(4);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				type = 1;
			}

			if (x <= 660) {
				type = 2;
			}
			if (x >= 840) {
				type = 1;
			}
		}
	}
}
