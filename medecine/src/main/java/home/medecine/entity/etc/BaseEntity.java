package home.medecine.entity.etc;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private String createdBy;

    @Column(nullable = false)
    private LocalDateTime createDate;

    private String insertedBy;

    @Column(nullable = false)
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
