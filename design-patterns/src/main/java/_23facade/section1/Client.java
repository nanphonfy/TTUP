package _23facade.section1;

/**
 * 场景类
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public class Client {

	/**
	 *与高内聚相差甚远，更别提迪米特法则、接口隔离原则了。
	 ①要知道这4步骤；
	 ②顺序出错即不能邮寄；
	 ③没有完成一个类的单一职责；
	 ④信件多了，每封都要这样运转一遍。
	 * @param args
     */
	public static void main(String[] args) {
		//创建一个处理信件的过程
		ILetterProcess letterProcess = new LetterProcessImpl();	
		//开始写信
		letterProcess.writeContext("Hello,It's me,do you know who I am? I'm your old lover. I'd like to....");	
		//开始写信封
		letterProcess.fillEnvelope("Happy Road No. 666,God Province,Heaven");	
		//把信放到信封里，并封装好
		letterProcess.letterInotoEnvelope();	
		//跑到邮局把信塞到邮箱，投递
		letterProcess.sendLetter();
	}
}
