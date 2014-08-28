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
                    if ((currentBean.getGueltig_von().before(updateBean.getGueltig_von()))) //Test dass neuer eintrag wirklich neuer ist
                    {
                        if (!(updateBean.getGueltig_bis().before(new Date()))) {  //Test dass neuer eintrag nicht schon beendet ist (verhindert dass alte einträge geladen werden
                            //close old entry and create new one
                            statements.add(WriteUPDATE.createUPDATE(currentBean, updateBean));
                            statements.addAll(WriteUPDATE.createINSERT(currentBean, updateBean));
                        } else if (currentBean.getGueltig_bis().after(new Date())) {
                            //unterlage beenden ohne neue zu erstellen falls nicht schon beendet
                            statements.add(WriteUPDATE.createUPDATE2(currentBean, updateBean));
                        }
                    }
                    // else
                    // {

                    //  if(updateBean.getGueltig_von().before(currentBean.getGueltig_von()))
                    //  {
                    //      if (updateBean.getGueltig_bis().before(currentBean.getGueltig_bis()))
                    //     {
                    //          //bestehenden eintrag beenden dessen von datum eigentlich vor dem liegt welches in der datenbank steht
                    //          System.out.println("TEST1" + " " + updateBean.getCode() + " " + updateBean.getGueltig_bis() + " " + currentBean.getGueltig_bis());
                    //          statements.add(WriteUPDATE.createUPDATE2(currentBean, updateBean));
                    //      }
                    // }
                            /*else if((updateBean.getGueltig_bis().after(new Date()))){
                                System.out.println("TEST2"+" "+updateBean.getCode());
                                statements.add(WriteUPDATE.createUPDATE(currentBean, updateBean));
                                statements.addAll(WriteUPDATE.createINSERT(currentBean, updateBean));
                            }*/
                    //}
                    //}
                    //}
                    //else {
                    //System.out.println(currentBean.toString());
                    //System.out.println(updateBean.toString());
                }
            }
            //create new entry if no entry exists
            if (!currentList.contains(updateBean)) {
                if (!(updateBean.getGueltig_bis().before(new Date()))) {
                    statements.addAll(WriteINSERT.createINSERT(updateBean));
                }
            }
        }
        return statements;
    }


}



