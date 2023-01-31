package ar.com.inmutable.inmutableobjects.builder;


import ar.com.inmutable.inmutableobjects.builder.utils.ValidatorUtils;

final public class MateriaBuilder {

    private final String nombreMateria;

    private final Double calificacionAlumno;

    public MateriaBuilder(final String nombreMateria, final Double calificacionAlumno) {
        super();
        if (ValidatorUtils.isEmpty(nombreMateria) || ValidatorUtils.isEmpty(calificacionAlumno)) {
            throw new IllegalArgumentException();
        }
        this.nombreMateria = nombreMateria;
        this.calificacionAlumno = calificacionAlumno;
    }


    public Materia build() {
        return new Materia(nombreMateria, calificacionAlumno);
    }

}
