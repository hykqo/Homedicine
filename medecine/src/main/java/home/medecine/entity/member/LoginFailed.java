package home.medecine.entity.member;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LoginFailed {

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private int failedAttempt;

    @CreationTimestamp
    private int lockTime;

    @CreationTimestamp
    private LocalDateTime lastTryTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "FAILED_STATUS")
    private LoginFailedStatus status;


    public boolean is_locked(){
        if(this.status.equals(LoginFailedStatus.LOCK)) return true;
        else return false;
    }

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public int getLockTime() {
        return lockTime;
    }

    public LocalDateTime getLastTryTime() {
        return lastTryTime;
    }

    public LoginFailedStatus getStatus() {
        return status;
    }
}
