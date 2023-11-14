package GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JPanel;
import java.util.Map;

public class Histogram extends JPanel {

    public Histogram(Map<String, Integer> sortedHashReservasPorYear) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : sortedHashReservasPorYear.entrySet()) {
            dataset.addValue(entry.getValue(), "Reservas", entry.getKey());
        }

        JFreeChart histogram = ChartFactory.createBarChart(
                "Histograma de Reservas",
                "Fecha",
                "Numero de Reservas",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(histogram);
        add(chartPanel);
    }
}