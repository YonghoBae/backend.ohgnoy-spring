package backend.ohgnoy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //클라이언트 처음 웹소켓에 접속하는 엔드포인트
        //ex) ws://localhost:8080/ws-chat
        registry.addEndpoint("ws-chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //1. 메세지를 구독(수신)하는 요청 URL의 접두사 설정
        registry.enableSimpleBroker("/sub");

        //2. 메세지를 발행(송신)하는 요청 URL의 접두사 설정
        registry.setApplicationDestinationPrefixes("/pub");
    }
}
