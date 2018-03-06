package _09abstract_factory.section1;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ���Թ���
 */
public class MaleFactory implements HumanFactory {
	//��������������
	@Override public Human createBlackHuman() {
		return new MaleBlackHuman();
	}

	//��������������
	@Override public Human createWhiteHuman() {
		return new MaleWhiteHuman();
	}

	//��������������
	@Override public Human createYellowHuman() {
		return new MaleYellowHuman();
	}

}
