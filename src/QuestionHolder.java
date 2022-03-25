public class QuestionHolder
{
	String qNo,Q,optA,optB,optC,optD,optRight;

	public QuestionHolder(String qNo,String Q, String optA, String optB, String optC, String optD,String optRight) {
		this.qNo=qNo;
		this.Q=Q;
		this.optA=optA;
		this.optB=optB;
		this.optC=optC;
		this.optD=optD;
		this.optRight=optRight;
		
		// TODO Auto-generated constructor stub
	}
	public String getqNo() {
		return qNo;
	}

	public void setqNo(String qNo) {
		this.qNo = qNo;
	}

	public String getQ() {
		return Q;
	}

	public void setQ(String q) {
		Q = q;
	}

	public String getOptA() {
		return optA;
	}

	public void setOptA(String optA) {
		this.optA = optA;
	}

	public String getOptB() {
		return optB;
	}

	public void setOptB(String optB) {
		this.optB = optB;
	}

	public String getOptC() {
		return optC;
	}

	public void setOptC(String optC) {
		this.optC = optC;
	}

	public String getOptD() {
		return optD;
	}

	public void setOptD(String optD) {
		this.optD = optD;
	}

	public String getOptRight() {
		return optRight;
	}

	public void setOptRight(String optRight) {
		this.optRight = optRight;
	}
	
}
