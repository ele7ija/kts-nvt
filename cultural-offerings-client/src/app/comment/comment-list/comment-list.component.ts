import { Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { PageableRequest } from '../../../app/core/model/pageable-request';
import { Comment } from '../../../app/core/model/comment';
import { CommentService } from '../../../app/core/services/comment/comment.service';
import { ImageService } from '../../../app/core/services/image/image.service';
import { ImageModel } from '../../../app/core/model/image-model';
import { CommentInput } from '../../../app/core/model/comment-input';
import { AuthService } from '../../../app/core/services/security/auth-service/auth.service';
import { PaginatorComponent } from 'src/app/shared/modules/paginator/paginator.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SimpleSnackbarComponent } from 'src/app/shared/components/snackbar/simple-snackbar/simple-snackbar.component';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.scss']
})
export class CommentListComponent implements OnChanges {

  @Input()
  culturalOfferingId: number;

  comments: Comment[] = [];
  totalLength: number;
  loading: boolean = false;
  pageSizeOptions: number[] = [5,10,20];
  pageIndex: number = 0;

  @ViewChild(PaginatorComponent) paginator: PaginatorComponent;

  constructor(
    public authService: AuthService,
    public commentService: CommentService,
    public imageService: ImageService,
    public matSnackBar: MatSnackBar
  ) { }

  ngOnChanges(changes: SimpleChanges) {
    if(changes.culturalOfferingId.currentValue){
      this.fetchComments({page: 0, size: this.pageSizeOptions[0]});  
    }
  }

  async fetchComments(pageableRequest: PageableRequest){
    this.loading = true;
    this.pageIndex = pageableRequest.page;
    pageableRequest.sort = 'date';
    pageableRequest.sortOrder = 'desc';
    if(this.culturalOfferingId){
      try{
        const pageableResponse = await this.commentService.getAllByCulturalOfferingId(pageableRequest, this.culturalOfferingId).toPromise();
        this.comments = pageableResponse.content;
        this.totalLength = pageableResponse.totalElements;
      }catch{
  
      }
    }
    this.loading = false;
  }

  async getUploadImagesPromise(files: File[]): Promise<ImageModel[]>{
    return Promise.all(
        files.map(
          file => this.imageService.upload(file).toPromise()
        )
    );
  }

  async commentAddedEvent(event: CommentInput){
    try{
      let imageIds: number[] = [];
      if(event.images.length != 0){
        const imageModels = await this.getUploadImagesPromise(event.images);
        imageIds = imageModels.map(imageModel => imageModel.id);
      }
      let newComment: Comment = {
        text: event.text,
        date: event.date,
        imageIds,
        culturalOfferingId: this.culturalOfferingId,
        userId: this.authService.getUserId()
      };
      newComment = await this.commentService.insert(newComment).toPromise();
      if(this.pageIndex == 0){
        this.comments.unshift(newComment);
        if(this.comments.length > this.paginator.pageSize)
          this.comments.splice(this.comments.length - 1, 1);
        this.totalLength += 1;
      }else{
        this.pageIndex = 0;
        this.fetchComments({page: this.pageIndex, size: this.paginator.pageSize});
      }
      this.showSnackbar('USPESNO DODAVANJE', `Uspesno ste dodali komentar za kulturnu ponudu`, true);
    }catch(error){
      this.showSnackbar('GRESKA', `${error.message}`, false);
      console.log(error);
    }
  }

  async removeCommentEvent(comment: Comment){
    await this.commentService.delete(comment.id).toPromise();
    if(this.totalLength % this.paginator.pageSize == 1 && Math.floor(this.totalLength / this.paginator.pageSize) == this.pageIndex){
      this.pageIndex = Math.max(0, this.pageIndex - 1);
    }
    this.fetchComments({page: this.pageIndex, size: this.paginator.pageSize});
    this.showSnackbar('USPESNO BRISANJE', `Uspesno ste obrisali komentar za kulturnu ponudu`, true);
  }

  showSnackbar(title: string, message: string, success: boolean) {
    this.matSnackBar.openFromComponent(SimpleSnackbarComponent, {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 4000,
      data: {
        title,
        message,
        success,
      },

    });
  }
}
