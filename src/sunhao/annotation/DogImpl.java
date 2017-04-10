package sunhao.annotation;

import sunhao.annotation.annotations.Seven;
import sunhao.annotation.impl.AnimalInterface;

public class DogImpl implements AnimalInterface {

	@Seven(value = "Lumia")
	private String name;

	private String Property;

	public DogImpl() {
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void say() {
		System.out.println("小狗:汪汪汪汪.....");
	}

	@Override
	@Seven(Property = "水陆两栖战士")
	public void setProperty(String Property) {
		this.Property = Property;
	}

	@Override
	public void getProperty() {
		System.out.println(this.name + this.Property);
	}
}
