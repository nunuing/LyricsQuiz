import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Music extends Thread {
    private Player player;
    private boolean isLoop;
    private InputStream is;
    private BufferedInputStream bis;
    private String resourcePath;

    public Music(String resourcePath, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            is = getClass().getResourceAsStream(resourcePath); // 리소스 파일을 읽는다
            bis = new BufferedInputStream(is); // 불러온 음악 파일을 버퍼에 담는다
            this.resourcePath = resourcePath;
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(resourcePath);
            e.printStackTrace();
        }
    }

    public int getTime() { // 음악이 실행되는 위치
        if (player == null)
            return 0;
        return player.getPosition();
    }

    public void close() { // 음악이 어느 위치에서 실행되더라도 종료될 수 있도록
        isLoop = false;
        player.close();
        this.interrupt(); // 해당 스레드를 종료
    }

    @Override
    public void run() {
        try {
            do {
                player.play();
                is = getClass().getResourceAsStream(resourcePath); // 리소스 파일을 읽는다
                bis = new BufferedInputStream(is); // 불러온 음악 파일을 버퍼에 담는다
                player = new Player(bis);
            } while (isLoop); // isLoop이 true값이라면 무한 반복
        } catch (Exception e) {
            System.out.println(e.getMessage()); // 오류 메시지 전송
        }
    }
}
