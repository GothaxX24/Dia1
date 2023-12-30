import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day4 {


    public static void main(String[] args) {
        Path path = Path.of("files/day4.txt");
        String line;
        List<List<Integer>> totalWCards = new ArrayList<>();
        List<List<Integer>> totalCards = new ArrayList<>();
        List<Integer> wCards;
        List<Integer> cards;
        List<Integer> match = new ArrayList<>();
        String[] split;
        int j= 0;
        int matchCount = 1;
        int total_count = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {
            while ((line = br.readLine()) != null) {
                split = line.split(":");
                split = split[1].split("\\|");

                cards = parseCard(split[0]);
                wCards = parseCard(split[1]);
                totalWCards.add(wCards);
                totalCards.add(cards);
                match.add(matchCount);
                matchCount++;

            }
            List<List<Integer>> copies = new ArrayList<>(totalCards);
            List<List<Integer>> wCopies = new ArrayList<>(totalWCards);
            List<Integer> copieM = new ArrayList<>(match);
            System.out.println("PRE: " +copies);

            while(j < copies.size()) {
                int game = findPosition(copies.get(j), totalCards);
                int commonCount = countingCards(copies.get(j), wCopies.get(j));
                if (game == -1) {
                    commonCount = 0;
                }
                 for (int x = 1; x <= commonCount; x++) {
                     List<Integer> newCopy = new ArrayList<>(totalCards.get(game + x));
                     List<Integer> newWCopy = new ArrayList<>(totalWCards.get(game + x));

                     copies.add(j +1,newCopy);
                     wCopies.add(j + 1,newWCopy);
                     copieM.add(game + x + 1);
                 }
                j++;
            }
            System.out.println(copieM.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int findPosition(List<Integer> integers, List<List<Integer>> totalCards) {
        for (int i = 0; i < totalCards.size(); i++) {
            if (totalCards.get(i).equals(integers)) {
                return i;
            }
        }
        return -1;
    }

    private static List<Integer> parseCard(String input) {
        String[] numbers = input.split("\\s+");
        List<Integer> card = new ArrayList<>();
        for (String number : numbers) {
            if (!number.isEmpty()) {
                card.add(Integer.valueOf(number));
            }
        }
        return card;
    }

    private static int countingCards(List<Integer> cards, List<Integer> wCards) {
        List<Integer> commonCards;
        commonCards = cards;
        commonCards.retainAll(wCards);
        return commonCards.size();
    }
}
