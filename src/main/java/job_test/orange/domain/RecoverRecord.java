package job_test.orange.domain;

public class RecoverRecord {
    private Long id;

    private String loanId;

    private Byte status;

    private String content;

    private Long createTime;

    private Long repeatTime;

    private Integer recoverUid;

    private String recoverNickname;

    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId == null ? null : loanId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(Long repeatTime) {
        this.repeatTime = repeatTime;
    }

    public Integer getRecoverUid() {
        return recoverUid;
    }

    public void setRecoverUid(Integer recoverUid) {
        this.recoverUid = recoverUid;
    }

    public String getRecoverNickname() {
        return recoverNickname;
    }

    public void setRecoverNickname(String recoverNickname) {
        this.recoverNickname = recoverNickname == null ? null : recoverNickname.trim();
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}