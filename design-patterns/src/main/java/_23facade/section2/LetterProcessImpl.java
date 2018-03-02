package _23facade.section2;

/**
 * 只要与 交互，其他的（步骤、顺序等）不用关心。既简单，又有扩展性。
 * eg.在某段特殊时期，需要对邮件进行安全检查。
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class LetterProcessImpl implements ILetterProcess {
	//写信
	@Override public void writeContext(String context) {
		System.out.println("填写信的内容...." + context);
	}
	//在信封上填写必要的信息
	@Override public void fillEnvelope(String address) {
		System.out.println("填写收件人地址及姓名...." + address);
	}
	//把信放到信封中，并封好
	@Override public void letterInotoEnvelope() {
		System.out.println("把信放到信封中....");
	}
	//塞到邮箱中，邮递
	@Override public void sendLetter() {
		System.out.println("邮递信件...");
	}
}
