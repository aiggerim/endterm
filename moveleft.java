package maria;

public class moveleft implements movestrategy {
    @Override
    public void move(mario mario) {
        mario.leftMove();
    }
}
