import java.util.*;

import static java.util.stream.Collectors.toMap;

    public class GolfClub {
        private static HashMap<String, Integer> scoreEntered;
        private static HashMap<String, Integer> recover;
        private static Scanner sc = new Scanner(System.in);
        private static Integer count=0;
        private static Integer countR=0;
        public static void main(String[] args) {

            scoreEntered = new HashMap<>();
            recover = new HashMap<>();

            int option;

                System.out.println("Welcome to Springfield Golf Club.");
                System.out.println("1) Enter the score");
                System.out.println("2) Find Golfer");
                System.out.println("3) Display score board");
                System.out.println("4) Remove Player");
                System.out.println("5) Restore player");
                System.out.println("6) Exit Program");
                System.out.println(">");

                while(!sc.hasNextInt()){

                    System.out.println("Invalid Value please enter a number!!!...");

                    sc.next();
                }
                option = sc.nextInt();
                switch(option){
                    case 1:
                        enterScore();
                        break;
                    case 2:
                        findGolfer();
                        break;
                    case 3:
                        displayScoreBoard();
                        break;
                    case 4:
                        removePlayer();
                        break;
                    case 5:
                        restorePlayer();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option!!! Please enter a number between 1 and 4");
                }
            }


        private static  void displayScoreBoard(){
            Map<String, Integer> scoreB = scoreEntered
                    .entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

            System.out.println("Score Board" + scoreB);
            System.out.println("Number of golfers : "+count);

        }

        private static  void findGolfer(){
            System.out.println("Enter the golfers name");
            String golferName = sc.next();
            while(!sc.hasNextLine()){
                System.out.println("Invalid Value please enter a number!!!...");
                sc.next();
            }
            while (!scoreEntered.containsKey(golferName)){
                System.out.println("Entered name can not be found in scoreboard");
                golferName=sc.next();
            }
            System.out.println("Name    -     Score");
            System.out.println(golferName+"    :    " + scoreEntered.get(golferName));
        }

        private static  void enterScore() {
            System.out.println("How many golfers in your group? : ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid Value please enter a number!!!...");
                sc.next();
            }
            Integer option = sc.nextInt();
            for (int i = 0; i < option; i++) {
                System.out.println("Enter a new name: ");
                String name = sc.next();
                if (scoreEntered.containsKey(name)) {
                    System.out.println("The entered name is already exist. Do you want to keep existing data?.'yes' or 'no' : ");
                    String data = sc.next();
                    switch (data) {
                        case "yes":
                            System.out.println("You have select the yes option");
                            break;

                        case "no":
                            System.out.println("Enter a new result : ");
                            Integer score = sc.nextInt();
                            scoreEntered.put(name, score);
                            recover.put(name,score);
                            break;
                        default:
                            System.out.println("Invalid option!!! Please enter 'yes' or 'no' :");
                            break;
                    }
                }
                if(!scoreEntered.containsKey(name)){
                    System.out.println("Enter a new result :");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid result, please enter a number!!!...");
                        sc.next();
                    }
                    Integer score = sc.nextInt();
                    while (score < 18 || score > 108) {
                        System.out.println("Invalid score. Please enter a score between 18 and 108 :");
                        score = sc.nextInt();
                    }
                    scoreEntered.put(name, score);
                    recover.put(name,score);
                    count++;
                }
            }
        }

        private static  void removePlayer() {
            switch (count) {
                case 0:
                    System.out.println("There aren't any players that you can delete");
                    break;
                default:
                    System.out.println("How many players do you want to delete ?:");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid value please enter a number:");
                        sc.next();
                    }
                    int deleteC = sc.nextInt();
                    while (deleteC > count || deleteC <= 0) {
                        System.out.println("That many players don't exist. Maximum Number of players you can delete is " + count + ". Please re enter a value :");
                        if (sc.hasNextInt()) {
                            deleteC = sc.nextInt();
                        } else {
                            System.out.println("Invalid value please enter a number: ");
                            deleteC = sc.nextInt();
                        }
                    }
                    for (int i = 1; i <= deleteC; i++) {
                        System.out.println("Enter the name of the player that you want do delete: ");
                        String deleteName = sc.next();
                        while (!scoreEntered.containsKey(deleteName)) {
                            System.out.println("Entered name can not be found in scoreboard");
                            deleteName = sc.next();
                        }
                        scoreEntered.remove(deleteName);

                        System.out.println("You removed "+deleteName);
                        count--;
                        countR++;
                    }
            }
        }

        private static void restorePlayer(){
            switch (countR) {
                case 0:
                    System.out.println("There aren't any players to restore");
                    break;
                default:
                    System.out.println("Enter the name of the player that you want to restore: ");
                    String restoreName = sc.next();
                    while (!recover.containsKey(restoreName)) {
                        System.out.println("Entered name cannot be found");
                        restoreName = sc.next();
                    }   String nameR = null;
                    for (String i : recover.keySet()) {
                        if (i.equals(restoreName)) {
                            nameR = i;
                            Integer b = recover.get(i);
                            scoreEntered.put(nameR, b);
                            recover.remove(restoreName);
                            countR--;
                            count++;
                        } else {
                            System.out.println("Entered name cannot be found");
                        }
                    }
            }
        }
    }

