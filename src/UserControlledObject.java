public interface UserControlledObject {
    void move(Canvas c); // ensure this is included
    void setImage(); // add this line
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
    void stopMoving();
}