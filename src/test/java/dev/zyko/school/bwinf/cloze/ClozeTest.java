package dev.zyko.school.bwinf.cloze;

public class ClozeTest {

    public static void main(String[] args) {
        Cloze cloze = new Cloze("__a _____n _______t _n _i___ _e_________ __s d__ _____e__ __d _i____ e__ __t___. _s _s_ __n_ ____e __n ______n ___e___, _i_ i_ _i_ ___h____ __________e __b_____ ___d__ s_____, _o __s_ s__ _i__ ___t___ _e________ _r_____. __________n _n_ _a_________ s____ e_____ _______b__ ___d ____n ______e___.",
                "Es in in so aus der die die ein ist Opa sie und und von dass eine eine sind einer Liste schon sowie einige findet Jürgen Rätsel sollen werden ergeben gegeben lustige Wörtern Apotheke blättert gebracht richtige Buchstaben Geschichte vorgegeben Leerzeichen Reihenfolge Satzzeichen Zeitschrift");
        ClozeSolver clozeSolver = new ClozeSolver(cloze);
        System.out.println(clozeSolver.solve());
    }

}
