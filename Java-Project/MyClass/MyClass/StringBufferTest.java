package MyClass;

public class StringBufferTest {
    public static void main(String[] args) {
    StringBuffer a = new StringBuffer("A");
    StringBuffer b = new StringBuffer("B");
    operate(a,b);
    System.out.println(a+","+b);

    String aa = new String("A");
    String bb = new String("B");
    operate2(aa,bb);
    System.out.println(aa+","+bb);        
    }
static void operate(StringBuffer x,StringBuffer y){
    x.append(y);
    y=x;
}
static void operate2(String x,String y){
    x=x+y;
    y=x;
}
}