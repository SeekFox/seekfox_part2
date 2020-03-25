package vue;
import processing.core.*;


public class ProcessingMain extends PApplet{

    public void settings(){
        size(200, 200);
    }

    public void setup(){

    }

    public void draw(){
        background(0);
        ellipse(mouseX, mouseY, 20, 20);
    }

    public static void main(String... args){
        PApplet.main("ProcessingMain.java");
    }

}
