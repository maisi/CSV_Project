import java.io.*;

public class Validator {

    public static boolean validate(String filename, int filetype) throws FileNotFoundException, UnsupportedEncodingException {
        BufferedReader bReader;

        bReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), "ISO-8859-15"));

        String line = null;
        try {
            line = bReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String datavalue[] = new String[0];
        if (line != null) {
            datavalue = line.split("\t");
        } else {
            return false;
        }

        if (filetype == 1) { //ATLAS file
            return datavalue[0].equals("# Code") && validate(filename);

        } else return filetype == 2 && (datavalue[0].equals("LVVVALUE")) && validate(filename);
    }

    //checks if files have the correct format
    public static boolean validate(String filename) throws FileNotFoundException, UnsupportedEncodingException {

        BufferedReader bReader;

        bReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), "ISO-8859-15"));

        String line = null;
        try {
            line = bReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String datavalue[] = new String[0];
        if (line != null) {
            datavalue = line.split("\t");
        } else {
            return false;
        }

        if (!((datavalue.length == 12) || (datavalue.length == 10))) {
            return false;
        }

        if (!(datavalue[0].equals("# Code") || (datavalue[0].equals("LVVVALUE")))) {
            System.out.println(datavalue[0] + " " + "0");
            return false;
        } else if (!((datavalue[1].equals("Qualifikator")) || (datavalue[1].equals("LVVVALIDFROM")))) {
            System.out.println(datavalue[1] + " " + "1");
            return false;
        } else if (!((datavalue[2].equals("gültig von")) || (datavalue[2].equals("LVVVALIDTO")))) {
            System.out.println(datavalue[2] + " " + "2");
            return false;
        } else if (!((datavalue[3].equals("gültig bis")) || (datavalue[3].equals("LOAATTRIBUTE")))) {
            System.out.println(datavalue[3] + " " + "3");
            return false;
        } else if (!((datavalue[4].equals("Referenz")) || (datavalue[4].equals("LVAVALUE")))) {
            System.out.println(datavalue[4] + " " + "4");
            return false;
        } else if (!((datavalue[5].equals("Zusatz")) || (datavalue[5].equals("LOTTRANSLATION")))) {
            System.out.println(datavalue[5] + " " + "5");
            return false;
        } else if (!((datavalue[6].equals("Detail")) || (datavalue[6].equals("LVVSID")))) {
            System.out.println(datavalue[6] + " " + "6");
            return false;
        } else if (!((datavalue[7].equals("Ausstellung")) || (datavalue[7].equals("LOVSID")))) {
            System.out.println(datavalue[7] + " " + "7");
            return false;
        } else if (!((datavalue[8].equals("Gültigkeitsende")) || (datavalue[8].equals("LVASID")))) {
            System.out.println(datavalue[8] + " " + "8");
            return false;
        } else if (!((datavalue[9].equals("Wert")) || (datavalue[9].equals("LOASID")))) {
            System.out.println(datavalue[9] + " " + "9");
            return false;
        } else if (datavalue.length > 10) {
            if (!(datavalue[10].equals("Maßeinheit"))) {
                System.out.println(datavalue[10] + " " + "10");
                return false;
            }
        } else if (datavalue.length > 10) {
            if (!(datavalue[11].equals("Bedeutung"))) {
                System.out.println(datavalue[11] + " " + "11");
                return false;
            }
        }

        return true;
    }
}
