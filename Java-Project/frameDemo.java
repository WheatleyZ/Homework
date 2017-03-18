import java.awt.*;

public class frameDemo {
    public static void main(String[] args){
        Frame f =new Frame("MDZZ");
        f.setSize(500,300);
        f.show(true);//=f.setVisable(true);
        //Button button=new Button("name");
        //f.add(button);
        Panel p=new Panel();
        f.add(p,"North");
        for(int i=0;i<20;i++){
            p.add(new Button("Space"+i));
        }

        String[] location ={"South","West","East","Center"};
        for(int i=0;i<10;i++){
            f.add(new Button("Student"+i),location[i%4]);
        }


    }
}
