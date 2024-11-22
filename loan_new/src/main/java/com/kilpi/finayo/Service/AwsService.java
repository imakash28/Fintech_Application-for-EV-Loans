package com.kilpi.finayo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Component
public class AwsService {
	 @Autowired
	    public AmazonSimpleEmailService amazonSimpleEmailService;

	    public void sendEmail(String recepient) {

	        String emailContent = "<!DOCTYPE html>\n" +
	                "<html lang=\"en\">\n" +
	                "<head>\n" +
	                "    <meta charset=\"utf-8\">\n" +
	                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
	                "    <title>Example HTML Email</title>\n" +
	                "</head>\n" +
	                "<body style=\"background: whitesmoke; padding: 30px; height: 100%\">\n" +
	                "<h5 style=\"font-size: 18px; margin-bottom: 6px\">Dear example,</h5>\n" +
	                "<p style=\"font-size: 16px; font-weight: 500\">Greetings from Finayo</p>\n" +
	                "<p>This is a simple html based email.</p>\n" +
	                "</body>\n" +
	                "</html>";

	        String senderEmail = "support@finayo.tech";
	        String receiverEmail = recepient;
	        String emailSubject = "Test Email";

	        try {
	            SendEmailRequest sendEmailRequest = new SendEmailRequest()
	                    .withDestination(
	                            new Destination().withToAddresses(receiverEmail))
	                    .withMessage(new Message()
	                            .withBody(new Body().withHtml(
	                                    new Content().withCharset("UTF-8").withData(emailContent)))
	                            .withSubject(new Content().withCharset("UTF-8").withData(emailSubject)))
	                    .withSource(senderEmail);
	            amazonSimpleEmailService.sendEmail(sendEmailRequest);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


}
