package com.hs2j.User.vo;

import com.hs2j.comm.DTO;

public class UserVO extends DTO{
	private String userId         ;
	private String userPw         ;
	private String userEmail      ;
	private String userName       ;
	private String userAccount    ;
	private String userSpecialism ;
	private String userWork       ;
	private String userImgFile   ;
	private String userPNumber   ;
	
	
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPw=" + userPw + ", userEmail=" + userEmail + ", userName=" + userName
				+ ", userAccount=" + userAccount + ", userSpecialism=" + userSpecialism + ", userWork=" + userWork
				+ ", userImgFile=" + userImgFile + ", userPNumber=" + userPNumber + "]";
	}
	
	
	public UserVO(String userId ,String userPw,String userEmail, String userName, String userAccount,   String userSpecialism, String userWork,String userImgFile, String userPNumber ) {
			super();
			this.userId         = userId;
			this.userPw         = userPw;
			this.userEmail      =userEmail;
			this.userName       =userName;
			this.userAccount    =userAccount;
			this.userSpecialism =userSpecialism;
			this.userImgFile = userImgFile;
			this.userWork       =userWork;
			this.userPNumber = userPNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userAccount == null) ? 0 : userAccount.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userImgFile == null) ? 0 : userImgFile.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPNumber == null) ? 0 : userPNumber.hashCode());
		result = prime * result + ((userPw == null) ? 0 : userPw.hashCode());
		result = prime * result + ((userSpecialism == null) ? 0 : userSpecialism.hashCode());
		result = prime * result + ((userWork == null) ? 0 : userWork.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		if (userAccount == null) {
			if (other.userAccount != null)
				return false;
		} else if (!userAccount.equals(other.userAccount))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userImgFile == null) {
			if (other.userImgFile != null)
				return false;
		} else if (!userImgFile.equals(other.userImgFile))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPNumber == null) {
			if (other.userPNumber != null)
				return false;
		} else if (!userPNumber.equals(other.userPNumber))
			return false;
		if (userPw == null) {
			if (other.userPw != null)
				return false;
		} else if (!userPw.equals(other.userPw))
			return false;
		if (userSpecialism == null) {
			if (other.userSpecialism != null)
				return false;
		} else if (!userSpecialism.equals(other.userSpecialism))
			return false;
		if (userWork == null) {
			if (other.userWork != null)
				return false;
		} else if (!userWork.equals(other.userWork))
			return false;
		return true;
	}

	public UserVO() {}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPw() {
		return userPw;
	}


	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserAccount() {
		return userAccount;
	}


	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}


	public String getUserSpecialism() {
		return userSpecialism;
	}


	public void setUserSpecialism(String userSpecialism) {
		this.userSpecialism = userSpecialism;
	}


	public String getUserWork() {
		return userWork;
	}


	public void setUserWork(String userWork) {
		this.userWork = userWork;
	}


	public String getUserImgFile() {
		return userImgFile;
	}


	public void setUserImgFile(String userImgFile) {
		this.userImgFile = userImgFile;
	}


	public String getUserPNumber() {
		return userPNumber;
	}


	public void setUserPNumber(String userPNumber) {
		this.userPNumber = userPNumber;
	}

}
