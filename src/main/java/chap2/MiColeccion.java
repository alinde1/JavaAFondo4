package chap2;

public class MiColeccion<T> {
    
    private static final int CAP_INIC=10;
    
    private T datos[] = null;
    private int len = 0;
    
    public MiColeccion() {
        datos = (T[]) new Object[CAP_INIC];
    }
    
    // Agrega un elemento al final de la coleccion
    public void agregar(T elm) {
        insertar(elm, len);
    };
    
    // Inserta un elemento en la i-ésima posición
    public void insertar(T elm, int i) {
        // Si corresponde agrandamos el array
        verificarYAgrandar();
        
        for (int j=len-1; j>=i; j--) {
            datos[j+1] = datos[j];
        }
        
        datos[i] = elm;
        len++;
    };
    
    // Retorna el i-ésimo elemento de la colección
    public T obtener(int i) { return datos[i]; };
    
    // Elimina y retorna el i-ésimo objeto de la colección
    public T elimina(int i) {
        T aux = datos[i];
        for (int j=i; j<len-1; j++) {
            datos[j] = datos[j+1];
        }
        len--;
        return aux;
    };
    
    // Busca la primera ocurrencia del objeto especificado y
    // retorna la posición donde lo encuentra o un valor
    // negativo si no encontró lo que buscaba
    public int buscar(T elm) {
        int i = 0;
        while( i<len && !datos[i].equals(elm) ) {
            i++;
        }
        return i<len ? i : -1;
    };
    
    // Retorna la cantidad de elementos del conjunto
    public int cantidad() { return len; };
    
    private void verificarYAgrandar() {
        if ( len==datos.length ) {
            T nuevo[] = (T[]) new Object[datos.length*2];
            
            for (int i=0; i<datos.length; i++) {
                nuevo[i] = datos[i];
            }
            
            datos = nuevo;
            nuevo = null;
        }
    }
    
}
