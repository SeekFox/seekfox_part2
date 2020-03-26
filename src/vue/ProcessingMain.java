package vue;
import processing.core.*;


public class ProcessingMain extends PApplet{
    Button testButton = new Button(100,100,200,50, color(0,255,255), "c la teuf", this);
    Slider testSlider = new Slider(100,300,100,0,100,this);
    Textbox testbox = new Textbox(300,100,100,30,"moncul",this);

    public void settings(){
        size(640,480);

    }

    public void setup(){


        //background(255);
    }




    public void draw(){
        background(color(255));
        testButton.display();
        testbox.display();
        testSlider.display();
    }




    public void mousePressed(){
        if(testButton.isPressed())
            println("ptdr");

        testbox.clickParsing();
        testSlider.clickParsing();
    }
    public void mouseReleased(){
        testButton.release();
        testbox.release();
        testSlider.release();
    }

    public void keyPressed(){
        testbox.keyPressedParsing(key);
    }

    public static void main(String... args){
        PApplet.main("vue.ProcessingMain");
    }

}
