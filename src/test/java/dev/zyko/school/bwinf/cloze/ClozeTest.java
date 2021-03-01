package dev.zyko.school.bwinf.cloze;

public class ClozeTest {

    public static void main(String[] args) {
        Cloze cloze = new Cloze("__s __e___ _a___ e____ _____n_ _u_ _________ ____m__ ________, _a__ _r s___ _n _____m ___t _u ____m ___________ ______e___ _______e__.", "er in zu Als aus Bett fand sich einem eines Samsa Gregor seinem Morgens Träumen erwachte unruhigen Ungeziefer verwandelt ungeheueren");
        ClozeSolver clozeSolver = new ClozeSolver(cloze);
        System.out.println(clozeSolver.solve());
    }

}
