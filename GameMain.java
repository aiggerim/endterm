package maria;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.JFrame;

public class GameMain extends JFrame implements Runnable {

	private ArrayList<Background> backgronds = new ArrayList<>();
	private Background backgrondnow = null;
	private final Mario mario;

	public GameMain() {
		this.setTitle("Mario Game");
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		StaticValues.init();

		backgrondnow = new Background(0, false);
		for (int i = 1; i <= 3; i++) {
			this.backgronds.add(new Background(i, i == 3));
		}
		this.backgrondnow = backgronds.get(0);

		mario = new Mario(0, 480);
		mario.setBackgrond(backgrondnow);
		this.repaint();

		// 🎯 МІНДЕТТІ: KeyListener және focus
		this.addKeyListener(new key());
		this.setFocusable(true);
		this.requestFocus(); // БІРАҚ window ашылған соң орындалуы үшін

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true); // Фокус бұдан бұрын шақырылуы керек
		this.setResizable(false);

		Thread thread = new Thread(this);
		thread.start();
	}


	@Override
	public void paint(Graphics graphics) {
		BufferedImage bufferedImage = new BufferedImage(900, 600, BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics2 = bufferedImage.getGraphics();
		graphics2.drawImage(this.backgrondnow.getBackgroundImage(), 0, 0, this);

		java.util.Iterator<MoveEnemy> iterator2 = this.backgrondnow.enemy.iterator();
		while (iterator2.hasNext()) {
			MoveEnemy moveenemy = iterator2.next();
			graphics2.drawImage(moveenemy.getImage(), moveenemy.getX(), moveenemy.getY(), this);
		}

		java.util.Iterator<Enemy> iterator = this.backgrondnow.obstraction.iterator();
		while (iterator.hasNext()) {
			Enemy obstraction = iterator.next();
			graphics2.drawImage(obstraction.getImage(), obstraction.getX(), obstraction.getY(), this);
		}

		if (backgrondnow.trutel != null) {
			graphics2.drawImage(backgrondnow.trutel.image, backgrondnow.trutel.x, backgrondnow.trutel.y, this);
		}
		graphics2.drawImage(mario.getImage(), mario.getX(), mario.getY(), this);
		graphics.drawImage(bufferedImage, 0, 0, this);
	}

	class key implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				GameMain.this.mario.rightMove();
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				GameMain.this.mario.leftMove();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				GameMain.this.mario.down(); // 👈 Қосылды
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				GameMain.this.mario.jump(); //TODO: fix multiple jumps
			}
		}


		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				GameMain.this.mario.rightstop();
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				GameMain.this.mario.leftstop();
			}
		}
	}

	public static void main(String[] args) {
		new GameMain();
	}

	@Override
	public void run() {
		while (true) {
			this.repaint();
			try {
				Thread.sleep(50);
				if (mario.getX() >= 840) {
					this.backgrondnow = backgronds.get(this.backgrondnow.getSort());
					mario.setBackgrond(backgrondnow);
					mario.setX(0);
					mario.setY(480);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
