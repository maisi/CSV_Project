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
        for (int i = 0; i < updateList.size(); i++) {
            Bean updateBean = updateList.get(i);
            for (int j = 0; j < currentList.size(); j++) {
                Bean currentBean = currentList.get(j);
                if (currentBean.getCode().equals(updateBean.getCode())) {
                    if (!(currentBean.getGueltig_von().after(updateBean.getGueltig_von()))) {
                        if (!(currentBean.getGueltig_bis().before(updateBean.getGueltig_von()))) {
                            //close old entry and create new one
                            statements.add(WriteUPDATE.createUPDATE(currentBean, updateBean));
                            statements.add(WriteUPDATE.createINSERT(currentBean, updateBean));
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



