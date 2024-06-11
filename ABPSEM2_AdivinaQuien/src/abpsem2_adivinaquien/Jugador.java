/*
    Clase para la creación y manejo de atributos que tiene un jugador, orden principalmente.
 */
package abpsem2_adivinaquien;

public class Jugador {
    
    public String nombre;
    public boolean gane;
    public Personaje personajeJugador;
    public Personaje[][] TableroJugador;
    public Display displayJugador;
    
    public Jugador(String Nombre_Del_Jugador, Personaje Personaje_Del_Jugador, Display Display_Del_Jugador){
        this.nombre = Nombre_Del_Jugador;
        this.gane = false;
        this.personajeJugador = Personaje_Del_Jugador;
        this.displayJugador = Display_Del_Jugador;
        this.TableroJugador = this.displayJugador.personajes;
    }
}