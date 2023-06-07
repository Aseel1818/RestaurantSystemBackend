package com.RestaurantSystemDB.RestaurantSystemDB.Audit;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Temporal;
import static jakarta.persistence.TemporalType.TIMESTAMP;
import java.util.Date;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public abstract class Auditable<U> {



    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "created_Date" , updatable = false)
    protected Date createdDate;

    @LastModifiedBy
    @Column(name = "LastModified_By")
    protected U lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LastModified_Date")
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public U getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(U lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}

