package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * create table rabbit (
 * 	id serial primary key,
 *     created_date timestamp
 * );
 */

public class AlertRabbit implements  AutoCloseable {
    private Connection cn;

    public static void main(String[] args) {
        AlertRabbit alertRabbit = new AlertRabbit();
        Properties prop = alertRabbit.init();
        int interval = Integer.parseInt(prop.getProperty("rabbit.interval"));
        try (Connection con = alertRabbit.getConnection(prop)) {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connection", con);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(interval)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public Properties init() {
        Properties config;
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            config = new Properties();
            config.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return config;
    }

    public Connection getConnection(Properties prop) throws ClassNotFoundException, SQLException {
        Class.forName(prop.getProperty("driver-class-name"));
        cn = DriverManager.getConnection(
                prop.getProperty("url"),
                prop.getProperty("username"),
                prop.getProperty("password")
        );
        return cn;
    }

    public static class Rabbit implements Job {
        private LocalDateTime create;
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

        public Rabbit() {
            System.out.println(hashCode());
            this.create = LocalDateTime.now().withNano(3);
        }

        @Override
        public void execute(JobExecutionContext context) {
            Connection cnct = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            var ls = System.lineSeparator();
            System.out.format("connection = %s%s", cnct.toString(), ls);
            String query = String.format("insert into %s (created_date) values (?)", "rabbit");
            try (PreparedStatement ps = cnct.prepareStatement(query)) {
                ps.setTimestamp(1, Timestamp.valueOf(create));
                if (ps.executeUpdate() > 0) {
                    System.out.format("insert into rabbit create = %s%s", create.format(FORMATTER), ls);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
