package cn.test.domain;

public class Question {
	private Integer qid;
	private Integer eid;
	private String qtype;
	private String qtitle;
	private String qcontent;
	private Integer qscore;
	private String qanswer;
	private String qdegree;
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public String getQtitle() {
		return qtitle;
	}
	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public Integer getQscore() {
		return qscore;
	}
	public void setQscore(Integer qscore) {
		this.qscore = qscore;
	}
	public String getQanswer() {
		return qanswer;
	}
	public void setQanswer(String qanswer) {
		this.qanswer = qanswer;
	}
	public String getQdegree() {
		return qdegree;
	}
	public void setQdegree(String degree) {
		this.qdegree = degree;
	}
	
   
}
