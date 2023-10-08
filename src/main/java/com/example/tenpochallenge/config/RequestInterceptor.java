package com.example.tenpochallenge.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {
	
//	 private final ConcurrentHashMap<String, Long> requestTimestamps = new ConcurrentHashMap<>();
//	    private final int maxRequests = 3;
//	    private final long rateLimitWindow = TimeUnit.MINUTES.toMillis(1);
//
//	    @Override
//	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//	        String ipAddress = request.getRemoteAddr();
//	        long now = System.currentTimeMillis();
//
//	        if (requestTimestamps.containsKey(ipAddress)) {
//	            long lastRequestTime = requestTimestamps.get(ipAddress);
//	            if (now - lastRequestTime < rateLimitWindow) {
//	                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//	                response.getWriter().write("Too many requests");
//	                response.getWriter().flush();
//	                return false;
//	            }
//	        }
//
//	        requestTimestamps.put(ipAddress, now);
//	        return true;
//	    }
//	
//	private final Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
//    private final int maxRequestsPerMinute = 3;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String key = request.getRequestURI();
//        
//        AtomicInteger counter = requestCounts.computeIfAbsent(key, k -> new AtomicInteger(0));
//        int currentCount = counter.incrementAndGet();
//        
//        if (currentCount > maxRequestsPerMinute) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("Too many requests");
//            response.getWriter().flush();
//            return false;
//        }
//        
//        return true;
//    }
    
    private final ConcurrentHashMap<String, AtomicLong> requestCounts = new ConcurrentHashMap<>();
    private final int maxRequests = 3; // Límite de 3 solicitudes por minuto
    private final long timeWindowMillis = 60000; // Ventana de tiempo en milisegundos (1 minuto)

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = request.getRequestURI();

        AtomicLong requestCount = requestCounts.computeIfAbsent(key, k -> new AtomicLong(0));
        long currentTimeMillis = System.currentTimeMillis();

        // Elimina registros antiguos
        requestCounts.entrySet().removeIf(entry -> currentTimeMillis - entry.getValue().get() > timeWindowMillis);

        if (requestCount.incrementAndGet() > maxRequests) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Too many requests");
            response.getWriter().flush();
            return false;
        }

        return true;
    }

}
