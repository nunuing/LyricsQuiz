
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class musicController {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private void getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
            String user = "sc_21K_bigdata25_p1_1";
            String password = "smhrd1";
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getClose() {
        try {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Music musicPlay(musicVO music) {
        Music played = null;
        try {
            getConnection();
            String sql = "SELECT * FROM sc_bigdata25_MUSIC WHERE TITLE = ? AND SINGER = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, music.getTitle());
            pstmt.setString(2, music.getSinger());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String mFile = rs.getString("mfile");
                System.out.println(mFile);
                played = new Music("/music/" + mFile, true);
                played.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return played;
    }

    public ArrayList<musicVO> getList() {
        ArrayList<musicVO> result = new ArrayList<>();
        try {
            getConnection();
            String sql = "select * from sc_bigdata25_MUSIC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String mtitle = rs.getString("title");
                String msinger = rs.getString("singer");
                String title_eng = rs.getString("title_eng");
                String singer_eng = rs.getString("singer_eng");
                String[] hints = new String[3];
                hints[0] = rs.getString("hint1");
                hints[1] = rs.getString("hint2");
                hints[2] = rs.getString("hint3");

                String mfile = rs.getString("mfile");

                result.add(new musicVO(mtitle, title_eng, msinger, singer_eng, hints, mfile));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getClose();
        }
        return result;
    }
}
