import java.util.*;

public class Evaluation {

    public static void main(String[] args){

        String input = "Bonjour";
        int result = longestProfit(new int[]{-1, 9, 0, 8, -5, 6, -24});
        System.out.println((result));

    }

    static int longestProfit(int[] data){
        int[] profits = new int[data.length];
        for (int i = 0; i <data.length ; i++) {
            profits[i] = 1;
        }

        int longProfit = 0;

        for (int i = 1; i <data.length ; i++) {
            for (int j = 0; j <i ; j++) {
                if(data[i] > data[j])
                    profits[i] = Math.max(profits[i], profits[j]+1);
            }

            longProfit = Math.max(longProfit, profits[i]);

        }

        return longProfit;
    }

    static boolean isTwin(String a, String b){
        String[] aArr = a.toLowerCase().split("");
        Arrays.sort(aArr);
        a = String.join("", aArr);

        String[] bArr = b.toLowerCase().split("");
        Arrays.sort(bArr);
        b = String.join("", bArr);

        return a.toLowerCase().equals(b.toLowerCase());
    }

    static int luckyMoney(int money, int giftees) {
        int diff = money / 8;
        if(diff >= giftees)
            return giftees;

        if(giftees-diff==1)
            return diff-1;

        return diff;


    }


        static String[] getTopStocks(String[] stocks, double[][] prices){
        Double[] averages = new Double[stocks.length];

        for (int j = 0; j < stocks.length; j++) {
            double sum = 0;
            for (int i = 0; i < prices.length; i++) {
                sum += prices[i][j];
            }

            averages[j] = (double)sum/stocks.length;
        }

        Map<String, Double> map = new HashMap<>();
        for (int i = 0; i <stocks.length ; i++) {
            map.put(stocks[i], averages[i]);
        }

        Arrays.sort(averages, Comparator.reverseOrder());

        List<String> result = new ArrayList<>();
        for (int i = 0; i< averages.length; i++) {
            for(Map.Entry<String, Double> m : map.entrySet()){
                if(m.getValue() == averages[i]){
                    result.add(m.getKey());
                }
            }
        }

        return result.stream().limit(3).toArray(String[]::new);
    }

    static boolean check(String str){
        if(str ==null)
            return true;

        Stack<Character> stack = new Stack<>();
        for(char c : str.toCharArray()){
            if(c == '(' || c == '[')
                stack.push(c);
            else if(c == ')' && !stack.isEmpty() && stack.peek() == '(')
                stack.pop();
            else if(c == ']' && !stack.isEmpty() && stack.peek() == '[')
                stack.pop();
            else
                return false;
        }

        return stack.isEmpty();
    }

    static String isDuoDigit(int number){

        String numberStr = String.valueOf(Math.abs(number));

        int countDigit = 0;
        for(int i= 0; i< numberStr.length(); i++){
            char digit = numberStr.charAt(i);
            if(numberStr.indexOf(digit) == i){
                countDigit++;
                if(countDigit > 2)
                    return "n";
            }

        }

        return "y";
    }


    static int[] countFrequencies(String[] words){
        Arrays.sort(words);

        List<Integer> result= new ArrayList<>();
        List<String> listCount = new ArrayList<>();
        for (int i = 0; i <words.length ; i++) {
            int count = 0;
            String searchWord = words[i];
            if(listCount.contains(words[i]))
                break;
            for (int j = 0; j < words.length; j++) {
                if(searchWord.equals(words[j])) {
                    count++;
                    listCount.add(words[i]);
                }
            }
            if(count>0)
                result.add(count);

        }

        return result.stream().mapToInt(a->a).toArray();
    }

    static int[] countFrequenciesMap(String[] words) {
        Map<String, Integer> map = new HashMap<>();

        for(String wd : words){
            if(map.containsKey(wd)) {
                map.put(wd, map.get(wd) + 1);
            }
            else
                map.put(wd, 1);

        }
        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        int[] result = new int[list.size()];
        int i=0;
        for(String st : list){
            result[i++] = map.get(st);
        }

        return result;
    }


    static String fizzBuzz(int number, Map<Integer, Integer> maps){

        StringBuilder result = new StringBuilder();

        for(Map.Entry<Integer, Integer> map : maps.entrySet()){
            if(number%map.getKey() == 0)
                result.append(map.getValue());
        }

        if(result.length()==0)
            return String.valueOf(number);

        return result.toString();

    }
    public static String translate(String text){
        StringBuilder translatedText = new StringBuilder();
        int length = text.length();
        String vowels = "aeiou";

        for (int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);

            if (vowels.contains(String.valueOf(currentChar))) {
                if (i > 0 && vowels.contains(String.valueOf(text.charAt(i - 1)))) {
                    translatedText.append(currentChar);
                } else {
                    translatedText.append("av").append(currentChar);
                }
            } else {
                translatedText.append(currentChar);
            }
        }

        return translatedText.toString();
    }

    public static double approx(double[][] pts){

        double result = 0;

        int countPoint =0;
        for(double[] pt : pts){
            double x = pt[0];
            double y = pt[1];
            if(Math.pow(x, 2) + Math.pow(y, 2) <= 1){
                countPoint++;
            }
        }

        return (double)(countPoint/pts.length) *4;

    }

    public static String encode(String plainText){

        String[] textArr = plainText.split("");

        StringBuilder result = new StringBuilder();
        int saut = 0;

        int i = 0;
        while (i<plainText.length()){
            int count = 1;
            char currentChar = plainText.charAt(i);
            for (int j = i+1; j <plainText.length() ; j++) {
                if(plainText.charAt(j) == currentChar)
                    count++;
                else
                    break;
            }
            i += count;

            result = result.append(count).append(currentChar);
        }
        return result.toString();

        /*for (int i = 0; i <textArr.length ; i = i+saut) {
            int count = 1;
            char currentChar = plainText.charAt(i);
            for (int j = i+1; j < textArr.length ; j++) {

                if(plainText.charAt(j) == currentChar) {
                    count++;
                }
                else
                    break;
            }
            saut = count;
            result += count+""+plainText.charAt(i);
        }

        return result;*/
    }

    public static int computeCheckDigit(String identificationNumber){

        String[] str = identificationNumber.split("");
        int sumPair = 0;
        int sumImpair =0;
        int result = 0;
        for (int i = 0; i < identificationNumber.length(); i++) {
            int currentNumber = Integer.valueOf(str[i]);

            if(i%2 == 0)
                sumPair += currentNumber;
            else
                sumImpair += currentNumber;
        }

        result = sumPair*3 + sumImpair;

        int lastInt = result%10;
        if( lastInt != 0)
            return 10-lastInt;

        return 0;

    }

    public static int puzzleSolver2(int width, int height, int nbBlocks, String[] grid) {
        // Création d'une copie de la grille pour éviter de la modifier directement
        char[][] puzzle = new char[height][width];
        for (int i = 0; i < height; i++) {
            puzzle[i] = grid[i].toCharArray();
        }

        // Parcours de la grille pour trouver le premier bloc disponible
        for (int blockNumber = 0; blockNumber < nbBlocks; blockNumber++) {
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    if (puzzle[row][col] == Character.forDigit(blockNumber, 10)) {
                        // Vérification si le bloc peut être déplacé vers la droite
                        if (col + 1 < width && puzzle[row][col + 1] == '.') {
                            return blockNumber; // Le bloc peut être déplacé
                        } else {
                            // Le bloc est bloqué, on arrête la recherche pour ce bloc
                            break;
                        }
                    }
                }
            }
        }

        // Si aucun bloc n'est disponible pour le déplacement, retourner -1 (aucune action)
        return -1;
    }

    public static int puzzleSolver(int width, int height, int nbBlocks, String[] grid) {


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String[] line = grid[i].split("");
                if(!(line[j].toLowerCase()).equals("x") && !line[j].equals(".")) {

                    int gridVal = Integer.valueOf(line[j]);
                    if(j+1 < width ) {
                        if (line[j + 1].equals(".")) {
                            return gridVal;
                        }
                    }else{
                        return gridVal;
                    }

                }
            }
        }

        return -1;
    }

    public static int remainingLeaves2(int width, int height, int[][] leaves, String winds) {

        int count = 0;
        for (int i = 0; i < winds.length(); i++) {
            if (winds.charAt(i) == 'U') {
                for (int j = 0; j < height; j++) {
                    for (int k = 0; k < width; k++) {
                        if (j > 0) {
                            count += leaves[j][k];
                        }

                    }
                }
            } else if (winds.charAt(i) == 'U') {
                for (int j = 0; j < height; j++) {
                    for (int k = 0; k < width; k++) {
                        if (j + 1 > height)
                            leaves[j + 1][k] = leaves[j][k];

                    }
                }
            } else if (winds.charAt(i) == 'R') {
                for (int j = 0; j < height; j++) {
                    for (int k = 0; k < width; k++) {
                        if (k + 1 > height) {
                            leaves[j][k + 1] = leaves[j][k];
                        }
                    }
                }
            } else if (winds.charAt(i) == 'L') {
                for (int j = 0; j < height; j++) {
                    for (int k = 0; k < width; k++) {
                        if (k > 0) {
                            leaves[j][k - 1] = leaves[j][k];
                        }
                    }
                }

            }



        }
        return count;

    }

    public static int remainingLeaves(int width, int height, int[][] leaves, String winds) {
        int remainingLeaves = 0;

        // Copie des emplacements initiaux des feuilles
        int[][] garden = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                garden[i][j] = leaves[i][j];
            }
        }

        // Parcours des coups de vent
        for (char wind : winds.toCharArray()) {
            // Déplacement des feuilles selon la direction du vent
            switch (wind) {
                case 'U':
                    for (int i = 0; i < height - 1; i++) {
                        for (int j = 0; j < width; j++) {
                            garden[i][j] = garden[i + 1][j];
                        }
                    }
                    break;
                case 'D':
                    for (int i = height - 1; i > 0; i--) {
                        for (int j = 0; j < width; j++) {
                            garden[i][j] = garden[i - 1][j];
                        }
                    }
                    break;
                case 'L':
                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width - 1; j++) {
                            garden[i][j] = garden[i][j + 1];
                        }
                    }
                    break;
                case 'R':
                    for (int i = 0; i < height; i++) {
                        for (int j = width - 1; j > 0; j--) {
                            garden[i][j] = garden[i][j - 1];
                        }
                    }
                    break;
            }

            // Réinitialisation des feuilles sorties du jardin
            for (int i = 0; i < height; i++) {
                garden[i][width - 1] = 0;
                garden[i][0] = 0;
            }
            for (int j = 0; j < width; j++) {
                garden[height - 1][j] = 0;
                garden[0][j] = 0;
            }
        }

        // Calcul du nombre de feuilles restantes
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                remainingLeaves += garden[i][j];
            }
        }

        return remainingLeaves;
    }
}

