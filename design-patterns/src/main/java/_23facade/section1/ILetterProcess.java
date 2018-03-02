package _23facade.section1;

/**
 * 写信过程接口
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public interface ILetterProcess {
	//首先要写信的内容
	void writeContext(String context);
	//其次写信封
	void fillEnvelope(String address);
	//把信放到信封里
	void letterInotoEnvelope();
	//然后邮递
	void sendLetter();
}
