import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommentItemComponent } from './comment-item/comment-item.component';
import { CommentListComponent } from './comment-list/comment-list.component';
import { CommentService } from '../core/services/comment/comment.service';
import { PaginatorModule } from '../shared/modules/paginator/paginator.module';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { UserService } from '../core/services/user/user.service';
import { MatIconModule } from '@angular/material/icon';
import { CommentInputComponent } from './comment-input/comment-input.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ImageService } from '../core/services/image/image.service';
import { ImageFileChipsComponent } from './image-file-chips/image-file-chips.component';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DialogModule } from '../shared/modules/dialog/dialog.module';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [CommentItemComponent, CommentListComponent, CommentInputComponent, ImageFileChipsComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    MatIconModule,
    MatChipsModule,
    MatFormFieldModule,
    MatSnackBarModule,
    PaginatorModule,
    DialogModule
  ],
  providers: [
    CommentService,
    UserService,
    ImageService
  ],
  exports: [CommentListComponent]
})
export class CommentModule { }
