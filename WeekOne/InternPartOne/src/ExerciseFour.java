import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class ExerciseFour {
    public static void main(String[] args) {
        Set<Point> setA = generatePoints(8000, new Point(800, 800), 400);
        Set<Point> setB = generatePoints(10000, new Point(4000, 800), 500);
        Set<Point> setC = generatePoints(12000, new Point(2400, 2400), 600);

        Set<Point> allPoints = new HashSet<>();
        allPoints.addAll(setA);
        allPoints.addAll(setB);
        allPoints.addAll(setC);

        writeToFile("src/output4.txt", allPoints);
    }

    private static Set<Point> generatePoints(int size, Point center, int maxDistance) {
        Set<Point> points = new HashSet<>();
        Random random = new Random();

        while (points.size() < size) {
            int x = center.x + random.nextInt(2 * maxDistance + 1) - maxDistance;
            int y = center.y + random.nextInt(2 * maxDistance + 1) - maxDistance;

            Point newPoint = new Point(x, y);

            if (!points.contains(newPoint)) {
                points.add(newPoint);
            }
        }

        return points;
    }

    private static void writeToFile(String fileName, Set<Point> points) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Point point : points) {
                writer.write(point.x + " " + point.y + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) return true;
//            if (obj == null || getClass() != obj.getClass()) return false;
//            Point point = (Point) obj;
//            return x == point.x && y == point.y;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(x, y);
//        }
    }
}
