package com.fas.common.utils;

import java.lang.reflect.Constructor;

public final class ClassUtils {

    /**
     * Default constructor.
     */
    private ClassUtils() {}

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> forName(final String className) throws ClassNotFoundException {

        ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        Class<?> cls = null;

        try {
            // Try with TCCL
            cls = Class.forName(className, true, tccl);
        } catch (ClassNotFoundException cnfe) {

            // Try now with the classloader used to load ClassUtils
            ClassLoader current = ClassUtils.class.getClassLoader();
            try {
                cls = Class.forName(className, true, current);
            } catch (ClassNotFoundException cnfe2) {
                // If this is still unknown, throw an Exception
                throw cnfe2;
            }
        }

        return cls;
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param className
     * @return
     */
    public static Object getInstanceOfClass(String className) {
    	
    	try {
    	    
    		Class<?>[] classParm = null;
    	    Object[] objectParm = null;
    	    
    		Class<?> cls = forName(className);
    		java.lang.reflect.Constructor<?> co = cls.getConstructor(classParm);
    		Object obj = co.newInstance(objectParm);
    		
    		return obj;
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param className
     * @return
     */
    public static Object getInstanceOfClass(String className, Class<?>[] classPara, Object[] objectParm) {
    	
    	try {
    	    
    		Class<?> cls = forName(className);
            Constructor<?> cList[] = cls.getDeclaredConstructors();
    		java.lang.reflect.Constructor<?> co = null;
    		for (int i = 0; i < cList.length; i++) {
    			Constructor<?> ct = cList[i];
    			Class<?> pvec[] = ct.getParameterTypes();
    			boolean isConstruct = true;
    			
    			if (classPara.length == pvec.length) {
	    			for (int j = 0; j < pvec.length; j++) {
	    				if (!pvec[j].getName().equals(classPara[j].getName())) {
	    					isConstruct = false;
	    				}
	    			}
    			} else {
    				isConstruct = false;
    			}
    			
    			if (isConstruct) {
    				co = cList[i];
    				break;
    			}
    		}
    		
    		Object obj = null;
    		if (co != null) {
    			obj = co.newInstance(objectParm);
    		}
    		
    		return obj;
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}