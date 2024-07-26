public class musicVO {
    private String title;
    private String title_eng = "";
    private String singer;
    private String singer_eng = "";
    private String[] hints;
    private String mfile;
    public int hidx = 0;

    public musicVO(String title, String singer) {
        this.singer = singer;
        this.title = title;
    }

    public musicVO(String title, String singer, String[] hints, String mfile) {
        this.title = title;
        this.singer = singer;
        this.hints = hints;
        this.mfile = mfile;
    }

    public musicVO(String title, String title_eng, String singer, String singer_eng, String[] hints, String mfile) {
        this.title = title;
        if (title_eng != null)
            this.title_eng = title_eng.toLowerCase();
        this.singer = singer;
        if (singer_eng != null)
            this.singer_eng = singer_eng.toLowerCase();
        this.hints = hints;
        this.mfile = mfile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String[] getHints() {
        return hints;
    }

    public void setHints(String[] hints) {
        this.hints = hints;
    }

    public String getMfile() {
        return mfile;
    }

    public void setMfile(String mfile) {
        this.mfile = mfile;
    }

    public String getSinger_eng() {
        return singer_eng;
    }

    public void setSinger_eng(String singer_eng) {
        this.singer_eng = singer_eng;
    }

    public String getTitle_eng() {
        return title_eng;
    }

    public void setTitle_eng(String title_eng) {
        this.title_eng = title_eng;
    }
}
