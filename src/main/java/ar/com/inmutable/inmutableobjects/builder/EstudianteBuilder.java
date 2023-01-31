package ar.com.inmutable.inmutableobjects.builder;


import ar.com.inmutable.inmutableobjects.builder.utils.ValidatorUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
        if (ValidatorUtils.isEmpty(nombre)
                || ValidatorUtils.isEmpty(edad)) {

            throw new IllegalArgumentException();
        }

        if ((!ValidatorUtils.isEmpty(materiasCursadas) && materiasCursadas > 0) && ValidatorUtils.isEmpty(lstMaterias)) {
            throw new IllegalArgumentException();
        }

        if ((!ValidatorUtils.isEmpty(materiasCursadas) && materiasCursadas <= 0) && !ValidatorUtils.isEmpty(lstMaterias)) {
            throw new IllegalArgumentException();
        }

        this.nombre = nombre;
        this.edad = edad;
        this.materiasCursadas = materiasCursadas;
        if (lstMaterias == null) {
            this.lstMaterias = Collections.unmodifiableList(new ArrayList<Materia>());
        } else {
            this.lstMaterias = lstMaterias;
        }
    }

    public EstudianteBuilder fechaFinalizacion(LocalDate fecha) {
        this.fechaFinalizacion = fecha;
        return this;
    }

    public Estudiante build() {
        return new Estudiante(nombre, edad, Optional.ofNullable(fechaFinalizacion), materiasCursadas, lstMaterias);
    }

}
