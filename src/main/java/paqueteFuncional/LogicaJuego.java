package paqueteFuncional;

import java.awt.Color;
import java.util.List;

public class LogicaJuego {

    public int turno = 0;
    public int pX = 0;
    public int pO = 0;

    public LogicaJuego() {
    }

    ;

    public int getTurno() { //  Sirve para usar el turno en las siguientes funciones
        return this.turno;
    }

    public void cambioTurno() {
        switch (getTurno()) {   //  Podría haber utilizado un "if" pero me parecía más interesante usar el switch, por cambiar un poco
            case 0: //  Si el turno es 0, lo cambia a 1
                turno = 1;
                break;
            case 1: //  Si el turno es 1, lo cambia a 0
                turno = 0;
                break;
        }
    }

    public boolean comprobarJuego(int[][] tablero) {
        boolean victoria = false;
        //  Como el tablero está lleno con valores del 2 al 10, este if comprueba si en las líneas
        //  horizontales, verticales o diagonales hay tres "0" o tres "1", lo cual se consigue jugando
        if ((tablero[0][1] == tablero[0][0] //  Horizontal fila 1
                && tablero[0][2] == tablero[0][0])
                || (tablero[1][1] == tablero[1][0] //  Horizontal fila 2
                && tablero[1][2] == tablero[1][0])
                || (tablero[2][1] == tablero[2][0] //  Horizontal fila 3
                && tablero[2][2] == tablero[2][0])
                || (tablero[1][0] == tablero[0][0] //  Vertical fila 1
                && tablero[2][0] == tablero[0][0])
                || (tablero[1][1] == tablero[0][1] //  Vertical fila 2
                && tablero[2][1] == tablero[0][1])
                || (tablero[1][2] == tablero[0][2] //  Vertical fila 3
                && tablero[2][2] == tablero[0][2])
                || (tablero[1][1] == tablero[0][0] // Diagonal celda 1
                && tablero[2][2] == tablero[0][0])
                || (tablero[1][1] == tablero[0][2] // Diagonal celda 3
                && tablero[2][0] == tablero[0][2])) {
            victoria = true;
        }
        return victoria;
    }

    public void pintarFicha(javax.swing.JButton boton) {
        if (getTurno() == 0) {  //  Según el turno que sea
            boton.setText("X"); //  Ponemos la ficha que toca
            boton.setForeground(Color.RED); //  Pintamos el texto de la ficha
        } else {
            boton.setText("O");
            boton.setForeground(Color.BLUE);
        }
    }

    public void ponerFicha(int[][] tablero, javax.swing.JButton boton, int i, int j) {
        tablero[i][j] = getTurno(); //  Según las coordenadas del botón y el turno, la matriz del tablero rellena ese hueco con 0 o 1
        pintarFicha(boton); //  Pintamos la ficha
    }

    // TiradaJugador pide el botón que se click, las coordenadas del botón, el tablero, las etiquetas que controlan la puntucación y la lista de botones.
    public void tiradaJugador(javax.swing.JButton boton, int i, int j, int[][] tablero, javax.swing.JLabel x, javax.swing.JLabel o, List<javax.swing.JButton> botones) {
        boton.setEnabled(false);            //  Desactivamos el botón al clickarlo
        ponerFicha(tablero, boton, i, j);   //  Pintamos la ficha con el texto que corresponda
        if (!(comprobarJuego(tablero))) {   //  Comprobamos si se ha ganado para pasar turno o terminar el juego
            cambioTurno();
        } else {
            ganador(x, o);  //  Usamos la función para marcar lo que 
            habilitarTablero(false, botones);
            cambioTurno();                  //  Cambiamos el turno tras ganar, para que empiece la otra ficha
        }
    }

    public void ganador(javax.swing.JLabel x, javax.swing.JLabel o) {
        switch (getTurno()) {
            case 0:
                pX++;   //  Sumamos 1 al contador de victorias
                String ganadasX = String.valueOf(pX);
                x.setText(ganadasX);    //  Proyectamos las victorias en la etiqueta
                break;
            case 1:
                pO++;
                String ganadasO = String.valueOf(pO);
                o.setText(ganadasO);

        }
    }

    public void habilitarTablero(boolean habilitar, List<javax.swing.JButton> botones) {
        botones.forEach(boton -> {  //  Cada botón se vuelve activado o desactivado
            boton.setEnabled(habilitar);
        });
    }

    public void iniciarPartida(int[][] tablero, List<javax.swing.JButton> botones) {
        int contador = 2;
        for (int i = 0; i < tablero.length; i++) {  // Reiniciamos el tablero igual que lo hacíamos en "Juego", dándole los valores iniciales
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = contador;
                contador++;
            }
        }
        habilitarTablero(true, botones);    //  Habilitamos los botones
    }

}
