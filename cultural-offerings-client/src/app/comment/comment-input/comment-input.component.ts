import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommentInput } from 'src/app/core/model/comment-input';
import { ListChangeEvent } from 'src/app/core/model/list-change-event';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';

@Component({
  selector: 'app-comment-input',
  templateUrl: './comment-input.component.html',
  styleUrls: ['./comment-input.component.scss']
})
export class CommentInputComponent implements OnInit {

  commentForm: FormGroup;
  addClicked: boolean = false;

  chosenImageFiles: File[] = [];

  chipsHeight: number = 50;

  @Output()
  commentAddedEvent: EventEmitter<CommentInput> = new EventEmitter<CommentInput>();

  constructor(
    private formBuilder: FormBuilder,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    this.commentForm = this.formBuilder.group({
      comment: ['', Validators.required],
    });
  }

  autoGrowTextZone(e) {
    e.target.style.height = "0px";
    if(e.target.value){
      this.chipsHeight = e.target.scrollHeight + 15;
      e.target.style.height = (e.target.scrollHeight + 15)+"px";
    }
    else{
      this.chipsHeight = 50;
      e.target.style.height = "50px";
    }
  }

  isLoggedIn(){
    return this.authService.isLoggedIn();
  }

  onFileChanged(event){
    this.chosenImageFiles.push(event.target.files[0]);
    event.target.value = "";
  }

  removeImageFile(event: ListChangeEvent<File>){
    this.chosenImageFiles = this.chosenImageFiles.filter(file => file != event.item);
  }

  isFormInvalid(): boolean{
    return !!Object.values(this.commentForm.controls).find(control => control.errors);
  }

  add(){
    if(!this.isFormInvalid()){
      this.commentAddedEvent.emit({
        text: this.commentForm.value.comment,
        date: new Date(),
        images: this.chosenImageFiles
      });
      this.commentForm.patchValue({comment: ""});
      this.chosenImageFiles = [];
      this.addClicked = false;
    }else{
      this.addClicked = true;
    }
  }
}
