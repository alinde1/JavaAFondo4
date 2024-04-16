package chap6;

@Component
public class TheBeatles implements Banda{
    @Autowired(implementation=GeorgeHarrison.class)
    private Guitarrista primeraGuitarra;

    @Autowired(implementation=JohnLennon.class)
    private Guitarrista segundaGuitarra;

    @Autowired
    private Bajista bajista;

    @Autowired
    private Baterista baterista;

    public String toString() {
        String ret = "";
        ret += "The Beatles { \n";
        ret += "       " + primeraGuitarra + "\n";
        ret += "      ," + segundaGuitarra + "\n";
        ret += "      ," + bajista + "\n";
        ret += "      ," + baterista + " }\n";
        return ret;
    }
}
