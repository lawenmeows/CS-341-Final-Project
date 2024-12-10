public class MyMain {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        
        // add Type D first to make it the initially controlled object
        Type_D_GameObject typeD = new Type_D_GameObject(200, 200);
        typeD.setDirection(Direction.NONE); 
        
        Type_A_GameObject typeA = new Type_A_GameObject(100, 100);
        typeA.setDirection(Direction.NONE);
        
        Type_B_GameObject typeB = new Type_B_GameObject(300, 300);
        Type_C_GameObject typeC = new Type_C_GameObject(500, 500);
        
        canvas.addGameObject(typeD); // add Type D first
        canvas.addGameObject(typeA);
        canvas.addGameObject(typeB);
        canvas.addGameObject(typeC);
    }
}