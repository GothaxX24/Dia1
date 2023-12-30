import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Day2 {

    public static void main(String[] args) {

        Path path = Path.of("files/day2.txt");
        int red, green, blue, total_sum = 0;
        String line;
        String[] data;
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {


            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] split = line.split(":");

                split = split[1].split(";");
                red = 0;
                green = 0;
                blue = 0;
                for (String s : split) {
                    data = s.split(",");
                    for (String d : data) {
                        d = d.replace(" ", "");

                        int number = Integer.parseInt(d.replaceAll("[^0-9]", ""));
                        String word = d.replaceAll("[^A-Za-z]", "");

                        switch (word) {
                            case "green"-> green = Math.max(number, green);
                            case "blue"->blue = Math.max(number, blue);
                            case "red"->red = Math.max(number, red);
                        }

                    }

                }
                    total_sum += (green * blue * red);

            }

            System.out.println(total_sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
