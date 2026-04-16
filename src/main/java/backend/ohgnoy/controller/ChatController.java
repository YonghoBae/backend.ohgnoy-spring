package backend.ohgnoy.controller;

import backend.ohgnoy.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    //특정 브로커로 메시지를 전달하는 템플릿 객체
    private final SimpMessageSendingOperations messagingTemplate;

    //클라이언트가 "/pub/chat/message" 로 메시지를 보내면 이 메서드가 가로챔
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        // 입장/퇴장 메시지 세팅
        if(ChatMessageDto.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }else if(ChatMessageDto.MessageType.LEAVE.equals(message.getType())){
            message.setMessage(message.getSender() + "님이 퇴장하셨습니다.");
        }

        //해당 채팅방(roomId)을 구독 중인 클라이언트들에게 메세지 브로드캐스트
        messagingTemplate.convertAndSend("/sub/chat/room" + message.getRoomId(), message);
    }
}
