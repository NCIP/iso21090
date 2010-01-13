package gov.nih.nci.iso21090.grid.dto.transform;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;

/**
 * To write a converter test, extend this class and follow public method pairs to generate data, and verify the
 * converted value. The methods are paired by their name patters: - makeXmlZZZ and verifyDtoZZZ, or - makeDtoZZZ and
 * verifyXmlZZZ.
 *
 * @author gax
 */
@SuppressWarnings("unchecked")
public abstract class AbstractTransformerTestBase<T extends Transformer<XML, DTO>, XML, DTO> {

    private final int NUM_OBJ_TO_CONVERT = 10;
    protected final Class<T> tClass;
    protected final Class<XML> xmlClass;
    protected final Class<DTO> dtoClass;
    {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments[0] instanceof Class) {
            tClass = (Class<T>) actualTypeArguments[0];
        } else {
            tClass = (Class<T>) ((ParameterizedType) actualTypeArguments[0]).getRawType();
        }
        xmlClass = (Class<XML>) actualTypeArguments[1];
        if (actualTypeArguments[2] instanceof Class) {
            dtoClass = (Class<DTO>) actualTypeArguments[2];
        } else {
            dtoClass = (Class<DTO>) ((ParameterizedType) actualTypeArguments[2]).getActualTypeArguments()[0];
        }
    }

    protected T transformer;

    private Method[] makers;
    private Method[] verifiers;

    private void setUpTests() {
        Method[] methods = getClass().getMethods();
        Hashtable<String, Method> makerMap = new Hashtable<String, Method>();
        Hashtable<String, Method> verifierMap = new Hashtable<String, Method>();
        for (Method m : methods) {
            String n = m.getName();
            if (n.startsWith("make")) {
                makerMap.put(n.substring("make".length()), m);
            } else if (n.startsWith("verify")) {
                verifierMap.put(n.substring("verify".length()), m);
            }
        }
        makers = new Method[makerMap.size()];
        verifiers = new Method[makers.length];
        int i = 0;
        for (Map.Entry<String, Method> e : makerMap.entrySet()) {
            makers[i] = e.getValue();
            String vn = null;
            if (e.getKey().startsWith("Xml")) {
                vn = "Dto" + e.getKey().substring(3);
            } else if (e.getKey().startsWith("Dto")) {
                vn = "Xml" + e.getKey().substring(3);
            } else {
                fail("method name should be makeXml* or makeDto*, but was make" + e.getKey());
            }
            verifiers[i] = verifierMap.remove(vn);
            assertNotNull("make" + e.getKey() + "() needs a matching verify" + vn + "()", verifiers[i]);
            // System.out.println("matched "+makers[i]+" to "+verifiers[i]);
            i++;
        }
        if (!verifierMap.isEmpty()) {
            fail(" found verifier(s) w/o matching makers : " + verifierMap.toString());
        }
    }

    public abstract XML makeXmlSimple() throws DtoTransformException;

    public abstract DTO makeDtoSimple() throws DtoTransformException;

    public abstract void verifyXmlSimple(XML x) throws DtoTransformException;

    public abstract void verifyDtoSimple(DTO x) throws DtoTransformException;

    public void testXmlConvert() throws DtoTransformException {
        // create list of XML objects
        XML[] arr = transformer.createXmlArray(NUM_OBJ_TO_CONVERT);
        for (int i = 0 ; i < NUM_OBJ_TO_CONVERT ; i++) {
            arr[i] = makeXmlSimple();
        }
        //test null
        XML[] nullArr = null;
        assertNull(transformer.convert(nullArr));

        List<DTO> dtos = transformer.convert(arr);
        for (DTO dto : dtos) {
            verifyDtoSimple(dto);
        }
    }

    public void testDtoConvert() throws DtoTransformException {
        // create list of DTO objects
        List<DTO> dtos = new ArrayList<DTO>();
        for (int i = 0 ; i < NUM_OBJ_TO_CONVERT ; i++) {
            dtos.add(makeDtoSimple());
        }

        //test null
        List<DTO> nullList = null;
        assertNull(transformer.convert(nullList));

        XML[] isos = transformer.convert(dtos);
        for (int i = 0 ; i < NUM_OBJ_TO_CONVERT ; i++) {
            verifyXmlSimple(isos[i]);
        }
    }

    public XML makeXmlNull() {
        return null;
    }

    public DTO makeDtoNull() {
        return null;
    }

    public void verifyXmlNull(XML x) {
        assertNull(x);
    }

    public void verifyDtoNull(DTO x) {
        assertNull(x);
    }

    @Before
    public void initTransformer() {
        try {
            transformer = (T) tClass.getField("INSTANCE").get(null);
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    @Test
    public void testAll() throws DtoTransformException {
        setUpTests();

        for (int i = 0; i < makers.length; i++) {
            testOne(makers[i], verifiers[i]);
        }

        testDtoConvert();
        testXmlConvert();
    }

    private void testOne(Method make, Method verify) {
        testConversion(make, verify);
        if (make.getName().startsWith("makeXml")) {
            testRoudTripXml(make, verify);
        } else {
            testRoudTripDto(make, verify);
        }
    }

    private void testConversion(Method make, Method verify) {
        try {
            Object o = make.invoke(this);
            Object converted;
            if (make.getName().startsWith("makeXml")) {
                converted = transformer.toDto((XML) o);
            } else {
                converted = transformer.toXml((DTO) o);
            }
            verify.invoke(this, converted);
        } catch (InvocationTargetException ex) {
            if (ex.getTargetException() instanceof Error) {
                throw (Error) ex.getTargetException();
            }
            throw new RuntimeException(make.getName(), ex.getTargetException());
        } catch (Exception ex) {
            throw new RuntimeException(make.getName(), ex);
        }

    }

    private void testRoudTripXml(Method make, Method verify) {
        try {
            XML x = (XML) make.invoke(this);

            JAXBContext jaxbContext = JAXBContext.newInstance(xmlClass.getPackage().getName());

            StringWriter sw = new StringWriter();
            jaxbContext.createMarshaller().marshal(new JAXBElement(new QName("foo"), xmlClass, x), sw);
            String xml = sw.getBuffer().toString();
            StreamSource s = new StreamSource(new StringReader(xml));
            XML x2 = jaxbContext.createUnmarshaller().unmarshal(s, xmlClass).getValue();

            DTO d = transformer.toDto(x2);
            verify.invoke(this, d);

        } catch (InvocationTargetException ex) {
            if (ex.getTargetException() instanceof Error) {
                throw (Error) ex.getTargetException();
            }
            throw new RuntimeException(make.getName(), ex.getTargetException());
        } catch (Exception ex) {
            throw new RuntimeException(make.getName(), ex);
        }

    }

    private void testRoudTripDto(Method make, Method verify) {
        try {
            DTO d = (DTO) make.invoke(this);
            XML x = transformer.toXml(d);

            JAXBContext jaxbContext = JAXBContext.newInstance(xmlClass.getPackage().getName());

            StringWriter sw = new StringWriter();
            jaxbContext.createMarshaller().marshal(new JAXBElement(new QName("foo"), xmlClass, x), sw);
            String xml = sw.getBuffer().toString();
            StreamSource s = new StreamSource(new StringReader(xml));
            XML x2 = jaxbContext.createUnmarshaller().unmarshal(s, xmlClass).getValue();

            verify.invoke(this, x2);

        } catch (InvocationTargetException ex) {
            if (ex.getTargetException() instanceof Error) {
                throw (Error) ex.getTargetException();
            }
            throw new RuntimeException(make.getName(), ex.getTargetException());
        } catch (Exception ex) {
            throw new RuntimeException(make.getName(), ex);
        }

    }
}
