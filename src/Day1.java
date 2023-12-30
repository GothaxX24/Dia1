import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Day1 {

    public static void main(String[] args) {
        int total_sum = 0;

        ArrayList<String> data = readFile();

        if (data != null) {
            for (String line : data) {
                int line_sum = calibrationValues(line);
                total_sum += line_sum;
                                                                                        }
            System.out.println("Total Sum: " + total_sum);
        }
    }

    private static int calibrationValues(String line) {
        int first_digit = -1;
        int last_digit = -1;
        StringBuilder word =  new StringBuilder();



        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            if (Character.isDigit(currentChar)) {
                if (first_digit == -1) {
                    first_digit = Character.getNumericValue(currentChar);
                }
                last_digit = Character.getNumericValue(currentChar);
                word = new StringBuilder();
            } else {
                word.append(currentChar);

                int value = checkString(word.toString());
                if (value != -1) {
                    if (first_digit == -1) {
                        first_digit = value;
                    }
                    last_digit = value;
                    word = new StringBuilder();
                    word.append(currentChar);
                }
            }

        }

        first_digit *= 10;
        return first_digit + last_digit;
    }

    private static int checkString(String word) {
        if (word.contains("one")) {
            return 1;
        } else if (word.contains("two")) {
            return 2;
        } else if (word.contains("three")) {
            return 3;
        } else if (word.contains("four")) {
            return 4;
        } else if (word.contains("five")) {
            return 5;
        } else if (word.contains("six")) {
            return 6;
        } else if (word.contains("seven")) {
            return 7;
        } else if (word.contains("eight")) {
            return 8;
        } else if (word.contains("nine")) {
            return 9;
        }
        return -1;
    }


    public static ArrayList<String> readFile() {
        Path path = Path.of("files/day1.txt");
        ArrayList<String> data;

        try {
            data = (ArrayList<String>) Files.readAllLines(path);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
