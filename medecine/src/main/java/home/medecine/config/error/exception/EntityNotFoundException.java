package home.medecine.config.error.exception;

/**
 * 각 엔티티들을 못찾았을 경우.
 * ex) findById, findByCode 메서드에서 조회가 안되었을 경우
 */
public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}
