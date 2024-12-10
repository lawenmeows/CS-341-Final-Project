import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_D_GameObject extends GameObject implements KeyListener, UserControlledObject {
    public Type_D_GameObject(int x, int y) {
        super(x, y);
        setDirection(Direction.NONE); // start without movement
        
        imageList = new ArrayList<Icon>();
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_D_Up.png"));
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_D_Down.png"));
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_D_Left.png"));
        imageList.add(new ImageIcon("/Users/laurenly/Desktop/CS-341-01/images/Type_D_Right.png"));
        
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
                    stopMoving(); // stop at the top
                }
                break;
            case Direction.DOWN:
                setY(getY() + getVelocity());
                if (getY() + iconHeight > canvasHeight) { 
                    setY((int)(canvasHeight - iconHeight));
                    stopMoving(); // stop at the bottom
                }
                break;
            case Direction.LEFT:
                setX(getX() - getVelocity());
                if (getX() < 0) { 
                    setX(0);
                    stopMoving(); // stop at the left edge
                }
                break;
            case Direction.RIGHT:
                setX(getX() + getVelocity());
                if (getX() + iconWidth > canvasWidth) { 
                    setX((int)(canvasWidth - iconWidth));
                    stopMoving(); // stop at the right edge
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
               currentImage = 0; // default to UP image when not moving
         } 
     }

     // KeyListener methods
     public void keyTyped(KeyEvent e) {}

     public void keyPressed(KeyEvent e) {
         switch (e.getKeyCode()) {
             case KeyEvent.VK_UP: 
                 setDirection(Direction.UP); 
                 break;
             case KeyEvent.VK_DOWN: 
                 setDirection(Direction.DOWN); 
                 break;
             case KeyEvent.VK_LEFT: 
                 setDirection(Direction.LEFT); 
                 break;
             case KeyEvent.VK_RIGHT: 
                 setDirection(Direction.RIGHT); 
                 break;
         }
     }

     public void keyReleased(KeyEvent e) {
         if (e.getKeyCode() != KeyEvent.VK_TAB) { 
             stopMoving(); 
         }
     }

     // userControlledObject methods
     @Override
     public void moveUp() { 
         setDirection(Direction.UP); 
     }

     @Override
     public void moveDown() { 
         setDirection(Direction.DOWN); 
     }

     @Override
     public void moveLeft() { 
         setDirection(Direction.LEFT); 
     }

     @Override
     public void moveRight() { 
         setDirection(Direction.RIGHT); 
     }

     @Override
     public void stopMoving() { 
         setDirection(Direction.NONE); 
     }
}