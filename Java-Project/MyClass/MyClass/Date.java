package MyClass;

public class Date{
    static String[] date = {"һ","��","��","��","��","��","��"};
    public static void main(String[] args){
        int i = Integer.parseInt(args[0]);
        System.out.println("����������"+date[i-1]);
    }
}