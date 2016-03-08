package MP1_URM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jessa
 */
public class Tracer {

    static ArrayList<Integer> values;
    static ArrayList<String> commands;
    static String fileName;
    static String outputFile;
    static PrintWriter writer;

    Tracer(String fileName) throws FileNotFoundException {
        values = new ArrayList();
        commands = new ArrayList();
        Scanner read = new Scanner(new File(fileName));
        String tmp = null;
        tmp = read.nextLine();

        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) != ' ') {
                values.add((int) tmp.charAt(i) - 48);
            }
        }

        System.out.println("");
        while (read.hasNextLine()) {
            String tmpCommand = read.nextLine();
            tmpCommand = tmpCommand.replaceAll("\\s", "");
            commands.add(tmpCommand);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scan = new Scanner(System.in);
        System.out.println("File Name: ");
        String filename = scan.nextLine();
        filename = filename + ".in";
        Tracer URM = new Tracer(filename);
        outputFile = filename + ".out";
        writer = new PrintWriter(outputFile, "UTF-8");

        for (int i = 0; i < values.size(); i++) {
            writer.print(values.get(i));
            System.out.print(values.get(i));
        }
        writer.print("\n");

        System.out.println("");
        for (int i = 0; i < commands.size(); i++) {
            String command = commands.get(i);
            int m = command.charAt(1) - 48;
            //System.out.println(command);
            if (command.charAt(0) == 'S') {
                if (command.length() > 2) {

                    int one = command.charAt(1) - 48;
                    int two = command.charAt(2) - 48;
                    m = one * 10 + two;
                }
                URM.successor(m);
            } else if (command.charAt(0) == 'Z') {
                if (command.length() > 2) {

                    int one = command.charAt(1) - 48;
                    int two = command.charAt(2) - 48;
                    m = one * 10 + two;
                }
                URM.zero(m);
            } else if (command.charAt(0) == 'J') {
                int n = command.charAt(2) - 48;
                int j = command.charAt(3) - 48;
                if (values.get(m) == values.get(n)) {
                    if (command.length() > 4) {

                        int one = command.charAt(3) - 48;
                        int two = command.charAt(4) - 48;
                        j = one * 10 + two;
                    }
                    i = j - 2;
                } else {
                    continue;
                }
                URM.print();

            } else if (command.charAt(0) == 'C') {
                int n = command.charAt(2) - 48;
                URM.copy(m, n);
            }
        }
        writer.close();
    }

    private void successor(int index) {
        int increment = values.get(index);
        increment += 1;
        values.set(index, increment);
        print();

    }

    private void zero(int index) {
        values.set(index, 0);
        print();
    }

    private void copy(int i1, int i2) {
        values.set(i2, values.get(i1));
        print();
    }

    private void print() {
        for (int i = 0; i < values.size(); i++) {
            System.out.print(values.get(i));
            writer.print(values.get(i));
        }
        writer.println();
        System.out.println("");
    }
}
