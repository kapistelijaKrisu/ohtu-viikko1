package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void nollaVarasto() {
        assertEquals(0, new Varasto(-2).getTilavuus(), vertailuTarkkuus);
        assertEquals(0, new Varasto(0).getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaToStingi() {
        assertEquals("saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu(), varasto.toString());
        varasto.lisaaVarastoon(1.5);
        assertEquals("saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu(), varasto.toString());
        }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenNegatiivisella() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(-2);

        // varastossa pitäisi olla tilaa 10 - 8 eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void lisaysNegatiivisella() {
        varasto.lisaaVarastoon(-8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenEiVieAlleNollan() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(1123);

        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void lisaysEiVieYliMaksimin() {
        varasto.lisaaVarastoon(888);

        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void testFullKonstruktoriAlkuTilavuus() {
        assertEquals(0, new Varasto(0,0).getTilavuus(), vertailuTarkkuus);
        assertEquals(0, new Varasto(-10,0).getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void testFullKonstruktoriAlkuSaldo() {
        assertEquals(0, new Varasto(0,10).getTilavuus(), vertailuTarkkuus);
        assertEquals(0, new Varasto(-10,10).getTilavuus(), vertailuTarkkuus);
        
        assertEquals(5, new Varasto(5,-10).getTilavuus(), vertailuTarkkuus);
        assertEquals(10, new Varasto(10,-10).getTilavuus(), vertailuTarkkuus); 
        
        assertEquals(5, new Varasto(5,10).getTilavuus(), vertailuTarkkuus);
        assertEquals(10, new Varasto(10,10).getTilavuus(), vertailuTarkkuus);
        
        assertEquals(5, 2, vertailuTarkkuus);
        
    }
}