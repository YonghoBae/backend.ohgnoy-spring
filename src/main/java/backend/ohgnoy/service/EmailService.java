package backend.ohgnoy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public int sendAuthEmail(String toEmail) {
        //1. 6자리 난수 생성
        Random random = new Random();
        int authNumber = random.nextInt(888888) + 111111;
        //2. 이메일 객체 생성 및 발송
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(toEmail);
        message.setSubject("Ohgnoy 메일 인증");
        message.setText("인증번호를 입력해주세요 \n\n\n\n\n\n" + authNumber);

        javaMailSender.send(message);

        //3. 발송된 난수 반환
        return authNumber;
    }
}
