package maria;
// jumpingstate
public class jumpingstate implements mariostate {
    @Override
    public void handleinput(mario mario) {
        mario.ymove = -10;
        mario.setStatus("jumping");
    }
}
