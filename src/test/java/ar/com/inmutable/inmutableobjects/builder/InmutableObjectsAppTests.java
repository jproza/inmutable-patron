package ar.com.inmutable.inmutableobjects.builder;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
    private static EstudianteBuilder estudianteBuilder;
    private static EstudianteBuilder estudianteClonBuilder;
    private static EstudianteBuilder estudianteSinMateriasCursadasBuilder;
    private static EstudianteBuilder estudianteIllegalStateBuilder;
    private static List<Materia> lstMaterias;

    @BeforeAll
    static void before() {
        lstMaterias = new ArrayList<>();
        lstMaterias.add(new MateriaBuilder("Matemática", 7.0).build());
        List collection = Collections.unmodifiableList(lstMaterias);
        LocalDate ld = LocalDate.now();

        estudianteBuilder = new EstudianteBuilder("Javier", 41, 12, collection);
        estudianteBuilder.fechaFinalizacion(ld);

        estudianteClonBuilder = new EstudianteBuilder("Javier", 41, 12, collection);
        estudianteClonBuilder.fechaFinalizacion(ld);

        estudianteSinMateriasCursadasBuilder = new EstudianteBuilder("Javier", 41, 0, null);
        estudianteSinMateriasCursadasBuilder.fechaFinalizacion(ld);

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("dd-MMM")
                .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                .toFormatter(Locale.US);

    }

    @Test
    public void test_instancias_Inmutables_ExpectedException() {
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            Assertions.assertTrue(estudianteBuilder.build() == estudianteClonBuilder.build());
        });
    }

    @Test
    public void test_unmodifiable_Collections_ExpectedException() {
        assertAll("Grupo de evaluaciones para colecciones inmutables",
                () -> assertEquals(1, estudianteBuilder.build().getLstMaterias().size()),
                () -> {
                    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
                        MateriaBuilder materiaBuilder = new MateriaBuilder("Matemática", 7.0);
                        estudianteBuilder.build().getLstMaterias().add(materiaBuilder.build());
                        assertEquals(1, estudianteBuilder.build().getLstMaterias().size());
                    });
                }
        );
    }

    @Test
    public void test_localdate_formatter_conditions() {
        assertAll("Conjunto de evaluaciónes para el formatter sobre fechas",
                () -> assertNotNull(estudianteBuilder.build().getFechaFinalizacion()),
                () -> assertTrue(estudianteBuilder.build().getFechaFinalizacion().contains("-")));
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
    public void test_formatter_pattern_conditions_REG_EXP_OK() {
        EstudianteBuilder builder = new EstudianteBuilder("Ariel", 40, 23, lstMaterias);
        builder.fechaFinalizacion(LocalDate.now());
        String[] fechaFinSplited = builder.build().getFechaFinalizacion().split("-");
        assertAll("Conjunto de evaluaciónes para el tratamiento de fechas validas",
                () -> assertTrue(fechaFinSplited.length == 2),
                () -> assertTrue(builder.build().getFechaFinalizacion().matches("\\d{2}-\\S{3}"))); //regex
    }

    @Test
    public void test_formatter_pattern_OK() {
        EstudianteBuilder builder = new EstudianteBuilder("Ariel", 40, 23, lstMaterias);
        builder.fechaFinalizacion(LocalDate.now());
        String[] fechaFinSplited = builder.build().getFechaFinalizacion().split("-");
        assertTrue(fechaFinSplited.length == 2);
    }

    @Test
    public void test_unmodifiable_Collections_Materias_ExpectedException() {
        assertAll("Grupo de validaciones para chequear mutabilidad",
                () -> assertEquals(estudianteClonBuilder.build().getLstMaterias().size(), estudianteBuilder.build().getLstMaterias().size()),
                () -> assertFalse(estudianteClonBuilder.build().getLstMaterias() == estudianteBuilder.build().getLstMaterias()));
    }

    @Test
    public void test_unmodifiable_Property_Nombre_OK() {
        assertTrue(estudianteClonBuilder.build().getNombre() == estudianteBuilder.build().getNombre());
    }

    @Test
    public void test_unmodifiable_Property_Edad_OK() {
        assertEquals(estudianteClonBuilder.build().getEdad(), estudianteBuilder.build().getEdad());
    }

    @Test
    public void test_validation_Property_MateriasCursadas_OK() {
        estudianteSinMateriasCursadasBuilder.build();
    }

    @Test
    public void test_validation_Property_MateriasCursadas_ExpectedException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            estudianteIllegalStateBuilder = new EstudianteBuilder("Javier", 41, 1, null);
            estudianteIllegalStateBuilder.build();
        });
    }

    @Test
    public void test_validation_Property_MateriasCursadas_ExpectedException_OK() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            estudianteIllegalStateBuilder = new EstudianteBuilder("Javier", 41, 0, lstMaterias);
            estudianteIllegalStateBuilder.build();
        });
    }

    @AfterAll
    static void tearDown() {
        lstMaterias = null;
        estudianteBuilder = null;
        estudianteClonBuilder = null;
        estudianteSinMateriasCursadasBuilder = null;
        estudianteIllegalStateBuilder = null;
    }
}
