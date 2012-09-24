import java.util.ResourceBundle;


public class test {
	public static void main(String[]args){
		String bundleName = "resources.application";
		ResourceBundle rb = ResourceBundle.getBundle("DRIVER");
		System.out.println(rb);
	}
}
