package com.suprimentos.suprimentosfilhos.service;

import com.suprimentos.suprimentosfilhos.domain.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendLowStockEmail(Product product) {
        String to = product.getUser().getEmail();
        String subject = "Produto está quase acabando: " + product.getName();
        String body = String.format(
                "O produto '%s' está acabando. Quantidade restante: %d %s. Considere comprar o mais breve possível",
                product.getName(),
                product.getLeftQuantity(),
                product.getUnit()
        );


        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, false);

            mailSender.send(mimeMessage);

            System.out.println("Email enviado para " + to + " sobre produto: " + product.getName());
        } catch (MessagingException e) {
            System.err.println("Erro ao enviar email: " + e.getMessage());
        }

    }
}
