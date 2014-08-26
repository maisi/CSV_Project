import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TSVReader {

    private String filename;

    public TSVReader(String filename) {
        this.filename = filename;
    }

    public ArrayList<Bean> parse() {

        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filename), "ISO-8859-15"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ArrayList<Bean> data = new ArrayList<Bean>();
        String line;

        assert bReader != null;
        try {
            while ((line = bReader.readLine()) != null) {

                String datavalue[] = line.split("\t");
                if (datavalue[0].equals("# Code")) {
                    continue;
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

                String Code = datavalue[0];
                String Qualifikator = datavalue[1];

                if (!Qualifikator.equals("")) {
                    Code = Code + "-" + Qualifikator;
                }

                java.util.Date date = df.parse(datavalue[2]);
                Timestamp gueltig_von = new java.sql.Timestamp(date.getTime());

                Timestamp gueltig_bis;
                if (!(datavalue[3].equals(""))) {
                    date = df.parse(datavalue[3]);
                    gueltig_bis = new java.sql.Timestamp(date.getTime());
                } else {
                    gueltig_bis = Timestamp.valueOf("2099-12-31 00:00:00.0");
                }

                char Referenz = datavalue[4].charAt(0);
                char Zusatz = datavalue[5].charAt(0);
                char Detail = datavalue[6].charAt(0);
                char Ausstellung = datavalue[7].charAt(0);
                char Gültigkeitsende = datavalue[8].charAt(0);
                char Wert = datavalue[9].charAt(0);
                char Maßeinheit = datavalue[10].charAt(0);
                String Bedeutung = datavalue[11];


                Bean bean = new Bean(Code, Qualifikator, gueltig_von, gueltig_bis, Referenz, Zusatz, Detail, Ausstellung, Gültigkeitsende, Wert, Maßeinheit, Bedeutung);
                data.add(bean);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
