package core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONCustomer {
    private String nip;
    private String nazwaPodmiotu;
    private String kodPocztowy;
    private String powiat;
    private String gmina;
    private String miejscowosc;
    private String ulica;
    private String nrBudynku;
    private String nrLokalu;
    private String glownyKodPkd;
    private String statusDzialalnosci;
    private String dataRozpoczeciaDzialalnosci;

    public JSONCustomer(){
    }

    public JSONCustomer(String nip, String nazwaPodmiotu, String kodPocztowy, String powiat, String gmina, String miejscowosc, String ulica, String nrBudynku, String nrLokalu, String glownyKodPkd, String statusDzialalnosci, String dataRozpoczeciaDzialalnosci) {
        this.nip = nip;
        this.nazwaPodmiotu = nazwaPodmiotu;
        this.kodPocztowy = kodPocztowy;
        this.powiat = powiat;
        this.gmina = gmina;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrBudynku = nrBudynku;
        this.nrLokalu = nrLokalu;
        this.glownyKodPkd = glownyKodPkd;
        this.statusDzialalnosci = statusDzialalnosci;
        this.dataRozpoczeciaDzialalnosci = dataRozpoczeciaDzialalnosci;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNazwaPodmiotu() {
        return nazwaPodmiotu;
    }

    public void setNazwaPodmiotu(String nazwaPodmiotu) {
        this.nazwaPodmiotu = nazwaPodmiotu;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getPowiat() {
        return powiat;
    }

    public void setPowiat(String powiat) {
        this.powiat = powiat;
    }

    public String getGmina() {
        return gmina;
    }

    public void setGmina(String gmina) {
        this.gmina = gmina;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrBudynku() {
        return nrBudynku;
    }

    public void setNrBudynku(String nrBudynku) {
        this.nrBudynku = nrBudynku;
    }

    public String getNrLokalu() {
        return nrLokalu;
    }

    public void setNrLokalu(String nrLokalu) {
        this.nrLokalu = nrLokalu;
    }

    public String getGlownyKodPkd() {
        return glownyKodPkd;
    }

    public void setGlownyKodPkd(String glownyKodPkd) {
        this.glownyKodPkd = glownyKodPkd;
    }

    public String getStatusDzialalnosci() {
        return statusDzialalnosci;
    }

    public void setStatusDzialalnosci(String statusDzialalnosci) {
        this.statusDzialalnosci = statusDzialalnosci;
    }

    public String getDataRozpoczeciaDzialalnosci() {
        return dataRozpoczeciaDzialalnosci;
    }

    public void setDataRozpoczeciaDzialalnosci(String dataRozpoczeciaDzialalnosci) {
        this.dataRozpoczeciaDzialalnosci = dataRozpoczeciaDzialalnosci;
    }

    @Override
    public String toString() {
        return "JSONCustomer{" +
                "nip='" + nip + '\'' +
                ", nazwaPodmiotu='" + nazwaPodmiotu + '\'' +
                '}';
    }
}
