package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortilleLadataanSaldoaEuro() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 11.0", kortti.toString());
    }
    
    @Test
    public void kortilleLadataanSaldoaEuroViisikymmentaKaksi() {
        kortti.lataaRahaa(152);
        assertEquals("saldo: 11.52", kortti.toString());
    }
    
    @Test
    public void korttiOtaRahaaVahentaaOikein() {
        kortti.otaRahaa(140);
        assertEquals("saldo: 8.60", kortti.toString());
    }
    @Test
    public void korttiOtaRahaaPalauttaaTrueJosSaldoaOn() {
        assertTrue(kortti.otaRahaa(140));
    }
    
    @Test
    public void korttiOtaRahaaLiikaaJosEiSaldoa() {
        kortti.otaRahaa(10001);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    @Test
    public void korttiOtaRahaaPalauttaaFalseJosEiSaldoa() {
        assertFalse(kortti.otaRahaa(10001));
    }
    @Test
    public void korttiGetSaldoPalauttaaOikeanSaldon() {
        assertEquals(1000, kortti.saldo());
    }
}
