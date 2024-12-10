public class Type_C_Adapter implements UserControlledObject {
    private Type_C_GameObject gameObject;

    public Type_C_Adapter(Type_C_GameObject gameObject) {
        this.gameObject = gameObject;
    }
    @Override
    public void setImage() {
        gameObject.setImage(); // Call GameObject's setImage method
    }
    @Override
    public void move(Canvas c) {
        gameObject.move(c); // delegate to the GameObject's move method
    }
    

    @Override
    public void moveUp() {
        // do nothing (restricted movement)
    }

    @Override
    public void moveDown() {
        // do nothing (restricted movement)
    }

    @Override
    public void moveLeft() {
        gameObject.setDirection(Direction.LEFT);
    }

    @Override
    public void moveRight() {
        gameObject.setDirection(Direction.RIGHT);
    }

    @Override
    public void stopMoving() {
        gameObject.setDirection(Direction.NONE);
    }
}