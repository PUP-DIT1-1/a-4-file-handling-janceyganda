import java.io.*;
import java.util.*;

public class PersistentRecordSystem {

    static String[] names = new String[100];
    static double[] grades = new double[100];
    static int count = 0;

    static final String FILE_NAME = "records.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loadRecords();

        int choice;

        do {
            System.out.println("\n==== RECORD MENU ====");
            System.out.println("1 - Add Record");
            System.out.println("2 - View Records");
            System.out.println("3 - Update Record");
            System.out.println("4 - Delete Record");
            System.out.println("5 - Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addRecord(sc);
                    break;
                case 2:
                    viewRecords();
                    break;
                case 3:
                    updateRecord(sc);
                    break;
                case 4:
                    deleteRecord(sc);
                    break;
                case 5:
                    saveRecords();
                    System.out.println("Exiting... Data saved.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

    static void loadRecords() {
        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] data = line.split(",");

                names[count] = data[0];
                grades[count] = Double.parseDouble(data[1]);
                count++;
            }

            fileReader.close();

        } catch (Exception e) {
        }
    }

    static void saveRecords() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);

            for (int i = 0; i < count; i++) {
                writer.write(names[i] + "," + grades[i] + "\n");
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving records.");
        }
    }
    static void addRecord(Scanner sc) {
        System.out.print("Enter name: ");
        names[count] = sc.nextLine();

        System.out.print("Enter grade: ");
        grades[count] = sc.nextDouble();
        sc.nextLine();

        count++;

        saveRecords();
        System.out.println("Record added!");
    }
    static void viewRecords() {
        if (count == 0) {
            System.out.println("No records found.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(i + " - " + names[i] + " : " + grades[i]);
        }
    }

    static void updateRecord(Scanner sc) {
        viewRecords();

        System.out.print("Enter index to update: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 0 || index >= count) {
            System.out.println("Invalid index.");
            return;
        }

        System.out.print("Enter new name: ");
        names[index] = sc.nextLine();

        System.out.print("Enter new grade: ");
        grades[index] = sc.nextDouble();
        sc.nextLine();

        saveRecords();
        System.out.println("Record updated!");
    }

    static void deleteRecord(Scanner sc) {
        viewRecords();

        System.out.print("Enter index to delete: ");
        int index = sc.nextInt();

        if (index < 0 || index >= count) {
            System.out.println("Invalid index.");
            return;
        }

        for (int i = index; i < count - 1; i++) {
            names[i] = names[i + 1];
            grades[i] = grades[i + 1];
        }

        count--;

        saveRecords();
        System.out.println("Record deleted!");
    }
}