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

    private static String pedirDescripcion(int numero) {
        String descripcion;
        do {
            descripcion = JOptionPane.showInputDialog("Producto " + numero + "\nDescripción (Introduzca un 0 si quiere salir):");
            if (descripcion == null || descripcion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía.");
            }
        } while (descripcion == null || descripcion.trim().isEmpty());
        return descripcion.trim();
    }

    private static LocalDate pedirFecha() {
        LocalDate fecha = null;
        boolean aceptar = false;

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String fecha2 = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])$";
        Pattern pat = Pattern.compile(fecha2);
        do {
            try {
                String mensaje = JOptionPane.showInputDialog("Fecha de caducidad (YYYY/MM/DD):");
                if (mensaje == null) {
                    JOptionPane.showMessageDialog(null, "Debes introducir una fecha.");
                    continue;
                }

                Matcher m = pat.matcher(mensaje);

                if (!m.matches()) {
                    JOptionPane.showMessageDialog(null, "Formato invalido, usa el formato yyyy/MM/dd");
                    continue;
                }
                fecha = LocalDate.parse(mensaje, formato);
                aceptar = true;

            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Usa YYYY/MM/DD.");
            }
        } while (!aceptar);
        return fecha;
    }
}