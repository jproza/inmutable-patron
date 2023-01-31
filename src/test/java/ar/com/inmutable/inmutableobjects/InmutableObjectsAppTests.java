package ar.com.inmutable.inmutableobjects;

import ar.com.inmutable.inmutableobjects.builder.EstudianteBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.opentest4j.AssertionFailedError;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("dd-MMM")
                .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                .toFormatter(Locale.US);

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
    @Test
    public void test_localdate_formatter_conditions() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            assertNotNull(estudiante.getFechaFinalizacion());
            assertTrue(estudiante.getFechaFinalizacion().contains("-"));
        });
    }



    @Test
    public void test_formatter_pattern_with_Mock() {
        Assertions.assertThrows(Exception.class, () -> {
            Estudiante estudianteMock = mock(Estudiante.class);
            when(estudianteMock.getFechaFinalizacion()).thenReturn("10-Jan");
            String[] fechaFinSplited = estudianteMock.getFechaFinalizacion().split("-");
            assertTrue(fechaFinSplited.length == 1);
        });
    }

    @Test
    public void test_formatter_pattern_OK() {
            //Estudiante estudiante =
            EstudianteBuilder builder = new EstudianteBuilder("Ariel", 40 ,23,null);
            builder.fechaFinalizacion(LocalDate.now());

            String[] fechaFinSplited = builder.build().getFechaFinalizacion().split("-");
            assertTrue(fechaFinSplited.length == 2);
    }


}
