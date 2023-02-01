package home.medecine.serviceImpl.member;

import home.medecine.config.error.exception.BusinessException;
import home.medecine.config.error.exception.EntityNotFoundException;
import home.medecine.config.error.exception.ErrorCode;

public class MemberException {

    public static class EmailDuplicateException extends BusinessException{
        public EmailDuplicateException(String message) {
            super(message, ErrorCode.EMAIL_DUPLICATION);
        }
    }

    public static class PhoneDuplicateException extends BusinessException{
        public PhoneDuplicateException(String message) {
            super(message, ErrorCode.PHONE_DUPLICATION);
        }
    }

    public static class IdDuplicateException extends BusinessException{
        public IdDuplicateException(String message) {
            super(message, ErrorCode.ID_DUPLICATION);
        }
    }

    public static class MemberNotFoundException extends EntityNotFoundException{
        public MemberNotFoundException(Long id) {
            super(id+" is Not Found");
        }
    }



}
