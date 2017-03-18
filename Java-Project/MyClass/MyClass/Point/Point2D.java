package MyClass.Point;

public class Point2D{
    public int x,y;
    public void Point2D(){
        x=0;
        y=0;
    }
    public void offset(int a,int b){
        this.x+=a;
        this.y+=b;
    }
    public void print(){
        System.out.println("x="+x+"\ny="+y);
    }
}