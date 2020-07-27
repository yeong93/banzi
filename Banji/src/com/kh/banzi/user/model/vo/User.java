package com.kh.banzi.user.model.vo;

public class User {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userEmail;
	private String userGrade;
	private String userQuestion;
	private String userAnswer;
	private String userStatus;
	private String userAuth;
	
	// (1) 기본 생성자 작성
	public User() {
		
	}
	
	// (2) 매개변수 있는 생성자 작성
	public User(int userNo, String userId, String userPwd, String userName, String userEmail, String userQuestion,
			String userAnswer, String userGrade, String userStatus, String userAuth) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userQuestion = userQuestion;
		this.userAnswer = userAnswer;
		this.userGrade = userGrade;
		this.userStatus = userStatus;
		this.userAuth = userAuth;
	}
	
	// 회원가입용 매개변수 있는 생성자 작성
	
	public User(String userId, String userPwd, String userName, String userEmail, String userGrade, String userQuestion,
			String userAnswer) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userGrade = userGrade;
		this.userQuestion = userQuestion;
		this.userAnswer = userAnswer;
	}
	
	 /** 로그인 입력
	    * @param userId
	    * @param userPwd
	    */
	   public User(String userId, String userPwd) {
		   super();
		   this.userId = userId;
		   this.userPwd = userPwd;
	   }


	   /** 로그인 결과
	 * @param userName
	 * @param userEmail
	 * @param userGrade
	 * @param userQuestion
	 * @param userAnswer
	 * @param userAuth
	 */
	public User(String userName, String userEmail, String userGrade, String userQuestion, String userAnswer,
			String userAuth) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userGrade = userGrade;
		this.userQuestion = userQuestion;
		this.userAnswer = userAnswer;
		this.userAuth = userAuth;
	}

	// (3) getter와 setter 작성
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserQuestion() {
		return userQuestion;
	}

	public void setUserQuestion(String userQuestion) {
		this.userQuestion = userQuestion;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}

	// (4) toString 메소드 오버라이딩
	
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", userEmail=" + userEmail + ", userQuestion=" + userQuestion + ", userAnswer=" + userAnswer
				+ ", userGrade=" + userGrade + ", userStatus=" + userStatus + ", userAuth=" + userAuth + "]";
	}
	
}
