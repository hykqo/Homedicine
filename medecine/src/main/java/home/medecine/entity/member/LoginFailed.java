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
@NoArgsConstructor
public class LoginFailed {

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private int failedAttempt;

    private LocalDateTime lockTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "FAILED_STATUS")
    private LoginFailedStatus status;

    public LoginFailed(int failedAttempt, LoginFailedStatus status, LocalDateTime lockTime) {
        this.failedAttempt = failedAttempt;
        this.status = status;
        this.lockTime = lockTime;
    }

    public boolean is_locked(){
        if(this.status.equals(LoginFailedStatus.LOCK)) return true;
        else return false;
    }

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public LocalDateTime getLockTime() {return lockTime;}

    public LoginFailedStatus getStatus() {
        return status;
    }
}
