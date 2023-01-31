package ar.com.inmutable.inmutableobjects.builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

final class Estudiante {

    private final String nombre;

    private final Integer edad;

    ///No lo hago final porque es un campo opcional desde mi punto de vista, el estudiante pudo o no finalizar
    private final Optional<LocalDate> fechaFinalizacion;

    private final Integer materiasCursadas;

    private final List<Materia> lstMaterias;


    Estudiante(final String nombre, final Integer edad, final Optional<LocalDate> fechaFinalizacion,
                      final Integer materiasCursadas, final List<Materia> lstMaterias) {
        super();
        this.nombre = nombre; //string es inmmutable //Copia defensiva
        this.edad = new Integer(edad); //Copia defensiva
        this.fechaFinalizacion = fechaFinalizacion; //default inmutable //Copia defensiva
        this.materiasCursadas = new Integer(materiasCursadas); //Copia defensiva
        this.lstMaterias = Collections.unmodifiableList(lstMaterias); //inmutable //Copia defensiva
    }


    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return new Integer(edad);
    }

    public String getFechaFinalizacion() {
        if (fechaFinalizacion.isPresent()) {
             DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                    .appendPattern("dd-MMM")
                    .parseDefaulting(ChronoField.YEAR, fechaFinalizacion.get().getYear())
                    .toFormatter(Locale.US);
            return dateTimeFormatter.format(fechaFinalizacion.get());
        }
        return null;
    }

    public Integer getMateriasCursadas() {
        return materiasCursadas;
    }

    public List<Materia> getLstMaterias() {
        return this.lstMaterias;
    }
}
