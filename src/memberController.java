import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class memberController {
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

    public int insert(memberVO memberVO) {
        int result = 0;
        try {
            getConnection();
            String sql = "insert into sc_bigdata25_MEMBER (ID, PW) values (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberVO.getId());
            pstmt.setString(2, memberVO.getPw());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("");
        } finally {
            getClose();
        }
        return result;
    }

    public int delete(memberVO memberVO) {
        int result = 0;
        try {
            getConnection();
            String sql = "delete from sc_bigdata25_MEMBER where id = ? and pw = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberVO.getId());
            pstmt.setString(2, memberVO.getPw());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getClose();
        }
        return result;
    }

    public memberVO login(memberVO memberVO) {
        memberVO result = null;
        try {
            getConnection();
            String sql = "select * from sc_bigdata25_MEMBER where id = ? and pw = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberVO.getId());
            pstmt.setString(2, memberVO.getPw());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String memberId = rs.getString("id");
                int memberScore = rs.getInt("score");
                result = new memberVO(memberId, memberScore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getClose();
        }
        return result;
    }

    public ArrayList<memberVO> getRank() {
        ArrayList<memberVO> result = new ArrayList<>();
        try {
            getConnection();
            String sql = "select * from sc_bigdata25_MEMBER order by score desc, score_updated desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String memberId = rs.getString("id");
                int memberScore = rs.getInt("score");
                result.add(new memberVO(memberId, memberScore));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getClose();
        }
        return result;
    }

    public int update(memberVO memberVO) {
        int result = 0;
        try {
            getConnection();
            String sql = "update sc_bigdata25_MEMBER set score = ?, score_updated = ? where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberVO.getScore());
            pstmt.setTimestamp(2, memberVO.getUpdated());
            pstmt.setString(3, memberVO.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getClose();
        }
        return result;
    }


}
