package com.myorganisation.traceboard.cron;

import com.myorganisation.traceboard.controller.ServerController;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "${my.cron.expression}") // Every hour
    public void logSystemMetrics() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        long totalMemory = osBean.getTotalMemorySize() / (1024 * 1024); // in MB
        long freeMemory = osBean.getFreeMemorySize() / (1024 * 1024);   // in MB
        double cpuLoad = osBean.getSystemCpuLoad() * 100; // percentage

        String timestamp = LocalDateTime.now().format(formatter);

        System.out.printf("🕒 [%s] 📊 System Stats: RAM Used = %dMB / %dMB | CPU Load = %.2f%%\n",
                timestamp,
                (totalMemory - freeMemory),
                totalMemory,
                cpuLoad);
    }

    @Scheduled(cron = "0 * * * * ?")
    public void checkServerStatus() {
        ServerController serverController = new ServerController("${spring.application.name}");
        System.out.println("Server status: " + serverController.checkServerHealth().getStatusCode());
    }
}
