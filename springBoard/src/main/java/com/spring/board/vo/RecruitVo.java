package com.spring.board.vo;

import java.util.ArrayList;
import java.util.List;

public class RecruitVo  {
	
    private Long   seq;
    private String name;
    private String birth;
    private String gender;
    private String phone;
    private String email;
    private String address;  
    private String hopeArea;
    private String jobType;
    
    private List<EducationVo> educationList = new ArrayList<>();
    private List<CereerVo> careerList = new ArrayList<>();
    private List<CertificateVo> certificateList = new ArrayList<>();
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHopeArea() {
		return hopeArea;
	}
	public void setHopeArea(String hopeArea) {
		this.hopeArea = hopeArea;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public List<EducationVo> getEducationList() {
		return educationList;
	}
	public void setEducationList(List<EducationVo> educationList) {
		this.educationList = educationList;
	}
	public List<CereerVo> getCareerList() {
		return careerList;
	}
	public void setCareerList(List<CereerVo> careerList) {
		this.careerList = careerList;
	}
	public List<CertificateVo> getCertificateList() {
		return certificateList;
	}
	public void setCertificateList(List<CertificateVo> certificateList) {
		this.certificateList = certificateList;
	}
    
	

}
