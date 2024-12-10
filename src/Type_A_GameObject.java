import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_A_GameObject extends GameObject {
    public Type_A_GameObject(int x, int y) {
        super(x, y);
        setDirection(Direction.NONE);
        
        imageList = new ArrayList<Icon>();
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_A_Up.png"));
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_A_Down.png"));
        
        setVelocity(10);
    }

    @Override
    public void move(Canvas c) {
        System.out.println("Type A moving: " + getDirection());
        if (getDirection() == Direction.NONE) return;

        Icon icon = getCurrentImage();
        if (icon == null) return;

        int iconHeight = icon.getIconHeight();
        int canvasHeight = (int)c.getSize().getHeight();
        
        switch (getDirection()) {
            case Direction.UP:
                setY(getY() - getVelocity());
                if (getY() < 0) {
                    setY(0);
                    setDirection(Direction.NONE);
                }
                break;
            case Direction.DOWN:
                setY(getY() + getVelocity());
                if (getY() + iconHeight > canvasHeight) {
                    setY((int)(canvasHeight - iconHeight));
                    setDirection(Direction.NONE);
                }
                break;
        }
    }

    @Override
    public void setImage() {
        int newImage = (getDirection() == Direction.UP) ? 0 : 1;
        if (currentImage != newImage) {
            currentImage = newImage;
            System.out.println("Type A image set: " + currentImage);
        }
    }
}