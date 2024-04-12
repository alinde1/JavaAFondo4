package chap2;

public class FechaDetallada extends Fecha {

    public FechaDetallada(String fecha) {
        super(fecha);
    }

    private static String meses[] = {
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"
    };

    @Override
    public String toString() {
      int mes = getMes() -1;
      return getDia() + " de " + meses[mes] + " de " + getAño();
    };
}

