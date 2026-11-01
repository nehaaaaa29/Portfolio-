package com.example.Portfolio.service;
import com.example.Portfolio.entity.VisitorLog;
import com.example.Portfolio.repository.VisitorLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class VisitorService {

    @Autowired
    private VisitorLogRepository visitorLogRepository;

    private boolean isBot(String userAgent) {
        if (userAgent == null) return true;
        String ua = userAgent.toLowerCase();
        return ua.contains("bot") ||
                ua.contains("crawler") ||
                ua.contains("spider") ||
                ua.contains("googlebot") ||
                ua.contains("bingbot") ||
                ua.contains("slurp") ||
                ua.contains("duckduckbot") ||
                ua.contains("baiduspider") ||
                ua.contains("yandexbot") ||
                ua.contains("facebookexternalhit") ||
                ua.contains("semrush") ||
                ua.contains("ahrefsbot") ||
                ua.contains("mj12bot") ||
                ua.contains("dotbot") ||
                ua.contains("petalbot") ||
                ua.contains("headlesschrome") ||
                ua.contains("python-requests") ||
                ua.contains("curl") ||
                ua.contains("wget") ||
                ua.contains("scrapy");
    }

    public void trackVisit(HttpServletRequest request, String page,
                           String referrer, String screenResolution) {
        String ip = getClientIp(request);
        if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) return;

        String userAgent = request.getHeader("User-Agent");
        if (isBot(userAgent)) return;
        VisitorLog log = VisitorLog.builder()
                .ipAddress(ip)
                .userAgent(userAgent)
                .page(page)
                .deviceType(detectDevice(userAgent))
                .operatingSystem(detectOS(userAgent))
                .browser(detectBrowser(userAgent))
                .referrer(referrer != null ? referrer : "Direct")
                .screenResolution(screenResolution)
                .build();

        visitorLogRepository.save(log);
    }

    private String detectDevice(String ua) {
        if (ua == null) return "Unknown";
        if (ua.contains("Mobile") || ua.contains("Android") && ua.contains("K)"))
            return "Mobile";
        if (ua.contains("Tablet") || ua.contains("iPad"))
            return "Tablet";
        return "Desktop";
    }

    private String detectOS(String ua) {
        if (ua == null) return "Unknown";
        if (ua.contains("Windows NT 10")) return "Windows 10/11";
        if (ua.contains("Windows NT 6")) return "Windows 7/8";
        if (ua.contains("Windows"))       return "Windows";
        if (ua.contains("Android"))       return "Android";
        if (ua.contains("iPhone"))        return "iOS (iPhone)";
        if (ua.contains("iPad"))          return "iOS (iPad)";
        if (ua.contains("Mac OS X"))      return "macOS";
        if (ua.contains("Linux"))         return "Linux";
        return "Unknown";
    }

    private String detectBrowser(String ua) {
        if (ua == null) return "Unknown";
        if (ua.contains("Edg/"))     return "Edge";
        if (ua.contains("OPR/"))     return "Opera";
        if (ua.contains("Chrome"))   return "Chrome";
        if (ua.contains("Firefox"))  return "Firefox";
        if (ua.contains("Safari"))   return "Safari";
        return "Other";
    }

    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        LocalDateTime today     = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime thisWeek  = LocalDateTime.now().minusDays(7);
        LocalDateTime thisMonth = LocalDateTime.now().minusDays(30);

        stats.put("totalVisits",     visitorLogRepository.count());
        stats.put("uniqueVisitors",  visitorLogRepository.countUniqueVisitors());
        stats.put("visitsToday",     visitorLogRepository.countByVisitedAtAfter(today));
        stats.put("visitsThisWeek",  visitorLogRepository.countByVisitedAtAfter(thisWeek));
        stats.put("visitsThisMonth", visitorLogRepository.countByVisitedAtAfter(thisMonth));
        stats.put("uniqueThisMonth", visitorLogRepository.countUniqueVisitorsAfter(thisMonth));
        stats.put("recentVisits",    visitorLogRepository.findTop20ByOrderByVisitedAtDesc());
        return stats;
    }

    private String getClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isEmpty()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}

