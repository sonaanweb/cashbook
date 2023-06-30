package cash.vo;

public class Member {
	 private String memberId;
	 private String memberPw;
	 private String updatedate;
	 private String createdate;
 
	public Member() { //기본 생성자 - 매개변수가 없는 형태. 기본 생성자를 명시적으로 정의해줌으로써 객체를 인스턴스화할 때 사용
		super();
	}
	 public Member(String memberId, String memberPw, String updatedate, String createdate) {
			// 객체를 생성할 때 필요한 매개변수를 받아 초기화하는 역할. 각 매개변수는 해당 필드에 대한 값을 전달받아 객체의 상태 설정
	     this.memberId = memberId;
	     this.memberPw = memberPw;
	     this.updatedate = updatedate;
	     this.createdate = createdate;
	 }
	
	 
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", updatedate=" + updatedate
				+ ", createdate=" + createdate + "]";
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	 
	 
}
