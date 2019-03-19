/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebserge
 */
public class KassapaateTest {
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    Kassapaate paate;
    @Before
    public void setUp() {
        paate = new Kassapaate();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void kassassaAlussaSaldoTuhat() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kassassaAlussaLounaitaMyytyNolla() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    // käteisellä ostettu testit
    @Test
    public void edullisenOstoKateisellaToimii() {
        paate.syoEdullisesti(240);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstoKateisellaKympilla() {
        paate.syoEdullisesti(1000);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstoKateisellaLounaitaMyyty() {
        paate.syoEdullisesti(240);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaastiOstoKateisellaLounaitaMyyty() {
        paate.syoMaukkaasti(400);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void maukkaastiOstoKateisellaKympilla() {
        paate.syoMaukkaasti(1000);
        assertEquals(100400, paate.kassassaRahaa());
    }
    @Test
    public void maukkaastiOstoKateinenTasarahalla() {
        paate.syoMaukkaasti(1000);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void maukkaastiOstoLiianVahanRahaa() {
        paate.syoMaukkaasti(200);
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void edullisestiOstoLiianVahanRahaa() {
        paate.syoEdullisesti(10);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    // kortilla ostamistestit
    @Test
    public void kortillaEdullinenLounasTarpeeksiRahaa() {
        Maksukortti kortti = new Maksukortti(10000);
        assertTrue(paate.syoEdullisesti(kortti));
        assertEquals(10000-240, kortti.saldo());
    }
    
    @Test
    public void kortillaOstettuEdullinenMyytyjenLounasKasvaaYhdella() {
        Maksukortti kortti = new Maksukortti(10000);
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortillaMaukkaastiLounasTarpeeksiRahaa() {
        Maksukortti kortti = new Maksukortti(10000);
        assertTrue(paate.syoMaukkaasti(kortti));
        assertEquals(10000-400, kortti.saldo());
    }
    @Test
    public void kortillaOstettuMaukasMyytyjenLounasKasvaaYhdella() {
        Maksukortti kortti = new Maksukortti(10000);
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kortillaEiRahaaEdullinenLounasMyytyNolla() {
        Maksukortti kortti = new Maksukortti(100);

        assertFalse(paate.syoEdullisesti(kortti));
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(100, kortti.saldo());
    }
    @Test
    public void kortillaEiRahaaMaukasLounasMyytyNolla() {
        Maksukortti kortti = new Maksukortti(100);
        
        assertFalse(paate.syoMaukkaasti(kortti));
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void kassanRahaEiMuutuKortillaOstaessa() {
        Maksukortti kortti = new Maksukortti(240);
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    
    @Test
    public void kortilleLadatessaKassanRahamaaraMuuttuu() {
        Maksukortti kortti = new Maksukortti(100);
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa());
    }
    
    @Test
    public void kortilleLadataanNegatiivinenSaldoJaKaikkiPysyyEnnallaan() {
        Maksukortti kortti = new Maksukortti(10);
        paate.lataaRahaaKortille(kortti, -1);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(10, kortti.saldo());
    }
}
