package config;

import com.google.gson.annotations.SerializedName;

public class Instance {
    @SerializedName("LOCATION")
    private String LOCATION;

    @SerializedName("IMAGE-BASE64")
    private boolean IMAGE;

    @SerializedName("ONE-LINE")
    private boolean ONELINE;

    @SerializedName("CSS-OBF")
    private boolean CSSOBF;

    @SerializedName("DEBUG")
    private boolean DEBUG;

    @SerializedName("CSS-TRASHCODE")
    private boolean CSSTRASHCODE;

    @SerializedName("CSS-TRASHCODE-SIZE")
    private long CSSTRASHCODESIZE;

    @SerializedName("VIEWSOURCEBLOCKER")
    private boolean VIEWSOURCEBLOCKER;

    @SerializedName("OBFUSCATION-STRINGS")
    private String OBFStrings;


    public Instance() {
        this.LOCATION = "index.html";
        this.IMAGE = true;
        this.ONELINE = true;
        this.CSSOBF = true;
        this.DEBUG = false;
        this.OBFStrings = "Ilﻓﺵﺵﺷﺷﺻﺿﺦ﷼﷽ﯸﯷﯲﷺﷻ﷼ﺄﱡﯯﯮ";
        this.CSSTRASHCODE = true;
        this.CSSTRASHCODESIZE = 150;
        this.VIEWSOURCEBLOCKER = true;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public boolean isIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(boolean IMAGE) {
        this.IMAGE = IMAGE;
    }

    public boolean isONELINE() {
        return ONELINE;
    }

    public void setONELINE(boolean ONELINE) {
        this.ONELINE = ONELINE;
    }

    public boolean isCSSOBF() {
        return CSSOBF;
    }

    public void setCSSOBF(boolean CSSOBF) {
        this.CSSOBF = CSSOBF;
    }

    public boolean isDEBUG() {
        return DEBUG;
    }

    public void setDEBUG(boolean DEBUG) {
        this.DEBUG = DEBUG;
    }

    public String getOBFStrings() {
        return OBFStrings;
    }

    public void setOBFStrings(String OBFStrings) {
        this.OBFStrings = OBFStrings;
    }

    public boolean isCSSTRASHCODE() {
        return CSSTRASHCODE;
    }

    public void setCSSTRASHCODE(boolean CSSTRASHCODE) {
        this.CSSTRASHCODE = CSSTRASHCODE;
    }

    public long getCSSTRASHCODESIZE() {
        return CSSTRASHCODESIZE;
    }

    public void setCSSTRASHCODESIZE(long CSSTRASHCODESIZE) {
        this.CSSTRASHCODESIZE = CSSTRASHCODESIZE;
    }

    public boolean isVIEWSOURCEBLOCKER() {
        return VIEWSOURCEBLOCKER;
    }

    public void setVIEWSOURCEBLOCKER(boolean VIEWSOURCEBLOCKER) {
        this.VIEWSOURCEBLOCKER = VIEWSOURCEBLOCKER;
    }
}
