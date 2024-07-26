import java.sql.Timestamp;
import java.time.Instant;

public class memberVO{
    private String id;
    private String pw;
    private int score;
    private Timestamp updated;
    


	public memberVO(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public memberVO(String id, int score) {
        this.id = id;
        this.score = score;
    }

    public Timestamp getUpdated() {
    	return updated;
    }
    
    public void setUpdated(Timestamp updated) {
    	this.updated = updated;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        if (this.score <= score) {
            this.score = score;
            updated = Timestamp.from(Instant.now());
//          db 업데이트 하는 함수 호출

        }
    }

	public memberVO(int score, Timestamp updated) {
		super();
		this.score = score;
		this.updated = updated;
	}
    

}
