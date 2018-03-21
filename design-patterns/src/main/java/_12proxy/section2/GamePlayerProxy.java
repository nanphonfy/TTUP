package _12proxy.section2;

/**
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 *         ������
 */
public class GamePlayerProxy implements IGamePlayer {
    private IGamePlayer gamePlayer = null;

    //ͨ�����캯������Ҫ��˭���д���
    public GamePlayerProxy(IGamePlayer _gamePlayer) {
        this.gamePlayer = _gamePlayer;
    }

    //����ɱ��
    @Override public void killBoss() {
        this.gamePlayer.killBoss();
    }

    //������¼
    @Override public void login(String user, String password) {
        this.gamePlayer.login(user, password);
    }

    //��������
    @Override public void upgrade() {
        this.gamePlayer.upgrade();
    }
}
