import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter how many draft picks have been decided: ");
    int number = input.nextInt();

    if (number == 1) {
      printSecondPickPerecentages();
    }
    else if (number == 2) {
      printThirdPickPerecentages();
    }
    else {
      printFourthPickPerecentages();
    }
  }

  public static double draftPercentage2(int firstPick) {
    double percentageFirst = getPercentage(firstPick);
    for (int i = 1; i < 15; i++) {
      double combinations = 1000.0;
      if (i != firstPick) {
        combinations = combinations - (10 * percentageFirst);
        System.out.print(i + " " + ((getPercentage(i) * 10.0) / combinations) * 100 + "\n");
      }
    }

    return 0;
  }

  public static double draftPercentage3(int firstPick, int secondPick) {
    double percentageFirst = getPercentage(firstPick);
    double percentageSecond = getPercentage(secondPick);

    for (int i = 1; i < 15; i++) {
      double combinations = 1000.0;
      if ((i != firstPick) && (i != secondPick)) {
        combinations = combinations - (10 * percentageFirst) - (10 * percentageSecond);
        System.out.print(i + " " + ((getPercentage(i) * 10.0) / combinations) * 100 + "\n");
      }
    }
    return 0;
  }

  public static void draftPercentage4(int firstPick, int secondPick, int thirdPick) {
    double percentageFirst = getPercentage(firstPick);
    double percentageSecond = getPercentage(secondPick);
    double percentageThird = getPercentage(thirdPick);

    for (int i = 1; i < 15; i++) {
      double combinations = 1000.0;
      if ((i != firstPick) && (i != secondPick) && (i != thirdPick)) {
        combinations = combinations - (10 * percentageFirst) - (10 * percentageSecond) - (10 * percentageThird);
        System.out.print(i + " " + ((getPercentage(i) * 10.0) / combinations) * 100 + "\n");
      }
    }
  }

  public static void printSecondPickPerecentages() {
    for (int i = 1; i < 15; i++) {
      System.out.print("The first seed is: " + i + "\n");
      draftPercentage2(i);
    }
  }

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
            System.out.print("The first 2 seeds are: " + i + " " + j + "\n");
            draftPercentage3(i, j);
          }
        }
      }
    }
  }

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
              System.out.print("The first 3 seeds are: " + i + " " + j + " " + k + "\n");
              draftPercentage4(i, j, k);
            }
          }
        }
      }
    }
  }

  public static double getPercentage(int firstPick) {
    double percentages[] = new double[] { 14.0, 14.0, 14.0, 12.5, 10.5, 9.0, 7.5, 6.0, 4.5, 3.0, 2.0, 1.5, 1.0, 0.5 };

    return percentages[firstPick - 1];
  }
}