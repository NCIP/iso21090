package gov.nih.nci.iso21090.grid.dto.transform;

import static org.junit.Assert.*;

import org.junit.Test;


public class DtoTransformExceptionTest {

    @Test
    public void noarg() {
        DtoTransformException dtoTransformException = new DtoTransformException();
        assertNotNull(dtoTransformException);
    }
    
    @Test 
    public void msg() {
        DtoTransformException dtoTransformException = new DtoTransformException("msg");
        assertNotNull(dtoTransformException);
        assertEquals("msg", dtoTransformException.getMessage());
    }
    @Test 
    public void cause() {
        UnsupportedOperationException cause = new UnsupportedOperationException();
        DtoTransformException dtoTransformException = new DtoTransformException(cause);
        assertNotNull(dtoTransformException);
        assertEquals(cause, dtoTransformException.getCause());
    }
    @Test 
    public void msg_cause() {
        UnsupportedOperationException cause = new UnsupportedOperationException();
        DtoTransformException dtoTransformException = new DtoTransformException("msg", cause);
        assertNotNull(dtoTransformException);
        assertEquals("msg", dtoTransformException.getMessage());
        assertEquals(cause, dtoTransformException.getCause());
    }
}
