package ar.com.inmutable.inmutableobjects;

final public class Materia {

    private final String nombreMateria;
    private final Double calificacionAlumno;

    public Materia(final String nombreMateria, final Double calificacionAlumno) {
        super();
        this.nombreMateria = nombreMateria;
        this.calificacionAlumno = new Double(calificacionAlumno); //Copia defensiva
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public Double getCalificacionAlumno() {
        return calificacionAlumno;
    }


}
