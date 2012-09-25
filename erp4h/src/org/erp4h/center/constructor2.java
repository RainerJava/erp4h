package org.erp4h.center;
import java.lang.reflect.*;
        
public class constructor2 {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]){
		try {
			Class cls = Class.forName("BusinessLogicLayer.UserRightBLL");
			Class partypes[] = new Class[2];
            partypes[0] = Integer.TYPE;
            partypes[1] = Integer.TYPE;
            Constructor ct= cls.getConstructor();
            Object arglist[] = new Object[2];
            arglist[0] = new Integer(37);
            arglist[1] = new Integer(47);
            Object retobj = ct.newInstance(arglist);
         }
         catch (Throwable e) {
            System.err.println(e);
         }
      }
        
	public constructor2()
	{
	}
        
	public constructor2(int a, int b){
		System.out.println("a = " + a + " b = " + b);
	}
 }