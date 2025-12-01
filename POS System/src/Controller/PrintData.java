package Controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import systemgui.PosGui;

public class PrintData {

    // public static byte[] merge(byte[] a, byte[] b, byte[] c, byte[] d) {
    //     byte[] r = new byte[a.length + b.length];
    //     System.arraycopy(a, 0, r, 0, a.length);
    //     System.arraycopy(b, 0, r, a.length, b.length);
    //     return r;
    // }

    public static byte[] merge(byte[] a, byte[] b, byte[] c, byte[] d) {
        
        byte[] r = new byte[a.length + b.length + c.length + d.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        System.arraycopy(c, 0, r, a.length + b.length, c.length);      
        System.arraycopy(d, 0, r, a.length + b.length + c.length, d.length);

        return r;
    }



    public static void printTicket(){

        // Get Ticket
        String ticketInformation = ticketGenerator();

        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        
        if (service == null) {
            JOptionPane.showMessageDialog(null, "Print not Found");
            return;
        }

        try {
            
            DocPrintJob job = service.createPrintJob();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

            byte[] init = new byte[] {0x1B, 0x40};
            byte[] cut = new byte[] {0x1B, 0x64, 0x03};
            byte[] end = new byte[] {0x1B, 0x64, 0x00};
            byte[] bytes = merge(init, (ticketInformation).getBytes(), cut, end);

            InputStream stream = new ByteArrayInputStream(bytes);
            Doc doc = new SimpleDoc(stream, flavor, null);

            job.print(doc, null);

            stream.close();

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }


        // Print with non thermal printer
        
        // try {
            
        //     java.awt.print.PrinterJob job = java.awt.print.PrinterJob.getPrinterJob();
            
        //     job.setPrintable(new java.awt.print.Printable() {
        //         @Override
        //         public int print(java.awt.Graphics g, java.awt.print.PageFormat pf, int pageIndex) {
                    
        //             if (pageIndex > 0) return NO_SUCH_PAGE;

        //             java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
        //             g2d.translate(pf.getImageableX(), pf.getImageableY());

        //             g.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 10));
                    
        //             String[] lines = ticketInformation.split("\n");
        //             int y = 15; 

        //             for (String line : lines) {
        //                 g.drawString(line, 10, y); 
        //                 y += 12; 
        //             }

        //             return PAGE_EXISTS;
        //         }
        //     });

            
        //     boolean doPrint = job.printDialog(); 
        //     if (doPrint) {
        //         job.print();
        //     }

        // } catch (Exception e) {
        //     JOptionPane.showMessageDialog(null, "Print Error: " + e.getMessage());
        //     e.printStackTrace();
        // }

    }


   public static String ticketGenerator() {

        DefaultTableModel model = (DefaultTableModel) PosGui.table.getModel();
        StringBuilder ticket = new StringBuilder();

        ticket.append("\n");
        ticket.append("                   My Company\n");
        ticket.append("\n");
        ticket.append("------------------------------------------------\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = formatter.format(LocalDateTime.now());
        ticket.append("Date: " + formattedDate + "\n");

        ticket.append("================================================\n");


        ticket.append(String.format("%-8s%-28s%12s%n", "Cant.", "Product", "Import"));
        ticket.append("\n");

        for (int i = 0; i < model.getRowCount(); i++) {
            ticket.append(String.format(
                "%-8s%-28s%12s%n",
                model.getValueAt(i, 2),
                model.getValueAt(i, 0),
                model.getValueAt(i, 3)
            ));
        }

        ticket.append("================================================\n");

        ticket.append(String.format("%-20s%28s%n", "Subtotal:", PosGui.subTotalAmount.getText()));
        ticket.append(String.format("%-20s%28s%n", "TAX:", PosGui.taxAmount.getText()));
        ticket.append(String.format("%-20s%28s%n", "Total:", PosGui.totalAmount.getText()));

        ticket.append("------------------------------------------------\n");
        ticket.append("\n");
        ticket.append("                Thanks for buying!\n");
        ticket.append("\n\n\n\n\n");

        return ticket.toString();
    }

    
}