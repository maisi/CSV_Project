import java.text.SimpleDateFormat;

public class WriteUPDATE {

    private static final String sqlINSERTnewUPDATEoldStmt = "INSERT INTO mic_lov_value (LVVSID,LVVLOVSID,LVVVALUE,LVVVALIDFROM,LVVVALIDTO) " +
            "VALUES (mic_lov_value_seq.nextval,%d,'%s','%s','%s');";
    private static final String sqlUPDATEStatementTemplate = "UPDATE MIC_LOV_VALUE set LVVVALIDTO='%s' WHERE LVVSID=%d;";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static String createUPDATE(Bean oldBean, Bean newBean) {

        String sqlStatement = String.format(sqlUPDATEStatementTemplate, dateFormat.format(newBean.getGueltig_von()), oldBean.getLVVSID());
        //System.out.println(sqlStatement);
        //System.out.println("did Update "+ bean.getCode()+" "+bean.getGueltig_von());
        return sqlStatement;
    }

    public static String createINSERT(Bean oldBean, Bean newBean) {

        String sqlStatement = String.format(sqlINSERTnewUPDATEoldStmt, oldBean.getLOVSID(), newBean.getCode(), dateFormat.format(newBean.getGueltig_von()), dateFormat.format(newBean.getGueltig_bis()));
        //System.out.println(sqlStatement);
        //System.out.println("did Insert " + bean.getCode() + " " + bean.getGueltig_von());
        return sqlStatement;
    }


}

