import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_C_GameObject extends GameObject {
    public Type_C_GameObject(int x, int y) {
        super(x, y);
        setDirection(Direction.NONE); // start without movement
        
        imageList = new ArrayList<Icon>();
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_C_Left.png"));
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_C_Right.png"));
        
        setVelocity(10);
    }

    @Override
    public void move(Canvas c) {
        if (getDirection() == Direction.NONE) return; // don't move if direction is NONE

        Icon icon = getCurrentImage();
        if (icon == null) return;

        int iconWidth = icon.getIconWidth();
        int canvasWidth = (int)c.getSize().getWidth();
        
        switch (getDirection()) {
            case Direction.LEFT:
                setX(getX() - getVelocity());
                if (getX() < 0) {
                    setX(0);
                    setDirection(Direction.NONE); // stop at the left edge
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
        currentImage = (getDirection() == Direction.LEFT) ? 0 : 1; // set image based on direction
    }
}