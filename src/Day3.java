import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Day3 {

    public static void main(String[] args) {
        Path path = Path.of("files/day3.txt");

        boolean flag = false;
        int total_sum = 0;
        int num_aux = 0;
        String line;
        int i = 0, j;
        char[][] data = new char[140][];

        try(BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {
            while ((line = br.readLine()) != null) {
                data[i] = line.toCharArray();
                i++;
            }

            for( i = 0; i < data.length ; i++) {

                for ( j = 0; j < data[i].length; j++) {
                    if (Character.isDigit(data[i][j])) {
                        num_aux = num_aux * 10 + Character.getNumericValue(data[i][j]);
                        if (!flag) {
                            flag = checkAdjacents(data, i,j);
                        }

                    } else {
                        if (flag) {
                            total_sum += num_aux;
                        }
                        num_aux = 0;
                        flag = false;
                    }
                }
            }
            System.out.println(total_sum);
        }catch (IOException ignored) {

        }

    }
    private static boolean checkAdjacents(char[][] data, int row, int column) {
        int rows = data.length;
        int columns = data[0].length;

        //-1,0
        int newRow = row - 1;
        int newColumn = column;

        if (newRow >= 0 && data[newRow][newColumn] != '.' && !Character.isDigit(data[newRow][newColumn])) {
            return true;
        }
        //1,0
        newRow = row + 1;
        if (newRow < rows && data[newRow][newColumn] != '.' && !Character.isDigit(data[newRow][newColumn])) {
            return true;
        }
        //0,-1
        newRow = row;
        newColumn = column - 1;
        if (newColumn >= 0 && data[newRow][newColumn] != '.' && !Character.isDigit(data[newRow][newColumn])) {
            return true;
        }
        //0,1
        newColumn = column + 1;
        if (newColumn < columns && data[newRow][newColumn] != '.' && !Character.isDigit(data[newRow][newColumn])) {
            return true;
        }
        //-1,-1
        newRow = row - 1;
        newColumn = column - 1;
        if (newRow >= 0 && newColumn >= 0 && data[newRow][newColumn] != '.' && !Character.isDigit(data[newRow][newColumn])) {
            return true;
        }
        //-1,1
        newRow = row - 1;
        newColumn = column + 1;
        if (newRow >= 0 && newColumn < columns && data[newRow][newColumn] != '.' && !Character.isDigit(data[newRow][newColumn])) {
            return true;
        }
        //1,-1
        newRow = row + 1;
        newColumn = column - 1;
        if (newRow < rows && newColumn >= 0 && data[newRow][newColumn] != '.' && !Character.isDigit(data[newRow][newColumn])) {
            return true;
        }
        //1,1
        newRow = row + 1;
        newColumn = column + 1;
        if (newRow < rows && newColumn < columns && data[newRow][newColumn] != '.' && !Character.isDigit(data[newRow][newColumn])) {
            return true;
        }

        return false;
    }

}
