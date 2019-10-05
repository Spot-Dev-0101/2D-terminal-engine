public class SceneObject {

    public Pixel[] points;
    public String name;
    public Pixel center;

    public SceneObject(Pixel[] points, String name){
        this.points = points;
        this.name = name;
        this.center = Pixel.midPoint(points);
    }
}
