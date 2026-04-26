import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class File_ListMaker {

    public static void main(String[] args) {

        String MenuOP = "";
        boolean done = false;

        ArrayList<String> UserList = new ArrayList<String>();
        Scanner input = new Scanner(System.in);

        boolean needsToBeSaved = false;
        String currentFileName = "";

        do {
            DisplayList(UserList);

            MenuOP = SafeInput.getRegExString(input,
                    "A – Add\n" +
                            "D – Delete\n" +
                            "I – Insert\n" +
                            "M – Move\n" +
                            "V – View\n" +
                            "O – Open file\n" +
                            "S – Save file\n" +
                            "C – Clear list\n" +
                            "Q – Quit\n",
                    "[AaDdIiMmVvOoSsCcQq]");

            try {

                if (MenuOP.equalsIgnoreCase("A")) {

                    UserList.add(SafeInput.getNonZeroLenString(input, "Enter item"));
                    needsToBeSaved = true;

                } else if (MenuOP.equalsIgnoreCase("D")) {

                    NumberList(UserList);
                    UserList.remove(SafeInput.getInt(input, "Enter number to delete") - 1);
                    needsToBeSaved = true;

                } else if (MenuOP.equalsIgnoreCase("I")) {

                    NumberList(UserList);
                    UserList.add(
                            SafeInput.getInt(input, "Enter position") - 1,
                            SafeInput.getNonZeroLenString(input, "Enter item"));
                    needsToBeSaved = true;

                } else if (MenuOP.equalsIgnoreCase("M")) {

                    NumberList(UserList);

                    int from = SafeInput.getInt(input, "Move FROM position") - 1;
                    int to = SafeInput.getInt(input, "Move TO position") - 1;

                    String item = UserList.remove(from);
                    UserList.add(to, item);

                    needsToBeSaved = true;

                } else if (MenuOP.equalsIgnoreCase("V")) {

                    DisplayList(UserList);

                } else if (MenuOP.equalsIgnoreCase("C")) {

                    UserList.clear();
                    needsToBeSaved = true;

                } else if (MenuOP.equalsIgnoreCase("S")) {

                    if (currentFileName.equals("")) {
                        currentFileName = SafeInput.getNonZeroLenString(input, "Enter file name") + ".txt";
                    }

                    saveFile(UserList, currentFileName);
                    needsToBeSaved = false;

                } else if (MenuOP.equalsIgnoreCase("O")) {

                    if (needsToBeSaved) {
                        if (SafeInput.getYNConfirm(input, "Unsaved work. Save first?")) {
                            if (!currentFileName.equals("")) {
                                saveFile(UserList, currentFileName);
                            }
                        }
                    }

                    String file = SafeInput.getNonZeroLenString(input, "Enter file name") + ".txt";

                    UserList = loadFile(file);
                    currentFileName = file;
                    needsToBeSaved = false;

                } else if (MenuOP.equalsIgnoreCase("Q")) {

                    if (needsToBeSaved) {
                        if (SafeInput.getYNConfirm(input, "Unsaved changes. Save before quitting?")) {

                            if (!currentFileName.equals("")) {
                                saveFile(UserList, currentFileName);
                            } else {
                                currentFileName = SafeInput.getNonZeroLenString(input, "Enter file name") + ".txt";
                                saveFile(UserList, currentFileName);
                            }
                        }
                    }

                    if (SafeInput.getYNConfirm(input, "Are you sure you want to quit?")) {
                        done = true;
                    }
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (!done);
    }

    private static void DisplayList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            IO.println(list.get(i));
        }
    }

    private static void NumberList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            IO.println((i + 1) + ". " + list.get(i));
        }
    }

    private static void saveFile(ArrayList<String> list, String fileName) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (String item : list) {
            writer.write(item);
            writer.newLine();
        }

        writer.close();
    }

    private static ArrayList<String> loadFile(String fileName) throws IOException {

        ArrayList<String> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }

        reader.close();
        return list;
    }
}