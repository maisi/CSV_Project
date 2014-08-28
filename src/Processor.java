import java.util.ArrayList;
import java.util.Date;

public class Processor {

    public static void process(TSVReader tsvReader, DSVReader dsvReader, String savePath) {

        ArrayList<Bean> currentList = dsvReader.parse();
        ArrayList<Bean> updateList = tsvReader.parse();
        WriteOut.fileWriter(createStatement(currentList, updateList), savePath);
        //WriteOut.screenWriter(createStatement(currentList, updateList));


    }

    //Business Logic
    public static ArrayList<String> createStatement(ArrayList<Bean> currentList, ArrayList<Bean> updateList) {

        ArrayList<String> statements = new ArrayList<String>();
        for (Bean updateBean : updateList) {
            for (Bean currentBean : currentList) {
                if (currentBean.equals(updateBean)) {
                    if (!(currentBean.getGueltig_von().after(updateBean.getGueltig_von()))) {  //Test dass neuer eintrag wirklich neuer ist
                        //if (!(currentBean.getGueltig_bis().before(updateBean.getGueltig_von()))) { //Test dass bestehendes bis datum nicht überschrieben wird durch neueren eintrag
                            if (!(updateBean.getGueltig_bis().before(new Date()))) {  //Test dass neuer eintrag nicht schon beendet ist (verhindert dass alte einträge geladen werden
                                //close old entry and create new one
                                statements.add(WriteUPDATE.createUPDATE(currentBean, updateBean));
                                statements.addAll(WriteUPDATE.createINSERT(currentBean, updateBean));
                            } else {
                                //close old entry without creating a new one
                                //will update old entry to the latest entry in the zoll file
                                //System.out.println(updateBean.getGueltig_bis() + " " + currentBean.getGueltig_bis());
                                statements.add(WriteUPDATE.createUPDATE2(currentBean, updateBean));
                            }

                        // }

                    }
                } else {
                    //System.out.println(currentBean.toString());
                    //System.out.println(updateBean.toString());
                }
            }
            //create new entry if no entry exists
            if (!currentList.contains(updateBean)) {
                //if (!(updateBean.getGueltig_bis().before(new Date()))) {
                    statements.addAll(WriteINSERT.createINSERT(updateBean));
                //}
            }
        }
        return statements;
    }


}



