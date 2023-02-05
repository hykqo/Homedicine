package home.medecine.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "PERSISTENT_LOGINS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersistentLogins {
    @Id
    private String series;
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime last_used;
}
