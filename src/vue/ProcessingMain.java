package vue;
import processing.core.*;


public class ProcessingMain extends PApplet{
    //Attributs
    public static PApplet processing;


    //Méthodes
    public static void main(String[] args){
        PApplet.main("vue.ProcessingMain",args);
    }

    public void settings(){
        size(200, 200);
    }

    public void setup(){
        processing=this;

    }

    public void draw(){
        background(0);
        ellipse(mouseX, mouseY, 20, 20);
    }



}
