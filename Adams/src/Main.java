import java.util.ArrayList;
import java.util.List;

public class Main {

    static double f(double x, double y) {
        // y' = x^2 - y
        return x * x - y;
    }

    static double exactSolution(double x) {
        return x*x - 2*x + 2 - Math.exp(-x);
    }

    static List<double[]> solveAdamsMoulton1(double x0, double y0, double xMax, double h) {
        List<double[]> result = new ArrayList<>();
        double x = x0;
        double y = y0;
        result.add(new double[]{x, y});
        int steps = (int)Math.round((xMax - x0) / h);

        for (int i = 0; i < steps; i++) {
            double xNext = x + h;
            double numerator = y + (h / 2.0) * (x*x + xNext*xNext - y);
            double denominator = 1.0 + (h / 2.0);
            double yNext = numerator / denominator;
            result.add(new double[]{xNext, yNext});
            x = xNext;
            y = yNext;
        }
        return result;
    }

    public static void main(String[] args) {
        double x0 = 0.0;
        double y0 = 1.0;
        double xMax = 2.0;
        double h = 0.1;

        List<double[]> solution = solveAdamsMoulton1(x0, y0, xMax, h);

        System.out.println(" x        y (численное)    y (точное)     |ошибка|");
        for (double[] point : solution) {
            double xVal = point[0];
            double yVal = point[1];
            double yExact = exactSolution(xVal);
            double error = Math.abs(yVal - yExact);
            System.out.printf("%6.3f   %12.6f   %12.6f   %10.3e\n", xVal, yVal, yExact, error);
        }
    }
}
