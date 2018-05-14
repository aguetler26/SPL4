import javax.swing.JOptionPane;

public class Game {

	static int _zeilen = 3;
	static int _spalten = 3;
	static String[][] minenfeld = spielfeldAnlegen(_zeilen, _spalten, true);
	static String[][] spielfeld = spielfeldAnlegen(_zeilen, _spalten, false);
	static int mines = 0;
	static int versuche = 0;
	
	public static void main(String[] args) {
		boolean ende = false;
		while (ende == false) {
			spielfeldAnzeigen(false);
			int posZeile = eingeben("Bitte Zeile waehlen", _zeilen);
			int posSpalte = eingeben("Bitte Spalte waehlen", _spalten);
			ende = spielfeldPruefen(posZeile, posSpalte);
		}
	}
	
	public static boolean spielfeldPruefen(int zeile, int spalte) {
		if (minenfeld[zeile][spalte] == "[x]") {
			spielfeld[zeile][spalte] = "[X]";
			spielfeldAnzeigen(true);
			System.out.println("Bummm\nDu hast leider die Mine erwischt...");
			System.out.println("Game over!");
			System.out.println("Gueltige Versuche: " + versuche);
			return true;
		} else {
			spielfeld[zeile][spalte] = "[*]";
			versuche++;
			return false;
		}
	}
	
	public static int eingeben(String hinweis, int max) {
		max--;
		String eingabe;
		int pos;
		boolean ok = false;
		do {
			eingabe = JOptionPane.showInputDialog(hinweis + " (0.." + max + ")");
			pos = Integer.parseInt(eingabe);
			if (pos >= 0 && pos <= max) {
				ok = true;
			} else {
				System.out.println("Achtung: bitte nur einen Wert zwischen 0 und " + max + " eingeben!");
			}
		} while (ok == false);
		return pos;
	}
	
	public static void spielfeldAnzeigen(boolean showMines) {
		for (int z = 0; z < _zeilen; z++) {
			for (int s = 0; s < _spalten; s++) {
				if (showMines) {
					if (minenfeld[z][s] == "[x]") {
						System.out.print(minenfeld[z][s]);
					} else {
						System.out.print(spielfeld[z][s]);
					}
				} else {
					System.out.print(spielfeld[z][s]);
				}
			}
			System.out.println();
		}
		System.out.println("---------");
	}
	
	public static String[][] spielfeldAnlegen(int zeilen, int spalten, boolean mienenAnlegen) {
		String[][] mineField = new String[zeilen][spalten];
		for (int z = 0; z < zeilen; z++) {
			for (int s = 0; s < spalten; s++) {
				mineField[z][s] = "[ ]";
			}
		}
		if (mienenAnlegen) {
			mineField[(zeilen-1)][0] = "[x]";
			mineField[(zeilen-1)][(spalten-1)] = "[x]";
			mineField[0][(spalten-1)] = "[x]";
			mines = 3;
		}
		return mineField;
	}
	
	
}
