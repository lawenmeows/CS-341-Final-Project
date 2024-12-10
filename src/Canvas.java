import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Canvas extends JComponent implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;

    private JFrame frame;
    private Timer gameLoopTimer;
    private List<GameObject> gameObjectList;
    private int highlighted = 0; // start from the first object
    private UserControlledObject currentControlled;

    public Canvas() {
        gameObjectList = new ArrayList<>();
        frame = new JFrame("Animation Canvas");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);

        gameLoopTimer = new Timer(25, this);
        gameLoopTimer.start();

        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);

        frame.setVisible(true);
        
        setFocusable(true);
        requestFocusInWindow();
    }

    public synchronized void addGameObject(GameObject sprite) {
        gameObjectList.add(sprite);
        
        // automatically set the first object as the controlled object
        if (gameObjectList.size() == 1) {
            highlighted = 0; // set highlighted to 0 to select the first object
            updateCurrentControlled(); // update current controlled immediately after adding
            // set initial direction for immediate movement
            if (currentControlled != null) {
                currentControlled.setImage(); // update image based on direction
            }
        }
    }

    private void updateCurrentControlled() {
        if (highlighted >= 0 && highlighted < gameObjectList.size()) {
            GameObject selected = gameObjectList.get(highlighted);
            if (selected != null) { // check if selected is not null
                System.out.println("Updating current controlled: " + selected.getClass().getSimpleName());
                if (selected instanceof Type_A_GameObject) {
                    currentControlled = new Type_A_Adapter((Type_A_GameObject) selected);
                } else if (selected instanceof Type_B_GameObject) {
                    currentControlled = new Type_B_Adapter((Type_B_GameObject) selected);
                } else if (selected instanceof Type_C_GameObject) {
                    currentControlled = new Type_C_Adapter((Type_C_GameObject) selected);
                } else if (selected instanceof Type_D_GameObject) {
                    currentControlled = (UserControlledObject) selected;
                }
            }
        }
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < gameObjectList.size(); i++) {
            GameObject obj = gameObjectList.get(i);
            if (obj != null && obj.getCurrentImage() != null) {
                obj.draw(this, g);
                if (i == highlighted) {
                    g.setColor(Color.RED);
                    g.drawRect(obj.getX() - 2, obj.getY() - 2,
                               obj.getCurrentImage().getIconWidth() + 4,
                               obj.getCurrentImage().getIconHeight() + 4);
                }
            } else {
                System.out.println("Warning: Null object or image at index " + i);
            }
        }
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        // move only the currently controlled object
        if (currentControlled != null) {
            currentControlled.move(this); // call move method of UserControlledObject
            currentControlled.setImage(); // update image based on direction
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
        if (currentControlled != null) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    System.out.println("Moving up: " + currentControlled.getClass().getSimpleName());
                    currentControlled.moveUp();
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("Moving down: " + currentControlled.getClass().getSimpleName());
                    currentControlled.moveDown();
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("Moving left: " + currentControlled.getClass().getSimpleName());
                    currentControlled.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("Moving right: " + currentControlled.getClass().getSimpleName());
                    currentControlled.moveRight();
                    break;
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        System.out.println("Key released: " + KeyEvent.getKeyText(e.getKeyCode()));
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            highlighted = (highlighted + 1) % gameObjectList.size(); // cycle through all objects
            updateCurrentControlled(); // update the currently controlled object
            System.out.println("Switched to: " + (currentControlled != null ? currentControlled.getClass().getSimpleName() : "null"));
            repaint();
        } else if (currentControlled != null) {
            currentControlled.stopMoving();
        }
    }

    public void requestFocus() {
        frame.requestFocus();
    }
}