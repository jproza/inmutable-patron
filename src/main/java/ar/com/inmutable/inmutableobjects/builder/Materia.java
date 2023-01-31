package ar.com.inmutable.inmutableobjects.builder;

final class Materia {

    private final String nombreMateria;
    private final Double calificacionAlumno;

    Materia(final String nombreMateria, final Double calificacionAlumno) {
        super();
        this.nombreMateria = nombreMateria; //ya es inmutable por ser String
        this.calificacionAlumno = new Double(calificacionAlumno); //Copia defensiva
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public Double getCalificacionAlumno() {
        return calificacionAlumno;
    }


}
