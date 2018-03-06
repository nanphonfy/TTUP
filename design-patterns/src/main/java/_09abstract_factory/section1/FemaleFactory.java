package _09abstract_factory.section1;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * Ů�Թ���
 */
public class FemaleFactory implements HumanFactory {
	//����������Ů��
	@Override public Human createBlackHuman() {
		return new FemaleBlackHuman();
	}

	//����������Ů��
	@Override public Human createWhiteHuman() {
		return new FemaleWhiteHuman();
	}

	//����������Ů��
	@Override public Human createYellowHuman() {
		return new FemaleYellowHuman();
	}

}
