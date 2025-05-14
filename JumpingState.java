package maria;
// jumpingstate
public class JumpingState implements MarioState {
    @Override
    public void handleinput(Mario mario) {
        mario.ymove = -10;
        mario.setStatus("jumping");
    }
}
