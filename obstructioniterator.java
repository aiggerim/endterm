package maria;

import java.util.List;

public class obstructioniterator {
    private List<enemy> enemies;
    private int position = 0;

    public obstructioniterator(List<enemy> enemies) {
        this.enemies = enemies;
    }

    public boolean hasnext() {
        return position < enemies.size();
    }

    public enemy next() {
        return enemies.get(position++);
    }
}
