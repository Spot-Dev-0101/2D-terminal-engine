public class Main {

    public static Display display;
    public static Scene scene;

    public static void main(String[] args) {

        display = new Display(80, 20);
        scene = new Scene();

        SceneObject obj = new SceneObject(new Pixel[]{new Pixel(2, 2), new Pixel(10, 2), new Pixel(10, 10), new Pixel(2, 10)}, "Some obj");

        scene.addObjects(new SceneObject[]{obj});

        display.Update(scene);
    }
}
