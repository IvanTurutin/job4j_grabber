package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.function.Predicate;

public class Test {
    public static void main(String[] args) {
        MemStore store = new MemStore();

        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100000.123456);
        Employee worker2 = new Employee("Vasiliy", now, now, 200);
        Employee worker3 = new Employee("Nikolay", now, now, 150);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);

        /*Comparator<Employee> comparator = (o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary());*/
        Comparator<Employee> comparator = (o1, o2) -> 0;
        Predicate<Employee> predicate = em -> true;


        Report engine = new ReportEngineProgr(store);
        System.out.println(engine.generate(predicate));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));

        DecimalFormat df = new DecimalFormat("###,###.##");
        Report engine2 = new ReportEngineBuhg(store, df);
        System.out.println(engine2.generate(predicate));

        Report engineXML = new ReportEngineXML(store);
        System.out.println(engineXML.generate(em -> true));

        Report engineJSON = new ReportEngineJSON(store);
        System.out.println(engineJSON.generate(em -> true));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String date = dateFormat.format(now.getTime());
        System.out.println(date);
        String pattern = "{\"name\":\"%s\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":%.1f}";
        System.out.format(pattern, worker1.getName(), date, date, worker1.getSalary());
    }
}
