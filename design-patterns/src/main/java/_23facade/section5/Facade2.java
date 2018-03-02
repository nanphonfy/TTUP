package _23facade.section5;

/**
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
public class Facade2 {
	/**
	 * 为什么要使用委托而不重新写一个委托呢？
	 * 尽量保持相同代码只编写一遍，避免以后到处修改。
	 */
	//引用原有的门面
	private Facade facade = new Facade();
	
	//对外提供唯一的访问子系统的方法
	public void methodB(){
		this.facade.methodB();
	}
}
