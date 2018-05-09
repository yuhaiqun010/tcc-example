package cn.com.yusys.yusp.example.common.domain;

import cn.com.yusys.yusp.commons.mapper.domain.BaseDomain;
import java.io.Serializable;
import javax.persistence.*;

@Table(name = "SEQUENCE_CONFIG")
public class SequenceConfig extends BaseDomain implements Serializable {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SEQ_NAME")
    private String seqName;

    @Column(name = "SEQ_ID")
    private String seqId;

    @Column(name = "STARTVALUE")
    private Short startvalue;

    @Column(name = "MAXIMUMVALUE")
    private Long maximumvalue;

    @Column(name = "INCREMENTVALUE")
    private Short incrementvalue;

    @Column(name = "IS_CYCLE")
    private String isCycle;

    @Column(name = "CACHEVALUE")
    private Short cachevalue;

    @Column(name = "SEQ_TEMPLET")
    private String seqTemplet;

    @Column(name = "SEQ_PLACE")
    private Short seqPlace;

    @Column(name = "ZERO_FILL")
    private String zeroFill;

    @Column(name = "SEQ_CREATE")
    private String seqCreate;

    @Column(name = "CURRENT_VALUE")
    private String currentValue;

    @Column(name = "LAST_CHG_DT")
    private String lastChgDt;

    @Column(name = "LAST_CHG_USR")
    private String lastChgUsr;

    private static final long serialVersionUID = 1L;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return SEQ_NAME
     */
    public String getSeqName() {
        return seqName;
    }

    /**
     * @param seqName
     */
    public void setSeqName(String seqName) {
        this.seqName = seqName == null ? null : seqName.trim();
    }

    /**
     * @return SEQ_ID
     */
    public String getSeqId() {
        return seqId;
    }

    /**
     * @param seqId
     */
    public void setSeqId(String seqId) {
        this.seqId = seqId == null ? null : seqId.trim();
    }

    /**
     * @return STARTVALUE
     */
    public Short getStartvalue() {
        return startvalue;
    }

    /**
     * @param startvalue
     */
    public void setStartvalue(Short startvalue) {
        this.startvalue = startvalue;
    }

    /**
     * @return MAXIMUMVALUE
     */
    public Long getMaximumvalue() {
        return maximumvalue;
    }

    /**
     * @param maximumvalue
     */
    public void setMaximumvalue(Long maximumvalue) {
        this.maximumvalue = maximumvalue;
    }

    /**
     * @return INCREMENTVALUE
     */
    public Short getIncrementvalue() {
        return incrementvalue;
    }

    /**
     * @param incrementvalue
     */
    public void setIncrementvalue(Short incrementvalue) {
        this.incrementvalue = incrementvalue;
    }

    /**
     * @return IS_CYCLE
     */
    public String getIsCycle() {
        return isCycle;
    }

    /**
     * @param isCycle
     */
    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle == null ? null : isCycle.trim();
    }

    /**
     * @return CACHEVALUE
     */
    public Short getCachevalue() {
        return cachevalue;
    }

    /**
     * @param cachevalue
     */
    public void setCachevalue(Short cachevalue) {
        this.cachevalue = cachevalue;
    }

    /**
     * @return SEQ_TEMPLET
     */
    public String getSeqTemplet() {
        return seqTemplet;
    }

    /**
     * @param seqTemplet
     */
    public void setSeqTemplet(String seqTemplet) {
        this.seqTemplet = seqTemplet == null ? null : seqTemplet.trim();
    }

    /**
     * @return SEQ_PLACE
     */
    public Short getSeqPlace() {
        return seqPlace;
    }

    /**
     * @param seqPlace
     */
    public void setSeqPlace(Short seqPlace) {
        this.seqPlace = seqPlace;
    }

    /**
     * @return ZERO_FILL
     */
    public String getZeroFill() {
        return zeroFill;
    }

    /**
     * @param zeroFill
     */
    public void setZeroFill(String zeroFill) {
        this.zeroFill = zeroFill == null ? null : zeroFill.trim();
    }

    /**
     * @return SEQ_CREATE
     */
    public String getSeqCreate() {
        return seqCreate;
    }

    /**
     * @param seqCreate
     */
    public void setSeqCreate(String seqCreate) {
        this.seqCreate = seqCreate == null ? null : seqCreate.trim();
    }

    /**
     * @return CURRENT_VALUE
     */
    public String getCurrentValue() {
        return currentValue;
    }

    /**
     * @param currentValue
     */
    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue == null ? null : currentValue.trim();
    }

    /**
     * @return LAST_CHG_DT
     */
    public String getLastChgDt() {
        return lastChgDt;
    }

    /**
     * @param lastChgDt
     */
    public void setLastChgDt(String lastChgDt) {
        this.lastChgDt = lastChgDt == null ? null : lastChgDt.trim();
    }

    /**
     * @return LAST_CHG_USR
     */
    public String getLastChgUsr() {
        return lastChgUsr;
    }

    /**
     * @param lastChgUsr
     */
    public void setLastChgUsr(String lastChgUsr) {
        this.lastChgUsr = lastChgUsr == null ? null : lastChgUsr.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SequenceConfig other = (SequenceConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSeqName() == null ? other.getSeqName() == null : this.getSeqName().equals(other.getSeqName()))
            && (this.getSeqId() == null ? other.getSeqId() == null : this.getSeqId().equals(other.getSeqId()))
            && (this.getStartvalue() == null ? other.getStartvalue() == null : this.getStartvalue().equals(other.getStartvalue()))
            && (this.getMaximumvalue() == null ? other.getMaximumvalue() == null : this.getMaximumvalue().equals(other.getMaximumvalue()))
            && (this.getIncrementvalue() == null ? other.getIncrementvalue() == null : this.getIncrementvalue().equals(other.getIncrementvalue()))
            && (this.getIsCycle() == null ? other.getIsCycle() == null : this.getIsCycle().equals(other.getIsCycle()))
            && (this.getCachevalue() == null ? other.getCachevalue() == null : this.getCachevalue().equals(other.getCachevalue()))
            && (this.getSeqTemplet() == null ? other.getSeqTemplet() == null : this.getSeqTemplet().equals(other.getSeqTemplet()))
            && (this.getSeqPlace() == null ? other.getSeqPlace() == null : this.getSeqPlace().equals(other.getSeqPlace()))
            && (this.getZeroFill() == null ? other.getZeroFill() == null : this.getZeroFill().equals(other.getZeroFill()))
            && (this.getSeqCreate() == null ? other.getSeqCreate() == null : this.getSeqCreate().equals(other.getSeqCreate()))
            && (this.getCurrentValue() == null ? other.getCurrentValue() == null : this.getCurrentValue().equals(other.getCurrentValue()))
            && (this.getLastChgDt() == null ? other.getLastChgDt() == null : this.getLastChgDt().equals(other.getLastChgDt()))
            && (this.getLastChgUsr() == null ? other.getLastChgUsr() == null : this.getLastChgUsr().equals(other.getLastChgUsr()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSeqName() == null) ? 0 : getSeqName().hashCode());
        result = prime * result + ((getSeqId() == null) ? 0 : getSeqId().hashCode());
        result = prime * result + ((getStartvalue() == null) ? 0 : getStartvalue().hashCode());
        result = prime * result + ((getMaximumvalue() == null) ? 0 : getMaximumvalue().hashCode());
        result = prime * result + ((getIncrementvalue() == null) ? 0 : getIncrementvalue().hashCode());
        result = prime * result + ((getIsCycle() == null) ? 0 : getIsCycle().hashCode());
        result = prime * result + ((getCachevalue() == null) ? 0 : getCachevalue().hashCode());
        result = prime * result + ((getSeqTemplet() == null) ? 0 : getSeqTemplet().hashCode());
        result = prime * result + ((getSeqPlace() == null) ? 0 : getSeqPlace().hashCode());
        result = prime * result + ((getZeroFill() == null) ? 0 : getZeroFill().hashCode());
        result = prime * result + ((getSeqCreate() == null) ? 0 : getSeqCreate().hashCode());
        result = prime * result + ((getCurrentValue() == null) ? 0 : getCurrentValue().hashCode());
        result = prime * result + ((getLastChgDt() == null) ? 0 : getLastChgDt().hashCode());
        result = prime * result + ((getLastChgUsr() == null) ? 0 : getLastChgUsr().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", seqName=").append(seqName);
        sb.append(", seqId=").append(seqId);
        sb.append(", startvalue=").append(startvalue);
        sb.append(", maximumvalue=").append(maximumvalue);
        sb.append(", incrementvalue=").append(incrementvalue);
        sb.append(", isCycle=").append(isCycle);
        sb.append(", cachevalue=").append(cachevalue);
        sb.append(", seqTemplet=").append(seqTemplet);
        sb.append(", seqPlace=").append(seqPlace);
        sb.append(", zeroFill=").append(zeroFill);
        sb.append(", seqCreate=").append(seqCreate);
        sb.append(", currentValue=").append(currentValue);
        sb.append(", lastChgDt=").append(lastChgDt);
        sb.append(", lastChgUsr=").append(lastChgUsr);
        sb.append("]");
        return sb.toString();
    }
}