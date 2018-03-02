package _23facade.section4;

/**
 * 门面模式也叫外观模式，是一种比较常用的封装模式。
 * 简单说，门面对象是外界访问子系统（可能代表一个类或几十个对象的集合）内部的唯一通道，不管子系统内部多么杂乱无章。
 * 包括：
 * ①Facade门面角色：无实际业务逻辑，只是委托类；
 * ②sybsystem子系统角色：是类的集合，不知道门面的存在，门面只是一个客户端而已。
 *
 * 【门面模式的优点】
 * ①减少系统的相互依赖；
 * 若外界直接深入子系统内部，相互强耦合。
 * 门面模式的所有依赖都是对门面对象的依赖，与子系统无关。
 * ②提高了灵活性；
 * 依赖减少，灵活性提高。
 * 不管子系统内部如何变化，只要不影响门面对象，任你自由活动。
 * ③提高安全性。
 * 不在门面开通的方法，无法访问。
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class Facade {
	//被委托的对象
	//sybsystem子系统角色包含以下三个类
	/**
	 * 这3个类属于近邻，处理相关业务。一个子系统的不同逻辑处理模块。
	 * 对于此子系统访问，需通过门面。
	 */
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
	
	public void methodC(){
		this.c.doSomethingC();
	}
}
