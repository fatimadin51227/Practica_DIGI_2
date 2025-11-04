import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Diez productos");
        int contador = 0;

        String descripcionMasLarga = "";
        int longitudMasLarga = 0;

        String descripcionMasUnidades = "";
        int maxUnidades = -1;

        String descripcionMasPronto = "";
        LocalDate fechaMasPronto = null;
        boolean salir = false;

        do {
            String descripcion = pedirDescripcion(contador + 1);
            if (descripcion.equals("0")) {
                salir = true;
                break;
            }
            int unidades = pedirUnidades();
            if (unidades == 0) {
                salir = true;
                break;
            }
            LocalDate fechaCaducidad = pedirFecha();

            if (descripcion.length() > longitudMasLarga) {
                longitudMasLarga = descripcion.length();
                descripcionMasLarga = descripcion;
            }

            if (unidades > maxUnidades) {
                maxUnidades = unidades;
                descripcionMasUnidades = descripcion;
            }

            if (fechaMasPronto == null || fechaCaducidad.isBefore(fechaMasPronto)) {
                fechaMasPronto = fechaCaducidad;
                descripcionMasPronto = descripcion;
            }
            contador++;

        } while (contador < 3 && !salir) ;

        JOptionPane.showMessageDialog(null,
                "RESULTADOS:\n" +
                        "Producto con descripción más larga: " + descripcionMasLarga + "\n" +
                        "Producto con más unidades: " + descripcionMasUnidades + " (" + maxUnidades + " unidades)\n" +
                        "Producto que caduca primero: " + descripcionMasPronto + " (Caduca: " + fechaMasPronto + ")"
        );
        JOptionPane.showMessageDialog(null, "Programa finalizado");
    }
}