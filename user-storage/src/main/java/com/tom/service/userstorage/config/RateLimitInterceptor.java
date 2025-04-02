package com.tom.service.userstorage.config;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

	private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket getBucket(String userId) {
        Bandwidth limit = Bandwidth.builder()
                .capacity(10)
                .refillIntervally(10, Duration.ofMinutes(1))
                .build();
        
        return buckets.computeIfAbsent(userId, key -> Bucket.builder().addLimit(limit).build());
    }

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String userId = request.getUserPrincipal().getName(); // This is for user
		Bucket bucket = getBucket(userId);

		if (bucket.tryConsume(1)) {
			return true;
		} else {
			response.setStatus(429);
			response.setContentType("application/json");
			return false;
		}
	}
}
