package chap2;

import java.util.Date;
import java.util.Scanner;

public class TestFecha {
    public static void main(String[] args) {
        Fecha f = new Fecha(1, 1,1900);

        f.setDia(2);
        f.setMes(10);
        f.setAño(1970);

        System.out.println("Dia: " + f.getDia());
        System.out.println("Mes: " + f.getMes());
        System.out.println("Año: " + f.getAño());
        System.out.println(f);

        Fecha f2 = new Fecha(1, 1, 1800);
        f2.setDia(2);
        f2.setMes(10);
        f2.setAño(1970);

        System.out.println("== : " + (f == f2));
        System.out.println("equals: " + f.equals(f2));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese Fecha1 (dia, mes, año): ");
        int dia = scanner.nextInt();
        int mes = scanner.nextInt();
        int año = scanner.nextInt();

        Fecha f1 = new Fecha(dia, mes, año);

        System.out.print("Ingrese Fecha2 (dia, mes, año): ");
        dia = scanner.nextInt();
        mes = scanner.nextInt();
        año = scanner.nextInt();

        Fecha f3 = new Fecha(dia, mes, año);

        System.out.println("Fecha 1: " + f1);
        System.out.println("Fecha 2: " + f3);

        if (f1.equals(f3)) {
            System.out.println("Son iguales!");
        } else {
            System.out.println("Son diferentes...");
        }

        Fecha f4 = new Fecha("06/02/2024");
        System.out.println("f4: "+ f4);

        f4.addDias(180);
        System.out.println("f4 + 180 días: " + f4);

        Date d = new Date();

        FechaDetallada f5 = new FechaDetallada("06/02/2024");
        System.out.println("f5: " + f5);

        scanner.close();
    }
}
