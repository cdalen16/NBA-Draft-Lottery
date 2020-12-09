import java.util.Scanner;

class Main {

  //accepts an input that determines how many picks have already
  //been decided (1, 2, or 3) and collects data accordingly
  public static void main(String[] args) {

    System.out.print("Enter how many draft picks have been decided: ");

    Scanner input = new Scanner(System.in);
    int number = input.nextInt();

    if (number == 1) {
      printSecondPickPerecentages();
    }
    else if (number == 2) {
      printThirdPickPerecentages();
    }
    else if (number == 3) {
      printFourthPickPerecentages();
    }
    else {
      System.out.print("More than 3 picks have already been chosen");
    }
  }

  //prints the probabilities of each remaining team to receive the
  //2nd overall pick given the first pick that has been chosen.
  public static double draftPercentage2(int firstPick) {
    double percentageFirst = getProbability(firstPick);

    for (int i = 1; i < 15; i++) {
      double combinations = 1000.0;

      if (i != firstPick) {
        combinations = combinations - (10 * percentageFirst);
        System.out.print(i + " " + ((getProbability(i) * 10.0) / combinations) * 100 + "\n");
      }
    }

    return 0;
  }

  //prints the probabilities of each remaining team to receive the
  //3rd overall pick given the first 2 picks that have been chosen.
  public static double draftPercentage3(int firstPick, int secondPick) {
    double percentageFirst = getProbability(firstPick);
    double percentageSecond = getProbability(secondPick);

    for (int i = 1; i < 15; i++) {
      double combinations = 1000.0;
      if ((i != firstPick) && (i != secondPick)) {
        combinations = combinations - (10 * percentageFirst) - (10 * percentageSecond);
        System.out.print(i + " " + ((getProbability(i) * 10.0) / combinations) * 100 + "\n");
      }
    }
    return 0;
  }

  //prints the probabilities of each remaining team to receive the
  //4th overall pick given the first 3 picks that have been chosen.
  public static void draftPercentage4(int firstPick, int secondPick, int thirdPick) {
    double percentageFirst = getProbability(firstPick);
    double percentageSecond = getProbability(secondPick);
    double percentageThird = getProbability(thirdPick);

    for (int i = 1; i < 15; i++) {
      double combinations = 1000.0;
      if ((i != firstPick) && (i != secondPick) && (i != thirdPick)) {
        combinations = combinations - (10 * percentageFirst) - (10 * percentageSecond) - (10 * percentageThird);
        System.out.print(i + " " + ((getProbability(i) * 10.0) / combinations) * 100 + "\n");
      }
    }
  }

  //considers all possible combinations of the first pick and calls 
  //draftPercentage4 to print the probabilities of each team for each new combination.
  public static void printSecondPickPerecentages() {
    for (int i = 1; i < 15; i++) {
      System.out.print("If the first overall pick is seed: " + i + "\n");
      draftPercentage2(i);
    }
  }

  //considers all possible combinations of the first 2 picks and excludes
  //those with same picks but in a different order. calls draftPercentage3
  //to print the probabilities of each team for each new combination.
  public static void printThirdPickPerecentages() {
    int[][] combos = new int[10000][3];
    int count = 0;

    for (int i = 1; i < 15; i++) {
      for (int j = 1; j < 15; j++) {

        if (i != j) {
          int flag = 0;
          for (int g = 0; g < count; g++) {
            if ((combos[g][0] == i || combos[g][0] == j) && (combos[g][1] == i || combos[g][1] == j)) {
              flag = 1;
            }
          }

          if (flag == 0) {
            combos[count][0] = i;
            combos[count][1] = j;
            count++;
            System.out.print("If the first 2 overall picks are seeds: " + i + " " + j + "\n");
            draftPercentage3(i, j);
          }
        }
      }
    }
  }

  //considers all possible combinations of the first 3 picks and excludes
  //those with same picks but in a different order. calls draftPercentage4
  //to print the probabilities of each team for each new combination.
  public static void printFourthPickPerecentages() {
    int[][] combos = new int[10000][3];
    int count = 0;

    for (int i = 1; i < 15; i++) {
      for (int j = 1; j < 15; j++) {
        for (int k = 1; k < 15; k++) {

          if (i != k && k != j && j != i) {
            int flag = 0;
            for (int g = 0; g < count; g++) {
              if ((combos[g][0] == i || combos[g][0] == j || combos[g][0] == k)
                  && (combos[g][1] == i || combos[g][1] == j || combos[g][1] == k)
                  && (combos[g][2] == i || combos[g][2] == j || combos[g][2] == k)) {
                flag = 1;
              }
            }
            if (flag == 0) {
              combos[count][0] = i;
              combos[count][1] = j;
              combos[count][2] = k;

              count++;
              System.out.print("If the first 3 overall picks are seeds: " + i + " " + j + " " + k + "\n");
              draftPercentage4(i, j, k);
            }
          }
        }
      }
    }
  }

  //returns the probability a specified team to receive the first 
  //overall pick, given the team's seed.
  public static double getProbability(int seed) {
    double probs[] = new double[] { 14.0, 14.0, 14.0, 12.5, 10.5, 9.0, 7.5, 6.0, 4.5, 3.0, 2.0, 1.5, 1.0, 0.5 };

    return probs[seed - 1];
  }
}
