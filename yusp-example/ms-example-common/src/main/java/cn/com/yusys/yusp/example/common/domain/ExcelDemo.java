package cn.com.yusys.yusp.example.common.domain;

import cn.com.yusys.yusp.commons.excel.annotation.ExcelColumn;
import cn.com.yusys.yusp.commons.excel.annotation.ExcelColumnDateformat;
import cn.com.yusys.yusp.commons.excel.annotation.ExcelColumnDictCode;
import cn.com.yusys.yusp.commons.excel.annotation.ExcelColumnPricision;
import cn.com.yusys.yusp.commons.excel.annotation.ExcelName;
import cn.com.yusys.yusp.commons.mapper.annotation.Generated;
import cn.com.yusys.yusp.commons.mapper.annotation.GenerationType;
import cn.com.yusys.yusp.commons.mapper.domain.BaseDomain;
import java.io.Serializable;

import javax.persistence.*;

@Table(name = "EXCEL_DEMO")
@ExcelName(value="ExcelDemo导出", sheet="工作表")
public class ExcelDemo extends BaseDomain implements Serializable {
    @Id
    @Column(name = "BOOK_ID")
    @Generated(GenerationType.UUID)
    @ExcelColumn(title="ID", index=0)
    private String bookId;

    @Column(name = "TITLE")
    @ExcelColumn(title="标题", index=1)
    private String title;

    @Column(name = "AUTHOR")
    @ExcelColumnDictCode(value="SEX_TYPE")
    @ExcelColumn(title="作者", index=2)
    private String author;

    @Column(name = "PUBLISH_TIME")
    @ExcelColumnDateformat("yyyy-MM-dd")
    @ExcelColumn(title="日期", index=3)
    private String publishTime;
    
//    @Column(name = "PUBLISH_TIME")
//    @ExcelColumnDateformat("yyyy-mm-dd")
//    @ExcelColumn(title="日期", index=5)
//    private String publishTimePre;

    @Column(name = "PRICE")
    @ExcelColumnPricision(2)
    @ExcelColumn(title="售价", index=4)
    private String price;

    private static final long serialVersionUID = 1L;

    /**
     * @return BOOK_ID
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * @param bookId
     */
    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    /**
     * @return TITLE
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return AUTHOR
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * @return PUBLISH_TIME
     */
    public String getPublishTime() {
        return publishTime;
    }

    /**
     * @param publishTime
     */
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * @return PRICE
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
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
        ExcelDemo other = (ExcelDemo) that;
        return (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getPublishTime() == null ? other.getPublishTime() == null : this.getPublishTime().equals(other.getPublishTime()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getPublishTime() == null) ? 0 : getPublishTime().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookId=").append(bookId);
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", price=").append(price);
        sb.append("]");
        return sb.toString();
    }
}