package cn.appinfo.entity;

import java.util.Date;

public class DataDictionary {
	private Integer id;//涓婚敭id
	private String typeCode;//绫诲瀷缂栫爜
	private String typeName;//绫诲瀷鍚嶇О
	private Integer valueId;//绫诲瀷鍊糏D
	private String valueName;//绫诲瀷鍊糿ame
	private Integer createdBy;//鍒涘缓鑰�
	private Date creationDate;//鍒涘缓鏃堕棿
	private Integer modifyBy;//鏇存柊鑰�
	private Date modifyDate;//鏇存柊鏃堕棿
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getValueId() {
		return valueId;
	}
	public void setValueId(Integer valueId) {
		this.valueId = valueId;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
