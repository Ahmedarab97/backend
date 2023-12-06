package nl.penguins.learnditwin.plaats.domain.ids;

import nl.penguins.learnditwin.plaats.exception.OngeldigeRegioCodeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegioCodeTest {
    @Test
    void nietValideRegioCode() {
        assertThrows(OngeldigeRegioCodeException.class, () -> {
            new RegioCode("ongeldigeCode");
        });
    }

    @Test
    void valideBuurtCode() {
        assertDoesNotThrow(() -> new RegioCode("BU03562500"));
    }

    @Test
    void valideWijkCode() {
        assertDoesNotThrow(() -> new RegioCode("WK035625"));

    }

    @Test
    void valideGemeenteCode() {
        assertDoesNotThrow(() -> new RegioCode("GM0356"));
    }

    @Test
    void isGemeente() {
        RegioCode regioCode = new RegioCode("GM0356");
        assertTrue(regioCode.isGemeente());
    }

    @Test
    void wijkIsNietGemeente() {
        RegioCode regioCode = new RegioCode("WK035625");
        assertFalse(regioCode.isGemeente());
    }

    @Test
    void buurtIsNietGemeente() {
        RegioCode regioCode = new RegioCode("BU03562500");
        assertFalse(regioCode.isGemeente());
    }

    @Test
    void gemeenteIsNietBuurt() {
        RegioCode regioCode = new RegioCode("GM0356");
        assertFalse(regioCode.isBuurt());
    }

    @Test
    void wijkIsNietBuurt() {
        RegioCode regioCode = new RegioCode("WK035625");
        assertFalse(regioCode.isBuurt());
    }

    @Test
    void isBuurt() {
        RegioCode regioCode = new RegioCode("BU03562500");
        assertTrue(regioCode.isBuurt());
    }

    @Test
    void gemeenteIsNietWijk() {
        RegioCode regioCode = new RegioCode("GM0356");
        assertFalse(regioCode.isWijk());
    }

    @Test
    void isWijk() {
        RegioCode regioCode = new RegioCode("WK035625");
        assertTrue(regioCode.isWijk());
    }

    @Test
    void buurtIsNietWijk() {
        RegioCode regioCode = new RegioCode("BU03562500");
        assertFalse(regioCode.isWijk());
    }

    @Test
    void getGemeenteKarakters() {
        RegioCode regioCode = new RegioCode("GM0356");
        assertEquals("GM", regioCode.getRegioKarakters());
    }

    @Test
    void getWijkKarakters() {
        RegioCode regioCode = new RegioCode("WK035625");
        assertEquals("WK", regioCode.getRegioKarakters());
    }

    @Test
    void getBuurtKarakters() {
        RegioCode regioCode = new RegioCode("BU03562500");
        assertEquals("BU", regioCode.getRegioKarakters());
    }
}