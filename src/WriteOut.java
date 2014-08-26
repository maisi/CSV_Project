import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class WriteOut {

    public static void fileWriter(ArrayList<String> list, String savePath) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savePath)));
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                writer.write(iterator.next());
                writer.newLine();
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void screenWriter(ArrayList<String> list) {
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
