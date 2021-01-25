import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CarouselDialogComponent } from 'src/app/shared/modules/dialog/carousel-dialog/carousel-dialog.component';
import { Comment } from '../../../app/core/model/comment';
import { ImageModel } from '../../../app/core/model/image-model';
import { User } from '../../../app/core/model/user';
import { ImageService } from '../../../app/core/services/image/image.service';
import { AuthService } from '../../../app/core/services/security/auth-service/auth.service';
import { UserService } from '../../../app/core/services/user/user.service';

@Component({
  selector: 'app-comment-item',
  templateUrl: './comment-item.component.html',
  styleUrls: ['./comment-item.component.scss']
})
export class CommentItemComponent implements OnInit {

  @Input()
  comment: Comment;
  images: ImageModel[] = [];
  imagesLoading: boolean = false;
  user: User;
  deleteStarted: boolean = false;

  @Output()
  removeCommentEvent: EventEmitter<Comment> = new EventEmitter<Comment>();

  constructor(
    public userService: UserService,
    public authService: AuthService,
    public imageService: ImageService,
    public matDialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.fetchUser();
    this.fetchImages();
  }

  isUserAdmin(): boolean{
    return this.authService.getUserRole() == "ADMIN";
  }

  userOwnsComment(): boolean{
    return this.authService.getUserId() == this.comment.userId;
  }

  async fetchUser(): Promise<void>{
    if(this.comment)
      this.user = await this.userService.getOne(this.comment.userId).toPromise();
  }

  async fetchImages(): Promise<void>{
    this.imagesLoading = true;
    const getImagesPromises: Promise<ImageModel>[] = this.comment.imageIds.map(imageId => this.imageService.getById(imageId).toPromise());
    this.images = await Promise.all(getImagesPromises);
    this.imagesLoading = false;
  }

  showImages(){
    this.matDialog.open(CarouselDialogComponent, {
      data: {
        images: this.images.map(imageModel => ({retrievedImage: 'data:image/jpeg;base64,'+imageModel.picByte})),
        imagesLoading: false,
        enableAddAndRemove: false,
        maxImageWidth: 800,
        maxImageHeight: 800
      }
    });
  }

  delete(){
    this.deleteStarted = true;
    this.removeCommentEvent.emit(this.comment);
  }

}
