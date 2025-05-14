package maria;
// mario
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Mario implements Runnable {

	private int x;
	private int y;
	private String status;
	private Image image;
	public int xmove = 0;
	public int ymove = 0;
	private int p = 0;
	private int a = 1;
	public int time = 0;
	public Thread thread = null;
	private Background backgrond = null;
	private int socre;
	private int lift;
	public boolean die = true;
	private boolean isClear = false;

	// Design Pattern fields
	private MarioState state;
	private MoveStrategy strategy;
	private List<EnemyObserver> observers = new ArrayList<>();

	public Mario(int x, int y) {
		this.x = x;
		this.y = y;
		image = StaticValues.mariao.get(0);
		this.socre = 0;
		this.lift = 3;
		this.status = "right-standing";
		this.state = new JumpingState();
		this.strategy = new MoveLeft();
		thread = new Thread(this);
		thread.start();
	}

	// Observer
	public void addobserver(EnemyObserver o) {
		observers.add(o);
	}

	public void notifyobservers() {
		for (EnemyObserver o : observers) {
			o.onenemycollision();
		}
	}

	// State pattern
	public void jump() {
		System.out.println("Jump called");
		if (!status.contains("jumping")) {
			if (status.contains("left")) {
				status = "left-jumping";
			} else {
				status = "right-jumping";
			}
			ymove = -10;
			time = 18;
		}
	}


	// Strategy pattern
	public void executeMove() {
		strategy.move(this);
	}

	public void down() {
		if (this.status.indexOf("left") != -1) {
			this.status = "left-jumping";
		} else {
			this.status = "right-jumping";
		}
		ymove = 10;
	}

	public void dead() {
		this.image = StaticValues.die;
		notifyobservers();
		die = false;
	}

	public void leftMove() {
		xmove = -5;
		this.status = "left-moving";
	}

	public void rightMove() {
		xmove = 5;
		this.status = "right-moving";
	}

	public void leftstop() {
		xmove = 0;
		this.status = "left-standing";
	}

	public void rightstop() {
		xmove = 0;
		this.status = "right-standing";
	}

	@Override
	public void run() {
		while (true) {
			if (!die) continue;

			boolean canleft = true;
			boolean canright = true;
			boolean jumb = false;

			if (backgrond.trutel != null && backgrond.trutel.y == this.y + 60 && (backgrond.trutel.x + 60 > this.x && backgrond.trutel.x - 60 < this.x)) {
				backgrond.trutel.type = 3;
				this.time = 10;
				this.ymove = -5;
			}

			if (backgrond.trutel != null && backgrond.trutel.x + 50 > this.x && backgrond.trutel.x - 50 < this.x && backgrond.trutel.y + 50 > this.y && backgrond.trutel.y - 50 < this.y) {
				this.dead();
			}

			for (int i = 0; i < backgrond.obstraction.size(); i++) {
				Enemy ob = backgrond.obstraction.get(i);
				if ((ob.getX() == this.x + 50) && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
					canright = false;
				}
				if ((ob.getX() == this.x - 50) && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
					canleft = false;
				}
				if (ob.getY() == this.y + 50 && ob.getX() + 50 > this.x && ob.getX() - 50 < this.x) {
					jumb = true;
				}
				if (ob.getY() == this.y - 60 && (ob.getX() + 50 > this.x && ob.getX() - 50 < this.x)) {
					if (ob.getType() == 0) {
						this.backgrond.obstraction.remove(ob);
						this.backgrond.removedenemy.add(ob);
					}
					if (ob.getType() == 4 || ob.getType() == 3 && time > 0) {
						ob.setType(2);
						ob.setImage();
					}
					time = 0;
				}
			}

			if (jumb && time == 0) {
				if (this.status.indexOf("left") != -1) {
					this.status = (xmove != 0) ? "left-moving" : "left-standing";
				} else {
					this.status = (xmove != 0) ? "right-moving" : "right-standing";
				}
			} else {
				if (time != 0) {
					time--;
				} else {
					this.down();
				}
				y += ymove;
			}

			if ((canleft && xmove < 0) || (canright && xmove > 0)) {
				if (x < 0) x = 0;
				else x += xmove;
			}

			int temp = 0;
			if (this.status.indexOf("left") != -1) temp += 5;
			if (this.status.indexOf("moving") != -1) {
				temp += p;
				if (a % 4 == 1) {
					p++;
					if (p == 4) p = 0;
				}
			}
			if (this.status.indexOf("jumping") != -1) temp += 4;
			this.image = StaticValues.mariao.get(temp);
			a++;

			for (int i = 0; i < this.backgrond.enemy.size(); i++) {
				MoveEnemy e = (MoveEnemy) this.backgrond.enemy.get(i);
				if (e.getX() + 50 > this.x && e.getX() - 50 < this.x && e.getY() + 50 > this.y && e.getY() - 50 < this.y) {
					this.dead();
				}
				if (e.getY() == this.y + 60 && (e.getX() + 60 > this.x && e.getX() - 60 < this.x)) {
					if (e.getType() == 1) {
						e.dead();
						this.time = 10;
						this.ymove = -5;
					} else if (e.getType() == 2) {
						this.dead();
					}
				}
			}

			try {
				Thread.sleep(50);
				if (a == 100) a = 0;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Getters and setters
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public Image getImage() { return image; }
	public void setImage(Image image) { this.image = image; }
	public Background getBackgrond() { return backgrond; }
	public void setBackgrond(Background backgrond) { this.backgrond = backgrond; }
	public int getSocre() { return socre; }
	public void setSocre(int socre) { this.socre = socre; }
	public int getLift() { return lift; }
	public void setLift(int lift) { this.lift = lift; }
}
