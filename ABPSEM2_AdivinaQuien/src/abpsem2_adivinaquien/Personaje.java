/*
 * Clase persona de proyecto principal
 */
package abpsem2_adivinaquien;

public class Personaje{
    
    public String nombre; // Nombre almacenado como cadena de caracteres
    public boolean sexo; // Sexo almacenado como booleano, pues solo hay dos
    public char cabello; // Situación de vello almacenado como caracter
    public char cabelloCol; // Color de cabello almacenado como caracter
    public char[] accesorios; // Lista enlazada para almacenamiento de accesorios en tipo caracter
    public char vello; // Situación de vello almacenado como caracter
    public boolean pielCol; // Color de piel almacenado en un booleano, pues solo hay dos posibles
    public boolean ojosCol; // Color de ojos almacenado en un booleano, pues solo hay dos posibles
    public boolean descartado; // Descartado, alamcenado en booleano

    public Personaje(String Nombre, char Sexo, char Situacion_De_Cabello, char Color_De_Cabello, String accesorios, char Situacion_De_Vello_Facial, boolean Color_De_Piel, char Color_De_Ojos){
        this.nombre = Nombre;
        this.sexo = (Sexo == 'F');
        this.cabello = Situacion_De_Cabello;
        this.cabelloCol = Color_De_Cabello;
        this.accesorios = accesorios.toCharArray();
        this.vello = Situacion_De_Vello_Facial;
        this.pielCol = Color_De_Piel;
        this.ojosCol = (Color_De_Ojos == 'A');
        this.descartado = false;
    }
    
    public String getSexo(){
        if(this.sexo){
            return "Mujer";
        }else{
            return "Hombre";
        }
    }
    
    public String getSituacionDeCabello(){
        switch(this.cabello){
            case 'L':{
                return "Largo";
            }case 'C':{
                return "Calvo";
            }case 'R':{
                return "Corto";
            }default:{
                return "Sin Cabello";
            }
        }
    }
    
    public String getColorDeCabello(){
        switch(this.cabelloCol){
            case 'R':{
                return "Rubio";
            }case 'C':{
                return "Castaño";
            }case 'N':{
                return "Negro";
            }case 'P':{
                return "Pelirrojo";
            }case 'B':{
                return "Blanco";
            }default:{
                return "No tiene Cabello";
            }
        }
    }
    
    public String getColorDeOjos(){
        if(this.ojosCol){
            return "Azules";
        }else{
            return "Marrones";
       }
    }
    
    public boolean tieneAccesorios(){
        return this.accesorios.length != 0;
    }
    
    public boolean buscarAccesorio(char accesorio){
        boolean found = false;
        for(byte i = 0; i < this.accesorios.length; i++){
            if(this.accesorios[i] == accesorio){
                found = true;
                break;
            }
        }
        return found;
    }
    
    public String getSituacionVello(){
        switch(this.vello){
            case 'A':{
                return "Barba";
            }case'B':{
                return "Bigote";
            }case 'C':{
                return "Barba y Bigote";
            }default:{
                return "No tiene vello facial";
            }
        }
    }
    
    public void descartar(){
        this.descartado = true;
    }
    
    public boolean tieneVelloFacial(){
        return !this.getSituacionVello().equalsIgnoreCase("No tiene vello facial");
    }
    
    public String getColorDePiel(){
        if(this.pielCol){
            return "Blanca";
        }else{
            return "Oscura";
        }
    }
    
    public String descripcion(){
        String descrip;
        descrip = "Nombre: "+this.nombre+"\nSexo: "+this.getSexo()+"\nTipo de Cabello: "+this.getSituacionDeCabello();
        
        if(!(this.getSituacionDeCabello().equalsIgnoreCase("Sin Cabello"))){
            descrip += "\nColor de Cabello: "+this.getColorDeCabello();
        }
        
        if(this.tieneAccesorios()){
            descrip += "\nAccesorio/s: ";
            for(byte i = 0; i < this.accesorios.length; i++){
                switch(this.accesorios[i]){
                    case 'L':{
                        descrip += "Lentes";
                        break;
                    }case 'B':{
                        descrip += "Boina";
                        break;
                    }case 'G':{
                        descrip += "Gorra";
                        break;
                    }case 'S':{
                        descrip += "Sombrero";
                        break;
                    }case 'A':{
                        descrip += "Aretes";
                        break;
                    }
                }
                if(i != this.accesorios.length - 1){
                    descrip += ", ";
                }
            }
        }
        descrip += "\nVello Facial: "+this.getSituacionVello();
        if(this.tieneVelloFacial()){
            descrip += "\nColor de Vello Facial: "+this.getColorDeCabello();
        }
        descrip +="\nColor de Piel: "+this.getColorDePiel()+"\nColor de Ojos: "+this.getColorDeOjos();
        return descrip;
    }
}