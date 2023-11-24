package core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Customer definition based on provided JSON file
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONCustomer {
    private String nip;
    private String nazwaPodmiotu;
    private String kodPocztowy;
    private String miejscowosc;
    private String ulica;
    private String nrBudynku;
    private String nrLokalu;

    public JSONCustomer(){
    }

    public JSONCustomer(String nip, String nazwaPodmiotu, String kodPocztowy, String miejscowosc, String ulica, String nrBudynku, String nrLokalu) {
        this.nip = nip;
        this.nazwaPodmiotu = nazwaPodmiotu;
        this.kodPocztowy = kodPocztowy;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrBudynku = nrBudynku;
        this.nrLokalu = nrLokalu;
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

    @Override
    public String toString() {
        return "JSONCustomer{" +
                "nip='" + nip + '\'' +
                ", nazwaPodmiotu='" + nazwaPodmiotu + '\'' +
                '}';
    }

    /**
     * Connects all address fields into single String
     * @return Customer address
     */
    public String getAddress(){
        return ulica + " " + nrBudynku + "\n" +
                kodPocztowy + ", " + miejscowosc;
    }
}
