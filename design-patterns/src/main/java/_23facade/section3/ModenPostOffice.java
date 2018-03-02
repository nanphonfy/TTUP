package _23facade.section3;

/**
 * 只增加了letterPolice变量和一个方法调用，整个写信过程就变了。
 * 高层模块没有做任何改动，但信封却已被检查。
 * 【设计模式：门面模式】不改变子系统对外暴露的接口、方法，只改变内部的处理逻辑。
 * 其他兄弟模块的调用产生了不同的结果
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class ModenPostOffice {
	private ILetterProcess letterProcess = new LetterProcessImpl();
	private Police letterPolice = new Police();
	
	//写信，封装，投递，一体化了
	public void sendLetter(String context,String address){
		//帮你写信
		letterProcess.writeContext(context);
		
		//写好信封
		letterProcess.fillEnvelope(address);

		//警察要检查信件了
		letterPolice.checkLetter(letterProcess);
		
		//把信放到信封中
		letterProcess.letterInotoEnvelope();
			
		//邮递信件
		letterProcess.sendLetter();
	}
}
