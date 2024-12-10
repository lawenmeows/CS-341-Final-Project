public class Type_A_Adapter implements UserControlledObject {
    private Type_A_GameObject gameObject;

    public Type_A_Adapter(Type_A_GameObject gameObject) {
        this.gameObject = gameObject;
    }
    @Override
    public void setImage() {
        gameObject.setImage(); // call GameObject's setImage method
    }
    
    @Override
    public void move(Canvas c) {
        gameObject.move(c); // delegate to the GameObject's move method
    }
    @Override
    public void moveUp() {
        gameObject.setDirection(Direction.UP);
    }

    @Override
    public void moveDown() {
        gameObject.setDirection(Direction.DOWN);
    }

    @Override
    public void moveLeft() {
        // do nothing (restricted movement)
    }

    @Override
    public void moveRight() {
        // do nothing (restricted movement)
    }

    @Override
    public void stopMoving() {
        gameObject.setDirection(Direction.NONE);
    }
}