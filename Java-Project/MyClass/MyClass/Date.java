package MyClass;

public class Date{
    static String[] date = {"一","二","三","四","五","六","日"};
    public static void main(String[] args){
        int i = Integer.parseInt(args[0]);
        System.out.println("今天是星期"+date[i-1]);
    }
}