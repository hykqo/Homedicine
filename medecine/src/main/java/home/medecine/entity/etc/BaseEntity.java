package home.medecine.entity.etc;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private String createdBy;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    private String insertedBy;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime inserteDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getInsertedBy() {
        return insertedBy;
    }

    public LocalDateTime getInserteDate() {
        return inserteDate;
    }
}
