package sunhao.annotation;

import java.lang.reflect.Method;

import sunhao.annotation.annotations.AnnoInjection;
import sunhao.annotation.impl.AOPMethod;
import sunhao.annotation.impl.AnimalInterface;

public class Test {

	public static void main(String[] args) {
		DogImpl dogImp = new DogImpl();
		System.out.println(dogImp.getName());
		dogImp.getProperty();
		
		
		AnimalInterface dog = AnimalFactory.getAnimal(DogImpl.class, new AOPMethod() {
			// 这里写方法执行前的AOP切入方法
			public void before(Object proxy, Method method, Object[] args) {
			}

			// 这里系方法执行后的AOP切入方法
			public void after(Object proxy, Method method, Object[] args) {
			}
		});
		
		AnnoInjection.getBean(dog);
		System.out.println(dog.getName());
		dog.getProperty();
	}
}
