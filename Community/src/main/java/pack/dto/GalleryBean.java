package pack.dto;

public class GalleryBean {
    private int num;
    private String uId;
    private String uName;
    private String subject;
    private String category;  // 카테고리
    private String content;
    private int pos;
    private int ref;
    private int depth;
    private String regTM;
    private String ip;
    private int readCnt;
    private String fileName;
    private long fileSize;  // 파일 크기 (long으로 변경)
    
    // 기본 게시물 정보
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getuId() {
        return uId;
    }
    public void setuId(String uId) {
        this.uId = uId;
    }
    public String getuName() {
        return uName;
    }
    public void setuName(String uName) {
        this.uName = uName;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getCategory() {  // 수정된 getter
        return category;
    }
    public void setCategory(String category) {  // 수정된 setter
        this.category = category;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getPos() {
        return pos;
    }
    public void setPos(int pos) {
        this.pos = pos;
    }
    public int getRef() {
        return ref;
    }
    public void setRef(int ref) {
        this.ref = ref;
    }
    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public String getRegTM() {
        return regTM;
    }
    public void setRegTM(String regTM) {
        this.regTM = regTM;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public int getReadCnt() {
        return readCnt;
    }
    public void setReadCnt(int readCnt) {
        this.readCnt = readCnt;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public long getFileSize() {  // 수정된 getter
        return fileSize;
    }
    public void setFileSize(long fileSize) {  // 수정된 setter
        this.fileSize = fileSize;
    }
}
