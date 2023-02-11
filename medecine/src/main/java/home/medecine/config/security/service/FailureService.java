package home.medecine.config.security.service;

import home.medecine.config.error.ErrorResponse;

public interface FailureService {
    public ErrorResponse handleBadCredentials(String id);
}
