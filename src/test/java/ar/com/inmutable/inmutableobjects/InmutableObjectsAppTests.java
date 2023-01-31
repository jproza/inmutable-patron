package ar.com.inmutable.inmutableobjects;

import ar.com.inmutable.inmutableobjects.builder.EstudianteBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InmutableObjectsAppTests {

    private Estudiante estudiante;
    private Estudiante estudianteClon;

    @BeforeEach
    public void before() {
        List collection = Collections.unmodifiableList(new ArrayList<>());
        LocalDate ld = LocalDate.now();
        EstudianteBuilder builder = new EstudianteBuilder("Javier", 41, 12, collection);
        builder.fechaFinalizacion(ld);
        this.estudiante = builder.build();
        EstudianteBuilder builderClon = new EstudianteBuilder("Javier", 41, 12,collection);
        builderClon.fechaFinalizacion(ld);
        this.estudianteClon = builderClon.build();
    }

    @Test
    public void test_instancias_Inmutables_ExpectedException() {
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            Assertions.assertTrue(estudiante.equals(estudianteClon));
        });
    }

    @Test
    public void test_unmodifiable_Collections_ExpectedException() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            assertEquals(0, estudiante.getLstMaterias().size());
            estudiante.getLstMaterias().add(new Materia("Matemática", 7.0));
            assertEquals(0, estudiante.getLstMaterias().size());
        });
    }


}
