package maria;

import java.util.List;

// obstructioniterator
public class ObstructionIterator {
    private List<Enemy> enemies;
    private int position = 0;

    public ObstructionIterator(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public boolean hasnext() {
        return position < enemies.size();
    }

    public Enemy next() {
        return enemies.get(position++);
    }
}
