package ar.com.inmutable.inmutableobjects.builder;

import ar.com.inmutable.inmutableobjects.Materia;

final public class MateriaBuilder {

    private final String nombreMateria;

    private final Double calificacionAlumno;

    public MateriaBuilder(final String nombreMateria, final Double calificacionAlumno) {
        this.nombreMateria = nombreMateria;
        this.calificacionAlumno = calificacionAlumno;
    }


    public Materia build() {
        return new Materia(nombreMateria, calificacionAlumno);
    }

}