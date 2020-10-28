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
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudenVarastonVirheellinenTilavuusNollataan() {
        varasto = new Varasto(-1);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudenKuormitetunVarastonVirheellinenTilavuusNollataan() {
        varasto = new Varasto(-1, 10);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudenKuormitetunVarastonVirheellinenSaldoNollataan() {
        varasto = new Varasto(10, -1);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudenVarastonSaldoOnAnnettuSaldoJosPienempiKuinTilavuus() {
        varasto = new Varasto(10, 1);
        assertEquals(1.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudenVarastonSaldoOnAnnettuTilavuusJosPienempiKuinSaldo() {
        varasto = new Varasto(1, 10);
        assertEquals(1.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-8);

        // saldon pitäisi olla sama kun alkuperäinen saldo
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

     @Test
    public void lisaysLisaaSaldoaVainSenVerranKunVarastoonMahtuu() {
        varasto.lisaaVarastoon(20);

        // saldon pitäisi olla sama kun tilavuus
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
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
    public void negatiivinenOttaminenPalauttaaNollan() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaKaikenJosYliSaldon() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(10);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringPalauttaaOkein() {
        
        assertTrue(varasto.toString().equals("saldo = 0.0, vielä tilaa 10.0"));
    }

}