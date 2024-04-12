package chap2;

import java.util.GregorianCalendar;

public class Fecha {
    private int dia;
    private int mes;
    private int año;

    public Fecha() {
        setDia(1);
        setMes(1);
        setAño(1981);
    }

    public Fecha(String s) {
        int p1 = s.indexOf('/');
        int p2 = s.lastIndexOf('/');

        this.dia = Integer.parseInt(s.substring(0, p1));
        this.mes = Integer.parseInt(s.substring(p1+1, p2));
        this.año = Integer.parseInt(s.substring(p2+1));
    }

    public Fecha(int d, int m, int a) {
        setDia(d);
        setMes(m);
        setAño(a);
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    @Override
    public String toString() {
        return dia+"/"+mes+"/"+año;
    }

    @Override
    public boolean equals(Object o) {
        Fecha fecha = (Fecha) o;
        return this.dia == fecha.dia && this.mes == fecha.mes && this.año == fecha.año;
    }

    public void addDias(int n) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(GregorianCalendar.YEAR, año);
        gc.set(GregorianCalendar.MONTH, mes);
        gc.set(GregorianCalendar.DAY_OF_MONTH, dia);

        gc.add(GregorianCalendar.DAY_OF_MONTH, n);

        año = gc.get(GregorianCalendar.YEAR);
        mes = gc.get(GregorianCalendar.MONTH);
        dia = gc.get(GregorianCalendar.DAY_OF_MONTH);
    }
}
