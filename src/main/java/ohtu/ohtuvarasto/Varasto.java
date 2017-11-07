package ohtu.ohtuvarasto;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private double tilavuus;  // paljonko varastoon mahtuu,  > 0
    private double saldo;     // paljonko varastossa on nyt, >= 0

    // --- konstruktorit: ---
    public Varasto(final double alkuTilavuus) {  // tilavuus on annettava
        if (alkuTilavuus > 0.0) {
            this.tilavuus = alkuTilavuus;
        } else { // virheellinen, nollataan

            this.tilavuus = 0.0;  // => käyttökelvoton varasto
        }
        saldo = 0;     // oletus: varasto on tyhjä
    }

    // kuormitetaan
    public Varasto(final double alkuTilavuus, final double alkuSaldo) {
        this.tilavuus = Math.max(alkuTilavuus, 0);
        if (alkuSaldo < 0.0) {
            this.saldo = 0.0;
        } else {
            this.saldo = Math.min(tilavuus, saldo);  // täyteen ja ylimäärä hukkaan!
        }

    }

    // --- ottavat aksessorit eli getterit: ---
    public final double getSaldo() {
        return saldo;
    }

    public final double getTilavuus() {
        return tilavuus;
    }

    public final double paljonkoMahtuu() {  // huom: ominaisuus voidaan myös laskea
        return tilavuus - saldo;        //  ei tarvita erillistä kenttää vielaTilaa tms.
    }

    // --- asettavat aksessorit eli setterit: ---
    public void lisaaVarastoon(double maara) {
        if (maara < 0) { // virhetilanteessa voidaan tehdä 

            return;       // tällainen pikapoistuminenkin!
        }
        if (maara <= paljonkoMahtuu()) {// omia aksessoreita voi kutsua

            saldo = saldo + maara;          // ihan suoraan sellaisinaan
        } else {
            saldo = tilavuus;  // täyteen ja ylimäärä hukkaan!
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < 0) {// virhetilanteessa voidaan tehdä 

            return 0.0;   // tällainen pikapoistuminenkin!
        }
        if (maara > saldo) {          // annetaan mitä voidaan
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0;               // ja tyhjäksi menee
            return kaikkiMitaVoidaan;  // poistutaan saman tien
        }
        // jos tänne päästään, kaikki pyydetty voidaan antaa
        saldo = saldo - maara;  // vähennetään annettava saldosta
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    @Override
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}
