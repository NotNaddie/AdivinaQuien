/*
    Clase de Java para acciones con JOptionPane y manejo de datos con este mismo
 */
package abpsem2_adivinaquien;

import javax.swing.JOptionPane;

public class PanelLog {
    
    public Jugador Jugador;
    public Personaje Personaje_Del_Contrario;
    
    public PanelLog(Jugador Jugador, Personaje Personaje_Del_Contrario){
        this.Jugador = Jugador;
        this.Personaje_Del_Contrario = Personaje_Del_Contrario;
    }
    
    public void darPersonaje(){
        System.out.println("Dandole personaje a "+this.Jugador.nombre+"...\n");
        boolean resB = JOptionPane.showConfirmDialog(null, this.Jugador.nombre+", tu personaje es "+this.Jugador.personajeJugador.nombre+". ¿Desea ver su descripción?", "Personaje de "+this.Jugador.nombre , 0) == 0;
        if(resB){
            JOptionPane.showMessageDialog(null, this.Jugador.personajeJugador.descripcion()+"\n\nHACER CLICK EN OK PARA CONTINUAR", "Personaje de "+this.Jugador.nombre, 1);
        }
    }
    
    public boolean evaluarOpcion(boolean permitirAdivinar){
        boolean retorno = false;
        System.out.println("\n\33[31mTURNO DE "+this.Jugador.nombre.toUpperCase()+"\33[30m");
        System.out.println("Evaluando lo que "+this.Jugador.nombre+" quiere hacer...\n");
        if(permitirAdivinar){
            System.out.println("\33[35m[LISTA DE ACCIONES]\33[30m\n---- Preguntar ----\n---- Adivinar  ----\n---- Descartar ----\n---- Describir ----\n");
        }else{
            System.out.println("\33[35m[LISTA DE ACCIONES]\33[30m\n---- Preguntar ----\n---- Descartar ----\n---- Describir ----\n");
        }
        System.out.println("\n\33[34mTablero actual de "+this.Jugador.nombre+":\33[30m");
        this.Jugador.displayJugador.mostrarTablero();
        System.out.println();
        String res = "", noDetectado = "";
        byte tipoDeMensaje = 1;
        while (!res.contains("pregunta") || !res.contains("adivinar") || !res.contains("descartar") || !res.contains("describir")){
            res = JOptionPane.showInputDialog(null, noDetectado+this.Jugador.nombre+" es tu turno,\nelije lo que quieres hacer:", "Selecciona una opción", tipoDeMensaje);
            if(res == null){
                res = "0";
            }
            res = res.toLowerCase();
            
            if(res.contains("pregunta")){
                String pregunta = JOptionPane.showInputDialog(null,this.Jugador.nombre+", ingresar pregunta:", "Pregunta de "+this.Jugador.nombre, 3);
                boolean procesoExitoso = detectarPreguntas(pregunta);
                while(!procesoExitoso){
                    pregunta = JOptionPane.showInputDialog(null,this.Jugador.nombre+", ingresar pregunta:\n(Recuerda que la pregunta debe contener 'tu personaje' y terminar con un signo de interrogación '?')", "Pregunta "+this.Jugador.nombre, 2);
                    procesoExitoso = detectarPreguntas(pregunta);
                }
                break;
            }
            
            else if(res.contains("adivinar") && permitirAdivinar){
                retorno = true;
                String input = JOptionPane.showInputDialog(null, "El personaje es: ", "Adivinando personaje...", 3);
                while(input == null){
                    JOptionPane.showMessageDialog(null, "NO SE INGRESÓ PERSONAJE", "Ingresar personaje...", 0);
                    input = JOptionPane.showInputDialog(null, "El personaje es: ", "Adivinando personaje...", 3);
                }
                input = input.toLowerCase();
                if(input.contains(this.Personaje_Del_Contrario.nombre.toLowerCase())){
                    this.Jugador.gane = true;
                }
                break;
            }
            
            else if(res.contains("descartar")){
                if(!(JOptionPane.showConfirmDialog(null, this.Jugador.nombre+", seguro que quieres descartar manualmente?\nCuando un personaje se descarte, no se podrá regresar a su estado original!", "Estás segur@?", 0) == 0)){
                    continue;
                }
                String input;
                boolean continuar = true;
                boolean completo = true;
                while(continuar){
                    input = JOptionPane.showInputDialog(null, this.Jugador.nombre+", ingresar órden para descartar:");
                    while(input == null){
                        JOptionPane.showMessageDialog(null, "NO SE INGRESÓ ORDEN", "Ingresar orden.", 0);
                        input = JOptionPane.showInputDialog(null, this.Jugador.nombre+", ingresar órden para descartar:");
                    }
                    input = input.toLowerCase();
                    if(input.contains("descartar")){
                        if(input.contains("piel oscura")){
                            if(!this.descartarColorPiel("oscura", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("piel blanca")){
                            if(!this.descartarColorPiel("blanca", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("mujeres")){
                            if(!this.descartarSexo("mujer", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("hombres")){
                            if(!this.descartarSexo("hombre", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("sin cabello")){
                            if(!this.descartarCabello("sin cabello", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("calvos")){
                            if(!this.descartarCabello("calvo", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("cabello largo")){
                            if(!this.descartarCabello("largo", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("cabello corto")){
                            if(!this.descartarCabello("corto", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("ojos azules")){
                            if(!this.descartarColorOjos("azules", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("ojos marrones")){
                            if(!this.descartarColorOjos("marrones", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("sin accesorios")){
                            if(!this.descartarAccesorios((byte)0, true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("un solo accesorio")){
                            if(!this.descartarAccesorios((byte)1, true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("dos accesorios")){
                            if(!this.descartarAccesorios((byte)2, true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("barba")){
                            if(!this.descartarVello("barba", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("bigote")){
                            if(!this.descartarVello("bigote", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("barba y bigote")){
                            if(!this.descartarVello("barba y bigote", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("sin vello")){
                            if(!this.descartarVello("no tiene vello facial", true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("lentes")){
                            if(!this.descartarAccesorios('L', true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("aretes")){
                            if(!this.descartarAccesorios('A', true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("boina")){
                            if(!this.descartarAccesorios('B', true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("gorro")){
                            if(!this.descartarAccesorios('G', true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else if(input.contains("sombrero")){
                            if(!this.descartarAccesorios('S', true)){
                                boolean descarteManual = JOptionPane.showConfirmDialog(null, "Quiere realizar descarte manual?", "Error", 0) == 0;
                                while(descarteManual){
                                    String personaje = JOptionPane.showInputDialog(null, "Ingresar nombre del personaje a descartar: ", "Descartando personaje...", 1);
                                    this.descartar(personaje);
                                    descarteManual = JOptionPane.showConfirmDialog(null, "Quiere continuar el descarte manual?", "Opción", 0) == 0;
                                }
                            }
                        }else{
                            if(input.contains("descartar a")){
                                completo = false;
                                for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
                                    for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                                        if(input.contains(this.Jugador.TableroJugador[i][j].nombre.toLowerCase())){
                                            if(this.Jugador.TableroJugador[i][j].descartado){
                                                JOptionPane.showMessageDialog(null, "EL PERSONAJE YA FUE DESCARTADO", this.Jugador.nombre+", su personaje a se descartó...", 0);
                                            }else{
                                                this.Jugador.TableroJugador[i][j].descartado = true;
                                                completo = true;
                                            }
                                        }
                                    }
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "LA ORDEN NO ES VÁLIDA", "La respuesta no es válida...", 0);
                                continue;
                            }
                        }
                        if(!completo){
                            continue;
                        }
                        System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m");
                        this.Jugador.displayJugador.mostrarTablero();
                        continuar = JOptionPane.showConfirmDialog(null, "Quiere descartar a alguien más?", "Continuar?", 1) == 0;
                    }else{
                        JOptionPane.showMessageDialog(null, "NO CONTIENE LA PALABRA 'descartar'", "La respuesta no es válida...", 0);
                    }
                }
                break;
            }
            
            else if(res.contains("describir") || res.contains("descripcion") || res.contains("descripción")){
                do{
                    String per = JOptionPane.showInputDialog(null, this.Jugador.nombre+", ingresar nombre del personaje:");
                    boolean procesoExitoso = this.descripciones(per);
                    while(!procesoExitoso){
                        per = JOptionPane.showInputDialog(null, this.Jugador.nombre+", ingresar nombre del personaje de nuevo:");
                        procesoExitoso = this.descripciones(per);
                    }
                }while(JOptionPane.showConfirmDialog(null, "Quieres otra descripción?", "Continuar descripciones?", 0) == 0);
                noDetectado = "";
                tipoDeMensaje = 1;
                continue;
            }
            noDetectado = "OPCION NO VÁLIDA\n";
            tipoDeMensaje = 0;
        }
        return retorno;
    }
    
    public boolean detectarPreguntas(String pregunta){
        if(pregunta == null){
            pregunta = "0";
        }
        pregunta = (pregunta.trim()).toLowerCase();
        if(pregunta.endsWith("?") && pregunta.contains("tu personaje")){
            String respuesta = "no";
            pregunta = pregunta.substring(0, pregunta.length()-1);
            
            boolean preguntaColorPiel = pregunta.contains("piel");
            boolean preguntaColorOjos = pregunta.contains("ojos");
            boolean preguntaColorCabello = (pregunta.contains("negro") || pregunta.contains("rubio") || pregunta.contains("castaño") || pregunta.contains("pelirrojo") || pregunta.contains("blanco")) && (pregunta.contains("cabello") || (pregunta.contains("es") && (pregunta.contains("pelirrojo") || pregunta.contains("rubio"))));
            boolean preguntaSituacionDeCabello = pregunta.contains("calvo") || pregunta.contains("corto") || pregunta.contains("largo") || pregunta.contains("tiene cabello") && !preguntaColorCabello;
            boolean preguntaAccesorios = (pregunta.contains("lentes") || pregunta.contains("boina") || pregunta.contains("aretes") || pregunta.contains("gorro") || pregunta.contains("sombrero")) && pregunta.contains("tiene");
            boolean preguntaSexo = pregunta.contains("hombre") || pregunta.contains("mujer");
            boolean preguntaSituacionVello = (pregunta.contains("barba") || pregunta.contains("bigote") || pregunta.contains("vello")) && pregunta.contains("tiene");
            boolean preguntaCantidadAccesorios = pregunta.contains("tiene") && (pregunta.contains("un solo accesorio") || pregunta.contains("dos accesorios") || pregunta.contains("accesorios"));
            
            if(preguntaColorPiel){
                if(pregunta.contains("blanca")){
                    if(this.Personaje_Del_Contrario.getColorDePiel().equalsIgnoreCase("blanca")){
                        respuesta = "si";
                        this.descartarColorPiel("oscura", false);
                    }else{
                        this.descartarColorPiel("blanca", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene piel blanca.");
                    return true;
                }else if(pregunta.contains("oscura")){
                    if(this.Personaje_Del_Contrario.getColorDePiel().equalsIgnoreCase("oscura")){
                        respuesta = "si";
                        this.descartarColorPiel("blanca", false);
                    }else{
                        this.descartarColorPiel("oscura", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene piel oscura.");
                    return true;
                }
            }
            
            else if(preguntaColorOjos){
                if(pregunta.contains("marrones")){
                    if(this.Personaje_Del_Contrario.getColorDeOjos().equalsIgnoreCase("marrones")){
                        respuesta = "si";
                        this.descartarColorOjos("azules", false);
                    }else{
                        this.descartarColorOjos("marrones", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene ojos marrones.");
                    return true;
                }else if(pregunta.contains("azules")){
                    if(this.Personaje_Del_Contrario.getColorDeOjos().equalsIgnoreCase("azules")){
                        respuesta = "si";
                        this.descartarColorOjos("marrones", false);
                    }else{
                        this.descartarColorOjos("azules", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene ojos azules.");
                    return true;
                }
            }
            
            else if(preguntaColorCabello){
                if(pregunta.contains("pelirrojo")){
                    if(this.Personaje_Del_Contrario.getColorDeCabello().equalsIgnoreCase("pelirrojo")){
                        respuesta = "si";
                        this.descartarColorCabello("blanco", false);
                        this.descartarColorCabello("castaño", false);
                        this.descartarColorCabello("negro", false);
                        this.descartarColorCabello("rubio", false);
                    }else{
                        this.descartarColorCabello("pelirrojo", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene el cabello color pelirrojo.");
                    return true;
                }else if(pregunta.contains("blanco")){
                    if(this.Personaje_Del_Contrario.getColorDeCabello().equalsIgnoreCase("blanco")){
                        respuesta = "si";
                        this.descartarColorCabello("pelirrojo", false);
                        this.descartarColorCabello("castaño", false);
                        this.descartarColorCabello("negro", false);
                        this.descartarColorCabello("rubio", false);
                    }else{
                        this.descartarColorCabello("blanco", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene el cabello color blanco.");
                    return true;
                }else if(pregunta.contains("castaño")){
                    if(this.Personaje_Del_Contrario.getColorDeCabello().equalsIgnoreCase("castaño")){
                        respuesta = "si";
                        this.descartarColorCabello("pelirrojo", false);
                        this.descartarColorCabello("blanco", false);
                        this.descartarColorCabello("negro", false);
                        this.descartarColorCabello("rubio", false);
                    }else{
                        this.descartarColorCabello("castaño", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene el cabello color castaño.");
                    return true;
                }else if(pregunta.contains("negro")){
                    if(this.Personaje_Del_Contrario.getColorDeCabello().equalsIgnoreCase("negro")){
                        respuesta = "si"; 
                        this.descartarColorCabello("pelirrojo", false);
                        this.descartarColorCabello("blanco", false);
                        this.descartarColorCabello("castaño", false);
                        this.descartarColorCabello("rubio", false);
                    }else{
                        this.descartarColorCabello("negro", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene el cabello color negro.");
                    return true;
                }else if(pregunta.contains("rubio")){
                    if(this.Personaje_Del_Contrario.getColorDeCabello().equalsIgnoreCase("rubio")){
                        respuesta = "si";
                        this.descartarColorCabello("pelirrojo", false);
                        this.descartarColorCabello("blanco", false);
                        this.descartarColorCabello("castaño", false);
                        this.descartarColorCabello("negro", false);
                    }else{
                        this.descartarColorCabello("rubio", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene cabello color rubio.");
                    return true;
                }
            }
            
            else if(preguntaSituacionDeCabello){
                
                if(pregunta.contains("calvo")){
                    if(this.Personaje_Del_Contrario.getSituacionDeCabello().equalsIgnoreCase("calvo")){
                        respuesta = "si";
                        this.descartarCabello("sin cabello", false);
                        this.descartarCabello("largo", false);
                        this.descartarCabello("corto", false);
                    }else{
                        this.descartarCabello("calvo", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" es calvo.");
                    return true;
                }else if(pregunta.contains("corto")){
                    if(this.Personaje_Del_Contrario.getSituacionDeCabello().equalsIgnoreCase("corto")){
                        respuesta = "si";
                        this.descartarCabello("sin cabello", false);
                        this.descartarCabello("largo", false);
                        this.descartarCabello("calvo", false);
                    }else{
                        this.descartarCabello("corto", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene el cabello corto.");
                    return true;
                }else if(pregunta.contains("largo")){
                    if(this.Personaje_Del_Contrario.getSituacionDeCabello().equalsIgnoreCase("largo")){
                        respuesta = "si";
                        this.descartarCabello("sin cabello", false);
                        this.descartarCabello("corto", false);
                        this.descartarCabello("calvo", false);
                    }else{
                        this.descartarCabello("largo", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene el cabello largo.");
                    return true;
                }else if(pregunta.contains("tiene cabello")){
                    if(!this.Personaje_Del_Contrario.getSituacionDeCabello().equalsIgnoreCase("sin cabello")){
                        respuesta = "si";
                        this.descartarCabello("sin cabello", false);
                    }else{
                        this.descartarCabello("calvo", false);
                        this.descartarCabello("largo", false);
                        this.descartarCabello("corto", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene cabello.");
                    return true;
                }
            }
            
            else if(preguntaSituacionVello){
                if(pregunta.contains("tiene vello")){
                    if(!this.Personaje_Del_Contrario.getSituacionVello().equalsIgnoreCase("no tiene vello facial")){
                        respuesta = "si";
                        this.descartarVello("no tiene vello facial", false);
                    }else{
                        this.descartarVello("barba", false);
                        this.descartarVello("bigote", false);
                        this.descartarVello("barba y bigote", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene vello facial.");
                    return true;
                }else if(pregunta.contains("barba") && pregunta.contains("bigote")){
                    if(this.Personaje_Del_Contrario.getSituacionVello().equalsIgnoreCase("barba y bigote")){
                        respuesta = "si";
                        this.descartarVello("barba", false);
                        this.descartarVello("bigote", false);
                        this.descartarVello("no tiene vello facial", false);
                    }else{
                        this.descartarVello("barba y bigote", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene barba y bigote.");
                    return true;
                }else if(pregunta.contains("barba")){
                    if(this.Personaje_Del_Contrario.getSituacionVello().equalsIgnoreCase("barba")){
                        respuesta = "si";
                        this.descartarVello("bigote", false);
                        this.descartarVello("no tiene vello facial", false);
                    }else{
                        this.descartarVello("barba y bigote", false);
                        this.descartarVello("barba", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene barba.");
                    return true;
                }else if(pregunta.contains("bigote")){
                    if(this.Personaje_Del_Contrario.getSituacionVello().equalsIgnoreCase("bigote")){
                        respuesta = "si";
                        this.descartarVello("barba", false);
                        this.descartarVello("no tiene vello facial", false);
                    }else{
                        this.descartarVello("barba y bigote", false);
                        this.descartarVello("bigote", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene bigote.");
                    return true;
                }
            }

            if(preguntaSexo){
                if(pregunta.contains("hombre")){
                    if(this.Personaje_Del_Contrario.getSexo().equalsIgnoreCase("hombre")){
                        respuesta = "si";
                        this.descartarSexo("mujer", false);
                    }else{
                        this.descartarSexo("hombre", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" es hombre.");
                    return true;
                }else if(pregunta.contains("mujer")){
                    if(this.Personaje_Del_Contrario.getSexo().equalsIgnoreCase("mujer")){
                        respuesta = "si";
                        this.descartarSexo("hombre", false);
                    }else{
                        this.descartarSexo("mujer", false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" es mujer.");
                    return true;
                }
            }
            
            else if(preguntaCantidadAccesorios){
                if(pregunta.contains("tiene accesorios")){
                    if(this.Personaje_Del_Contrario.tieneAccesorios()){
                        respuesta = "si";
                        this.descartarAccesorios((byte)0, false);
                    }else{
                        this.descartarAccesorios((byte)1, false);
                        this.descartarAccesorios((byte)2, false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene accesorios.");
                    return true;
                }else if(pregunta.contains("un solo accesorio")){
                    if(this.Personaje_Del_Contrario.accesorios.length == 1){
                        respuesta = "si";
                        this.descartarAccesorios((byte)0, false);
                        this.descartarAccesorios((byte)2, false);
                    }else{
                        this.descartarAccesorios((byte)1, false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene solo un accesorio.");
                    return true;
                }else if(pregunta.contains("dos accesorios")){
                    if(this.Personaje_Del_Contrario.accesorios.length == 2){
                        respuesta = "si";
                        this.descartarAccesorios((byte)0, false);
                        this.descartarAccesorios((byte)1, false);
                    }else{
                        this.descartarAccesorios((byte)2, false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene dos accesorios.");
                    return true;
                }
            }
            
            else if(preguntaAccesorios){
                if(pregunta.contains("lentes")){
                    if(this.Personaje_Del_Contrario.buscarAccesorio('L')){
                        respuesta = "si";
                        this.descartarNoAccesorios('L', false);
                    }else{
                        this.descartarAccesorios('L', false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene lentes.");
                    return true;
                }else if(pregunta.contains("aretes")){
                    if(this.Personaje_Del_Contrario.buscarAccesorio('A')){
                        respuesta = "si";
                        this.descartarNoAccesorios('A', false);
                    }else{
                        this.descartarAccesorios('A', false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene aretes.");
                    return true;
                }else if(pregunta.contains("boina")){
                    if(this.Personaje_Del_Contrario.buscarAccesorio('B')){
                        respuesta = "si";
                        this.descartarNoAccesorios('B', false);
                    }else{
                        this.descartarAccesorios('B', false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene boina.");
                    return true;
                }else if(pregunta.contains("gorro")){
                    if(this.Personaje_Del_Contrario.buscarAccesorio('G')){
                        respuesta = "si";
                        this.descartarNoAccesorios('G', false);
                    }else{
                        this.descartarAccesorios('G', false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene gorro.");
                    return true;
                }else if(pregunta.contains("sombrero")){
                    if(this.Personaje_Del_Contrario.buscarAccesorio('S')){
                        respuesta = "si";
                        this.descartarNoAccesorios('S', false);
                    }else{
                        this.descartarAccesorios('S', false);
                    }
                    System.out.println("\33[31m"+this.Jugador.nombre+" hizo la pregunta: \33[30m"+pregunta+"?\n");
                    System.out.println("\33[34mTablero de "+this.Jugador.nombre+" con personajes descartados:\33[30m\n");
                    this.Jugador.displayJugador.mostrarTablero();
                    JOptionPane.showMessageDialog(null, "El personaje "+respuesta+" tiene sombrero.");
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Esa pregunta no es válida ", pregunta + ", no es una cadena válida", 0);
        return false;
    }
    
    public boolean descartar(String Nombre_Del_Personaje){
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(this.Jugador.TableroJugador[i][j].nombre.equalsIgnoreCase(Nombre_Del_Personaje)){
                    if(this.Jugador.TableroJugador[i][j].descartado){
                        JOptionPane.showMessageDialog(null, "EL PERSONAJE YA FUE DESCARTADO", this.Jugador.nombre+", su personaje a se descartó...", 0);
                        return false;
                    }
                    this.Jugador.TableroJugador[i][j].descartado = true;
                    JOptionPane.showMessageDialog(null, "El personaje "+this.Jugador.TableroJugador[i][j].nombre+" ha sido descartado", this.Jugador.nombre+" está descartando...", 1);
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "NO SE ENCONTRÓ EL PERSONAJE\nVolver a ingresar nombre", this.Jugador.nombre+", hubo un problema...", 0);
        return false;
    }
    
    public boolean descartarColorPiel(String colorPiel, boolean conMensaje){
        boolean procesoExitoso = false;
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(this.Jugador.TableroJugador[i][j].getColorDePiel().equalsIgnoreCase(colorPiel) && !this.Jugador.TableroJugador[i][j].descartado){
                    this.Jugador.TableroJugador[i][j].descartado = true;
                    procesoExitoso = true;
                }
            }
        }
        if(!procesoExitoso){
            if(conMensaje){
                JOptionPane.showMessageDialog(null, "No se hallaron más personajes de piel "+colorPiel, this.Jugador.nombre+", hubo un problema...", 0);
            }
            return false;
        }
        return true;
    }
    
    public boolean descartarSexo(String sexo, boolean conMensaje){
        boolean procesoExitoso = false;
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(this.Jugador.TableroJugador[i][j].getSexo().equalsIgnoreCase(sexo) && !this.Jugador.TableroJugador[i][j].descartado){
                    this.Jugador.TableroJugador[i][j].descartado = true;
                    procesoExitoso = true;
                }
            }
        }
        if(!procesoExitoso){
            if(conMensaje){
                JOptionPane.showMessageDialog(null, "No se hallaron más personajes con sexo de "+sexo, this.Jugador.nombre+", hubo un problema...", 0);            
            }
            return false;
        }
        return true;
    }
    
    public boolean descartarCabello(String cabello, boolean conMensaje){
        boolean procesoExitoso = false;
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(this.Jugador.TableroJugador[i][j].getSituacionDeCabello().equalsIgnoreCase(cabello) && !this.Jugador.TableroJugador[i][j].descartado){
                    this.Jugador.TableroJugador[i][j].descartado = true;
                    procesoExitoso = true;
                }
            }
        }
        if(!procesoExitoso){
            if(conMensaje){
                JOptionPane.showMessageDialog(null, "No se hallaron más personajes en la siguente situación de cabello: \n'"+cabello+"'", this.Jugador.nombre+", hubo un problema...", 0);
            }
            return false;
        }
        return true;
    }
    
    public boolean descartarColorCabello(String colorCabello, boolean conMensaje){
        boolean procesoExitoso = false;
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(this.Jugador.TableroJugador[i][j].getColorDeCabello().equalsIgnoreCase(colorCabello) && !this.Jugador.TableroJugador[i][j].descartado){
                    this.Jugador.TableroJugador[i][j].descartado = true;
                    procesoExitoso = true;
                }
            }
        }
        if(!procesoExitoso){
            if(conMensaje){
            JOptionPane.showMessageDialog(null, "No se hallaron más personajes con cabello "+colorCabello, this.Jugador.nombre+", hubo un problema...", 0);
            }
            return false;
        }
        return true;
    }
    
    public boolean descartarColorOjos(String colorOjos, boolean conMensaje){
        boolean procesoExitoso = false;
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(this.Jugador.TableroJugador[i][j].getColorDeOjos().equalsIgnoreCase(colorOjos) && !this.Jugador.TableroJugador[i][j].descartado){
                    this.Jugador.TableroJugador[i][j].descartado = true;
                    procesoExitoso = true;
                }
            }
        }
        if(!procesoExitoso){
            if(conMensaje){
            JOptionPane.showMessageDialog(null, "No se hallaron más personajes con ojos "+colorOjos, this.Jugador.nombre+", hubo un problema...", 0);
            }
            return false;
        }
        return true;
    }
    
    public boolean descartarVello(String vello, boolean conMensaje){
        boolean procesoExitoso = false;
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(this.Jugador.TableroJugador[i][j].getSituacionVello().equalsIgnoreCase(vello) && !this.Jugador.TableroJugador[i][j].descartado){
                    this.Jugador.TableroJugador[i][j].descartado = true;
                    procesoExitoso = true;
                }
            }
        }
        if(!procesoExitoso){
            if(conMensaje){
                JOptionPane.showMessageDialog(null, "No se hallaron más personajes en la siguiente situación de vello: \n'"+vello+"'", this.Jugador.nombre+", hubo un problema...", 0);
            }
            return false;
        }
        return true;
    }
    
    public boolean descartarAccesorios(byte cantidadAccesorios, boolean conMensaje){
        boolean procesoExitoso = false;
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(this.Jugador.TableroJugador[i][j].accesorios.length == (cantidadAccesorios) && !this.Jugador.TableroJugador[i][j].descartado){
                    this.Jugador.TableroJugador[i][j].descartado = true;
                    procesoExitoso = true;
                }
            }
        }
        if(!procesoExitoso){
            if(conMensaje){
                JOptionPane.showMessageDialog(null, "No se hallaron personajes con "+cantidadAccesorios+" accesorios", this.Jugador.nombre+", hubo un problema...", 0);
            }
            return false;
        }
        return true;
    }
    
    public boolean descartarAccesorios(char accesorio, boolean conMensaje){
        boolean procesoExitoso = false;
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(this.Jugador.TableroJugador[i][j].buscarAccesorio(accesorio) && !this.Jugador.TableroJugador[i][j].descartado){
                    this.Jugador.TableroJugador[i][j].descartado = true;
                    procesoExitoso = true;
                }
            }
        }
        if(!procesoExitoso){
            if(conMensaje){
                JOptionPane.showMessageDialog(null, "No se hallaron más personajes con ese accesorio", this.Jugador.nombre+", hubo un problema...", 0);
            }
            return false;
        }
        return true;
    }
    
    public boolean descartarNoAccesorios(char accesorio, boolean conMensaje){
        boolean procesoExitoso = false;
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(!this.Jugador.TableroJugador[i][j].buscarAccesorio(accesorio) && !this.Jugador.TableroJugador[i][j].descartado){
                    this.Jugador.TableroJugador[i][j].descartado = true;
                    procesoExitoso = true;
                }
            }
        }
        if(!procesoExitoso){
            if(conMensaje){
                JOptionPane.showMessageDialog(null, "No se hallaron más personajes con ese accesorio", this.Jugador.nombre+", hubo un problema...", 0);
            }
            return false;
        }
        return true;
    }
    
    public boolean descripciones(String Nombre_Del_Personaje){
        for(byte i = 0; i < this.Jugador.TableroJugador.length; i++){
            for(byte j = 0; j < this.Jugador.TableroJugador[0].length; j++){
                if(this.Jugador.TableroJugador[i][j].nombre.equalsIgnoreCase(Nombre_Del_Personaje)){
                    JOptionPane.showMessageDialog(null, this.Jugador.TableroJugador[i][j].descripcion(), "Descripción de "+this.Jugador.TableroJugador[i][j].nombre, 1);
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "NO SE ENCONTRÓ EL PERSONAJE\nVolver a ingresar nombre", this.Jugador.nombre+", hubo un problema...", 0);
        return false;
    }
}