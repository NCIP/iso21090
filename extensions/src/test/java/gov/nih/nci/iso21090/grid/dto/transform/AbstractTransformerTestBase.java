/**
 * The software subject to this notice and license includes both human readable
 * source code form and machine readable, binary, object code form. The ISO21090
 * Software was developed in conjunction with the National Cancer Institute
 * (NCI) by NCI employees and 5AM Solutions, Inc. (5AM). To the extent
 * government employees are authors, any rights in such works shall be subject
 * to Title 17 of the United States Code, section 105.
 *
 * This ISO21090 Software License (the License) is between NCI and You. You (or
 * Your) shall mean a person or an entity, and all other entities that control,
 * are controlled by, or are under common control with the entity. Control for
 * purposes of this definition means (i) the direct or indirect power to cause
 * the direction or management of such entity, whether by contract or otherwise,
 * or (ii) ownership of fifty percent (50%) or more of the outstanding shares,
 * or (iii) beneficial ownership of such entity.
 *
 * This License is granted provided that You agree to the conditions described
 * below. NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 * no-charge, irrevocable, transferable and royalty-free right and license in
 * its rights in the ISO21090 Software to (i) use, install, access, operate,
 * execute, copy, modify, translate, market, publicly display, publicly perform,
 * and prepare derivative works of the ISO21090 Software; (ii) distribute and
 * have distributed to and by third parties the ISO21090 Software and any
 * modifications and derivative works thereof; and (iii) sublicense the
 * foregoing rights set out in (i) and (ii) to third parties, including the
 * right to license such rights to further third parties. For sake of clarity,
 * and not by way of limitation, NCI shall have no right of accounting or right
 * of payment from You or Your sub-licensees for the rights granted under this
 * License. This License is granted at no charge to You.
 *
 * Your redistributions of the source code for the Software must retain the
 * above copyright notice, this list of conditions and the disclaimer and
 * limitation of liability of Article 6, below. Your redistributions in object
 * code form must reproduce the above copyright notice, this list of conditions
 * and the disclaimer of Article 6 in the documentation and/or other materials
 * provided with the distribution, if any.
 *
 * Your end-user documentation included with the redistribution, if any, must
 * include the following acknowledgment: This product includes software
 * developed by 5AM and the National Cancer Institute. If You do not include
 * such end-user documentation, You shall include this acknowledgment in the
 * Software itself, wherever such third-party acknowledgments normally appear.
 *
 * You may not use the names "The National Cancer Institute", "NCI", or "5AM"
 * to endorse or promote products derived from this Software. This License does
 * not authorize You to use any trademarks, service marks, trade names, logos or
 * product names of either NCI or 5AM, except as required to comply with the
 * terms of this License.
 *
 * For sake of clarity, and not by way of limitation, You may incorporate this
 * Software into Your proprietary programs and into any third party proprietary
 * programs. However, if You incorporate the Software into third party
 * proprietary programs, You agree that You are solely responsible for obtaining
 * any permission from such third parties required to incorporate the Software
 * into such third party proprietary programs and for informing Your
 * sub-licensees, including without limitation Your end-users, of their
 * obligation to secure any required permissions from such third parties before
 * incorporating the Software into such third party proprietary software
 * programs. In the event that You fail to obtain such permissions, You agree
 * to indemnify NCI for any claims against NCI by such third parties, except to
 * the extent prohibited by law, resulting from Your failure to obtain such
 * permissions.
 *
 * For sake of clarity, and not by way of limitation, You may add Your own
 * copyright statement to Your modifications and to the derivative works, and
 * You may provide additional or different license terms and conditions in Your
 * sublicenses of modifications of the Software, or any derivative works of the
 * Software as a whole, provided Your use, reproduction, and distribution of the
 * Work otherwise complies with the conditions stated in this License.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO
 * EVENT SHALL THE NATIONAL CANCER INSTITUTE, 5AM SOLUTIONS, INC. OR THEIR
 * AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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
