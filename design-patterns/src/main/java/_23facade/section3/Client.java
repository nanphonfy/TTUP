package _23facade.section3;

/**
 * 门面模式也叫外观模式，是一种比较常用的封装模式。
 * 简单说，门面对象是外界访问子系统（可能代表一个类或几十个对象的集合）内部的唯一通道，不管子系统内部多么杂乱无章。
 * 包括：
 * ①Facade门面角色：无实际业务逻辑，只是委托类；
 * ②sybsystem子系统角色：是类的集合，不知道门面的存在，门面只是一个客户端而已。
 *
 * eg.在某段特殊时期，需要对邮件进行安全检查。
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class Client {
	
	public static void main(String[] args) {
		//现代化的邮局，有这项服务，邮局名称叫Hell Road
		ModenPostOffice hellRoadPostOffice = new ModenPostOffice();
		//你只要把信的内容和收信人地址给他，他会帮你完成一系列的工作；
		//定义一个地址
		String address = "Happy Road No. 666,God Province,Heaven";
		//信的内容
		String context = "Hello,It's me,do you know who I am? I'm your old lover. I'd like to....";
		//你给我发送吧
		hellRoadPostOffice.sendLetter(context, address);
	}
}

