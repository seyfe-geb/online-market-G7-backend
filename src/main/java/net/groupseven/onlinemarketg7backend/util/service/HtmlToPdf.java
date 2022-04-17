package net.groupseven.onlinemarketg7backend.util.service;

import com.online.market.order.dto.OrderDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class HtmlToPdf {

    public void generateInvoicePdf(String htmlTemplate, String outputFile, OrderDto order) {
        try {
            String basePath = System.getProperty("user.dir");
            htmlTemplate = Path.of(basePath, htmlTemplate).toString();
            // HTML file - Input
            File inputHTML = new File(htmlTemplate);
            // Converted PDF file - Output
            File outputPdf = new File(outputFile);
            //create well formed HTML
            String xhtml = createWellFormedHtml(inputHTML, order);
            System.out.println("Starting conversion to PDF...");
            xhtmlToPdf(xhtml, outputPdf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createWellFormedHtml(File inputHTML, OrderDto order) throws IOException {
        Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        Element elem = document.select("span[name=\"order-id\"]").first();
        TextNode orderIdNode = new TextNode(order.getId() + "");
        elem.replaceWith(orderIdNode);

        elem = document.select("span[name=\"customer-name\"]").first();
        TextNode customerNameNode = new TextNode(order.getUser().getFname() + " " + order.getUser().getLname());
        elem.replaceWith(customerNameNode);

        elem = document.select("span[name=\"total-price\"]").first();
        TextNode priceNode = new TextNode(order.getPrice() + "");
        elem.replaceWith(priceNode);

        elem = document.select("p[name=\"items\"]").first();
        for (int i = 0; i < order.getOrderItems().size(); i++) {
            elem.append("<span class=\"item-number\">" + (i + 1) + ": </span>");
            elem.append("<span name=\"product-item-name\" style=\"color:blue; font-size:20px\">"
                    + order.getOrderItems().get(i).getQuantity() + " - "
                    + order.getOrderItems().get(i).getProduct().getName() + "</span>\n");
            elem.append("<span name=\"product-item-price\" style=\"color:blue; font-size:20px\">"
                    + order.getOrderItems().get(i).getProduct().getPrice() + "</span>\n");
            elem.append("<br />");
        }
        System.out.println("HTML parsing done...");
        return document.html();
    }

    private void xhtmlToPdf(String xhtml, File outputPdf) throws Exception {
        OutputStream outputStream = null;
        String basePath = System.getProperty("user.dir");

        try {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            // Register custom ReplacedElementFactory implementation
            sharedContext.setReplacedElementFactory(new ReplacedElementFactoryImpl());
            sharedContext.getTextRenderer().setSmoothingThreshold(0);
            // Register additional font

            renderer.getFontResolver().addFont(
                    Path.of(basePath, "src\\main\\resources\\fonts\\PRISTINA.ttf").toString()
                    , true);
            // Setting base URL to resolve the relative URLs
            String baseUrl = FileSystems.getDefault()
                    .getPath("")
                    .toUri()
                    .toURL()
                    .toString();
            renderer.setDocumentFromString(xhtml, baseUrl);
            renderer.layout();
            outputStream = new FileOutputStream(outputPdf);
            renderer.createPDF(outputStream);
            System.out.println("PDF creation completed");
        }
        finally {
            if(outputStream != null)
                outputStream.close();
        }
    }
}