package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import com.google.gson.GsonBuilder;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Locale;

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

    @Test
    public void whenGeneratedJson() {
        MemStore store = new MemStore();

        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String date = dateFormat.format(now.getTime());
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vasiliy", now, now, 200);
        store.add(worker1);
        store.add(worker2);

        Report engine = new ReportEngineJSON(store);

        String pattern = "{\"name\":\"%s\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":%.1f}";
        StringBuilder expect = new StringBuilder()
                .append("[")
                .append(String.format(Locale.US, pattern, worker1.getName(), date, date, worker1.getSalary()))
                .append(",")
                .append(String.format(Locale.US, pattern, worker2.getName(), date, date, worker2.getSalary()))
                .append("]");

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedXML() {
        MemStore store = new MemStore();

        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String date = dateFormat.format(now.getTime());
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vasiliy", now, now, 200);
        store.add(worker1);
        store.add(worker2);

        Report engine = new ReportEngineXML(store);

        String head = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
        String pattern = "<employees name=\"%s\" hired=\"%s\" fired=\"%s\" salary=\"%.1f\"/>";
        StringBuilder expect = new StringBuilder()
                .append(head)
                .append("<employees>")
                .append(String.format(Locale.US, pattern, worker1.getName(), date, date, worker1.getSalary()))
                .append(String.format(Locale.US, pattern, worker2.getName(), date, date, worker2.getSalary()))
                .append("</employees>");

        String result = engine.generate(em -> true).replaceAll("\\n\\s*", "");
        assertThat(result, is(expect.toString()));
    }

}
