import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Please enter your preferred UI\n1=Swing GUI\n2=Console\n3=Exit");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();

            if (choice == 1) {
                SwingUI.createUI();
            } else if (choice == 3) {
                System.exit(0);
            } else {
                console();
            }
        }
    }

    //Does validation,Error Handling
    //Return a valid path
    //choice 1=ATLAS file
    //choice 2=DB export
    public static String IOStuff(int filetype) {
        String ret;
        Scanner scanner = new Scanner(System.in);
        ret = scanner.nextLine();
        try {
            if (!Validator.validate(ret, filetype)) {
                System.out.println("The given file does not have the required format,please try again:");
                ret = IOStuff(filetype);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The given file was not found, please again:");
            ret = IOStuff(filetype);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("No Path/File was selected");
        }

        return ret;
    }

    //Handles the console output
    public static void console() {
        System.out.println("Please enter the ATLAS file");

        String updateFilePath = IOStuff(1);

        System.out.println("Please enter the DB export");

        String currentFilePath = IOStuff(2);

        System.out.println("Processing...");
        TSVReader tsvReader = new TSVReader(updateFilePath);  //must be used for ATLAS file
        DSVReader dsvReader = new DSVReader(currentFilePath); //DSV must be used for DB export
        Processor.process(tsvReader, dsvReader, "output.txt");


        System.out.println("Done...");
    }

}





/*

select LVVVALUE,TO_CHAR(LVVVALIDFROM, 'dd/mm/yyyy hh:MM:ss'),TO_CHAR(LVVVALIDTO, 'dd/mm/yyyy hh:MM:ss'),LOAATTRIBUTE,LVAVALUE,LOTTRANSLATION,LVVSID,LOVSID,LVASID,LOASID from mic_lov,mic_lov_value,mic_lov_attribute,mic_lov_value_attribute,mic_lov_translation
where lovsid=lvvlovsid
and loalovsid=lovsid
and lvalvvsid=lvvsid
and lvaloasid=loasid
and lotrefsid=lvvsid
and lotlanguage='DEU'
and LOVGROUP='UNTERLAGEN_AES';
 */