import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_B_GameObject extends GameObject {
    public Type_B_GameObject(int x, int y) {
        super(x, y);
        setDirection(Direction.NONE); // start without movement
        
        imageList = new ArrayList<Icon>();
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_B_Up.png"));
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_B_Down.png"));
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_B_Left.png"));
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_B_Right.png"));
        
        setVelocity(10);
    }

    @Override
    public void move(Canvas c) {
        if (getDirection() == Direction.NONE) return; // don't move if direction is NONE

        Icon icon = getCurrentImage();
        if (icon == null) return;

        int iconHeight = icon.getIconHeight();
        int iconWidth = icon.getIconWidth();
        int canvasHeight = (int)c.getSize().getHeight();
        int canvasWidth = (int)c.getSize().getWidth();
        
        switch (getDirection()) {
            case Direction.UP:
                setY(getY() - getVelocity());
                if (getY() < 0) {
                    setY(0);
                    setDirection(Direction.NONE); // stop at the top
                }
                break;
            case Direction.DOWN:
                setY(getY() + getVelocity());
                if (getY() + iconHeight > canvasHeight) {
                    setY((int)(canvasHeight - iconHeight));
                    setDirection(Direction.NONE); // stop at the bottom
                }
                break;
            case Direction.LEFT:
                setX(getX() - getVelocity());
                if (getX() < 0) {
                    setX(0);
                    setDirection(Direction.NONE); // Stop at the left edge
                }
                break;
            case Direction.RIGHT:
                setX(getX() + getVelocity());
                if (getX() + iconWidth > canvasWidth) {
                    setX((int)(canvasWidth - iconWidth));
                    setDirection(Direction.NONE); // stop at the right edge
                }
                break;
        }
    }

    @Override
    public void setImage() {
        switch (getDirection()) {
            case Direction.UP:
                currentImage = 0;
                break;
            case Direction.DOWN:
                currentImage = 1;
                break;
            case Direction.LEFT:
                currentImage = 2;
                break;
            case Direction.RIGHT:
                currentImage = 3;
                break;
            default:
                currentImage = 3; // default to RIGHT image when not moving
        }
    }
}