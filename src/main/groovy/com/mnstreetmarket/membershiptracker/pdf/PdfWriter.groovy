package com.mnstreetmarket.membershiptracker.pdf

import com.mnstreetmarket.membershiptracker.dto.ApplicationDto
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.springframework.stereotype.Service

@Service
class PdfWriter {
    PDDocument document(String text) {
        PDDocument document = new PDDocument()
        PDPage page = new PDPage()
        document.addPage(page)

        PDPageContentStream contentStream = new PDPageContentStream(document, page)
        contentStream.setLeading(14.5f)
        contentStream.setFont(PDType1Font.COURIER, 12)
        contentStream.beginText()
        contentStream.newLineAtOffset(25, 725)

        text.split('\n').each {
            writeLine(contentStream, it)
        }

        contentStream.endText()
        contentStream.close()
        return document
    }

    void generateApplicationPdf(ApplicationDto application) {
        String text = """
# Membership Application

Contact Info:
  Name: $application.contactInfo.firstName $application.contactInfo.lastName
  Phone: $application.contactInfo.phoneNumber
  Email: $application.contactInfo.email

  Are you a student? ${application.contactInfo.student ? 'Yes' : 'No'}

Address:
  $application.address.streetAddress
  $application.address.city $application.address.state, $application.address.zipCode

Family Members:
  ${application.familyMembers.familyMembers.join('\n  ')}
"""
        def document = document(text)
        document.save("${application.contactInfo.firstName}_${application.contactInfo.lastName}.pdf")
        document.close()
    }

    void writeLine(PDPageContentStream contentStream, String line){
        contentStream.showText(line)
        contentStream.newLine()
    }
}
