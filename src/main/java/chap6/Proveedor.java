package chap6;

@Table(name="proveedor")
public class Proveedor {

    @Id
    @Column(name="id_proveedor")
    private int idProveedor;

    @Column(name="empresa")
    private String empresa;

    @Column(name="contacto")
    private String contacto;

    @Column(name="direccion")
    private String direccion;

    public Proveedor() {
        super();
    }

    public Proveedor(Integer id) {
        Proveedor proveedor = MyHibernate.find(this.getClass(), id);
        this.idProveedor = proveedor.idProveedor;
        this.contacto = proveedor.contacto;
        this.direccion = proveedor.direccion;
        this.empresa = proveedor.empresa;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", empresa='" + empresa + '\'' +
                ", contacto='" + contacto + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
