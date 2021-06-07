import javax.swing.*;
import java.text.DecimalFormat;

public class Chrono extends Thread {
    private int temps;
    boolean demarre;

    Chrono(int temps){
        this.temps=temps;
    }

    public void run(){
        demarre=true;
        float t=0;

        while (demarre) {
            try{
                this.sleep(temps);

                t+=0.1;
                //System.out.println(t);
                DecimalFormat df = new DecimalFormat("#######0.0");
                String str = df.format(t);
/*
                temps.setText(str);
*/
            }
            catch(InterruptedException e){

            }
        }
    }

    public void terminate(){
        demarre=false;
    }
}