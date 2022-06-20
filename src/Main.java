import java.io.*;
import java.util.Scanner;

public class Main {

    static int lastMenId = 0;

    static class Men {
        public int id;
        public String FCs;
        public String gender;
        public String reason;
    }

    static void printlnMessage(String message) {
        System.out.println(message);
    }

    static Men[] createEmptyMenArray() {
        return new Men[0];
    }

    static void printlnTableHeader() {
        System.out.println(String.format("%-3s%-20s%-4s%-40s", "ИД", "ФИО", "Гендер", "Причина внесения"));
    }

    static void printlnSeparator() {
        System.out.println("-".repeat(15));
    }

    static void printlnMenu() {
        printlnMessage("Меню:");
        printlnMessage("1. Добавить нового человека.");
        printlnMessage("2. Сохранить данные в текстовый файл.");
        printlnMessage("3. Загрузить данные из текстового файла.");
        printlnMessage("0. Выйти из программы.");
    }

    static int choosingAnAction() {
        Scanner scanner = new Scanner(System.in);
        int temp;
            printlnMenu("Введите номер действия: ");
            temp = scanner.nextInt();
        return temp;
    }

    static Men createMen() {
        Scanner scanner = new Scanner(System.in);

        lastMenId++;

        printlnMessage("Введите ФИО: ");
        String FCs = scanner.nextLine();

        printlnMessage("Введите гендер: ");
        String gender = scanner.nextLine();

        printlnMessage("Введите причину внесения: ");
        String reason = scanner.nextLine();

        return new Men(lastMenId, FCs, gender, reason);
    }

    static Men[] addMenToEndOfArray(Men[] men, Men insertMen) {
        Men[] tempMen = new Men[men.length + 1];

        for (int i = 0; i < men.length; i++) {
            tempMen[i] = men[i];
        }

        tempMen[tempMen.length - 1] = insertMen;

        return tempMen;
    }

    static void saveMenToTxtFile(String filename, Men[] men) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(Integer.toString(men.length));
        bufferedWriter.newLine();

        for (int i = 0; i < men.length; i++) {
            bufferedWriter.write(Integer.toString(men[i].id));
            bufferedWriter.newLine();

            bufferedWriter.write(men[i].FCs);
            bufferedWriter.newLine();

            bufferedWriter.write(Integer.toString(men[i].gender);
            bufferedWriter.newLine();

            bufferedWriter.write(Integer.toString(men[i].reason));
            bufferedWriter.newLine();
        }
        bufferedWriter.write(Integer.toString(lastMenId));
        bufferedWriter.newLine();

        bufferedWriter.close();
        fileWriter.close();
    }

    static Men[] loadMenFromTxtFile(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int menLength = Integer.parseInt(bufferedReader.readLine());
        Men[] men = new Men[menLength];

        for (int i = 0; i < men.length; i++) {
            int id = Integer.parseInt(bufferedReader.readLine());
            String FCs = bufferedReader.readLine();
            String gender = bufferedReader.readLine();
            String reason = bufferedReader.readLine();

            men[i] = new Men(id, FCs, gender, reason);
        }

        lastMenId = Integer.parseInt(bufferedReader.readLine());

        bufferedReader.close();
        fileReader.close();

        return men;
    }

    public static void main(String[] args) throws IOException {
        Men[] men = createEmptyMenArray();

        printlnMessage("Эта программа позволяет заполнить список: Миротворец.");

        while (true) {
            printlnTableHeader();
            printlnSeparator();
            printlnMenu();
            int menuPoint = choosingAnAction();

            switch (menuPoint) {
                case 1: {
                    Men insertMen = createMen();
                    men = addMenToEndOfArray(men, insertMen);
                }
                break;

                case 2: {
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Введите имя файла: ");
                    String filename = scanner.nextLine();

                    saveMenToTxtFile(filename, men);
                }

                case 3: {
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Введите имя файла: ");
                    String filename = scanner.nextLine();

                    men = loadMenFromTxtFile(filename);
                }

                case 0: {
                    System.exit(0);
                }
                break;
            }
        }
    }
}
