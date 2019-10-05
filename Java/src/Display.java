import java.util.ArrayList;

public class Display {

    private int width;
    private int height;
    public static String pixelChar;
    private Pixel[][] pixels;

    public Display(int w, int h){
        this.width = w;
        this.height = h;
        pixelChar = "▒";
    }

    public void Update(Scene scene){
        createInitPixels();
        ArrayList<SceneObject> objects = scene.objects;
        for(SceneObject obj : objects){
            ArrayList<Pixel> outline = outLinePixels(obj);
            for(Pixel pixel : outline){
                pixels[pixel.x][pixel.y].value = "▓";
            }
            ArrayList<Pixel> inner = innerPixels(outline, obj);
            for(Pixel pixel : inner){
                pixels[pixel.x][pixel.y].value = "▓";
            }
        }

        drawPixels();
    }

    private void drawPixels(){
        for(Pixel[] row : pixels){
            String tempRow = "";
            for(Pixel pixel : row){
                tempRow += pixel.value;
            }
            System.out.println(tempRow);
        }
    }

    private void createInitPixels(){
        pixels = new Pixel[height][width];

        for (int x = 0; x < height; x++){
            for (int y = 0; y < width; y++){
                pixels[x][y] = new Pixel(x, y);
            }
        }
    }

    private ArrayList<Pixel> outLinePixels(SceneObject obj){
        ArrayList<Pixel> outLinePixels = new ArrayList<Pixel>();
        Pixel[] points = obj.points;
        for(int i = 0; i < points.length; i++){


            Pixel start = points[i];
            Pixel end;
            if(i >= points.length-1){
                start = points[0];
                end = points[i];
            } else{
                end = points[i+1];
            }


            if(end.x < start.x){
                start = points[i+1];
                end = points[i];
            }

            double length = Math.sqrt(((end.x*end.x)-(start.x*start.x))+((end.y*end.y)-(start.y*start.y)));

            for(int z = 0; z < Math.abs(length); z++){
                double t = z/length;
                int x = (int)Math.round((1-t)*start.x+t*end.x);
                int y = (int)Math.round((1-t)*start.y+t*end.y);
                outLinePixels.add(new Pixel(x, y));
            }
        }

        return outLinePixels;
    }

    private ArrayList<Pixel> innerPixels(ArrayList<Pixel> outline, SceneObject obj){
        ArrayList<Pixel> innerPixels = new ArrayList<Pixel>();

        Pixel start = obj.center;
        innerPixels.add(start);

        for(int i = 0; i < innerPixels.size(); i++){
            Pixel point = innerPixels.get(i);

            if(arrayIncludes(outline, point) == false){
                innerPixels.add(point);
                Pixel n = new Pixel(point.x, point.y-1);
                Pixel s = new Pixel(point.x, point.y+1);
                Pixel e = new Pixel(point.x+1, point.y);
                Pixel w = new Pixel(point.x-1, point.y);

                if(isPixelValid(n, innerPixels) && isPixelValid(n, outline)){
                    innerPixels.add(n);
                }
                if(isPixelValid(s, innerPixels) && isPixelValid(s, outline)){
                    innerPixels.add(s);
                }
                if(isPixelValid(e, innerPixels) && isPixelValid(e, outline)){
                    innerPixels.add(e);
                }
                if(isPixelValid(w, innerPixels) && isPixelValid(w, outline)){
                    innerPixels.add(w);
                }
            }

            if(innerPixels.size() >= 500){
                System.out.println("Reached limit");
                break;
            }
        }

        return innerPixels;
    }

    private boolean isPixelValid(Pixel pixel, ArrayList<Pixel> array){
        if(arrayIncludes(array, pixel) == false && pixel.x >= 0 && pixel.y >= 0){
            return true;
        }
        return false;
    }

    private boolean arrayIncludes(ArrayList<Pixel> array, Pixel item){
        for(Pixel pixel : array){
            if(pixel.x == item.x && pixel.y == item.y){
                return true;
            }
        }
        return false;
    }

}