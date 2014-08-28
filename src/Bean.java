import java.sql.Timestamp;

public class Bean {

    int LVVSID;
    int LOVSID;
    int LOASID;
    int LVASID;
    String Code;
    String Qualifikator;
    Timestamp gueltig_von;
    Timestamp gueltig_bis;
    char Referenz;
    char Zusatz;
    char Detail;
    char Ausstellung;
    char Gueltigkeitsende;
    char Wert;
    char Maﬂeinheit;
    String Bedeutung;

    public Bean(String code, String qualifikator, Timestamp gueltig_von, Timestamp gueltig_bis, char referenz, char zusatz, char detail, char ausstellung, char gueltigkeitsende, char wert, char maﬂeinheit, String bedeutung) {
        Code = code;
        Qualifikator = qualifikator;
        this.gueltig_von = gueltig_von;
        this.gueltig_bis = gueltig_bis;
        Referenz = referenz;
        Zusatz = zusatz;
        Detail = detail;
        Ausstellung = ausstellung;
        Gueltigkeitsende = gueltigkeitsende;
        Wert = wert;
        Maﬂeinheit = maﬂeinheit;
        Bedeutung = bedeutung;
    }

    public Bean() {
    }

    public int getLVVSID() {
        return LVVSID;
    }

    public void setLVVSID(int LVVSID) {
        this.LVVSID = LVVSID;
    }



    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getQualifikator() {
        return Qualifikator;
    }

    public void setQualifikator(String qualifikator) {
        Qualifikator = qualifikator;
    }

    public Timestamp getGueltig_von() {
        return gueltig_von;
    }

    public void setGueltig_von(Timestamp gueltig_von) {
        this.gueltig_von = gueltig_von;
    }

    public Timestamp getGueltig_bis() {
        return gueltig_bis;
    }

    public void setGueltig_bis(Timestamp gueltig_bis) {
        this.gueltig_bis = gueltig_bis;
    }

    public char getReferenz() {
        return Referenz;
    }

    public void setReferenz(char referenz) {
        Referenz = referenz;
    }

    public char getZusatz() {
        return Zusatz;
    }

    public void setZusatz(char zusatz) {
        Zusatz = zusatz;
    }

    public char getDetail() {
        return Detail;
    }

    public void setDetail(char detail) {
        Detail = detail;
    }

    public char getAusstellung() {
        return Ausstellung;
    }

    public void setAusstellung(char ausstellung) {
        Ausstellung = ausstellung;
    }

    public char getGueltigkeitsende() {
        return Gueltigkeitsende;
    }

    public void setGueltigkeitsende(char gueltigkeitsende) {
        Gueltigkeitsende = gueltigkeitsende;
    }

    public char getWert() {
        return Wert;
    }

    public void setWert(char wert) {
        Wert = wert;
    }

    public char getMaﬂeinheit() {
        return Maﬂeinheit;
    }

    public void setMaﬂeinheit(char maﬂeinheit) {
        Maﬂeinheit = maﬂeinheit;
    }

    public String getBedeutung() {
        return Bedeutung;
    }

    public void setBedeutung(String bedeutung) {
        Bedeutung = bedeutung;
    }

    public int getLOVSID() {
        return LOVSID;
    }

    public void setLOVSID(int LOVSID) {
        this.LOVSID = LOVSID;
    }

    public int getLOASID() {
        return LOASID;
    }

    public void setLOASID(int LOASID) {
        this.LOASID = LOASID;
    }

    public int getLVASID() {
        return LVASID;
    }

    public void setLVASID(int LVASID) {
        this.LVASID = LVASID;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "Code='" + Code + '\'' +
                ", Qualifikator='" + Qualifikator + '\'' +
                ", gueltig_von=" + gueltig_von +
                ", gueltig_bis=" + gueltig_bis +
                ", Referenz=" + Referenz +
                ", Zusatz=" + Zusatz +
                ", Detail=" + Detail +
                ", Ausstellung=" + Ausstellung +
                ", Gueltigkeitsende=" + Gueltigkeitsende +
                ", Wert=" + Wert +
                ", Maﬂeinheit=" + Maﬂeinheit +
                ", Bedeutung='" + Bedeutung + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bean)) return false;

        Bean bean = (Bean) o;
        if (Code != null ? !Code.equals(bean.Code) : bean.Code != null) return false;
        if (Ausstellung != '\0' && bean.getAusstellung() != '\0') {
            if (Ausstellung != (bean.Ausstellung)) {
                System.out.println("Ausstellung false :" + Ausstellung + "soll:" + bean.getAusstellung());
                return false;
            }
        }
        if (Zusatz != '\0' && bean.getZusatz() != '\0') {
            if (Zusatz != (bean.Zusatz)) {
                System.out.println("Zusatz false " + Zusatz + "soll:" + bean.getZusatz());
                return false;
            }
        }
        if (Maﬂeinheit != '\0' && bean.getMaﬂeinheit() != '\0') {
            if (Maﬂeinheit != (bean.Maﬂeinheit)) {
                System.out.println("Maﬂeinheit false" + Maﬂeinheit + "soll:" + bean.getMaﬂeinheit());
                return false;
            }
        }
        if (Wert != '\0' && bean.getWert() != '\0') {
            if (Wert != (bean.Wert)) {
                System.out.println("Wert false" + Wert + "soll:" + bean.getWert());
                return false;
            }
        }
        if (Referenz != '\0' && bean.getReferenz() != '\0') {
            if (Referenz != (bean.Referenz)) {
                System.out.println("Referenz false" + Referenz + "soll:" + bean.getReferenz());
                return false;
            }
        }
        if (Gueltigkeitsende != '\0' && bean.getGueltigkeitsende() != '\0') {
            if (Gueltigkeitsende != (bean.Gueltigkeitsende)) {
                System.out.println("Gueltigkeitsende false" + Gueltigkeitsende + "soll:" + bean.getGueltigkeitsende());
                return false;
            }
        }
        if (Detail != '\0' && bean.getDetail() != '\0') {
            if (Detail != (bean.Detail)) {
                System.out.println("Detail false" + Detail + "soll:" + bean.getDetail());
                return false;
            }
        }
        if (Bedeutung != null && bean.getBedeutung() != null) {
            if (!(Bedeutung.equals(bean.Bedeutung))) {
                System.out.println("Bedeutung false" + Code + "soll:" + bean.getCode());
                return false;
            }
        }


        return true;
    }

    @Override
    public int hashCode() {
        int result = Code != null ? Code.hashCode() : 0;
        return result;
    }
}
