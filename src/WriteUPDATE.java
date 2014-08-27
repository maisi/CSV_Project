import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class WriteUPDATE {

    private static final String sqlINSERTnewUPDATEoldStmt = "INSERT INTO mic_lov_value (LVVSID,LVVLOVSID,LVVVALUE,LVVVALIDFROM,LVVVALIDTO) " +
            "VALUES (mic_lov_value_seq.nextval,%d,'%s','%s','%s');";
    private static final String sqlUPDATEStatementTemplate = "UPDATE MIC_LOV_VALUE set LVVVALIDTO='%s' WHERE LVVSID=%d;";
    private static final String sqlADDinsert = "INSERT INTO mic_lov_value_attribute (lvasid,lvalvvsid,lvaloasid,lvavalue) VALUES(mic_lov_value_attribute_seq.nextval,mic_lov_value_seq.currval," +
            " (select LOASID from mic_lov_attribute where loalovsid=(select LOVSID from mic_lov where lovcountry = 'DE' and LOVGROUP = 'UNTERLAGEN_AES') and LOAATTRIBUTE='ADDITION_REQUIRED'),'%s');";
    private static final String sqlREFinsert = "INSERT INTO mic_lov_value_attribute (lvasid,lvalvvsid,lvaloasid,lvavalue) VALUES(mic_lov_value_attribute_seq.nextval,mic_lov_value_seq.currval," +
            " (select LOASID from mic_lov_attribute where loalovsid=(select LOVSID from mic_lov where lovcountry = 'DE' and LOVGROUP = 'UNTERLAGEN_AES') and LOAATTRIBUTE='REFERENCE_REQUIRED'),'%s');";
    private static final String sqISSUEinsert = "INSERT INTO mic_lov_value_attribute (lvasid,lvalvvsid,lvaloasid,lvavalue) VALUES(mic_lov_value_attribute_seq.nextval,mic_lov_value_seq.currval," +
            " (select LOASID from mic_lov_attribute where loalovsid=(select LOVSID from mic_lov where lovcountry = 'DE' and LOVGROUP = 'UNTERLAGEN_AES') and LOAATTRIBUTE='ISSUEDATE_REQUIRED'),'%s');";
    private static final String sqlVALinsert = "INSERT INTO mic_lov_value_attribute (lvasid,lvalvvsid,lvaloasid,lvavalue) VALUES(mic_lov_value_attribute_seq.nextval,mic_lov_value_seq.currval," +
            " (select LOASID from mic_lov_attribute where loalovsid=(select LOVSID from mic_lov where lovcountry = 'DE' and LOVGROUP = 'UNTERLAGEN_AES') and LOAATTRIBUTE='VALIDTO_REQUIRED'),'%s');";
    private static final String sqlDETinsert = "INSERT INTO mic_lov_value_attribute (lvasid,lvalvvsid,lvaloasid,lvavalue) VALUES(mic_lov_value_attribute_seq.nextval,mic_lov_value_seq.currval," +
            " (select LOASID from mic_lov_attribute where loalovsid=(select LOVSID from mic_lov where lovcountry = 'DE' and LOVGROUP = 'UNTERLAGEN_AES') and LOAATTRIBUTE='DETAIL_REQUIRED'),'%s');";
    private static final String sqlVALUinsert = "INSERT INTO mic_lov_value_attribute (lvasid,lvalvvsid,lvaloasid,lvavalue) VALUES(mic_lov_value_attribute_seq.nextval,mic_lov_value_seq.currval," +
            " (select LOASID from mic_lov_attribute where loalovsid=(select LOVSID from mic_lov where lovcountry = 'DE' and LOVGROUP = 'UNTERLAGEN_AES') and LOAATTRIBUTE='VALUE_REQUIRED'),'%s');";
    private static final String sqlUOMinsert = "INSERT INTO mic_lov_value_attribute (lvasid,lvalvvsid,lvaloasid,lvavalue) VALUES(mic_lov_value_attribute_seq.nextval,mic_lov_value_seq.currval," +
            " (select LOASID from mic_lov_attribute where loalovsid=(select LOVSID from mic_lov where lovcountry = 'DE' and LOVGROUP = 'UNTERLAGEN_AES') and LOAATTRIBUTE='UOM_REQUIRED'),'%s');";
    private static final String sqlTRANSinsert = "INSERT INTO MIC_LOV_TRANSLATION(LOTSID,LOTREFSID,LOTREFTYPE,LOTLANGUAGE,LOTTRANSLATION) VALUES (mic_lov_translation_seq.nextval,mic_lov_value_seq.currval,'LVV','DEU','%s');";



    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static String createUPDATE2(Bean oldBean, Bean newBean) {

        return String.format(sqlUPDATEStatementTemplate, dateFormat.format(newBean.getGueltig_bis()), oldBean.getLVVSID());
    }

    public static String createUPDATE(Bean oldBean, Bean newBean) {


        Calendar cal = Calendar.getInstance();
        cal.setTime(newBean.getGueltig_von());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

        return String.format(sqlUPDATEStatementTemplate, dateFormat.format(timestamp), oldBean.getLVVSID());
    }

    public static ArrayList<String> createINSERT(Bean oldBean, Bean newBean) {

        ArrayList<String> statements = new ArrayList<String>();

        String sqlStatement = String.format(sqlINSERTnewUPDATEoldStmt, oldBean.getLOVSID(), newBean.getCode(), dateFormat.format(newBean.getGueltig_von()), dateFormat.format(newBean.getGueltig_bis()));
        statements.add(sqlStatement);

        String sqlStatement1 = String.format(sqlADDinsert, newBean.getZusatz());
        statements.add(sqlStatement1);

        String sqlStatement2 = String.format(sqlREFinsert, newBean.getReferenz());
        statements.add(sqlStatement2);

        String sqlStatement3 = String.format(sqISSUEinsert, newBean.getAusstellung());
        statements.add(sqlStatement3);

        String sqlStatement4 = String.format(sqlVALinsert, newBean.getGueltigkeitsende());
        statements.add(sqlStatement4);

        String sqlStatement5 = String.format(sqlDETinsert, newBean.getDetail());
        statements.add(sqlStatement5);

        String sqlStatement6 = String.format(sqlVALUinsert, newBean.getWert());
        statements.add(sqlStatement6);

        String sqlStatement7 = String.format(sqlUOMinsert, newBean.getMaﬂeinheit());
        statements.add(sqlStatement7);

        String sqlStatement8 = String.format(sqlTRANSinsert, newBean.getBedeutung());
        statements.add(sqlStatement8);

        return statements;
    }


}

