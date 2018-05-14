import javax.swing.*;

public class GameTest {

    static int _Zeilen  = 2;
    static int _Spalten = 2;
    static int mines = 2;

    static String[][] MinefieldMines = SpielfeldAnlegen(_Zeilen, _Spalten, true);
    static String[][] MinefieldUser = SpielfeldAnlegen(_Zeilen, _Spalten, false);

    static int versuche = 0;

    public static void main(String[] args) {


        boolean Schleife = true;

        while(Schleife==true) {

            SpielfeldAnzeigen(MinefieldUser, _Zeilen, _Spalten);

            int eingZeile= eingeben("Bitte Zeile w�hlen: ");
            int eingSpalte = eingeben("Bitte Spalte eingeben: ");

            Schleife = VersuchePr�fen(versuche, _Zeilen, _Spalten, mines);

            Schleife = SpielfeldPr�fen(eingZeile,eingSpalte);


        }

        System.out.println("GAME OVER");




    }

    public static String[][] SpielfeldAnlegen(int Zeilen, int Spalten, boolean Minen) {


        for(int z = 0; z < Zeilen; z++) {

            for(int s = 0; s < Spalten; s++) {

                MinefieldUser[z][s] = "[ ]";
                MinefieldMines[z][s] = "[ ]";

            }

        }

        if(Minen ==  true) {

            while(mines > 0) {
                int zeileMine = (int) (Math.random() * Zeilen);
                int spalteMine = (int) (Math.random() * Spalten);

                MinefieldMines[zeileMine][spalteMine] = "[x]";

                mines--;
            }
        }
        return MinefieldMines;
    }




    public static void SpielfeldAnzeigen(String[][] MinefieldUser, int Zeilen, int Spalten) {

        for(int z = 0; z < Zeilen; z++) {

            for(int s = 0; s < Spalten; s++) {

                System.out.print(MinefieldUser[z][s]);

            }

            System.out.println();

        }

        System.out.println("_________");

    }




    public static int eingeben(String hinweis) {

        String eingabe = JOptionPane.showInputDialog(hinweis);

        return Integer.parseInt(eingabe);

    }




    public static boolean SpielfeldPr�fen(int Zeile, int Spalte) {

        if (MinefieldMines[Zeile][Spalte] == "[x]") {

            SpielfeldAnzeigen(MinefieldUser, _Zeilen, _Spalten);

            System.out.println("Bumm -  du hast eine Mine erwischt!");

            MinefieldUser[Zeile][Spalte] = "[x]";


            versuche++;

            return false;
        } else {

            SpielfeldAnzeigen(MinefieldUser, _Zeilen, _Spalten);

            System.out.println("Du hast keine Mine erwischt!");

            MinefieldUser[Zeile][Spalte] = "[-]";

            versuche++;

            return true;
        }

    }



    public static boolean VersuchePr�fen(int Versuche, int Zeilen, int Spalten, int Minen) {

        int maxVersuche = (Zeilen*Spalten) - Minen;

        boolean Pr�fung = true;

        if (Versuche >= maxVersuche) {

            Pr�fung = false;

        }

        return Pr�fung;
    }





}