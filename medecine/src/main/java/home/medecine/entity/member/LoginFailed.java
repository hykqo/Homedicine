package home.medecine.entity.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginFailed {

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private int failedAttempt; //실패횟수

    private LocalDateTime lockTime; //잠겼던 시간.

    @Enumerated(EnumType.STRING)
    @Column(name = "FAILED_STATUS", columnDefinition = "DEFAULT UN_LOCK")
    private LoginFailedStatus status; //상태

    public boolean is_locked(){
        if(this.status.equals(LoginFailedStatus.LOCK)) return true;
        else return false;
    }

    public int getFailedAttempt() {return failedAttempt;}
    public LocalDateTime getLockTime() {return lockTime;}
    public LoginFailedStatus getStatus() {return status;}

    public static LoginFailed AddCount(int failedAttempt){ return  new LoginFailed(failedAttempt+1,null, LoginFailedStatus.UN_LOCK);}
    public static LoginFailed ChangeToLock(int maxAttempt, int lockHour){return  new LoginFailed(maxAttempt,LocalDateTime.now().plusHours(lockHour), LoginFailedStatus.LOCK);}
    public static LoginFailed ChangeToUnLock(){return new LoginFailed(0, null, LoginFailedStatus.UN_LOCK);}

}
