package sunhao.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import sunhao.annotation.impl.AOPMethod;

public class AOPHandler implements InvocationHandler{
	
	private AOPMethod method;
	private Object o;
	public AOPHandler(Object o,AOPMethod method){
		this.o = o;
		this.method = method;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object ret = null;
		this.method.before(proxy, method, args);
		
		ret = method.invoke(o, args);
		
		this.method.after(proxy, method, args);
		return ret;
	}

}
