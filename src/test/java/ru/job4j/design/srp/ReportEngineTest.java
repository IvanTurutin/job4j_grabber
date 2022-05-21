package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Comparator;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForHR() {
        MemStore store = new MemStore();

        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vasiliy", now, now, 200);
        Employee worker3 = new Employee("Nikolay", now, now, 150);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);

        Comparator<Employee> comparator = (o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary());

        Report engine = new ReportEngineHR(store, comparator);

        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForBuhg() {
        MemStore store = new MemStore();

        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100000.1234);
        store.add(worker);

        DecimalFormat df = new DecimalFormat("###,###.##");

        Report engine = new ReportEngineBuhg(store, df);

        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(df.format(worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForProgr() {
        MemStore store = new MemStore();

        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100000.1234);
        store.add(worker);

        Report engine = new ReportEngineProgr(store);

        var ls = System.lineSeparator();

        StringBuilder expect = new StringBuilder()
                .append("<html>").append(ls)
                .append("<body>").append(ls)
                .append("<p>").append("Name; Hired; Fired; Salary;").append("</p>").append(ls)
                .append("<p>")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append("</p>").append(ls)
                .append("</body>").append(ls)
                .append("</html>").append(ls);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}
