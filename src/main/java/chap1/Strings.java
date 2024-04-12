package chap1;

public class Strings {
    public static void main(String[] args) {
        String s = "Hola";
        int x = s.length();
        System.out.println("x: " + x);

        boolean b1 = s.isEmpty();
        System.out.println("b1: " + b1);

        String s2 = "";
        boolean b2 = s2.isEmpty();
        System.out.println("b2: " + b2);

        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            System.out.print(c + " ");
        }
        System.out.println();

        String s3 = "Hola";
        System.out.println("s == s3: " + s == s3);
        System.out.println("s.equals(s3): " + s.equals(s3));

        String s4 = "Hola, ¿cómo estás Lola?";
        System.out.println("indexOf('a': " + s4.indexOf('a'));
        System.out.println("lastIndexOf('a'): " + s4.lastIndexOf('a'));
        System.out.println("indexOf(\"la\": " + s4.indexOf("la"));
        System.out.println("lastIndexOf(\"la\": " + s4.lastIndexOf("la"));
        System.out.println("Upper case: " + s4.toUpperCase());
        System.out.println("Lower case: " + s4.toLowerCase());
        System.out.println("Substring (0,4): " + s4.substring(0,4));
        System.out.println("Substring (6,10): "+ s4.substring(6,10));
        System.out.println("Substring (11): " + s4.substring(11));

        String s5 = "1234";
        int i = Integer.parseInt(s5);
        String si = Integer.toString(i);
        System.out.println("int <-> String: " + i + " " + si);

        System.out.println(s.hashCode());
        s = "Pepito";
        System.out.println(s.hashCode());

        StringBuilder sb = new StringBuilder();
        System.out.println(sb.hashCode());
        sb.append("Hola");
        System.out.println(sb.hashCode());
        sb.append(", Radiola.");
        System.out.println(sb.hashCode());
        System.out.println(sb);
        sb.setCharAt(4, '.');
        System.out.println(sb.hashCode());
        System.out.println(sb);
    }
}
