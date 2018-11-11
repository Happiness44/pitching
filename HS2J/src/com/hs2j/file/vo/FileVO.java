package com.hs2j.file.vo;

import com.hs2j.file.comm.FileDTO;

public class FileVO extends FileDTO{
	private String F_SEQ	 ;
	private String F_DTL_SEQ ;
	private String F_ORG_NM	 ;
	private String F_RE_NM	 ;
	private String F_URL	 ;
	private String F_EXT     ;
	private String F_USE	;
	
	public FileVO(){}
		@Override
	public String toString() {
		return "FileVO [F_SEQ=" + F_SEQ + ", F_DTL_SEQ=" + F_DTL_SEQ + ", F_ORG_NM=" + F_ORG_NM + ", F_RE_NM=" + F_RE_NM
				+ ", F_URL=" + F_URL + ", F_EXT=" + F_EXT +", F_USE="+F_USE+ "]";
	}
	
	public FileVO( String F_DTL_SEQ,String F_ORG_NM, String F_RE_NM,String F_URL,String F_EXT) {
		super();
		this.F_DTL_SEQ =F_DTL_SEQ;
		this.F_ORG_NM = F_ORG_NM;
		this.F_RE_NM =F_RE_NM;
		this.F_URL =F_URL;
		this.F_EXT =F_EXT;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((F_DTL_SEQ == null) ? 0 : F_DTL_SEQ.hashCode());
		result = prime * result + ((F_EXT == null) ? 0 : F_EXT.hashCode());
		result = prime * result + ((F_ORG_NM == null) ? 0 : F_ORG_NM.hashCode());
		result = prime * result + ((F_RE_NM == null) ? 0 : F_RE_NM.hashCode());
		result = prime * result + ((F_SEQ == null) ? 0 : F_SEQ.hashCode());
		result = prime * result + ((F_URL == null) ? 0 : F_URL.hashCode());
		result = prime * result + ((F_USE == null) ? 0 : F_USE.hashCode());
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
			FileVO other = (FileVO) obj;
			if (F_DTL_SEQ == null) {
				if (other.F_DTL_SEQ != null)
					return false;
			} else if (!F_DTL_SEQ.equals(other.F_DTL_SEQ))
				return false;
			if (F_EXT == null) {
				if (other.F_EXT != null)
					return false;
			} else if (!F_EXT.equals(other.F_EXT))
				return false;
			if (F_ORG_NM == null) {
				if (other.F_ORG_NM != null)
					return false;
			} else if (!F_ORG_NM.equals(other.F_ORG_NM))
				return false;
			if (F_RE_NM == null) {
				if (other.F_RE_NM != null)
					return false;
			} else if (!F_RE_NM.equals(other.F_RE_NM))
				return false;
			if (F_SEQ == null) {
				if (other.F_SEQ != null)
					return false;
			} else if (!F_SEQ.equals(other.F_SEQ))
				return false;
			if (F_URL == null) {
				if (other.F_URL != null)
					return false;
			} else if (!F_URL.equals(other.F_URL))
				return false;
			if (F_USE == null) {
				if (other.F_USE != null)
					return false;
			} else if (!F_USE.equals(other.F_USE))
				return false;
			return true;
		}

	public String getF_USE() {
			return F_USE;
		}

		public void setF_USE(String f_USE) {
			F_USE = f_USE;
		}

	public String getF_SEQ() {
		return F_SEQ;
	}
	public void setF_SEQ(String f_SEQ) {
		F_SEQ = f_SEQ;
	}
	public String getF_DTL_SEQ() {
		return F_DTL_SEQ;
	}
	public void setF_DTL_SEQ(String f_DTL_SEQ) {
		F_DTL_SEQ = f_DTL_SEQ;
	}
	public String getF_ORG_NM() {
		return F_ORG_NM;
	}
	public void setF_ORG_NM(String f_ORG_NM) {
		F_ORG_NM = f_ORG_NM;
	}
	public String getF_RE_NM() {
		return F_RE_NM;
	}
	public void setF_RE_NM(String f_RE_NM) {
		F_RE_NM = f_RE_NM;
	}
	public String getF_URL() {
		return F_URL;
	}
	public void setF_URL(String f_URL) {
		F_URL = f_URL;
	}
	public String getF_EXT() {
		return F_EXT;
	}
	public void setF_EXT(String f_EXT) {
		F_EXT = f_EXT;
	}	
}
