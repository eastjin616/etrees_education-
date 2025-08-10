package com.spring.board.vo;

import java.util.List;

public class CertificateVo  {
	
	private Long certSeq;       
    private Long seq;          
    private String qualifiName;   
    private String acquDate;      
    private String organizeName;  
    
    
	public Long getCertSeq() {
		return certSeq;
	}
	public void setCertSeq(Long certSeq) {
		this.certSeq = certSeq;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getQualifiName() {
		return qualifiName;
	}
	public void setQualifiName(String qualifiName) {
		this.qualifiName = qualifiName;
	}
	public String getAcquDate() {
		return acquDate;
	}
	public void setAcquDate(String acquDate) {
		this.acquDate = acquDate;
	}
	public String getOrganizeName() {
		return organizeName;
	}
	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}
    
    

}
