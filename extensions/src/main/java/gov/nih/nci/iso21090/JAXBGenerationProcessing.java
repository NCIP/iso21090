package gov.nih.nci.iso21090;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this class is to add @JaxbElement annotations to all of the ISO 21090 files.
 * @author Justin Permar
 *
 */
public class JAXBGenerationProcessing {

	private static final String XML_ROOT_ELEMENT_ANNOTATION = "@XmlRootElement";
	private static final String XML_TYPE_ANNOTATION = "@XmlType";
	private static final String RIGHT_PARENTHESE = ")";
	private static final String SEMICOLON = ";";
	private static final String JAVA_PACKAGE_DECLARATION = "package";
	private static Map<String,String> map;
	private static String NEWLINE = System.getProperty("line.separator");
	private static String IMPORT_XML_ROOT_ELEMENT = "import javax.xml.bind.annotation.XmlRootElement;";

	static {
		map = new HashMap<String,String>();

		map.put("CD.java", "Cd");
		map.put("BL.java", "Bl");
		map.put("BlNonNull.java", "BlNonNull");
		map.put("ST.java", "St");
		map.put("StNt.java", "StNt");
		map.put("Ii.java", "Ii");
		map.put("TEL.java", "Tel");
		map.put("TELPerson.java", "TelPerson");
		map.put("TelUrl.java", "TelUrl");
		map.put("TelPhone.java", "TelPhone");
		map.put("TelEmail.java", "TelEmail");
		map.put("ED.java", "Ed");
		map.put("EdText.java", "EdText");
		map.put("CD.java", "Cd");
		map.put("SC.java", "Sc");
		map.put("INT.java", "Int");
		map.put("Real.java", "Real");
		map.put("TS.java", "Ts");
		map.put("PQ.java", "Pq");
		map.put("IVLTS.java", "IvlTs");
		map.put("IVLPQ.java", "IvlPq");
		map.put("IVLREAL.java", "IvlReal");
		map.put("IVLINT.java", "IvlInt");
		map.put("Ad.java", "Ad");
		map.put("EN.java", "En");
		map.put("EnOn.java", "EnOn");
		map.put("EnPn.java", "EnPn");
		map.put("DSetII.java", "DSetII");
		map.put("DSetTel.java", "DSetTel");
		map.put("DSetCd.java", "DSetCd");
		map.put("DSetAd.java", "DSetAd");
	}

	public static String[] getFilenames() {
		return map.keySet().toArray(new String[0]);
	}

	/*
	 * The purpose of this class is to 
	 * @param directory
	 * @param filenames
	 * @throws FileNotFoundException 
	 * @throws TokenStreamException 
	 * @throws RecognitionException 
	 */

	/*
	public static void processFiles(File directory, String[] filenames) throws RecognitionException, TokenStreamException, FileNotFoundException {
		JavaSourceFactory factory = new JavaSourceFactory();
		JavaParser parser = new JavaParser(factory);
		File pFile = new File(directory, filenames[0]);
		List<JavaSource> classesParsed = parser.parse(pFile);
		for (JavaSource s : classesParsed) {
			//search for @XmlRootElement annotation
			//if not present, add annotation
			//debug
			System.out.println(s);
		}
	}
	 */

	public static void processFiles(File directory, String[] filenames) throws IOException, FileNotFoundException, ProcessingException {

		for (String filename : filenames) {
			File pFile = new File(directory, filename);
			System.out.println("Processing file:"+pFile.getName());
			String contents = getContents(pFile);
			//search for @XmlRootElement annotation
			//if not present, add annotation
			if (contents.contains(XML_ROOT_ELEMENT_ANNOTATION)) {
				continue;
			}

			//add import after package statement
			if (contents.contains(JAVA_PACKAGE_DECLARATION)) {
				int index = contents.indexOf(JAVA_PACKAGE_DECLARATION);
				int endindex = contents.indexOf(SEMICOLON, index);

				String first = contents.substring(0, endindex+1);
				String rest = contents.substring(endindex+2,contents.length()-1);

				String newImport = NEWLINE + IMPORT_XML_ROOT_ELEMENT + NEWLINE;
				String newFileContents = first.concat(newImport).concat(rest);
				setContents(pFile, newFileContents);

			}
			
			//re-load file after we made first change above
			contents = getContents(pFile);
			
			//add it after XmlType annotation
			if (!contents.contains(XML_TYPE_ANNOTATION)) {
				throw new ProcessingException("Could not find " + XML_TYPE_ANNOTATION + " in file " + pFile.getAbsolutePath());
			} else {
				//add our new annotation after this one
				int index = contents.indexOf(XML_TYPE_ANNOTATION);
				//now search forward for the closing right parenthese
				int endindex = contents.indexOf(RIGHT_PARENTHESE, index);
				//insert a newline and our text
				String first = contents.substring(0, endindex+1);
				String rest = contents.substring(endindex+2,contents.length()-1);

				String mappedValue = map.get(pFile.getName());
				if (mappedValue == null) {
					String msg = "Could not find map entry for file " + pFile.getName();
					throw new ProcessingException(msg);
				}
				String newAnnotation = NEWLINE + XML_ROOT_ELEMENT_ANNOTATION + "(name = \"" + mappedValue + "\")" + NEWLINE;
				String newFileContents = first.concat(newAnnotation).concat(rest);
				setContents(pFile, newFileContents);
			}

		}

	}

	static public String getContents(File aFile) throws IOException {
		//...checks on aFile are elided
		StringBuilder contents = new StringBuilder();

		//use buffering, reading one line at a time
		//FileReader always assumes default encoding is OK!
		BufferedReader input =  new BufferedReader(new FileReader(aFile));
		try {
			String line = null; //not declared within while loop
			/*
			 * readLine is a bit quirky :
			 * it returns the content of a line MINUS the newline.
			 * it returns null only for the END of the stream.
			 * it returns an empty String if two newlines appear in a row.
			 */
			while (( line = input.readLine()) != null){
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
		}
		finally {
			input.close();
		}

		return contents.toString();
	}

	/**
	 * Change the contents of text file in its entirety, overwriting any
	 * existing text.
	 *
	 * This style of implementation throws all exceptions to the caller.
	 *
	 * @param aFile is an existing file which can be written to.
	 * @throws IllegalArgumentException if param does not comply.
	 * @throws FileNotFoundException if the file does not exist.
	 * @throws IOException if problem encountered during write.
	 */
	static public void setContents(File aFile, String aContents)
	throws FileNotFoundException, IOException {
		if (aFile == null) {
			throw new IllegalArgumentException("File should not be null.");
		}
		if (!aFile.exists()) {
			throw new FileNotFoundException ("File does not exist: " + aFile);
		}
		if (!aFile.isFile()) {
			throw new IllegalArgumentException("Should not be a directory: " + aFile);
		}
		if (!aFile.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: " + aFile);
		}

		//use buffering
		Writer output = new BufferedWriter(new FileWriter(aFile));
		try {
			//FileWriter always assumes default encoding is OK!
			output.write( aContents );
		}
		finally {
			output.close();
		}
	}



	public static void main(String[] args) throws Exception {
		//test it
		//		String[] files = new String[] { "Ad.java" };
		//		String[] files = new String[] { "CD.java" };
		System.out.println("Executing JAXB Generation Processing..........");
		if (args.length < 1) {
			throw new ProcessingException("Pass in a directory for the location of the ISO 21090 jaxb-generated source files");
		}
		String dir = args[0];
		//String dir = "target/generated-sources/stubs/org/iso/_21090";
		processFiles(new File(dir), getFilenames());
	}

	static class ProcessingException extends Exception {
		public ProcessingException(String msg) {
			super(msg);
		}
	}
}
