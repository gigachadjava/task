import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Main <circle_file> <points_file>");
            return;
        }

        String circleFile = args[0];
        String pointsFile = args[1];

        try {
            BufferedReader circleReader = new BufferedReader(new FileReader(circleFile));
            BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFile));

            // Считываем координаты центра окружности и радиус
            String[] circleCoords = circleReader.readLine().split(" ");
            double circleCenterX = Double.parseDouble(circleCoords[0]);
            double circleCenterY = Double.parseDouble(circleCoords[1]);
            double radius = Double.parseDouble(circleReader.readLine());

            String line;
            while ((line = pointsReader.readLine()) != null) {
                // Считываем координаты точки
                String[] pointCoords = line.split(" ");
                double pointX = Double.parseDouble(pointCoords[0]);
                double pointY = Double.parseDouble(pointCoords[1]);

                // Вычисляем расстояние от точки до центра окружности
                double distance = Math.sqrt(Math.pow(pointX - circleCenterX, 2) + Math.pow(pointY - circleCenterY, 2));

                // Проверяем положение точки относительно окружности
                if (distance < radius) {
                    System.out.println("1"); // Точка внутри
                } else if (distance == radius) {
                    System.out.println("0"); // Точка на окружности
                } else {
                    System.out.println("2"); // Точка снаружи
                }
            }

            circleReader.close();
            pointsReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
