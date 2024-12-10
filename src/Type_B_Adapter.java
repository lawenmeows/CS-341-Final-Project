public class Type_B_Adapter implements UserControlledObject {
    private Type_B_GameObject gameObject;

    public Type_B_Adapter(Type_B_GameObject gameObject) {
        this.gameObject = gameObject;
    }
    @Override
    public void move(Canvas c) {
        gameObject.move(c); // delegate to the GameObject's move method
    }
    @Override
    public void setImage() {
        gameObject.setImage(); // call GameObject's setImage method
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