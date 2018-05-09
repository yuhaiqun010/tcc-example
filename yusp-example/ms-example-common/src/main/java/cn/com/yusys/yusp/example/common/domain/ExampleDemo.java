package cn.com.yusys.yusp.example.common.domain;

import cn.com.yusys.yusp.commons.mapper.annotation.Generated;
import cn.com.yusys.yusp.commons.mapper.domain.BaseDomain;
import java.io.Serializable;
import javax.persistence.*;
import cn.com.yusys.yusp.commons.mapper.annotation.GenerationType;
@Table(name = "EXAMPLE_DEMO")
public class ExampleDemo extends BaseDomain implements Serializable {
    @Id
    @Generated(GenerationType.UUID)// 主键自动生成
    @Column(name = "LOGIC_SEQ")
    private String logicSeq;

    @Column(name = "CURRENT_ACTION")
    private String currentAction;

    @Column(name = "CUST_ID")
    private String custId;

    @Column(name = "CUST_NAME")
    private String custName;

    private static final long serialVersionUID = 1L;

    /**
     * @return LOGIC_SEQ
     */
    public String getLogicSeq() {
        return logicSeq;
    }

    /**
     * @param logicSeq
     */
    public void setLogicSeq(String logicSeq) {
        this.logicSeq = logicSeq == null ? null : logicSeq.trim();
    }

    /**
     * @return CURRENT_ACTION
     */
    public String getCurrentAction() {
        return currentAction;
    }

    /**
     * @param currentAction
     */
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction == null ? null : currentAction.trim();
    }

    /**
     * @return CUST_ID
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     */
    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    /**
     * @return CUST_NAME
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName
     */
    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
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
        ExampleDemo other = (ExampleDemo) that;
        return (this.getLogicSeq() == null ? other.getLogicSeq() == null : this.getLogicSeq().equals(other.getLogicSeq()))
            && (this.getCurrentAction() == null ? other.getCurrentAction() == null : this.getCurrentAction().equals(other.getCurrentAction()))
            && (this.getCustId() == null ? other.getCustId() == null : this.getCustId().equals(other.getCustId()))
            && (this.getCustName() == null ? other.getCustName() == null : this.getCustName().equals(other.getCustName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogicSeq() == null) ? 0 : getLogicSeq().hashCode());
        result = prime * result + ((getCurrentAction() == null) ? 0 : getCurrentAction().hashCode());
        result = prime * result + ((getCustId() == null) ? 0 : getCustId().hashCode());
        result = prime * result + ((getCustName() == null) ? 0 : getCustName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logicSeq=").append(logicSeq);
        sb.append(", currentAction=").append(currentAction);
        sb.append(", custId=").append(custId);
        sb.append(", custName=").append(custName);
        sb.append("]");
        return sb.toString();
    }
}