import { Component, OnInit } from '@angular/core';
import {Message} from "../model/message";
import {ChatService} from "../chat.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {

  messages: Message[] = [];
  newMessage: Message = { content: '', sender: '' };

  constructor(private chatService: ChatService) { }

  ngOnInit(): void {
    this.loadMessages();
  }

  loadMessages() {
    this.chatService.getAllMessages().subscribe(messages => {
      this.messages = messages;
    });
  }

  sendMessage() {
    this.chatService.sendMessage(this.newMessage).subscribe(() => {
      this.loadMessages();
      this.newMessage.content = '';
    });
  }
}
