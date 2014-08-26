import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DSVReader {

    private String filename;

    public DSVReader(String filename) {
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
                if (datavalue[0].equals("LVVVALUE")) {
                    continue;
                }

                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

                String Code = datavalue[0];

                Bean bean = new Bean();
                bean.setCode(Code);

                if (data.contains(bean)) {

                    bean = data.get(data.indexOf(bean));

                } else {
                    bean = new Bean();
                    bean.setCode(Code);

                }

                java.util.Date date = df.parse(datavalue[1]);
                Timestamp gueltig_von = new java.sql.Timestamp(date.getTime());

                bean.setGueltig_von(gueltig_von);

                Timestamp gueltig_bis;
                if (!(datavalue[2].equals(""))) {
                    date = df.parse(datavalue[2]);
                    gueltig_bis = new java.sql.Timestamp(date.getTime());
                } else {
                    gueltig_bis = Timestamp.valueOf("3999-12-31 00:00:00.0");
                }

                bean.setGueltig_bis(gueltig_bis);


                String Attribut = datavalue[3];
                char Value = datavalue[4].charAt(0);
                if (Attribut.equals("REFERENCE_REQUIRED")) {
                    bean.setReferenz(Value);
                } else if (Attribut.equals("UOM_REQUIRED")) {
                    bean.setMaﬂeinheit(Value);
                } else if (Attribut.equals("VALUE_REQUIRED")) {
                    bean.setWert(Value);
                } else if (Attribut.equals("DETAIL_REQUIRED")) {
                    bean.setDetail(Value);
                } else if (Attribut.equals("VALIDTO_REQUIRED")) {
                    bean.setGueltigkeitsende(Value);
                } else if (Attribut.equals("ISSUEDATE_REQUIRED")) {
                    bean.setAusstellung(Value);
                }

                bean.setBedeutung(datavalue[5]);
                bean.setLVVSID(Integer.parseInt(datavalue[6]));
                bean.setLOVSID(Integer.parseInt(datavalue[7]));
                bean.setLVASID(Integer.parseInt(datavalue[8]));
                bean.setLOASID(Integer.parseInt(datavalue[9]));

                //Bean bean = new Bean(Code, gueltig_von, gueltig_bis, Referenz, Zusatz, Detail, Ausstellung, Gueltigkeitsende, Wert, Maﬂeinheit, Bedeutung);
                if (!data.contains(bean)) {
                    data.add(bean);
                }
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
