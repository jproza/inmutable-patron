package ar.com.inmutable.inmutableobjects.builder;

import ar.com.inmutable.inmutableobjects.builder.utils.ValidatorUtils;

final class Materia {

    private final String nombreMateria;
    private final Double calificacionAlumno;

    Materia(final String nombreMateria, final Double calificacionAlumno) {
        super();

        if (ValidatorUtils.isEmpty(nombreMateria) || ValidatorUtils.isEmpty(calificacionAlumno)) {
            throw new IllegalArgumentException();
        }

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
