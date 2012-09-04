package Demo;

public class ClassB {

public ClassB(int x)
{
// gan lai
MyResx.myVar = x; 
}

public static void main(String args[])
{
	new ClassA(10);
	System.out.println(String.valueOf(MyResx.myVar));
	new ClassB(15);
	System.out.println(String.valueOf(MyResx.myVar));
}
}