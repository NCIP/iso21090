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
package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.zip.GZIPOutputStream;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mshestopalov
 */
public class EdTest {

    private Ed t;
    private final String phrase = "this+is+the=way+the+world+ends";

    @Before
    public void init() {
        t = new Ed();
    }

    @Test
    public void testEquality() {
        t.setCompression(Compression.BZ);
        t.setData(phrase.getBytes());
        St st = new St();
        st.setValue(phrase);
        t.setDescription(st);
        t.setNullFlavor(NullFlavor.NA);
        TelUrl a = new TelUrl();
        a.setValue(URI.create("http:"+phrase));
        t.setValue(phrase);
        assertTrue(t.equals(t));
        assertFalse(t.equals(null));

        Ed t2 = new Ed();
        t2.setCompression(Compression.BZ);
        t2.setData(phrase.getBytes());
        St st2 = new St();
        st2.setValue(phrase);
        
        t2.setDescription(st2);
        t2.setNullFlavor(NullFlavor.NA);
        t2.setValue(phrase);

        assertTrue(t.equals(t2));

        St st3 = new St();
        st3.setValue(phrase+"something extra");
        t2.setDescription(st3);

        assertFalse(t.equals(t2));
    }

    @Test
    public void testHashCode() {
        t.setCompression(Compression.BZ);
        t.setData(phrase.getBytes());

        St st = new St();
        st.setValue(phrase);
        t.setDescription(st);
        
        t.setNullFlavor(NullFlavor.NA);
        TelUrl a = new TelUrl();
        a.setValue(URI.create("http:"+phrase));
        t.setValue(phrase);
        assertTrue(t.equals(t));
        assertFalse(t.equals(null));

        Ed t2 = new Ed();
        t2.setCompression(Compression.BZ);
        t2.setData(phrase.getBytes());
        
        St st2 = new St();
        st2.setValue(phrase);
        t2.setDescription(st2);

        t2.setNullFlavor(NullFlavor.NA);
        t2.setValue(phrase);

        assertEquals(t.hashCode(), t2.hashCode());
        
        
        St st3 = new St();
        st3.setValue(phrase+"something extra");
        t2.setDescription(st3);
        assertFalse(t.hashCode() == t2.hashCode());
    }

    @Test
    public void testCloneable() {
        t.setCompression(Compression.BZ);
        t.setData(phrase.getBytes());

        St st = new St();
        st.setValue(phrase);
        t.setDescription(st);

        t.setNullFlavor(NullFlavor.NA);
        TelUrl a = new TelUrl();
        a.setValue(URI.create("http:"+phrase));
        t.setValue(phrase);
        assertTrue(t.equals(t));
        assertFalse(t.equals(null));

        Ed t2 = t.clone();

        assertTrue(t != t2);
        assertTrue(t.equals(t2));
        assertEquals(t.hashCode(), t2.hashCode());
    }

    @Test
    public void testNullUncompression() throws Exception {
        t.setData(null);
        InputStream is = t.getDataUncompressed();
        assertEquals(0, is.available());
    }

    @Test(expected = IOException.class)
    public void testNullUncompressionWithGZ() throws Exception {
        t.setData(null);
        t.setCompression(Compression.GZ);
        t.getDataUncompressed();  // valid GZIP streams have some preamble
    }

    @Test
    public void testEmptyUncompression() throws Exception {
        t.setData(new byte[] {});
        InputStream is = t.getDataUncompressed();
        assertEquals(0, is.available());
    }

    @Test
    public void testSimpleUncompression() throws Exception {
        byte[] bytes = new byte[] {1, 2, 3, 4, 5};
        t.setData(bytes);
        InputStream is = t.getDataUncompressed();
        for (int i = 1; i < 6; ++i) {
            assertEquals(i, is.read());
        }
        assertEquals(0, is.available());
    }

    @Test
    public void testGZEmptyUncompression() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = new GZIPOutputStream(baos);
        gzos.close();
        t.setData(baos.toByteArray());
        t.setCompression(Compression.GZ);

        InputStream is = t.getDataUncompressed();
        baos = new ByteArrayOutputStream();
        int cur;
        while ((cur = is.read()) != -1) {
            baos.write(cur); // blindingly fast
        }
        is.close();
        assertEquals(0, baos.toByteArray().length);
    }

    @Test
    public void testGZSimpleUncompression() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = new GZIPOutputStream(baos);
        byte[] value = "something to be gziped".getBytes();
        gzos.write(value);
        gzos.close();
        t.setData(baos.toByteArray());
        t.setCompression(Compression.GZ);

        InputStream is = t.getDataUncompressed();
        baos = new ByteArrayOutputStream();
        int cur;
        while ((cur = is.read()) != -1) {
            baos.write(cur); // blindingly fast
        }
        is.close();
        byte[] receivedBytes = baos.toByteArray();
        assertTrue(Arrays.equals(value, receivedBytes));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonGZUncompression() throws Exception {
        t.setData(new byte[] {1, 2, 3});
        t.setCompression(Compression.ZL);
        t.getDataUncompressed();
    }
}
