package com.tom.service.storage.exception;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
