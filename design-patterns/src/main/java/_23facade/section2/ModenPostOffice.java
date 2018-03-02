package _23facade.section2;

/**
 * 邮局开发的新业务，只要告知信件必要信息，即可完成4个步骤。
 * 增加了一个ModenPostOffice（负责信封处理过程，高层模块只要与它交互即可）
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class ModenPostOffice {
	private ILetterProcess letterProcess = new LetterProcessImpl();
	
	//写信，封装，投递，一体化了
	public void sendLetter(String context,String address){
		//帮你写信
		letterProcess.writeContext(context);
		
		//写好信封
		letterProcess.fillEnvelope(address);
		
		//把信放到信封中
		letterProcess.letterInotoEnvelope();
			
		//邮递信件
		letterProcess.sendLetter();
	}
}
