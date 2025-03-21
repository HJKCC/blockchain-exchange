import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author chencheng0816@gmail.com
 * @date 2020/1/8 10:51
 * @Description TestLiu
 */
public class TestLiu {
	private static XWPFDocument document;

	@Test
	public void countWords() {
		String allStr = readWord("C:\\Users\\ThinkPad\\Desktop\\liu\\大纲词专词.docx");
		String[] alls = allStr.split("\\s+|\n");
		List<String>  allList = Arrays.asList(alls);

		String clinicalStr = readWord("C:\\Users\\ThinkPad\\Desktop\\liu\\临床篇生词专名.docx");
		String[] clinicals = clinicalStr.split("\\s+|\n");
		List<String>  clinicalList = Arrays.asList(clinicals);

		String internStr = readWord("C:\\Users\\ThinkPad\\Desktop\\liu\\实习篇生词专名.docx");
		String[] interns = internStr.split("\\s+|\n");
		List<String>  internList = Arrays.asList(interns);

		String clinicalExtraStr = readWord("C:\\Users\\ThinkPad\\Desktop\\liu\\临床篇附加词.docx");
		String[] clinicalExtras = clinicalExtraStr.split("\\s+|\n");
		List<String>  clinicalExtraList = Arrays.asList(clinicalExtras);

		String internExtraStr = readWord("C:\\Users\\ThinkPad\\Desktop\\liu\\实习篇附加词.docx");
		String[] internExtras = internExtraStr.split("\\s+|\n");
		List<String>  internExtraList = Arrays.asList(internExtras);

		String allComStr = readWord("C:\\Users\\ThinkPad\\Desktop\\liu\\大纲普名.docx");
		String[] allComs = allComStr.split("\\s+|\n");
		List<String>  allComList = Arrays.asList(allComs);

		String internComStr = readWord("C:\\Users\\ThinkPad\\Desktop\\liu\\实习篇普名.docx");
		String[] internComs = internComStr.split("\\s+|\n");
		List<String>  internComList = Arrays.asList(internComs);

		List<String> internOfAll = allList.stream().filter(internList :: contains).collect(toList());
		List<String> clinicalOfAll = allList.stream().filter(clinicalList :: contains).collect(toList());
		List<String> internAndClinical = internOfAll.stream().filter(clinicalOfAll :: contains).collect(toList());

		List<String> internExtraAndClinicalExtra = clinicalExtraList.stream().filter(internExtraList :: contains).collect(toList());

		List<String> internComOfallCom = allComList.stream().filter(internComList :: contains).collect(toList());

		try {
			//新建一个文档
			document = new XWPFDocument(); //document对象只创建一次，保证内容不被覆盖
//			writeWord("实习篇与大纲的共同词", internOfAll);
//			writeWord("临床篇与大纲的共同词", clinicalOfAll);
//			writeWord("实习篇、临床篇和大纲三个的共同词", internAndClinical);
//			writeWord("临床篇附加词和实习篇附加词的共同词", internExtraAndClinicalExtra);
			writeWord("实习篇普名与大纲普名的共同词", internComOfallCom);
		} finally {
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("over");


	}

	public static void readNIO(String pathname) {
		try {
			FileInputStream file = new FileInputStream(new File(pathname));
			FileChannel fileChannel = file.getChannel();

			int length = -1;
			int offset = 0;
			ByteBuffer buffer = ByteBuffer.allocate(100 * 1024);
			while((length = fileChannel.read(buffer)) != -1) {
				buffer.clear();

				byte[] bytes = buffer.array();
				String str = new String(bytes, 0, length, "gbk");
				offset += length;
				System.out.println(str);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readWord(String pathname) {
			String buffer = "";
			try {
				if (pathname.endsWith(".doc")) {
					InputStream is = new FileInputStream(new File(pathname));
					WordExtractor ex = new WordExtractor(is);
					buffer = ex.getText();
					ex.close();
				} else if (pathname.endsWith("docx")) {
					OPCPackage opcPackage = POIXMLDocument.openPackage(pathname);
					POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
					buffer = extractor.getText();
					extractor.close();
				} else {
					System.out.println("此文件不是word文件！");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return buffer;
	}

	public static void writeWord(String text, List<String> words) {
		//创建一个段落
		XWPFParagraph para = document.createParagraph();
		//一个XWPFRun代表具有相同属性的一个区域
		XWPFRun run = para.createRun();
		run.setBold(true);//加粗
		run.setText(text);
		for (String str : words) {
			run = para.createRun();
			run.setText(str + "  ");
		}

		OutputStream os;
		try {
			os = new FileOutputStream("C:\\Users\\ThinkPad\\Desktop\\liu\\统计结果.docx");
			// document = new XWPFDocument();  此写法内容会被覆盖
			//把doc输出到输出流
			document.write(os);
			//关闭流
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
