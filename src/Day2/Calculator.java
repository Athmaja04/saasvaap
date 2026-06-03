package Day2;
import java.util.*;
class DividezeroException extends Exception
{
    DividezeroException(String message)
    {
        super(message);
    }
}
public class Calculator {
    public static void add(int a,int b)
    {
        System.out.println("Result= "+(a+b));
    }
    public static void sub(int a,int b)
    {
        System.out.println("Result= "+(a-b));
    }
    public static void div(int a,int b) throws DividezeroException
    {
        if(b==0)
        {
            throw new DividezeroException("Cannot divide by zero");
        }
        System.out.println("Result = " + (a / b));
    }
    public static void mul(int a,int b)
    {
        System.out.println("Result= "+(a*b));
    }
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        int choice = 1;

        do {
            System.out.println("Enter your choice:\n1.ADD\n2.SUBTRACT\n3.DIVIDE\n4.MULTIPLY\n5.QUIT\n");
            int ch=sc.nextInt();
            if(ch==5)
            {
                break;
            }
            System.out.println("Enter 2 Numbers:");
            int a=sc.nextInt();
            int b=sc.nextInt();

            switch(ch){
                case 1:
                    add(a,b);
                    break;
                case 2:
                    sub(a,b);
                    break;
                case 3:
                    try {
                        div(a, b);
                    }
                    catch(DividezeroException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    mul(a,b);
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        }while(choice==1);
    }
}
