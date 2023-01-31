package ar.com.inmutable.inmutableobjects.builder;

import ar.com.inmutable.inmutableobjects.Estudiante;
import ar.com.inmutable.inmutableobjects.Materia;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

final public class EstudianteBuilder {

    private final String nombre;

    private final Integer edad;

    //No lo hago final porque es un campo opcional desde mi punto de vista, el estudiante pudo o no finalizar. Aparte
    //ejemplifico como seria la incorporación de un campo opcional.
    private LocalDate fechaFinalizacion;

    private final Integer materiasCursadas;

    private final List<Materia> lstMaterias;


    public EstudianteBuilder(final String nombre, final Integer edad, final Integer materiasCursadas, final List<Materia> lstMaterias) {
        super();
        this.nombre = nombre;
        this.edad = edad;
        this.materiasCursadas = materiasCursadas;
        this.lstMaterias = lstMaterias;
    }

    public EstudianteBuilder fechaFinalizacion(LocalDate fecha) {
        this.fechaFinalizacion = fecha;
        return this;
    }

    public Estudiante build() {
        return new Estudiante(nombre, edad, Optional.ofNullable(fechaFinalizacion), materiasCursadas, lstMaterias);
    }

}
