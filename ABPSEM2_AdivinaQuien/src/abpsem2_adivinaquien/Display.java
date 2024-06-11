/*
    Clase de Java para mostrar Tablero y demás diseños de consola
 */
package abpsem2_adivinaquien;

public class Display {
    public char separador;
    public String paredes;
    public Personaje[][] personajes;

    public Display(Personaje Tablero_De_Personajes[][]) { //ATRIBUTO OBLIGATORIO: Tablero con nombres de personajes
        this.personajes = Tablero_De_Personajes;
        this.separador = '-';
        this.paredes = "||";
    }

    public void mostrarTablero() {
        for (byte i = 0; i < this.personajes.length; i++) {
            System.out.print(this.paredes);
            for (byte j = 0; j < this.personajes[0].length; j++) {
                if (this.personajes[i][j].descartado) {
                    System.out.print("\t" + "#####" + "\t" + this.separador);
                } else {
                    System.out.print("\t" + this.personajes[i][j].nombre + "\t" + this.separador);
                }
            }
            System.out.println("\b\b\t" + this.paredes);
        }
    }
}