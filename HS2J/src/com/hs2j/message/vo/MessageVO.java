package com.hs2j.message.vo;

import com.hs2j.comm.DTO;

public class MessageVO extends DTO {
	
	private String seq        ;
	private String title      ;
	private String contents   ;
	private String sender     ;
	private String receiver   ;
	private String msgDate   ;
	private String noParent  ;
	private String showYn    ;
	private String read		  ;
	
	public MessageVO(){}

	public MessageVO(String title, String contents, String sender, String receiver, 
			String noParent) {
		super();
		this.title = title;
		this.contents = contents;
		this.sender = sender;
		this.receiver = receiver;
		this.noParent = noParent;
	}
	
	public MessageVO(String seq, String title, String contents, String sender, String receiver, String msgDate,
			String noParent, String showYn, String read) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.sender = sender;
		this.receiver = receiver;
		this.msgDate = msgDate;
		this.noParent = noParent;
		this.showYn = showYn;
		this.read = read;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}

	public String getNoParent() {
		return noParent;
	}

	public void setNoParent(String noParent) {
		this.noParent = noParent;
	}

	public String getShowYn() {
		return showYn;
	}

	public void setShowYn(String showYn) {
		this.showYn = showYn;
	}

	@Override
	public String toString() {
		return "MessageVO [seq=" + seq + ", title=" + title + ", contents=" + contents + ", sender=" + sender
				+ ", receiver=" + receiver + ", msgDate=" + msgDate + ", noParent=" + noParent + ", showYn="
				+ showYn + ", read=" + read + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((msgDate == null) ? 0 : msgDate.hashCode());
		result = prime * result + ((noParent == null) ? 0 : noParent.hashCode());
		result = prime * result + ((read == null) ? 0 : read.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		result = prime * result + ((showYn == null) ? 0 : showYn.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		MessageVO other = (MessageVO) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (msgDate == null) {
			if (other.msgDate != null)
				return false;
		} else if (!msgDate.equals(other.msgDate))
			return false;
		if (noParent == null) {
			if (other.noParent != null)
				return false;
		} else if (!noParent.equals(other.noParent))
			return false;
		if (read == null) {
			if (other.read != null)
				return false;
		} else if (!read.equals(other.read))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		if (showYn == null) {
			if (other.showYn != null)
				return false;
		} else if (!showYn.equals(other.showYn))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}	
}
