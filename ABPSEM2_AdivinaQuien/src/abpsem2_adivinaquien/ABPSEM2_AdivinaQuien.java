package abpsem2_adivinaquien;

import javax.swing.JOptionPane;

/*
    Clase principal de aplicación ADIVINA QUIEN
    
    FORMATO PARA INSTANCIA DE OBJETOS PERSONAJE:

        Nombre: Cadena de caracteres que contiene nombre del personaje, nada especial

        Sexo: Caracter, puede ser 'F' o 'M', 'F' se almacena como un booleano = true, cualquier otro será almacenado como false

        Situacion de Cabello:
        'L': Representa pelo largo
        'R': Representa pelo corto
        'C': Representa calvicie o que el personaje es calvo
        Cualquier otro caracter será tomado como personaje sin cabello.

        Accesorios: Pueden asumir los siguientes valores según el caracter
        'L': Lentes
        'B': Boina
        'A': Aretes
        'G': Gorra
        'S': Sombrero
        Cualquier otro caracter no será tomado en cuenta.

        Vello Facial: Puede tener los siguientes valores según el caracter
        'A': Barba
        'B': Bigote
        'C': Barba y bigote
        Cualquier otro caracter no será tomado en cuenta.

        Color de piel: Se usan booleanos, en los que true es piel blanca y false es piel oscura

        Color de ojos: Se ingresa el caracter 'A' para representar azul, como solo hay ojos marrones y azules este se almacena
        mediante un booleano, en el cual false es marrón y true es azul

        COLORES EN GENERAL, para los colores se utilizaro,M,MMNNN   NM M,n caracteres que representan cada color posible, cabe aclarar
        que el atributo de color de cabello se comparte con el color de vello, en caso de que el personaje cuente con vello facial
        a continuación la lista de los colores que representa cada caracter:

        'P': Representa pelirrojo
        'B': Representa blanco
        'C': Representa castaño
        'N': Representa negro
        'R': Representa rubio

    Solo en caso de los ojos se usa 'A' como representación del azul.
*/

public class ABPSEM2_AdivinaQuien {

    public static void main(String[] args) {

        Personaje Tablero_De_Personajes[][] = new Personaje[4][6];

        Tablero_De_Personajes[0][0] = new Personaje("Raquel", 'F', 'L', 'C', "S", 'Z', true, 'M');
        Tablero_De_Personajes[0][1] = new Personaje("Sergio", 'M', 'R', 'B', "", 'A', true, 'M');
        Tablero_De_Personajes[0][2] = new Personaje("Nico", 'M', 'C', 'C', "L", 'Z', true, 'A');
        Tablero_De_Personajes[0][3] = new Personaje("Maria", 'F', 'L', 'P', "B", 'Z', true, 'M');
        Tablero_De_Personajes[0][4] = new Personaje("Carlos", 'M', 'R', 'R', "", 'B', true, 'M');
        Tablero_De_Personajes[0][5] = new Personaje("Oscar", 'M', 'R', 'B', "", 'B', true, 'M');

        Tablero_De_Personajes[1][0] = new Personaje("Emilia", 'F', 'L', 'B', "LA", 'Z', true, 'A');
        Tablero_De_Personajes[1][1] = new Personaje("Antonio", 'M', 'R', 'R', "", 'Z', true, 'A');
        Tablero_De_Personajes[1][2] = new Personaje("Alex", 'M', 'Z', 'Z', "L", 'Z', true, 'M');
        Tablero_De_Personajes[1][3] = new Personaje("Sara", 'F', 'L', 'N', "LA", 'Z', false, 'M');
        Tablero_De_Personajes[1][4] = new Personaje("Hugo", 'M', 'R', 'C', "", 'B', true, 'M');
        Tablero_De_Personajes[1][5] = new Personaje("Ana", 'F', 'L', 'R', "A", 'Z', true, 'A');

        Tablero_De_Personajes[2][0] = new Personaje("Eric", 'M', 'R', 'B', "", 'Z', true, 'M');
        Tablero_De_Personajes[2][1] = new Personaje("Leo", 'M', 'R', 'R', "G", 'Z', true, 'M');
        Tablero_De_Personajes[2][2] = new Personaje("Rudy", 'M', 'R', 'R', "", 'C', true, 'M');
        Tablero_De_Personajes[2][3] = new Personaje("Cris", 'M', 'R', 'C', "", 'B', false, 'M');
        Tablero_De_Personajes[2][4] = new Personaje("Rich", 'M', 'R', 'N', "S", 'Z', true, 'M');
        Tablero_De_Personajes[2][5] = new Personaje("Daniel", 'M', 'R', 'N', "S", 'Z', false, 'M');

        Tablero_De_Personajes[3][0] = new Personaje("Andres", 'M', 'R', 'N', "", 'A', false, 'M');
        Tablero_De_Personajes[3][1] = new Personaje("Beto", 'M', 'Z', 'Z', "", 'A', false, 'M');
        Tablero_De_Personajes[3][2] = new Personaje("David", 'M', 'Z', 'Z', "", 'C', true, 'M');
        Tablero_De_Personajes[3][3] = new Personaje("Memo", 'M', 'C', 'P', "", 'Z', true, 'M');
        Tablero_De_Personajes[3][4] = new Personaje("Luis", 'M', 'R', 'P', "L", 'Z', true, 'A');
        Tablero_De_Personajes[3][5] = new Personaje("Jesus", 'M', 'R', 'N', "", 'Z', true, 'M');
        
        Personaje Tablero_De_Personajes2[][] = new Personaje[4][6]; //SE TUVO QUE DUPLICAR POR ERRORES CON LOS TABLEROS

        Tablero_De_Personajes2[0][0] = new Personaje("Raquel", 'F', 'L', 'C', "S", 'Z', true, 'M');
        Tablero_De_Personajes2[0][1] = new Personaje("Sergio", 'M', 'R', 'B', "", 'A', true, 'M');
        Tablero_De_Personajes2[0][2] = new Personaje("Nico", 'M', 'C', 'C', "L", 'Z', true, 'A');
        Tablero_De_Personajes2[0][3] = new Personaje("Maria", 'F', 'L', 'P', "B", 'Z', true, 'M');
        Tablero_De_Personajes2[0][4] = new Personaje("Carlos", 'M', 'R', 'R', "", 'B', true, 'M');
        Tablero_De_Personajes2[0][5] = new Personaje("Oscar", 'M', 'R', 'B', "", 'B', true, 'M');

        Tablero_De_Personajes2[1][0] = new Personaje("Emilia", 'F', 'L', 'B', "LA", 'Z', true, 'A');
        Tablero_De_Personajes2[1][1] = new Personaje("Antonio", 'M', 'R', 'R', "", 'Z', true, 'A');
        Tablero_De_Personajes2[1][2] = new Personaje("Alex", 'M', 'Z', 'Z', "L", 'Z', true, 'M');
        Tablero_De_Personajes2[1][3] = new Personaje("Sara", 'F', 'L', 'N', "LA", 'Z', false, 'M');
        Tablero_De_Personajes2[1][4] = new Personaje("Hugo", 'M', 'R', 'C', "", 'B', true, 'M');
        Tablero_De_Personajes2[1][5] = new Personaje("Ana", 'F', 'L', 'R', "A", 'Z', true, 'A');

        Tablero_De_Personajes2[2][0] = new Personaje("Eric", 'M', 'R', 'B', "", 'Z', true, 'M');
        Tablero_De_Personajes2[2][1] = new Personaje("Leo", 'M', 'R', 'R', "G", 'Z', true, 'M');
        Tablero_De_Personajes2[2][2] = new Personaje("Rudy", 'M', 'R', 'R', "", 'C', true, 'M');
        Tablero_De_Personajes2[2][3] = new Personaje("Cris", 'M', 'R', 'C', "", 'B', false, 'M');
        Tablero_De_Personajes2[2][4] = new Personaje("Rich", 'M', 'R', 'N', "S", 'Z', true, 'M');
        Tablero_De_Personajes2[2][5] = new Personaje("Daniel", 'M', 'R', 'N', "S", 'Z', false, 'M');

        Tablero_De_Personajes2[3][0] = new Personaje("Andres", 'M', 'R', 'N', "", 'A', false, 'M');
        Tablero_De_Personajes2[3][1] = new Personaje("Beto", 'M', 'Z', 'Z', "", 'A', false, 'M');
        Tablero_De_Personajes2[3][2] = new Personaje("David", 'M', 'Z', 'Z', "", 'C', true, 'M');
        Tablero_De_Personajes2[3][3] = new Personaje("Memo", 'M', 'C', 'P', "", 'Z', true, 'M');
        Tablero_De_Personajes2[3][4] = new Personaje("Luis", 'M', 'R', 'P', "L", 'Z', true, 'A');
        Tablero_De_Personajes2[3][5] = new Personaje("Jesus", 'M', 'R', 'N', "", 'Z', true, 'M');

        do{
            for(byte i = 0; i < Tablero_De_Personajes.length; i++){
                for(byte j = 0; j < Tablero_De_Personajes[0].length; j++){
                    Tablero_De_Personajes[i][j].descartado = false;
                    Tablero_De_Personajes2[i][j].descartado = false;                    
                }
            }
            System.out.println("\33[32mBIENVENIDO A ADIVINA QUIEN!!\33[30m");

            byte fila_jg1 = (byte) (Math.random() * 4), columna_jg1 = (byte) (Math.random() * 6); //Indice de personaje para el jugador 1
            byte fila_jg2 = (byte) (Math.random() * 4), columna_jg2 = (byte) (Math.random() * 6); //Indice de personaje para el jugador 2

            while (fila_jg1 == fila_jg2 && columna_jg1 == columna_jg2) {
                fila_jg2 = (byte) (Math.random() * 4);
                columna_jg2 = (byte) (Math.random() * 6);
            }

            Display Tablero = new Display(Tablero_De_Personajes);
            Display Tablero2 = new Display(Tablero_De_Personajes2);
            
            String NombreJugador = JOptionPane.showInputDialog(null, "Jugador 1, ingrese su nombre:", "", 1);
            while(NombreJugador == null || NombreJugador.isBlank()){
                if(JOptionPane.showConfirmDialog(null, "Su nombre no fue ingresado, desea llamarse 'Jugador 1'?", "Nombre no decidido", 0) == 0){
                    NombreJugador = "Jugador 1";
                    break;
                }else{
                    NombreJugador = JOptionPane.showInputDialog(null, "Jugador 1, ingrese su nombre:", "", 1);
                }
            }
            Jugador Jugador1 = new Jugador(NombreJugador, Tablero.personajes[fila_jg1][columna_jg1], Tablero);
            PanelLog Panel1 = new PanelLog(Jugador1, Tablero.personajes[fila_jg2][columna_jg2]);
            System.out.println("Jugador 1: \33[35m"+Jugador1.nombre+"\33[30m");
            Panel1.darPersonaje();
            
            NombreJugador = JOptionPane.showInputDialog(null, "Jugador 2, ingrese su nombre:", "", 1);
            while(NombreJugador == null || NombreJugador.isBlank()){
                if(JOptionPane.showConfirmDialog(null, "Su nombre no fue ingresado, desea llamarse 'Jugador 2'?", "Nombre no decidido", 0) == 0){
                    NombreJugador = "Jugador 2";
                    break;
                }else{
                    NombreJugador = JOptionPane.showInputDialog(null, "Jugador 2, ingrese su nombre:", "", 1);
                }
            }
            Jugador Jugador2 = new Jugador(NombreJugador, Tablero2.personajes[fila_jg2][columna_jg2], Tablero2);
            PanelLog Panel2 = new PanelLog(Jugador2, Tablero2.personajes[fila_jg1][columna_jg1]);
            System.out.println("Jugador 2: \33[35m"+Jugador2.nombre+"\33[30m");
            Panel2.darPersonaje();
            
            boolean Jugador1Adivinar = false, Jugador2Adivinar = false, permitirAdivinar = false;
            byte ronda = 1;
            String ext = "";
            
            while (!Jugador1Adivinar || !Jugador2Adivinar) { //MIENTRAS NINGUNO ADIVINE
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("Ronda #\33[34m"+ronda+"\33[30m.");
                System.out.println("--------------------------------------------------------------------------------------------------");
                if(ronda == 3){
                    ext = "\n---- Adivinar  ----";
                    permitirAdivinar = true;
                    System.out.println("Los jugadores ya pueden adivinar!");
                }
                Jugador1Adivinar = Panel1.evaluarOpcion(permitirAdivinar);
                if (Jugador1Adivinar) {
                    JOptionPane.showMessageDialog(null, Jugador1.nombre + " intentó adivinar, " + Jugador2.nombre + " deberías adivinar también", "Ojito :OOOOO", 1);
                    System.out.println("--------------------------------------------------------------------------------------------------");
                    Panel2.evaluarOpcion(true);
                    break;
                }
                System.out.println("--------------------------------------------------------------------------------------------------");
                Jugador2Adivinar = Panel2.evaluarOpcion(permitirAdivinar);
                if (Jugador2Adivinar) {
                    JOptionPane.showMessageDialog(null, Jugador2.nombre + " intentó adivinar, " + Jugador1.nombre + " deberías adivinar también", "Ojito :OOOOO", 1);
                    System.out.println("--------------------------------------------------------------------------------------------------");
                    Panel1.evaluarOpcion(true);
                    break;
                }
                System.out.println();
                ronda++;
            }

            System.out.println("Personaje de \33[34m"+Jugador1.nombre+"\33[30m: "+Jugador1.personajeJugador.nombre);
            System.out.println("Personaje de \33[34m"+Jugador2.nombre+"\33[30m: "+Jugador2.personajeJugador.nombre+'\n');
            
            if (Panel1.Jugador.gane && Panel2.Jugador.gane) { //AMBOS JUGADOREs GANAN = EMPATE
                JOptionPane.showMessageDialog(null, Jugador1.nombre + " y " + Jugador2.nombre + " han adivinado correctamente ", " ES UN EMPATE!", 1);
            }else if (Panel1.Jugador.gane && !Panel2.Jugador.gane) { //PIERDE JUGADOR 2
                JOptionPane.showMessageDialog(null, "Felicidades " + Jugador1.nombre + "!, has ganado la partida. \nLo siento " + Jugador2.nombre + "... has perdido la partida ", " FIN DEL JUEGO", 1);
            }else if (!Panel1.Jugador.gane && Panel2.Jugador.gane) { //PIERDE JUGADOR 1
                JOptionPane.showMessageDialog(null, "Lo siento " + Jugador1.nombre + "... has perdido la partida. \nFelicidades " + Jugador2.nombre + "! has ganado la partida ", " FIN DEL JUEGO", 1);
            }else if(!Panel1.Jugador.gane && !Panel2.Jugador.gane){ //AMBOS JUGADORES PIERDEN 
                JOptionPane.showMessageDialog(null, "\n" + Jugador1.nombre + " y " + Jugador2.nombre + " han adivinado incorrectamente ", " AMBOS JUGADORES HAN PERDIDO LA PARTIDA!", 1);
            }
            
        }while(JOptionPane.showConfirmDialog(null, "Quieren volver a jugar?", "Continuar?", 0) == 0);
    }
}