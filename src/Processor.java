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
                if (currentBean.getCode().equals(updateBean.getCode())) {
                    if (!(currentBean.getGueltig_von().after(updateBean.getGueltig_von()))) {  //Test dass neuer eintrag wirklich neuer ist
                        if (!(currentBean.getGueltig_bis().before(updateBean.getGueltig_von()))) { //Test dass bestehendes bis datum nicht überschrieben wird durch neueren eintrag
                            if (!(updateBean.getGueltig_bis().before(new Date()))) {  //Test dass neuer eintrag nicht schon beendet ist (verhindert dass alte einträge geladen werden
                                //close old entry and create new one
                                statements.add(WriteUPDATE.createUPDATE(currentBean, updateBean));
                                statements.addAll(WriteUPDATE.createINSERT(currentBean, updateBean));
                            } else {
                                statements.add(WriteUPDATE.createUPDATE2(currentBean, updateBean));
                            }

                        }

                    }
                }
            }
            //create new entry if no current entry exists
            if (!currentList.contains(updateBean)) {
                if (updateBean.getGueltig_bis().after(new Date())) {
                    statements.addAll(WriteINSERT.createINSERT(updateBean));
                }
            }
        }
        return statements;
    }


}



