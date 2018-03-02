package _23facade.section7;

/**
 * 在门面模式中，门面角色应该是稳定的，不应经常变换，一旦系统投入运行就不应被改变。
 * 但业务逻辑会经常变化，将变化封装在子系统内部。
 * 无论如何变化，对外界访问者来说，都还是同一个门面。
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class Facade {
	//被委托的对象
	private ClassA a = new ClassA();
	private ClassB b = new ClassB();
	private Context context = new Context();
	
	//提供给外部访问的方法
	public void methodA(){
		this.a.doSomethingA();
	}
	
	public void methodB(){
		this.b.doSomethingB();
	}

	/**
	 * 门面对象通过对它访问，完成一个复杂的业务逻辑。
	 */
	public void methodC(){
		this.context.complexMethod();
	}
}
