package sunhao.annotation.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnoInjection {

	private static final Logger logger = LoggerFactory.getLogger(AnnoInjection.class);
	
	public static Object getBean(Object obj){
		try{
			Field[] f = obj.getClass().getDeclaredFields();
			if(f != null){
				for(Field ff : f){
					Seven s = ff.getAnnotation(Seven.class);
					if(s != null){
						logger.info("注入" + ff.getName() + "属性" + "\t\t" + s.value());
						obj.getClass().getMethod("set"+ff.getName().substring(0,1).toUpperCase() + 
								ff.getName().substring(1), new Class<?>[]{String.class})
								.invoke(obj,s.value());
					}
				}
			}
			
			Method[] m = obj.getClass().getDeclaredMethods();
			if(m != null){
				for(Method mm : m){
					Seven s = mm.getAnnotation(Seven.class);
					if(s != null){
						logger.info("注入" + mm.getName() + "方法" + "\t\t" + s.Property());
						mm.invoke(obj, s.Property());
					}
				}
			}
		}catch(Exception e){
			logger.error("Error",e);
		}
		
		return obj;
	}
}
