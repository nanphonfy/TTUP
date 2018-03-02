package _23facade.section6;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public class Facade {
	//被委托的对象
	private ClassA a = new ClassA();
	private ClassB b = new ClassB();
	private ClassC c = new ClassC();
	
	//提供给外部访问的方法
	public void methodA(){
		this.a.doSomethingA();
	}
	
	public void methodB(){
		this.b.doSomethingB();
	}

	/**
	 * 【门面不参与子系统内的业务逻辑】
	 * 只在methodC增加了a.doSomethingA，然后再执行c.doSomethingC。
	 * 该设计不靠谱，因为门面对象只提供一个访问子系统的路径而已，不该参与具体的业务逻辑。
	 * 否则会产生倒依赖问题：子系统必须依赖门面才能被访问。（违反单一职责，也破坏了封装性）
	 */
	public void methodC(){
		this.a.doSomethingA();
		this.c.doSomethingC();
	}
}
