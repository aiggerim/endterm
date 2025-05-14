

package maria;
// background
import java.awt.Image;
import java.util.ArrayList;


public class Background {
	private boolean isOver=false;
     public Image getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public ArrayList getEnemy() {
		return enemy;
	}

	public void setEnemy(ArrayList enemy) {
		this.enemy = enemy;
	}

	public ArrayList<Enemy> getObstraction() {
		return obstraction;
	}

	public void setObstraction(ArrayList<Enemy> obstraction) {
		this.obstraction = obstraction;
	}

	public ArrayList getRemovedenemy() {
		return removedenemy;
	}

	public void setRemovedenemy(ArrayList removedenemy) {
		this.removedenemy = removedenemy;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	private boolean isDown=false;	public boolean isDown() {
		return isDown;
	}

	public Image backgroundImage = null;
     private int sort;
     private boolean flag;
     ArrayList enemy = new ArrayList<>();
     ArrayList<Enemy> obstraction = new ArrayList<>();
     ArrayList<Enemy> removedenemy = new ArrayList<>();
     ArrayList<MoveEnemy> remove = new ArrayList<>();
     trutel trutel = null;
     
     public Background(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;
        if(flag)
        {
        	backgroundImage = StaticValues.end;
        }
        else {
			backgroundImage = StaticValues.bgImage;
		}
        if(sort == 1)
         {  
        	for(int i = 0;i < 15;i++)
        	{   
        		this.obstraction.add(new Enemy(i*60, 540, 9));
        	}

          this.obstraction.add(new Enemy(120, 360, 4));
        	this.obstraction.add(new Enemy(300, 360, 0));
        	this.obstraction.add(new Enemy(360, 360, 4));
        	this.obstraction.add(new Enemy(420, 360, 0));
        	this.obstraction.add(new Enemy(480, 360, 4));
        	this.obstraction.add(new Enemy(540, 360, 0));
        	this.obstraction.add(new Enemy(420, 180, 0));
        	 
        	this.enemy.add(new MoveEnemy(690,540 , true , 2 , 420 , 540 ,this));
        	
         	this.obstraction.add(new Enemy(660, 540, 6));
        	this.obstraction.add(new Enemy(720, 540, 5));
        	this.obstraction.add(new Enemy(660, 480, 8));
        	this.obstraction.add(new Enemy(720, 480, 7));
           
            this.enemy.add(new MoveEnemy(600, 480, true,1,this));
         }
		if(sort==2){
 			for(int i=0;i<15;i++){
 				if(i!=10){
					this.obstraction.add(new Enemy(i*60,540,9));
				}
			}
			this.obstraction.add(new Enemy(60,540,6));
			this.obstraction.add(new Enemy(120,540,5));
			this.obstraction.add(new Enemy(60,480,6));
			this.obstraction.add(new Enemy(120,480,5));
			this.obstraction.add(new Enemy(60,420,8));
			this.obstraction.add(new Enemy(120,420,7));
			

			this.obstraction.add(new Enemy(240,540,6));
			this.obstraction.add(new Enemy(300,540,5));
			this.obstraction.add(new Enemy(240,480,6));
			this.obstraction.add(new Enemy(300,480,5));
			this.obstraction.add(new Enemy(240,420,6));
			this.obstraction.add(new Enemy(300,420,5));
			this.obstraction.add(new Enemy(240,360,8));
			this.obstraction.add(new Enemy(300,360,7));
			
			
        	this.obstraction.add(new Enemy(480, 360, 4));
        	this.obstraction.add(new Enemy(540, 360, 0));
        	this.obstraction.add(new Enemy(420, 180, 0));
			
			this.enemy.add(new MoveEnemy(90,540 , true , 2 , 350 , 540 ,this));
			this.enemy.add(new MoveEnemy(270,540 , true , 2 , 300 , 400 ,this));
			trutel = new trutel(680, 480);
			
		}
		if(sort==3){
			for(int i=0;i<15;i++){			
				this.obstraction.add(new Enemy(i*60,540,9));
			}
			this.obstraction.add(new Enemy(550,180,11));
			this.obstraction.add(new Enemy(520,480,2));
			
			
		}
	}
 	public boolean isOver() {
		return isOver;
	}
     
     public void reset()
     {
    	 
     }
     
}
