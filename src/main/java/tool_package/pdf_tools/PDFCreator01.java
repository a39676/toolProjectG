package tool_package.pdf_tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFCreator01 {

	class MainCreator {
		private static final String FILE = "D:/auxiliary/tmp/1.pdf";
		
	}

	class PDFSubTools {
		
		
		private void addTitleCell(PdfPTable tmpTable, PdfPCell tmpCell, 
				int setRowspan, int setColspan, String title) {
			Font fontTitle = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD); // 样式
			tmpCell.setColspan(setColspan); //设置列宽
			tmpCell.setRowspan(setRowspan); //设置行高
			tmpCell.setUseVariableBorders(true); // 激活框体样式修改
			tmpCell.setBorderWidth(0.8f);
			tmpCell.setBorderColorBottom(BaseColor.WHITE); // 框体底线设白色
			tmpCell.addElement(new Phrase(title, fontTitle)); // 添加标题内容  设置样式
			tmpCell.setHorizontalAlignment(Element.ALIGN_LEFT); // 左对齐
			tmpTable.addCell(tmpCell); // 添加进表格
		}
		
		private void addTitleCell02(HashMap<String, Object> infoMap) {
			if (infoMap.containsKey("tmpCell") && infoMap.containsKey("tmpTable")) {
				Font fontTitle = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD); // 样式
				PdfPCell tmpCell = (PdfPCell) infoMap.get("tmpCell");
				PdfPTable tmpTable = (PdfPTable) infoMap.get("tmpTable");
				tmpCell.setColspan((infoMap.containsKey("setColspan")) ? ((Integer) infoMap.get("setColspan")) : 1);//设置列宽
				tmpCell.setRowspan((infoMap.containsKey("setRowspan")) ? ((Integer) infoMap.get("setRowspan")) : 1); //设置行高
				tmpCell.setUseVariableBorders(true); // 激活框体样式修改
				tmpCell.setBorderWidth(0.8f);
				tmpCell.setBorderColorBottom(BaseColor.WHITE); // 框体底线设白色
				tmpCell.addElement(new Phrase((String) infoMap.get("title"), fontTitle)); // 添加标题内容  设置样式
				tmpCell.setHorizontalAlignment(Element.ALIGN_LEFT); // 左对齐
				tmpTable.addCell(tmpCell); // 添加进表格
			} else {
				System.out.println("something lost");
			}
		}
		
		private void addContentCell(PdfPTable tmpTable, PdfPCell tmpCell, 
				int setRowspan, int setColspan, String content) {
			Font fontContext = new Font(Font.FontFamily.COURIER, 7, Font.BOLD); // 样式
			tmpCell.setColspan(setColspan);
			tmpCell.setRowspan(setRowspan);
			tmpCell.setUseVariableBorders(true);
			tmpCell.setBorderWidth(0.8f);
			tmpCell.setBorderColorTop(BaseColor.WHITE); // 框体顶线设置成白色
			tmpCell.addElement(new Phrase(content, fontContext));
			tmpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			tmpTable.addCell(tmpCell);
		}
		

		private void addContent(Document document) throws DocumentException, MalformedURLException, IOException {
			
			String targetImage = "src/main/resources/images/mskLogo02.png"; // 图片路径
			Image img = Image.getInstance(targetImage); // 实例化图片
			img.scalePercent(30f);
			Font fontTitle = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
			
			PdfPTable table01 = new PdfPTable(36); // 新设表格, 设36列
			table01.setWidthPercentage(110); // 设表格占页面宽比例(%)
			PdfPCell tmpCell = new PdfPCell();
			
			// 插入图片
			tmpCell.setRowspan(2);
			tmpCell.setColspan(18);
			tmpCell.setUseVariableBorders(true); // 激活框体样式修改
			tmpCell.setBorderWidth(0.8f);
			tmpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tmpCell.addElement(img);
			table01.addCell(tmpCell);
			
			// 开始添加元素格
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 9, "Document Type");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 9, "SCAC");

			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 9, "BILL OF LADING FOR OCEAN TRANSPORT");

			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 9, "SCAC info");
			
			
			// next big row
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 18, "SHIPPER");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 9, "BookingNo.");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 9, "B/L No.");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 3, 18, "SHIPPER info SHIPPER info SHIPPER info SHIPPER info "
					+ "\rline2"
					+ "\rline3"
					+ "\rline4"
					+ "\rline5"
					+ "\rline6");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 9, "booking No info\r"
					+ "\r"); 
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 9, "B/L No. info");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 9, "Export Reference");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 9, "Svc Contradt");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 9, "export reference info");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 9, "svc contradt info");
			
			
			// next big row
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 18, "CONSIGNEE:");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 18, "NOTIFY PARTY:");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 3, 18, "CONSIGNEE info CONSIGNEE info CONSIGNEE info "
					+ "\rL2"
					+ "\rL3"
					+ "\rL4"
					+ "\rL5"
					+ "\rL6");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 18, "NOTIFY PARTY NOTIFY PARTY NOTIFY PARTY NOTIFY PARTY "
					+ "\r2"
					+ "\r3"
					+ "\r4"
					+ "\r5"
					+ "\r6");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 18, "Onward Inland Routing：");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 18, "Onward Inland Routing info");
			
			
			// next big row
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 9, "VESSEL ");

			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 9, "VOYAGE NO.");

			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 18, "Place of Receipt");

			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 9, "VESSEL info");

			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 9, "VOYAGE NO. info");

			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 18, "Place of Receipt info");
			
			
			// next big row
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 9, "Port of Loading");

			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 9, "Port of Discharge");

			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 18, "Place of Delivery");

			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 9, "Port of Loading info ");

			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 9, "Port of Discharge info");

			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 18, "Place of Delivery info");
			
			
			// next big row
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 36, "                           "
					+ "                                                       "
					+ "                                  "
					+ "PARTICULARS FURNISHED BY SHIPPER");
			
			
			// next big row
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 24, "Kind of Packages; Description of goods; Marks and Numbers;  Container No./Seal No.");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 4, "TOTAL PACKAGE");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 4, "GROSS WEIGHT");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 4, "MEASUREMENT\nCBM");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 24, "something something something something something \r"
					+ "2\r"
					+ "3\r"
					+ "4\r"
					+ "5\r"
					+ "6\r"
					+ "7\r"
					+ "8\r"
					+ "9\r"
					+ "10\r"
					+ "11\r"
					+ "12\r"
					+ "13\r"
					+ "14\r"
					+ "15\r"
					+ "16\r"
					+ "17\r"
					+ "18\r");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 4, "packages");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 4, "WEIGHT");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 4, "MEASUREMENT");
			
			
			// next big row
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 12, "Freight & Charges");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 12, "Rate&Currency");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 6, "Prepaid");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 6, "Collect");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 12, "Freight & Charges info");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 12, "Rate&Currency info");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 6, "Prepaid info");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 6, "Collect info");
			
			
			// next big row
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 12, "Carrier's Receipt Total number ");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 12, "Place of Issus of B/L");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 12, "Additional Comments");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 12, "Carrier's Receipt Total number info");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 12, "Place of Issus of B/L info");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 5, 12, "Additional Comments info");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 12, "Number & Sequence of Original B(s)/L ");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 12, "Date of Issue of B/L");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 12, "Number & Sequence of Original B(s)/L info");
			
			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 12, "Date of Issue of B/L");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 12, "Declared Value (see clause 7.3) ");
			
			tmpCell = new PdfPCell();
			addTitleCell(table01, tmpCell, 1, 12, "Shipped on Board Date (Local Time)");

			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 12, "Declared Value (see clause 7.3) info");

			tmpCell = new PdfPCell();
			addContentCell(table01, tmpCell, 1, 12, "ShippedDate (Local Time) info");
			
			
			document.add(table01);
			
		}

	}

	public static void main(String[] args) {
		PDFCreator01.MainCreator creator = new PDFCreator01().new MainCreator();
		PDFCreator01.PDFSubTools tools = new PDFCreator01().new PDFSubTools();
		
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(creator.FILE));
			document.open();
			tools.addContent(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
